package com.zhengsr.wanandroid.moudle.fragment.project;

import android.os.Bundle;

import com.zhengsr.wanandroid.R;
import com.zhengsr.wanandroid.bean.ProjectBean;
import com.zhengsr.wanandroid.bean.ProjectListBean;
import com.zhengsr.wanandroid.moudle.fragment.base.BaseMvpFragment;
import com.zhengsr.wanandroid.moudle.fragment.base.BaseNetFragment;
import com.zhengsr.wanandroid.mvp.contract.IContractView;
import com.zhengsr.wanandroid.mvp.present.ProjectPresent;

import java.util.List;

/**
 * @author by  zhengshaorui on 2019/10/8
 * Describe:
 */
public class ProjectDetailFragment extends BaseNetFragment<ProjectPresent> implements IContractView.IProjectDetailView {

    public static ProjectDetailFragment newInstance(ProjectListBean bean) {
        
        Bundle args = new Bundle();
        args.putSerializable("bean",bean);
        ProjectDetailFragment fragment = new ProjectDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public ProjectPresent getPresent() {
        mPresent = ProjectPresent.create(this);
        return mPresent;
    }

    @Override
    public int getLayoutId() {
        return R.layout.recycler_layout;
    }

    @Override
    public void initDataAndEvent() {
        super.initDataAndEvent();
        Bundle arguments = getArguments();
        if (arguments != null) {
           ProjectListBean bean = (ProjectListBean) arguments.getSerializable("bean");
        }
    }

    @Override
    public void getProjectDetail(List<ProjectBean> beans, int maxPage) {

    }
}
