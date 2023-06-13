package com.example.notepadapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

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

    public int updateNote(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(DB.N_TITLE, note.getTitle());
        contentValues.put(DB.N_CONTENT, note.getContent());
        contentValues.put(DB.N_ADDED_TIME, "" + note.getAddedTime());
        contentValues.put(DB.N_UPDATED_TIME, "" + note.getUpdatedTime());

        String whereClause = DB.N_ID + " = ?";
        String[] whereArgs = {String.valueOf(note.getId())};

        int rowsAffected = db.update(DB.TABLE_NAME, contentValues, whereClause, whereArgs);

        db.close();

        return rowsAffected;
    }

    public int deleteNote(String noteId){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        String whereClause = DB.N_ID + " = ?";
        String[] whereArgs = {String.valueOf(noteId)};

        int rowsAffected = db.delete(DB.TABLE_NAME, whereClause, whereArgs);

        db.close();

        return rowsAffected;

    }

    public List<Note> getAllData(){
        List<Note> arrayList = new ArrayList<>();

        String selectQuery = "SELECT * FROM "+DB.TABLE_NAME;

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        if (cursor.moveToFirst()){
            do {
                Note note = new Note(
                        cursor.getInt(cursor.getColumnIndexOrThrow(DB.N_ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(DB.N_TITLE)),
                        cursor.getString(cursor.getColumnIndexOrThrow(DB.N_CONTENT)),
                        cursor.getString(cursor.getColumnIndexOrThrow(DB.N_ADDED_TIME)),
                        cursor.getString(cursor.getColumnIndexOrThrow(DB.N_UPDATED_TIME))

                );
                arrayList.add(note);
            }while (cursor.moveToNext());
        }

        db.close();

        return arrayList;
    }
}
