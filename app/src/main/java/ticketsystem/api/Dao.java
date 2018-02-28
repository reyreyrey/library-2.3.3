package ticketsystem.api;


import library.network.retrofit.RetrofitDao;
import okhttp3.HttpUrl;

import com.android.library.BuildConfig;
import com.android.library.R;
/**
 * <请描述这个类是干什么的>
 *
 * @data: 2016/7/7 11:28
 * @version: V1.0
 */
public class Dao {
    private static ApiService mApiService;

    public static ApiService getApiService() {
        if (mApiService == null) {
            synchronized (Dao.class) {
                if (mApiService == null) {
                    mApiService = RetrofitDao.buildRetrofit(new RetrofitDao.IBuildPublicParams() {
                        @Override
                        public HttpUrl.Builder buildPublicParams(HttpUrl.Builder builder) {
                            return buildPublicParams(builder);
                        }
                    }).create(ApiService.class);
                }
            }
        }
        return mApiService;
    }

    private static HttpUrl.Builder buildPublicParams(HttpUrl.Builder builder) {
        builder.addQueryParameter("showapi_sign", BuildConfig.TICKET_SECRET);
        builder.addQueryParameter("showapi_appid", BuildConfig.TICKET_APP_ID);
        return builder;
    }


}
