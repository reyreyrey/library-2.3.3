package com.android.webview.x5.download;

import java.io.File;

/**
 * author: Rea.X
 * date: 2017/11/13.
 */

public interface DownloadCallback {
    void requestSuccess(File file);

    void requestFial();

    void downProgress(long readLength, long contentLength, int progress);
}
