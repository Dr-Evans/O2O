package com.example.orangestooranges;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
 
public class DatabaseHandler extends SQLiteOpenHelper {
 
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "OrangesToOranges";
    static final String ORANGES_TABLE = "Oranges";
    static final String BLUES_TABLE = "Blues";
    static final String ID_COLUMN = "Card_ID";
    static final String NAME_COLUMN = "Card_Name";
    static final String TEXT_COLUMN = "Card_Text";
    
    //creates the database
    DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    
    //only runs once; at the first run of the app. Once db exists, will not run again
    @Override
    public void onCreate(SQLiteDatabase db) {
    //create table for oranges
    	db.execSQL("CREATE TABLE "+ORANGES_TABLE+" ("+ID_COLUMN+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+
    		    NAME_COLUMN+ " TEXT , " +TEXT_COLUMN+ " TEXT)");
    //create table for blues
    	db.execSQL("CREATE TABLE "+BLUES_TABLE+" ("+ID_COLUMN+ " INTEGER PRIMARY KEY , AUTOINCREMENT"+
    		    NAME_COLUMN+ " TEXT , " +TEXT_COLUMN+ " TEXT)");
    	
    }
    
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    	//will be added as is necessary
    	//changing version number will force a run of onUpgrade
    }
    
    
    //to be transformed into addCard(char cardColor, String cardName, String cardDesc)
    public void addCard() {
        SQLiteDatabase db = this.getWritableDatabase();
     
        ContentValues values = new ContentValues();
        values.put(NAME_COLUMN, "TestCard"); //Card name
        values.put(TEXT_COLUMN, "Sample Text"); //Card text
     
        // Inserting Row
        db.insert(ORANGES_TABLE, null, values);
        db.close(); // Closing database connection
    }
    
    //returns orange with given ID
    public CardOrange getOrange(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
 
        Cursor cursor = db.query(ORANGES_TABLE, new String[] { ID_COLUMN,
                NAME_COLUMN, TEXT_COLUMN}, ID_COLUMN + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
 
        CardOrange newOrange = new CardOrange(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        // return card
        return newOrange;
    }
    
}