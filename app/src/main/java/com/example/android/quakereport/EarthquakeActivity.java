/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity {
    public static final String LOG_TAG = EarthquakeActivity.class.getName();
    private ArrayList<EarthQuake> earthquakes;
    private class EarthquakeAsyncTask extends AsyncTask<String, Void, ArrayList<EarthQuake>> {
        @Override
        protected ArrayList<EarthQuake> doInBackground(String... urls) {
            ArrayList <EarthQuake> res = null;
            if (urls.length < 1 || urls[0] == null) {
                Log.v("Failed to find URL", "FAIL");
                return null;
                    }
            try {
                res = QueryUtils.getEarthquakeData(urls[0]);
                Log.v("SET RES","SUCCESSFUL");
                } catch (IOException e) {

                e.printStackTrace();
                Log.v("SET RES", "EXCEPT");
                }
                //Log.v("Earthquake size", Integer.toString(res.size()));
            return res;
        }
        protected void onPostExecute(ArrayList<EarthQuake> res){
//            ListView earthquakeListView = (ListView) findViewById(R.id.list);
//            EarthQuakeAdapter adapter = new EarthQuakeAdapter(EarthquakeActivity.this, R.layout.list_item,earthquakes);

            // Set the adapter on the {@link ListView}
            // so the list can be populated in the user interface
           earthquakes = res;
        }

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        //probably pointless
        ////earthquakes = new ArrayList<EarthQuake>();
        earthquakes = QueryUtils.extractEarthquakes();

        ListView earthquakeListView = (ListView) findViewById(R.id.list);
        EarthquakeAsyncTask earthquakeAsyncTask = new EarthquakeAsyncTask();
        earthquakeAsyncTask.execute("http://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10");
        EarthQuakeAdapter adapter = new EarthQuakeAdapter(this, R.layout.list_item,earthquakes);
        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(adapter);
    }
    private void setAdapter(ListView v, ListAdapter adapter){
        v.setAdapter(adapter);
    }
}
