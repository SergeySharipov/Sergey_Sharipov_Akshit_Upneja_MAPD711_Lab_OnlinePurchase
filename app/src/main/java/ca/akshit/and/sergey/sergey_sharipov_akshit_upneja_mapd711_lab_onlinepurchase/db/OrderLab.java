package ca.akshit.and.sergey.sergey_sharipov_akshit_upneja_mapd711_lab_onlinepurchase.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import ca.akshit.and.sergey.sergey_sharipov_akshit_upneja_mapd711_lab_onlinepurchase.model.Order;


/**
 * Created by Sergey-PC on 04.12.2017.
 */

public class OrderLab {
    private DBLab mDBLab;

    public OrderLab(Context context) {
        mDBLab = DBLab.get(context);
    }

    private static ContentValues getContentValues(Order Order) {
        ContentValues values = new ContentValues();

        values.put(DBSchema.OrderTable.Cols.UUID, Order.getId().toString());
        values.put(DBSchema.OrderTable.Cols.CUSTOMER_ID, Order.getCustomerId().toString());
        values.put(DBSchema.OrderTable.Cols.PRODUCT_ID, Order.getProductId().toString());
        values.put(DBSchema.OrderTable.Cols.EMPLOYEE_ID, Order.getEmployeeId().toString());
        values.put(DBSchema.OrderTable.Cols.ORDER_DATE, Order.getOrderDate().toString());
        values.put(DBSchema.OrderTable.Cols.STATUS, Order.getStatus());

        return values;
    }

    public List<Order> getOrders() {
        List<Order> orders = new ArrayList<>();
        DBCursorWrapper cursor = queryOrders(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                orders.add(cursor.getOrder());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return orders;
    }

    public List<Order> getOrders(UUID customerId) {
        List<Order> orders = new ArrayList<>();
        DBCursorWrapper cursor = queryOrders(
                DBSchema.OrderTable.Cols.CUSTOMER_ID + " = ?",
                new String[]{customerId.toString()});
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                orders.add(cursor.getOrder());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return orders;
    }

    public Order getOrder(UUID id) {
        DBCursorWrapper cursor = queryOrders(
                DBSchema.OrderTable.Cols.UUID + " = ?",
                new String[]{id.toString()});
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getOrder();
        } finally {
            cursor.close();
        }
    }

    public void addOrder(Order order) {
        ContentValues values = getContentValues(order);
        mDBLab.getDatabase().insert(DBSchema.OrderTable.NAME, null, values);
    }

    public void updateOrder(Order order) {
        String uuidString = order.getId().toString();
        ContentValues values = getContentValues(order);
        mDBLab.getDatabase().update(DBSchema.OrderTable.NAME, values,
                DBSchema.OrderTable.Cols.UUID + " = ?",
                new String[]{uuidString});
    }

    public void updateOrderStatus(Order order, int status) {
        order.setStatus(status);
        updateOrder(order);
    }

    public void deleteOrder(Order order) {
        String uuidString = order.getId().toString();
        mDBLab.getDatabase().delete(DBSchema.OrderTable.NAME,
                DBSchema.OrderTable.Cols.UUID + " = ?",
                new String[]{uuidString});
    }

    private DBCursorWrapper queryOrders(String whereClause, String[] whereArgs) {
        Cursor cursor = mDBLab.getDatabase().query(
                DBSchema.OrderTable.NAME,
                null, // Columns - null selects all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null // orderBy
        );
        return new DBCursorWrapper(cursor);
    }
}
