package com.android.imac.je_m_ennuie;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Marie on 26/12/2014.
 */
public class DetailedActivityActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_activity);

         /* Loading the fonts */
        TextView title_activity = (TextView) findViewById(R.id.text_activity);

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Pacifico.ttf");

        title_activity.setTypeface(font);
    }
}
