package com.example.reservas.view;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.reservas.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ListCalendarioAdapter extends BaseAdapter {

    Context context;
    objReserva reserva;
    List<objReserva> reservaslist;
    LayoutInflater inflater;
    RelativeLayout rlMainLayout;
    int ultimo;
    private String horasTV[]={"8:00","9:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00","20:00"};


    public ListCalendarioAdapter(Context context, List<objReserva> reservaslist) {
        this.context = context;
        this.reservaslist = reservaslist;
    }

    @Override
    public int getCount() {

        //return reservaslist.size();
        return horasTV.length;
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
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
         View itemView = inflater.inflate(R.layout.calendarlistview, parent, false);
        // Locate the TextViews in listview_item.xml
        txtTitle = (TextView) itemView.findViewById(R.id.txtCalendarListCircuito);
        txtSubTitle = (TextView) itemView.findViewById(R.id.txtCalendarListNombre);
        txtHour = (TextView) itemView.findViewById(R.id.txtCalendarListHora);
       // rlMainLayout = (RelativeLayout) convertView.findViewById(R.id.rlMainLayout);
        // Capture position and set to the TextViews
        String nombre = null;
        String circuito = null;
        String horainicio= null;
        String horafin= null;

        try {
            itemView.setBackgroundColor(Color.BLUE);
            reserva = reservaslist.get(position);
            nombre = reserva.nombreTitular();
            circuito = reserva.getCircuito();
            horainicio = reserva.horaInicio;
            horafin=reserva.horaFin;
            if(!(horainicio.equals(horafin))){
                ultimo=restarHoras(horainicio,horafin);
            }else{
                ultimo=0;
            }

        } catch (Exception e) {
            if(ultimo>0) {
                //reserva = reservaslist.get(position);
                nombre = reserva.nombreTitular();
                circuito = reserva.getCircuito();
                horainicio = (reserva.horaInicio);
               itemView.setBackgroundColor(Color.BLUE);

            }else {
                nombre = "Disponible";
                circuito = "";
                horainicio = horasTV[position];
                itemView.setBackgroundColor(Color.GRAY);
            }
        }
        txtTitle.setText(circuito);
        txtSubTitle.setText(nombre);
        txtHour.setText(horainicio);
        return itemView;

    }
    public int restarHoras(String HoraInicio,String HoraFin){
        int fin=0;
        try {
            //String HoraInicio = "9:15";
            DateFormat inFormat = new SimpleDateFormat("HH:mm");
            //String HoraFin = "10:15";
            Date horainicio = inFormat.parse(HoraInicio);
            Date horafin = inFormat.parse(HoraFin);
            long hora1 = horainicio.getTime();
            long hora2 = horafin.getTime();
            long resultado=hora2-hora1;
            fin=(int)(resultado/3600000);
        } catch (ParseException e ) {
            e.printStackTrace();
        }
        return fin;
    }
}
