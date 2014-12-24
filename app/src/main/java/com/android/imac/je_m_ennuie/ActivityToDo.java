package com.android.imac.je_m_ennuie;

/**
 * Created by bruno on 01/12/2014.
 */
public class ActivityToDo {
    String nameActivity;
    int idActivity;

    ActivityToDo(String name, int id)
    {
        idActivity = id;
        nameActivity = name;
    }

    Answer getImpact(DataBase dataBase, Question question)
    {
        return dataBase.getImpactActivity(idActivity, question);
    }

    String getNameActivity() { return nameActivity; }

    // à modifier en fonction de la BDD
    public boolean isFavorite(){
        return false;
    }

    @Override
    public String toString() {
        return nameActivity;
    }
}
