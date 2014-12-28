package com.android.imac.je_m_ennuie;

import android.app.Activity;
import android.database.SQLException;
import android.os.Bundle;
import android.widget.Toast;

import java.io.IOException;

/**
 * Created by Justine on 28/12/2014.
 */
public class TestDatabaseActivity extends Activity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jemennuie);

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
        }catch(SQLException sqle){
            Toast.makeText(this, "Database not opened ! :( ", Toast.LENGTH_LONG).show();
            throw sqle;
        }

    }
}
