package com.example.orangestooranges;

import java.util.Collections;
import java.util.Stack;

import android.os.Bundle;
import android.os.Parcelable;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.*;

public class PlayGameMenu extends Activity {

	Match match;
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
				match = new Match(playerCount, round ,1, blueStack, orangeStack);
				match.setRoundBlue(blueStack.pop());
				match.players.get(match.getNumPlayers()-1).makeJudge();
				
				//Name Players
				if(match.gameStart==0){
					AlertDialog.Builder alert = new AlertDialog.Builder(PlayGameMenu.this);
					alert.setTitle("Name Players");
					final LinearLayout layout = new LinearLayout(PlayGameMenu.this);
					layout.setOrientation(LinearLayout.VERTICAL);
					//dynamically add player names
					for(int i = 0; i<match.getNumPlayers();i++){
						EditText playerText = new EditText(PlayGameMenu.this);
						playerText.setHint("Player "+(i+1));
						playerText.setId(i);
						layout.addView(playerText);
					}
					alert.setView(layout);
					alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int whichButton){
							//Pull the names from EditText and set Player Names
							for(int j=0; j<match.getNumPlayers();j++){
								EditText input = (EditText) layout.findViewById(j);
								String name = input.getText().toString();
								if(name.length()==0){
									match.players.get(j).username = "Player " + (j+1);
									continue;
								}
								match.players.get(j).username = name;
							}
							Intent next = new Intent(PlayGameMenu.this, SplashScreen.class);
							next.putExtra("matchData", (Parcelable) match);
							startActivity(next);
						}
					});
					/*alert.setNegativeButton("Defaults", new DialogInterface.OnClickListener(){
						public void onClick(DialogInterface dialog, int which){
							//exit and start next
							for(int k =0; k<match.getNumPlayers();k++){
								match.players.get(k).username = "Player " + (k+1);
							}
							Intent next = new Intent(PlayGameMenu.this, SplashScreen.class);
							next.putExtra("matchData", (Parcelable) match);
							startActivity(next);
						}
					});*/
					alert.setIcon(android.R.drawable.ic_dialog_alert);
					alert.setCancelable(false);
					alert.show();
					match.gameStart = 1;
				}
				

				

			}
		});
	}
	public void toJudge(View v){
		Intent judge = new Intent(PlayGameMenu.this, JudgeScreen.class);
		startActivity(judge);
	}
	

}
