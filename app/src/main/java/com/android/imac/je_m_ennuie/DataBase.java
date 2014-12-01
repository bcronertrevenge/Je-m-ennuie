package com.android.imac.je_m_ennuie;

import android.app.Activity;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by bruno on 01/12/2014.
 */
public class DataBase {
    private
    ArrayList<ActivityToDo> activityDBArray;
    ArrayList<Question> questionDBArray;

    public
    DataBase()
    {

    }

    void initialize()
    {
        activityDBArray = new ArrayList<ActivityToDo>();
        questionDBArray = new ArrayList<Question>();

        //Remplissage d'activité avec la base de données
        activityDBArray.add( new ActivityToDo("Activité 1") );
        activityDBArray.add( new ActivityToDo("Activité 2") );
        activityDBArray.add( new ActivityToDo("Activité 3") );
        activityDBArray.add( new ActivityToDo("Activité 4") );
        activityDBArray.add( new ActivityToDo("Activité 5") );

        //Remplissage de questions avec la base de données
        questionDBArray.add( new Question("Question 1") );
        questionDBArray.add( new Question("Question 2") );
        questionDBArray.add( new Question("Question 3") );
        questionDBArray.add( new Question("Question 4") );
        questionDBArray.add( new Question("Question 5") );
    }

    ArrayList<ActivityToDo> getActivityDBArray()
    {
        return activityDBArray;
    }

    ArrayList<Question> getQuestionDBArray()
    {
        return questionDBArray;
    }

    ActivityToDo getActivity(int id)
    {
        return activityDBArray.get(id);
    }

    Question getQuestion(int id)
    {
        return questionDBArray.get(id);
    }
}
