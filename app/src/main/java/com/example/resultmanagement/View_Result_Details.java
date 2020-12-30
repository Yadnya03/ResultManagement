package com.example.resultmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class View_Result_Details extends AppCompatActivity {
    String username,email,id;
    public Button delete;
    public TextView mothername2,seatno2,studname,subj1,subj2,subj3,subj4,subj5,staus3,percen;
    private RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__result__details);
        Intent intent=getIntent();
        username=intent.getStringExtra("username");
        email=intent.getStringExtra("email");
        id=intent.getStringExtra("post_id");
        delete=(Button)findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete1();
            }
        });
        mothername2=(TextView)findViewById(R.id.moname);
        seatno2=(TextView)findViewById(R.id.seno);
        studname=(TextView)findViewById(R.id.studname);
        staus3=(TextView)findViewById(R.id.status1);
        percen=(TextView)findViewById(R.id.percent1);
        subj1=(TextView)findViewById(R.id.sub1);
        subj2=(TextView)findViewById(R.id.sub2);
        subj3=(TextView)findViewById(R.id.sub3);
        subj4=(TextView)findViewById(R.id.sub4);
        subj5=(TextView)findViewById(R.id.sub5);
        requestQueue= Volley.newRequestQueue(getApplicationContext());
        showData1();
        Toast.makeText(this, id, Toast.LENGTH_SHORT).show();
    }
    private void delete1() {
        String ExtractDataUrl = "http://www.resultmanagement.tk/delete1.php?id="+id;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, ExtractDataUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("Data Deleted Succsesfully")) {
                  Intent intent=new Intent(View_Result_Details.this,ViewButtons.class);
                  startActivity(intent);
                    //  Toast.makeText(View_Result_Details.this, "Please Enter Correct Mother name or Password", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(View_Result_Details.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(stringRequest);


    }

    private void showData1() {
            String ExtractDataUrl = "http://www.datamanagement.ml/idfetch1.php?id="+id;
            StringRequest stringRequest = new StringRequest(Request.Method.POST, ExtractDataUrl, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equals("[]")) {
                        Toast.makeText(View_Result_Details.this, "Please Enter Correct Mother name or Password", Toast.LENGTH_SHORT).show();
                    } else {
                        try {

                            //  Toast.makeText(ViewResult.this, ""+response, Toast.LENGTH_SHORT).show();
                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject data = array.getJSONObject(i);
                                //String moname= String.valueOf(data.get("mothername"));
                                mothername2.setText((String) data.get("mothername"));
                                seatno2.setText((String) data.get("seatno"));
                                studname.setText((String) data.get("username"));
                                subj1.setText((String) data.get("subject1"));
                                subj2.setText((String) data.get("subject2"));
                                subj3.setText((String) data.get("subject3"));
                                subj4.setText((String) data.get("subject4"));
                                subj5.setText((String) data.get("subject5"));
                                percen.setText((String)data.get("percentage"));
                                staus3.setText((String)data.get("result"));
                                //Toast.makeText(ViewResult.this, moname, Toast.LENGTH_SHORT).show();
//                        ListItem item=new ListItem(
//                                data.getString("email"),
//                                data.getString("username"),
//                                data.getString("id")
//
//
//                        );
                                //selectedValues.add(String.valueOf(item));
                            }

                        } catch (JSONException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(View_Result_Details.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });


            requestQueue.add(stringRequest);


    }
}