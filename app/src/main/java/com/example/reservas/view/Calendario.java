package com.example.reservas.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.reservas.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Calendario extends Fragment {
    ListCalendarioAdapter adapter;
    private int dia, mes, año;
    private TextView textview;
    private ListView listview;
    private Button botonBorrar;
    private View v;
    private String horasTV[]={"0:00","1:00","2:00","3:00","4:00","5:00","6:00","7:00","8:00","9:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00","20:00","21:00","22:00","23:00",};
    private Spinner spinGuia;
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
        buttonfecha=(Button)getView().findViewById(R.id.buttonCalendarioFecha);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);
        mDatabase= FirebaseDatabase.getInstance().getReference();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        formattedDate = df.format(c);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    // Inflate the layout for this fragment
        v=inflater.inflate(R.layout.fragment_calendario, container, false);


       spinGuia=v.findViewById(R.id.spinnerCalendarioGuia);
        loadGuias();
        textview=v.findViewById(R.id.txtCalendarioFecha);
        textview.setText(formattedDate);
        listview=v.findViewById(R.id.lvCalendarioHoras);
        spinGuia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                loadHoras();
            }
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        textview.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                loadHoras();

            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
            }

        });

       loadHoras();
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

    public void loadSpinner() {

        ArrayList<String>aGuia= new ArrayList<>();
        mDatabase.child("guia").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {//ver
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        String nombre = ds.child("nombre").getValue().toString();
                        int precio= Integer.valueOf(ds.child("precio").getValue().toString());
                        aGuia.add(nombre);
                    }
                    ArrayAdapter<String> adapterGuia = new ArrayAdapter<>(getActivity().getApplication(), android.R.layout.simple_dropdown_item_1line, aGuia);

                    spinGuia.setAdapter(adapterGuia);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

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

    public void loadHoras() {
        String fecha=textview.getText().toString();
        final List<objReserva>rlist = new ArrayList<>();
        final ListView lista =v.findViewById(R.id.lvCalendarioHoras);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        System.out.println(fecha+
        "qwerty44444");
        mDatabase.child("reserva").orderByChild("fecha").equalTo(fecha).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        String guia = ds.child("guia").getValue().toString();
                        try {
                            if (guia.equals(spinGuia.getSelectedItem().toString())) {
                                System.out.println(ds.getKey());
                                List<objPersona> plist = new ArrayList<>();
                                List<obProductos> clist = new ArrayList<>();

                                String fecha=ds.child("fecha").getValue().toString();
                                String hora = ds.child("horaInicio").getValue().toString();
                                String correo = ds.child("correo").getValue().toString();
                                String urlBuenaSalud=ds.child("urlBuenaSalud").getValue().toString();
                                String urlDNI=ds.child("urlDNI").getValue().toString();
                                String usuario = ds.child("usuario").getValue().toString();
                                String hospedaje = ds.child("hospedaje").getValue().toString();
                                String telefono = ds.child("telefono").getValue().toString();
                                String hora0 = ds.child("horaInicio").getValue().toString();
                                String horaFin = ds.child("horaFin").getValue().toString();
                                String circuito = ds.child("circuito").getValue().toString();
                                String deposito= ds.child("deposito").getValue().toString();
                                String procedencia= ds.child("procedencia").getValue().toString();
                                String pendiente = ds.child("pendiente").getValue().toString();
                                for (DataSnapshot dsi : ds.child("personalist").getChildren()) {
                                    String nombre = dsi.child("nombre").getValue().toString();
                                    String dni = dsi.child("dni").getValue().toString();
                                    String fechaN = dsi.child("fechaN").getValue().toString();
                                    String tipo = dsi.child("tipo").getValue().toString();
                                    plist.add(new objPersona(nombre, dni, fechaN, tipo));
                                }
                                for (DataSnapshot dsi : ds.child("caballolist").getChildren()) {
                                    String nombre = dsi.child("nombre").getValue().toString();
                                    String precio = dsi.child("precio").getValue().toString();
                                    obProductos cab=new obProductos(nombre,precio,"caballo");
                                    clist.add(cab);
                                }
                                System.out.println("=============="+clist.size()+"===============");









                                objReserva Res=new objReserva(fecha, hora0, horaFin, correo, telefono, hospedaje, usuario, guia, circuito, plist, clist,pendiente,deposito,procedencia);
                                Res.setUrlDNI(urlDNI);
                                Res.setID(ds.getKey());
                                System.out.println(urlBuenaSalud);
                                Res.setUrlBuenaSalud(urlBuenaSalud);
                                rlist.add(Res);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }else{
                    System.out.println("no se ha enctrado nadi aca");
                }
                BurbujaColObj(rlist);
                Reservalist=acomodar(rlist);
                try {
                    adapter = new ListCalendarioAdapter(getActivity().getApplicationContext(), Reservalist);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //listview.setAdapter(adapter);
                listview.setAdapter((ListAdapter) adapter);
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
                            String horaInicio  = texHorainicio.getText().toString();//si esta disponible le manda la hora de inicio
                            String guia=spinGuia.getSelectedItem().toString();
                            String fecha=textview.getText().toString();
                            Intent intent = new Intent(getActivity(), nuevaReserva.class);
                            Bundle datoenvia = new Bundle();
                            datoenvia.putString("guia",guia);
                            datoenvia.putString("bandera","reserva");
                            datoenvia.putString("horaInicio", horaInicio);
                            datoenvia.putString("fecha",fecha);
                            intent.putExtras(datoenvia);
                            startActivity(intent);
                        }else{
                            Bundle datoenvia = new Bundle();
                            objReserva Reserva=Reservalist.get(i);
                            datoenvia.putString("datos", textItemList);
                            Intent intent = new Intent(getActivity(), ConsultaReserva.class);
                            intent.putExtras(datoenvia);
                            intent.putExtra("reserva", Reserva);
                            startActivity(intent);

                        }
                        Toast.makeText(getContext(), "presiono " + i, Toast.LENGTH_SHORT).show();
                    }
                });
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }
    public void loadGuias(){
        String ID="";

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String usuariuo=user.getDisplayName();
        if (user != null) {
            ID = user.getUid();
        }
final List<String> pguia = new ArrayList<>();
pguia.add(usuariuo);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        String finalID = ID;
        mDatabase.child("guia").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        String nombre = ds.child("nombre").getValue().toString();
                        if(!(nombre.equals(usuariuo))) {
                            pguia.add(nombre);
                            System.out.println("pguia es: " + nombre);
                        }
                    }
                }
                try {
                    ArrayAdapter<String> adapterGuia = new ArrayAdapter<String>(getActivity().getApplication(), android.R.layout.simple_dropdown_item_1line, pguia);
                    spinGuia.setAdapter(adapterGuia);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

}