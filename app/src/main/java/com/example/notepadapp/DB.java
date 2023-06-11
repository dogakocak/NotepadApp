package com.example.notepadapp;

public class DB {

    public static final String DATABASE_NAME = "NOTES_DB";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME ="NOTES_TABLE";

    public static final String N_ID ="ID";
    public static final String N_TITLE ="TITLE";
    public static final String N_CONTENT ="CONTENT";

    public static final String N_ADDED_TIME ="ADDED_TIME";
    public static final String N_UPDATED_TIME="UPDATED_TIME";

    public static final String CREATE_TABLE_QUERY = "CREATE TABLE " + TABLE_NAME +"( " +
            N_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            N_TITLE + " TEXT, " +
            N_CONTENT + " TEXT, " +
            N_ADDED_TIME + " TEXT, " +
            N_UPDATED_TIME + " TEXT " +
            " );";

}
