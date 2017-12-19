package ca.akshit.and.sergey.sergey_sharipov_akshit_upneja_mapd711_lab_onlinepurchase.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import ca.akshit.and.sergey.sergey_sharipov_akshit_upneja_mapd711_lab_onlinepurchase.model.Clerk;


/**
 * Created by Sergey-PC on 04.12.2017.
 */

public class ClerkLab {
    private DBLab mDBLab;

    public ClerkLab(Context context) {
        mDBLab = DBLab.get(context);
        if (getItemCount() == 0)
            initClerks();
    }

    private static ContentValues getContentValues(Clerk clerk) {
        ContentValues values = new ContentValues();

        values.put(DBSchema.ClerkTable.Cols.UUID, clerk.getId().toString());
        values.put(DBSchema.ClerkTable.Cols.LOGIN, clerk.getLogin());
        values.put(DBSchema.ClerkTable.Cols.PASSWORD, clerk.getPassword());
        values.put(DBSchema.ClerkTable.Cols.FIRST_NAME, clerk.getFirstName());
        values.put(DBSchema.ClerkTable.Cols.LAST_NAME, clerk.getLastName());

        return values;
    }

    public int getItemCount() {
        return getClerks().size();
    }

    public List<Clerk> getClerks() {
        List<Clerk> clerks = new ArrayList<>();
        DBCursorWrapper cursor = queryClerks(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                clerks.add(cursor.getClerk());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return clerks;
    }

    public boolean isClerkDataCorrect(String login, String password){
        Clerk clerk = getClerk(login);
        return clerk != null && clerk.getPassword().equals(password);
    }

    public Clerk getClerk(String login) {
        DBCursorWrapper cursor = queryClerks(
                DBSchema.ClerkTable.Cols.LOGIN + " = ?",
                new String[]{login}
        );
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getClerk();
        } finally {
            cursor.close();
        }
    }

    public Clerk getClerk(UUID id) {
        DBCursorWrapper cursor = queryClerks(
                DBSchema.ClerkTable.Cols.UUID + " = ?",
                new String[]{id.toString()}
        );
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getClerk();
        } finally {
            cursor.close();
        }
    }

    public void addClerk(Clerk clerk) {
        ContentValues values = getContentValues(clerk);
        mDBLab.getDatabase().insert(DBSchema.ClerkTable.NAME, null, values);
    }

    public void updateClerk(Clerk clerk) {
        String uuidString = clerk.getId().toString();
        ContentValues values = getContentValues(clerk);
        mDBLab.getDatabase().update(DBSchema.ClerkTable.NAME, values,
                DBSchema.ClerkTable.Cols.UUID + " = ?",
                new String[]{uuidString});
    }

    public void deleteClerk(Clerk clerk) {
        String uuidString = clerk.getId().toString();
        mDBLab.getDatabase().delete(DBSchema.ClerkTable.NAME,
                DBSchema.ClerkTable.Cols.UUID + " = ?",
                new String[]{uuidString});
    }

    private DBCursorWrapper queryClerks(String whereClause, String[] whereArgs) {
        Cursor cursor = mDBLab.getDatabase().query(
                DBSchema.ClerkTable.NAME,
                null, // Columns - null selects all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null // orderBy
        );
        return new DBCursorWrapper(cursor);
    }

    private void initClerks() {
        String login = "test@mail.com";
        String password = "12345678";
        String firstName = "Vladislav";
        String lastName = "Tigranovich";
        Clerk clerk = new Clerk(login, password, firstName, lastName);
        addClerk(clerk);
    }
}
