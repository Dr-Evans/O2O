package com.example.orangestooranges;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class EndScreen extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_end_screen);
		
		
		//sends back to the play game screen
		Button playAgain = (Button)findViewById(R.id.playAgainButton);
		playAgain.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
			    startActivity(new Intent(EndScreen.this, PlayGameMenu.class));
			   
			}
		});
		
		//sends back to the main menu screen
		Button mainMenuButton = (Button)findViewById(R.id.mainMenuButton);
		mainMenuButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
			    startActivity(new Intent(EndScreen.this, MainActivity.class));
			   
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.end_screen, menu);
		return true;
	}

}
