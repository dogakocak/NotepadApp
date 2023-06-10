package com.example.notepadapp;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class AddNoteActivity extends AppCompatActivity {

    EditText titleEt;
    EditText contentEt;

    String titleStr;
    String contentStr;
    private boolean isFirstLine = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        titleEt = findViewById(R.id.noteTitleInput);
        contentEt = findViewById(R.id.noteContentInput);
    }


    public void saveButton(View view){
        titleStr = titleEt.getText().toString();
        contentStr = contentEt.getText().toString();

        Log.d(TAG, "saveButton: "+titleStr);
        Log.d(TAG, "saveButton: "+contentStr);


    }
}