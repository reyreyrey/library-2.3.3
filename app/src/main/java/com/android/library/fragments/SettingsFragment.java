package com.android.library.fragments;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.android.library.R;

/**
 * Created by wiki on 2018/1/23.
 */

public class SettingsFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.addPreferencesFromResource(R.xml.setting_preferences);
    }
}
