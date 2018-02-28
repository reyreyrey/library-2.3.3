package com.android.library.utils;

import android.app.ProgressDialog;
import android.content.Context;


public class ProgressDialogUtils {

    private static ProgressDialog progressDialog;

    private static void initProgressDialog(Context context) {
        progressDialog = new LoadingProgressDialog(context);
        progressDialog.setCanceledOnTouchOutside(false);
    }


    public static void showProgress(Context context) {
        try {
            initProgressDialog(context);
            progressDialog.show();
        } catch (Throwable e) {
        }
    }

    public static void dismissProgress() {
        try {
            progressDialog.dismiss();
        } catch (Throwable e) {
        }
    }


}
