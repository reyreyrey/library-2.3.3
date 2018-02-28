package com.android.library.utils;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static java.lang.Thread.sleep;

/**
 * author: Rea.X
 * date: 2017/12/11.
 */

public class SplashTools {
    public static void checkTime(long time, final SplashCallback callback) {
        Observable
                .just(time)
                .subscribeOn(Schedulers.io())
                .map(new Function<Long, Long>() {
                    @Override
                    public Long apply(@NonNull Long aLong) throws Exception {
                        long now = System.currentTimeMillis();
                        long waitTime = now - aLong.longValue();
                        if (waitTime < 3000) {
                            sleep(3000 - waitTime);
                        }
                        return now;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        if (callback != null) {
                            callback.done();
                        }
                    }
                });
    }

    public static interface SplashCallback {
        void done();
    }
}
