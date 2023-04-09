package com.example.reservas.view;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import androidx.fragment.app.Fragment;
import com.example.reservas.R;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link reservaDatosClientesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class reservaDatosClientesFragment extends Fragment implements View.OnClickListener{

    EditText tNombre, tNacimiento,tDNI, correo, telefono, hospedaje,procedencia;
    View v;
    Button agregar,siguiente;
    List<objPersona> Personalist;
    objPersona persona;
    ListViewAdapter adapter1;
   // ListClientesAdpater adapter;
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
    //    fragment = new reservaDProductoFragment();



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v=inflater.inflate(R.layout.fragment_reserva_datos_clientes, container, false);
        correo=v.findViewById(R.id.txtCorreoDatosClientes);
        procedencia=v.findViewById(R.id.txtProcedenciaDatosCliente);
        hospedaje=v.findViewById(R.id.txtHospedajeDatosCliente);
        telefono=v.findViewById(R.id.txtTelefonoDatosClientes);
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
        //lista.setAdapter(adapter);
        return v;
    }



public void bund(String nombre, String DNI, String fNacimiento ){
    bundle.putString("nombre", nombre );
    bundle.putString("dni", DNI );
    bundle.putString("nacimiento", fNacimiento );
    getParentFragmentManager().setFragmentResult("key",bundle);

}


    public void bunde(String correo, String telefono, String hospedaje, String procedencia ){
        bundle.putString("correo", correo );
        bundle.putString("telefono", telefono );
        bundle.putString("hospedaje", hospedaje );
        bundle.putString("procedencia",procedencia);
        getParentFragmentManager().setFragmentResult("K",bundle);
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