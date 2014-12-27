package com.android.imac.je_m_ennuie;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by Marie on 26/12/2014.
 */
public class DetailedActivityActivity extends Activity implements View.OnClickListener {
    final String EXTRA_FAVORITE = "is_favorite";
    Button btn_facebook;
    Button btn_twitter;
    Button btn_gmail;
    Button btn_favorite;
    Intent intent;
    boolean is_favorite;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_activity);

        /* Récupération des éléments de la vue */
        TextView title_activity = (TextView) findViewById(R.id.title_activity_detailed);
        btn_facebook = (Button) findViewById(R.id.btn_facebook);
        btn_twitter = (Button) findViewById(R.id.btn_twitter);
        btn_gmail = (Button) findViewById(R.id.btn_gmail);
        btn_favorite = (Button) findViewById(R.id.btn_favorite);
        intent = getIntent();
        is_favorite=intent.getBooleanExtra(EXTRA_FAVORITE,false);

        /* On charge la bonne police */
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Pacifico.ttf");
        title_activity.setTypeface(font);
        btn_favorite.setTypeface(font);

        /* Changement de couleur au clic */
        btn_favorite.setBackgroundResource(R.drawable.selector);

        /* Changement du bouton favori en fonction de l'activité */
        if (is_favorite){
            btn_favorite.setText("Retirer des favoris");
        // Sinon
        }else{
            btn_favorite.setText("Ajouter aux favoris");
        }

        /* Evenements au clic */
        btn_facebook.setOnClickListener(this);
        btn_twitter.setOnClickListener(this);
        btn_gmail.setOnClickListener(this);
        btn_favorite.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==btn_facebook)
            Toast.makeText(getApplicationContext(), "Partage Facebook", Toast.LENGTH_SHORT).show();

        if(v==btn_twitter)
            Toast.makeText(getApplicationContext(), "Partage Twitter", Toast.LENGTH_SHORT).show();

        if(v==btn_gmail)
            Toast.makeText(getApplicationContext(), "Partage Google", Toast.LENGTH_SHORT).show();

        if(v==btn_favorite)
            if(is_favorite){
                btn_favorite.setText("Ajouter aux favoris");
                is_favorite=false;
                //fonction pour retirer des favoris
            }else{
                btn_favorite.setText("Retirer des favoris");
                is_favorite=true;
                //fonction pour ajouter des favoris
            }

    }
}
