package com.android.library.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.android.library.fragments.UserFavPostListFragment;
import com.android.library.fragments.UserPostListFragment;

/**
 * Created by wiki on 2018/1/22.
 */

public class OtherUserPagerAdapter extends FragmentPagerAdapter {
    private String userid;

    public OtherUserPagerAdapter(FragmentManager fm, String userid) {
        super(fm);
        this.userid = userid;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return UserPostListFragment.instants(userid);
            case 1:
                return UserFavPostListFragment.instants(userid);
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "主页";
            case 1:
                return "喜欢";
        }
        return null;
    }
}
