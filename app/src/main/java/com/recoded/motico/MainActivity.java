package com.recoded.motico;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inflater  = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout popContainer = (LinearLayout) findViewById(R.id.pop_albums_container);
        LinearLayout rockContainer = (LinearLayout) findViewById(R.id.rock_albums_container);
        LinearLayout jazzContainer = (LinearLayout) findViewById(R.id.jazz_albums_container);
        for(int i=0; i<5; i++){
            View album = inflater.inflate(R.layout.album_card_template, popContainer);
            album.setOnClickListener(albumsClickListener);
            album.setTag("Pop");
            View album2 = inflater.inflate(R.layout.album_card_template, rockContainer);
            album2.setOnClickListener(albumsClickListener);
            album2.setTag("Rock");
            View album3 = inflater.inflate(R.layout.album_card_template, jazzContainer);
            album3.setOnClickListener(albumsClickListener);
            album3.setTag("Jazz");
        }

        findViewById(R.id.now_playing_bar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View playListContaner = findViewById(R.id.play_list_container_sv);
                if(playListContaner.getVisibility() == View.GONE){
                    playListContaner.setVisibility(View.VISIBLE);
                    view.setBackgroundColor(Color.GRAY);
                } else {
                    playListContaner.setVisibility(View.GONE);
                    view.setBackgroundResource(R.drawable.album_card_back);
                }

            }
        });
        LinearLayout playListContainer = (LinearLayout) findViewById(R.id.play_list_container);
        for(int i=0; i<5;i++){
            View item = inflater.inflate(R.layout.play_list_item, playListContainer, false);
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(MainActivity.this, PlayerActivity.class));
                }
            });
            if(i==3){
                item.setBackgroundColor(Color.GRAY);
                ((TextView)((LinearLayout)item).getChildAt(0)).setText(R.string.now_playing_track);
            }
            playListContainer.addView(item);
        }
        findViewById(R.id.subscribe_button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SubscriptionActivity.class));
            }
        });
    }

    View.OnClickListener albumsClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            String genre = (String) view.getTag();
            Intent intent = new Intent(MainActivity.this, ListActivity.class);
            intent.putExtra("Genre", genre);
            startActivity(intent);
        }
    };

}
