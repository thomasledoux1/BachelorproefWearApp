package com.example.ledou.bachelorproefwearapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
    private Button noteAddBtn;
    private Button viewPhotosBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                noteAddBtn = (Button) stub.findViewById(R.id.createNoteBtn);
                noteAddBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        toCreateNotePage();
                    }
                });
                viewPhotosBtn = (Button) findViewById(R.id.viewPhotosBtn);
                viewPhotosBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        toPhotosViewPage();
                    }
                });
            }
        });
    }

    public void toCreateNotePage() {
        Intent createNoteIntent = new Intent(MainActivity.this, CreateNoteActivity.class);
        MainActivity.this.startActivity(createNoteIntent);
    }

    public void toPhotosViewPage() {
        Intent photosViewIntent = new Intent(MainActivity.this, PhotosViewActivity.class);
        MainActivity.this.startActivity(photosViewIntent);
    }
}
