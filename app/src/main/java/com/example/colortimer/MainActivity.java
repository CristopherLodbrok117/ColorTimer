package com.example.colortimer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.example.colortimer.DAO.DaoCabello;
import com.example.colortimer.DAO.DaoColor;
import com.example.colortimer.DAO.DaoProceso;
import com.example.colortimer.DAO.DaoTinte;
import com.example.colortimer.Datos.Proceso;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {



    private String currentPhotoPath;
    private ImageView img;
    private Button btnProcesos;

    private Button btn;

    private DaoCabello dbCabello;
    private DaoColor dbColor;
    private DaoProceso dbProceso;
    private DaoTinte dbTinte;


    private  int CAMERA_PERMISION_CODE = 1;
    private  int CAMERA_REQUEST_CODE = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn_camera);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(
                        view.getContext(),
                        android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, CAMERA_REQUEST_CODE);
                } else {
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{android.Manifest.permission.CAMERA},
                            CAMERA_PERMISION_CODE);
                }
            }
        });

        if (ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    android.Manifest.permission.CAMERA
            }, 1000);
        }


        img = findViewById(R.id.iv_image);


        btnProcesos = findViewById(R.id.button2);
        dbColor = new DaoColor(this);
        dbTinte = new DaoTinte(this);
        dbProceso = new DaoProceso(this);
        dbCabello = new DaoCabello(this);



        /*Proceso p = new Proceso();
        p.setId((int) dbProceso.crear(p));
        Toast.makeText(MainActivity.this,"ID: "+p.getId(),Toast.LENGTH_SHORT).show();



        */
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == CAMERA_PERMISION_CODE){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, CAMERA_REQUEST_CODE);
            }else{
                Toast.makeText(this,
                        "Sin permisos. Otorgalos desde configuracion",
                        Toast.LENGTH_LONG);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == Activity.RESULT_OK){
            if(requestCode == CAMERA_REQUEST_CODE){
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                img.setImageBitmap(imageBitmap);
            }
        }
    }
}