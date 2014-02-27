package com.example.orangestooranges;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayCards extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_displaycards);
		Intent playCard = getIntent();
		Match match = (Match) playCard.getParcelableExtra("matchData");
		String username = match.getPlayer(0).getUsername();
		TextView setUsername = (TextView) findViewById(R.id.username);
		setUsername.setText(username);
	}
}
