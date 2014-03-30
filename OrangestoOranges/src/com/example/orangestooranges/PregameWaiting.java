package com.example.orangestooranges;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.*;

public class PregameWaiting extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pregame_waiting);
		
		
		/* Create a FIFO queue that takes in players who have started a game.
		 * Game starts when there are enough players.
		 * this startActivity line will be the last in the algorithm
		 */
		
//		int numCurrentPlayers=0;
//		Match m = new Match(4,4,1);
//		while(m.getNumPlayers() > numCurrentPlayers)
//		{
//			//grab new player
//		}
		
		startActivity(new Intent(PregameWaiting.this, PlayCard.class));

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.play_menu, menu);
		return true;
	}

}