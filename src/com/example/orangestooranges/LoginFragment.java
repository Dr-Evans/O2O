package com.example.orangestooranges;

import library.MySQLDatabaseHandler;
import library.UserFunctions;

import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.widget.LoginButton;

public class LoginFragment extends Fragment {
	private static final String TAG = "LoginFragment";
	private UiLifecycleHelper uiHelper;
	Button btnLogin;
	Button btnLinkToRegister;
	EditText inputEmail;
	EditText inputPassword;
	TextView loginErrorMsg;
	// JSON Response node names
		private static String KEY_SUCCESS = "success";
		//private static String KEY_ERROR = "error";
		//private static String KEY_ERROR_MSG = "error_msg";
		private static String KEY_UID = "uid";
		private static String KEY_NAME = "name";
		private static String KEY_EMAIL = "email";
		private static String KEY_CREATED_AT = "created_at";


	
	@SuppressLint("NewApi")
	@Override
	public View onCreateView(LayoutInflater inflater, 
	        ViewGroup container, 
	        Bundle savedInstanceState) {
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

		StrictMode.setThreadPolicy(policy); 
	    View view = inflater.inflate(R.layout.login_combined, container, false);
	    LoginButton authButton = (LoginButton) view.findViewById(R.id.authButton);
	    authButton.setFragment(this);
	    inputEmail = (EditText) view.findViewById(R.id.loginEmail);
 		inputPassword = (EditText) view.findViewById(R.id.loginPassword);
 		btnLogin = (Button) view.findViewById(R.id.btnLogin);
 		btnLinkToRegister = (Button) view.findViewById(R.id.btnLinkToRegisterScreen);
 		loginErrorMsg = (TextView) view.findViewById(R.id.login_error);
	    btnLogin.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				String email = inputEmail.getText().toString();
				String password = inputPassword.getText().toString();
				UserFunctions userFunction = new UserFunctions();
				Log.d("Button", "Login");
				JSONObject json = userFunction.loginUser(email, password);

				// check for login response
				try {
					if (json.getString(KEY_SUCCESS) != null) {
						loginErrorMsg.setText("");
						String res = json.getString(KEY_SUCCESS); 
						if(Integer.parseInt(res) == 1){
							// user successfully logged in
							// Store user details in SQLite Database
							MySQLDatabaseHandler db = new MySQLDatabaseHandler(getActivity());
							JSONObject json_user = json.getJSONObject("user");
							
							// Clear all previous data in database
							userFunction.logoutUser(getActivity());
							db.addUser(json_user.getString(KEY_NAME), json_user.getString(KEY_EMAIL), json.getString(KEY_UID), json_user.getString(KEY_CREATED_AT));						
							
							// Launch Dashboard Screen
							Intent main = new Intent(getActivity(), MainActivity.class);
							
							// Close all views before launching Dashboard
							main.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
							startActivity(main);
							
							// Close Login Screen
							
						}else{
							// Error in login
							loginErrorMsg.setText("Incorrect username/password");
						}
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
	    btnLinkToRegister.setOnClickListener(new View.OnClickListener() {

		public void onClick(View view) {
				Intent i = new Intent(getActivity(),
						RegisterActivity.class);
				startActivity(i);
			}
		});


	    return view;
	}
	
	private void onSessionStateChange(Session session, SessionState state, Exception exception) {
	    if (state.isOpened()) {
	        Log.i(TAG, "Logged in...");
	        Intent main = new Intent(getActivity(), MainActivity.class);
        	startActivity(main);
	    } else if (state.isClosed()) {
	        Log.i(TAG, "Logged out...");
	    }
	}
	
	private Session.StatusCallback callback = new Session.StatusCallback() {
	    @Override
	    public void call(Session session, SessionState state, Exception exception) {
	        onSessionStateChange(session, state, exception);
	    }
	};
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    uiHelper = new UiLifecycleHelper(getActivity(), callback);
	    uiHelper.onCreate(savedInstanceState);
	    
	}
	
	@Override
	public void onResume() {
	    super.onResume();
	    // For scenarios where the main activity is launched and user
	    // session is not null, the session state change notification
	    // may not be triggered. Trigger it if it's open/closed.
	    Session session = Session.getActiveSession();
	    if (session != null &&
	           (session.isOpened() || session.isClosed()) ) {
	        onSessionStateChange(session, session.getState(), null);
	    }
	    uiHelper.onResume();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	    super.onActivityResult(requestCode, resultCode, data);
	    uiHelper.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onPause() {
	    super.onPause();
	    uiHelper.onPause();
	}

	@Override
	public void onDestroy() {
	    super.onDestroy();
	    uiHelper.onDestroy();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
	    super.onSaveInstanceState(outState);
	    uiHelper.onSaveInstanceState(outState);
	}
}
