package com.example.orangestooranges;

import android.app.Activity;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		/* Carson: added this to filter the flow into my new screen
		 * (play game menu).
		 *
		 * This code can be used as standard template for switching views
		 */
		Button mainPlay = (Button)findViewById(R.id.mainPlayButton);
		mainPlay.setOnClickListener(this);
		Button mainSettings = (Button)findViewById(R.id.mainSettingsButton);
		mainSettings.setOnClickListener(this);
		Button mainFriends = (Button)findViewById(R.id.mainFriendsButton);
		mainFriends.setOnClickListener(this);
		Button mainCreate = (Button)findViewById(R.id.mainCreateButton);
		mainCreate.setOnClickListener(this);
		Button mainStats = (Button)findViewById(R.id.mainStatsButton);
		mainStats.setOnClickListener(this);
	}
	
	public void onClick(View v) {
		// Steve:
	    // to change screen with specific button remove Toast.makeText() and add startActivity()
		switch (v.getId()) {
	        case R.id.mainPlayButton: 
	        	startActivity(new Intent(MainActivity.this, PlayGameMenu.class));
	        	break;
	        case R.id.mainSettingsButton:
	        	Toast.makeText(MainActivity.this, "You Click Account Settings", Toast.LENGTH_SHORT).show();
	        	break;
	        case R.id.mainFriendsButton:
	        	Toast.makeText(MainActivity.this, "You Click Friends List", Toast.LENGTH_SHORT).show();
	            break;
	        case R.id.mainCreateButton:
	        	Toast.makeText(MainActivity.this, "You Click Create Game", Toast.LENGTH_SHORT).show();
	            break;
	        case R.id.mainStatsButton:
	        	Toast.makeText(MainActivity.this, "You Click Stats", Toast.LENGTH_SHORT).show();
	            break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
