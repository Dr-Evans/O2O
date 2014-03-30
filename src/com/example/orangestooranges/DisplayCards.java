package com.example.orangestooranges;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class DisplayCards extends Activity {
	Timer t = new Timer();
	int seconds = 20;
	public int minutes = 10;//Minutes not used
	Match match;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent playCard = getIntent();
		match = (Match) playCard.getParcelableExtra("matchData");
		setContentView(R.layout.activity_displaycards);
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
		//Timer Here
	     t.scheduleAtFixedRate(new TimerTask(){
	        	@Override
	        	public void run(){
	        		runOnUiThread(new Runnable(){
	        			@Override
	        			public void run(){
	        				TextView tv = (TextView) findViewById(R.id.timer);
	        				if(seconds > 5){
	        					tv.setText(String.valueOf(seconds-5));
	        					seconds--;
	        				}
	        				else if(seconds <= 5 && seconds > 0){
	        					tv.setTextColor(Color.GREEN);
	        					tv.setText("...");
	        					seconds--;
	        				} else if(seconds == 0) {
	        					Intent nextRound = new Intent(DisplayCards.this, MainActivity.class);
	        					nextRound.putExtra("matchData", (Parcelable) match);
	        					t.cancel();
	        					t.purge();
	        					startActivity(nextRound);
	        				}
	        			}
	        		});
	        	}
	        }, 0, 1000);
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
