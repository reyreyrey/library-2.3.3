package ticketsystem.presenter;

import android.app.Activity;

import com.android.library.BuildConfig;
import com.google.gson.Gson;

import java.util.List;

import library.cache.SPHelp;
import ticketsystem.base.BasePresenter;
import ticketsystem.bean.TicketType;
import ticketsystem.manager.TicketTypeDataManager;
import ticketsystem.presenter.view.IFollowAddView;

/**
 * @author xiaolong
 * @version v1.0
 * @function <描述功能>
 * @date: 2017/09/11 11:10:50
 */

public class FollowAddPresenter extends BasePresenter<IFollowAddView> {

    public FollowAddPresenter(Activity activity) {
        super(activity);
    }

    public void getMyFollowList() {
        addSubscribe(TicketTypeDataManager.getTicketDataManager().getMyFollowData().subscribe(getSubscriber(ticketTypeList ->
                mView.onGetTicketListSuccess(ticketTypeList)
        )));
    }

    public void cacheList(List<TicketType> ticketTypes) {
        SPHelp.setUserParam(BuildConfig.KEY_MY_FOLLOW, new Gson().toJson(ticketTypes));
    }
}
