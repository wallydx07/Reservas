package com.example.reservas.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.reservas.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link reservaDatosClientesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class reservaDatosClientesFragment extends Fragment implements View.OnClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    EditText tNombre, tNacimiento,tDNI;
    View v;
    Button agregar;
    List<objPersona> Personalist;
    ListViewAdapter adapter1;
    ListView lista;

    public reservaDatosClientesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment reservaDatosClientesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static reservaDatosClientesFragment newInstance(String param1, String param2) {
        reservaDatosClientesFragment fragment = new reservaDatosClientesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v=inflater.inflate(R.layout.fragment_reserva_datos_clientes, container, false);
        tNombre=v.findViewById(R.id.txtDatosApellidoyNombre);
        tNacimiento=v.findViewById(R.id.txtDAtosFechaNacimiento);
        tDNI=v.findViewById(R.id.txtDatosDNI);
        agregar=v.findViewById(R.id.bottomDatosnAgregar);
        agregar.setOnClickListener(this);
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
                objPersona persona=new objPersona(nombre,DNI,fNacimiento,"nose");
                Personalist.add(persona);
                ArrayAdapter<objPersona> adapter=new ArrayAdapter<objPersona>(getActivity().getApplicationContext(),R.layout.listview_item,Personalist);
                lista.setAdapter(adapter);
                tNombre.setText("");
                tDNI.setText("");
                tNacimiento.setText("");
                break;

        }
    }
}