package com.android.library.ui;

import com.android.library.adapter.UserAdapter;
import com.android.library.base.RefreshActivity;
import com.android.library.manager.UserManager;
import com.android.library.models.BaseModel;
import com.android.library.models.UserModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.joanzapata.android.QuickAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.List;

import static com.android.library.utils.Cons.MY_ATTENTION_URL;
import static com.android.library.utils.Cons.MY_FANS_URL;


public class FanActivity extends RefreshActivity<UserModel> {
    @Override
    protected void init() {
        super.init();
        tvTitle.setText("我的粉丝");
    }


    @Override
    protected QuickAdapter<UserModel> getAdapter() {
        return new UserAdapter(context, false);
    }

    @Override
    protected void query() {
        OkGo.<String>get(MY_FANS_URL + UserManager.getCurrentUser().getUserid())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String result = response.body();
                        BaseModel<List<UserModel>> baseModel = new Gson().fromJson(result, new TypeToken<BaseModel<List<UserModel>>>(){}.getType());
                        if(baseModel.getSuccess() == 1){
                            if(isRefresh){
                                adapter.clear();
                            }
                            adapter.addAll(baseModel.getData());
                        }
                        smartRefreshLayout.finishRefresh();
                        smartRefreshLayout.finishLoadmore();
                        refreshView();
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
}
