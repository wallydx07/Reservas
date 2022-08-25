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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class ListCalendarioAdapter extends BaseAdapter {

    Context context;
    objReserva reserva;
    List<objReserva> reservaslist;
    LayoutInflater inflater;
    RelativeLayout rlMainLayout;
    int ultimo;
    private String horasTV[]={"7:00","8:00","9:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00","20:00"};

    public ListCalendarioAdapter(Context context, List<objReserva> reservaslist) {
        this.context = context;
       /* try {
            BurbujaColObj(reservaslist);
        } catch (ParseException e) {
            e.printStackTrace();
        }*/
       // this.reservaslist = acomodar(reservaslist);
       this.reservaslist = reservaslist;

    }
    public static void BurbujaColObj(List<objReserva> reservador) throws ParseException {
        objReserva aux;
        for(int i = 0;i < reservador.size()-1;i++){
            for(int j = 0;j < reservador.size()-i-1;j++){
                DateFormat inFormat = new SimpleDateFormat("HH:mm");
                Date horainicio = inFormat.parse(reservador.get(j+1).getHoraInicio());
                Date horafin = inFormat.parse(reservador.get(j).getHoraInicio());
                long hora1 = horainicio.getTime();
                long hora2 = horafin.getTime();
                if(hora1 < hora2){
                    aux = reservador.get(j+1);
                    reservador.set(j+1,reservador.get(j));
                    reservador.set(j,aux);
                }
            }
        }
    }

    public static int getPositionInVector(String[] vector, String num) {
        int posicion = -1;
        for (int i = 0; i < vector.length; i++) {
            if (vector[i].equals(num)) {
                posicion = i;
                break;
            }
        }
        return posicion;
    }


    public List<objReserva> acomodar(List<objReserva> res){

        List<objReserva> aux= new ArrayList<objReserva>();
        for(int f=0;f<horasTV.length;f++) {
            aux.add(null);
        }
        List<objReserva> Reservas1 = res;
        String inicio="00:00";
        String fin="00:00";
        for( int i=0;i<res.size();i++){
            System.out.println(i+"_");
                objReserva reser = null;
            try {
                reser = Reservas1.get(i);
                inicio=reser.getHoraInicio();
                fin=reser.getHoraFin();
                int dif=restarHoras(inicio,fin);
                int posicion=getPositionInVector(horasTV,inicio);
                System.out.println("indice: "+i);
                System.out.println("se detecto Lahora inicio: "+inicio);
                System.out.println("se detecto Lahora fin: "+fin);
                System.out.println("exixste una diferencia "+dif);
                for(int j=0;j<dif;j++){
                    int newpost=posicion+j;
                    System.out.println("se agregara a la posicion "+newpost);
                    aux.add(newpost,reser);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }




              }
    return aux;
    }

    @Override
    public int getCount() {

       // return reservaslist.size();
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
        System.out.println(position);
        System.out.println(reserva);
        TextView txtTitle, txtSubTitle,txtHour;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
         View itemView = inflater.inflate(R.layout.calendarlistview, parent, false);
        txtTitle = (TextView) itemView.findViewById(R.id.txtCalendarListCircuito);
        txtSubTitle = (TextView) itemView.findViewById(R.id.txtCalendarListNombre);
        txtHour = (TextView) itemView.findViewById(R.id.txtCalendarListHora);
        String nombre = null;
        String circuito = null;
        String horainicio= null;
        String horafin= null;


        if((reservaslist.get(position))!=null){
            reserva = reservaslist.get(position);
            nombre = reserva.nombreTitular();
            circuito = reserva.getCircuito();
            horainicio = horasTV[position];
            horafin=reserva.horaFin;
            itemView.setBackgroundColor(Color.BLUE);
        }else{
                nombre = "Disponible";
                circuito = "Disponible";
                horainicio = horasTV[position];
                itemView.setBackgroundColor(Color.GRAY);
            }

        txtTitle.setText(circuito);
        txtSubTitle.setText(nombre);
        txtHour.setText(horainicio);
        return itemView;

    }
    public int restarHoras(String HoraInicio,String HoraFin){
        int fin=0;
        try {
            DateFormat inFormat = new SimpleDateFormat("HH:mm");
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
