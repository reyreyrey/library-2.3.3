package com.android.library.adapter;

import android.content.Context;

import com.android.library.R;
import com.android.library.models.SystemNotify;
import com.joanzapata.android.BaseAdapterHelper;
import com.joanzapata.android.QuickAdapter;


public class SystemNotifyAdapter extends QuickAdapter<SystemNotify> {
    public SystemNotifyAdapter(Context context) {
        super(context, R.layout.item_system_notify);
    }

    @Override
    protected void convert(final BaseAdapterHelper helper, SystemNotify item) {
        helper.setText(R.id.tv_notify_title, item.getTitle())
                .setVisible(R.id.tv_notify_red, !item.isIsRead());
    }
}
