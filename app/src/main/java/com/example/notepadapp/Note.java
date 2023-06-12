package com.example.notepadapp;

public class Note {

    private int id;
    private String title;
    private String content;

    private String addedTime;

    private String updatedTime;


    public Note(String title, String content, String addedTime, String updatedTime) {
        this.title = title;
        this.content = content;
        this.addedTime = addedTime;
        this.updatedTime = updatedTime;
    }

    public Note(int id, String title, String content, String addedTime, String updatedTime) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.addedTime = addedTime;
        this.updatedTime = updatedTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(String updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getAddedTime() {
        return addedTime;
    }

    public void setAddedTime(String addedTime) {
        this.addedTime = addedTime;
    }
}
