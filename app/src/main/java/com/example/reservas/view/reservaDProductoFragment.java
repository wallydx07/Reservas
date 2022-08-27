package com.example.reservas.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;

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
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link reservaDProductoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class reservaDProductoFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    View v;
    List<objPersona> personalist;
    List<obProductos> cabalgatalist;
    List<obProductos> pcircuito;
    objReserva miReserva;
    objPersona persona;
    Spinner spinCircuito,spinCaballo, spinhoras;
    EditText nombre,dni,fechanNacimiento, total, anticipo, pendiente;
    String correo,telefono,hospedaje;
    ListView datoscaballos;
    DatabaseReference mDatabase;
    Switch cabalgata;
    Button finalizar;
    ImageButton agregar;
    FirebaseUser user;
    String horaInicio,fecha,guia,User;
    int cant;

    public reservaDProductoFragment() {
        // Required empty publifffc constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment reservaDProductoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static reservaDProductoFragment newInstance(String param1, String param2) {
        reservaDProductoFragment fragment = new reservaDProductoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        horaInicio =((nuevaReserva)this.getActivity()).horaInicio;
        fecha=((nuevaReserva)this.getActivity()).fecha;
        guia=((nuevaReserva)this.getActivity()).guia;
        user = FirebaseAuth.getInstance().getCurrentUser();
        super.onCreate(savedInstanceState);
        personalist = new ArrayList<>();
        mDatabase= FirebaseDatabase.getInstance().getReference();
        cabalgatalist=new ArrayList<>();
        getParentFragmentManager().setFragmentResultListener("key",this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String nombreO=result.getString("nombre");
                String dni=result.getString("dni");
                String nacimineto=result.getString("nacimiento");
                persona=new objPersona(nombreO,dni,nacimineto,"Cliente");
                personalist.add(persona);
                }
        });
        getParentFragmentManager().setFragmentResultListener("K",this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                correo=result.getString("correo");
                telefono=result.getString("telefono");
                hospedaje=result.getString("hospedaje");

            }
        });
      }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        v=inflater.inflate(R.layout.fragment_reserva_d_producto, container, false);
        spinCircuito=v.findViewById(R.id.spinnerReservaProductoCircuito);
        spinCaballo=v.findViewById(R.id.spinnerReservaProductoCaballo);
        spinhoras =v.findViewById(R.id.spinnerReservaProductoHoras);
        total=v.findViewById(R.id.txtReservaProductoTotal);
        anticipo=v.findViewById(R.id.txtReservaProductoAnticipo);
        pendiente=v.findViewById(R.id.txtReservaProductoPendiente);
        agregar=v.findViewById(R.id.buttonReservaProductoagregar);
        agregar.setOnClickListener(this);
        datoscaballos=v.findViewById(R.id.listViewReservaProducto);
        cabalgata=v.findViewById(R.id.switchCaballo);
        finalizar=v.findViewById(R.id.buttonReservaProductoFinalizar);
        finalizar.setOnClickListener(this);
        cabalgata=v.findViewById(R.id.switchCaballo);
        agregar.setEnabled(false);
        spinCaballo.setEnabled(false);
        cabalgata.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    agregar.setEnabled(isChecked);
                    spinCaballo.setEnabled(isChecked);
                    if(isChecked){
                        loadcaballo();
                    }

            }

        });


        loadproducto();
        return v;
    }



    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.buttonReservaProductoagregar:

                List<obProductos> cabalgata=new ArrayList<>();
                String nombre=spinCaballo.getSelectedItem().toString();
                for (int i = 0; i< cabalgatalist.size(); i++) {
                    obProductos productosss= cabalgatalist.get(i);
                    if(productosss.getNombre().equals(nombre)){
                        cabalgata.add(productosss);
                    }
                }
                ArrayAdapter<obProductos> adapter=new ArrayAdapter<obProductos>(getActivity().getApplicationContext(),R.layout.listview_item,cabalgata);
                datoscaballos.setAdapter(adapter);
                break;
            case R.id.buttonReservaProductoFinalizar:
                int Fin=Integer.valueOf(spinhoras.getSelectedItem().toString());
                Fin=Integer.valueOf(horaInicio)+Fin;
                objReserva reserva=new objReserva(fecha, horaInicio, String.valueOf(Fin),  correo,  telefono,  hospedaje,  user.toString(),guia, spinCircuito.getSelectedItem().toString(), personalist,cabalgatalist);
                writeNewReserva(reserva);

                break;
        }
    }

    private void writeNewReserva(objReserva reserva) {
        mDatabase.child("reserva").push().setValue(reserva);
    }
    public void loadproducto() {
        final List<obProductos> pcabalgata = new ArrayList<>();
        //final List<obProductos> pcircuito = new ArrayList<>();
        pcircuito = new ArrayList<>();
        this.cabalgatalist = new ArrayList<>();
        mDatabase.child("producto").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {//ver
                    System.out.println("se van a cargar los elementos");
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        String nombre = ds.child("nombre").getValue().toString();
                        String tipo = ds.child("tipo").getValue().toString();
                        int precio= Integer.valueOf(ds.child("precio").getValue().toString());
                        if(tipo.equals("Circuito")){
                            pcircuito.add(new obProductos(nombre, precio,tipo));
                         }
                        if(tipo.equals("Caballo")){
                            reservaDProductoFragment.this.cabalgatalist.add(new obProductos(nombre, precio,tipo));
 }


                    }
                   // ArrayAdapter<obProductos> adaptercaballo = new ArrayAdapter<>(getActivity().getApplication(), android.R.layout.simple_dropdown_item_1line, reservaDProductoFragment.this.cabalgatalist);
                    ArrayAdapter<obProductos> adaptercircuito = new ArrayAdapter<>(getActivity().getApplication(), android.R.layout.simple_dropdown_item_1line, pcircuito);
                 //   spinCaballo.setAdapter(adaptercaballo);
                    spinCircuito.setAdapter(adaptercircuito);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void loadcaballo() {
        final List<obProductos> pcabalgata = new ArrayList<>();
        //final List<obProductos> pcircuito = new ArrayList<>();
        //this.cabalgata = new ArrayList<>();
        pcircuito = new ArrayList<>();
        mDatabase.child("reserva").orderByChild("fecha").equalTo(fecha).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {//ver
                    System.out.println("se van a cargar los elementos");
                    int precio=0;
                    String tipo="";
                    String caballonombre="";
                    long hora1=0;
                    long hora2=0;
                    try {
                        DateFormat inFormat = new SimpleDateFormat("HH:mm");
                        Date horainicio = inFormat.parse(horaInicio);
                        Date horafin = inFormat.parse(spinhoras.getSelectedItem().toString());
                        System.out.println("las horas "+horainicio);
                        System.out.println("las horas "+horafin);
                        hora1 = (horainicio.getTime()/3600000)-3;//hora d einicio desde el listviewanterior
                        hora2 = (horafin.getTime()/3600000)-3+hora1;//+hora1;//hora inicio desde el spiner actual
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        System.out.println(ds.child("circuito").getValue().toString());



                        long horarec=0;
                        String horaI = ds.child("horaInicio").getValue().toString();
                        try {
                            DateFormat inFormat = new SimpleDateFormat("HH:mm");
                            Date horainicio = inFormat.parse(horaI);
                            horarec = (horainicio.getTime()/3600000)-3;
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        System.out.println("estas son las horas");
                        System.out.println("hora a la q posiblemete c/o"+horarec);
                        System.out.println("hora q pretndo iniciar"+hora1);
                        System.out.println("hora que pretendo finalizar"+hora2);
                        if((horarec>hora1)&&(horarec<hora2)){
                             for (DataSnapshot dsi : ds.child("caballolist").getChildren()){
                                 System.out.println("estee s caballilisto");

                                 try {
                                     caballonombre = dsi.child("nombre").getValue().toString();
                                     precio = Integer.valueOf(dsi.child("precio").getValue().toString());
                                     tipo=dsi.child("tipo").getValue().toString();

                                 } catch (Exception e) {
                                     e.printStackTrace();
                                 }


                                 System.out.println(caballonombre);
                                 obProductos caballo=new obProductos(caballonombre,precio,tipo);
                                 pcabalgata.add(caballo);
                             }
                             pcabalgata.add(new obProductos(caballonombre, precio,tipo));
                        }else{
                            System.out.println("hora no concuerda");
                        }
                   }
                    boolean disponible=true;
                    List<obProductos> auxadapter = new ArrayList<>();
                    for(int i = 0; i < cabalgatalist.size(); i++) {
                        obProductos auxpro=cabalgatalist.get(i);
                        for(int j = 0; j < pcabalgata.size(); j++) {
                            obProductos auxpro1=pcabalgata.get(j);
                            if((auxpro.getNombre()).equals(auxpro1.getNombre())){
                                disponible=false;
                            }else {
                                disponible=true;
                            }

                        }
                        if(disponible){
                            auxadapter.add(auxpro);
                        }

                    }
                    ArrayAdapter<obProductos> adaptercaballo = new ArrayAdapter<>(getActivity().getApplication(), android.R.layout.simple_dropdown_item_1line, auxadapter);
                    spinCaballo.setAdapter(adaptercaballo);
                }else{
                    System.out.println("no exiten datos");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}