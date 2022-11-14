package com.example.futbol.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.futbol.R;
import com.example.futbol.ResumenPartidoActivity;
import com.example.futbol.modelos.Partido;

import java.util.List;

public class PartidosAdapter  extends RecyclerView.Adapter<PartidosAdapter.PartidoVH> {
    private List<Partido> objects;
    private int linea;
    private Context context;

    public PartidosAdapter(List<Partido> objects, int linea, Context context) {
        this.objects = objects;
        this.linea = linea;
        this.context = context;
    }

    @NonNull
    @Override
    public PartidosAdapter.PartidoVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //se encarga de instanciar o inflar tantos elementos como quepan en la pantalla
        View partidoView = LayoutInflater.from(context).inflate(linea,null);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        partidoView.setLayoutParams(lp);
        return new PartidoVH(partidoView);
    }

    @Override
    public void onBindViewHolder(@NonNull PartidosAdapter.PartidoVH holder, int position) {
        //para modificar
        Partido partido = objects.get(position); //numero de posicion que recibe
        holder.lblequ1.setText(partido.getEquipo1());
        holder.lblequ2.setText(partido.getEquipo2());
        holder.lblres.setText(String.valueOf(partido.getRes1()+" - "+partido.getRes2()));

        holder.btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                infoAlert(partido).show();
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ResumenPartidoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("PARTIDO",partido);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    public class PartidoVH extends RecyclerView.ViewHolder{
        TextView lblequ1, lblequ2, lblres;
        ImageButton btnInfo;

        public PartidoVH(@NonNull View itemView) {
            super(itemView);
            lblequ1 = itemView.findViewById(R.id.lbleq1);
            lblequ2 = itemView.findViewById(R.id.lbleq2);
            lblres = itemView.findViewById(R.id.lblresviewhol);
            btnInfo = itemView.findViewById(R.id.btnresumen);
        }
    }
    public AlertDialog infoAlert(Partido partido){
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(context);
        builder.setTitle("El ganador es: ");
        builder.setCancelable(false);

        View partidoDialogModel = LayoutInflater.from(context).inflate(R.layout.partido_dialog_model, null);
        TextView lblGanador = partidoDialogModel.findViewById(R.id.lblgan);

        int local = partido.getRes1();
        int visit = partido.getRes2();

        if(local > visit){
            lblGanador.setText(partido.getEquipo1());
        }else if (visit > local){
            lblGanador.setText(partido.getEquipo2());
        }else if(local == visit){
            lblGanador.setText("Empate");
        }
        builder.setView(partidoDialogModel);
        builder.setNegativeButton("SALIR", null);

        return builder.create();
    }


}
