package com.example.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper
{
    static final String DB_NAME = "employee_db";
    static final int DB_VERSION = 1;
    static final String TABLE = "employee";
    static final String EMP_CODE = "code";
    static final String EMP_NAME = "name";
    static final String GENDER = "gender";
    static final String DEPARTMENT = "department";
    static final String SALARY = "salary";

    public DBHelper(Context context)
    {
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String query = "CREATE TABLE IF NOT EXISTS " + TABLE+ " ("
                + EMP_CODE + " TEXT PRIMARY KEY, "
                + EMP_NAME + " TEXT,"
                + GENDER + " TEXT,"
                + DEPARTMENT + " TEXT,"
                + SALARY + " TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE);
        onCreate(db);
    }

    public void insertEmployee(String code,String name,String gender,String dept,String salary)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(EMP_CODE, code);
        values.put(EMP_NAME, name);
        values.put(GENDER, gender);
        values.put(DEPARTMENT, dept);
        values.put(SALARY, salary);
        db.insertOrThrow(TABLE, null, values);
    }

    public void updateEmployee(String code, String name, String gender, String dept, String salary)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        if(name != "")
            values.put(EMP_NAME, name);
        if(gender != "")
            values.put(GENDER, gender);
        if(dept != "")
            values.put(DEPARTMENT, dept);
        if(salary != "")
            values.put(SALARY, salary);
        db.update(TABLE,values,"code=?",new String[] {code});
    }

    public void deleteEmployee(String code)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE, "code = ?", new String[]{code});
    }

    public String[] retrieveEmployee(String code)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] vals = new String[4];
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE+" WHERE code = '"+code+"';",null);

        if(cursor.moveToNext())
        {
            vals[0] = cursor.getString(1);
            vals[1] = cursor.getString(2);
            vals[2] = cursor.getString(3);
            vals[3] = cursor.getString(4);
            return vals;
        }
        else{
            return null;
        }
    }

}
