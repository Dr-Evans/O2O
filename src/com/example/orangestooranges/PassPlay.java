package com.example.orangestooranges;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PassPlay extends Activity {
	
	Timer t = new Timer();
	int seconds = 10;
	int cardPreviewing = -1;
	Match match;
	Player currPlayer;
	int playerIndex;
	boolean timerPause = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pass_play);
		
		Intent matchInfo = getIntent();
		match = (Match) matchInfo.getParcelableExtra("matchData");
		
		TextView roundBlue = (TextView)findViewById(R.id.roundBlue);
		roundBlue.setText(match.getRoundBlue().getCtopic()+"\n"+match.getRoundBlue().getCdes());
		
		String s = "";
		for(int i= 0; i < match.getNumPlayers(); i++)
		{
			if((match.inPlay.get(i) == null) && (!match.players.get(i).getJudge()))
			{
				match.dealOranges(match.players.get(i));
				currPlayer = match.players.get(i);
				playerIndex = i;
				s = "Player " + (i+1) + "'s turn.";
				break;
			}
		}
		
		for(int j = 0; j < 7; j++)
		{
			String buttonID = "card" + (j+1);
			int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
			Button cardButton = (Button)findViewById(resID);
			cardButton.setText(currPlayer.getOrange(j).getCtopic());
			cardButton.setTag((Integer)j);
		}
		
		TextView player = (TextView)findViewById(R.id.passPlayPlayer);
		player.setText(s);
		
		t.scheduleAtFixedRate(new TimerTask(){
        	@Override
        	public void run(){
        		runOnUiThread(new Runnable(){
        			@Override
        			public void run(){
        				if (!timerPause){
	        				TextView tv = (TextView) findViewById(R.id.timer);
	        				if(seconds > 20){//Provide buffer period
	        					tv.setTextColor(Color.GREEN);
	        					tv.setText("...");
	        					seconds = seconds-1;
	        				}
	        				else if(seconds > 5){
	        					if(seconds == 20) {
	        						tv.setTextColor(Color.BLACK);
	        					}
	        					tv.setText(String.valueOf(seconds-5));
	        					seconds = seconds - 1;
	        					if(seconds == 9) {
	        						tv.setTextColor(Color.RED);
	        					}
	        				}
	        				else if(seconds <= 5 && seconds > 0){
	        					tv.setText("...");
	        					if(seconds == 5) {
	        						for(int i = 0; i < 7; i++) {
		        						String buttonID = "card" + (i+1);
		        						int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
		        						Button cardButton = (Button)findViewById(resID);
		        						cardButton.setEnabled(false);
	        						}
	        						if(currPlayer.getOrangePlayed() == null && cardPreviewing != -1) {
	        							currPlayer.setOrangePlayed(cardPreviewing);
	        							match.setInPlay(currPlayer.getOrange(cardPreviewing), playerIndex);
	        						} else if(currPlayer.getOrangePlayed() == null && cardPreviewing == -1) {
	        							match.setInPlay(currPlayer.selectRand(), playerIndex);
	        						}
	        					} 
	        					Button lockButton = (Button)findViewById(R.id.lockCard);
	        					lockButton.setEnabled(false);
	        					seconds = seconds - 1;
	        				} else if(seconds == 0) {
	        					//should move to display cards
	        					Intent next = new Intent(PassPlay.this, SplashScreen.class);
	        					next.putExtra("matchData", (Parcelable) match);
	        					t.cancel();
	        					t.purge();
	        					startActivity(next);
	        				}
        				}
        			}
        		});
        	}
        }, 0, 1000);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.pass_play, menu);
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
		String cardInfo = currPlayer.getOrange(cardPreviewing).getCtopic() + "\n"+currPlayer.getOrange(cardPreviewing).getCdes();
		TextView tv = (TextView)findViewById(R.id.cardPreview);
		tv.setText(cardInfo);
		Button lockButton = (Button)findViewById(R.id.lockCard);
		lockButton.getBackground().setColorFilter(Color.RED,PorterDuff.Mode.SRC_ATOP);
		lockButton.setVisibility(View.VISIBLE);
		v.setBackgroundResource(R.drawable.button_playcard_selected);
	}
	
	public void lockCard(View v) {

		match.setInPlay(currPlayer.getOrange(cardPreviewing), playerIndex); 
		currPlayer.setOrangePlayed(cardPreviewing);
		for(int i = 0; i < 7; i++) {
			String buttonID = "card" + (i+1);
			int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
			Button cardButton = (Button)findViewById(resID);
			cardButton.setEnabled(false);
		}
		v.setEnabled(false);
	}
	
	@Override
	public void onBackPressed() {
		//This will override the back button
		//Prompts the user if they want to end the game
		
		//Pause the timer
		timerPause = true;
	
		new AlertDialog.Builder(this)
	    .setTitle("End Game")
	    .setMessage("Are you sure you want to end the game?")
	    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int which) { 
	        	//Brings the user back to main menu
	        	startActivity(new Intent(PassPlay.this, MainActivity.class));
	        	
	        	//Kills the timer - for some reason you HAVE to this as the finish() will not take care of this
	        	t.cancel();
	        	
	        	//Ends pass play activity
	        	PassPlay.super.finish();
	        }
	     })
	    .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int which) { 
	            // Resume game
	        	timerPause = false;
	        }
	     })
	    .setIcon(android.R.drawable.ic_dialog_alert)
	     .show();
	}

}
