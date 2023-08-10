package com.example.test;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class SQLiteAdapter {

    // Constant variables
    public static final String MYDATABASE_NAME = "MY_DATABASE3";
    public static final String MYDATABASE_TABLE = "MY_TABLE_HISTORY";
    public static final int MYDATABASE_VERSION = 2;
    // Column (in the table)
    public static final String KEY_CONTENT = "Date_Time";
    public static final String KEY_CONTENT_2 = "Mode";
    public static final String KEY_CONTENT_3 = "Description";
    public static final String KEY_CONTENT_6 = "TotalBill";
    public static final String KEY_CONTENT_4 = "Name";
    public static final String KEY_CONTENT_7 = "Amount";


    // SQL command: Create a table with 4 columns
    private static final String SCRIPT_CREATE_DATABASE = "create table " + MYDATABASE_TABLE +
            " (id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_CONTENT + " text not null, "
            + KEY_CONTENT_2 + " text, "
            + KEY_CONTENT_3 + " text, "
            + KEY_CONTENT_6 + " text, "
            + KEY_CONTENT_4 + " text, "
            + KEY_CONTENT_7 + " text)";

    // Variables (for database creation)
    private Context context;
    private SQLiteHelper sqLiteHelper;  // We created this SQLiteHelper class below
    private SQLiteDatabase sqLiteDatabase;

    // Constructor for SQLiteAdapter
    public SQLiteAdapter(Context c)
    {
        context = c;
    }

    // Open the database to write something
    public SQLiteAdapter openToWrite() throws android.database.SQLException
    {
        sqLiteHelper = new SQLiteHelper(context, MYDATABASE_NAME, null, MYDATABASE_VERSION);    // Format follow the constructor below
        sqLiteDatabase = sqLiteHelper.getWritableDatabase();    // Writing mode.

        return this;
    }

    // Open the database to read something
    public SQLiteAdapter openToRead() throws android.database.SQLException
    {
        sqLiteHelper = new SQLiteHelper(context, MYDATABASE_NAME, null, MYDATABASE_VERSION);    // Format follow the constructor below
        sqLiteDatabase = sqLiteHelper.getReadableDatabase();    // Reading mode

        return this;
    }

    // To write the data into the table for equalBD
    public long insert(String datetime, String mode, String desc, String total, String amt)
    {
        ContentValues contentValues = new ContentValues();
        // To write the content to the column of KEY_CONTENT (KEY_CONTENT is the column in the table)
        contentValues.put(KEY_CONTENT, datetime);    // insert content into column KEY_CONTENT
        contentValues.put(KEY_CONTENT_2, mode);    // insert content_2 into column KEY_CONTENT_2
        contentValues.put(KEY_CONTENT_3, desc);    // insert content_2 into column KEY_CONTENT_2
        contentValues.put(KEY_CONTENT_6, total);
        contentValues.put(KEY_CONTENT_4, "null");    // insert content_2 into column KEY_CONTENT_2
        contentValues.put(KEY_CONTENT_7, amt);

        return sqLiteDatabase.insert(MYDATABASE_TABLE, null, contentValues);
    }

    // To write the data into the table for non-equalBD
    public long insert(String datetime, String mode, String desc, String total, String name, String amt)
    {
        ContentValues contentValues = new ContentValues();
        // To write the content to the column of KEY_CONTENT (KEY_CONTENT is the column in the table)
        contentValues.put(KEY_CONTENT, datetime);    // insert content into column KEY_CONTENT
        contentValues.put(KEY_CONTENT_2, mode);    // insert content_2 into column KEY_CONTENT_2
        contentValues.put(KEY_CONTENT_3, desc);    // insert content_2 into column KEY_CONTENT_2
        contentValues.put(KEY_CONTENT_6, total);
        contentValues.put(KEY_CONTENT_4, name);    // insert content_2 into column KEY_CONTENT_2
        contentValues.put(KEY_CONTENT_7, amt);

        return sqLiteDatabase.insert(MYDATABASE_TABLE, null, contentValues);
    }


    // To retrieve/read from the table for history
    public String queue()
    {
        String[] columns = new String[] {KEY_CONTENT, KEY_CONTENT_2, KEY_CONTENT_3, KEY_CONTENT_6, KEY_CONTENT_4, KEY_CONTENT_7};  // Which column you want to read
        // To locate the cursor
        // Move cursor to table MYDATABASE_TABLE, column columns
        Cursor cursor = sqLiteDatabase.query(MYDATABASE_TABLE, columns,
                null, null, null, null, KEY_CONTENT + "DESC");

        String result = "";

        int index_CONTENT = cursor.getColumnIndex(KEY_CONTENT); // Get ID of the column
        int index_CONTENT_2 = cursor.getColumnIndex(KEY_CONTENT_2); // Get ID of the column
        int index_CONTENT_3 = cursor.getColumnIndex(KEY_CONTENT_3); // Get ID of the column
        int index_CONTENT_4 = cursor.getColumnIndex(KEY_CONTENT_6); // Get ID of the column
        int index_CONTENT_5 = cursor.getColumnIndex(KEY_CONTENT_4); // Get ID of the column
        int index_CONTENT_6 = cursor.getColumnIndex(KEY_CONTENT_7); // Get ID of the column

        // Read data until finish
        // Move cursor to 1st item (row); As long as cursor isn't after last item (row); Move cursor to next item (row)
        for(cursor.moveToFirst(); !(cursor.isAfterLast()); cursor.moveToNext())
        {
            result = result + cursor.getString(index_CONTENT) + "\n "
                    + "Mode: " + cursor.getString(index_CONTENT_2) + "; "
                    + "Description: " + cursor.getString(index_CONTENT_3) + "; "
                    + "Total bill: " + cursor.getString(index_CONTENT_4) + "; "
                    + "Name: " + cursor.getString(index_CONTENT_5) + "; "
                    + "Amount: " + cursor.getString(index_CONTENT_6) + "\n";
        }

        return result;
    }


    // Close the database
    public void close()
    {
        sqLiteHelper.close();
    }

    // Reduce the redundancy
    public int deleteAll()
    {
        return sqLiteDatabase.delete(MYDATABASE_TABLE, null, null);
    }

    // SQLiteOpenHelper: A helper class to manage database creation and version management
    public class SQLiteHelper extends SQLiteOpenHelper
    {

        // Constructor for SQLiteHelper
        public SQLiteHelper(@Nullable Context context, @Nullable String name,
                            @Nullable SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        // Create a table with column(s)
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SCRIPT_CREATE_DATABASE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(SCRIPT_CREATE_DATABASE);
        }
    }
}
