package ca.akshit.and.sergey.sergey_sharipov_akshit_upneja_mapd711_lab_onlinepurchase.controller;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import ca.akshit.and.sergey.sergey_sharipov_akshit_upneja_mapd711_lab_onlinepurchase.R;


public class ProductsActivity extends SingleFragmentActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.product_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.show_orders:
                Intent intent = new Intent(this, OrdersActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected Fragment createFragment() {
        return new ProductsFragment();
    }

}
