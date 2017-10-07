package com.recoded.motico;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

public class PlayerActivity extends ApplicationSubActivity { //extending a frame activity to use back button on all non main activities
    private SeekBar seeker;
    private Button prev, next, play_pause;
    private MediaPlayer player;
    private String audioFile;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        seeker = (SeekBar) findViewById(R.id.seekBar);
        prev = (Button) findViewById(R.id.button_prev);
        next = (Button) findViewById(R.id.button_next);
        play_pause = (Button) findViewById(R.id.button_play_pause);

        //Initialize the player
        player = new MediaPlayer();
        audioFile = "android.resource://" + getPackageName() + "/" + R.raw.hooray; // In a complete application we should parse file name using intent extras
        try {
            player.setDataSource(getApplicationContext(), Uri.parse(audioFile));
            player.prepare();
            player.start();
            seeker.setMax(player.getDuration());
            play_pause.setText(R.string.pause);
        } catch (Exception e) {
            e.printStackTrace();
        }

        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                play_pause.setText(R.string.play);
            }
        });

        //Implement seekbar function
        seeker.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    player.seekTo(progress);
                    if (!player.isPlaying()) {
                        playPause();
                    }
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //Update seekbar with audio on UI thread
        mHandler = new Handler();
        PlayerActivity.this.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                if (player != null) {
                    int mCurrentPosition = player.getCurrentPosition();
                    seeker.setProgress(mCurrentPosition);
                }
                mHandler.postDelayed(this, 1000);
            }
        });

        //Implementing the play button function
        play_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playPause();
            }
        });
    }

    //A method to update button and play pause
    public void playPause(){
        if (player.isPlaying()) {
            player.pause();
            play_pause.setText(R.string.play);
        } else {
            player.start();
            play_pause.setText(R.string.pause);
        }
    }
}
