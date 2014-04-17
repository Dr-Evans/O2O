package com.example.orangestooranges;

import android.media.AudioManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class SettingActivity extends Activity{

	 private SeekBar volumeSeekbar = null;
	 private AudioManager audioManager = null; 
	    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		setVolumeControlStream(AudioManager.STREAM_MUSIC);
        initControls();
	}
	
	private void initControls()
    {
        try
        {
            volumeSeekbar = (SeekBar)findViewById(R.id.seekBar2);
            audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            volumeSeekbar.setMax(audioManager
                    .getStreamMaxVolume(AudioManager.STREAM_MUSIC));
            volumeSeekbar.setProgress(audioManager
                    .getStreamVolume(AudioManager.STREAM_MUSIC));   


            volumeSeekbar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() 
            {
                @Override
                public void onStopTrackingTouch(SeekBar arg0) 
                {
                }

                @Override
                public void onStartTrackingTouch(SeekBar arg0) 
                {
                }

                @Override
                public void onProgressChanged(SeekBar arg0, int progress, boolean arg2) 
                {
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
                            progress, 0);
                }
            });
        }
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
}
