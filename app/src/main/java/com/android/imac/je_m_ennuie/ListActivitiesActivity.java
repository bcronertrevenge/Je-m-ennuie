package com.android.imac.je_m_ennuie;

import android.app.Activity;
import android.app.ListActivity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Marie on 24/12/2014.
 */
public class ListActivitiesActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activities);

         /* Loading the fonts */
        TextView title = (TextView) findViewById(R.id.title_activity);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Pacifico.ttf");
        title.setTypeface(font);

        final ListView listview = (ListView) findViewById(R.id.list_activity);

        // A remplacer par les vraies activités avec une boucle
        String[] values = new String[]{"Activité 1", "Activité 2", "Activité 3",
                "Activité 4", "Activité 5", "Activité 6", "Activité 7", "Activité 8",
                "Activité 9", "Activité 10", "Activité 11", "Activité 12"};

        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < values.length; ++i) {
            list.add(values[i]);
        }
        final ListActivityAdapter adapter = new ListActivityAdapter(getApplicationContext(), values);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                System.out.println("coucou");
            }

        });
    }

    private class StableArrayAdapter extends ArrayAdapter<String> {

        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(ListActivitiesActivity context, int textViewResourceId,
                                  ArrayList<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    }
}
