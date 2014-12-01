package com.android.imac.je_m_ennuie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by bruno on 01/12/2014.
 */
public class Game {

    public static final int NB_QUESTIONS_PER_ROUND = 3;
    public static final int NB_ROUND = 2;
    public static final int NB_ACTIVITIES_TO_SHOW = 3;

    ArrayList<ActivityToDo> activityGameArray;
    ArrayList<Question> questionGameArray;
    DataBase dataBase;
    HashMap< Question, Answer > questionAnsweredMap;
    ArrayList<ActivityToDo> activityToShowArray;
    int roundNumber;
    int nbQuestionAnswered;
    int idCurrentQuestion;

    Game()
    {
        dataBase = new DataBase();
        activityGameArray = new ArrayList<ActivityToDo>();
        questionGameArray = new ArrayList<Question>();
        questionAnsweredMap = new HashMap<Question, Answer>();
        activityToShowArray = new ArrayList<ActivityToDo>();

        dataBase.initialize();
        roundNumber = 0;
        idCurrentQuestion = -1;
        nbQuestionAnswered = 0;
    }

    void newGame()
    {
        activityGameArray.clear();
        questionGameArray.clear();

        activityGameArray = (ArrayList<ActivityToDo>)dataBase.getActivityDBArray().clone();
        questionGameArray = (ArrayList<Question>)dataBase.getQuestionDBArray().clone();

        questionAnsweredMap.clear();
        roundNumber = 0;
        nbQuestionAnswered = 0;

        askQuestion();
    }

    void askQuestion()
    {
        //rand on question
        int idQuestion = (int)(Math.random() * questionGameArray.size());
        idCurrentQuestion = idQuestion;
        //Print
        System.out.println(questionGameArray.get(idQuestion).getNameQuestion());
    }

    void answerQuestion(Answer answer)
    {
        if( idCurrentQuestion == -1 )
        {
            System.out.println("No Question");
            return;
        }
        questionAnsweredMap.put( questionGameArray.get( idCurrentQuestion ), answer);
        questionGameArray.remove( idCurrentQuestion );

        idCurrentQuestion = -1;

        nbQuestionAnswered++;

        if( nbQuestionAnswered >= NB_QUESTIONS_PER_ROUND )
        {
            roundFinished();
        }
        else {
            askQuestion();
        }
    }

    void roundFinished()
    {
        roundNumber++;

        System.out.println("Round " + roundNumber);

        //Test print
        for(Map.Entry<Question, Answer> entry : questionAnsweredMap.entrySet()) {
            Question key = entry.getKey();
            Answer value = entry.getValue();

            System.out.println(key.getNameQuestion() + " : " + value.toString());
        }

        // Remove des activities à faire

        //Selection des activités à montrer
        searchPossibleActivities();
        for(ActivityToDo act : activityToShowArray)
        {
            System.out.println("Activité : " + act);
        }

        activityToShowArray.clear();

        if( roundNumber >= NB_ROUND )
        {
            System.out.println("All" + roundNumber + "Round ");
        }
    }

    void searchPossibleActivities()
    {
        if(activityGameArray.size() <= NB_ACTIVITIES_TO_SHOW)
        {
            activityToShowArray = activityGameArray;
        }
        else
        {
            Collections.shuffle(activityGameArray);
            for(int i=0; i < NB_ACTIVITIES_TO_SHOW; ++i)
            {
                activityToShowArray.add(activityGameArray.get(i));
            }
        }
    }

    public static void main(String [] args)
    {

    }
}
