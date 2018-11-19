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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import fr.eseo.dis.pavlovpi.somanager.data.EvaluationItem;
import fr.eseo.dis.pavlovpi.somanager.data.adapters.EvaluationAdapter;

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
    private String note;

    private ArrayList<String> userIdList;
    private ArrayList<String> userForenameList;
    private ArrayList<String> userSurnameList;

    private EditText myEditText;
    private EditText groupNoteEditText;

    private RecyclerView myRecyclerView;
    private EvaluationAdapter mEvaluationAdapter;
    private ArrayList<EvaluationItem> mEvaluationList;

    private RequestQueue mRequestQueue;

    private Button buttonSameNote;
    private Button buttonToNotes;

    /**
     * @fn onCreateView
     * @brief creates and returns the view hierarchy associated with the fragment
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluation);

        myRecyclerView = findViewById(R.id.EvaluationList);
        myRecyclerView.setHasFixedSize(true);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mRequestQueue = Volley.newRequestQueue(this);

        mEvaluationList = new ArrayList<>();

        getViews();
        getValuesIntent();
        loadStudents();

        setListeners();
    }

    private void getViews() {
        buttonSameNote = findViewById(R.id.evaluation_button);
        buttonToNotes = findViewById(R.id.evaluation_to_note_button);
        myEditText = findViewById(R.id.evaluation_note);
        groupNoteEditText = findViewById(R.id.evaluation_group_note);
    }

    private void setListeners() {
        buttonSameNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    setUrlForAllStudentsNotes();
                } catch (Exception e) {
                    Log.e(TAG, "value is null");
                }
            }
        });
        buttonToNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EvaluationActivity.this, NotationActivity.class);
                intent.putExtra("user_name", userName);
                intent.putExtra("token", token);
                intent.putExtra("project_id",projectId);
                startActivity(intent);
            }
        });
    }

    private void loadStudents() {
        for(int i = 0; i < userIdList.size(); i++) {
            String id = userIdList.get(i);
            String forename = userForenameList.get(i);
            String surname = userSurnameList.get(i);

            mEvaluationList.add(new EvaluationItem(forename, surname));
        }

        mEvaluationAdapter = new EvaluationAdapter(EvaluationActivity.this, mEvaluationList);
        myRecyclerView.setAdapter(mEvaluationAdapter);
    }

    /**
     * @fn setJsonNote
     * @brief given a note to a project
     */
    private void setJSONNote(String url) {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String result = response.getString("result");
                            if(result.equals("OK")){
                                Log.d(TAG,"Succesfully set the note");
                            } else {
                                Log.e(TAG, "problem in setting note");
                            }
                        } catch (JSONException e) {
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
     * @fn setListeners
     * @brief initialize connection to a web service
     */
    private void setUrlForAllStudentsNotes() {
        this.note = groupNoteEditText.getText().toString();
        for(int i = 0; i < mEvaluationList.size(); i++) {
            String url = this.url + this.userName + "&proj=" + projectId + "&student=" + userIdList.get(i) + "&note=" + this.note + "&token=" + this.token;
            setJSONNote(url);
            Log.d(TAG, "STUDENTIDNUMBER : " + userIdList.get(i));
            Log.d(TAG + " -- URL de MyJurys", "URL = " + url);
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
        this.userForenameList = i.getStringArrayListExtra("student_forename_list");
        this.userSurnameList = i.getStringArrayListExtra("student_surname_list");
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