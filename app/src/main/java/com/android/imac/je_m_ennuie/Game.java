package com.android.imac.je_m_ennuie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by bruno on 01/12/2014.
 */
public class Game {

    public static final int NB_QUESTIONS_PER_ROUND = 3;
    public static final int NB_ROUND = 2;
    public static final int NB_ACTIVITIES_TO_SHOW = 10;

    LinkedList<ActivityToDo> activityGameArray;
    ArrayList<Question> questionGameArray;
    DataBase dataBase;
    HashMap< Question, Answer > questionAnsweredMap;
    LinkedList<ActivityToDo> activityToShowArray;
    int roundNumber;
    int nbQuestionAnswered;
    int idCurrentQuestion;

    Game()
    {
        dataBase = new DataBase();
        activityGameArray = new LinkedList<ActivityToDo>();
        questionGameArray = new ArrayList<Question>();
        questionAnsweredMap = new HashMap<Question, Answer>();
        activityToShowArray = new LinkedList<ActivityToDo>();

        dataBase.initialize();
        roundNumber = 0;
        idCurrentQuestion = -1;
        nbQuestionAnswered = 0;
    }

    void newGame()
    {
        activityGameArray.clear();
        questionGameArray.clear();

        activityGameArray = (LinkedList<ActivityToDo>)dataBase.getActivityDBArray().clone();
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

        // Remove des activities à faire
        removeActivities();

        //Selection des activités à montrer
        searchPossibleActivities();

        if( activityToShowArray.size() > 0 )
        {
            for(ActivityToDo act : activityToShowArray)
            {
                System.out.println("Activité : " + act);
            }
        }
        else
        {
            System.out.println("Désolé, aucune activité à proposer");
        }

        if( roundNumber >= NB_ROUND )
        {
            System.out.println("All" + roundNumber + "Round ");
        }
    }

    void removeActivities()
    {
        for(Map.Entry<Question, Answer> entry : questionAnsweredMap.entrySet()) {
            Question question = entry.getKey();
            Answer answer = entry.getValue();

            System.out.println(question.getNameQuestion() + " : " + answer.toString());

            //Si le joueur a répondu peu importe, on supprime aucune activité
            if( answer == Answer.NoMatter )
                continue;

            LinkedList<ActivityToDo> activitiesToSupress = new LinkedList<ActivityToDo>();

            for(ActivityToDo activityToDo : activityGameArray )
            {
                Answer impact = activityToDo.getImpact(dataBase, question);

                if( impact != Answer.NoMatter && impact != answer )
                {
                    //Remove
                    //activityGameArray.remove(activityToDo);
                    activitiesToSupress.push(activityToDo);
                    System.out.println("Activité " + activityToDo + " supprimée");
                }
            }

            for(ActivityToDo activityToSupress : activitiesToSupress)
            {
                activityGameArray.remove(activityToSupress);
            }

            activitiesToSupress.clear();
        }
    }

    void searchPossibleActivities()
    {
        activityToShowArray.clear();

        if(activityGameArray.size() <= NB_ACTIVITIES_TO_SHOW)
        {
            activityToShowArray = (LinkedList<ActivityToDo>)activityGameArray.clone();
        }
        else
        {
            //On mélange et on prend les NB_ACTIVITIES_TO_SHOW premiers
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
