package com.example.reservas.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.reservas.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class consultaHorario extends AppCompatActivity {
    ReservasAdapter adapter;
    objReserva Reserva;
    objHorario horario;
    TextView circuito,fecha,horapartida,horallegada,deposito,pendiente,guia,procedencia,hospedaje,telefono,usuario,correo;
    ListView reservalv, caballos;
    Button agregar;
    int index;
    List<objPersona> Personalist;
    List<objReserva> Reservalist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_horario);
        Bundle parametros = this.getIntent().getExtras();
        if(parametros !=null){
            horario=(objHorario) getIntent().getSerializableExtra("horario");
            Reservalist=horario.getReservalist();
            guia=(TextView)findViewById(R.id.tvGuiaValor);
            circuito=(TextView)findViewById(R.id.tvCircuitoValor);
            horapartida=(TextView)findViewById(R.id.tvHoraPartidaValor);
            horallegada=(TextView)findViewById(R.id.tvHoraRegresoValor);
            fecha=(TextView)findViewById(R.id.tvFechaValor);
            guia.setText(horario.getGuia());
            circuito.setText(horario.getCircuito());
            agregar=(Button) findViewById(R.id.btnAgregarReserva);
            agregar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//para agregar una nueva cita se debe pasar como parametro la ubicacion a donde queremos agregar la cita,
                    Bundle datoenvia = new Bundle();
                    datoenvia.putSerializable("horario", horario);
                    datoenvia.putString("bandera","agregar");
                    Intent intent = new Intent(getApplicationContext(), crearReserv.class);
                    intent.putExtras(datoenvia);
                    startActivity(intent);
                }
            });
            horallegada.setText(horario.getHoraFin());
            horapartida.setText(horario.getHoraInicio());
            fecha.setText(horario.getFecha());
            reservalv=(ListView) findViewById(R.id.listViewres);
            adapter = new ReservasAdapter(this,Reservalist);
            reservalv.setAdapter(adapter);
            reservalv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView adapterView, View view, int i, long l) {
                    Bundle datoenvia = new Bundle();
                    objReserva res = Reservalist.get(i);
                    //horario.getReservalist().remove(res);
                    datoenvia.putString("horai",horario.getHoraInicio());
                    datoenvia.putString("horaf", horario.getHoraFin());
                    datoenvia.putString("fecha", horario.getFecha());
                    datoenvia.putString("guia", horario.getGuia());
                    datoenvia.putSerializable("horario", horario);
                    Intent intent = new Intent(getApplicationContext(), ConsultaReserva.class);
                    intent.putExtras(datoenvia);
                    intent.putExtra("index", i);
                    intent.putExtra("reserva", res);
                    startActivity(intent);
                }
            });
        cambiar();
        }
    }


public void cambiar(){
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("cita").child(horario.getID());
    ref.addValueEventListener(new ValueEventListener() {

        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            if (snapshot.exists()) {
                List<objReserva> rlist = new ArrayList<>();
                for (DataSnapshot dsii : snapshot.child("reservalist").getChildren()) {
                    String rID = dsii.getKey();
                    String correo = dsii.child("correo").getValue(String.class);
                    String usuario = dsii.child("usuario").getValue(String.class);
                    String hospedaje = dsii.child("hospedaje").getValue(String.class);
                    String telefono = dsii.child("telefono").getValue(String.class);
                    String deposito = dsii.child("deposito").getValue(String.class);
                    String usuariores = dsii.child("usuario").getValue(String.class);
                    String procedencia = dsii.child("procedencia").getValue(String.class);
                    String pendiente = dsii.child("pendiente").getValue(String.class);
                    String total = dsii.child("total").getValue(String.class);
                    List<objPersona> plist = new ArrayList<>();
                    for (DataSnapshot dsi : dsii.child("personalist").getChildren()) {
                        String nombre = dsi.child("nombre").getValue(String.class);
                        String dni = dsi.child("dni").getValue(String.class);
                        String fechaN = dsi.child("fechaN").getValue(String.class);
                        String tipo = dsi.child("tipo").getValue(String.class);
                        String caballo = dsi.child("caballo").getValue(String.class);
                        String obs = dsi.child("obs").getValue(String.class);
                        plist.add(new objPersona(nombre, dni, fechaN, tipo, caballo, obs));
                    }
                    objReserva Res = new objReserva(correo, telefono, hospedaje, usuariores, plist, pendiente, deposito, procedencia, total);
                    Res.setID(rID);
                    rlist.add(Res);
                }
                horario.setReservalist(rlist);
                adapter = new ReservasAdapter(getApplicationContext(),rlist);
                reservalv.setAdapter(adapter);
                Reservalist=rlist;
                adapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {
            // Manejar error
        }
    });
}
}
