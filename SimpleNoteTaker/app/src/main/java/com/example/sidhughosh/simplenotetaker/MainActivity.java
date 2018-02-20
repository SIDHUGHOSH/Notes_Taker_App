package com.example.sidhughosh.simplenotetaker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView mListViewNotes;

    private ListView mListviewNotes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListviewNotes =  findViewById(R.id.main_listview_notes);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.manu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_main_new_note:
                //start NoteActivity in NewNote mode
                startActivity(new Intent(this,NoteActivity.class));
                break;
        }

        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mListviewNotes.setAdapter(null);

        ArrayList<Note> notes = Utilities.getAllSavedNotes(this);

        if(notes == null ||notes.size() == 0){
            Toast.makeText(this,"you have no saved notes!",Toast.LENGTH_SHORT).show();
            return;
        } else {
            NoteAdapter na = new NoteAdapter(this, R.layout.item_note, notes);
            mListviewNotes.setAdapter(na);

            mListviewNotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String fileName = ((Note)mListviewNotes.getItemAtPosition(position)).getmDateTime()
                            + Utilities.FILE_EXTENSION;

                    Intent viewNoteIntent = new Intent(getApplicationContext(),NoteActivity.class);
                    viewNoteIntent.putExtra("NOTE_FILE",fileName);
                    startActivity(viewNoteIntent);
                }
            });


        }
    }
}
