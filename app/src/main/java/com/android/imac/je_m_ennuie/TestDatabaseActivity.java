package com.android.imac.je_m_ennuie;

import android.app.Activity;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import java.io.IOException;

/**
 * Created by Virginie on 28/12/2014.
 */
public class TestDatabaseActivity extends Activity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jemennuie);
/*
        ActivityDatabase databaseTest = new ActivityDatabase(this);

        ActivityToDo testActivity;

        DataBaseHelper myDbHelper;

        myDbHelper = databaseTest.getBDDHelper();

        try {
            myDbHelper.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "erreur database", Toast.LENGTH_LONG).show();
        }

        myDbHelper.openDataBase();

        databaseTest.open();
        testActivity = databaseTest.getActivityToDo(0);
        /*if(testActivity != null) {
            Toast.makeText(this, testActivity.toString(), Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "erreur", Toast.LENGTH_LONG).show();
        }
*/

        DataBaseHelper myDbHelper = new DataBaseHelper(this);



        try {
            myDbHelper.createDataBase();
            //On affiche les infos du livre dans un Toast
            Toast.makeText(this, "Database created ! ", Toast.LENGTH_LONG).show();



        } catch (IOException ioe) {
            Toast.makeText(this, "Database not created ! :( ", Toast.LENGTH_LONG).show();
            throw new Error("Unable to create database");

        }

        try {
            myDbHelper.openDataBase();
            Toast.makeText(this, "Database opened ! ", Toast.LENGTH_LONG).show();
            String dbname = myDbHelper.getDatabaseName();
            Toast.makeText(this, dbname, Toast.LENGTH_LONG).show();

            // Test en dur --> récupérer info bdd
            SQLiteDatabase bdd;
            bdd = myDbHelper.getWritableDatabase();

            String bddPath = bdd.getPath();
            Toast.makeText(this, bddPath, Toast.LENGTH_LONG).show();


            Cursor c = bdd.rawQuery("SELECT text FROM Activity WHERE _id = 0", null);
            Toast.makeText(this, c.toString(), Toast.LENGTH_LONG).show();

            /*Cursor c = bdd.query("Activity", new String[] {"id", "text", "favorite", "discover"}, "id LIKE 0", null, null, null, null );
            Toast.makeText(this, "Cursor test", Toast.LENGTH_LONG).show();
            Toast.makeText(this, c.toString(), Toast.LENGTH_LONG).show();
*/



        }catch(SQLException sqle){
            Toast.makeText(this, "Database not opened ! :( ", Toast.LENGTH_LONG).show();
            throw sqle;
        }

    }
}
