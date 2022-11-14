package com.example.futbol;

import android.content.Intent;
import android.os.Bundle;

import com.example.futbol.adapters.PartidosAdapter;
import com.example.futbol.modelos.Partido;
import com.google.android.material.snackbar.Snackbar;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;


import com.example.futbol.databinding.ActivityMainBinding;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

// diferencia entre activity main(parte externa) y content main: parte intena de la interfaz donde se ponen los elementos
    private ActivityMainBinding binding;
    //Creo el launcher para devolver la informacion de una actividad secundaria que no es la principal en este caso y caos
    // crear partido
    private ActivityResultLauncher<Intent> addPartidoLauncher;
    ArrayList<Partido> partidoslist;
    private PartidosAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        partidoslist = new ArrayList<>();
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        adapter = new PartidosAdapter(partidoslist, R.layout.partido_view_holder, MainActivity.this);
        binding.ContentActMain.contenedor.setAdapter(adapter);
        layoutManager = new LinearLayoutManager(MainActivity.this);
        binding.ContentActMain.contenedor.setLayoutManager(layoutManager);
        inicializaLauncher();
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);




        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addPartidoLauncher.launch(new Intent(MainActivity.this, crear_partido.class));
            }
        });
    }
    private void inicializaLauncher() {
        addPartidoLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {
                            if (result.getData() != null && result.getData().getExtras() != null) {
                               Partido par = (Partido) result.getData().getExtras().getSerializable("ALUMNO");
                                if (par != null){
                                    // metemos dentro del arraylist de Cards cada uno de los objetos Partido creados y los insertamos
                                    // en el Main
                                    partidoslist.add(par);
                                    // Aqui empieza el adapter
                                    adapter.notifyDataSetChanged();
                                }
                            }
                        }
                    }
                }
        );
    }

  /*  private void mostrarPartidos() {
        // 1. Conjunto de Datos -> ArrayLis
        // 2. Contenedor de los elementos -> View capaz de contener elementos LinearLayout Vertical -> Scroll
        // 3. Plantilla para la visualización de los elementos ->

        binding.contendorMain.contenedorAlumnos.removeAllViews();
        int posicion = 0;
        for (Partido part: partidoslist) {
            View alumnoVista = LayoutInflater.from(this).inflate(R.layout.alumno_layout, null);
            TextView txtNombre = alumnoVista.findViewById(R.id.lblNombreAlumno);
            TextView txtApellidos = alumnoVista.findViewById(R.id.lblApellidosAlumno);
            TextView txtCiclo = alumnoVista.findViewById(R.id.lblCicloAlumno);
            TextView txtNombreGrupo = alumnoVista.findViewById(R.id.lblGrupoAlumno);
            txtNombre.setText(a.getNombre());
            txtApellidos.setText(a.getApellidos());
            txtCiclo.setText(a.getCiclo());
            txtNombreGrupo.setText(""+a.getGrupo());

            int finalPosicion = posicion;
            alumnoVista.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, EditAlumnoActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("PARTIDO", PART);
                    bundle.putInt("POSICION", finalPosicion);
                    intent.putExtras(bundle);
                    editLauncher.launch(intent);
                }
            });
            binding.contendorMain.contenedorAlumnos.addView(alumnoVista);
            posicion++;
        }

    }

   */


}