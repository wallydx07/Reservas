package com.example.reservas.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.reservas.R;

import java.util.List;

public class ConsultaReserva extends AppCompatActivity {
    objReserva Reserva;
    TextView circuito,fecha,horapartida,horallegada,deposito,pendiente;
    ListView clientes, caballos;
    List<objPersona> Personalist;
    List<objCaballo> Caballolist;
    objPersona persona;
    ListViewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_reserva);
        Bundle parametros = this.getIntent().getExtras();
        if(parametros !=null){

            String datos = parametros.getString("datos");
            Reserva  = (objReserva) getIntent().getSerializableExtra("reserva");
            Personalist=Reserva.getPersonalist();
            fecha=(TextView)findViewById(R.id.txtConsultaReservaCircuito);
            horapartida=(TextView)findViewById(R.id.txtConsutlaReservaHoraPartida);
            horallegada=(TextView)findViewById(R.id.txtConsutlaReservaHoraRegreso);
            deposito=(TextView)findViewById(R.id.txtConsutlaReservaDeposito);
            pendiente=(TextView)findViewById(R.id.txtConsutlaReservaPendiente);
            clientes=(ListView)findViewById(R.id.lvConsutlaReservaclientes);
            caballos=(ListView)findViewById(R.id.lvConsutlaReservaCaballos);
            fecha.setText(Reserva.getFecha());
            horapartida.setText(Reserva.getHoraInicio());
            horallegada.setText(Reserva.getHoraFin());
            //deposito.setText(Reserva.get);
            //pendiente.setText(Reserva.);
            ArrayAdapter<objPersona> adapter=new ArrayAdapter<objPersona>(this.getApplicationContext(),R.layout.listview_item,Personalist);
            clientes.setAdapter(adapter);






            System.out.println(Reserva);


            //Reserva=parametros.getString("datos");
            System.out.println(datos);
        }
    }
}