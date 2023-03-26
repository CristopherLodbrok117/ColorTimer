package com.example.colortimer.Datos;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.colortimer.R;

import java.util.Timer;
import java.util.TimerTask;

public class Temporizador {
    private Timer reloj;
    private int tiempoNotificacionMin;
    private boolean activo;

    private String nombreDecoloracion;

    public static final int MILISEGUNDOS_POR_SEG = 1000;
    public static final int SEGUNDOS_POR_MIN = 60;
    public static final int TOTAL_MINUTOS = 10;
    public static final int INICIO_INMEDIATO = 0;

    //Se fija el intervalo de notificación en un tiempo default de 10 minutos
    public Temporizador (){
        reloj = new Timer();
        tiempoNotificacionMin = TOTAL_MINUTOS * SEGUNDOS_POR_MIN * MILISEGUNDOS_POR_SEG;
    }

    //Fija el intervalo de notificación a partir del parametro recibido (minutos)
    public Temporizador(int minutos) {
        reloj = new Timer();
        tiempoNotificacionMin = minutos * SEGUNDOS_POR_MIN * MILISEGUNDOS_POR_SEG;
    }

    public void setNombreDecoloracion(String nombreDecoloracion){
        this.nombreDecoloracion = nombreDecoloracion;
    }

    public void pararReloj() {
        activo = false;
        reloj.cancel();
    }

    public boolean estaActivo() {
        return activo;
    }

    //Esta función inicia el temporizador y
    public void iniciarReloj(Context context) {
        activo = true;

        TimerTask tarea = new TimerTask() {

            @Override
            public void run() {
                //System.out.println("Lanzar notificación");
                // Toast.makeText(this, "Llego la hora de tomar la foto", Toast.LENGTH_SHORT).show();
                crearCanalNotificacion(context);
                enviarNotificacion(context);
            }
        };

        //reloj.schedule(tarea, INICIO_INMEDIATO, tiempoNotificacionMin);
        reloj.schedule(tarea, INICIO_INMEDIATO, 5000);
    }

    public void crearCanalNotificacion(Context context){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("My Notification"
                    , "Notification", NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("My channel description");
            NotificationManager manage = context.getSystemService(NotificationManager.class);
            manage.createNotificationChannel(channel);

        }
    }

    public void enviarNotificacion(Context context){
        String title = "Decoloración: " + nombreDecoloracion;
        String desc = "Tomar nueva foto para actualizar estado de decoloración";

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "My Notification");
        builder.setContentTitle(title);
        builder.setContentText(desc);
        builder.setSmallIcon(R.drawable.baseline_add_alert_24);
        builder.setAutoCancel(true);

        NotificationManagerCompat manager = NotificationManagerCompat.from(context);

        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        manager.notify(1, builder.build());

    }

}