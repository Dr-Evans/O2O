package com.example.orangestooranges;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
 
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
    	db.execSQL("CREATE TABLE "+BLUES_TABLE+" ("+ID_COLUMN+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+
    		    NAME_COLUMN+ " TEXT , " +TEXT_COLUMN+ " TEXT)");
    
    /*insert cards into database; this is the quickest means for insertion but I'm not sure if 
     * we want to declare all the cards like this or read them in from a file? just adding them here
     * for now and we can always move them and use a loop, but this will only every run once on install 
     */
    	
    	 String sql = "INSERT INTO "+ ORANGES_TABLE +" VALUES (?,?,?);";
         SQLiteStatement statement = db.compileStatement(sql);
         db.beginTransaction();
           	statement.clearBindings();
           	statement.bindString(2, "Bull Fight");
           	statement.bindString(3, "A whole lot of bull.");
           	statement.execute();
           	statement.clearBindings();
           	statement.bindString(2, "Can Of Worms");
           	statement.bindString(3, "Now you've opened it!");
           	statement.execute();
           	statement.clearBindings();
           	statement.bindString(2, "Desert");
           	statement.bindString(3, "The one you don't want more of!");
           	statement.execute();
           	statement.clearBindings();
           	statement.bindString(2, "Flat Tire");
           	statement.bindString(3, "What do you mean we don't have a spare?");
           	statement.execute();
           	statement.clearBindings();
           	statement.bindString(2, "Hole In One");
           	statement.bindString(3, "Par for the course?");
           	statement.execute();
           	statement.clearBindings();
           	statement.bindString(2, "Submarine");
           	statement.bindString(3, "It's a sandwich shaped like a boat or a boat shaped like a sandwhich.");
           	statement.execute();
           	statement.clearBindings();
           	statement.bindString(2, "Airplanes");
           	statement.bindString(3, "I want to sit by the window!");
           	statement.execute();
           	statement.clearBindings();
           	statement.bindString(2, "Buffalo");
           	statement.bindString(3, "A large animal with a big, shaggy head and a hump on its back, native to North America.");
           	statement.execute();
         db.setTransactionSuccessful();	
         db.endTransaction();
         
         //insert some blues too
         sql = "INSERT INTO "+ BLUES_TABLE +" VALUES (?,?,?);";
         statement = db.compileStatement(sql);
         db.beginTransaction();
         	statement.clearBindings();
         	statement.bindString(2, "Absurd");
         	statement.bindString(3, "ridiculous, senseless, foolish");
         	statement.execute();
         	statement.clearBindings();
         	statement.bindString(2, "Amazing");
         	statement.bindString(3, "surprising, wonderful");
         	statement.execute();
         	statement.clearBindings();
            statement.bindString(2, "Influential");
            statement.bindString(3, "powerful, prominent, important");
            statement.execute();
            statement.clearBindings();
            statement.bindString(2, "Lazy");
            statement.bindString(3, "lifeless, apathetic, weary");
            statement.execute();
        db.setTransactionSuccessful();	
        db.endTransaction();   
         
    	
    }
    
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    	//will be added as is necessary
    	//changing version number will force a run of onUpgrade
    }
    
    public void addBlue(String _cname, String _cdes) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME_COLUMN, _cname); //Card name
        values.put(TEXT_COLUMN, _cdes); //Card text
     
        // Inserting Row
        db.insert(BLUES_TABLE, null, values);
        db.close(); // Closing database connection
    }
    
    public void addOrange(String _cname, String _cdes) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME_COLUMN, _cname); //Card name
        values.put(TEXT_COLUMN, _cdes); //Card text
     
        // Inserting Row
        db.insert(ORANGES_TABLE, null, values);
        db.close(); // Closing database connection
    }
    
  //returns corresponding Orange given ID
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
    
    //returns corresponding Blue given ID
    public CardBlue getBlue(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
 
        Cursor cursor = db.query(BLUES_TABLE, new String[] { ID_COLUMN,
                NAME_COLUMN, TEXT_COLUMN}, ID_COLUMN + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
 
        CardBlue newBlue = new CardBlue(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        // return card
        return newBlue;
    }
    
}