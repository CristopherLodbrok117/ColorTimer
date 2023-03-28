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
    //private ImageView img;
    private Button btnProcesos;
    private Button btnSuggestion;

    public ImageView img; //Para mostrar la fotografia NO ES NECESARIO CONSERVAR, solo es para mostrar funcionamiento
    private Button photo_btn;  //Boton para tomar fotografia

    private boolean photoHasBeenTaken = false;

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

        img = findViewById(R.id.iv_image);  //Asociamos la variable con la vista relacionada en el .xml
        photo_btn = findViewById(R.id.btn_camera); //Basarnos en el id que le pongamos a cada vista
        btnSuggestion= findViewById(R.id.btn_suggestion);
        btnSuggestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(photoHasBeenTaken == false){
                    //Mostrar alerta de que no se ha tomado una foto aún.
                } else{
                    Intent secondActivityIntent = new Intent(getApplicationContext(), SuggestionActivity.class);
                    startActivity(secondActivityIntent);
                }
            }
        });


        photo_btn.setOnClickListener(new View.OnClickListener(){ //Modificamos el qué hace ese boton cuando hacemos click
            @Override
            public void onClick(View view) { //Primero comprobamos si los permisos que solicitamos (Camara) ya fue otorgado
                if (ContextCompat.checkSelfPermission(
                        view.getContext(),
                        android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, CAMERA_REQUEST_CODE);
                    /*
                    El intent es muy... flexible. En este caso intentamos crear una actividad de capturar imagen
                    Despues de eso iniciamos la usamos startActivityForResult porque queremos obtener un resultado de la actividad (La foto/data)

                     */
                } else {
                    ActivityCompat.requestPermissions(MainActivity.this, //Si no tiene permisos entonces los solicita
                            new String[]{android.Manifest.permission.CAMERA},  //Nos sale la notificacion solicitandolos
                            CAMERA_PERMISION_CODE);
                }
            }






        });



        btnProcesos = findViewById(R.id.button2);
        dbColor = new DaoColor(this);
        dbTinte = new DaoTinte(this);
        dbProceso = new DaoProceso(this);
        dbCabello = new DaoCabello(this);



        /*Proceso p = new Proceso();
        p.setId((int) dbProceso.crear(p));
        Toast.makeText(MainActivity.this,"ID: "+p.getId(),Toast.LENGTH_SHORT).show();

        if (ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    android.Manifest.permission.CAMERA
            }, 1000);
        }

        */
    }





    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        /* Bueno, esto no hace mucho, solamente solicitar los permisos de la camara

         */
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
            if(requestCode == CAMERA_REQUEST_CODE){
                Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");
                img.setImageBitmap(imageBitmap);
                photoHasBeenTaken = true;
                /*
                data representa a la imagen propiamente dicho, en este caso lo casteamos a
                bitmap para "dibujarlo" en el imageview.
                Se puede modificar esto para variar el como manejar la informacion de la foto
                 */


                /*
                Si queremos mandar la informacion de esta foto podriamos usar algo como:

                Intent i = new Intent(CurrentActivity.this, NewActivity.class);
                i.putExtra("key",data); //la variable data de aqui xd
                startActivity(i);

                En la otra actividad seria:
                Bundle extras = getIntent().getExtras();
                if (extras != null) {
                    Datatype variable = extras.get("key");
                    //La llave debe de coincidir con la que declaramo
                }
                 */

                //Enviando la información de la foto a otra actividad. En este caso Suggestion para Suggestion.
                Intent intent = new Intent(this, SuggestionActivity.class);
                intent.putExtra("bmp_Image", data);
            }

    }

    public void abrirProceso(View view){
        Intent siguiente = new Intent(this, DecoloracionActivity.class);
        startActivity(siguiente);
    }
}