package com.example.orangestooranges;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.os.Build;

public class JudgeScreen extends Activity implements OnClickListener {
	
	Timer t = new Timer();
	int seconds = 20;
	public int minutes = 10;//Minutes not used
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_judge_screen);
		Intent playCard = getIntent();
		//No match set up yet so this section of code is commented out
		/*Match match = (Match) playCard.getParcelableExtra("matchData");
		TextView roundBlue = (TextView)findViewById(R.id.roundBlue);
		roundBlue.setText(match.getRoundBlue().getCtopic()+"\n"+match.getRoundBlue().getCdes());
		
		//dynamically print out cards as text views
		//Copied from DisplayCards
		int numCards = match.getNumPlayers();
		Button[] cards = new Button[numCards];
		Button temp; 
		RelativeLayout judge = (RelativeLayout) findViewById(R.id.judge);
		for (int i = 0; i < numCards; i++) {
		    temp = new Button(this);
		    temp.setBackgroundResource(R.drawable.button_menu_orange);
		    temp.setText(match.getPlayer(i).getUsername() + ": " + match.getPlayer(i).getOrangePlayed().getCtopic());
		    judge.addView(temp);
		    temp.setOnClickListener(this);
		    cards[i] = temp;
		}*/
			
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
	        					tv.setText("Time is UP!");
	        					seconds--;
	        				} else if(seconds == 0) {
	        					//Back to main menu
	        					//Select a random card!
	        					Intent nextRound = new Intent(JudgeScreen.this, MainActivity.class);
	        					t.cancel();
	        					t.purge();
	        					startActivity(nextRound);
	        				}
	        			}
	        		});
	        	}
	        }, 0, 1000);
	     
	     //Manually initialize the buttons
	     //Delete this later
	     Button b1 = (Button) findViewById(R.id.O1);
	     Button b2 = (Button) findViewById(R.id.O2);
	     Button b3 = (Button) findViewById(R.id.O3);
	     Button b4 = (Button) findViewById(R.id.O4);
	     Button b5 = (Button) findViewById(R.id.O5);
	     b1.setText("Test1");
	     b2.setText("Test2");
	     b3.setText("Test3");
	     b4.setText("Test4");
	     b5.setText("Test5");
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.judge_screen, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// On Clicking button for selection
		//AKA 'locking in'
		Button b1 = (Button) findViewById(R.id.O1);
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
        		break;
        	case R.id.O2:
        		t.setText("You have picked "+b2.getText());
        		break;
        	case R.id.O3:
        		t.setText("You have picked "+b3.getText());
        		break;
        	case R.id.O4:
        		t.setText("You have picked "+b4.getText());
        		break;
        	case R.id.O5:
        		t.setText("You have picked "+b5.getText());
        		break;
		}	
	}



	

}
