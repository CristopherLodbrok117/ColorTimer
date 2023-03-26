package com.example.colortimer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.colortimer.DAO.DaoProceso;
import com.example.colortimer.Datos.Proceso;
import com.example.colortimer.Datos.Temporizador;

import java.util.ArrayList;
import java.util.List;

public class ProcesosActivity extends AppCompatActivity {
    private ArrayAdapter<String> arrayAdapter;
    private ListView lvProcesos;
    private List<String> listaProcesosStr;
    private List<Proceso> listaProcesos;

    private Temporizador nzcth_800;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_procesos);
        lvProcesos = findViewById(R.id.listaProcesos);
        listaProcesos = new ArrayList<>();
        listaProcesosStr = new ArrayList<>();
        try{
            DaoProceso dbProceso = new DaoProceso(ProcesosActivity.this);
            listaProcesos = dbProceso.listar();

            for(int i = 0; i < listaProcesos.size(); i++){
                listaProcesosStr.add(listaProcesos.get(i).toString());
            }

            arrayAdapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1, listaProcesosStr);
            lvProcesos.setAdapter(arrayAdapter);

            /* Si se descomenta esta parte del codigo, se borrara el elemento de la base de datos
                Aunque la idea es utilizar la lista para poder ver el estado del proceso.
            lvProcesos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    dbProceso.borrar(listaProcesos.get(i).getId());
                    listaProcesos.remove(i);
                }
            });*/
        } catch (Exception e){
            Toast.makeText(this, "Error al leer la Base de Datos",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void iniciarNZCTH800(){
        nzcth_800 = new Temporizador();
        nzcth_800.setNombreDecoloracion("Cliente 1");

        nzcth_800.iniciarReloj(this); // Requiero pasarle una referencia de ProcesosActivity
    }

    public void detenerNZCTH800(){
        try {
            nzcth_800.pararReloj();
        }catch(NullPointerException ex){
            Toast.makeText(this, "No se ha iniciado el temporizador", Toast.LENGTH_LONG).show();
        }
    }
}