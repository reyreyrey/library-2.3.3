package com.android.library.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.android.library.R;
import com.github.ybq.android.spinkit.SpinKitView;

import java.util.ArrayList;
import java.util.List;


public class LoadingProgressDialog extends ProgressDialog {
    protected List<SpinKitView> views;
    protected int random;
    private SpinKitView currentView;

    public LoadingProgressDialog(Context context) {
        super(context, R.style.progressDialogStyle);
        views = new ArrayList<>();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCanceledOnTouchOutside(false);
        setContentView(R.layout.dialog_progress);
        addViews();
    }

    private void addViews() {
        views.add((SpinKitView) findViewById(R.id.spin_kit1));
        views.add((SpinKitView) findViewById(R.id.spin_kit2));
        views.add((SpinKitView) findViewById(R.id.spin_kit3));
        views.add((SpinKitView) findViewById(R.id.spin_kit4));
        views.add((SpinKitView) findViewById(R.id.spin_kit5));
        views.add((SpinKitView) findViewById(R.id.spin_kit6));
        views.add((SpinKitView) findViewById(R.id.spin_kit7));
        views.add((SpinKitView) findViewById(R.id.spin_kit8));
        views.add((SpinKitView) findViewById(R.id.spin_kit9));
        views.add((SpinKitView) findViewById(R.id.spin_kit10));
        views.add((SpinKitView) findViewById(R.id.spin_kit11));
        views.add((SpinKitView) findViewById(R.id.spin_kit12));

        random = (int) (Math.random() * views.size());
        currentView = views.get(random);
        currentView.setVisibility(View.VISIBLE);
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }
}
