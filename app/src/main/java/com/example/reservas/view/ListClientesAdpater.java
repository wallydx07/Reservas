package com.example.reservas.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.reservas.R;

import java.util.List;

public class ListClientesAdpater extends BaseAdapter {
    Context context;
    objPersona cliente;
    List<objPersona> clientelist;
    LayoutInflater inflater;
    RelativeLayout rlMainLayout;


    public ListClientesAdpater(Context context, List<objPersona> clientelist) {
        this.context = context;
        this.clientelist = clientelist;
    }

    public List<objPersona> getData() {
        return clientelist;
    }


    @Override
    public int getCount() {
        return clientelist.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView txtnombre, txtdni,txtfecha;
        ImageButton borrar;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.clientesadapter, parent, false);
        txtnombre = (TextView) itemView.findViewById(R.id.txtNombreClienteAdapter);
        txtdni = (TextView) itemView.findViewById(R.id.txtDNIClienteAdapter);
        txtfecha = (TextView) itemView.findViewById(R.id.txtFechaClienteAdapter);
        borrar=(ImageButton)itemView.findViewById(R.id.imageButton3);
        borrar.setImageResource(R.drawable.eliminar);
        cliente = clientelist.get(position);
        String nombre = cliente.getNombre();
        String dni=  cliente.getDni();
        String fecha =cliente.getFechaN();
        txtnombre.setText(nombre);
        txtdni.setText(dni);
        txtfecha.setText(fecha);
        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Eliminar el elemento correspondiente en el ArrayList
                clientelist.remove(position);
                notifyDataSetChanged();
            }
        });
        return itemView;
    }
}