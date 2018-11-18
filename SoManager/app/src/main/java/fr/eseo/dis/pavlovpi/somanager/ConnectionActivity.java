/**
 * @file ConnectionActivity.java
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

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @class Connection Activity
 * @brief Connection to a web service
 */
public class ConnectionActivity extends AppCompatActivity {

    Button btnGetRequest, btnPostRequest;

    EditText textUserName;
    EditText textPassword;

    private String userName;
    private String password;
    private String pageType = "LOGON";
    private String baseUrl = "https://192.168.4.248/pfe/webservice.php?q=" + pageType +"&user=";
    private String url;
    private String result;
    private String api;
    private String token;

    private RequestQueue mRequestQueue;
    private Context mContext;
    private Activity mActivity;
    private PopupWindow mPopupWindow;
    private LinearLayout mLinearLayout;

    /**
     * @fn onCreateView
     * @brief creates and returns the view hierarchy associated with the fragment
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);

        // Volley
        SSL_Activity.nuke();
        mRequestQueue = Volley.newRequestQueue(this);
        init();
    }

    /**
     * @fn init
     * @brief initialize view and listeners
     */
    private void init() {
        getViews();
        setListeners();
    }

    /**
     * @fn getViews
     * @brief retrieve views
     */
    @SuppressLint("CutPasteId")
    private void getViews() {
        btnGetRequest = findViewById(R.id.connexion_button);
        btnPostRequest = findViewById(R.id.connexion_button);
        textUserName = findViewById(R.id.user_name);
        textPassword = findViewById(R.id.password);
    }

    /**
     * @fn setVListeners
     * @brief initialize connection to a web service
     */
    private void setListeners() {
        mContext = getApplicationContext();
        mActivity = ConnectionActivity.this;
        mLinearLayout = findViewById(R.id.linearLayoutConnection);

        btnGetRequest.setOnClickListener(new View.OnClickListener() {
            //EditText textTest = findViewById(R.id.user_name);
            @Override
            public void onClick(View v) {
                // TODO if you want to use textView
                //textUserName.getText().toString();
                userName = "chavijer";
                url = baseUrl + userName;

                // TODO if you want to use textView
                //textPassword.getText().toString();
                url = url + "&pass=";
                password = "78MYzDuUcGYh";
                url = url + password;

                Log.d("TextConnectionURL = ", url);

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Log.d("TestConnectionDelta", "connexion Volley OK");
                                try {
                                    result = response.getString("result");
                                    api = response.getString("api");
                                    token = response.getString("token");

                                    if(result.equals("OK")){
                                        Log.d("TestConnectionDelta","checkResult est true");
                                        Intent myIntent = new Intent(ConnectionActivity.this, MyJuryActivity.class);
                                        myIntent.putExtra("user_name",userName);
                                        myIntent.putExtra("token",token);
                                        startActivity(myIntent);
                                    } else {
                                        Log.e("TestConnectionDelta","checkResult est FALSE");
                                    }
                                    Log.d("TestConnectionDelta", "Le token = " + token);
                                } catch (JSONException e) {
                                    Log.e("TestConnectionDelta","JSON a pas marché");
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
                        assert inflater != null;
                        @SuppressLint("InflateParams") View customView = inflater.inflate(R.layout.connection_popup,null);

                        mPopupWindow = new PopupWindow(
                                customView,
                                RelativeLayout.LayoutParams.WRAP_CONTENT,
                                RelativeLayout.LayoutParams.WRAP_CONTENT
                        );

                        if(Build.VERSION.SDK_INT>=21){
                            mPopupWindow.setElevation(5.0f);
                        }

                        mPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        mPopupWindow.setTouchInterceptor(new View.OnTouchListener() {
                            @SuppressLint("ClickableViewAccessibility")
                            public boolean onTouch(View v, MotionEvent event) {
                                if(event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                                    mPopupWindow.dismiss();
                                    return true;
                                }
                                return false;
                            }
                        });

                        mPopupWindow.setOutsideTouchable(true);
                        mPopupWindow.showAtLocation(mLinearLayout, Gravity.TOP,0,360);

                        Log.e("TestConnectionDelta", "Connexion VolleyError");
                        error.printStackTrace();
                    }
                });
                mRequestQueue.add(request);
            }
        });
    }
}
