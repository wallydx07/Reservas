package com.example.reservas.view;

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

import java.util.List;

public class consultaHorario extends AppCompatActivity {
    ReservasAdapter adapter;
    objReserva Reserva;
    objHorario horario;
    TextView circuito,fecha,horapartida,horallegada,deposito,pendiente,guia,procedencia,hospedaje,telefono,usuario,correo;
    ListView reservalv, caballos;
    Button descarga,eliminar,editar;
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
                    datoenvia.putString("horai",horario.getHoraInicio());
                    datoenvia.putString("horaf", horario.getHoraFin());
                    datoenvia.putString("fecha", horario.getFecha());
                    datoenvia.putString("guia", horario.getGuia());


                    Intent intent = new Intent(getApplicationContext(), ConsultaReserva.class);
                    intent.putExtras(datoenvia);
                    intent.putExtra("reserva", res);
                    startActivity(intent);
                }
            });
        }
    }
    }
