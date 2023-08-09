package com.example.test;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class SQLiteAdapter {

    // Constant variables
    public static final String MYDATABASE_NAME = "MY_DATABASE";
    public static final String MYDATABASE_TABLE = "MY_TABLE_FOOD";
    public static final int MYDATABASE_VERSION = 2;
    // Column (in the table)
    public static final String KEY_CONTENT = "Content";
    public static final String KEY_CONTENT_2 = "Ingredients";
    public static final String VALUE = "Price";

    // SQL command: Create a table with 4 columns
    private static final String SCRIPT_CREATE_DATABASE = "create table " + MYDATABASE_TABLE +
            " (id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_CONTENT + " text not null, "
            + KEY_CONTENT_2 + " text, "
            + VALUE + " float);";

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

    // To write the data into the table with ONE column
    public long insert(String content)
    {
        ContentValues contentValues = new ContentValues();
        // To write the content to the column of KEY_CONTENT (KEY_CONTENT is the column in the table)
        contentValues.put(KEY_CONTENT, content);

        return sqLiteDatabase.insert(MYDATABASE_TABLE, null, contentValues);
    }

    // To write the data into the table with THREE column
    public long insert(String content, String content_2, float content_3)
    {
        ContentValues contentValues = new ContentValues();
        // To write the content to the column of KEY_CONTENT (KEY_CONTENT is the column in the table)
        contentValues.put(KEY_CONTENT, content);    // insert content into column KEY_CONTENT
        contentValues.put(KEY_CONTENT_2, content_2);    // insert content_2 into column KEY_CONTENT_2
        contentValues.put(VALUE, content_3);

        return sqLiteDatabase.insert(MYDATABASE_TABLE, null, contentValues);
    }

    // To retrieve/read from the table with ONE column
    public String queueAll()
    {
        String[] columns = new String[] {KEY_CONTENT};  // Which column you want to read
        // To locate the cursor
        // Move cursor to table MYDATABASE_TABLE, column columns
        Cursor cursor = sqLiteDatabase.query(MYDATABASE_TABLE, columns,
                null, null, null, null, null);

        String result = "";

        int index_CONTENT = cursor.getColumnIndex(KEY_CONTENT); // Get ID of the column

        // Read data until finish
        // Move cursor to 1st item (row); As long as cursor isn't after last item (row); Move cursor to next item (row)
        for(cursor.moveToFirst(); !(cursor.isAfterLast()); cursor.moveToNext())
        {
            result = result + cursor.getString(index_CONTENT) + "\n";
        }

        return result;
    }

    // To retrieve/read from the table with THREE column
    public String queueAll_Two()
    {
        String[] columns = new String[] {KEY_CONTENT, KEY_CONTENT_2, VALUE};  // Which column you want to read
        // To locate the cursor
        // Move cursor to table MYDATABASE_TABLE, column columns
        Cursor cursor = sqLiteDatabase.query(MYDATABASE_TABLE, columns,
                null, null, null, null, null);

        String result = "";

        int index_CONTENT = cursor.getColumnIndex(KEY_CONTENT); // Get ID of the column
        int index_CONTENT_2 = cursor.getColumnIndex(KEY_CONTENT_2); // Get ID of the column
        int index_CONTENT_3 = cursor.getColumnIndex(VALUE); // Get ID of the column

        // Read data until finish
        // Move cursor to 1st item (row); As long as cursor isn't after last item (row); Move cursor to next item (row)
        for(cursor.moveToFirst(); !(cursor.isAfterLast()); cursor.moveToNext())
        {
            result = result + cursor.getString(index_CONTENT) + "; "
                    + cursor.getString(index_CONTENT_2) + "; "
                    + cursor.getString(index_CONTENT_3) + "\n";
        }

        return result;
    }

    // To retrieve/read from the table with THREE columns based on certain criteria
    // SELECT Content, Ingredients, Price from MY_TABLE_FOOD
    // WHERE Ingredients == "Rice" OR Ingredients == "Flour"
    public String queueAll_Three()
    {
        String[] columns = new String[] {KEY_CONTENT, KEY_CONTENT_2, VALUE};  // Which column you want to read
        // To locate the cursor
        // Move cursor to table MYDATABASE_TABLE, column columns
        // ?: Content to be defined in selectionArgs
        // 1st ?: Refer to 1st value in selectionArgs; 2nd ?: Refer to 2nd value in selectionArgs
        Cursor cursor = sqLiteDatabase.query(MYDATABASE_TABLE, columns,
                KEY_CONTENT_2 + "=? OR " + KEY_CONTENT_2 + "=?",
                new String[] {"Rice", "Flour"},
                null, null, null);

        String result = "";

        int index_CONTENT = cursor.getColumnIndex(KEY_CONTENT); // Get ID of the column
        int index_CONTENT_2 = cursor.getColumnIndex(KEY_CONTENT_2); // Get ID of the column
        int index_CONTENT_3 = cursor.getColumnIndex(VALUE); // Get ID of the column

        // Read data until finish
        // Move cursor to 1st item (row); As long as cursor isn't after last item (row); Move cursor to next item (row)
        for(cursor.moveToFirst(); !(cursor.isAfterLast()); cursor.moveToNext())
        {
            result = result + cursor.getString(index_CONTENT) + "; "
                    + cursor.getString(index_CONTENT_2) + "; "
                    + cursor.getString(index_CONTENT_3) + "\n";
        }

        return result;
    }

    // To retrieve/read from the table with THREE columns based on certain criteria and order
    // SELECT Content, Ingredients, Price from MY_TABLE_FOOD
    // WHERE Ingredients == "Rice" OR Ingredients == "Flour"
    // ORDER BY Content ASC/DESC
    public String queueAll_Four()
    {
        String[] columns = new String[] {KEY_CONTENT, KEY_CONTENT_2, VALUE};  // Which column you want to read
        // To locate the cursor
        // Move cursor to table MYDATABASE_TABLE, column columns
        // ?: Content to be defined in selectionArgs
        // 1st ?: Refer to 1st value in selectionArgs; 2nd ?: Refer to 2nd value in selectionArgs
        Cursor cursor = sqLiteDatabase.query(MYDATABASE_TABLE, columns,
                KEY_CONTENT_2 + "=? OR " + KEY_CONTENT_2 + "=?",
                new String[] {"Rice", "Flour"},
                null, null, KEY_CONTENT + " ASC");

        String result = "";

        int index_CONTENT = cursor.getColumnIndex(KEY_CONTENT); // Get ID of the column
        int index_CONTENT_2 = cursor.getColumnIndex(KEY_CONTENT_2); // Get ID of the column
        int index_CONTENT_3 = cursor.getColumnIndex(VALUE); // Get ID of the column

        // Read data until finish
        // Move cursor to 1st item (row); As long as cursor isn't after last item (row); Move cursor to next item (row)
        for(cursor.moveToFirst(); !(cursor.isAfterLast()); cursor.moveToNext())
        {
            result = result + cursor.getString(index_CONTENT) + "; "
                    + cursor.getString(index_CONTENT_2) + "; "
                    + cursor.getString(index_CONTENT_3) + "\n";
        }

        return result;
    }

    // To retrieve/read from the table with THREE columns, group by
    // SELECT Content, Ingredients, Price from MY_TABLE_FOOD
    // GROUP BY Ingredients --> Sum( Price )
    public String queueAll_Five()
    {
        String[] columns = new String[] { KEY_CONTENT, KEY_CONTENT_2, "sum(" + VALUE + ")" };  // Which column you want to read
        // To locate the cursor
        // Move cursor to table MYDATABASE_TABLE, column columns
        // ?: Content to be defined in selectionArgs
        // 1st ?: Refer to 1st value in selectionArgs; 2nd ?: Refer to 2nd value in selectionArgs
        Cursor cursor = sqLiteDatabase.query(MYDATABASE_TABLE, columns,
                null, null,
                KEY_CONTENT_2, null, KEY_CONTENT + " ASC");

        String result = "";

        int index_CONTENT = cursor.getColumnIndex(KEY_CONTENT); // Get ID of the column
        int index_CONTENT_2 = cursor.getColumnIndex(KEY_CONTENT_2); // Get ID of the column
        int index_TOTAL_SPENT = cursor.getColumnIndex("sum(" + VALUE + ")"); // Get ID of the column

        // Read data until finish
        // Move cursor to 1st item (row); As long as cursor isn't after last item (row); Move cursor to next item (row)
        for(cursor.moveToFirst(); !(cursor.isAfterLast()); cursor.moveToNext())
        {
            result = result + cursor.getString(index_CONTENT) + "; "
                    + cursor.getString(index_CONTENT_2) + "; "
                    + cursor.getString(index_TOTAL_SPENT) + "\n";
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
