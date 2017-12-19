package ca.akshit.and.sergey.sergey_sharipov_akshit_upneja_mapd711_lab_onlinepurchase.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import ca.akshit.and.sergey.sergey_sharipov_akshit_upneja_mapd711_lab_onlinepurchase.model.Customer;


/**
 * Created by Sergey-PC on 04.12.2017.
 */

public class CustomerLab {
    private DBLab mDBLab;

    public CustomerLab(Context context) {
        mDBLab = DBLab.get(context);
        if (getItemCount() == 0)
            initCustomer();
    }

    private static ContentValues getContentValues(Customer customer) {
        ContentValues values = new ContentValues();

        values.put(DBSchema.CustomerTable.Cols.UUID, customer.getId().toString());
        values.put(DBSchema.CustomerTable.Cols.LOGIN, customer.getLogin());
        values.put(DBSchema.CustomerTable.Cols.PASSWORD, customer.getPassword());
        values.put(DBSchema.CustomerTable.Cols.FIRST_NAME, customer.getFirstName());
        values.put(DBSchema.CustomerTable.Cols.LAST_NAME, customer.getLastName());
        values.put(DBSchema.CustomerTable.Cols.ADDRESS, customer.getAddress());
        values.put(DBSchema.CustomerTable.Cols.PROVINCE, customer.getProvince());
        values.put(DBSchema.CustomerTable.Cols.CITY, customer.getCity());
        values.put(DBSchema.CustomerTable.Cols.POSTAL_CODE, customer.getPostalCode());

        return values;
    }

    public int getItemCount() {
        return getCustomers().size();
    }

    public List<Customer> getCustomers() {
        List<Customer> customers = new ArrayList<>();
        DBCursorWrapper cursor = queryCustomers(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                customers.add(cursor.getCustomer());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return customers;
    }

    public Customer getCustomer(UUID id) {
        DBCursorWrapper cursor = queryCustomers(
                DBSchema.CustomerTable.Cols.UUID + " = ?",
                new String[]{id.toString()}
        );
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getCustomer();
        } finally {
            cursor.close();
        }
    }

    public boolean isEmailAvailable(String email) {
        return getCustomer(email) == null;
    }

    public boolean isCustomerDataCorrect(String login, String password) {
        Customer customer = getCustomer(login);
        return customer != null && customer.getPassword().equals(password);
    }

    public Customer getCustomer(String login) {
        DBCursorWrapper cursor = queryCustomers(
                DBSchema.ClerkTable.Cols.LOGIN + " = ?",
                new String[]{login}
        );
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getCustomer();
        } finally {
            cursor.close();
        }
    }


    public void addCustomer(Customer customer) {
        ContentValues values = getContentValues(customer);
        mDBLab.getDatabase().insert(DBSchema.CustomerTable.NAME, null, values);
    }

    public void updateCustomer(Customer customer) {
        String uuidString = customer.getId().toString();
        ContentValues values = getContentValues(customer);
        mDBLab.getDatabase().update(DBSchema.CustomerTable.NAME, values,
                DBSchema.CustomerTable.Cols.UUID + " = ?",
                new String[]{uuidString});
    }

    public void deleteCustomer(Customer customer) {
        String uuidString = customer.getId().toString();
        mDBLab.getDatabase().delete(DBSchema.CustomerTable.NAME,
                DBSchema.CustomerTable.Cols.UUID + " = ?",
                new String[]{uuidString});
    }

    private DBCursorWrapper queryCustomers(String whereClause, String[] whereArgs) {
        Cursor cursor = mDBLab.getDatabase().query(
                DBSchema.CustomerTable.NAME,
                null, // Columns - null selects all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null // orderBy
        );
        return new DBCursorWrapper(cursor);
    }

    private void initCustomer() {
        String login = "test@mail.com";
        String password = "qwerty123";
        String firstName = "Vladislav";
        String lastName = "Tigranovich";
        String address = "22 Progress Drive";
        String province = "Ontario";
        String city = "Toronto";
        String postalCode = "M1Y4B3";
        Customer customer = new Customer(login, password, firstName, lastName,
                address, province, city, postalCode);
        addCustomer(customer);
    }
}
