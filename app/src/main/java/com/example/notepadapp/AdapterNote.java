package com.example.notepadapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterNote extends RecyclerView.Adapter<AdapterNote.NoteViewHolder>{

    private Context context;
    private List<Note> noteList;

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
