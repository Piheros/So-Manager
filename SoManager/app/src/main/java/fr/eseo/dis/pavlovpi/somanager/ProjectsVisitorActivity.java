package fr.eseo.dis.pavlovpi.somanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
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

public class ProjectsVisitorActivity extends AppCompatActivity {

    private static final int MY_SOCKET_TIMEOUT_MS = 10000;
    private String pageType = "PORTE";
    private String url = "https://192.168.4.248/pfe/webservice.php?q=" + pageType +"&user=";

    private String userName;
    private String token;

    private String projectId;
    private String projectTitle;
    private String projectDescription;

    private RecyclerView myRecyclerView;
    private ProjectsAdapter mProjectsAdapter;
    private ArrayList<ProjectItem> mProjectList;

    private RequestQueue mRequestQueue;

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

                                String creatorName = hit.getString("idProject");
                                String type = hit.getString("title");
                                String likeCount = hit.getString("description");

                                mProjectList.add(new ProjectItem(creatorName, type, likeCount));
                            }

                            mProjectsAdapter = new ProjectsAdapter(ProjectsVisitorActivity.this, mProjectList);
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

        request.setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        mRequestQueue.add(request);
    }

    private void setUserNameandToken(){
        Intent i = getIntent();
        this.userName = i.getStringExtra("user_name");
        this.token = i.getStringExtra("token");
        Log.d("info de activity 1", "Le user name = " + this.userName);
        Log.d("info de activity 1", "Le TOKEN = " + this.token);
    }

    private void createUrlToGetAllProjects(){
        this.url = this.url + this.userName + "&token=" + this.token;
        Log.d("URL des projets", "URL = " + this.url);
    }

}



