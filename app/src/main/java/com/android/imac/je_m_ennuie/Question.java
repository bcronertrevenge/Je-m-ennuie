package com.android.imac.je_m_ennuie;

/**
 * Created by bruno on 01/12/2014.
 */
public class Question {
    String nameQuestion;

    Question(String name)
    {
        nameQuestion = name;
    }

    String getNameQuestion()
    {
        return nameQuestion;
    }

    @Override
    public String toString() {
        return nameQuestion;
    }
}
