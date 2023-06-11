package com.example.notepadapp;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private RecyclerView noteRv;

    private DbHelper dbHelper;
    private AdapterNote adapterNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        noteRv = findViewById(R.id.noteRv);
        noteRv.setHasFixedSize(true);
        dbHelper = new DbHelper(this);
        
        loadData();
    }

    private void loadData() {
        adapterNote = new AdapterNote(this,dbHelper.getAllData());
        noteRv.setLayoutManager(new LinearLayoutManager(this));
        noteRv.setAdapter(adapterNote);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    public void addButton(View view){
        Intent intent = new Intent(this,AddNoteActivity.class);
        startActivity(intent);

    }
}