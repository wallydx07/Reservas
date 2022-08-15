package com.example.reservas.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;

import com.example.reservas.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link reservaDProductoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class reservaDProductoFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
View v;
    Spinner spinCircuito,spinCaballo;
    EditText nombre,dni,fechanacimiento,correo,telefono,hospedaje, total, anticipo, pendiente;
    ListView datoscaballos;
    DatabaseReference mDatabase;
    Switch cabalgata;
    Button finalizar;
    ImageButton agregar;
    int cant;
    objReserva miReserva;
    List<obProductos> cabalgatalist;
    List<obProductos> pcaballo;
    List<obProductos> pcircuito;
    public reservaDProductoFragment() {
        // Required empty public constructor
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
        super.onCreate(savedInstanceState);
        mDatabase= FirebaseDatabase.getInstance().getReference();
        cabalgatalist=new ArrayList<>();
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        v=inflater.inflate(R.layout.fragment_reserva_d_producto, container, false);
        spinCircuito=v.findViewById(R.id.spinnerReservaProductoCircuito);
        spinCaballo=v.findViewById(R.id.spinnerReservaProductoCaballo);
        total=v.findViewById(R.id.txtReservaProductoTotal);
        anticipo=v.findViewById(R.id.txtReservaProductoAnticipo);
        pendiente=v.findViewById(R.id.txtReservaProductoPendiente);
        agregar=v.findViewById(R.id.buttonReservaProductoagregar);
        agregar.setOnClickListener(this);
        datoscaballos=v.findViewById(R.id.listViewReservaProducto);
        cabalgata=v.findViewById(R.id.switchCaballo);
        cabalgata.setOnClickListener(this);
        finalizar=v.findViewById(R.id.buttonReservaProductoFinalizar);
        finalizar.setOnClickListener(this);
        loadproducto();
        return v;
    }



    public void writeNewReserva( String name, int price, String type) {

        obProductos producto = new obProductos(name,price,type);
        mDatabase.child("producto").push().setValue(producto);
    }



    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.buttonReservaProductoagregar:
                String nombre=spinCaballo.getSelectedItem().toString();

                for (int i=0;i<pcaballo.size();i++) {
                    obProductos productosss=pcaballo.get(i);
                    if(productosss.getNombre().equals(nombre)){
                        cabalgatalist.add(productosss);
                    }
                }
                ArrayAdapter<obProductos> adapter=new ArrayAdapter<obProductos>(getActivity().getApplicationContext(),R.layout.listview_item,cabalgatalist);
                datoscaballos.setAdapter(adapter);
                break;
            case R.id.buttonReservaProductoFinalizar:
                objReserva mireserva=new objReserva();
                break;
        }
    }

    public void loadproducto() {
        //final List<obProductos> pcaballo = new ArrayList<>();
        //final List<obProductos> pcircuito = new ArrayList<>();
         pcaballo = new ArrayList<>();
        pcircuito = new ArrayList<>();
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
                            pcaballo.add(new obProductos(nombre, precio,tipo));
 }


                    }
                    ArrayAdapter<obProductos> adaptercaballo = new ArrayAdapter<>(getActivity().getApplication(), android.R.layout.simple_dropdown_item_1line, pcaballo);
                    ArrayAdapter<obProductos> adaptercircuito = new ArrayAdapter<>(getActivity().getApplication(), android.R.layout.simple_dropdown_item_1line, pcircuito);
                    spinCaballo.setAdapter(adaptercaballo);
                    spinCircuito.setAdapter(adaptercircuito);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


}