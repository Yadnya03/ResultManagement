package com.example.resultmanagement;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import jxl.Sheet;
import jxl.Workbook;

import static android.os.Environment.getExternalStorageDirectory;

public class ViewButtons extends AppCompatActivity {
    public Button addresult,viewresult,download;
    ArrayList<ListItem1> MyList1;
    ListItem1 saveData;
    ListItem1 username1;
    ListItem1 mothername1;
    ListItem1 seatno1;
    ListItem1 email1;
    ListItem1  sub1;
    ListItem1  sub2;
    ListItem1  sub3;
    ListItem1  sub4;
    ListItem1  sub5;
    ListItem1 percentage1;
    ListItem1 result1;

    private RequestQueue requestQueue;
    private static final int STORAGE_CODE=1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_buttons);
        addresult=(Button)findViewById(R.id.addresult);
        viewresult=(Button)findViewById(R.id.viewresult);
        saveData = new ListItem1();
        requestQueue= Volley.newRequestQueue(getApplicationContext());
        download=(Button)findViewById(R.id.download);
       // acceptAttendance();
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showAlertDialog();

            }
        });
        addresult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewButtons.this,AddResult.class));
            }
        });
        viewresult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewButtons.this,View_Result.class));
            }
        });

    }

    private void showAlertDialog() {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(ViewButtons.this);
        alertDialog.setTitle("Alert")
                .setMessage("Are You Sure You Want To Download Pdf");
        alertDialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(Build.VERSION.SDK_INT>Build.VERSION_CODES.M){
                    if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED){
                        String[] permimssion={Manifest.permission.WRITE_EXTERNAL_STORAGE};

                        requestPermissions(permimssion,STORAGE_CODE);
                    }
                    else{
                        acceptAttendance();
                    }
                }
                else{
                    acceptAttendance();


                }
            }

        });

        AlertDialog alert = alertDialog.create();
        alert.setCanceledOnTouchOutside(true);
        alert.show();

    }

