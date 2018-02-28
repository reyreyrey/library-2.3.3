package com.android.webview.x5.download.download;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.library.R;
import com.daimajia.numberprogressbar.NumberProgressBar;


public class UpdateProgressDialog extends DialogFragment {

    private ProgressReceiver progressReceiver;


    private NumberProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_progressbar, null);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        progressBar = (NumberProgressBar) view.findViewById(R.id.number_progress_bar);
        return view;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Black_NoTitleBar);
        progressReceiver = new ProgressReceiver();
        IntentFilter intentFilter = new IntentFilter("downloadProgress");
        intentFilter.addAction("downloadError");
        intentFilter.addAction("dismiss");
        getContext().registerReceiver(progressReceiver, intentFilter);
        setCancelable(false);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        getContext().unregisterReceiver(progressReceiver);
    }


    private void refresh(long currentSize, long totalSize, int progress) {
        progressBar.setMax(100);
        progressBar.setProgress(progress);

    }


    private class ProgressReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                String action = intent.getAction();
                if (action.equals("downloadError")) {
                    dismissAllowingStateLoss();
                    return;
                }
                if (action.equals("dismiss")) {
                    dismissAllowingStateLoss();
                    return;
                }
                long currentSize = intent.getLongExtra("currentSize", 0);
                long totalSize = intent.getLongExtra("totalSize", 0);
                int progress = intent.getIntExtra("progress", 0);
                if (progress != 100)
                    refresh(currentSize, totalSize, progress);
                else {
                    dismissAllowingStateLoss();
                }
            } catch (Throwable e) {
            }
        }
    }
}
