package com.example.reservas.view;
import android.app.FragmentManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.example.reservas.R;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class nuevaReserva extends AppCompatActivity implements View.OnClickListener  {
    TabLayout tablayout;
    ViewPager viewpager;
    TabItem tab1,tab2;
    EditText tNombre, tNacimiento,tDNI, correo, telefono, hospedaje;
    PagerController pagercontroller;
    String horaInicio,fecha,guia;
    objReserva reserva;
    objPersona persona;
    ListViewAdapter adapter1;
    String bandera;
    public List<objPersona> getPersonalist() {
        return Personalist;
    }

    public void setPersonalist(List<objPersona> personalist) {
        Personalist = personalist;
    }

    List<objPersona> Personalist, personafinal;
    Button siguiente;
    ListClientesAdpater adapter;

    reservaDatosClientesFragment cliente=new reservaDatosClientesFragment();
    reservaDProductoFragment producto=new reservaDProductoFragment();
   Bundle bundle = new Bundle();
    public nuevaReserva() {
    }

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        loadFragment(cliente);
        Bundle parametros = this.getIntent().getExtras();
        // setContentView(R.layout.nuevareserva);
        setContentView(R.layout.nuevares);
        tNombre=(EditText)findViewById(R.id.txtDatosApellidoyNombre);
       // pers=(ListView)findViewById(R.id.listViewDatosClientes);
        Personalist = new ArrayList<objPersona>();
        if(parametros !=null) {

            bandera=parametros.getString("bandera");

            if(bandera.equals("reserva")) {
                horaInicio = parametros.getString("horaInicio");
                System.out.println("______________________________"+horaInicio+"______________________________");
                fecha = parametros.getString("fecha");
                guia = parametros.getString("guia");
            }
            if(bandera.equals("editar")){
                reserva  = (objReserva) getIntent().getSerializableExtra("reserva");
                horaInicio =reserva.getHoraInicio();
                fecha = reserva.getFecha();
                guia = reserva.getGuia();
                Personalist=reserva.getPersonalist();
                cliente.Personalist=Personalist;
               // adapter = new ListClientesAdpater(cliente.getContext(), Personalist);
               // if (cliente.lista != null) {
              //  adapter = new ListClientesAdpater(cliente.getContext(), Personalist);
              //      cliente.lista.setAdapter(adapter);
              //  }
            }

        }




    }

    public void loadFragment(Fragment fragment){
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_reserva,fragment);
        transaction.commit();

    }
    public void mandarlist(){

        personafinal=new ArrayList<objPersona>();
        objPersona persona1;
        ListClientesAdpater adapter1 = (ListClientesAdpater) cliente.lista.getAdapter();
        if (adapter1 != null) {
            List<objPersona> allData =adapter1.getData();
            for (int i = 0; i < adapter1.getCount(); i++) {

                persona1=allData.get(i);
                String nombre= persona1.getNombre();
                String DNI=persona1.getDni();
                String fNacimiento=persona1.getFechaN();
                personafinal.add(new objPersona(nombre,DNI,fNacimiento,"Cliente"));


               // cliente.bund(nombre,DNI,fNacimiento);
                System.out.println("=============="+i+"-"+nombre+"=============");
            }
        }
    }
    @Override
    public void onClick(View view) {
        switch(view.getId()) {

            case R.id.btSiguienteReservaDatosCliente:
                int count=0;
                ListClientesAdpater adapterb = (ListClientesAdpater) cliente.lista.getAdapter();
                if (adapterb != null) {
                    count = adapterb.getCount();
                    System.out.println(count);
                }
                String correo=cliente.correo.getText().toString();
                String hospedaje=cliente.hospedaje.getText().toString();
                String telefono=cliente.telefono.getText().toString();
                String origen=cliente.procedencia.getText().toString();
                if (correo.isEmpty() || hospedaje.isEmpty() ||telefono.isEmpty()||count==0) {
                    Toast.makeText(getApplicationContext(), "Por favor complete todos los campos", Toast.LENGTH_SHORT).show();

                }else {
                    mandarlist();
                    cliente.bunde(correo,hospedaje,telefono,origen);
                    loadFragment(producto);
                }

                break;
            case R.id.bottomDatosnAgregar:
                String nombre=cliente.tNombre.getText().toString();
                String DNI=cliente.tDNI.getText().toString();
                String fNacimiento=cliente.tNacimiento.getText().toString();

                if (nombre.isEmpty() || DNI.isEmpty() ||fNacimiento.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Por favor complete todos los campos", Toast.LENGTH_SHORT).show();

                }else{
                    persona=new objPersona(nombre,DNI,fNacimiento,"cliente");
                    //    cliente.bund(nombre,DNI,fNacimiento);
                    Personalist.add(persona);
                    adapter = new ListClientesAdpater(cliente.getContext(), Personalist);
                    cliente.lista.setAdapter(adapter);
                    cliente.tNombre.setText("");
                    cliente. tDNI.setText("");
                    cliente.tNacimiento.setText("");

                }





             break;

        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}



