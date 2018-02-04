package com.example.junsu.calltest1;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;


public class MainActivity extends AppCompatActivity {
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onButton1Clicked(View v){
        String filename = editText.getText().toString();
        if(filename.length()>0){
            openPDF(filename.trim());
        }
        else{
            Toast.makeText(getApplicationContext(),"PDF 파일명을 입력하세요",
                            Toast.LENGTH_LONG).show();
        }
    }
    public void openPDF(String filename){
        String sdcardFolder = Environment.getExternalStorageDirectory().getAbsolutePath();
        String filepath = sdcardFolder+ File.separator+filename;
        File file = new File(filepath);
        Toast.makeText(this, sdcardFolder,Toast.LENGTH_LONG).show();

        if(file.exists()){
            Uri uri = Uri.fromFile(file);

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(uri,"application/pdf");

            try{
                startActivity(intent);
            }catch (ActivityNotFoundException ex){
                Toast.makeText(this,"PDF파일을 보기위한 뷰어앱이 없습니다.",
                                        Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this,"PDF파일이 없습니다",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
