package ca.akshit.and.sergey.sergey_sharipov_akshit_upneja_mapd711_lab_onlinepurchase.util;

import android.content.SharedPreferences;

import java.util.UUID;

/**
 * Created by Sergey-PC on 24.10.2016.
 */

public class SharedPreferencesHelper {
    public static final String NAME = "ca.sergey.loyalty.SharedPreferences";
    private static final String CURRENT_USER_ID = "ca.sergey.loyalty.utill.CURRENT_USER_ID";
    private final String CURRENT_USER_LOGIN = "ca.sergey.loyalty.utill.CURRENT_USER_LOGIN";
    private final String CURRENT_USER_PASSWORD = "ca.sergey.loyalty.utill.CURRENT_USER_PASSWORD";
    private final String IS_CUSTOMER = "ca.sergey.loyalty.utill.IS_CUSTOMER";

    private SharedPreferences sPref;

    private static SharedPreferencesHelper sSharedPreferencesHelper;

    public static SharedPreferencesHelper get(SharedPreferences preferences) {
        if (sSharedPreferencesHelper == null) {
            sSharedPreferencesHelper = new SharedPreferencesHelper(preferences);
        }
        return sSharedPreferencesHelper;
    }

    private SharedPreferencesHelper(SharedPreferences preferences){
        sPref = preferences;
    }

    public void saveCurrentUser(UUID id, String login, String password, boolean isCustomer) {
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString(CURRENT_USER_ID, id.toString());
        ed.putString(CURRENT_USER_LOGIN, login);
        ed.putString(CURRENT_USER_PASSWORD, password);
        ed.putBoolean(IS_CUSTOMER, isCustomer);
        ed.apply();
    }

    public String getCurrentUserID() {
        return sPref.getString(CURRENT_USER_ID, "");
    }

    public String getCurrentUserLogin() {
        return sPref.getString(CURRENT_USER_LOGIN, "");
    }

    public String getCurrentUserPassword() {
        return sPref.getString(CURRENT_USER_PASSWORD, "");
    }

    public boolean isCurrentUserCustomer() {
        return sPref.getBoolean(IS_CUSTOMER, true);
    }

    public void saveToPref(String key, String value) {
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString(key, value);
        ed.apply();
    }

    public void saveToPref(String key, int value) {
        SharedPreferences.Editor ed = sPref.edit();
        ed.putInt(key, value);
        ed.apply();
    }

    public String loadString(String key) {
        return sPref.getString(key, "");
    }

    public int loadInt(String key) {
        return sPref.getInt(key, 0);
    }

}
