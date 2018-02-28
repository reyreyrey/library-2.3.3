package ticketsystem.api;

import com.google.gson.JsonParseException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import com.android.library.BuildConfig;
import com.android.library.R;

import library.app.AppContext;
import library.app.ReturnCode;
import library.model.BaseInfo;
import library.model.Response;
import library.rx.ErrorThrowable;
import ticketsystem.api.Exception.RetryWhenNetworkException;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * <请描述这个类是干什么的>
 *
 * @data: 2016/7/7 13:39
 * @version: V1.0
 */
public class ResponseHandle {

    public static <T> Func1<Throwable, Observable<? extends T>> errorResumeFunc() {
        return new Func1<Throwable, Observable<? extends T>>() {
            @Override
            public Observable<? extends T> call(Throwable throwable) {

                if (throwable instanceof UnknownHostException || throwable instanceof JsonParseException) {
                    if (!AppContext.isNetworkAvailable()) {
                        return Observable.error(new ErrorThrowable(ReturnCode.LOCAL_NO_NETWORK, AppContext.getContext().getString(R.string.load_no_network)));
                    }
                    return Observable.error(new ErrorThrowable(ReturnCode.LOCAL_ERROR_TYPE_ERROR, BuildConfig.DEBUG_LOG ? throwable.toString() : AppContext.getString(R.string.load_system_busy)));
                } else if (throwable instanceof SocketTimeoutException || throwable instanceof ConnectException) {
                    return Observable.error(new ErrorThrowable(ReturnCode.LOCAL_TIMEOUT_ERROR, BuildConfig.DEBUG_LOG ? throwable.toString() : AppContext.getString(R.string.load_time_out)));
                } else if (throwable instanceof ErrorThrowable) {
                    Observable.error(throwable);
                }
                return Observable.error(new ErrorThrowable(ReturnCode.LOCAL_UNKNOWN_ERROR, BuildConfig.DEBUG_LOG ? throwable.toString() : AppContext.getString(R.string.load_system_busy)));
            }
        };
    }

    // 读取实体数据
    private static class ReadDataFunc<E> implements Func1<Response<E>, Observable<E>> {
        @Override
        public Observable<E> call(Response<E> x) {
            if (x.rsCode == ReturnCode.CODE_SUCCESS) {
                if (x.data != null && x.data instanceof BaseInfo) {
                    ((BaseInfo) x.data).setSussceHintMsg(x.rsMsg);
                }
                return Observable.just(x.data);
            } else {
                return Observable.error(new ErrorThrowable(x.rsCode, (x.rsCode == ReturnCode.CODE_TOKEN_INVALID) ? "" : x.rsMsg));//用户失效不显示提示语
            }
        }
    }

    //获取response数据
    public static class ReadResponse implements Func1<Response, Observable<Response>> {

        @Override
        public Observable<Response> call(Response x) {
            if (x.rsCode == ReturnCode.CODE_SUCCESS) {
                return Observable.just(x);
            } else {
                return Observable.error(new ErrorThrowable(x.rsCode, x.rsCode == ReturnCode.CODE_TOKEN_INVALID ? "" : x.rsMsg));
            }
        }
    }

    //新建处理实体类型数据
    public static ReadDataFunc newEntityData() {
        return new ReadDataFunc();
    }

    public static ReadResponse newResponseData() {
        return new ReadResponse();
    }


    public static <T> Observable.Transformer<T, T> applySchedulersWithToken() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> tObservable) {
                return tObservable
                        .subscribeOn(Schedulers.io())
                        .observeOn(Schedulers.io())
                        .onErrorResumeNext(errorResumeFunc())
                        .retryWhen(new RetryWhenNetworkException())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }


}
