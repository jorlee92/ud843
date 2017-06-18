package com.example.android.quakereport;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity {
    public static final String LOG_TAG = EarthquakeActivity.class.getName();
    private ArrayList<EarthQuake> earthquakes;

    private class EarthquakeAsyncTask extends AsyncTask<String, Void, ArrayList<EarthQuake>> {
        @Override
        protected ArrayList<EarthQuake> doInBackground(String... urls) {
            ArrayList<EarthQuake> res = null;
            if (urls.length < 1 || urls[0] == null) {
                Log.v(LOG_TAG, "Could not find a URL");
                return null;
            }
            try {
                res = QueryUtils.getEarthquakeData(urls[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return res;
        }

        protected void onPostExecute(ArrayList<EarthQuake> res) {


            // Set the adapter on the {@link ListView}
            // so the list can be populated in the user interface
            earthquakes = res;
            Log.v("Change res", "Yes");
            Log.v("q", Integer.toString(earthquakes.size()));
            updateView();
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
         * Grab the data from the background.
         */
        setContentView(R.layout.earthquake_activity);
        EarthquakeAsyncTask earthquakeAsyncTask = new EarthquakeAsyncTask();
        earthquakeAsyncTask.execute("https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10");


    }

    private void updateView() {
        /*
         * This function is here to allow the asynctask to update the UI after it is completed.
         */
        ListView earthquakeListView = (ListView) findViewById(R.id.list);
        EarthQuakeAdapter adapter = new EarthQuakeAdapter(this, R.layout.list_item, earthquakes);
        Log.v("quakes", earthquakes.toString());
        earthquakeListView.setAdapter(adapter);
    }
}
