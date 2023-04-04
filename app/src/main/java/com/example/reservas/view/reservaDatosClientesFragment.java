package com.example.reservas.view;

import static android.app.Activity.RESULT_OK;

import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.reservas.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link reservaDatosClientesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class reservaDatosClientesFragment extends Fragment implements View.OnClickListener{

    EditText tNombre, tNacimiento,tDNI, correo, telefono, hospedaje;
    View v;
    Button agregar,siguiente;
    List<objPersona> Personalist;
    objPersona persona;
    ListViewAdapter adapter1;
    ListClientesAdpater adapter;
    ListView lista;
    Bundle bundle;
    reservaDProductoFragment fragment;
    public reservaDatosClientesFragment() {
        // Required empty public constructor
    }

    public static reservaDatosClientesFragment newInstance(String param1, String param2) {
        reservaDatosClientesFragment fragment = new reservaDatosClientesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = new Bundle();
        fragment = new reservaDProductoFragment();



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v=inflater.inflate(R.layout.fragment_reserva_datos_clientes, container, false);
        correo=v.findViewById(R.id.txtCorreoDatosClientes);

       /* correo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String myMessage = correo.getText().toString();
                bundle.putString("correo", myMessage );
                getParentFragmentManager().setFragmentResult("K",bundle);

            }

            @Override
            public void afterTextChanged(Editable editable) {
                System.out.println("se ha dehado de actulizar");


            }
        });*/
        telefono=v.findViewById(R.id.txtTelefonoDatosClientes);
       /* telefono.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String myMessage = telefono.getText().toString();
                bundle.putString("telefono", myMessage );
                getParentFragmentManager().setFragmentResult("K",bundle);

            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });*/
        /*hospedaje=v.findViewById(R.id.txtHospedajeDatosCliente);
        hospedaje.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String myMessage = hospedaje.getText().toString();
                bundle.putString("hospedaje", myMessage );
                getParentFragmentManager().setFragmentResult("K",bundle);

            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });*/
        siguiente=v.findViewById(R.id.btSiguienteReservaDatosCliente);
        siguiente.setOnClickListener(((nuevaReserva)this.getActivity()));
        tNombre=v.findViewById(R.id.txtDatosApellidoyNombre);
        tNacimiento=v.findViewById(R.id.txtDAtosFechaNacimiento);
        tDNI=v.findViewById(R.id.txtDatosDNI);
        agregar=v.findViewById(R.id.bottomDatosnAgregar);
        //agregar.setOnClickListener(this);
        agregar.setOnClickListener(((nuevaReserva)this.getActivity()));
        Personalist = new ArrayList<>();
        lista =v.findViewById(R.id.listViewDatosClientes);
        return v;
    }





    @Override
    public void onClick(View view) {
        switch(view.getId()) {
          case R.id.bottomDatosnAgregar:
                String nombre=String.valueOf(tNombre.getText());
                String DNI=String.valueOf(tDNI.getText());
                String fNacimiento=String.valueOf(tNacimiento.getText());
                persona=new objPersona(nombre,DNI,fNacimiento,"nose");
                Personalist.add(persona);
                ArrayAdapter<objPersona> adapter=new ArrayAdapter<objPersona>(getActivity().getApplicationContext(),R.layout.clientesadapter,Personalist);
                lista.setAdapter(adapter);
                tNombre.setText("");
                tDNI.setText("");
                tNacimiento.setText("");
                bundle.putString("nombre", nombre );
                bundle.putString("dni", DNI );
                bundle.putString("nacimiento", fNacimiento );
                getParentFragmentManager().setFragmentResult("key",bundle);
                break;

        }
    }






}