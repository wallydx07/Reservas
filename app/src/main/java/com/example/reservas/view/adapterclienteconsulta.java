package com.example.reservas.view;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.reservas.R;
import com.itextpdf.text.DocumentException;

import java.io.IOException;
import java.util.List;

public class adapterclienteconsulta extends BaseAdapter {
    Context context;
    objPersona cliente;
    List<objPersona> clientelist;
    LayoutInflater inflater;
    RelativeLayout rlMainLayout;


    public adapterclienteconsulta(Context context, List<objPersona> clientelist) {
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
        View itemView = inflater.inflate(R.layout.activity_adapterclienteconsulta, parent, false);
        txtnombre = (TextView) itemView.findViewById(R.id.txtNombreClienteAdapter1);
        txtdni = (TextView) itemView.findViewById(R.id.txtDNIClienteAdapter1);
        txtfecha = (TextView) itemView.findViewById(R.id.txtFechaClienteAdapter1);
        borrar=(ImageButton)itemView.findViewById(R.id.imageButton31);
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
                try {
                    new generarPDF().generatePDF(context.getApplicationContext(), "fileName");
                } catch (IOException | DocumentException e) {
                    e.printStackTrace();
                }
            }
        });
        return itemView;
    }
}