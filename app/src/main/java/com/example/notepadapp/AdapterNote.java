package com.example.notepadapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AdapterNote extends RecyclerView.Adapter<AdapterNote.NoteViewHolder>{

    private Context context;
    private List<Note> noteList;

    private static final int MAX_CONTENT_LENGTH = 40;

    public AdapterNote(Context context, List<Note> noteList) {
        this.context = context;
        this.noteList = noteList;
    }


    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_row,parent,false);
        NoteViewHolder vh = new NoteViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = noteList.get(position);

        int id = note.getId();
        String title = note.getTitle();
        String content = note.getContent();
        String addedTime = note.getAddedTime();
        String currentTime = ""+System.currentTimeMillis();
        long ctimestampLong = Long.parseLong(currentTime);

        long timestampLong = Long.parseLong(addedTime);

        if (title.isEmpty()){
            title = "Untitled";
        }

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            Instant instant = Instant.ofEpochMilli(timestampLong);
            LocalDate date = instant.atZone(ZoneId.systemDefault()).toLocalDate();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            addedTime = date.format(formatter);
        }

        if (content.length() > MAX_CONTENT_LENGTH){
            content = content.substring(0,MAX_CONTENT_LENGTH);
            content += "...";

        }





        holder.title.setText(title);
        holder.content.setText(content);
        holder.addedTime.setText(addedTime);
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    class NoteViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView content;
        TextView addedTime;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            content = itemView.findViewById(R.id.content);
            addedTime = itemView.findViewById(R.id.addedTime);
        }
    }


}
