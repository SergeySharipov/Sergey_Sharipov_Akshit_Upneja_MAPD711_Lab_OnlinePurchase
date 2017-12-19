package ca.akshit.and.sergey.sergey_sharipov_akshit_upneja_mapd711_lab_onlinepurchase.util;

import android.app.Application;
import android.graphics.drawable.Drawable;

import ca.akshit.and.sergey.sergey_sharipov_akshit_upneja_mapd711_lab_onlinepurchase.R;


/**
 * Created by Sergey-PC on 13.12.2017.
 */

public class App extends Application {
    static Drawable[] mBottleIcons;
    static Drawable mDoneIcon;

    public static Drawable[] getBottleIcons() {
        return mBottleIcons;
    }
    public static Drawable getDoneIcon() {
        return mDoneIcon;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mBottleIcons = new Drawable[]{
                getResources().getDrawable(R.drawable.ic_1l),
                getResources().getDrawable(R.drawable.ic_2l),
                getResources().getDrawable(R.drawable.ic_3l),
                getResources().getDrawable(R.drawable.ic_6l),
                getResources().getDrawable(R.drawable.ic_25l),
                getResources().getDrawable(R.drawable.ic_pump),
                getResources().getDrawable(R.drawable.ic_cooler)
        };
        mDoneIcon = getResources().getDrawable(R.drawable.ic_done);
    }
}
