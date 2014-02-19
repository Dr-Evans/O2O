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
	Player newPlayer = new Player();
	DatabaseHandler db = new DatabaseHandler(this);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_playcard);
		
		for(int i = 0; i < 7; i++) {
			newPlayer.addOrange(db.getOrange(i+1));
			String buttonID = "card" + (i+1);
			int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
			Button cardButton = (Button)findViewById(resID);
			cardButton.getBackground().setColorFilter(Color.parseColor("#FF8B00"),PorterDuff.Mode.SRC_ATOP);
			cardButton.setText(newPlayer.getOrange(i).getCtopic());
			cardButton.setTag((Integer)i);
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.playcard, menu);
		return true;
	}
	
	public void cardClicked(View v) {
		int index = (Integer) v.getTag();
		String cardInfo = newPlayer.getOrange(index).getCtopic() + "\n"+newPlayer.getOrange(index).getCdes();
		TextView tv = (TextView)findViewById(R.id.cardPreview);
		tv.setText(cardInfo);
	}

}