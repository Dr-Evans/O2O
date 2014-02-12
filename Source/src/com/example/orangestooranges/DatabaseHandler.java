package com.example.orangestooranges;
 
import android.content.Context;
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
    
}