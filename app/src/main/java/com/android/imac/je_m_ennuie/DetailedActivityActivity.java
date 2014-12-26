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
public class DetailedActivityActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_activity);

         /* Loading the fonts */
        TextView title_activity = (TextView) findViewById(R.id.title_activity_detailed);
        Button btn_facebook = (Button) findViewById(R.id.btn_facebook);
        Button btn_twitter = (Button) findViewById(R.id.btn_twitter);
        Button btn_gmail = (Button) findViewById(R.id.btn_gmail);
        Button btn_favorite = (Button) findViewById(R.id.btn_favorite);

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Pacifico.ttf");

        title_activity.setTypeface(font);
        btn_favorite.setTypeface(font);

        /* Click events */
        btn_facebook.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Partage Facebook", Toast.LENGTH_SHORT).show();
            }
        });

        btn_twitter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Partage Twitter", Toast.LENGTH_SHORT).show();
            }
        });

        btn_gmail.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Partage Google", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
