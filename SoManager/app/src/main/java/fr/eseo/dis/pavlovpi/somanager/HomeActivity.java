/**
 * @file HomeActivity.java
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
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static fr.eseo.dis.pavlovpi.somanager.CommentaireActivity.RED_BLOCK_SIZE;

/**
 * @class Home Activity
 * @brief Initialize connection and display to option Normal or Visitor
 */
public class HomeActivity extends AppCompatActivity {

    private Button btnVisitor;
    private Button btnTestConnection;

    private String userToken;
    private static final String URLJPO = "https://192.168.4.248/pfe/webservice.php?q=LOGON&user=jpo&pass=Lsm5hs51s9ks";

    private RequestQueue mRequestQueue;

    /**
     * @fn onCreateView
     * @brief creates and returns the view hierarchy associated with the fragment
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        SSL_Activity.nuke();

        btnVisitor = findViewById(R.id.button_visitor);
        btnTestConnection = findViewById(R.id.button_connection);
        mRequestQueue = Volley.newRequestQueue(this);

        try {
            FileInputStream fileInputStream = openFileInput("dataProject.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            char[] iChars = new char[RED_BLOCK_SIZE];
            String s = "";
            int charRead;

            while ((charRead = inputStreamReader.read(iChars))>0) {
                String readString = String.copyValueOf(iChars,0,charRead);
                s += readString;
                iChars = new char[RED_BLOCK_SIZE];
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        btnVisitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URLJPO, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    Log.d("SoManagerActivityBeta", "JSON receive");
                                    Log.d("SoManagerActivityBeta","JSON result = " + response.getString("result"));
                                    userToken = response.getString("token");
                                    Log.d("SoManagerActivityBeta","LE TOKEN HOME = " + userToken);

                                    Intent myIntent = new Intent(HomeActivity.this, ProjectsVisitorActivity.class);
                                    myIntent.putExtra("user_name","jpo");
                                    myIntent.putExtra("token",userToken);
                                    startActivity(myIntent);

                                } catch (JSONException e) {
                                    Log.e("SoManagerActivityBeta","JSON does not work");
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("SoManagerActivityBeta", "SoManager does not work");
                        error.printStackTrace();
                    }
                });

                mRequestQueue.add(request);
            }
        });

        btnTestConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(HomeActivity.this, ConnectionActivity.class);
                startActivity(myIntent);
            }
        });

        btnVisitor.setVisibility(View.VISIBLE);
        btnTestConnection.setVisibility(View.VISIBLE);
    }

}
