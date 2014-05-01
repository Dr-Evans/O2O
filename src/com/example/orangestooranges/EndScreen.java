package com.example.orangestooranges;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

public class EndScreen extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_end_screen);
		Intent playCard = getIntent();
		Match results = playCard.getParcelableExtra("matchData");
		int numPlayers = results.getNumPlayers();
		LayoutParams p = new LayoutParams(
                android.view.ViewGroup.LayoutParams.MATCH_PARENT,
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
	    p.setMargins(50,20,50,0);
		TextView temp; 
		LinearLayout scoreBoard = (LinearLayout) findViewById(R.id.scores);
		//ArrayList<TextView> scores = new ArrayList<TextView>();
		for (int i = 0; i < numPlayers; i++) {
				temp = new TextView(this);
			    temp.setText(results.players.get(i).username +"'s Score: "+ results.getPlayer(i).getPoints());
			    //Set text view needs to be set to the username of the player in the future
			    temp.setId(i);
			    if(results.getPlayer(i).getPoints()==results.maxScore){
			    	temp.setTextSize(35);
			    	temp.setTextColor(Color.GREEN);
			    }
			    else{
				    temp.setTextSize(20);
			    }
			    //scores.add(temp);
			    scoreBoard.addView(temp,p);
		}
		
		
	}
	
	public void replay(View v){
		Intent startNew = new Intent(EndScreen.this, PlayGameMenu.class);
		startActivity(startNew);
	}
	
	public void mainMenu(View v){
		Intent menu = new Intent(EndScreen.this, MainActivity.class);
		startActivity(menu);
	}
	
	@Override
	public void onBackPressed(){
		//Do nothing
	}
	
}
