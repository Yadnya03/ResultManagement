package com.example.resultmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextClock;
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

import java.util.ArrayList;
import java.util.List;

public class ViewResult extends AppCompatActivity {
    private EditText mothername1,seatno1;
    private Button showresult;
    public TextView mothername2,seatno2,studname,subj1,subj2,subj3,subj4,subj5,percentage3,status3;
    private RequestQueue requestQueue;
    List<String> selectedlist;
    public List<String> selectedValues=new ArrayList<String>();
    public LinearLayout linearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_result);
        mothername1=(EditText)findViewById(R.id.resmothername);
        seatno1=(EditText)findViewById(R.id.resseatno);
        showresult=(Button)findViewById(R.id.showresult);
        percentage3=(TextView)findViewById(R.id.percent2);
        status3=(TextView)findViewById(R.id.result1);
        linearLayout=(LinearLayout)findViewById(R.id.linearlayout);
        mothername2=(TextView)findViewById(R.id.moname);
        seatno2=(TextView)findViewById(R.id.seno);
        studname=(TextView)findViewById(R.id.studname);
        subj1=(TextView)findViewById(R.id.sub1);
        subj2=(TextView)findViewById(R.id.sub2);
        subj3=(TextView)findViewById(R.id.sub3);
        subj4=(TextView)findViewById(R.id.sub4);
        subj5=(TextView)findViewById(R.id.sub5);
        requestQueue= Volley.newRequestQueue(getApplicationContext());
        showresult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showData();
            }
        });
    }


    private void showData() {

        String mother=mothername1.getText().toString();
        String seatn=seatno1.getText().toString();
        if(!TextUtils.isEmpty(mother)&&!TextUtils.isEmpty(seatn)) {
            String ExtractDataUrl = "http://www.datamanagement.ml/display1.php?mothername=" + mother + "&seatno=" + seatn;
            StringRequest stringRequest = new StringRequest(Request.Method.POST, ExtractDataUrl, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equals("[]")) {
                        Toast.makeText(ViewResult.this, "Please Enter Correct Mother name or Password", Toast.LENGTH_SHORT).show();
                    } else {
                        try {
                            linearLayout.setVisibility(View.VISIBLE);
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
                                percentage3.setText((String) data.get("percentage"));
                                status3.setText((String) data.get("result"));
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
                    Toast.makeText(ViewResult.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });


            requestQueue.add(stringRequest);
        }
        else{
            Toast.makeText(this, "Please Enter Mother's name and Seat No", Toast.LENGTH_SHORT).show();
        }

    }

    public List<String> listofselectedactivites(){
        return selectedValues;
    }
}