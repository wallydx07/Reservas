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
    private String horas[]={"8:00","9:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00","20:00"};
    private View v;
    List<objReserva> rlist;
    String formattedDate;
    DatabaseReference mDatabase;
    public Calendario() {
        // Required empty public constructor

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Calendario.
     */
    // TODO: Rename and change types and number of parameters
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

        SimpleDateFormat df = new SimpleDateFormat("dd/MMM/yyyy");
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
       // ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity().getApplicationContext() ,R.layout.listview_item,horas);
        //listview.setAdapter(adapter);
        //listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          //  @Override
           // public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
             //   Intent intent=new Intent(getActivity(), nuevaReserva.class);
              //  startActivity(intent);

            //}
        //});
        loadproducto();
        return v;
    }


    public void loadproducto() {
        String fecha="fecha";//textview.getText().toString();
        final List<objReserva>
        rlist = new ArrayList<>();
        //plist=new ArrayList<>();
        final ListView lista =v.findViewById(R.id.listviewhoras);
        //Query ref = FirebaseDatabase.getInstance().getReference().
               // child("Universidades").orderByChild("nombre").equalTo("UAP");
        mDatabase.child("reserva").orderByChild("fecha").equalTo(fecha).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {//ver

                    System.out.println("se van a cargar los elementos");
                    for (DataSnapshot ds : snapshot.getChildren()) {
                       //objReserva res = snapshot.getValue(objReserva.class);
                       //System.out.println(snapshot.getValue(objReserva.class));
                        List<objPersona> plist=new ArrayList<>();
                        //objReserva persona=snapshot.getValue(objReserva.class);
                        System.out.println("====================");
                       System.out.println("====================");
                        String hora = ds.child("horaInicio").getValue().toString();
                        String cabecera = ds.child("circuito").getValue().toString();
                        //String nombre=ds.child("nombre").getValue().toString();
                        String hora0=ds.child("horaInicio").getValue().toString();
                        String guia=ds.child("guia").getValue().toString();
                        String circuito=ds.child("circuito").getValue().toString();
                        //System.out.println(ds.child("personalist").getValue());
                        for (DataSnapshot dsi : ds.child("personalist").getChildren()) {


                            System.out.println(("____________"));
                            String nombre=dsi.child("nombre").getValue().toString();
                            String dni=dsi.child("dni").getValue().toString();

                            String fechaN=dsi.child("fechaN").getValue().toString();

                            String tipo=dsi.child("tipo").getValue().toString();
                            plist.add(new objPersona(nombre,dni,fechaN,tipo));


                        }

                        rlist.add(new objReserva("", hora0, "horaFin", "", "", "", "", guia, circuito, plist, null));

                    }


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
                }else {
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

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }

}