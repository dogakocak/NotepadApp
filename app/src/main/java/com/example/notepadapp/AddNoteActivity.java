package com.example.notepadapp;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddNoteActivity extends AppCompatActivity {

    EditText titleEt;
    EditText contentEt;
    String titleStr;
    String contentStr;
    String timeStamp = "" + System.currentTimeMillis();

    TextView deleteText;

    private DbHelper dbHelper;

    private String noteId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        titleEt = findViewById(R.id.noteTitleInput);
        contentEt = findViewById(R.id.noteContentInput);
        deleteText = findViewById(R.id.deleteButton);
        dbHelper = new DbHelper(this);

        Intent intent = getIntent();
        noteId = intent.getStringExtra("noteId");

        if (noteId != null){
            deleteText.setVisibility(View.VISIBLE);
            loadData(noteId);
        }
    }

    private void loadData(String id) {
        String selectQuery = "SELECT * FROM " + DB.TABLE_NAME + " WHERE " + DB.N_ID + " = ?";
        String[] selectionArgs = {String.valueOf(id)};

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery,selectionArgs);

        Note note = null;

        if (cursor.moveToFirst()){
            do {
                note = new Note(
                        cursor.getInt(cursor.getColumnIndexOrThrow(DB.N_ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(DB.N_TITLE)),
                        cursor.getString(cursor.getColumnIndexOrThrow(DB.N_CONTENT)),
                        cursor.getString(cursor.getColumnIndexOrThrow(DB.N_ADDED_TIME)),
                        cursor.getString(cursor.getColumnIndexOrThrow(DB.N_UPDATED_TIME))

                );
            }while (cursor.moveToNext());
        }
        if (note == null){
            Toast.makeText(this, "Beklenmeyen bir hata olustu", Toast.LENGTH_SHORT).show();
            return;
        }
        titleEt.setText(note.getTitle());
        contentEt.setText(note.getContent());
    }


    public void saveButton(View view){
        titleStr = titleEt.getText().toString();
        contentStr = contentEt.getText().toString();

        if (titleStr.isEmpty() && contentStr.isEmpty()){
            super.onBackPressed();
            return;
        }
        Note note = new Note(titleStr,contentStr,timeStamp,timeStamp);

        if(noteId != null){
            note.setId(Integer.parseInt(noteId));
            int id = dbHelper.updateNote(note);
            Toast.makeText(this, "Note is updated ", Toast.LENGTH_SHORT).show();

        }else{
            long id = dbHelper.insertNote(note);
            Toast.makeText(this, "Note is added", Toast.LENGTH_SHORT).show();
        }

        super.onBackPressed();

    }

    public void deleteButton(View view){

    }
}