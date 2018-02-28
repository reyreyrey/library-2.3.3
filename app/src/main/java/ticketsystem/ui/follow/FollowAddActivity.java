package ticketsystem.ui.follow;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;


import com.android.library.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import library.model.Event;
import library.rx.ErrorThrowable;
import ticketsystem.adapter.TabFragmentAdapter;
import ticketsystem.base.BaseTitleBar;
import ticketsystem.base.BaseTitleBarActivity;
import ticketsystem.bean.event.FollowDataChangeEvent;
import ticketsystem.bean.TicketType;
import ticketsystem.bean.type.TicketTypeEnum;
import ticketsystem.presenter.FollowAddPresenter;
import ticketsystem.presenter.view.IFollowAddView;
import ticketsystem.utils.LaunchUtil;

/**
 * @author xiaolong
 * @version v1.0
 * @function <添加更多彩种主Activity>
 * @date: 2017/9/11 15:05
 */

public class FollowAddActivity extends BaseTitleBarActivity<FollowAddPresenter> implements IFollowAddView {
    private List<TicketType> mTicketTypes;
    private ImageView titleBarRight;

    @Override
    public void initTitleBar(BaseTitleBar titleBar) {
        titleBar.setTitleText("添加关注的彩种");
        titleBarRight = (ImageView) titleBar.right;
        titleBarRight.setImageResource(R.drawable.ic_setting);
        titleBarRight.setVisibility(View.VISIBLE);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_follow_add;
    }


    @Override
    public FollowAddPresenter getPresenter() {
        return new FollowAddPresenter(this);
    }

    @Override
    protected void init() {
        mPresenter.getMyFollowList();
    }

    @Override
    protected void setListener() {

    }

    private List<Fragment> getFragmentList(ArrayList<TicketType> ticketTypes) {
        List<Fragment> fragmentList = new ArrayList<>();
        for (int i = 1; i < TicketTypeEnum.values().length; i++) {
            fragmentList.add(TicketTypeFollowAddFragment.getNewInstance(TicketTypeEnum.values()[i], ticketTypes));
        }
        return fragmentList;
    }

    private List<String> getFragmentTitle() {
        List<String> titles = new ArrayList<>();
        for (int i = 1; i < TicketTypeEnum.values().length; i++) {
            titles.add(TicketTypeEnum.values()[i].getValue());
        }
        return titles;
    }

    private void initTabTop(List<String> fragmentTitles, List<Fragment> fragmentList) {
        TabFragmentAdapter tabFragmentAdapter = new TabFragmentAdapter(fragmentList, fragmentTitles, getSupportFragmentManager(), this);
        ViewPager pagerContent = findView(R.id.pageContent);
        pagerContent.setAdapter(tabFragmentAdapter);
        TabLayout tlMainTop = findView(R.id.tlFollowTop);
        tlMainTop.setupWithViewPager(pagerContent);
    }

    /**
     * 为什么要做这个呢，因为Activity传过去的对象跟这边的对象不是同一个，所以需要将数据post回来。
     * @param followDataChangeEvent
     */
    @Subscribe
    public void onRefreshData(FollowDataChangeEvent<List<TicketType>> followDataChangeEvent) {
        mTicketTypes.clear();
        mTicketTypes.addAll(followDataChangeEvent.data);
    }

    @Override
    protected void onDestroy() {
        mPresenter.cacheList(mTicketTypes);
        EventBus.getDefault().post(new Event(mTicketTypes));
        super.onDestroy();
    }

    @Override
    public void onGetTicketListSuccess(List<TicketType> ticketTypeList) {
        mTicketTypes = ticketTypeList;
        ClickView(titleBarRight).subscribe(o -> {
            if (mTicketTypes != null) {
                LaunchUtil.launchActivity(this, FollowSortActivity.class, FollowSortActivity.buildBundle((ArrayList<TicketType>) mTicketTypes));
            }
        });
        initTabTop(getFragmentTitle(), getFragmentList((ArrayList<TicketType>) ticketTypeList));
    }

    @Override
    public void showError(ErrorThrowable errorThrowable) {

    }
}
