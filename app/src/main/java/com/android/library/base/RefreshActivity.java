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

public abstract class RefreshActivity<D> extends UIActivity implements OnRefreshLoadmoreListener, AdapterView.OnItemClickListener {
    protected int currentPage = 1;
    protected QuickAdapter<D> adapter;
    protected boolean isRefresh;
    protected SmartRefreshLayout smartRefreshLayout;
    protected ListView listView;
    protected TextView emptyView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_refresh_listview;
    }

    @Override
    protected void init() {
        listView = (ListView) findViewById(R.id.listview);
        listView.setOnItemClickListener(this);
        emptyView = (TextView) findViewById(R.id.emptyView);
        smartRefreshLayout = (SmartRefreshLayout) findViewById(R.id.refreshLayout);
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
        if (!enableLoadmore()) {
            smartRefreshLayout.finishRefresh();
            smartRefreshLayout.finishLoadmore();
            return;
        }
        isRefresh = false;
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

    protected boolean enableLoadmore() {
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
