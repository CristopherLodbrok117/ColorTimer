package com.example.colortimer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.colortimer.DAO.DaoProceso;
import com.example.colortimer.Datos.Proceso;

import java.util.ArrayList;
import java.util.List;

public class ProcesosActivity extends AppCompatActivity {
    private ArrayAdapter<String> arrayAdapter;
    private ListView procesosListView;
    private List<String> listaProcesosStr;
    private ArrayList<Proceso> listaProcesos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_procesos);
        procesosListView = findViewById(R.id.listaProcesos);
        listaProcesos = new ArrayList<>();
        listaProcesosStr = new ArrayList<>();
        DaoProceso dbProceso = new DaoProceso(ProcesosActivity.this);
        try {
            listaProcesos = dbProceso.listar();
            Toast.makeText(this, listaProcesos.size(), Toast.LENGTH_SHORT).show();
        }catch (Exception e){
        }

        /*try {
            listaProcesos = dbProceso.listar();

            for (int i = 0; i < listaProcesos.size(); i++) {
                listaProcesosStr.add(listaProcesos.get(i).toString());
            }

            arrayAdapter = new ArrayAdapter<>(ProcesosActivity.this,
                    android.R.layout.simple_list_item_1, listaProcesosStr);
            procesosListView.setAdapter(arrayAdapter);
        } catch (Exception e){
            e.toString();
        }*/
    }
}