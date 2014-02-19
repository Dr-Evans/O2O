package com.example.orangestooranges;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.*;

public class PlayGameMenu extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.play_game);
		
		/*	Creates objects out of the inputs on the screen
		 *  to be used when starting a game.
		 */
		
		Spinner dropdown = (Spinner)findViewById(R.id.playGameSpinner1);
		String[] items = new String[]{"All", "Random", "Other"};
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
		dropdown.setAdapter(adapter);
		
		Button play = (Button)findViewById(R.id.playGameButton1);
		play.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v)
			{
				startActivity(new Intent(PlayGameMenu.this, PlayCard.class));
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.play_menu, menu);
		return true;
	}

}
