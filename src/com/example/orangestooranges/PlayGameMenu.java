package com.example.orangestooranges;

import java.util.Collections;
import java.util.Stack;

import android.os.Bundle;
import android.os.Parcelable;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.*;

public class PlayGameMenu extends Activity {

	DatabaseHandler db = new DatabaseHandler(this);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.play_game);
		
		/*	Creates objects out of the inputs on the screen
		 *  to be used when starting a game.
		 */
		
		final Spinner dropdown = (Spinner)findViewById(R.id.playGameSpinner1);
		String[] items = new String[]{"4", "5", "6"};
		final Spinner numRounds = (Spinner) findViewById(R.id.numRoundSpinner);
		String[] players = new String[] {"1", "2", "3", "4", "5", "6", "7"};
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
		dropdown.setAdapter(adapter);
		ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, players);
		numRounds.setAdapter(adapter2);
		
		Button play = (Button)findViewById(R.id.playGameButton1);
		play.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v)
			{
				Stack<CardOrange> orangeStack = new Stack<CardOrange>();
				Stack<CardBlue> blueStack = new Stack<CardBlue>();
				for(int i = 1; i <= 746; i++)
				{
					orangeStack.push(db.getOrange(i));
				}
				for(int i = 1; i <= 249; i++)
				{
					blueStack.push(db.getBlue(i));
				}
				
				Collections.shuffle(orangeStack);
				Collections.shuffle(blueStack);
				
				String rounds = (String) numRounds.getSelectedItem();
				int round = Integer.parseInt(rounds);
				String players = (String) dropdown.getSelectedItem();
				int playerCount = Integer.parseInt(players);
				Match match = new Match(playerCount, round ,1, blueStack, orangeStack);
				match.setRoundBlue(blueStack.pop());
				match.players.get(match.getNumPlayers()-1).makeJudge();
				Intent next = new Intent(PlayGameMenu.this, SplashScreen.class);
				next.putExtra("matchData", (Parcelable) match);
				startActivity(next);

			}
		});
	}
	public void toJudge(View v){
		Intent judge = new Intent(PlayGameMenu.this, JudgeScreen.class);
		startActivity(judge);
	}

}
