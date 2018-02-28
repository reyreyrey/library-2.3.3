package com.android.library.ui;

import com.android.library.R;
import com.android.library.base.UIActivity;
import com.android.library.databinding.ActivitySystemNotifyDetailBinding;
import com.android.library.manager.UserManager;
import com.android.library.models.BaseModel;
import com.android.library.models.SystemNotify;
import com.android.library.models.SystemNotifyDetail;
import com.android.library.models.UserModel;
import com.android.library.utils.Cons;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.GetRequest;

/**
 * author: Rea.X
 * date: 2017/12/13.
 */

public class SystemNotifyDetailActivity extends UIActivity<ActivitySystemNotifyDetailBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_system_notify_detail;
    }

    private SystemNotify notify;

    @Override
    protected void init() {
        showProgress();
        tvTitle.setText("系统消息");
        notify = (SystemNotify) getIntent().getSerializableExtra("data");
        databinding.tvMessageTitle.setText(notify.getTitle());
        databinding.tvMessageTime.setText("发布时间：" + notify.getTime());
        query();
    }

    private void query() {
        GetRequest request = OkGo.<String>get(Cons.SYSTEM_NOTIFY_DETAIL_URL);
        UserModel model = UserManager.getCurrentUser();
        if (model != null) {
            request.params("user_id", model.getUserid());
        }
        request.params("notify_id", notify.getId());
        request.execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String result = response.body();
                BaseModel<SystemNotifyDetail> baseModel = new Gson().fromJson(result, new TypeToken<BaseModel<SystemNotifyDetail>>() {
                }.getType());
                if (baseModel.getSuccess() == 1) {
                    SystemNotifyDetail detail = baseModel.getData();
                    databinding.tvMessageContent.setText(detail.getContent());
                }
            }
        });
    }
}
