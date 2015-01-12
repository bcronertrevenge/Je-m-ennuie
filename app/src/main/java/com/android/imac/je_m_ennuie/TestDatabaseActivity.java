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

    private static final String DB_NAME = "Jemennuie_database.sqlite3";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jemennuie);


        DataBaseHelper myDbHelper = new DataBaseHelper(this, DB_NAME);

        System.out.println("Debut Database");
        myDbHelper.createDataBase();

        System.out.println("Database created ! ");

        try {
            myDbHelper.openDataBase();

            String dbname = myDbHelper.getDatabaseName();
            System.out.println("Open Database" + dbname);

            Question question = myDbHelper.getQuestionById(8);
            System.out.println("HEYYY YAAAA ");
            /*
            cur.moveToFirst();
            Toast.makeText(this, "HEYYY YAAAA "+cur.getString(1), Toast.LENGTH_LONG).show();
            System.out.println("HEYYY YAAAA "+cur.getString(1));

            Question question = myDbHelper.cursorToQuestion(cur);
            */
            System.out.println(question.toString());
            Toast.makeText(this, "HEYYY YAAAA question : "+question.toString(), Toast.LENGTH_LONG).show();

            /*Cursor cur = myDbHelper.myDataBase.rawQuery("SELECT * FROM Activity", null);


            cur.moveToFirst();
            while (cur.isAfterLast() == false) {
                System.out.println("BOOOOWWAAA " + cur.getString(1));
                cur.moveToNext();
            }*/

        }catch(SQLException sqle){
            System.out.println("Database not opened ! :( ");
            throw sqle;
        }


    }


}
