package ticketsystem.presenter;

import android.app.Activity;


import library.model.ListData;
import library.util.TimeUtils;
import ticketsystem.api.DataManager;
import ticketsystem.base.BasePresenter;
import ticketsystem.base.OnSubscribeSuccess;
import ticketsystem.bean.TicketOpenData;
import ticketsystem.presenter.view.IParityTrendView;

/**
 * @author xiaolong
 * @version v1.0
 * @function <描述功能>
 * @date: 2017/9/14 14:58
 */

public class ParityTrendPresenter extends BasePresenter<IParityTrendView> {
    public ParityTrendPresenter(Activity activity) {
        super(activity);
    }

    public void getRecentOpenDatas(String ticketCode, String count) {
        addSubscribe(DataManager.getMutiPeriodCheck(ticketCode, count,
                TimeUtils.milliseconds2String(System.currentTimeMillis()))
                .subscribe(getSubscriber(new OnSubscribeSuccess<ListData<TicketOpenData>>() {
                    @Override
                    public void onSuccess(ListData<TicketOpenData> ticketOpenDataListData) {
                        mView.onGetHistoryRecentTicketListSuccess(ticketOpenDataListData.list);
                    }
                })));
    }
}
