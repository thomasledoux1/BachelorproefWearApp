package com.example.ledou.bachelorproefwearapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.wearable.view.WatchViewStub;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by ledou on 17/03/2017.
 */

public class CreateNoteActivity extends Activity {
    public Button voiceSearch;
    private final int SPEECH = 100;
    public TextView noteText;
    private SpeechRecognizer recognizerIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_note);
        voiceSearch = (Button) findViewById(R.id.voiceSearchBtn);
        noteText = (TextView) findViewById(R.id.noteVoiceText);
        voiceSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recognizerIntent = SpeechRecognizer.createSpeechRecognizer(getApplicationContext());
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                        "Say something");

                startActivityForResult(intent, SPEECH);
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
                    noteText.setText(result.get(0));

                    break;
                }
            }
        }
    }
}
