package com.example.sever_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
 public final static   String  NAME_SEVER="http://10.24.48.212/bac_ph20234/student_GET.php";
    public EditText  edName,edScore;
    public Button btn;
    String strName,strScore;
    public TextView txtResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edName=findViewById(R.id.edname);
        edScore=findViewById(R.id.edsorce);
        btn=(Button)findViewById(R.id.btn);
        txtResult=(TextView) findViewById(R.id.txtresult);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              strName=edName.getText().toString();
              strScore=edScore.getText().toString();
              BackgroundTask_GET backgroundTask_get =new BackgroundTask_GET(strName,strScore,txtResult,v.getContext());
              backgroundTask_get.execute();

            }
        });
    }
}