package com.android.imac.je_m_ennuie;

import java.io.Serializable;

/**
 * Created by bruno on 01/12/2014.
 */
public class Question implements Serializable {
    String nameQuestion;
    int id;

    Question(String name)
    {
        nameQuestion = name;
    }

    String getNameQuestion()
    {
        return nameQuestion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return nameQuestion;
    }
}
