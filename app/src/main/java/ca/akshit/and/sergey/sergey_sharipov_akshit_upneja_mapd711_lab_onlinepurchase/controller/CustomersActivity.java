package ca.akshit.and.sergey.sergey_sharipov_akshit_upneja_mapd711_lab_onlinepurchase.controller;

import android.support.v4.app.Fragment;

public class CustomersActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new CustomersFragment();
    }

}
