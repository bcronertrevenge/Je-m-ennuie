package com.android.imac.je_m_ennuie;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Marie on 23/12/2014.
 */
public class GameDisplayActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_display);

         /* Loading the fonts */
        TextView question = (TextView) findViewById(R.id.question);
        TextView number_question = (TextView) findViewById(R.id.number_question);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Pacifico.ttf");

        question.setTypeface(font);
        number_question.setTypeface(font);
    }
}
