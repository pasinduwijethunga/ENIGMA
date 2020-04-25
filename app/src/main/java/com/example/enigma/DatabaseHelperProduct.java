package com.example.enigma;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelperProduct extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "product.db";

    public static final String PRODUCT_TABLE_NAME = "product_table";
    public static final String Col_Id = "ID";
    public static final String Col_name = "Name";
    public static final String Col_title = "Title";
    public static final String Col_rating = "Rating";
    public static final String Col_price = "Price";

    public static final String DELIVERY_TABLE_NAME = "deliveries_table";
    public static final String Col_ProID = "ID";
    public static final String Col_ProName = "Product";
    public static final String Col_cusName = "Name";
    public static final String Col_address = "Address";
    public static final String Col_Phone = "Phone";
    public static final String Col_qty = "Quantity";
    public static final String Col_ProPrice = "Price";

    public static final String EMPLOYEE_TABLE_NAME = "employee_table";
    public static final String COL_eID = "ID";
    public static final String COL_eNAME = "Name";
    public static final String COL_eCONTACT = "Contact";
    public static final String COL_eEMAIL = "Email";
    public static final String COL_ePASSWORD = "Password";

    public static final String PURCHASE_TABLE_NAME = "Cart_table";
    public static final String COL_1 = "cartID";
    public static final String COL_2 = "itemName";
    public static final String COL_3 = "cname";
    public static final String COL_4 = "address";
    public static final String COL_5 = "price";
    public static final String COL_6 = "qty";



    public DatabaseHelperProduct(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + DELIVERY_TABLE_NAME + "(ID INTEGER PRIMARY KEY , Product TEXT, Name TEXT,Address TEXT,Phone TEXT, Quantity INTEGER, Price DOUBLE )");
        sqLiteDatabase.execSQL("CREATE TABLE " + PRODUCT_TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, Name TEXT, Title text,Rating DOUBLE,Price DOUBLE )");
        sqLiteDatabase.execSQL("CREATE TABLE " + EMPLOYEE_TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, Name TEXT, Contact TEXT, Email TEXT, Password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + PRODUCT_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DELIVERY_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + EMPLOYEE_TABLE_NAME);
        onCreate(sqLiteDatabase);

    }

    //samitha insert
    public boolean addInfo(Product obj) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelperProduct.Col_name, obj.getTitle());
        values.put(DatabaseHelperProduct.Col_title, obj.getShortdesc());
        values.put(DatabaseHelperProduct.Col_rating, obj.getRating());
        values.put(DatabaseHelperProduct.Col_price, obj.getPrice());

        long result = db.insert(PRODUCT_TABLE_NAME, null, values);

        if (result > 0) {
            return true;

        } else {
            return false;
        }

    }

    //uvini insert
    public boolean addDelivery(Delivery delivery) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        /*values.put(Col_ProID,proID);
        values.put(Col_ProName,proName);
        values.put(Col_cusName,cusName);
        values.put(Col_address,address);
        values.put(Col_Phone,phone);
        values.put(Col_qty,qty);
        values.put(Col_ProPrice,price);*/

        values.put(DatabaseHelperProduct.Col_ProID, delivery.getProID());
        values.put(DatabaseHelperProduct.Col_ProName, delivery.getProName());
        values.put(DatabaseHelperProduct.Col_cusName, delivery.getCustomerName());
        values.put(DatabaseHelperProduct.Col_address, delivery.getAddress());
        values.put(DatabaseHelperProduct.Col_Phone, delivery.getPhone());
        values.put(DatabaseHelperProduct.Col_qty, delivery.getQuantity());
        values.put(DatabaseHelperProduct.Col_ProPrice, delivery.getPrice());

        long result = db.insert(DELIVERY_TABLE_NAME, null, values);

        if (result > 0) {
            return true;

        } else {
            return false;

        }


    }

    public Cursor getAllData(){
        SQLiteDatabase db = getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + PRODUCT_TABLE_NAME,null);
        return res;
    }

    public Cursor getAllDelivery(){
        SQLiteDatabase db = getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + DELIVERY_TABLE_NAME,null);
        return res;
    }

    //samitha update
    public boolean updateData(String id, String name, String des, String rate, String price){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();



        values.put(DatabaseHelperProduct.Col_Id,id);
        values.put(DatabaseHelperProduct.Col_name,name);
        values.put(DatabaseHelperProduct.Col_title,des);
        values.put(DatabaseHelperProduct.Col_rating,rate);
        values.put(DatabaseHelperProduct.Col_price,price);

        db.update(PRODUCT_TABLE_NAME,values,"ID = ?",new String[]{ id });

        return true;
    }

    //uvini update
    public boolean updateDelivery(String proID, String ProName, String cusName, String address, String phone, String qty, String price){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DatabaseHelperProduct.Col_ProID,proID);
        values.put(DatabaseHelperProduct.Col_ProName,ProName);
        values.put(DatabaseHelperProduct.Col_cusName,cusName);
        values.put(DatabaseHelperProduct.Col_address,address);
        values.put(DatabaseHelperProduct.Col_Phone,phone);
        values.put(DatabaseHelperProduct.Col_qty,qty);
        values.put(DatabaseHelperProduct.Col_price,price);

        db.update(DELIVERY_TABLE_NAME,values,"ID = ?", new String[] {proID});

        return true;
    }

    //samitha delete
    public int deleteData(String id){

        SQLiteDatabase db = getWritableDatabase();
        return db.delete(PRODUCT_TABLE_NAME,"ID = ?",new String[]{id});

    }

    //uvini delete
    public int deleteDelivery(String  proID){
        SQLiteDatabase db = getWritableDatabase();
        return  db.delete(DELIVERY_TABLE_NAME,"ID = ?",new String[] {proID});
    }



}
