package com.example.resultmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class AddResult extends AppCompatActivity {
    private Button getData,calculate;
    private EditText username,email,seatno,mothername,subject1,subject2,subject3,subject4,subject5,percentage,status;
    private String sendUrl="http://www.datamanagement.ml/get1.php";
    private RequestQueue requestQueue;
    private  static  final  String TAG=View_Result.class.getSimpleName();
    int sucess;
    private String TAG_SUCESS="sucess";
    private String TAG_MESSAGE="message";
    private String tag_json_obj="json_obj_req";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_result);
        getData=(Button)findViewById(R.id.btn_submit);
        username=(EditText)findViewById(R.id.name);
        email=(EditText)findViewById(R.id.result);
        seatno=(EditText)findViewById(R.id.seatno);
        calculate=(Button)findViewById(R.id.calculate);
        mothername=(EditText)findViewById(R.id.mothername);
        subject1=(EditText)findViewById(R.id.subject1);
        subject2=(EditText)findViewById(R.id.subject2);
        subject3=(EditText)findViewById(R.id.subject3);
        subject4=(EditText)findViewById(R.id.subject4);
        subject5=(EditText)findViewById(R.id.subject5);
        percentage=(EditText)findViewById(R.id.percentage);
        status=(EditText)findViewById(R.id.status);

        requestQueue= Volley.newRequestQueue(getApplicationContext());
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email1=email.getText().toString();
                String username1=username.getText().toString();
                String mothersname1=mothername.getText().toString();
                String seatno1=seatno.getText().toString();
                String subjec1=subject1.getText().toString();
                String subjec2=subject2.getText().toString();
                String subjec3=subject3.getText().toString();
                String subjec4=subject4.getText().toString();
                String subjec5=subject5.getText().toString();
                if(!TextUtils.isEmpty(email1)&&!TextUtils.isEmpty(username1)&&!TextUtils.isEmpty(mothersname1)&&!TextUtils.isEmpty(seatno1)
                        &&!TextUtils.isEmpty(subjec1)&&!TextUtils.isEmpty(subjec2)&&!TextUtils.isEmpty(subjec3)&&!TextUtils.isEmpty(subjec4)&&!TextUtils.isEmpty(subjec5)) {
                    calculatepercentage();

                }
                else{
                    Toast.makeText(AddResult.this, "Please Enter all the data", Toast.LENGTH_SHORT).show();
                }
            }
        });
        getData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email2=email.getText().toString();
                String username2=username.getText().toString();
                String mothersname2=mothername.getText().toString();
                String seatno2=seatno.getText().toString();
                String subjec12=subject1.getText().toString();
                String subjec22=subject2.getText().toString();
                String subjec32=subject3.getText().toString();
                String subjec42=subject4.getText().toString();
                String subjec52=subject5.getText().toString();
                String result=status.getText().toString();
                String percentage1=percentage.getText().toString();
                if(!TextUtils.isEmpty(email2)&&!TextUtils.isEmpty(username2)&&!TextUtils.isEmpty(mothersname2)&&!TextUtils.isEmpty(seatno2)
                        &&!TextUtils.isEmpty(subjec12)&&!TextUtils.isEmpty(subjec22)&&!TextUtils.isEmpty(subjec32)
                        &&!TextUtils.isEmpty(subjec52)&&!TextUtils.isEmpty(subjec42)&&!TextUtils.isEmpty(result)&&!TextUtils.isEmpty(percentage1)) {
                    sendGetRequest();

                }
                else{
                    Toast.makeText(AddResult.this, "Please Make sure that you have Entered all the data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void calculatepercentage() {
        double sub1= Double.parseDouble(subject1.getText().toString());
        double sub2= Double.parseDouble(subject2.getText().toString());
        double sub3= Double.parseDouble(subject3.getText().toString());
        double sub4= Double.parseDouble(subject4.getText().toString());
        double sub5= Double.parseDouble(subject5.getText().toString());
        double total=((sub1+sub2+sub3+sub4+sub5)/500)*100;
        String decimalFormat=new DecimalFormat("##.##").format(total);
        percentage.setText(decimalFormat);
        if(sub1<35||sub2<35||sub3<35||sub4<35||sub5<35){
            status.setText("Fail");
        }
        else{
            status.setText("Pass");
        }

    }

    private void sendGetRequest() {
        StringRequest request=new StringRequest(Request.Method.POST, sendUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jobj = new JSONObject(response);
                    sucess = jobj.getInt(TAG_SUCESS);
                    if (sucess == 1) {
                        startActivity(new Intent(AddResult.this,ViewButtons.class));
                        finish();
                        Toast.makeText(AddResult.this, jobj.getString(TAG_MESSAGE), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(AddResult.this, jobj.getString(TAG_MESSAGE), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    Toast.makeText(AddResult.this,e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AddResult.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            public Map<String,String> getParams(){
                Map<String, String> params=new HashMap<String, String>();
                params.put("username",username.getText().toString());
                params.put("email",email.getText().toString());
                params.put("mothername",mothername.getText().toString());
                params.put("seatno",seatno.getText().toString());
                params.put("subject1",subject1.getText().toString());
                params.put("subject2",subject2.getText().toString());
                params.put("subject3",subject3.getText().toString());
                params.put("subject4",subject4.getText().toString());
                params.put("subject5",subject5.getText().toString());
                params.put("percentage",percentage.getText().toString());
                params.put("result",status.getText().toString());
                return params;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(10000,1,1.0f));
        requestQueue.add(request);

    }
}