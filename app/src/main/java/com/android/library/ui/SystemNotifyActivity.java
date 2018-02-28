package com.android.library.ui;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;

import com.android.library.adapter.SystemNotifyAdapter;
import com.android.library.base.RefreshActivity;
import com.android.library.manager.UserManager;
import com.android.library.models.BaseModel;
import com.android.library.models.SystemNotify;
import com.android.library.models.UserModel;
import com.android.library.utils.Cons;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.joanzapata.android.QuickAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.GetRequest;

import java.util.List;


public class SystemNotifyActivity extends RefreshActivity<SystemNotify> {
    @Override
    protected void init() {
        super.init();
        String title = getIntent().getStringExtra("title");
        if (!TextUtils.isEmpty(title))
            tvTitle.setText(title);
        else
            tvTitle.setText("系统消息");
    }


    @Override
    protected QuickAdapter<SystemNotify> getAdapter() {
        return new SystemNotifyAdapter(this);
    }

    @Override
    protected void query() {
        GetRequest request = OkGo.<String>get(Cons.SYSTEM_NOTIFY_URL);
        UserModel model = UserManager.getCurrentUser();
        if (model != null) {
            request.params("user_id", model.getUserid());
        }
        request.execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String result = response.body();
                BaseModel<List<SystemNotify>> baseModel = new Gson().fromJson(result, new TypeToken<BaseModel<List<SystemNotify>>>() {
                }.getType());
                if (baseModel.getSuccess() == 1) {
                    List<SystemNotify> notifies = baseModel.getData();
                    if (isRefresh) {
                        adapter.clear();
                    }
                    adapter.addAll(notifies);
                    smartRefreshLayout.finishRefresh();
                    smartRefreshLayout.finishLoadmore();
                    refreshView();
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                smartRefreshLayout.finishRefresh();
                smartRefreshLayout.finishLoadmore();
                refreshView();
            }
        });
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        super.onItemClick(adapterView, view, i, l);
        SystemNotify notify = adapter.getItem(i);
        Intent intent = new Intent(context, SystemNotifyDetailActivity.class);
        intent.putExtra("data", notify);
        startActivity(intent);
    }
}
