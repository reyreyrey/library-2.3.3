package android.support.v4.app;

import android.support.annotation.CallSuper;


public class DialogFragment2 extends DialogFragment {

    @CallSuper
    @Override
    public int show(FragmentTransaction transaction, String tag) {
        mDismissed = false;
        mShownByMe = true;
        transaction.add(this, tag);
        mViewDestroyed = false;
        //mBackStackId = transaction.commit();
        mBackStackId = transaction.commitAllowingStateLoss();
        return mBackStackId;
    }

    @CallSuper
    @Override
    public void show(FragmentManager manager, String tag) {
        mDismissed = false;
        mShownByMe = true;
        FragmentTransaction ft = manager.beginTransaction();
        ft.add(this, tag);
        //ft.commit();
        ft.commitAllowingStateLoss();
    }
}