//    private void saveExcel() {
//        String mFileName=new SimpleDateFormat("yyyyMMdd_HHmmss",
//                Locale.getDefault()).format(System.currentTimeMillis());
//        HSSFWorkbook wb=new HSSFWorkbook();
//        Cell cell=null;
//
//        //Now we are creating sheet
//        HSSFSheet sheet=null;
//        sheet = wb.createSheet("Name of sheet");
//        //Now column and row
//        Row row =sheet.createRow(0);
//
//        cell=row.createCell(0);
//        cell.setCellValue("Name");
//        cell=row.createCell(1);
//        cell.setCellValue("Mother's Name");
//        cell=row.createCell(2);
//        cell.setCellValue("Seat No");
//        cell=row.createCell(3);
//        cell.setCellValue("email");
//        cell=row.createCell(4);
//        cell.setCellValue("Subject1");
//        cell=row.createCell(5);
//        cell.setCellValue("Subject2");
//        cell=row.createCell(6);
//        cell.setCellValue("Subject3");
//        cell=row.createCell(7);
//        cell.setCellValue("Subject4");
//        cell=row.createCell(8);
//        cell.setCellValue("Subject5");
//        cell=row.createCell(9);
//        cell.setCellValue("Percentage");
//        cell=row.createCell(10);
//        cell.setCellValue("Result");
//
//        sheet.setColumnWidth(0,(10*200));
//        sheet.setColumnWidth(1,(10*200));
//        int k=1;
//        for (int i = 0; i < MyList1.size(); i++) {
//            // name = MyList1.get(i);
//            username1 = MyList1.get(i);
//            email1 = MyList1.get(i);
//            mothername1 = MyList1.get(i);
//            seatno1 = MyList1.get(i);
//            sub1=MyList1.get(i);
//            sub2=MyList1.get(i);
//            sub3=MyList1.get(i);
//            sub4=MyList1.get(i);
//            sub5=MyList1.get(i);
//            percentage1=MyList1.get(i);
//            result1=MyList1.get(i);
//            String username2 = username1.getUsername1();
//            Log.d("usernam2",username2);
//            String email2 = email1.getEmail1();
//            String mothername2=mothername1.getMothername1();
//            String seatno2 = seatno1.getSeatno1();
//            String sub12 = sub1.getSubject11();
//            String sub22 = sub2.getSubject21();
//            String sub32 = sub3.getSubject31();
//            String sub42 = sub4.getSubject41();
//            String sub52 = sub5.getSubject51();
//            String percentage2 = percentage1.getPercentage1();
//            String result2=result1.getResult1();
//            row = sheet.createRow(i+1);
//                row.createCell(k);
//            cell=row.createCell(0);
//            cell.setCellValue(username2);
//            cell=row.createCell(1);
//            cell.setCellValue(mothername2);
//            cell=row.createCell(2);
//            cell.setCellValue(seatno2);
//            cell=row.createCell(3);
//            cell.setCellValue(email2);
//            cell=row.createCell(4);
//            cell.setCellValue(sub12);
//            cell=row.createCell(5);
//            cell.setCellValue(sub22);
//            cell=row.createCell(6);
//            cell.setCellValue(sub32);
//            cell=row.createCell(7);
//            cell.setCellValue(sub42);
//            cell=row.createCell(8);
//            cell.setCellValue(sub52);
//            cell=row.createCell(9);
//            cell.setCellValue(percentage2);
//            cell=row.createCell(10);
//            cell.setCellValue(result2);
//            k++;
//
//
//
//            //table.addCell(String.valueOf(namen));
////             String dn =date11.replace(daten.substring(3,5),"");
////             String date12=date0.getText().toString();
//
//
//        }
//
//
//        File mediaStorageDir = new File(Environment.getExternalStorageDirectory(), "Result");
//        if (!mediaStorageDir.exists()) {
//            if (!mediaStorageDir.mkdirs()) {
//                Toast.makeText(ViewButtons.this, "Failed To Create Directory", Toast.LENGTH_SHORT).show();
//            }
//        }
//      //  File file = new File(Environment.getExternalStorageDirectory(),"plik.xls");
//        String mFilePath=Environment.getExternalStorageDirectory()+"/Result/"+mFileName+".xls";
//        FileOutputStream outputStream =null;
//
//        try {
//            outputStream=new FileOutputStream(mFilePath);
//            wb.write(outputStream);
//            Toast.makeText(getApplicationContext(),"OK",Toast.LENGTH_LONG).show();
//        } catch (java.io.IOException e) {
//            e.printStackTrace();
//
//            Toast.makeText(getApplicationContext(),"NO OK",Toast.LENGTH_LONG).show();
//            try {
//                outputStream.close();
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//        }
//
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.main_search_icon:
                Toast.makeText(this, "user profile", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ViewButtons.this, MainActivity.class));
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
    private void acceptAttendance(){
         String ExtractDataUrl="http://www.datamanagement.ml/fetch1.php";


        StringRequest stringRequest=new StringRequest(Request.Method.GET, ExtractDataUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("response3:",""+response);
                try{
                    JSONArray array=new JSONArray(response);
                    String mFileName=new SimpleDateFormat("yyyyMMdd_HHmmss",
                            Locale.getDefault()).format(System.currentTimeMillis());
                    HSSFWorkbook wb=new HSSFWorkbook();
                    Cell cell=null;

                    //Now we are creating sheet
                    HSSFSheet sheet=null;
                    sheet = wb.createSheet("Name of sheet");
                    //Now column and row
                    Row row =sheet.createRow(0);
                    cell=row.createCell(0);
                    cell.setCellValue("Name");
                    cell=row.createCell(1);
                    cell.setCellValue("Mother's Name");
                    cell=row.createCell(2);
                    cell.setCellValue("Seat No");
                    cell=row.createCell(3);
                    cell.setCellValue("email");
                    cell=row.createCell(4);
                    cell.setCellValue("Subject1");
                    cell=row.createCell(5);
                    cell.setCellValue("Subject2");
                    cell=row.createCell(6);
                    cell.setCellValue("Subject3");
                    cell=row.createCell(7);
                    cell.setCellValue("Subject4");
                    cell=row.createCell(8);
                    cell.setCellValue("Subject5");
                    cell=row.createCell(9);
                    cell.setCellValue("Percentage");
                    cell=row.createCell(10);
                    cell.setCellValue("Result");

                    sheet.setColumnWidth(0,(10*200));
                    sheet.setColumnWidth(1,(10*200));
                    Log.d("arraysize:",""+array.toString());
                    int k=1;
                    for(int i=0;i<array.length();i++){
                        JSONObject data=array.getJSONObject(i);
            row = sheet.createRow(i+1);
            row.createCell(k);
            cell=row.createCell(0);
            cell.setCellValue(data.getString("username"));
            cell=row.createCell(1);
            cell.setCellValue(data.getString("mothername"));
            cell=row.createCell(2);
            cell.setCellValue(data.getString("seatno"));
            cell=row.createCell(3);
            cell.setCellValue(data.getString("email"));
            cell=row.createCell(4);
            cell.setCellValue(data.getString("subject1"));
            cell=row.createCell(5);
            cell.setCellValue(data.getString("subject2"));
            cell=row.createCell(6);
            cell.setCellValue(data.getString("subject3"));
            cell=row.createCell(7);
            cell.setCellValue(data.getString("subject4"));
            cell=row.createCell(8);
            cell.setCellValue(data.getString("subject5"));
            cell=row.createCell(9);
            cell.setCellValue(data.getString("percentage"));
            cell=row.createCell(10);
            cell.setCellValue(data.getString("result"));
            k++;
            //table.addCell(String.valueOf(namen));
//             String dn =date11.replace(daten.substring(3,5),"");
//             String date12=date0.getText().toString();


        }


        File mediaStorageDir = new File(Environment.getExternalStorageDirectory(), "Result");
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Toast.makeText(ViewButtons.this, "Failed To Create Directory", Toast.LENGTH_SHORT).show();
            }
        }
      //  File file = new File(Environment.getExternalStorageDirectory(),"plik.xls");
        String mFilePath=Environment.getExternalStorageDirectory()+"/Result/"+mFileName+".xls";
        FileOutputStream outputStream =null;

        try {
            outputStream=new FileOutputStream(mFilePath);
            wb.write(outputStream);
            Toast.makeText(getApplicationContext(),"File Is Successfully Created",Toast.LENGTH_LONG).show();
        } catch (java.io.IOException e) {
            e.printStackTrace();

            Toast.makeText(getApplicationContext(),"Some error Occured While Creation of the file",Toast.LENGTH_LONG).show();
            try {
                outputStream.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
//                    for (int i=0;i<array.length();i++){
//
//                        JSONObject data=array.getJSONObject(i);
//                        MyList1 = new ArrayList<ListItem1>();
//                            // saveData.setName(snapshot.getString("name"));
//                            saveData.setUsername1(data.getString("username"));
//                            saveData.setEmail1(data.getString("email"));
//                            saveData.setMothername1(data.getString("mothername"));
//                            saveData.setSeatno1(data.getString("seatno"));
//                            saveData.setSubject11(data.getString("subject1"));
//                            saveData.setSubject21(data.getString("subject2"));
//                            saveData.setSubject31(data.getString("subject3"));
//                            saveData.setSubject41(data.getString("subject4"));
//                            saveData.setSubject51(data.getString("subject5"));
//                            saveData.setPercentage1(data.getString("percentage"));
//                            saveData.setResult1(data.getString("result"));
//                            MyList1.add(saveData);
//                            saveData = new ListItem1();
//
                              //  Log.d("sangle", ""+MyList1.toString());


//                        ListItem item=new ListItem(
//                                data.getString("username"),
//                                data.getString("seatno"),
//                                data.getString("id")


//                        );



                     //   Log.d("Data1:",""+data.get("seatno"));
//                        String username1=data.getString("username");
//                        String email1=data.getString("email");
//                        userName.setText(username1);
//                        email.setText(email1);
                 //   }
                }catch (Exception e){
                    Toast.makeText(ViewButtons.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ViewButtons.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(stringRequest);


    }

}