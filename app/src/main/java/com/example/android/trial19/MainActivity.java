package com.example.android.trial19;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    SQLiteDatabase notesDB;
    ArrayAdapter arrayAdapter;
    ArrayList<String> titles;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titles = new ArrayList<String>();
        listView =(ListView) findViewById(R.id.listView);
        arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, titles);
        listView.setAdapter(arrayAdapter);

        notesDB = this.openOrCreateDatabase("Note", MODE_PRIVATE, null);
        notesDB.execSQL("CREATE TABLE IF NOT EXISTS notes(id INTEGER PRIMARY KEY, title VARCHAR, content VARCHAR, place VARCHAR)");
        //notesDB.execSQL("DELETE FROM notes");
        //notesDB.execSQL("INSERT INTO notes(title, content, place) VALUES('toney', 'hi guys','chennai')");
        //notesDB.execSQL("INSERT INTO notes(title, content, place) VALUES('toney1', 'hi guys1','chennai1')");


        try {
            Cursor c = notesDB.rawQuery("SELECT * FROM notes", null);

            int titleIndex = c.getColumnIndex("title");
            int contentIndex = c.getColumnIndex("content");
            int placeIndex = c.getColumnIndex("place");

            c.moveToFirst();

            titles.clear();

            while (c.moveToNext()) {
                titles.add(c.getString(titleIndex));
                Log.i("Title", c.getString(titleIndex));

            }
            arrayAdapter.notifyDataSetChanged();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        //updateListView();

    }


}
