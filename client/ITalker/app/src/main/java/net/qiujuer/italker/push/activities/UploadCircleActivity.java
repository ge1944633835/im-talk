package net.qiujuer.italker.push.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import net.qiujuer.italker.common.app.ToolbarActivity;
import net.qiujuer.italker.factory.Factory;
import net.qiujuer.italker.factory.model.api.RspModel;
import net.qiujuer.italker.factory.model.api.circle.CircleModel;
import net.qiujuer.italker.factory.net.Network;
import net.qiujuer.italker.factory.net.RemoteService;
import net.qiujuer.italker.factory.net.UploadHelper;
import net.qiujuer.italker.factory.persistence.Account;
import net.qiujuer.italker.push.R;
import net.qiujuer.italker.push.frags.media.GalleryFragment;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author 戈传光
 *         发朋友圈
 */
public class UploadCircleActivity extends ToolbarActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.upload_circle_et_content)
    EditText mEtContent;
    @BindView(R.id.upload_circle_img_add)
    ImageView mImgAdd;

    @BindView(R.id.upload_circle_img_preView)
    ImageView mImgpreView;

    private String imgPath;

    /**
     * 显示发朋友圈界面
     *
     * @param context 上下文
     */
    public static void show(Context context) {
        Intent intent = new Intent(context, UploadCircleActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_upload_circle;
    }

    @OnClick(R.id.upload_circle_img_add)
    public void onViewClicked() {
        new GalleryFragment()
                .setListener(new GalleryFragment.OnSelectedListener() {
                    @Override
                    public void onSelectedImage(String path) {
                        loadPortrait(path);
                        imgPath = path;
                    }
                })
                // show 的时候建议使用getChildFragmentManager，
                // tag GalleryFragment class 名
                .show(getSupportFragmentManager(), GalleryFragment.class.getName());
    }

    @OnClick(R.id.btn_submit)
    public void submit() {

        final String content = mEtContent.getText().toString();
        if (TextUtils.isEmpty(content)) {
            Toast.makeText(this, "内容不能为空！", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!TextUtils.isEmpty(imgPath)) {
            // 上传头像
            Factory.runOnAsync(new Runnable() {
                @Override
                public void run() {
                    String url = UploadHelper.uploadImage(imgPath);
                    if (TextUtils.isEmpty(url)) {
                        // 上传失败
                        Toast.makeText(UploadCircleActivity.this, getResources().getString(R.string.data_upload_error),
                                Toast.LENGTH_SHORT).show();
                    } else {

                        // 调用Retrofit对我们的网络请求接口做代理
                        RemoteService service = Network.remote();

                        CircleModel model = new CircleModel();
                        model.description = content;
                        model.imgs = url;
                        model.portrait = "";
                        model.personId = Account.getUserId();
                        // 得到一个Call
                        Call<RspModel<CircleModel>> call = service.uploadCircle(model);
                        // 异步的请求
                        call.enqueue(new Callback<RspModel<CircleModel>>() {
                            @Override
                            public void onResponse(Call<RspModel<CircleModel>> call, Response<RspModel<CircleModel>> response) {
                                if (response.isSuccessful()) {
                                    Toast.makeText(UploadCircleActivity.this, "发布成功", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            }

                            @Override
                            public void onFailure(Call<RspModel<CircleModel>> call, Throwable t) {
                                Log.e("PRETTY_LOGGER", "onFailure() returned: " + t.getMessage());
                                Toast.makeText(UploadCircleActivity.this, "发布失败：" + t.getMessage(), Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        });

                    }
                }
            });
        }

    }

    /**
     * 加载Uri到当前的头像中
     *
     * @param uri Uri
     */
    private void loadPortrait(final String uri) {
        if (TextUtils.isEmpty(uri))
            return;
        Glide.with(this)
                .load(uri)
                .asBitmap()
                .centerCrop()
                .into(mImgpreView);

    }
}
