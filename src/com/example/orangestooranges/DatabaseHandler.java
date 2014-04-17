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
        	statement.bindString(2, "Bad Haircut");
        	statement.bindString(3, "The perfect start to a bad hair day.");
        	statement.execute();
           	statement.clearBindings();
           	statement.bindString(2, "Bull Fight");
           	statement.bindString(3, "A whole lot of bull.");
           	statement.execute();
           	statement.clearBindings();
           	statement.bindString(2, "Car Crash");
           	statement.bindString(3, "Hey, it was an accident!");
           	statement.execute();
           	statement.clearBindings();
           	statement.bindString(2, "Cheap Motel");
           	statement.bindString(3, "No charge for the cockroaches.");
           	statement.execute();
           	statement.clearBindings();
           	statement.bindString(2, "Crawl Space");
           	statement.bindString(3, "Where you'll find something the cat dragged in.");
           	statement.execute();
           	statement.clearBindings();
           	statement.bindString(2, "Dozen Red Roses");
           	statement.bindString(3, "When eleven just won't do.");
           	statement.execute();
           	statement.clearBindings();
           	statement.bindString(2, "Flat Tire");
           	statement.bindString(3, "What do you mean there's no spare?");
           	statement.execute();
           	statement.clearBindings();
           	statement.bindString(2, "Full Moon");
           	statement.bindString(3, "When the moon hits your eye like a big pizza pie, that's amore! -Dean Martin");
           	statement.execute();
        	statement.clearBindings();
           	statement.bindString(2, "Haunted House");
           	statement.bindString(3, "Maybe if people would STOP building their dream homes on ancient burial grounds");
           	statement.execute();
        	statement.clearBindings();
           	statement.bindString(2, "High School Bathroom");
           	statement.bindString(3, "Fools rush in where angels fear to tread -Alexander Pope");
           	statement.execute();
        	statement.clearBindings();
           	statement.bindString(2, "Honeymoon");
           	statement.bindString(3, "America's top honeymoon spots are Hawaii, Niagara Falls, Las Vegas and Florida. You can get there by air, land, sea... or shotgun.");
           	statement.execute();
        	statement.clearBindings();
           	statement.bindString(2, "Locker Room");
           	statement.bindString(3, "Steamy atmospher... bawdy humor... sweaty bodies... HEY! Sounds like Cable TV!");
           	statement.execute();
           	statement.clearBindings();
           	statement.bindString(2, "Morgue");
           	statement.bindString(3, "Given strange eons, even death may die... -H.P. Lovecraft");
           	statement.execute();
           	statement.clearBindings();
           	statement.bindString(2, "A Nine Iron");
           	statement.bindString(3, "A golf club best used on short shots or large opponents.");
           	statement.execute();
           	statement.clearBindings();
           	statement.bindString(2, "School Bus");
           	statement.bindString(3, "The only thing we have to fear is fear itself. -Franklin D. Roosevelt");
           	statement.execute();
           	statement.clearBindings();
           	statement.bindString(2, "School Cafeteria");
           	statement.bindString(3, "Food Fight!");
           	statement.execute();
           	statement.clearBindings();
           	statement.bindString(2, "Sunrise");
           	statement.bindString(3, "But he who kisses the joy as it flies/ Lives in eternity's sunrise. -William Blake");
           	statement.execute();
           	statement.clearBindings();
           	statement.bindString(2, "Sunset");
           	statement.bindString(3, "The sun never set on the British Empire ... because God didn't trust the English in the dark.");
           	statement.execute();
           	statement.clearBindings();
           	statement.bindString(2, "Tree House");
           	statement.bindString(3, "Your first high-rise apartment.");
           	statement.execute();
           	statement.clearBindings();
           	statement.bindString(2, "Used Car Lot");
           	statement.bindString(3, "One of the most honest places around. Honest...");
           	statement.execute();
           	statement.clearBindings();
           	statement.bindString(2, "Abraham Lincoln");
           	statement.bindString(3, "1809-65, 16th U.S. president, led the Union to victory in the American Civil War and abolished slavery. Was assassinated for his efforts.");
           	statement.execute();
           	statement.clearBindings();
           	statement.bindString(2, "Adam Sandler");
           	statement.bindString(3, "1966- , American comedian, film star, and Saturday Night Live alumnus. Genius or goofball - you make the call.");
           	statement.execute();
           	statement.clearBindings();
           	statement.bindString(2, "Adolf Hitler");
           	statement.bindString(3, " 1889-1945, turned Germany into a militarized dictatorship, caused the slaughter of millions and launched World War II.");
           	statement.execute();
           	statement.clearBindings();
           	statement.bindString(2, "AIDS");
           	statement.bindString(3, "Acquired Immune Deficiency Syndrome.");
           	statement.execute();
           	statement.clearBindings();
           	statement.bindString(2, "Airline Food");
           	statement.bindString(3, "Since when is a bag of peanuts considered a meal?");
           	statement.execute();
        	statement.clearBindings();
           	statement.bindString(2, "Airplanes");
           	statement.bindString(3, "I want to sit by the window!");
           	statement.execute();
           	statement.clearBindings();
           	statement.bindString(2, "Al Pacino");
           	statement.bindString(3, "One very intense guy.");
           	statement.execute();
           	statement.clearBindings();
           	statement.bindString(2, "Americans");
           	statement.bindString(3, "How many Americans does it take to screw in a light bulb? THAT'S NOT FUNNY! WE'RE SUING!");
           	statement.execute();
         	statement.clearBindings();
           	statement.bindString(2, "Apples");
           	statement.bindString(3, "More than 40 million metric tons are produced worldwide every year. How 'bout them apples?");
           	statement.execute();
         	statement.clearBindings();
           	statement.bindString(2, "Antartica");
           	statement.bindString(3, "Home to the lowest temperature ever recorded on earth, -126.9 F.");
           	statement.execute();
         	statement.clearBindings();
           	statement.bindString(2, "Babies");
           	statement.bindString(3, "Little bundles of joy... and who needs sleep, anyway?");
           	statement.execute();
           	statement.clearBindings();
           	statement.bindString(2, "Baked Beans");
           	statement.bindString(3, "Add a little bacon, brown sugar and Beano!");
           	statement.execute();
           	statement.clearBindings();
           	statement.bindString(2, "Baking Cookies");
           	statement.bindString(3, "An experience only surpassed by eating them.");
           	statement.execute();
           	statement.clearBindings();
           	statement.bindString(2, "Bats");
           	statement.bindString(3, "The little brown bat consumes as many as 600 mosquitoes in an hour. The vampire bat dines elsewhere");
           	statement.execute();
           	statement.clearBindings();
           	statement.bindString(2, "Beer");
           	statement.bindString(3, "Beer is proof that God loves us and wants us to be happy.-Benjamin Franklin");
           	statement.execute();
           	statement.clearBindings();
           	statement.bindString(2, "Beets");
           	statement.bindString(3, "Beats Brussels sprout.");
           	statement.execute();
           	statement.clearBindings();
           	statement.bindString(2, "Bill Gates");
           	statement.bindString(3, "1955-, chairman and chief software architect of Microsoft Corporation. A very rich guy.");
           	statement.execute();
           	statement.clearBindings();
           	statement.bindString(2, "Cabbage");
           	statement.bindString(3, "From the French word caboche, meaning big head.");
           	statement.execute();
           	statement.clearBindings();
           	statement.bindString(2, "Car Horns");
           	statement.bindString(3, "Blow it, buddy!");
           	statement.execute();
           	statement.clearBindings();
           	statement.bindString(2, "Caves");
           	statement.bindString(3, "Stalactites, stalagmites. Which go up and which go down?");
           	statement.execute();
           	statement.clearBindings();
           	statement.bindString(2, "Chains");
           	statement.bindString(3, "Gold or iron: name your shackles.");
           	statement.execute();
           	statement.clearBindings();
           	statement.bindString(2, "Computer Hackers");
           	statement.bindString(3, "We didn't mean to shut down the entire government...");
           	statement.execute();
           	statement.clearBindings();
           	statement.bindString(2, "Diamonds");
           	statement.bindString(3, "The hard fact: they are just highly refractive crystalline allotropes.");
           	statement.execute();
           	statement.clearBindings();
           	statement.bindString(2, "Desert");
           	statement.bindString(3, "The one you don't want more of!");
           	statement.execute();
           	statement.clearBindings();
           	statement.bindString(2, "Ear Wax");
           	statement.bindString(3, "A waxy, yellowish substance that protects the ear from dust, bacteria, and from hearing things you don't want to hear.");
           	statement.execute();
           	statement.clearBindings();
           	statement.bindString(2, "Europe");
           	statement.bindString(3, "The Old Country ... except to people who live there.");
           	statement.execute();
           	statement.clearBindings();
           	statement.bindString(2, "Feminists");
           	statement.bindString(3, "Political and social activists who support selected women's causes.");
           	statement.execute();
           	statement.clearBindings();
           	statement.bindString(2, "Geisha");
           	statement.bindString(3, "Of course, you would never recognize them without all that make-up.");
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