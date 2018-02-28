package ticketsystem.ui;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;


import com.android.library.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import library.listview.ListGroupPresenter;
import library.listview.listview.RecycleListViewImpl;
import library.listview.manager.BaseGroupListManager;
import library.model.Event;
import rx.functions.Action1;
import ticketsystem.adapter.TicketRecentOpenAdapter;
import ticketsystem.base.BaseFuncFragment;
import ticketsystem.bean.TicketType;
import ticketsystem.bean.type.TicketTypeEnum;
import ticketsystem.group.LoadingPage;
import ticketsystem.group.Scene;
import ticketsystem.manager.TicketTypeManager;
import ticketsystem.ui.follow.FollowAddActivity;
import ticketsystem.ui.widget.RecycleViewDivider;
import ticketsystem.utils.LaunchUtil;


/**
 * @author xiaolong
 * @version v1.0
 * @function <我的关注>
 * @date 2017年9月12日 14:55:21
 */
public class MyFollowFragment extends BaseFuncFragment {
    private TicketRecentOpenAdapter ticketRecentOpenAdapter;
    private ListGroupPresenter presenter;
    private BaseGroupListManager manager;
    private RecycleListViewImpl recycleListView;


    private TicketTypeEnum mTicketTypeEnum;

    public static MyFollowFragment getNewInstance(TicketTypeEnum ticketTypeEnum) {
        MyFollowFragment fragment = new MyFollowFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("ticketListType", ticketTypeEnum);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void getExtra() {
        if (getArguments() != null) {
            mTicketTypeEnum = (TicketTypeEnum) getArguments().getSerializable("ticketListType");
        }
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_my_follow;
    }

    @Override
    public void init() {
        EventBus.getDefault().register(this);
        recycleListView = new RecycleListViewImpl(true, false, false);
        RelativeLayout rlContent = findView(R.id.rlContent);
        LoadingPage loadingPage = new LoadingPage(getActivity(), Scene.TICKET_FAVORITE);
        ticketRecentOpenAdapter = new TicketRecentOpenAdapter(getActivity());
        manager = new TicketTypeManager(mTicketTypeEnum);
        presenter = ListGroupPresenter.create(getActivity(), recycleListView, manager, ticketRecentOpenAdapter, loadingPage);
        recycleListView.getRecyclerView().addItemDecoration(new RecycleViewDivider(getActivity(),
                LinearLayoutManager.HORIZONTAL, 2, getResources().getColor(R.color.main_divider_color)));
        rlContent.addView(presenter.getRootView(), new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    @Override
    public void setListener() {
        ClickView(findView(R.id.tvAdd)).subscribe(new Action1() {
            @Override
            public void call(Object o) {
                LaunchUtil.launchActivity(getActivity(), FollowAddActivity.class);
            }
        });
        ticketRecentOpenAdapter.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LaunchUtil.launchActivity(getActivity(), OpenResultActivity.class,
                        OpenResultActivity.buildBundle((TicketType) view.getTag()));
            }
        });


    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Subscribe
    public void refreshData(Event event) {
        ticketRecentOpenAdapter.setItems((List<TicketType>) event.data);
        presenter.onRefresh();
    }
}
