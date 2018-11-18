/**
 * @file MyJuryActivity.java
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
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

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

import fr.eseo.dis.pavlovpi.somanager.data.JuryItem;
import fr.eseo.dis.pavlovpi.somanager.data.adapters.JuryAdapter;

/**
 * @class My Jury Activity
 * @brief Shows all the jury of the user who is connected
 */
public class MyJuryActivity extends AppCompatActivity {

    private static final String TAG = MyJuryActivity.class.getSimpleName();
    private String pageType = "MYJUR";
    private String url = "https://192.168.4.248/pfe/webservice.php?q=" + pageType +"&user=";
    private String pageType2 = "JYINF";
    private String url2 = "https://192.168.4.248/pfe/webservice.php?q=" + pageType2 +"&user=";
    private String userName;
    private String token;
    private String titleProject;
    private String supervisorName;
    private String date;
    private String juryNumber;

    private FloatingActionButton buttonToProject;
    private RecyclerView myRecyclerView;
    private JuryAdapter mJuryAdapter;
    private ArrayList<JuryItem> mJuryList;
    private RequestQueue mRequestQueue;

    /**
     * @fn onCreateView
     * @brief creates and returns the view hierarchy associated with the fragment
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_somanager);

        buttonToProject = findViewById(R.id.fab);

        callButtonListener();
        setUserNameandToken();
        createUrlToGetMyJurys();

        myRecyclerView = findViewById(R.id.JuryList);
        myRecyclerView.setHasFixedSize(true);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mJuryList = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(this);

        getJSONMyJuries();
    }

    /**
     * @fn get Json
     * @brief splitting the messages to retrieve the necessary data
     */
    private void getJSONMyJuries() {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, this.url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d(TAG, "JSON receive");
                            JSONArray responseArray = response.getJSONArray("juries");

                            for (int i = 0; i < responseArray.length(); i++) {
                                JSONObject hit = responseArray.getJSONObject(i);

                                juryNumber = hit.getString("idJury");
                                date = hit.getString("date");

                            }
                            // Calling a second connection within the first because
                            // Volley in Async in order to do one THEN the other
                            createUrlToGetMyJurysWithMoreInfos();
                            getJSONMyJurysWithMoreInfos();

                        } catch (JSONException e) {
                            Log.e(TAG,"JSON does not work");
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "SoManager does not work");
                error.printStackTrace();
            }
        });
        mRequestQueue.add(request);
    }

    /**
     * @fn get Json with detail information
     * @brief splitting the messages to retrieve the necessary data
     */
    private void getJSONMyJurysWithMoreInfos() {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, this.url2, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray projectsArray = response.getJSONArray("projects");

                            for (int j = 0; j < projectsArray.length(); j++) {
                                JSONObject projectsObject = projectsArray.getJSONObject(j);
                                JSONObject supervisor = projectsObject.getJSONObject("supervisor");

                                String supervisorForename = supervisor.getString("forename");
                                String supervisorSurname = supervisor.getString("surname");
                                String students = projectsObject.getString("students");
                                String titleProject = projectsObject.getString("title");
                                String description = projectsObject.getString("descrip");
                                String projectId = projectsObject.getString("projectId");

                                supervisorName = supervisorForename + " " + supervisorSurname;
                                mJuryList.add(new JuryItem(juryNumber, date, titleProject, supervisorName, students, description, projectId));
                            }

                            mJuryAdapter = new JuryAdapter(MyJuryActivity.this, mJuryList);
                            myRecyclerView.setAdapter(mJuryAdapter);

                        } catch (JSONException e) {
                            Log.e(TAG,"JSON 2 does not work");
                            e.printStackTrace();
                        }
                    }
                    }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "SoManager does not work");
                error.printStackTrace();
            }
        });
        mRequestQueue.add(request);
    }

    /**
     * @fn set User and token
     * @brief definition of the token and the user
     */
    private void setUserNameandToken(){
        Intent i = getIntent();
        this.userName = i.getStringExtra("user_name");
        this.token = i.getStringExtra("token");
        Log.d(TAG, "Le user name = " + this.userName);
        Log.d(TAG, "Le TOKEN = " + this.token);
    }

    /**
     * @fn create url
     * @brief creation of the data recovery url
     */
    private void createUrlToGetMyJurys(){
        this.url = this.url + this.userName + "&token=" + this.token;
        Log.d(TAG + " -- URL de MyJurys", "URL = " + this.url);
    }

    /**
     * @fn create url with more information
     * @brief creation of the data recovery url
     */
    private void createUrlToGetMyJurysWithMoreInfos() {
        this.url2 = this.url2 + this.userName + "&jury=" + this.juryNumber + "&token=" + this.token;
        Log.d(TAG + " -- JurysWithMoreInfos", "URL2 = " + this.url2);
    }

    /**
     * @fn click button
     * @brief when clicking on the project this one send different information
     */
    private void callButtonListener(){
        buttonToProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MyJuryActivity.this, ProjectsActivity.class);
                myIntent.putExtra("user_name",userName);
                myIntent.putExtra("token",token);
                startActivity(myIntent);
            }
        });
    }

    /**
     * @fn click on jury card
     * @brief when clicking on a jury, it sends the information to details which displays them
     * @param currentJury
     * @param supervisorName
     * @param students
     * @param description
     * @param projectId
     */
    public void clickJuryCard(String currentJury, String supervisorName, String students, String description, String projectId) {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra("supervisor_name", supervisorName);
        intent.putExtra("user_name",userName);
        intent.putExtra("token",token);
        intent.putExtra("project_title", currentJury);
        intent.putExtra("project_description", description);
        intent.putExtra("project_id", projectId);
        intent.putExtra("students_member", "{ \"students\": " +students+ "}");
        startActivity(intent);
    }
}
