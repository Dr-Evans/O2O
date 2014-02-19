package com.example.orangestooranges;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		/* Carson: added this to filter the flow into my new screen
		 * (play game menu).
		 *
		 * This code can be used as standard template for switching views
		 */
		Button mainPlay = (Button)findViewById(R.id.mainPlayButton);
		mainPlay.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
			    startActivity(new Intent(MainActivity.this, PlayGameMenu.class));
			   
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
