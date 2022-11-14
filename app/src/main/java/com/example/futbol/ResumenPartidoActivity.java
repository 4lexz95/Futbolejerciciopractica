package com.example.futbol;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.futbol.databinding.ActivityCrearPartidoBinding;
import com.example.futbol.databinding.ResumenpartidoActivityBinding;
import com.example.futbol.modelos.Partido;

public class ResumenPartidoActivity extends AppCompatActivity {
    // Clickes en el card te envia a otra actividad donde te dara la informacion de dicho partido
    private ResumenpartidoActivityBinding binding;
    private TextView lbllocal;
    private TextView lblvisitante;
    private TextView lblresultado;
    private TextView lbldescripcion;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resumenpartido_activity);
        binding = ResumenpartidoActivityBinding.inflate(getLayoutInflater());

        Intent intent = getIntent();
        inicializa();
        Partido partido = (Partido) intent.getExtras().getSerializable("PARTIDO");
        lbllocal.setText(partido.getEquipo1());
        lblvisitante.setText(partido.getEquipo2());
        lblresultado.setText(String.valueOf(partido.getRes1()+" - "+partido.getRes2()));
        lbldescripcion.setText(partido.getResumen());

    }

    private void inicializa() {
        lbllocal = findViewById(R.id.lblEq2ResAct);
        lblvisitante = findViewById(R.id.lblEq2ResAct);
        lblresultado = findViewById(R.id.lblResulResAct);
        lbldescripcion = findViewById(R.id.lbldescresumenact);
    }
}
