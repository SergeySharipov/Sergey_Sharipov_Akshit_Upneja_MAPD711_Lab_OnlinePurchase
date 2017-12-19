package ca.akshit.and.sergey.sergey_sharipov_akshit_upneja_mapd711_lab_onlinepurchase.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import ca.akshit.and.sergey.sergey_sharipov_akshit_upneja_mapd711_lab_onlinepurchase.model.Constants;
import ca.akshit.and.sergey.sergey_sharipov_akshit_upneja_mapd711_lab_onlinepurchase.model.Product;

import static ca.akshit.and.sergey.sergey_sharipov_akshit_upneja_mapd711_lab_onlinepurchase.model.Constants.*;


/**
 * Created by Sergey-PC on 04.12.2017.
 */

public class ProductLab {
    private DBLab mDBLab;

    public ProductLab(Context context) {
        mDBLab = DBLab.get(context);
        if (getItemCount() == 0)
            initProducts();
    }

    private static ContentValues getContentValues(Product product) {
        ContentValues values = new ContentValues();

        values.put(DBSchema.ProductTable.Cols.UUID, product.getId().toString());
        values.put(DBSchema.ProductTable.Cols.PRODUCT_NAME, product.getProductName());
        values.put(DBSchema.ProductTable.Cols.PRICE, product.getPrice());
        values.put(DBSchema.ProductTable.Cols.QUANTITY, product.getQuantity());
        values.put(DBSchema.ProductTable.Cols.CATEGORY, product.getCategory());

        return values;
    }

    public int getItemCount() {
        return getProducts().size();
    }

    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        DBCursorWrapper cursor = queryProducts(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                products.add(cursor.getProduct());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return products;
    }

    public Product getProduct(UUID id) {
        DBCursorWrapper cursor = queryProducts(
                DBSchema.ProductTable.Cols.UUID + " = ?",
                new String[]{id.toString()}
        );
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getProduct();
        } finally {
            cursor.close();
        }
    }

    public void addProduct(Product product) {
        ContentValues values = getContentValues(product);
        mDBLab.getDatabase().insert(DBSchema.ProductTable.NAME, null, values);
    }

    public boolean plusProduct(UUID productId){
        return changeCountProduct(getProduct(productId),true);
    }

    public boolean minusProduct(Product product) {
        return changeCountProduct(product,false);
    }

    private boolean changeCountProduct(Product product, boolean plus){
        int quantity = product.getQuantity();
        if(quantity>0 && !plus){
            product.setQuantity(--quantity);
        } else if(plus){
            product.setQuantity(++quantity);
        } else return false;
            updateProduct(product);
        return true;
    }

    public void updateProduct(Product product) {
        String uuidString = product.getId().toString();
        ContentValues values = getContentValues(product);
        mDBLab.getDatabase().update(DBSchema.ProductTable.NAME, values,
                DBSchema.ProductTable.Cols.UUID + " = ?",
                new String[]{uuidString});
    }

    public void deleteProduct(Product product) {
        String uuidString = product.getId().toString();
        mDBLab.getDatabase().delete(DBSchema.ProductTable.NAME,
                DBSchema.ProductTable.Cols.UUID + " = ?",
                new String[]{uuidString});
    }

    private DBCursorWrapper queryProducts(String whereClause, String[] whereArgs) {
        Cursor cursor = mDBLab.getDatabase().query(
                DBSchema.ProductTable.NAME,
                null, // Columns - null selects all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null // orderBy
        );
        return new DBCursorWrapper(cursor);
    }

    private void initProducts() {
        //add BOTTLES with water
        int price = 5;
        int pricePlus = 2;
        int quantity = 10;
        Product product = new Product("1L", price, quantity,
                Category.BOTTLES_WITH_WATER);
        addProduct(product);
        price+=pricePlus;
        product = new Product("2L", price, quantity,
                Category.BOTTLES_WITH_WATER);
        addProduct(product);
        price+=pricePlus;
        product = new Product("3L", price, quantity,
                Category.BOTTLES_WITH_WATER);
        addProduct(product);
        price+=pricePlus;
        product = new Product("6L", price, quantity,
                Category.BOTTLES_WITH_WATER);
        addProduct(product);
        price+=pricePlus;
        product = new Product("25L", price, quantity,
                Category.BOTTLES_WITH_WATER);
        addProduct(product);
        //add BOTTLES without water
        price = 3;
        pricePlus = 1;
        quantity = 10;
        product = new Product("1L", price, quantity,
                Category.BOTTLES);
        addProduct(product);
        price+=pricePlus;
        product = new Product("2L", price, quantity,
                Category.BOTTLES);
        addProduct(product);
        price+=pricePlus;
        product = new Product("3L", price, quantity,
                Category.BOTTLES);
        addProduct(product);
        price+=pricePlus;
        product = new Product("6L", price, quantity,
                Category.BOTTLES);
        addProduct(product);
        price+=pricePlus;
        product = new Product("25L", price, quantity,
                Category.BOTTLES);
        addProduct(product);
        //other
        product = new Product("Cooler for 25L bottle", 150, quantity,
                Category.COOLERS);
        addProduct(product);
        product = new Product("Pump for 25L bottle", 30, quantity,
                Category.PUMP);
        addProduct(product);
    }
}
