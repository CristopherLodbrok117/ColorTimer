package com.example.colortimer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.colortimer.Datos.Colorimetria;
import com.example.colortimer.Datos.ExtractorColor;
import com.example.colortimer.Datos.MyColor;
import com.example.colortimer.Datos.Proceso;
import com.example.colortimer.Datos.Temporizador;

import java.util.Random;

public class DecoloracionActivity extends AppCompatActivity {

    private Proceso proceso;
    private Colorimetria colorimetria;

    private Temporizador temporizador;

    private ExtractorColor extractor;

    private TextView tv_id;
    private TextView tv_estado;

    private ImageView img_inicial;
    private ImageView img_actual;
    private ImageView img_final;

    private Button btn_iniciar;
    private Button btn_foto;

    private ImageButton btn_volver;
    private ImageButton btn_terminar;

    private final int TOTAL_COLORES = 3;
    private String colores[] = {"35E20A", "1081DC", "DF53C7"};

    private boolean activo = false; // Cambia hasta que se presiona el boton iniciar
    private boolean fotoInicialTomada = false; // Cambia con la primer foto

    // Atributos de camara
    private boolean photoHasBeenTaken = false;

    private  int CAMERA_PERMISION_CODE = 1;
    private  int CAMERA_REQUEST_CODE = 2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decoloracion);

        inicializarViews(); // Cargamos los componentes de la ventana
        elCosoEseDeLaFoto(); // Sobreescribimos el listener del boton foto
        inicializarProceso();
    }

    private void inicializarViews(){

        tv_id = (TextView)findViewById(R.id.tv_id);
        tv_estado = (TextView)findViewById(R.id.tv_estado);

        img_inicial= (ImageView) findViewById(R.id.img_inicial);
        img_actual= (ImageView) findViewById(R.id.img_actual);
        img_final= (ImageView) findViewById(R.id.img_final);

        btn_foto = (Button)findViewById(R.id.btn_foto);
        btn_iniciar = (Button)findViewById(R.id.btn_iniciar);

        btn_volver = (ImageButton) findViewById(R.id.btn_volver);
        btn_terminar = (ImageButton) findViewById(R.id.btn_terminar);

    }

    private void inicializarProceso(){

        proceso = new Proceso();
        proceso.setId(117);
        proceso.setEstado("Estado actual: Preparando decoloración");
        proceso.setTiempoDecoloracion(0);

        String strId = proceso.getId() + "";

        colorimetria = new Colorimetria();

        temporizador = new Temporizador(); // Comienza hasta que invoquemos .iniciarReloj(this);
        temporizador.setNombreDecoloracion(strId);

        extractor = new ExtractorColor(this);

        MyColor colorDeseado = new MyColor();
        int pos = sugerirColorTinte();
        colorDeseado.setValorHexadecimal(colores[pos]); // Le damos un valor hexadecimal


        tv_id.setText(strId);

        img_final.setBackgroundColor(colorDeseado.getValorView());

        btn_iniciar.setEnabled(false); // No lo activamos hasta que preparemos todo

    }

    private int sugerirColorTinte(){

        Random random = new Random();
        int pos = random.nextInt(TOTAL_COLORES - 0) + 0;

        return pos;
    }

    private void actualizarEstadoActual(Bitmap foto){
        if(activo){
            MyColor color = extractor.extraerColor(foto);
            String estado = "Actualización: ";
            String resultadoAnalisis;
            colorimetria.setColorActual(color);

            img_actual.setBackgroundColor(color.getValorView());
            resultadoAnalisis = colorimetria.obtenerEstado();
            //Comparar para actualizar le estado
            estado += resultadoAnalisis;
            tv_estado.setText(estado);
            if(resultadoAnalisis == "Cabello arruinado"){
                temporizador.pararReloj();
            }
        }
        else{ //No se ha iniciado la decoloración y se debe tomar la primer foto
            actualizarFotoInicial(foto);
        }

    }

    private void actualizarFotoInicial(Bitmap foto){

        MyColor color = extractor.extraerColor(foto);
        String estado = "Color obtenido: listo para iniciar";
        colorimetria.setColorInicial(color);
        colorimetria.setColorActual(color); // Tras nuestra primer foto, inicial = actual

        img_inicial.setBackgroundColor(color.getValorView()); // Mostramos el color extraido en la ImageView
        img_actual.setBackgroundColor(color.getValorView());
        tv_estado.setText(estado);
        btn_iniciar.setEnabled(true); // Ahora podemos iniciar la decoloración

        fotoInicialTomada = true;
    }

    public void volverPantallaPrincipal(View view){
        Intent anterior = new Intent(this, MainActivity.class);
        startActivity(anterior);
    }

    public void iniciarDecoloracion(View view){
        activo = true;

        tv_estado.setText("Actualización: Decoloración iniciada");

        btn_iniciar.setEnabled(false); // No volvera a ser utilizado

        temporizador.iniciarReloj(this);
    }

    public void detenerDecoloración(View view){
        tv_estado.setText("Decoloración finalizada");

        temporizador.pararReloj();

    }

    // Funciones de la camara

    private void elCosoEseDeLaFoto(){
        btn_foto.setOnClickListener(new View.OnClickListener(){ //Modificamos el qué hace ese boton cuando hacemos click
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
                    ActivityCompat.requestPermissions(DecoloracionActivity.this, //Si no tiene permisos entonces los solicita
                            new String[]{android.Manifest.permission.CAMERA},  //Nos sale la notificacion solicitandolos
                            CAMERA_PERMISION_CODE);
                }
            }

        });
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
            //img.setImageBitmap(imageBitmap); //No necesitamos mostrar la imagen en esta ventana
            photoHasBeenTaken = true;

            actualizarEstadoActual(imageBitmap); // Ahora si se viene lo chido
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
            //Intent intent = new Intent(this, SuggestionActivity.class);
            //intent.putExtra("bmp_Image", data);
        }

    }




}