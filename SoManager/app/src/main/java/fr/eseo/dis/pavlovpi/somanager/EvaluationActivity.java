/**
 * @file EvaluationActivity.java
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
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

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

/**
 * @class Evaluation Activity
 * @brief Evaluation of projects
 */
public class EvaluationActivity extends AppCompatActivity {

    private static final String TAG = EvaluationActivity.class.getSimpleName();

    private String pageType = "NEWNT";
    private String url = "https://192.168.4.248/pfe/webservice.php?q=" + pageType +"&user=";
    private String userName;
    private String token;
    private String projectId;

    private ArrayList<String> userIdList;
    private TextView myTextView;
    private RequestQueue mRequestQueue;

    /**
     * @fn onCreateView
     * @brief creates and returns the view hierarchy associated with the fragment
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluation);

        mRequestQueue = Volley.newRequestQueue(this);

        getValuesIntent();
        setListeners();

        myTextView = findViewById(R.id.evaluation_thingy);
        myTextView.setText(userIdList.get(0));
    }

    /**
     * @fn setJsonNote
     * @brief given a note to a project
     */
    private void setJSONNote() {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, this.url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG,"Succesfully set the note");
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
     * @fn setListeners
     * @brief initialize connection to a web service
     */
    private void setListeners() {
        for(int i = 0; i < userIdList.size(); i++) {
            this.url = this.url + this.userName + "&proj=" + projectId + "&student=" + userIdList.get(i) + "&token=" + this.token;
            Log.d(TAG, "STUDENTIDNUMBER : " + userIdList.get(i));
            Log.d(TAG + " -- URL de MyJurys", "URL = " + this.url);
            //setJSONNote();
        }
    }

    /**
     * @fn getValueIntent
     * @brief recovery from the web service information needed to display
     */
    private void getValuesIntent() {
        Intent i = getIntent();
        this.userIdList = i.getStringArrayListExtra("student_id_list");
        this.userName = i.getStringExtra("user_name");
        this.token = i.getStringExtra("token");
        this.projectId = i.getStringExtra("project_id");
    }

    /**
     * @fn create url
     * @brief creation of the data recovery url
     */
    private void createURLToSetNote(String studentId){
        this.url = this.url + this.userName + "&proj=" + projectId + "&student=" + studentId + "&note=" + "4" + "&token=" + this.token;
        Log.d(TAG + " -- URL de MyJurys", "URL = " + this.url);
    }

}
