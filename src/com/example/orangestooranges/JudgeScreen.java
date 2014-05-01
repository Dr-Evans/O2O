package com.example.orangestooranges;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
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
			LayoutParams p = new LayoutParams(
	                android.view.ViewGroup.LayoutParams.MATCH_PARENT,
	                android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
		    p.setMargins(0,20,0,0);
			Button temp;
			ArrayList<Button> butts = new ArrayList<Button>();
			LinearLayout cardsLayout = (LinearLayout) findViewById(R.id.judge);
			for (int i = 0; i < numCards; i++) {
				if(!match.players.get(i).getJudge()) {
					temp = new Button(this);
				    temp.setBackgroundResource(R.drawable.button_menu_orange);
				    temp.setOnClickListener(this);
				    temp.setText(match.getPlayer(i).getOrangePlayed().getCtopic());
				    temp.setId(i);
				    butts.add(temp);
				    //cardsLayout.addView(temp,p);
				}
			}
			Collections.shuffle(butts);
			for(int j = 0; j<butts.size();j++){
				cardsLayout.addView(butts.get(j),p);
			}
		/*
		 * This code should not be needed... setOrangePlayed should already do this, but I'll leave it in case
		 * 
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
		        					if(seconds == 5 ){
		        						tv.setText("...");
		        						tv.setTextColor(Color.RED);
		        					}
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
	        								nextRound = new Intent(JudgeScreen.this, EndScreen.class);
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
		Button temp;
		for(int i = 0; i < match.getNumPlayers(); i++) {
			if(!match.players.get(i).getJudge()) {
				temp = (Button) findViewById(i);
				temp.setEnabled(false);
			}
		}
		int id = v.getId();
		Button b = (Button) findViewById(id);
		b.setBackgroundResource(R.drawable.button_playcard_selected);
		match.players.get(id).updatePoints(match.roundBlue);
	}
	
	@Override
	public void onPause() {
		//Call onPause() of super
		super.onPause();
		
		//Pause the timer
		timerPause = true;
	}
	
	@Override
	public void onResume() {
		//Call onPause() of super
		super.onResume();
		
		//Resume the timer
		timerPause = false;
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
	    .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int which) { 
	        	//Brings the user back to main menu
	        	startActivity(new Intent(JudgeScreen.this, MainActivity.class));
	        	
	        	//Kills the timer - for some reason you HAVE to this as the finish() will not take care of this
	        	t.cancel();
	        	
	        	//Ends pass play activity
	        	JudgeScreen.super.finish();
	        }
	     })
	    .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int which) { 
	            // Resume game
	        	timerPause = false;
	        }
	     })
	    .setIcon(android.R.drawable.ic_dialog_alert)
	     .show();
	}
}
