package com.example.orangestooranges;

import library.UserFunctions;
import library.MySQLDatabaseHandler;
import library.JSONParser;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity {
	EditText signinInputEmail;
	EditText signinInputPassword;
	Button signinButton;
	
    EditText createInputUsername;
    EditText createInputPassword;
    EditText createInputEmail;
    EditText createInputConfirmEmail;
    Button createButton;

    TextView loginErrorMsg;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
	    // JSON Response node names
	    final String KEY_SUCCESS = "success";
	    final String KEY_ERROR = "error";
	    final String KEY_ERROR_MSG = "error_msg";
	    final String KEY_UID = "uid";
	    final String KEY_NAME = "name";
	    final String KEY_EMAIL = "email";
	    final String KEY_CREATED_AT = "created_at";
		
	    //Importing all assets
	    signinInputEmail = (EditText)findViewById(R.id.signin_email);
	    signinInputPassword = (EditText)findViewById(R.id.signin_password);
	    signinButton = (Button)findViewById(R.id.login_button);
		
		createInputEmail = (EditText)findViewById(R.id.create_username);
		createInputPassword = (EditText)findViewById(R.id.create_password);
		createInputEmail = (EditText)findViewById(R.id.create_email);
		createInputConfirmEmail =(EditText)findViewById(R.id.create_confirm_email);
		createButton = (Button)findViewById(R.id.create_button);
		
		signinButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				/*
				String email = signinInputEmail.getText().toString();
				String password = signinInputPassword.getText().toString();
				UserFunctions userFunction = new UserFunctions();
				JSONObject json = userFunction.loginUser(email, password);
				
				
				// check for login response
                try {
                    if (json.getString(KEY_SUCCESS) != null) {
                        String res = json.getString(KEY_SUCCESS); 
                        if(Integer.parseInt(res) == 1){
                            // user successfully logged in
                            // Store user details in SQLite Database
                        	MySQLDatabaseHandler db = new MySQLDatabaseHandler(getApplicationContext());
                            JSONObject json_user = json.getJSONObject("user");
                             
                            // Clear all previous data in database
                            userFunction.logoutUser(getApplicationContext());
                            db.addUser(json_user.getString(KEY_NAME), json_user.getString(KEY_EMAIL), json.getString(KEY_UID), json_user.getString(KEY_CREATED_AT));                        
                             
                            // Launch Dashboard Screen
                            Intent mainMenu = new Intent(getApplicationContext(), MainActivity.class);
                             
                            // Close all views before launching Dashboard
                            mainMenu.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(mainMenu);
                             
                            // Close Login Screen
                            finish();
                        }else{
                            // Error in login
                        	System.out.println("Incorrect username/password");
                        	//loginErrorMsg.setText("Incorrect username/password");
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                */
				
			    startActivity(new Intent(getApplicationContext(), MainActivity.class));
			}
		});
	}
	public void authenticate(View view){
		Intent intent = new Intent(this, MainActivity.class);
		System.out.print("AUTHENTICATE");
		startActivity(intent);
	}

}
