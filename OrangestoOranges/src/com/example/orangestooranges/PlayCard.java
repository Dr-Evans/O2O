package com.example.orangestooranges;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.graphics.Color;
import android.graphics.PorterDuff;

import java.util.Timer;
import java.util.TimerTask;

public class PlayCard extends Activity {
	DatabaseHandler db = new DatabaseHandler(this);
	Match newMatch = new Match(1, 2, 1);
	int cardPreviewing = -1;
	int playerIndex = 0; //this will be set by server for user
	Player newPlayer = new Player(playerIndex, "SampleUser", 0, false);
	Timer t = new Timer();
	int seconds = 25;
	public int minutes = 10;//Minutes not used
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_playcard);
		newMatch.addPlayer(newPlayer);
		newMatch.setRoundBlue(db.getBlue(1));
		
		TextView roundBlue = (TextView)findViewById(R.id.roundBlue);
		roundBlue.setText(newMatch.getRoundBlue().getCtopic()+"\n"+newMatch.getRoundBlue().getCdes());
		for(int i = 0; i < 7; i++) {
			//this will need to be swapped for how players actually receive cards from server
			newMatch.getPlayer(playerIndex).addOrange(db.getOrange(i+1));
			String buttonID = "card" + (i+1);
			int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
			Button cardButton = (Button)findViewById(resID);
			cardButton.setText(newMatch.getPlayer(playerIndex).getOrange(i).getCtopic());
			cardButton.setTag((Integer)i); //set the cards index in the view so it can be referenced when clicked
		}
		
		//Timer Here
	     t.scheduleAtFixedRate(new TimerTask(){
	        	@Override
	        	public void run(){
	        		runOnUiThread(new Runnable(){
	        			@Override
	        			public void run(){
	        				TextView tv = (TextView) findViewById(R.id.timer);
	        				if(seconds > 20){//Provide buffer period
	        					tv.setText("Starting...");
	        					seconds = seconds-1;
	        				}
	        				else if(seconds > 5){
	        					tv.setText(String.valueOf(seconds-5));
	        					seconds = seconds - 1;
	        				}
	        				else if(seconds <= 5 && seconds > 0){
	        					tv.setText("TIME IS UP");
	        					if(seconds == 5) {
	        						for(int i = 0; i < 7; i++) {
		        						String buttonID = "card" + (i+1);
		        						int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
		        						Button cardButton = (Button)findViewById(resID);
		        						cardButton.setEnabled(false);
	        						}
	        						if(newMatch.getPlayer(playerIndex).getOrangePlayed() == null && cardPreviewing != -1) {
	        							newMatch.getPlayer(playerIndex).setOrangePlayed(cardPreviewing);
	        							newMatch.setInPlay(newMatch.getPlayer(playerIndex).getOrange(cardPreviewing), playerIndex);
	        						} else if(newMatch.getPlayer(playerIndex).getOrangePlayed() == null && cardPreviewing == -1) {
	        							newMatch.setInPlay(newMatch.getPlayer(playerIndex).selectRand(), playerIndex);
	        						}
	        					} 
	        					Button lockButton = (Button)findViewById(R.id.lockCard);
	        					lockButton.setEnabled(false);
	        					seconds = seconds - 1;
	        				} else if(seconds == 0) {
	        					//should move to display cards
	        					Intent displayCards = new Intent(PlayCard.this, DisplayCards.class);
	        					displayCards.putExtra("matchData", (Parcelable) newMatch);
	        					t.cancel();
	        					t.purge();
	        					startActivity(displayCards);
	        				}
	        			}
	        		});
	        	}
	        }, 0, 1000);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.playcard, menu);
		return true;
	}
	
	public void cardClicked(View v) {
		//onClick is set within XML to prevent a bunch of onClick listeners
		if(cardPreviewing != -1) {
			String buttonID = "card" + (cardPreviewing+1);
			int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
			Button cardButton = (Button)findViewById(resID);
			cardButton.setBackgroundResource(R.drawable.button_playcard_default);
		}
		cardPreviewing = (Integer) v.getTag();
		String cardInfo = newMatch.getPlayer(playerIndex).getOrange(cardPreviewing).getCtopic() + "\n"+newMatch.getPlayer(playerIndex).getOrange(cardPreviewing).getCdes();
		TextView tv = (TextView)findViewById(R.id.cardPreview);
		tv.setText(cardInfo);
		Button lockButton = (Button)findViewById(R.id.lockCard);
		lockButton.getBackground().setColorFilter(Color.RED,PorterDuff.Mode.SRC_ATOP);
		lockButton.setVisibility(View.VISIBLE);
		v.setBackgroundResource(R.drawable.button_playcard_selected);
	}
	
	public void lockCard(View v) {
		newMatch.getPlayer(playerIndex).setOrangePlayed(cardPreviewing);
		newMatch.setInPlay(newMatch.getPlayer(playerIndex).getOrange(cardPreviewing), playerIndex); 
		for(int i = 0; i < 7; i++) {
			String buttonID = "card" + (i+1);
			int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
			Button cardButton = (Button)findViewById(resID);
			cardButton.setEnabled(false);
		}
		v.setEnabled(false);
	}

}