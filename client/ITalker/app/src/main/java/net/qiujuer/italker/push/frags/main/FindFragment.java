package net.qiujuer.italker.push.frags.main;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import net.qiujuer.italker.common.app.Fragment;
import net.qiujuer.italker.common.widget.EmptyView;
import net.qiujuer.italker.common.widget.recycler.RecyclerAdapter;
import net.qiujuer.italker.factory.model.api.RspModel;
import net.qiujuer.italker.factory.model.api.circle.CircleCard;
import net.qiujuer.italker.factory.net.Network;
import net.qiujuer.italker.factory.net.RemoteService;
import net.qiujuer.italker.push.R;

import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * <pre>
 *     author : 戈传光
 *     e-mail : 1944633835@qq.com
 *     time   : 2018/01/26
 *     desc   :
 *     version:
 * </pre>
 */
public class FindFragment extends Fragment {
    @BindView(R.id.find_recycler)
    RecyclerView mRecycler;
    @BindView(R.id.find_empty)
    EmptyView mEmpty;

    private RecyclerAdapter<CircleCard> mAdapter;

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_find;
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);

        mEmpty.bind(mRecycler);
        setPlaceHolderView(mEmpty);

        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecycler.setAdapter(mAdapter = new RecyclerAdapter<CircleCard>() {
            @Override
            protected int getItemViewType(int position, CircleCard circleCard) {
                return R.layout.item_find_circle_rllist;
            }

            @Override
            protected ViewHolder<CircleCard> onCreateViewHolder(View root, int viewType) {
                return new ItemHolder(root);
            }
        });

    }

    /**
     * 朋友圈动态列表
     */
    class ItemHolder extends RecyclerAdapter.ViewHolder<CircleCard> {

        @BindView(R.id.item_find_img_portview)
        ImageView ivPortView;
        @BindView(R.id.item_find_tv_detail)
        TextView tvDetail;

        @BindView(R.id.item_find_img_detail)
        ImageView ivDetail;


        public ItemHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void onBind(CircleCard circleCard) {
            Glide.with(getActivity())
                    .load(circleCard.portrait)
                    .into(ivPortView);

            tvDetail.setText(circleCard.description);

            Glide.with(getActivity())
                    .load(circleCard.imgs)
                    .into(ivDetail);
        }
    }

    @Override
    protected void initData() {
        super.initData();

        // 调用Retrofit对我们的网络请求接口做代理
        RemoteService service = Network.remote();

        // 得到一个Call
        Call<RspModel<List<CircleCard>>> call = service.circleList();
        // 异步的请求
        call.enqueue(new Callback<RspModel<List<CircleCard>>>() {
            @Override
            public void onResponse(Call<RspModel<List<CircleCard>>> call, Response<RspModel<List<CircleCard>>> response) {
                if (response.isSuccessful()) {
                    List<CircleCard> result = response.body().getResult();

                    Log.e("PRETTY_LOGGER", "onResponse() returned: " + result.toString());
                    mPlaceHolderView.triggerOkOrEmpty(result.size()>0);
                    mAdapter.add(result);
                }
            }

            @Override
            public void onFailure(Call<RspModel<List<CircleCard>>> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage() + "", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
