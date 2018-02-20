package com.example.sidhughosh.simplenotetaker;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class NoteActivity extends AppCompatActivity {


    private EditText mEtTitle;
    private EditText mEtContent;

    private String mNoteFileName;
    private Note mLoadedNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        mEtTitle = (EditText) findViewById(R.id.note_et_title);
        mEtContent = (EditText) findViewById(R.id.note_et_content);

        mNoteFileName = getIntent().getStringExtra("NOTE_FILE");
        if (mNoteFileName != null && !mNoteFileName.isEmpty()) {
            mLoadedNote = Utilities.getNoteByName(this, mNoteFileName);

            if (mLoadedNote != null) {
                mEtTitle.setText(mLoadedNote.getmTitle());
                mEtContent.setText(mLoadedNote.getMcontent());
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_note_new, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_note_save:
                saveNote();
            case R.id.action_note_deleat:
                deleteNote();

                break;
        }

        return true;


    }


    private void saveNote() {
        Note note;
        if(mEtTitle.getText().toString().trim().isEmpty() || mEtContent.getText().toString().trim().isEmpty()){
            Toast.makeText(this,"please enter a title and a content", Toast.LENGTH_SHORT).show();
            return;
        }

        if (mLoadedNote == null) {
            note = new Note(System.currentTimeMillis(), mEtTitle.getText().toString()
                    , mEtContent.getText().toString());
        } else {
            note = new Note(mLoadedNote.getmDateTime(), mEtTitle.getText().toString()
                    , mEtContent.getText().toString());
        }

        if (Utilities.saveNote(this, note)) {
            Toast.makeText(this, "your note is saved!", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "can not save the note,please make sure you have enough space on your device"
                    , Toast.LENGTH_SHORT).show();
        }

        finish();

    }

    public void deleteNote() {
        if (mLoadedNote == null) {
            finish();
        } else {

            AlertDialog.Builder dialog = new AlertDialog.Builder(this)
                    .setTitle("delete")
                    .setMessage("you are about to delete" + mEtTitle.getText().toString() + ",are you sure?")
                    .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Utilities.deleteNote(getApplicationContext()
                                    , mLoadedNote.getmDateTime() + Utilities.FILE_EXTENSION);
                            Toast.makeText(getApplicationContext()
                                    , mEtTitle.getText().toString() + "id deleted", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    })
                    .setNegativeButton("no", null)
                    .setCancelable(false);
            dialog.show();


        }
    }
}

