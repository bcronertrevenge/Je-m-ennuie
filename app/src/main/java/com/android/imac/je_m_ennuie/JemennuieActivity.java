package com.android.imac.je_m_ennuie;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class JemennuieActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jemennuie);

        System.out.println("Debut du jeu!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("Debut du jeu!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("Debut du jeu!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");


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
