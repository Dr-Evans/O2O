package com.example.orangestooranges;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SplashScreen extends Activity {
	Match match;
	boolean judgeTime;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);
		
		Intent matchInfo = getIntent();
		match = (Match) matchInfo.getParcelableExtra("matchData");
		
		TextView gameInfo = (TextView)findViewById(R.id.splashTextView1);
		String gi = "";
		gi += "The game will go first to " + match.maxScore + " points.\n";
		for(int i = 0; i < match.getNumPlayers(); i++)
		{
			gi += "Player " + (i+1) + " has " + match.players.get(i).getPoints() + " points.\n";
		}
		gi += match.players.get(0).getPoints() == match.maxScore;
		gameInfo.setText(gi);
		
		
		TextView text = (TextView)findViewById(R.id.splashTextView);
		String s = "";
		Player judge = new Player();
		int judgeIndex = -1;
		judgeTime = false;
		for(int i = 0; i < match.getNumPlayers(); i++)
		{
			if(match.inPlay.get(i) == null)
			{
				if(match.players.get(i).getJudge())
				{
					judge = match.players.get(i);
					judgeIndex = i;
				}
				else
				{
					s = "Player " + (i+1) + "'s turn.";
					break;
				}
			}
			
		}
		if(s.equals(""))
		{
			s = "Player " + (judgeIndex+1) + "'s turn. Time to judge!";
			judgeTime = true;
		}
		text.setText(s);
		
		Button play = (Button)findViewById(R.id.splashButton);
		play.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v)
			{
				Intent passPlay;
				if(!judgeTime)
					passPlay = new Intent(SplashScreen.this, PassPlay.class);
				else
					passPlay = new Intent(SplashScreen.this, JudgeScreen.class);
				passPlay.putExtra("matchData", (Parcelable) match);
				startActivity(passPlay);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splash_screen, menu);
		return true;
	}
	
	@Override
	public void onBackPressed() {
		//This will override the back button
		//Prompts the user if they want to end the game

		new AlertDialog.Builder(this)
	    .setTitle("End Game")
	    .setMessage("Are you sure you want to end the game?")
	    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int which) { 
	        	//Brings the user back to main menu
	        	startActivity(new Intent(SplashScreen.this, MainActivity.class));
	        	
	        	//Ends pass splash screen activity
	        	SplashScreen.super.finish();
	        }
	     })
	    .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int which) { 
	            // Resume game
	        }
	     })
	    .setIcon(android.R.drawable.ic_dialog_alert)
	     .show();
	}

}
