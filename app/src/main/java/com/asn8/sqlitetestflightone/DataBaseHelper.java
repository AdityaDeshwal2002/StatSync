package com.asn8.sqlitetestflightone;

import static java.sql.Types.NULL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.ContentView;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String CUSTOMER_TABLE = "CUSTOMER_TABLE";
    public static final String _ID = "ID";
    public static final String _NAME = "NAME";
    public static final String _AGE = "AGE";
    public static final String _ACTIVE_COSTUMER = "ACTIVE_COSTUMER";
    private SQLiteDatabase sqLiteDatabase ;



    public DataBaseHelper(@Nullable Context context) {

        super(context, "customer.db", null , 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String customerTableCreator = " CREATE TABLE " + CUSTOMER_TABLE + " ( " + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + _NAME + " TEXT , " + _AGE + " INT , " + _ACTIVE_COSTUMER + " BOOL )";
        sqLiteDatabase.execSQL(customerTableCreator);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addOne(CustomerModel customerModel){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //Items set
        contentValues.put(_AGE,customerModel.getAge());
        contentValues.put(_NAME,customerModel.getName());
        contentValues.put(_ACTIVE_COSTUMER,customerModel.getCustomerActive());
        //Items uploaded
        long inseerted = sqLiteDatabase.insert(CUSTOMER_TABLE, null, contentValues);
        if (inseerted == -1){
            return false;
        }
        else {
            return true ;
        }
    }
    public boolean deleteOne(CustomerModel customerModel){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String stringQuery = "DELETE FROM "+ CUSTOMER_TABLE + " WHERE " + _ID + " = " + customerModel.getId();
        Cursor cursor = sqLiteDatabase.rawQuery(stringQuery, null);
        if (cursor.moveToFirst()){
            return true;
        }
        else {
            return false;
        }


    }

    public List<CustomerModel> getEveryone(CustomerModel customerModel){
        List<CustomerModel> returnList = new ArrayList<>();
        String stringQuery = "SELECT * FROM " + CUSTOMER_TABLE ;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(stringQuery,null);
        if (cursor.moveToFirst()){
            do {
                int Customer_ID = cursor.getInt(0);
                String Customer_Name = cursor.getString(1);
                int Customer_Age = cursor.getInt(2);
                boolean Customer_ACTIVE = cursor.getInt(3) == 1 ? true : false;
                CustomerModel customer_Entering = new CustomerModel(Customer_ID,Customer_Name,Customer_Age,Customer_ACTIVE);
                returnList.add(customer_Entering);
            }while (cursor.moveToNext());

        }
        else {

        }
        cursor.close();
        sqLiteDatabase.close();
        return returnList;
    }



}
