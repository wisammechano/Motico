package com.recoded.motico;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;

public class ListActivity extends ApplicationSubActivity {
    GridView container;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        setTitle((String)getIntent().getExtras().get("Genre"));

        container = (GridView) findViewById(R.id.tracks_container);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.album_card_template, R.id.card_title);
        for(int i=0; i<10; i++){
            adapter.add("Track Name");
        }
        container.setAdapter(adapter);
        container.setOnItemClickListener(trackClickListener);

    }

    AdapterView.OnItemClickListener trackClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            startActivity(new Intent(ListActivity.this, PlayerActivity.class));
        }
    };
}
