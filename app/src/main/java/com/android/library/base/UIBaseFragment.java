package com.android.library.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.library.utils.SoftKeyboardUtils;


public abstract class UIBaseFragment<T extends ViewDataBinding> extends Fragment {
    protected boolean isVisible;
    protected boolean isPrepared;
    protected boolean isLoaded;
    protected T databinding;

    public UIBaseFragment() {
    }

    @Nullable
    public final View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        databinding = DataBindingUtil.inflate(inflater, this.getContentView(), container, false);
        this.isPrepared = true;
        this.init(databinding.getRoot());
        this.onVisible();
        return databinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (this.getUserVisibleHint()) {
            this.isVisible = true;
            this.onVisible();
        } else {
            this.isVisible = false;
            this.onInvisible();
        }

    }

    private void onVisible() {
        if (this.isPrepared && this.isVisible) {
            this.lzayLoadEveryVisible();
            if (!this.isLoaded) {
                this.isLoaded = true;
                this.lazyLoad();
            }
        }
    }

    protected abstract void lazyLoad();

    protected void lzayLoadEveryVisible() {
    }

    protected abstract int getContentView();

    protected void onInvisible() {
        SoftKeyboardUtils.hideSoftKeyboard(getContext());
    }

    protected abstract void init(View view);

}
