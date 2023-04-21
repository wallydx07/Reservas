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

public class ReservasAdapter extends BaseAdapter {
    Context context;
    objReserva reserva;
    List<objReserva> reservalist;
    LayoutInflater inflater;
    RelativeLayout rlMainLayout;


    public ReservasAdapter(Context context, List<objReserva> reservalist) {
        this.context = context;
        this.reservalist = reservalist;
    }

    public List<objReserva> getData() {
        return reservalist;
    }


    @Override
    public int getCount() {
        return reservalist.size();
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
        TextView tctTitular, txtCantitad,txTotal;
        ImageButton borrar;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.activity_reservas_adapter, parent, false);
        tctTitular = (TextView) itemView.findViewById(R.id.tvTitular);
        txtCantitad = (TextView) itemView.findViewById(R.id.tvCantidad);
        txTotal = (TextView) itemView.findViewById(R.id.tvTotal);
        reserva = reservalist.get(position);
        String titular = reserva.Personalist.get(0).getNombre().toString();
        String personas=String.valueOf(reserva.Personalist.size());
        String total=reserva.getTotal();
        tctTitular.setText("Titular: "+titular);
        txtCantitad.setText("Clientes: "+personas);
        txTotal.setText("importe: "+total+"$");
        return itemView;
    }
}