package com.example.birthdayreminders;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DbHelper extends SQLiteOpenHelper {

    public static final  String DATABASE_NAME="birthday.db";
    public static final int DATABASE_VERSION=1;

    public static final String CREATE_TABLE="CREATE TABLE "+BirthdayContract.BirthdayEntry.TABLE_NAME+
            "("+BirthdayContract.BirthdayEntry.mobile+" text,"+BirthdayContract.BirthdayEntry.name+" text,"+
            BirthdayContract.BirthdayEntry.email+" text,"+BirthdayContract.BirthdayEntry.dob+" text)";

    public static final String DROP_TABLE="DROP TABLE IF EXISTS "+BirthdayContract.BirthdayEntry.TABLE_NAME;
    public DbHelper(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        Log.d("Database OPeration", "Database created");

    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(CREATE_TABLE);
        Log.d("Datbase operation","Table created");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        sqLiteDatabase.execSQL(DROP_TABLE);
        onCreate(sqLiteDatabase);

    }
    public void addBirthday(String mobile,String name,String email,String dob,SQLiteDatabase sqLiteDatabase)
    {
        ContentValues contentValues=new ContentValues();
        contentValues.put(BirthdayContract.BirthdayEntry.mobile,mobile);
        contentValues.put(BirthdayContract.BirthdayEntry.name,name);
        contentValues.put(BirthdayContract.BirthdayEntry.email,email);
        contentValues.put(BirthdayContract.BirthdayEntry.dob,dob);

        sqLiteDatabase.insert(BirthdayContract.BirthdayEntry.TABLE_NAME,null,contentValues);
        Log.d("Database operation","New entry inserted...");
    }
    public Cursor displayBirthday(SQLiteDatabase sqLiteDatabase)
    {


        String []projection={BirthdayContract.BirthdayEntry.mobile,BirthdayContract.BirthdayEntry.name,
                BirthdayContract.BirthdayEntry.email,BirthdayContract.BirthdayEntry.dob};

        Cursor cursor=sqLiteDatabase.query(BirthdayContract.BirthdayEntry.TABLE_NAME,projection,null,null,null,null,null);

        return cursor;
    }

    public void updateBirthday(String mobile,String name,String email,String dob,SQLiteDatabase sqLiteDatabase)
    {


        ContentValues contentValues=new ContentValues();
        contentValues.put(BirthdayContract.BirthdayEntry.name,name);
        contentValues.put(BirthdayContract.BirthdayEntry.email,email);
        contentValues.put(BirthdayContract.BirthdayEntry.dob,dob);

        String selection=BirthdayContract.BirthdayEntry.mobile+"="+mobile;
        sqLiteDatabase.update(BirthdayContract.BirthdayEntry.TABLE_NAME,contentValues,selection,null);


    }

    public void deleteBithday(String mobile,SQLiteDatabase sqLiteDatabase)
    {


        try {
            String selection = BirthdayContract.BirthdayEntry.mobile + "=" + mobile;

            sqLiteDatabase.delete(BirthdayContract.BirthdayEntry.TABLE_NAME, selection, null);
        }
        catch(Exception e)
        {

        }

    }
}
