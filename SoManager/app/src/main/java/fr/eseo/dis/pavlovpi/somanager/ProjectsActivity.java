/**
 * @file ProjectsActivity.java
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

import fr.eseo.dis.pavlovpi.somanager.data.ProjectItem;
import fr.eseo.dis.pavlovpi.somanager.data.adapters.ProjectsAdapter;

/**
 * @class Projects Activity
 * @brief Show all the projects of the school
 */
public class ProjectsActivity extends AppCompatActivity {

    private String pageType = "LIPRJ";
    private String url = "https://192.168.4.248/pfe/webservice.php?q=" + pageType +"&user=";
    private String userName;
    private String token;

    private RecyclerView myRecyclerView;
    private ProjectsAdapter mProjectsAdapter;
    private ArrayList<ProjectItem> mProjectList;
    private RequestQueue mRequestQueue;

    /**
     * @fn onCreateView
     * @brief creates and returns the view hierarchy associated with the fragment
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);

        setUserNameandToken();
        createUrlToGetAllProjects();

        myRecyclerView = findViewById(R.id.ProjectList);
        myRecyclerView.setHasFixedSize(true);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mProjectList = new ArrayList<>();

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
                            Log.d("ProjectsActivity", "JSON receive");
                            JSONArray jsonArray = response.getJSONArray("projects");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject hit = jsonArray.getJSONObject(i);

                                String creatorName = hit.getString("projectId");
                                String type = hit.getString("title");
                                String likeCount = hit.getString("descrip");

                                mProjectList.add(new ProjectItem(creatorName, type, likeCount));
                            }

                            mProjectsAdapter = new ProjectsAdapter(ProjectsActivity.this, mProjectList);
                            myRecyclerView.setAdapter(mProjectsAdapter);

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
     * @fn setUsername and token
     * @brief define the user and the associated token
     */
    private void setUserNameandToken(){
        Intent i = getIntent();
        this.userName = i.getStringExtra("user_name");
        this.token = i.getStringExtra("token");
        Log.d("info de activity 1", "Le user name = " + this.userName);
        Log.d("info de activity 1", "Le TOKEN = " + this.token);
    }

    /**
     * @fn create url
     * @brief creation of the data recovery url
     */
    private void createUrlToGetAllProjects(){
        this.url = this.url + this.userName + "&token=" + this.token;
        Log.d("URL des projets", "URL = " + this.url);
    }

}


