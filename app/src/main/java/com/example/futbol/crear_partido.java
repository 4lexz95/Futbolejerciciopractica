package com.example.futbol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.futbol.databinding.ActivityCrearPartidoBinding;
import com.example.futbol.modelos.Partido;

import java.util.ArrayList;

public class crear_partido extends AppCompatActivity {

    private EditText txtreseq1;
    private EditText txtreseq2;
    private Spinner speq1;
    private Spinner speq2;
    private Button btnCrear;
    private Button btnCancelar;
    private EditText txtresumen;

    //EL ACTIVITY SE GENERA SOLO;
     private ActivityCrearPartidoBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //inicializarVistas();
        //binging evita hacerlo mediante findviewById
        binding = ActivityCrearPartidoBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_crear_partido);
        binding.btncan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        binding.btnCrearPar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //SOLO USANDO BINDING
                String eq1 = (String) binding.spEq1.getSelectedItem();
                String eq2 = (String) binding.spEq2.getSelectedItem();

                //SOLO CUANDO USAS FINDVIEWBYID
                // String eq1 = (String) speq1.getSelectedItem();
                //String eq2 = (String) speq2.getSelectedItem();

                Partido par = new Partido(eq1,eq2,Integer.parseInt(binding.txtResEq1.getText().toString()) , Integer.parseInt(binding.txtResEq2.getText().toString()),
                        binding.txtdescr.getText().toString());
                // Darle el Bundle para pasar la informaciona a la MainActivity
                Bundle bundle = new Bundle();
                //Le damos una clave para como aasignarle un tag al objeto para recoger la info
                bundle.putSerializable("PARTIDO",par);
                Intent intent = new Intent();
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
                finish();




            }
        });


    }

    /*private void inicializarVistas() {
        txtreseq1.findViewById(R.id.txtResEq1);
        txtreseq2.findViewById(R.id.txtResEq2);
        speq1.findViewById(R.id.spEq1);
        speq2.findViewById(R.id.spEq2);
        btnCrear.findViewById(R.id.btnCrearPar);

    }

     */
}