package com.example.sever_android;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class BackgroundTask_GET extends AsyncTask<Void,Void,Void> {

    String duongDan=MainActivity.NAME_SEVER;
    String strName,strScore;
    String str;
    ProgressDialog progressDialog;
    TextView txtResult;
    Context context;

    public BackgroundTask_GET(String strName, String strScore, TextView txtResult, Context context) {
        this.strName = strName;
        this.strScore = strScore;
        this.txtResult = txtResult;
        this.context = context;
    }

    @Override
    protected Void doInBackground(Void... voids) {
       duongDan+="?name="+this.strName+"&score="+this.strScore;
       try {
           URL url=new URL(duongDan);
           HttpURLConnection urlConnection=(HttpURLConnection) url.openConnection();
           BufferedReader bft=new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
           String line="";
           StringBuffer sb=new StringBuffer();
           while ((line=bft.readLine())!=null){
               sb.append(line);
           }
           str=sb.toString();
           urlConnection.disconnect();
       } catch (MalformedURLException e) {
           e.printStackTrace();
       } catch (IOException e) {
           e.printStackTrace();
       }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog=new ProgressDialog(context);
        progressDialog.setMessage("Sending...");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    protected void onPostExecute(Void unused) {
        super.onPostExecute(unused);
     if(progressDialog.isShowing()){
         progressDialog.dismiss();
     }
     txtResult.setText(str);

    }
}
