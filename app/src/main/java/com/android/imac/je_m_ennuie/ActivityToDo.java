package com.android.imac.je_m_ennuie;

/**
 * Created by bruno on 01/12/2014.
 */
public class ActivityToDo {
    String nameActivity;

    ActivityToDo(String name)
    {
        nameActivity = name;
    }

    int getImpact(Question question)
    {
        return 1;
    }

    String getNameActivity() { return nameActivity; }

    @Override
    public String toString() {
        return nameActivity;
    }
}
