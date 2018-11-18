/**
 * @file NotationActivity.java
 * @version 0.2
 * @author Mehdi Bouafia & Pierre Pavlovic
 * @date 18/11/2018
 *
 * @section License
 *
 * GNU GENERAL PUBLIC LICENSE
 * Version 3, 29 June 2007
 *
 * Copyright (C) 2018  Mehdi Bouafia & Pierre Pavlovic
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 */
package fr.eseo.dis.pavlovpi.somanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import fr.eseo.dis.pavlovpi.somanager.data.NotationItem;
import fr.eseo.dis.pavlovpi.somanager.data.ProjectItem;
import fr.eseo.dis.pavlovpi.somanager.data.adapters.NotationAdapter;
import fr.eseo.dis.pavlovpi.somanager.data.adapters.ProjectsAdapter;

/**
 * @class Notation Activity
 * @brief Display all notations of project
 */
public class NotationActivity extends AppCompatActivity {

    private static final String TAG = NotationActivity.class.getSimpleName();
    private String pageType = "NOTES";
    private String url = "https://192.168.4.248/pfe/webservice.php?q=" + pageType +"&user=";
    private String userName;
    private String token;
    private String projectId;
    private RequestQueue mRequestQueue;

    private RecyclerView myRecyclerView;
    private ArrayList<NotationItem> mNotationList;
    private NotationAdapter mNotationAdapter;

    /**
     * @fn onCreateView
     * @brief creates and returns the view hierarchy associated with the fragment
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notation);
        getValuesIntent();
        createUrlToGetNotes();

        getValuesIntent();
        createUrlToGetNotes();

        myRecyclerView = findViewById(R.id.notationList);
        myRecyclerView.setHasFixedSize(true);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mNotationList = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(this);

        parseJSON();
    }

    /**
     * @fn parseJson
     * @brief splitting the messages to retrieve the necessary data
     */
    private void parseJSON() {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, this.url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d("NotationActivity", "JSON receive");
                            JSONArray jsonArray = response.getJSONArray("notes");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject hit = jsonArray.getJSONObject(i);

                                String forename = hit.getString("forename");
                                String surname = hit.getString("surname");
                                String note = hit.getString("mynote");
                                String noteAverage = hit.getString("avgNote");

                                if(note.equals("null")) {
                                    note = "N.A.N";
                                }

                                if(noteAverage.equals("null")) {
                                    noteAverage = "N.A.N";
                                }

                                mNotationList.add(new NotationItem(forename, surname, note, noteAverage));
                            }

                            mNotationAdapter = new NotationAdapter(NotationActivity.this, mNotationList);
                            myRecyclerView.setAdapter(mNotationAdapter);

                        } catch (JSONException e) {
                            Log.e("ProjectsActivity","JSON does not work");
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ProjectsActivity", "SoManager does not work");
                error.printStackTrace();
            }
        });
        mRequestQueue.add(request);
    }

    /**
     * @fn getValueIntent
     * @brief recovery from the web service information needed to display
     */
    private void getValuesIntent() {
        Intent i = getIntent();
        userName = i.getStringExtra("user_name");
        token = i.getStringExtra("token");
        projectId = i.getStringExtra("project_id");
    }

    /**
     * @fn create url
     * @brief creation of the data recovery url
     */
    private void createUrlToGetNotes(){
        this.url = this.url + this.userName + "&proj=" + projectId + "&token=" + this.token;
        Log.d(TAG + " -- URL de ProjectThumb", "URL = " + this.url);
    }

}
