package com.example.colortimer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ProcesosActivity extends AppCompatActivity {
    private ArrayAdapter<String> arrayAdapter;
    private ListView procesosListView;
    private List<String> listaProcesos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_procesos);
        procesosListView = (ListView) findViewById(R.id.listaProcesos);
        listaProcesos = new ArrayList<>();
        // Toma los datos extra que se envian, en este caso, datos de los procesos
        Bundle datosExtra = getIntent().getExtras();

        if(datosExtra != null)
            // Los añade a listaProcesos
            listaProcesos = datosExtra.getStringArrayList("Procesos");

        // Los añade a una lista en la Activity de procesos
        arrayAdapter = new ArrayAdapter<String>(ProcesosActivity.this, android.R.layout.simple_list_item_1, listaProcesos);
        procesosListView.setAdapter(arrayAdapter);
    }
}