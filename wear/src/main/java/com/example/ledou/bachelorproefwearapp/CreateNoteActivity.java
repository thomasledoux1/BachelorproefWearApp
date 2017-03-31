package com.example.ledou.bachelorproefwearapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.wearable.view.WatchViewStub;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

import static com.example.ledou.bachelorproefwearapp.R.color.black;

/**
 * Created by ledou on 17/03/2017.
 */

public class CreateNoteActivity extends Activity {
    public ImageButton voiceSearch;
    public ImageButton backBtn;
    private final int SPEECH = 100;
    public TableLayout table;
    private SpeechRecognizer recognizerIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_note);
        voiceSearch = (ImageButton) findViewById(R.id.voiceSearchBtn);
        backBtn = (ImageButton) findViewById(R.id.backBtn);
        table = (TableLayout) findViewById(R.id.resultTable);
        voiceSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recognizerIntent = SpeechRecognizer.createSpeechRecognizer(getApplicationContext());
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                startActivityForResult(intent, SPEECH);
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case SPEECH: {
                if (resultCode == RESULT_OK && null != data) {
                    recognizerIntent.stopListening();
                    System.out.println("Test");
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    final TableRow row = (TableRow) getLayoutInflater().inflate(R.layout.table_row, null);
                    TextView txv;
                    txv = (TextView) row.findViewById(R.id.noteTextView);
                    String resultString = result.get(0).substring(0,1).toUpperCase() + result.get(0).substring(1);
                    txv.setText("- " + resultString);
                    txv.setTextColor(Color.BLACK);
                    table.addView(row);
                    break;
                }
            }
        }
    }
}
