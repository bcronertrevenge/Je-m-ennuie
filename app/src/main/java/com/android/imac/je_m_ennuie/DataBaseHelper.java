package com.android.imac.je_m_ennuie;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Virginie on 28/12/2014.
 */
public class DataBaseHelper extends SQLiteOpenHelper {

    //The Android's default system path of your application database.
    private static String DB_PATH = "/data/data/com.android.imac.je_m_ennuie/databases/";

    private static String DB_NAME = "Jemennuie_database";

    // private static String ASSETS_DB_FOLDER = "db";

    public SQLiteDatabase myDataBase;

    private final Context myContext;

        public SQLiteDatabase getDb() {
            return myDataBase;
        }

        public DataBaseHelper(Context context, String databaseName) {
            super(context, databaseName, null, 1);
            myContext = context;
            //Write a full path to the databases of your application
            String packageName = context.getPackageName();
            DB_PATH = String.format("//data//data//%s//databases//", packageName);
            DB_NAME = databaseName;
            openDataBase();
        }

        //This piece of code will create a database if it’s not yet created
        public void createDataBase() {
            boolean dbExist = checkDataBase();
            if (!dbExist) {
                this.getReadableDatabase();
                try {
                    copyDataBase();
                } catch (IOException e) {
                    Log.e(this.getClass().toString(), "Copying error");
                    throw new Error("Error copying database!");
                }
            } else {
                Log.i(this.getClass().toString(), "Database already exists");
            }
        }

        //Performing a database existence check
        private boolean checkDataBase() {
            SQLiteDatabase checkDb = null;
            try {
                String path = DB_PATH + DB_NAME;
                checkDb = SQLiteDatabase.openDatabase(path, null,
                        SQLiteDatabase.OPEN_READONLY);
            } catch (SQLException e) {
                Log.e(this.getClass().toString(), "Error while checking db");
            }
            //Android doesn’t like resource leaks, everything should
            // be closed
            if (checkDb != null) {
                checkDb.close();
            }
            return checkDb != null;
        }

        //Method for copying the database
        private void copyDataBase() throws IOException {
            //Open a stream for reading from our ready-made database
            //The stream source is located in the assets
            InputStream externalDbStream = myContext.getAssets().open(DB_NAME);

            //Path to the created empty database on your Android device
            String outFileName = DB_PATH + DB_NAME;

            //Now create a stream for writing the database byte by byte
            OutputStream localDbStream = new FileOutputStream(outFileName);

            //Copying the database
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = externalDbStream.read(buffer)) > 0) {
                localDbStream.write(buffer, 0, bytesRead);
            }
            //Don’t forget to close the streams
            localDbStream.close();
            externalDbStream.close();
        }

        public SQLiteDatabase openDataBase() throws SQLException {
            String path = DB_PATH + DB_NAME;
            if (myDataBase == null) {
                createDataBase();
                myDataBase = SQLiteDatabase.openDatabase(path, null,
                        SQLiteDatabase.OPEN_READWRITE);
            }
            return myDataBase;
        }


        /****************************** Questions *************************************/
        public Question getQuestionById(int id){
            Cursor c = this.myDataBase.rawQuery("SELECT * FROM Question WHERE _id = "+ id, null);
            return cursorToQuestion(c);
        }

        public Question cursorToQuestion(Cursor cursor){
            //si aucun élément n'a été retourné dans la requête, on renvoie null
            if (cursor.getCount() == 0) {
                return null;
            }
            //Sinon on se place sur le premier élément
            cursor.moveToFirst();
            //On créé une question
            Question question = new Question(cursor.getString(1));
            //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
            question.setId(cursor.getInt(0));

            //On ferme le cursor
            cursor.close();

            return question;
        }

        // choisir aléatoirement un nombre de questions dans la base de données et les entrer dans un ArrayList<Question>
        public ArrayList<Question> generateQuestions(int nbQuestions){

            ArrayList<Question> questions = new ArrayList<Question>();

            // on compte le nb de questions dans la bdd
            Cursor cur = this.myDataBase.rawQuery("SELECT * FROM Questions", null);
            int maxValue = cur.getCount();

            // on génère nbQuestions nombre aléatoire
            int[]randomNumbers = generateRandom(nbQuestions, maxValue);

            // on remplit l'ArrayList avec les questions de la bdd
            for (int i = 0; i<nbQuestions; ++i){
                questions.add(this.getQuestionById(randomNumbers[i]));
                System.out.println(randomNumbers[i]+ " Question numéro "+ questions.get(i).getId() +" énoncé : "+questions.get(i).toString());
            }

            return questions;
        }


        /****************************** Activité *************************************/
        public ActivityToDo getActivityToDoById(int id){
            Cursor c = this.myDataBase.rawQuery("SELECT * FROM Activity WHERE _id = "+ id, null);
            return cursorToActivityToDo(c);
        }


        private ActivityToDo cursorToActivityToDo(Cursor c){
            //si aucun élément n'a été retourné dans la requête, on renvoie null
            if (c.getCount() == 0) {
                return null;
            }
            //Sinon on se place sur le premier élément
            c.moveToFirst();
            //On créé une activité
            ActivityToDo activityToDo = new ActivityToDo(c.getString(1), c.getInt(0));
            //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
            activityToDo.setIdActivity(c.getInt(0));
            activityToDo.setNameActivity(c.getString(1));

            // caster favorite et discover : int en bdd -> boolean en java
            boolean favorite = c.getInt(2) > 0 ? true : false ;
            boolean discover = c.getInt(3) > 0 ? true : false ;

            activityToDo.setFavorite(favorite);
            activityToDo.setDiscovered(discover);
            //On ferme le cursor
            c.close();

            //On retourne le livre
            return activityToDo;
        }


        // générer un certain nombre (length) de chiffre aléatoire
        public static int[] generateRandom(int length, int maxValue) {
            Random random = new Random();
            int[] digits = new int[length];
            digits[0] = (int) (random.nextInt(maxValue) + '1');
            for (int i = 1; i < length; i++) {
                digits[i] = (int) (random.nextInt(maxValue+1) + '0');
            }

            for (int i=0; i<length; ++i){
                System.out.println(digits[i]);
            }
            //return Long.parseLong(new String(digits));
            return digits;
        }


        @Override
        public synchronized void close() {
            if (myDataBase != null) {
                myDataBase.close();
            }
            super.close();
        }
        @Override
        public void onCreate(SQLiteDatabase db) {}
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
    }