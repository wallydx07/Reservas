package com.example.reservas.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.reservas.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Calendario#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Calendario extends Fragment {
    ListCalendarioAdapter adapter;
    private int dia, mes, año;
    private TextView textview;
    private ListView listview;
    private View v;
    private String horasTV[]={"7:00","8:00","9:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00","20:00"};

    List<objReserva> Reservalist;
    String formattedDate;
    DatabaseReference mDatabase;
    reservaDProductoFragment fragment;
    public Calendario() {
        // Required empty public constructor

    }

    public Calendario newInstance(String param1, String param2) {
        Calendario fragment = new Calendario();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        Button buttonfecha;
        buttonfecha=(Button)getView().findViewById(R.id.buttonfecha);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);
        mDatabase= FirebaseDatabase.getInstance().getReference();
        SimpleDateFormat df = new SimpleDateFormat("dd/mm/yyyy");
        formattedDate = df.format(c);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    // Inflate the layout for this fragment
        v=inflater.inflate(R.layout.fragment_calendario, container, false);
       textview=v.findViewById(R.id.txtfecha);
       textview.setText(formattedDate);
       listview=v.findViewById(R.id.listviewhoras);
       loadproducto();
        return v;
    }


    public static void BurbujaColObj(List<objReserva> reservador) {
        objReserva aux;
        for(int i = 0;i < reservador.size()-1;i++){
            for(int j = 0;j < reservador.size()-i-1;j++){

                try {
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
                } catch (ParseException e) {
                    e.printStackTrace();
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

    public void loadproducto() {
        String fecha="fecha";//textview.getText().toString();
        final List<objReserva>rlist = new ArrayList<>();
        final ListView lista =v.findViewById(R.id.listviewhoras);
        //Query ref = FirebaseDatabase.getInstance().getReference().
               // child("Universidades").orderByChild("nombre").equalTo("UAP");
        mDatabase.child("reserva").orderByChild("fecha").equalTo(fecha).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        System.out.println(ds.getKey());
                        List<objPersona> plist=new ArrayList<>();
                        String hora = ds.child("horaInicio").getValue().toString();
                        String cabecera = ds.child("circuito").getValue().toString();
                        String hora0=ds.child("horaInicio").getValue().toString();
                        String horaFin=ds.child("horaFin").getValue().toString();
                        String guia=ds.child("guia").getValue().toString();
                        String circuito=ds.child("circuito").getValue().toString();
                        for (DataSnapshot dsi : ds.child("personalist").getChildren()){
                            String nombre=dsi.child("nombre").getValue().toString();
                            String dni=dsi.child("dni").getValue().toString();
                            String fechaN=dsi.child("fechaN").getValue().toString();
                            String tipo=dsi.child("tipo").getValue().toString();
                            plist.add(new objPersona(nombre,dni,fechaN,tipo));
                        }
                        rlist.add(new objReserva("", hora0, horaFin, "", "", "", "", guia, circuito, plist, null));
                    }
                    BurbujaColObj(rlist);
                    Reservalist=acomodar(rlist);
                    adapter = new ListCalendarioAdapter(getActivity().getApplicationContext(), Reservalist);
                    listview.setAdapter(adapter);
                    listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView adapterView, View view, int i, long l) {
                            //Se busca la referencia del TextView en la vista.
                            TextView textView = (TextView)view.findViewById(R.id.txtCalendarListNombre);
                            //Obtiene el texto dentro del TextView.
                            String textItemList  = textView.getText().toString();
                            System.out.println(textItemList);
                            if(textItemList.equals("Disponible")){
                                TextView texHorainicio = (TextView)view.findViewById(R.id.txtCalendarListHora);
                                //Obtiene el texto dentro del TextView.
                                String cf  = texHorainicio.getText().toString();//si esta disponible le manda la hora de inicio
                                Intent intent = new Intent(getActivity(), nuevaReserva.class);
                                Bundle datoenvia = new Bundle();
                                datoenvia.putString("datos", cf);
                                intent.putExtras(datoenvia);
                                startActivity(intent);
                                }else{
                                Bundle datoenvia = new Bundle();
                                objReserva Reserva=Reservalist.get(i);
                                datoenvia.putString("datos", textItemList);

                                Intent intent = new Intent(getActivity(), ConsultaReserva.class);
                              //  intent.putExtras(datoenvia);
                               intent.putExtra("reserva", Reserva);
                                startActivity(intent);

                            }
                            Toast.makeText(getContext(), "presiono " + i, Toast.LENGTH_SHORT).show();
                        }
                    });
                }else {/*
                    List<objPersona> plist=new ArrayList<>();
                    plist.add(new objPersona("Nombre","DNI","00000","Cliente"));
                    rlist.add(new objReserva(""," hora0", "horaFin", "", "", "", "", "guia", "circuito", plist, null));
                    adapter = new ListCalendarioAdapter(getActivity().getApplicationContext(), rlist);
                    listview.setAdapter(adapter);
                    listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView adapterView, View view, int i, long l) {
                            Intent intent=new Intent(getActivity(), nuevaReserva.class);
                            startActivity(intent);
                            Toast.makeText(getContext(), "presiono " + i, Toast.LENGTH_SHORT).show();
                        }
                    });

                */}
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }

}