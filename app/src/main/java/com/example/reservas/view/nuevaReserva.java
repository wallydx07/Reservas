package com.example.reservas.view;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
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
    List<objPersona> Personalist;
    Button siguiente;
    ListClientesAdpater adapter;
    reservaDatosClientesFragment cliente=new reservaDatosClientesFragment();
    reservaDProductoFragment producto=new reservaDProductoFragment();



    public nuevaReserva() {


    }

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        Bundle parametros = this.getIntent().getExtras();
        if(parametros !=null) {

            String bandera=parametros.getString("bandera");
            if(bandera.equals("reserva")) {
                horaInicio = parametros.getString("horaInicio");
                fecha = parametros.getString("fecha");
                guia = parametros.getString("guia");
            }
            if(bandera.equals("editar")){
                reserva  = (objReserva) getIntent().getSerializableExtra("reserva");
                horaInicio =reserva.getHoraInicio();
                fecha = reserva.getFecha();
                guia = reserva.getGuia();
            }

        }
           // setContentView(R.layout.nuevareserva);
        setContentView(R.layout.nuevares);
            //tablayout=findViewById(R.id.tabnuevareserva);
            //viewpager=findViewById(R.id.viewventa);
        tNombre=(EditText)findViewById(R.id.txtDatosApellidoyNombre);
        loadFragment(cliente);
        Personalist = new ArrayList<>();
            //tab1=findViewById(R.id.tabCLiente);
            //tab2=findViewById(R.id.tabReserva);
            //pagercontroller=new PagerController(getSupportFragmentManager(),tablayout.getTabCount());
            //viewpager.setAdapter(pagercontroller);
            /*tablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    viewpager.setCurrentItem(tab.getPosition());
                    if(tab.getPosition()==0) {
                        pagercontroller.notifyDataSetChanged();

}
                    if(tab.getPosition()==1){
                        pagercontroller.notifyDataSetChanged();

}
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });
             viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));
       */
        /*
       // pasarparametros();
             */
    }

    public void loadFragment(Fragment fragment){
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_reserva,fragment);
        transaction.commit();




    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {

            case R.id.btSiguienteReservaDatosCliente:
                loadFragment(producto);
                break;

            case R.id.bottomDatosnAgregar:
                String nombre=cliente.tNombre.getText().toString();
                String DNI=cliente.tDNI.getText().toString();
                String fNacimiento=cliente.tNacimiento.getText().toString();
                persona=new objPersona(nombre,DNI,fNacimiento,"cliente");
                Personalist.add(persona);
                adapter = new ListClientesAdpater(cliente.getContext(), Personalist);
                cliente.lista.setAdapter(adapter);
                cliente.tNombre.setText("");
                cliente. tDNI.setText("");
                cliente.tNacimiento.setText("");
             break;

















        }





    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}



