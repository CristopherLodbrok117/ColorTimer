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

    private int control_notificacion;
    private String notificationTittle;
    private String notificationMessage;


    public static final int MILISEGUNDOS_POR_SEG = 1000;
    public static final int SEGUNDOS_POR_MIN = 60;
    public static final int TOTAL_MINUTOS = 10;

    public static final int INTERVALO_CONTEO = 1;
    public static final int INICIO_INMEDIATO = 0;

    public static final int CONTADOR_INICIAL = 0;

    //Se fija el intervalo de notificación en un tiempo default de 10 minutos
    public Temporizador() { this(INTERVALO_CONTEO); }

    //Fija el intervalo de notificación a partir del parametro recibido (minutos)
    public Temporizador(int minutos) {
        this.notificationTittle = "Hay decoloraciones por actualizar: ";
        this.reloj = new Timer();
        this.tiempoNotificacionMin = minutos * SEGUNDOS_POR_MIN * MILISEGUNDOS_POR_SEG;
    }

    public void setNotificationMessage(String msg) {
        this.notificationMessage = msg;
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

        //reloj.schedule(tarea, INICIO_INMEDIATO, this.tiempoNotificacionMin);
        reloj.schedule(tarea, INICIO_INMEDIATO, 5000);
    }

    public void comenzarReloj(Context context) {
        activo = true;
        control_notificacion = CONTADOR_INICIAL; // Inicializamos el contador de minutos

        TimerTask tarea = new TimerTask() {

            @Override
            public void run() {
                //System.out.println("Lanzar notificación");
                // Toast.makeText(this, "Llego la hora de tomar la foto", Toast.LENGTH_SHORT).show();
                control_notificacion++;
                if (control_notificacion >= TOTAL_MINUTOS) {
                    crearCanalNotificacion(context);
                    enviarNotificacion(context);
                    control_notificacion = CONTADOR_INICIAL;
                }

            }
        };

        //reloj.schedule(tarea, INICIO_INMEDIATO, tiempoNotificacionMin);
        reloj.schedule(tarea, INICIO_INMEDIATO, 2000);
    }

    public void crearCanalNotificacion(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("My Notification"
                    , "Notification", NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("My channel description");
            NotificationManager manage = context.getSystemService(NotificationManager.class);
            manage.createNotificationChannel(channel);

        }
    }

    public void enviarNotificacion(Context context) {


        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "My Notification");
        builder.setContentTitle(notificationTittle);
        builder.setContentText(notificationMessage);
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