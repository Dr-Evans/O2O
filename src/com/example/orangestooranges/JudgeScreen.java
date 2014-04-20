package com.example.orangestooranges;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class JudgeScreen extends Activity implements OnClickListener {
	
	Timer t = new Timer();
	int seconds = 10;
	public int minutes = 10;//Minutes not used
	Match match;
	boolean timerPause = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_judge_screen);
		Intent playCard = getIntent();
		//No match set up yet so this section of code is commented out
		match = (Match) playCard.getParcelableExtra("matchData");
		TextView roundBlue = (TextView)findViewById(R.id.roundBlue);
		roundBlue.setText(match.getRoundBlue().getCtopic()+"\n"+match.getRoundBlue().getCdes());
				
		//dynamically print out cards as text views
		//Copied from DisplayCards
			int numCards = match.getNumPlayers();
			Button[] cards = new Button[numCards];
			Button temp; 
			LinearLayout cardsLayout = (LinearLayout) findViewById(R.id.judge);
			for (int i = 0; i < numCards; i++) {
				if(!match.players.get(i).getJudge()) {
					temp = new Button(this);
				    temp.setBackgroundResource(R.drawable.button_menu_orange);
				    temp.setOnClickListener(this);
				    temp.setText("Player " + (i+1) + ": " + match.getPlayer(i).getOrangePlayed().getCtopic());
				    cardsLayout.addView(temp);
				    temp.setId(i);
				    cards[i] = temp;
				}
			}
		/*
		for(int i = 0; i < match.getNumPlayers(); i++)
		{
			if(!match.getPlayer(i).getJudge())
			{
				cards[i].setText("Player "+ (i+1) + ": " + match.getPlayer(i).getOrangePlayed().getCtopic());
				CardOrange rem = match.getPlayer(i).getOrangePlayed();
				match.getPlayer(i).removeOrange(rem);
			}
		}*/
		for(int i = 0; i < numCards; i++)
		{
			if(match.inPlay.get(i) != null)
				match.inPlay.set(i, null);
		}
		/*
		/*
		Button temp; 
		RelativeLayout judge = (RelativeLayout) findViewById(R.id.judge);
		for (int i = 0; i < numCards; i++) {
		    temp = new Button(this);
		    temp.setBackgroundResource(R.drawable.button_menu_orange);
		    temp.setText("Player "+ (i+1) + ": " + match.getPlayer(i).getOrangePlayed().getCtopic());
		    judge.addView(temp);
		    temp.setOnClickListener(this);
		    cards[i] = temp;
		}
		*/
			
		//Timer Here
	     t.scheduleAtFixedRate(new TimerTask(){
	        	@Override
	        	public void run(){
	        		runOnUiThread(new Runnable(){
	        			@Override
	        			public void run(){
	        				if (!timerPause){
		        				TextView tv = (TextView) findViewById(R.id.timer);
		        				if(seconds > 5){
		        					tv.setText(String.valueOf(seconds-5));
		        					seconds--;
		        				}
		        				else if(seconds <= 5 && seconds > 0){
		        					tv.setText("Time is UP!");
		        					seconds--;
		        				} else if(seconds == 0) {
		        						        					
	        						for(int i = 0; i < match.getNumPlayers(); i++)
	        						{
	        							if(match.players.get(i).getJudge() && i == 0)
	        							{
	        								match.players.get(i).unmakeJudge();
	        								match.players.get(match.getNumPlayers()-1).makeJudge();
	        								break;
	        								
	        							}
	        							else if(match.players.get(i).getJudge())
	        							{
	        								match.players.get(i).unmakeJudge();
	        								match.players.get(i-1).makeJudge();
	        								break;
	        							}        							
	        						}
	        						Intent nextRound = new Intent();
	        						for(int i = 0; i < match.getNumPlayers(); i++)
	        						{
	        							if(match.players.get(i).getPoints() == match.maxScore)
	        							{
	        								nextRound = new Intent(JudgeScreen.this, PlayGameMenu.class);
	        								break;
	        							}
	        							else
	        								nextRound = new Intent(JudgeScreen.this, SplashScreen.class);
	        						}
	        						
	        						match.setRoundBlue(match.blueStack.pop());
		        					
		        					nextRound.putExtra("matchData", (Parcelable) match);
		        					t.cancel();
		        					t.purge();
		        					startActivity(nextRound);
		        				}
		        			}
	        			}
	        		});
	        	}
	        }, 0, 1000);
	    
	}

	@Override
	public void onClick(View v) {
		// On Clicking button for selection
		//AKA 'locking in'
		/*Button b1 = (Button) findViewById(R.id.O1);
	    Button b2 = (Button) findViewById(R.id.O2);
	    Button b3 = (Button) findViewById(R.id.O3);
	    Button b4 = (Button) findViewById(R.id.O4);
	    Button b5 = (Button) findViewById(R.id.O5);
	    
		b1.setEnabled(false);
		b2.setEnabled(false);
		b3.setEnabled(false);
		b4.setEnabled(false);
		b5.setEnabled(false);
		TextView t = (TextView) findViewById(R.id.choice);
		switch(v.getId()){
        	case R.id.O1:
        		t.setText("You have picked "+b1.getText());
        		if(b1.getText() != "")
        		{
        			match.players.get(0).updatePoints(match.roundBlue);
        			break;
        		}
        	case R.id.O2:
        		t.setText("You have picked "+b2.getText());
        		if(b2.getText() != "")
        		{
        			match.players.get(1).updatePoints(match.roundBlue);
        			break;
        		}
        	case R.id.O3:
        		t.setText("You have picked "+b3.getText());
        		
        		if(b3.getText() != "")
        		{
        			match.players.get(2).updatePoints(match.roundBlue);
        			break;
        		}
        	case R.id.O4:
        		t.setText("You have picked "+b4.getText());
        		if(b4.getText() != "")
        		{
        			match.players.get(3).updatePoints(match.roundBlue);
        			break;
        		}
        	case R.id.O5:
        		t.setText("You have picked "+b5.getText());
        		if(b5.getText() != "")
        		{
        			match.players.get(4).updatePoints(match.roundBlue);
        			break;
        		}
		}*/
		LinearLayout cardsLayout = (LinearLayout) findViewById(R.id.judge);
		cardsLayout.setEnabled(false);
		int id = v.getId();
		Button b = (Button) findViewById(id);
		TextView t = (TextView) findViewById(R.id.choice);
		t.setText("You have picked " + b.getText());
		match.players.get(id).updatePoints(match.roundBlue);
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
	        	startActivity(new Intent(JudgeScreen.this, MainActivity.class));
	        	
	        	//Kills the timer - for some reason you HAVE to this as the finish() will not take care of this
	        	t.cancel();
	        	
	        	//Ends pass play activity
	        	JudgeScreen.super.finish();
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
