package com.android.library.utils;

import android.content.SharedPreferences;

import com.android.library.Library;


public class PreferencesHelper {
    private static SharedPreferences preferences;


    private static SharedPreferences getPreferences() {
        if (preferences == null)
            preferences = Library.get().getSharedPreferences("library", 0);
        return preferences;
    }

    public static String getString(String key) {
        return getPreferences().getString(key, null);
    }

    public static void saveString(String key, String value) {
        getPreferences().edit().putString(key, value).apply();
    }

    public static int getInt(String key) {
        return getPreferences().getInt(key, 0);
    }

    public static void saveInt(String key, int value) {
        getPreferences().edit().putInt(key, value).apply();
    }

    public static boolean getBoolean(String key) {
        return getPreferences().getBoolean(key, false);
    }

    public static void saveBoolean(String key, boolean value) {
        getPreferences().edit().putBoolean(key, value).apply();
    }

    public static void delete(String key) {
        getPreferences().edit().remove(key).apply();
    }
}
