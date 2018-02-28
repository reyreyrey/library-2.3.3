package ticketsystem.manager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import library.app.AppContext;
import rx.Subscriber;
import rx.functions.Action1;
import ticketsystem.bean.TicketRegular;
import ticketsystem.utils.JsonUtil;
import rx.Observable;

/**
 * @author xiaolong
 * @version v1.0
 * @function <描述功能>
 * @date: 2017/9/19 16:18
 */

public class TicketRegularManager {
    public static TicketRegularManager sTicketRegularManager;
    public static List<TicketRegular> sTicketRegular;

    public static TicketRegularManager getTicketDataManager() {
        if (sTicketRegularManager == null) {
            synchronized (TicketRegularManager.class) {
                if (sTicketRegularManager == null) {
                    sTicketRegularManager = new TicketRegularManager();
                }
            }
        }
        return sTicketRegularManager;
    }

    public Observable<List<TicketRegular>> getTicketRegularList() {
        return sTicketRegular == null ? getDataFromAsset().doOnNext(new Action1<List<TicketRegular>>() {
            @Override
            public void call(List<TicketRegular> ticketRegulars) {
                sTicketRegular = ticketRegulars;
            }
        }): Observable.just(sTicketRegular);
    }


    private Observable<List<TicketRegular>> getDataFromAsset() {
        return Observable.create(new Observable.OnSubscribe<List<TicketRegular>>() {
            @Override
            public void call(Subscriber<? super List<TicketRegular>> subscriber) {
                List<TicketRegular> ticketRegulars = new Gson().fromJson(JsonUtil.getJson(AppContext.getContext(), "regular.json"), new TypeToken<List<TicketRegular>>() {
                }.getType());
                subscriber.onNext(ticketRegulars);
                subscriber.onCompleted();
            }
        });
    }
}
