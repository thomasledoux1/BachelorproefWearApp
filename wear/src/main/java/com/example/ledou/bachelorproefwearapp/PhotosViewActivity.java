package com.example.ledou.bachelorproefwearapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.ViewAnimator;

/**
 * Created by ledou on 31/03/2017.
 */

public class PhotosViewActivity extends Activity{
    private ViewAnimator viewAnimator;
    private int[] images = {R.drawable.photo1, R.drawable.photo2, R.drawable.photo3};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_gallery);
        viewAnimator = (ViewAnimator) findViewById(R.id.animator);
        for (int i = 0;i<images.length;i++) {
            ImageView imageView = new ImageView(getApplicationContext());
            imageView.setImageResource(images[i]);
            viewAnimator.addView(imageView);
        }
        Animation in = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        Animation out = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);
        viewAnimator.setInAnimation(in);
        viewAnimator.setOutAnimation(out);
        viewAnimator.setAnimateFirstView(false);
        viewAnimator.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                viewAnimator.showNext();
                return true;
            }
        });
    }
}
