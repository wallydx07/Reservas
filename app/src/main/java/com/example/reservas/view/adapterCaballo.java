package com.example.reservas.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.reservas.R;

import java.util.List;

public class adapterCaballo extends BaseAdapter {
    Context context;
    obProductos caballo;
    List<obProductos> pructlist;
    LayoutInflater inflater;
    RelativeLayout rlMainLayout;


    public adapterCaballo(Context context, List<obProductos> pructlist) {
        this.context = context;
        this.pructlist = pructlist;
    }

    public List<obProductos> getData() {
        return pructlist;
    }


    @Override
    public int getCount() {
        return pructlist.size();
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
        View itemView = inflater.inflate(R.layout.cabaloadapter, parent, false);
        txtnombre = (TextView) itemView.findViewById(R.id.txtNombreClienteAdapter);
        borrar=(ImageButton)itemView.findViewById(R.id.imageButton3);
        caballo = pructlist.get(position);
        String nombre = caballo.getNombre();
        txtnombre.setText(nombre);
        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Eliminar el elemento correspondiente en el ArrayList
                pructlist.remove(position);
                notifyDataSetChanged();
            }
        });
        return itemView;
    }
}