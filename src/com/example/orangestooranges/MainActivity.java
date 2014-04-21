package com.example.orangestooranges;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;

import library.UserFunctions;
import android.app.Activity;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ImageButton;

public class MainActivity extends Activity implements OnClickListener {
	UserFunctions userFunctions;
	Button btnLogout;
	Session session = Session.getActiveSession();
	TextView username; 
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		userFunctions = new UserFunctions();
        if((userFunctions.isUserLoggedIn(getApplicationContext())) || (session != null && session.isOpened())){
        	setContentView(R.layout.activity_main);
        	btnLogout = (Button) findViewById(R.id.btnLogout);
        	if(userFunctions.isUserLoggedIn(getApplicationContext())) {
        		LoginButton loginbutton = (LoginButton) findViewById(R.id.authButton);
        		loginbutton.setVisibility(View.INVISIBLE);
            	btnLogout.setOnClickListener(new View.OnClickListener() {
        			public void onClick(View arg0) {
        				// TODO Auto-generated method stub
        				userFunctions.logoutUser(getApplicationContext());
        				
        				Intent login = new Intent(getApplicationContext(), LoginActivity.class);
        	        	login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        	        	startActivity(login);
        	        	// Closing dashboard screen
        	        	finish();
        			}
        		});
        	} else {
        		btnLogout.setVisibility(View.INVISIBLE);
        		username = (TextView) findViewById(R.id.username);
        		makeMeRequest(session);
        		LoginButton loginbutton = (LoginButton) findViewById(R.id.authButton);
        		loginbutton.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						session.closeAndClearTokenInformation();
						Intent intent = new Intent(getApplicationContext(), LoginActivity.class);	
						startActivity(intent);
					}
				});
        	}
        	
        	Button mainPlay = (Button)findViewById(R.id.mainPlayButton);
    		mainPlay.setOnClickListener(this);
    		Button mainSettings = (Button)findViewById(R.id.mainSettingsButton);
    		mainSettings.setOnClickListener(this);
    		Button mainFriends = (Button)findViewById(R.id.mainFriendsButton);
    		mainFriends.setOnClickListener(this);
    		Button mainCreate = (Button)findViewById(R.id.mainPassPlayButton);
    		mainCreate.setOnClickListener(this);
    		/*Button mainStats = (Button)findViewById(R.id.mainStatsButton);
    		mainStats.setOnClickListener(this);*/
    		ImageButton mainInstructions = (ImageButton)findViewById(R.id.imageButtonInstructions);
    		mainInstructions.setOnClickListener(this);		
        	
        }
        else{
        	// user is not logged in show login screen
        	Intent login = new Intent(getApplicationContext(), LoginActivity.class);
        	login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        	startActivity(login);
        	// Closing dashboard screen
        	finish();
        }
    
		
		/* Carson: added this to filter the flow into my new screen
		 * (play game menu).
		 *
		 * This code can be used as standard template for switching views
		 */
		
	}
	
	public void onClick(View v) {
		// Steve:
	    // to change screen with specific button remove Toast.makeText() and add startActivity()
		switch (v.getId()) {
	        case R.id.mainPlayButton: 
	        	startActivity(new Intent(MainActivity.this, PlayGameMenu.class));
	        	break;
	        case R.id.mainSettingsButton:
	        	startActivity(new Intent(MainActivity.this, SettingActivity.class));
	        	break;
	        case R.id.mainFriendsButton:
	        	Toast.makeText(MainActivity.this, "You Click Friends List", Toast.LENGTH_SHORT).show();
	            break;
	        case R.id.mainPassPlayButton:
	        	startActivity(new Intent(MainActivity.this, PlayGameMenu.class));
	            break;
	        /*case R.id.mainStatsButton:
	        	Toast.makeText(MainActivity.this, "You Click Stats", Toast.LENGTH_SHORT).show();
	            break;*/
	        case R.id.imageButtonInstructions:
	        	startActivity(new Intent(MainActivity.this,Instructions.class));
	            break;
		}
	}
	private void makeMeRequest(final Session session) {
	    // Make an API call to get user data and define a 
	    // new callback to handle the response.
	    Request request = Request.newMeRequest(session, 
	            new Request.GraphUserCallback() {
	        @Override
	        public void onCompleted(GraphUser user, Response response) {
	            // If the response is successful
	            if (session == Session.getActiveSession()) {
	                if (user != null) {
	                    // Set the id for the ProfilePictureView
	                    // view that in turn displays the profile picture.
	                    // Set the Textview's text to the user's name.
	                   username.setText(user.getName());
	                }
	            }
	            if (response.getError() != null) {
	                // Handle errors, will do so later.
	            }
	        }

		
	    });
	    request.executeAsync();
	} 
}
