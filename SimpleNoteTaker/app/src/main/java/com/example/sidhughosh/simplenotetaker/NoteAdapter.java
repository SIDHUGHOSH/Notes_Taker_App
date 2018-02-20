package com.example.sidhughosh.simplenotetaker;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
/**
 * Created by sidhu ghosh on 18-02-2018.
 */

public class NoteAdapter extends ArrayAdapter<Note> {

    public NoteAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Note> notes) {
        super(context, resource, notes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //return super.getView(position, convertView, parent);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.item_note, null);


        }

        Note note = getItem(position);

        if (note != null) {
            TextView title = (TextView) convertView.findViewById(R.id.list_note_title);
            TextView date= (TextView) convertView.findViewById(R.id.list_note_date);
            TextView content = (TextView) convertView.findViewById(R.id.list_note_content);

            title.setText(note.getmTitle());
            date.setText(note.getDateTimeFormatted(getContext()));

            if(note.getMcontent().length() >50){
                content.setText(note.getMcontent().substring(0,50));
            } else {
                content.setText(note.getMcontent());
            }
        }

        return convertView;
    }
}