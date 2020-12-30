package com.example.resultmanagement;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class View_Result extends AppCompatActivity {
    private RecyclerView recyclerView;
   private RecyclerView.Adapter adapter;
   private List<ListItem> listItems;

    private RequestQueue requestQueue;
    private String ExtractDataUrl="http://www.datamanagement.ml/fetch1.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__result);
        recyclerView=(RecyclerView)findViewById(R.id.userrecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listItems=new ArrayList<>();
        requestQueue= Volley.newRequestQueue(getApplicationContext());
        ExtractData();
    }
    private void ExtractData(){
        StringRequest stringRequest=new StringRequest(Request.Method.GET, ExtractDataUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONArray array=new JSONArray(response);
                    for (int i=0;i<array.length();i++){
                        JSONObject data=array.getJSONObject(i);
                        ListItem item=new ListItem(
                                data.getString("email"),
                                data.getString("username"),
                                data.getString("id")


                        );

                        listItems.add(item);
                        //Log.d("Data1:",""+data.get("username"));
//                        String username1=data.getString("username");
//                        String email1=data.getString("email");
//                        userName.setText(username1);
//                        email.setText(email1);
                    }
                    adapter=new UserAdapter(listItems,getApplicationContext());
                    recyclerView.setAdapter(adapter);

                }catch (Exception e){
                    Toast.makeText(View_Result.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(View_Result.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(stringRequest);

    }


}