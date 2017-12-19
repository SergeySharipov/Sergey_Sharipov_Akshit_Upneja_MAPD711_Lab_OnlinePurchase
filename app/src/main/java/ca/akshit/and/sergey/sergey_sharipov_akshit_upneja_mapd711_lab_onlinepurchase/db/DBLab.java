package ca.akshit.and.sergey.sergey_sharipov_akshit_upneja_mapd711_lab_onlinepurchase.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

class DBLab {
    private static DBLab sDBLab;

    private SQLiteDatabase mDatabase;

    private DBLab(Context context) {
        mDatabase = new DBHelper(context.getApplicationContext())
                .getWritableDatabase();
    }

    static DBLab get(Context context) {
        if (sDBLab == null) {
            sDBLab = new DBLab(context);
        }
        return sDBLab;
    }

    SQLiteDatabase getDatabase() {
        return mDatabase;
    }
}
