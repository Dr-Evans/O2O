package com.example.orangestooranges;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class PlayOnline extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_play_online);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.play_online, menu);
		return true;
	}

}