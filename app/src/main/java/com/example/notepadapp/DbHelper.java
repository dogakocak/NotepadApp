package com.example.notepadapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper{
    public DbHelper(@Nullable Context context) {
        super(context, DB.DATABASE_NAME, null, DB.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DB.CREATE_TABLE_QUERY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DB.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public long insertNote(Note note){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(DB.N_TITLE,note.getTitle());
        contentValues.put(DB.N_CONTENT,note.getContent());
        contentValues.put(DB.N_ADDED_TIME,""+note.getAddedTime());
        contentValues.put(DB.N_UPDATED_TIME,""+note.getUpdatedTime());

        long id = db.insert(DB.TABLE_NAME,null,contentValues);


        db.close();

        return id;
    }
}
