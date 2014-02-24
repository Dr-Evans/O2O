package com.example.orangestooranges;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.graphics.Color;
import android.graphics.PorterDuff;

public class PlayCard extends Activity {
	DatabaseHandler db = new DatabaseHandler(this);
	Match newMatch = new Match(1, 2, 1);
	int cardPreviewing = 0;
	int playerIndex = 0; //this will be set by server for user
	Player newPlayer = new Player(playerIndex, "SampleUser", 0, false);
	
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
			//SRC_ATOP distorts the color less than MULTILPY but neither show hex colors correctly
			cardButton.getBackground().setColorFilter(Color.parseColor("#FF8B00"),PorterDuff.Mode.SRC_ATOP);
			cardButton.setText(newMatch.getPlayer(playerIndex).getOrange(i).getCtopic());
			cardButton.setTag((Integer)i); //set the cards index in the view so it can be referenced when clicked
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.playcard, menu);
		return true;
	}
	
	public void cardClicked(View v) {
		//onClick is set within XML to prevent a bunch of onClick listeners
		cardPreviewing = (Integer) v.getTag();
		String cardInfo = newMatch.getPlayer(playerIndex).getOrange(cardPreviewing).getCtopic() + "\n"+newMatch.getPlayer(playerIndex).getOrange(cardPreviewing).getCdes();
		TextView tv = (TextView)findViewById(R.id.cardPreview);
		tv.setText(cardInfo);
		Button lockButton = (Button)findViewById(R.id.lockCard);
		lockButton.getBackground().setColorFilter(Color.RED,PorterDuff.Mode.SRC_ATOP);
		lockButton.setVisibility(View.VISIBLE);
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