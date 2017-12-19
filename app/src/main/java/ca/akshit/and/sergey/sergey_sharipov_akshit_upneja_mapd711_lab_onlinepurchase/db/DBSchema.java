package ca.akshit.and.sergey.sergey_sharipov_akshit_upneja_mapd711_lab_onlinepurchase.db;

/**
 * Created by Sergey-PC on 23.11.2017.
 */

class DBSchema {
    static final class ClerkTable {
        static final String NAME = "clerks";

        static final class Cols {
            static final String UUID = "uuid";
            static final String LOGIN = "login";
            static final String PASSWORD = "password";
            static final String FIRST_NAME = "first_name";
            static final String LAST_NAME = "last_name";

            static String toStringAll() {
                return UUID + DBDataTypes.TEXT + ", " +
                        LOGIN + DBDataTypes.TEXT + ", " +
                        PASSWORD + DBDataTypes.TEXT + ", " +
                        FIRST_NAME + DBDataTypes.TEXT + ", " +
                        LAST_NAME + DBDataTypes.TEXT;
            }
        }
    }

    static final class CustomerTable {
        static final String NAME = "customers";

        static final class Cols {
            static final String UUID = "uuid";
            static final String LOGIN = "login";
            static final String PASSWORD = "password";
            static final String FIRST_NAME = "first_name";
            static final String LAST_NAME = "last_name";
            static final String ADDRESS = "address";
            static final String PROVINCE = "province";
            static final String CITY = "city";
            static final String POSTAL_CODE = "postal_code";

            static String toStringAll() {
                return UUID + DBDataTypes.TEXT + ", " +
                        LOGIN + DBDataTypes.TEXT + ", " +
                        PASSWORD + DBDataTypes.TEXT + ", " +
                        FIRST_NAME + DBDataTypes.TEXT + ", " +
                        LAST_NAME + DBDataTypes.TEXT + ", " +
                        ADDRESS + DBDataTypes.TEXT + ", " +
                        PROVINCE + DBDataTypes.TEXT + ", " +
                        CITY + DBDataTypes.TEXT + ", " +
                        POSTAL_CODE + DBDataTypes.TEXT;
            }
        }
    }

    static final class OrderTable {
        static final String NAME = "orders";

        static final class Cols {
            static final String UUID = "uuid";
            static final String CUSTOMER_ID = "customer_id";
            static final String PRODUCT_ID = "product_id";
            static final String EMPLOYEE_ID = "employee_id";
            static final String ORDER_DATE = "order_date";
            static final String STATUS = "status";

            static String toStringAll() {
                return UUID + DBDataTypes.TEXT + ", " +
                        CUSTOMER_ID + DBDataTypes.TEXT + ", " +
                        PRODUCT_ID + DBDataTypes.TEXT + ", " +
                        EMPLOYEE_ID + DBDataTypes.TEXT + ", " +
                        ORDER_DATE + DBDataTypes.TEXT + ", " +
                        STATUS + DBDataTypes.INTEGER;
            }
        }
    }

    static final class ProductTable {
        static final String NAME = "products";

        static final class Cols {
            static final String UUID = "uuid";
            static final String PRODUCT_NAME = "product_name";
            static final String PRICE = "price";
            static final String QUANTITY = "quantity";
            static final String CATEGORY = "category";

            static String toStringAll() {
                return UUID + DBDataTypes.TEXT + ", " +
                        PRODUCT_NAME + DBDataTypes.TEXT + ", " +
                        PRICE + DBDataTypes.REAL + ", " +
                        QUANTITY + DBDataTypes.INTEGER + ", " +
                        CATEGORY + DBDataTypes.TEXT;
            }
        }
    }
}
