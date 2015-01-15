package com.android.imac.je_m_ennuie;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

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

            /* test question */
            Question question = myDbHelper.getQuestionById(8);
            System.out.println("HEYYY YAAAA ");

            System.out.println(question.toString());
            Toast.makeText(this, "HEYYY YAAAA question : "+question.toString(), Toast.LENGTH_LONG).show();

             /* test activité */
            ActivityToDo activityToDo = myDbHelper.getActivityToDoById(1);
            System.out.println("HEYYY YAAAA ");

            System.out.println(activityToDo.toString());
            Toast.makeText(this, "HEYYY YAAAA activité : "+activityToDo.toString(), Toast.LENGTH_LONG).show();


            /* test ArrayList Question */

            /*int[] test_int =  myDbHelper.generateRandom(10, 50);
            for (int i = 0; i<10; ++i){
                System.out.println(test_int[i]);
                Toast.makeText(this, "intRandom : "+test_int[i], Toast.LENGTH_LONG).show();
            }*/

            ArrayList<Question> questionArrayList = myDbHelper.generateQuestions(10);
            for (int i = 0; i<10; ++i){
                Toast.makeText(this, " Question numéro "+ questionArrayList.get(i).getId() +" énoncé : "+questionArrayList.get(i).toString(), Toast.LENGTH_LONG).show();
            }

        }catch(SQLException sqle){
            System.out.println("Database not opened ! :( ");
            throw sqle;
        }

        // fermer la bdd
        myDbHelper.close();

    }


}
