/**
 * @file DetailsActivity.java
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
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import fr.eseo.dis.pavlovpi.somanager.data.ProjectMemberItem;
import fr.eseo.dis.pavlovpi.somanager.data.adapters.ProjectMemberAdapter;

/**
 * @class Details Activity
 * @brief Detailed description of the projects
 */
public class DetailsActivity extends AppCompatActivity{

    private static final String TAG = DetailsActivity.class.getSimpleName();

    private String pageType = "POSTR";
    private String url = "https://192.168.4.248/pfe/webservice.php?q=" + pageType +"&user=";
    private String userName;
    private String token;
    private String titleProject;
    private String supervisorName;
    private String studentsMembers;
    private String individualStudentName;
    private String projectDescription;
    private String projectId;

    private Button btnDetails;
    private Button btnCommentaire;
    private Button btnEvaluation;

    private TextView titre;
    private TextView supervisorNameTextView;
    private TextView projectDescriptionView;
    private ImageView mImageView;

    private ArrayList<String> userIdList;
    private ArrayList<String> userForenameList;
    private ArrayList<String> userSurnameList;
    private RecyclerView myRecyclerView;
    private ProjectMemberAdapter mProjectMemberAdapter;
    private ArrayList<ProjectMemberItem> mProjectMemberList;

    /**
     * @fn onCreateView
     * @brief creates and returns the view hierarchy associated with the fragment
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        myRecyclerView = findViewById(R.id.project_member_name_view);
        myRecyclerView.setHasFixedSize(true);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mProjectMemberList = new ArrayList<>();

        userIdList = new ArrayList<>();
	userForenameList = new ArrayList<>();
        userSurnameList = new ArrayList<>();

        getViews();
        getValuesIntent();

        createUrlToGetPosterThumb();
        parseJson();

        Picasso.with(DetailsActivity.this).load(this.url).placeholder( R.drawable.progress_animation ).into(mImageView);
        setListeners();
    }

    /**
     * @fn getViews
     * @brief retrieve views
     */
    private void getViews() {
        btnDetails = findViewById(R.id.button_details);
        btnCommentaire = findViewById(R.id.button_commentaire);
        btnEvaluation = findViewById(R.id.button_evaluation);
        titre = findViewById(R.id.detail_project_title);
        supervisorNameTextView = findViewById(R.id.detail_project_supervisor_name);
        projectDescriptionView = findViewById(R.id.jury_project_description_view);
        mImageView = findViewById(R.id.poster_project_view);
    }

    /**
     * @fn getValueIntent
     * @brief recovery from the web service information needed to display
     */
    private void getValuesIntent() {
        Intent i = getIntent();
        userName = i.getStringExtra("user_name");
        token = i.getStringExtra("token");
        titleProject = i.getStringExtra("project_title");
        supervisorName = i.getStringExtra("supervisor_name");
        studentsMembers = i.getStringExtra("students_member");
        projectDescription = i.getStringExtra("project_description");
        projectId = i.getStringExtra("project_id");
    }

    /**
     * @fn parseJson
     * @brief splitting the messages to retrieve the necessary data
     */
    private void parseJson() {
        try {
            JSONObject studentsJsonObject = new JSONObject(studentsMembers);
            JSONArray studentsJsonArray = studentsJsonObject.getJSONArray("students");

            for (int j = 0; j < studentsJsonArray.length(); j++) {
                JSONObject studentsArrayObject = studentsJsonArray.getJSONObject(j);

                String studentForename = studentsArrayObject.getString("forename");
                String studentSurname = studentsArrayObject.getString("surname");
                String userId = studentsArrayObject.getString("userId");

                this.userIdList.add(userId);
		this.userForenameList.add(studentForename);
                this.userSurnameList.add(studentSurname);

                individualStudentName = studentForename + " " + studentSurname;
                mProjectMemberList.add(new ProjectMemberItem(individualStudentName));
            }

            mProjectMemberAdapter = new ProjectMemberAdapter(DetailsActivity.this, mProjectMemberList);
            myRecyclerView.setAdapter(mProjectMemberAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        titre.setText(titleProject);
        supervisorNameTextView.setText("Superviseur : " + supervisorName);
        projectDescriptionView.setText(projectDescription);
    }

    /**
     * @fn create url
     * @brief creation of the data recovery url
     */
    private void createUrlToGetPosterThumb(){
        this.url = this.url + this.userName + "&proj=" + projectId + "&style=FULL" + "&token=" + this.token;
        Log.d(TAG + " -- URL de ProjectThumb", "URL = " + this.url);
    }

    /**
     * @fn setListeners
     * @brief initialize connection to a web service
     */
    private void setListeners() {
        btnDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailsActivity.this, NotationActivity.class);
                intent.putExtra("user_name", userName);
                intent.putExtra("token", token);
                intent.putExtra("project_id",projectId);
                startActivity(intent);
            }
        });

        btnCommentaire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailsActivity.this, CommentaireActivity.class);
                startActivity(intent);
            }
        });

        btnEvaluation.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(DetailsActivity.this, EvaluationActivity.class);
                        intent.putExtra("user_name", userName);
                        intent.putExtra("token", token);
                        intent.putExtra("project_id",projectId);
                        intent.putStringArrayListExtra("student_id_list", userIdList);
			intent.putStringArrayListExtra("student_forename_list", userForenameList);
                        intent.putStringArrayListExtra("student_surname_list", userSurnameList);
                        startActivity(intent);
                    }
        });

        btnCommentaire.setVisibility(View.VISIBLE);
    }

}
