package com.example.reservas.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.reservas.R;

import java.util.List;

public class ListCalendarioAdapter extends BaseAdapter {

    Context context;
    objReserva reserva;
    List<objReserva> reservaslist;
    LayoutInflater inflater;

    public ListCalendarioAdapter(Context context, List<objReserva> reservaslist) {
        this.context = context;
        this.reservaslist = reservaslist;
    }

    @Override
    public int getCount() {
        return reservaslist.size();
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
        // Declare Variables
        TextView txtTitle, txtSubTitle,txtHour;
        //http://developer.android.com/intl/es/reference/android/view/LayoutInflater.html
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.calendarlistview, parent, false);
        // Locate the TextViews in listview_item.xml
        txtTitle = (TextView) itemView.findViewById(R.id.txtCalendarListCircuito);
        txtSubTitle = (TextView) itemView.findViewById(R.id.txtCalendarListNombre);
        txtHour = (TextView) itemView.findViewById(R.id.txtCalendarListHora);

        // Capture position and set to the TextViews
        reserva = reservaslist.get(position);
        String nombre = reserva.nombreTitular();
        String circuito = reserva.getCircuito();
        String hora=reserva.horaInicio;
        txtTitle.setText(circuito);
        txtSubTitle.setText(nombre);
        txtHour.setText(hora);
        return itemView;

    }
}
