package com.example.orangestooranges;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class FriendsList extends Activity implements View.OnTouchListener{

	private float oldXvalue;
	private float oldYvalue;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_friends_list);
		
		Button testButton = (Button) findViewById(R.id.test);
		testButton.setOnTouchListener(this);
	}
	
	@Override
    public boolean onTouch(View v, MotionEvent me) {
        if (me.getAction() == MotionEvent.ACTION_DOWN) {
            oldXvalue = me.getX();
            oldYvalue = me.getY();
            Log.i("Omid", "Action Down " + oldXvalue + "," + oldYvalue);
        } 
        else if (me.getAction() == MotionEvent.ACTION_MOVE) {
            RelativeLayout rl = (RelativeLayout) findViewById(R.id.friends_list_layout);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(v.getWidth(), v.getHeight());
            params.leftMargin = (int) (me.getRawX() - (v.getWidth() / 2));
            params.topMargin = (int) (me.getRawY() - (v.getHeight()));
            //v.getHeight(), (int) (me.getRawX() - (v.getWidth() / 2)), (int) (me.getRawY() - (v.getHeight())));
            v.setLayoutParams(params);
        }
        return true;
    }
}
