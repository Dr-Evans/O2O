package com.example.serversocket;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class ServerActivity extends Activity implements DataDisplay {

	private TextView serverMessage;
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        serverMessage=(TextView)findViewById(R.id.textView1);
    }

   //Server handler
   public void connect(View view)
   {
	   //Start server
	    Server server= new Server();
	   	server.setEventListener(this);
	   	server.startNetwork();

   }
   
   //Asyncronous UI text refresher 
   public void Display(String message)
   {
	   serverMessage.setText(""+message);
   }
   
}
