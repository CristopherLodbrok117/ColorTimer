package com.example.colortimer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.colortimer.DAO.DaoCabello;
import com.example.colortimer.DAO.DaoColor;
import com.example.colortimer.DAO.DaoProceso;
import com.example.colortimer.DAO.DaoTinte;
import com.example.colortimer.Datos.MyColor;
import com.example.colortimer.Datos.Proceso;
import com.example.colortimer.Datos.Temporizador;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    //private ImageView img;
    private Button btnProcesos;




    private boolean photoHasBeenTaken = false;

    private DaoCabello dbCabello;
    private DaoColor dbColor;
    private DaoProceso dbProceso;
    private DaoTinte dbTinte;

    private List<Proceso> procesos = null;
    private Temporizador temporizador = new Temporizador(1);;
    private  int CAMERA_PERMISION_CODE = 1;
    private  int CAMERA_REQUEST_CODE = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnProcesos = findViewById(R.id.btn_proceso);
        dbProceso = new DaoProceso(this);


    }

    @Override
    protected void onResume(){
        super.onResume();
        cargarListaProcesos();

        if(!procesos.isEmpty()){ // Lista con procesos activos ? activar notificaciones
            if(!temporizador.estaActivo()){
                activarTemporizador();
            }

        }
        else{   // Lista vacia y siguen activas las notificaicones ? desactivar notificaciones
            desactivarTemporizador();
        }

    }

    @Override
    protected void onStop(){
        super.onStop();
        mostrarMensaje("Main onstop");
        desactivarTemporizador();
        temporizador = null;
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        mostrarMensaje("Main onDestroy");
        temporizador = null;
    }


    public void abrirProceso(View view){
        Intent siguiente = new Intent(this, DecoloracionActivity.class);
        siguiente.putExtra("id", -1);
        startActivity(siguiente);
    }

    public void abrirProceso(int id){
        Intent siguiente = new Intent(this, DecoloracionActivity.class);
        siguiente.putExtra("id", id);
        startActivity(siguiente);
    }

    public void activarTemporizador(){
        // Cada minuto incrementara el contador y al llegar a 10 envia una notificación

        actualizarMensajeNotificacion();
        temporizador.comenzarReloj(this);

    }

    public void actualizarMensajeNotificacion(){
        StringBuffer msg = new StringBuffer("");

        if(!procesos.isEmpty()){
            for(Proceso proceso : procesos ){
                msg.append(proceso.getNombreCliente());
                msg.append(", ");
            }
        }

        temporizador.setNotificationMessage(msg.toString());
    }

    public void desactivarTemporizador(){

        try {
            if(temporizador.estaActivo()) {
                temporizador.pararReloj();
            }
        }catch(NullPointerException ex){
            Toast.makeText(this, "No se ha iniciado el temporizador", Toast.LENGTH_LONG).show();
        }

    }

    public void cargarListaProcesos(){
        this.procesos = dbProceso.listarProcesosActivos();

        for(Proceso proceso : procesos){
            agregarBotonProceso(proceso);
        }
    }

    public void agregarBotonProceso(Proceso proceso){
        LinearLayout layout = (LinearLayout) findViewById(R.id.layout_procesos);
        Button newBtn = new Button(this);
        personalizarBoton(newBtn, proceso);
        layout.addView(newBtn);

    }
    /*Este método agrega texto (vacio o datos del proceso)
    * Sobreescribe la función onClick
    * Define el color de fondo y color de texto del boton
    * */
    public void personalizarBoton(Button btn, Proceso proceso){
        String text = "Vacio";
        if(proceso != null){
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    abrirProceso(proceso.getId());
                }
            });

            text = String.valueOf(proceso.getId());
            text += " - ";
            text += proceso.getNombreCliente();
        }

        btn.setText(text);

        MyColor color = new MyColor("303030");
        btn.setBackgroundColor(color.getValorView());
        color.setValorHexadecimal("FFFFFF");
        btn.setTextColor(color.getValorView());

    }

    public void mostrarMensaje(String text){
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }
}