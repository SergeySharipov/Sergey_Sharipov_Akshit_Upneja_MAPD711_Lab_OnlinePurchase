package ca.akshit.and.sergey.sergey_sharipov_akshit_upneja_mapd711_lab_onlinepurchase.controller;

import android.support.v4.app.Fragment;

import java.util.UUID;

import ca.akshit.and.sergey.sergey_sharipov_akshit_upneja_mapd711_lab_onlinepurchase.util.SharedPreferencesHelper;


public class OrdersActivity extends SingleFragmentActivity {

    public static final String CUSTOMER_ID = "ca.sergey.loyalty.controller.CUSTOMER_ID";
    private SharedPreferencesHelper mSharedPrefHelper;

    @Override
    protected Fragment createFragment() {
        mSharedPrefHelper = SharedPreferencesHelper.get(getSharedPreferences(
                SharedPreferencesHelper.NAME, MODE_PRIVATE
        ));
        if (mSharedPrefHelper.isCurrentUserCustomer())
            return new OrdersFragment();
        else {
            String customerId = getIntent().getStringExtra(CUSTOMER_ID);
            return OrdersFragment.newInstance(UUID.fromString(customerId));
        }
    }

}
