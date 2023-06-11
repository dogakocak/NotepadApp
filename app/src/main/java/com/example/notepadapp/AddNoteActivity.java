package com.example.notepadapp;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddNoteActivity extends AppCompatActivity {

    EditText titleEt;
    EditText contentEt;

    String titleStr;
    String contentStr;
    String timeStamp = "" + System.currentTimeMillis();

    private DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        titleEt = findViewById(R.id.noteTitleInput);
        contentEt = findViewById(R.id.noteContentInput);
        dbHelper = new DbHelper(this);
    }


    public void saveButton(View view){
        titleStr = titleEt.getText().toString();
        contentStr = contentEt.getText().toString();

        if (titleStr.isEmpty() && contentStr.isEmpty()){
            super.onBackPressed();
            return;
        }

        Log.d(TAG, "saveButton: "+titleStr);
        Log.d(TAG, "saveButton: "+contentStr);

        Note note = new Note(titleStr,contentStr,timeStamp,timeStamp);

        long id = dbHelper.insertNote(note);

        Toast.makeText(this, "Data is added: "+id, Toast.LENGTH_SHORT).show();


    }
}