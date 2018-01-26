package net.qiujuer.italker.push.frags.main;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import net.qiujuer.italker.common.app.Fragment;
import net.qiujuer.italker.common.widget.EmptyView;
import net.qiujuer.italker.push.R;

import butterknife.BindView;

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

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_find;
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);


    }
}
