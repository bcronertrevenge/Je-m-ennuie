package com.android.imac.je_m_ennuie;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Virginie on 28/12/2014.
 */
public class ActivityDatabase {

    private static final String TABLE_ACTIVITY = "Activity";
    private static final String COL_ID = "id";
    private static final int NUM_COL_ID = 0;
    private static final String COL_TEXT = "text";
    private static final int NUM_COL_TEXT = 1;
    private static final String COL_IS_FAVORITE = "favorite";
    private static final int NUM_COL_IS_FAVORITE = 2;
    private static final String COL_IS_DISCOVERED = "discover";
    private static final int NUM_COL_IS_DISCOVERED = 3;

    private SQLiteDatabase bdd;

    private DataBaseHelper bddHelper;

    public ActivityDatabase(Context context){
        //On créer la BDD et sa table
        bddHelper = new DataBaseHelper(context);
    }

    public void open(){
        //on ouvre la BDD en écriture
        bdd = bddHelper.getWritableDatabase();
    }

    public void close(){
        //on ferme l'accès à la BDD
        bdd.close();
    }

    public SQLiteDatabase getBDD(){
        return bdd;
    }

    public DataBaseHelper getBDDHelper(){
        return bddHelper;
    }

    // insérer une activité dans la base de données
    public long insertNewActivityInDB(ActivityToDo activityToDo){
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
        values.put(COL_TEXT, activityToDo.getNameActivity());
        values.put(COL_IS_FAVORITE, activityToDo.getFavorite());
        values.put(COL_IS_DISCOVERED, activityToDo.getDiscovered());
        //on insère l'objet dans la BDD via le ContentValues
        return bdd.insert(TABLE_ACTIVITY, null, values);
    }

    // Changer une activité de la bdd d'après une activité du java
    public int updateActivityToDo(int id, ActivityToDo activityToDo){
        //La mise à jour d'une activité dans la BDD fonctionne plus ou moins comme une insertion
        //il faut simple préciser quelle livre on doit mettre à jour grâce à l'ID
        ContentValues values = new ContentValues();
        values.put(COL_TEXT, activityToDo.getNameActivity());
        values.put(COL_IS_FAVORITE, activityToDo.getFavorite());
        values.put(COL_IS_DISCOVERED, activityToDo.getDiscovered());
        return bdd.update(TABLE_ACTIVITY, values, COL_ID + " = " +id, null);
    }

    //Suppression d'une activité de la BDD grâce à l'ID
    public int removeActivityToDoWithID(int id){
        return bdd.delete(TABLE_ACTIVITY, COL_ID + " = " +id, null);
    }

/*** Vraiment utile ? ***/
    // récupérer une activité de la base de données en entrant son nom
    public ActivityToDo getActivityToDoWithName(String name){
        //Récupère dans un Cursor les valeur correspondant à un livre contenu dans la BDD (ici on sélectionne le livre grâce à son titre)
        Cursor c = bdd.query(TABLE_ACTIVITY, new String[] {COL_ID, COL_TEXT, COL_IS_FAVORITE, COL_IS_DISCOVERED}, COL_TEXT + " LIKE \"" + name +"\"", null, null, null, null);
        return cursorToActivity(c);
    }

    // récupérer une activité de la base de données en entrant son nom
    public ActivityToDo getActivityToDo(int id){
        //Récupère dans un Cursor les valeur correspondant à un livre contenu dans la BDD (ici on sélectionne le livre grâce à son titre)
        //Cursor c = bdd.query(TABLE_ACTIVITY, new String[] {COL_ID, COL_TEXT, COL_IS_FAVORITE, COL_IS_DISCOVERED}, COL_ID + " LIKE \"" + id +"\"", null, null, null, null, null);

        Cursor c = bdd.query(TABLE_ACTIVITY, new String[] {COL_ID, COL_TEXT, COL_IS_FAVORITE, COL_IS_DISCOVERED}, COL_ID + " LIKE \"" + id +"\"", null, null, null, null );
        return cursorToActivity(c);
    }

/*** Vraiment utile ? ***/
    //Cette méthode permet de convertir un cursor en un livre
    private ActivityToDo cursorToActivity(Cursor c){
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0) {
            return null;
        }
        //Sinon on se place sur le premier élément
        c.moveToFirst();
        //On créé une activité
        ActivityToDo activityToDo = new ActivityToDo(c.getString(NUM_COL_TEXT), c.getInt(NUM_COL_ID));
        //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
        activityToDo.setIdActivity(c.getInt(NUM_COL_ID));
        activityToDo.setNameActivity(c.getString(NUM_COL_TEXT));

        // caster favorite et discover : int en bdd -> boolean en java
        boolean favorite = c.getInt(NUM_COL_IS_FAVORITE) > 0 ? true : false ;
        boolean discover = c.getInt(NUM_COL_IS_DISCOVERED) > 0 ? true : false ;

        activityToDo.setFavorite(favorite);
        activityToDo.setDiscovered(discover);
        //On ferme le cursor
        c.close();

        //On retourne le livre
        return activityToDo;
    }

}
