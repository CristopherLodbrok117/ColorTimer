package com.example.colortimer;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;

public class SuggestionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestion);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Bitmap bmp = (Bitmap) extras.get("bmp_Image");
        }
    }

}