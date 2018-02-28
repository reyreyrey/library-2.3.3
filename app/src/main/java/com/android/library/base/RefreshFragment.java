package com.android.library.base;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.library.R;
import com.joanzapata.android.QuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

/**
 * author: Rea.X
 * date: 2017/12/14.
 */

public abstract class RefreshFragment<D> extends UIBaseFragment implements OnRefreshLoadmoreListener, AdapterView.OnItemClickListener {
    protected int currentPage = 1;
    protected QuickAdapter<D> adapter;
    protected boolean isRefresh;
    protected SmartRefreshLayout smartRefreshLayout;
    protected ListView listView;
    protected TextView emptyView;


    @Override
    protected void init(View view) {
        listView = (ListView) view.findViewById(R.id.listview);
        listView.setOnItemClickListener(this);
        emptyView = (TextView) view.findViewById(R.id.emptyView);
        smartRefreshLayout = (SmartRefreshLayout) view.findViewById(R.id.refreshLayout);
        smartRefreshLayout.setOnRefreshLoadmoreListener(this);
        adapter = getAdapter();
        listView.setAdapter(adapter);
        if (needQueryInInit())
            smartRefreshLayout.autoRefresh();
    }

    protected void showEmptyView() {
        listView.setVisibility(View.GONE);
        emptyView.setVisibility(View.VISIBLE);
    }

    protected void showContent() {
        listView.setVisibility(View.VISIBLE);
        emptyView.setVisibility(View.GONE);
    }

    protected abstract QuickAdapter<D> getAdapter();

    protected void refreshView() {
        if (adapter.getCount() <= 0) {
            showEmptyView();
        } else {
            showContent();
        }
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        isRefresh = false;
        if (!enableLoadmore()) {
            smartRefreshLayout.finishRefresh();
            smartRefreshLayout.finishLoadmore();
            return;
        }
        currentPage++;
        query();
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        showContent();
        isRefresh = true;
        currentPage = 1;
        query();
    }

    protected boolean needQueryInInit() {
        return true;
    }

    protected abstract void query();

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_refresh_listview;
    }

    protected boolean enableLoadmore() {
        return true;
    }

}
