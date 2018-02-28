package ticketsystem.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.android.library.R;

import java.util.ArrayList;
import java.util.List;

import ticketsystem.adapter.TabFragmentAdapter;
import ticketsystem.base.BaseTitleBar;
import ticketsystem.base.BaseTitleBarActivity;
import ticketsystem.bean.type.TicketTypeEnum;

/**
 * @author xiaolong
 * @version v1.0
 * @function <描述功能>
 * @date: 2017/9/7 14:45
 */

public class LibraryMainActivity extends BaseTitleBarActivity {

    private TextView tvTitle;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_library_main;
    }

    @Override
    protected void init() {
        initTabTop(getFragmentTitle(), getFragmentList());
        tvTitle.setText("开奖查询");
    }

    private List<Fragment> getFragmentList() {
        List<Fragment> fragmentList = new ArrayList<>();
        for (int i = 0; i < TicketTypeEnum.values().length-1; i++) {
            fragmentList.add(TicketTypeFragment.getNewInstance(TicketTypeEnum.values()[i]));
        }
        fragmentList.add(MyFollowFragment.getNewInstance(TicketTypeEnum.Follow));
        return fragmentList;
    }

    private List<String> getFragmentTitle() {
        List<String> titles = new ArrayList<>();
        for (int i = 0; i < TicketTypeEnum.values().length; i++) {
            titles.add(TicketTypeEnum.values()[i].getValue());
        }
        return titles;
    }

    private void initTabTop(List<String> fragmentTitles, List<Fragment> fragmentList) {
        TabFragmentAdapter tabFragmentAdapter = new TabFragmentAdapter(fragmentList, fragmentTitles, getSupportFragmentManager(), this);
        ViewPager pagerContent = findView(R.id.pageContent);
        pagerContent.setAdapter(tabFragmentAdapter);
        TabLayout tlMainTop = findView(R.id.tlMainTop);
        tlMainTop.setupWithViewPager(pagerContent);
    }

    @Override
    protected void setListener() {

    }

    @Override
    public void initTitleBar(BaseTitleBar titleBar) {
        tvTitle = (TextView) titleBar.center;
    }
}
