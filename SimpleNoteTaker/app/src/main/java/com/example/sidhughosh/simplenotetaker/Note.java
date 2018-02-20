package com.example.sidhughosh.simplenotetaker;

import android.content.Context;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by sidhu ghosh on 10-02-2018.
 */

public class Note implements Serializable {

    private long mDateTime;
    private String mTitle;
    private String mcontent;

    public Note(long DateTime, String title, String content) {
        mcontent = content;
        mDateTime = DateTime;
        mTitle = title;
    }

    public void setmDateTime(long mDateTime) {
        this.mDateTime = mDateTime;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public void setMcontent(String mcontent) {
        this.mcontent = mcontent;
    }

    public long getmDateTime() {
        return mDateTime;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getMcontent() {
        return mcontent;
    }

    public String getDateTimeFormatted(Context context){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"
        , context.getResources().getConfiguration().locale);
        sdf.setTimeZone(TimeZone.getDefault());
        return  sdf.format(new Date(mDateTime));
    }

}
