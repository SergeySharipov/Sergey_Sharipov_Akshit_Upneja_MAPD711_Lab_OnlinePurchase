package ca.akshit.and.sergey.sergey_sharipov_akshit_upneja_mapd711_lab_onlinepurchase.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Sergey-PC on 23.11.2017.
 */

class DBHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "db_app.db";

    DBHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    private void createTable(SQLiteDatabase db, String name, String cols) {
        String clerkTable = String.format("create table %s (" +
                        " _id %s, %s )",
                name,
                DBDataTypes.PRIMARY_KEY,
                cols
        );
        db.execSQL(clerkTable);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTable(db, DBSchema.ClerkTable.NAME, DBSchema.ClerkTable.Cols.toStringAll());

        createTable(db, DBSchema.CustomerTable.NAME, DBSchema.CustomerTable.Cols.toStringAll());

        createTable(db, DBSchema.OrderTable.NAME, DBSchema.OrderTable.Cols.toStringAll());

        createTable(db, DBSchema.ProductTable.NAME, DBSchema.ProductTable.Cols.toStringAll());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}