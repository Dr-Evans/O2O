package com.example.orangestooranges;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class CardCreationTest extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		DatabaseHandler db = new DatabaseHandler(this);
	    //Inserting an orange
		Log.d("Insert: ", "Inserting..."); 
	    db.addCard();
	    Log.d("Reading: ", "Reading card with given ID..."); 
        CardOrange card = db.getOrange(1);
        String log = "Id: "+card.getID()+", Name: " + card.getCtopic() + ", Description: " + card.getCdes();
                // Writing Contacts to log
        Log.d("Name: ", log);
	}
}