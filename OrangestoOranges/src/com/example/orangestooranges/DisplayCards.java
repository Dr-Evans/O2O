package com.example.orangestooranges;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayCards extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_displaycards);
		Intent playCard = getIntent();
		Match match = (Match) playCard.getParcelableExtra("matchData");
		TextView roundBlue = (TextView)findViewById(R.id.roundBlue);
		roundBlue.setText(match.getRoundBlue().getCtopic()+"\n"+match.getRoundBlue().getCdes());
		
		//dynamically print out cards as text views
		int numCards = match.getNumPlayers();
		Button[] cards = new Button[numCards];
		Button temp; 
		LinearLayout cardsLayout = (LinearLayout) findViewById(R.id.cardsLayout);
		for (int i = 0; i < numCards; i++) {
		    temp = new Button(this);
		    temp.setBackgroundResource(R.drawable.button_menu_orange);
		    temp.setText(match.getPlayer(i).getUsername() + ": " + match.getPlayer(i).getOrangePlayed().getCtopic());
		    cardsLayout.addView(temp);
		    cards[i] = temp;
		}
	}
	
	@Override
	public void onBackPressed() {
		Context context = getApplicationContext();
		CharSequence text = "You are in the middle of a game!";
		int duration = Toast.LENGTH_SHORT;

		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
	}
}
