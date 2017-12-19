package ca.akshit.and.sergey.sergey_sharipov_akshit_upneja_mapd711_lab_onlinepurchase.db;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.Date;
import java.util.UUID;

import ca.akshit.and.sergey.sergey_sharipov_akshit_upneja_mapd711_lab_onlinepurchase.model.Clerk;
import ca.akshit.and.sergey.sergey_sharipov_akshit_upneja_mapd711_lab_onlinepurchase.model.Customer;
import ca.akshit.and.sergey.sergey_sharipov_akshit_upneja_mapd711_lab_onlinepurchase.model.Order;
import ca.akshit.and.sergey.sergey_sharipov_akshit_upneja_mapd711_lab_onlinepurchase.model.Product;


/**
 * Created by Sergey-PC on 23.11.2017.
 */

class DBCursorWrapper extends CursorWrapper {

     DBCursorWrapper(Cursor cursor) {
        super(cursor);
    }

     Clerk getClerk() {
        String uuidString = getString(getColumnIndex(DBSchema.ClerkTable.Cols.UUID));
        String login = getString(getColumnIndex(DBSchema.ClerkTable.Cols.LOGIN));
        String password = getString(getColumnIndex(DBSchema.ClerkTable.Cols.PASSWORD));
        String first_name = getString(getColumnIndex(DBSchema.ClerkTable.Cols.FIRST_NAME));
        String last_name = getString(getColumnIndex(DBSchema.ClerkTable.Cols.LAST_NAME));

        Clerk clerk = new Clerk(UUID.fromString(uuidString));
        clerk.setLogin(login);
        clerk.setPassword(password);
        clerk.setFirstName(first_name);
        clerk.setLastName(last_name);

        return clerk;
    }

     Customer getCustomer() {
        String uuidString = getString(getColumnIndex(DBSchema.CustomerTable.Cols.UUID));
        String login = getString(getColumnIndex(DBSchema.CustomerTable.Cols.LOGIN));
        String password = getString(getColumnIndex(DBSchema.CustomerTable.Cols.PASSWORD));
        String firstName = getString(getColumnIndex(DBSchema.CustomerTable.Cols.FIRST_NAME));
        String lastName = getString(getColumnIndex(DBSchema.CustomerTable.Cols.LAST_NAME));
        String address = getString(getColumnIndex(DBSchema.CustomerTable.Cols.ADDRESS));
        String province = getString(getColumnIndex(DBSchema.CustomerTable.Cols.PROVINCE));
        String city = getString(getColumnIndex(DBSchema.CustomerTable.Cols.CITY));
        String postalCode = getString(getColumnIndex(DBSchema.CustomerTable.Cols.POSTAL_CODE));

        Customer customer = new Customer(UUID.fromString(uuidString));
        customer.setLogin(login);
        customer.setPassword(password);
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setAddress(address);
        customer.setProvince(province);
        customer.setCity(city);
        customer.setPostalCode(postalCode);

        return customer;
    }

     Order getOrder() {
        String uuidString = getString(getColumnIndex(DBSchema.OrderTable.Cols.UUID));
        String productId = getString(getColumnIndex(DBSchema.OrderTable.Cols.PRODUCT_ID));
        String customerId = getString(getColumnIndex(DBSchema.OrderTable.Cols.CUSTOMER_ID));
        String employeeId = getString(getColumnIndex(DBSchema.OrderTable.Cols.EMPLOYEE_ID));
        String orderDate = getString(getColumnIndex(DBSchema.OrderTable.Cols.ORDER_DATE));
        int status = getInt(getColumnIndex(DBSchema.OrderTable.Cols.STATUS));

        Order order = new Order(UUID.fromString(uuidString));
        order.setProductId(UUID.fromString(productId));
        order.setCustomerId(UUID.fromString(customerId));
        order.setEmployeeId(UUID.fromString(employeeId));
        order.setOrderDate(new Date(orderDate));
        order.setStatus(status);

        return order;
    }

     Product getProduct() {
        String uuidString = getString(getColumnIndex(DBSchema.ProductTable.Cols.UUID));
        String productName = getString(getColumnIndex(DBSchema.ProductTable.Cols.PRODUCT_NAME));
        double price = getDouble(getColumnIndex(DBSchema.ProductTable.Cols.PRICE));
        int quantity = getInt(getColumnIndex(DBSchema.ProductTable.Cols.QUANTITY));
        String category = getString(getColumnIndex(DBSchema.ProductTable.Cols.CATEGORY));

        Product product = new Product(UUID.fromString(uuidString));
        product.setProductName(productName);
        product.setPrice(price);
        product.setQuantity(quantity);
        product.setCategory(category);

        return product;
    }
}