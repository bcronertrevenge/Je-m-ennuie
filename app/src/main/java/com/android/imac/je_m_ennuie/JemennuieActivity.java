package com.android.imac.je_m_ennuie;


import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class JemennuieActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jemennuie);

        System.out.println("Debut du jeu!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("Debut du jeu!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("Debut du jeu!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        /* Loading the fonts */
        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Pacifico.ttf");

        button1.setTypeface(font);
        button2.setTypeface(font);
        button3.setTypeface(font);

        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(JemennuieActivity.this, GameDisplayActivity.class);
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("Page Mes activités");
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("Quitter");
            }
        });



        Game game = new Game();
        game.newGame();

        game.answerQuestion(Answer.Yes);
        game.answerQuestion(Answer.NoMatter);
        game.answerQuestion(Answer.No);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.jemennuie, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
