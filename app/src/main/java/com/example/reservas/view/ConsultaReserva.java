package com.example.reservas.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.reservas.R;

import java.util.List;

public class ConsultaReserva extends AppCompatActivity implements View.OnClickListener  {
    objReserva Reserva;
    TextView circuito,fecha,horapartida,horallegada,deposito,pendiente;
    ListView clientes, caballos;
    Button descarga,eliminar,editar;
    List<objPersona> Personalist;
    List<objCaballo> Caballolist;
    objPersona persona;
    ListClientesAdpater adapter;
    String URLdni, URLSalud;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_reserva);
        Bundle parametros = this.getIntent().getExtras();
        if(parametros !=null){

            String datos = parametros.getString("datos");
            Reserva  = (objReserva) getIntent().getSerializableExtra("reserva");
            Personalist=Reserva.getPersonalist();
            URLdni=Reserva.getUrlDNI();
            URLSalud=Reserva.getUrlBuenaSalud();
            System.out.println(URLSalud+"qwertyuiopñlkjhgfdsazxcvbnm");
            fecha=(TextView)findViewById(R.id.txtConsultaReservaCircuito);
            horapartida=(TextView)findViewById(R.id.txtConsutlaReservaHoraPartida);
            horallegada=(TextView)findViewById(R.id.txtConsutlaReservaHoraRegreso);
            deposito=(TextView)findViewById(R.id.txtConsutlaReservaDeposito);
            pendiente=(TextView)findViewById(R.id.txtConsutlaReservaPendiente);
            clientes=(ListView)findViewById(R.id.lvConsutlaReservaclientes);
            caballos=(ListView)findViewById(R.id.lvConsutlaReservaCaballos);
            descarga=(Button)findViewById(R.id.btDescargaConsultaReserva);
            descarga.setOnClickListener(this);

            editar=(Button)findViewById(R.id.btEditarConsultaReserva);
            editar.setOnClickListener(this);

            eliminar=(Button)findViewById(R.id.btEliminarConsultaReserva);
            eliminar.setOnClickListener(this);




            fecha.setText(Reserva.getFecha());
            horapartida.setText(Reserva.getHoraInicio());
            horallegada.setText(Reserva.getHoraFin());
            deposito.setText(Reserva.getDeposito());
            pendiente.setText(Reserva.getPendiente());
           // ArrayAdapter<objPersona> adapter=new ArrayAdapter<objPersona>(this.getApplicationContext(),R.layout.clientesadapter,Personalist);
            adapter = new ListClientesAdpater(this,Personalist);
            clientes.setAdapter(adapter);
            System.out.println(Reserva);


            //Reserva=parametros.getString("datos");
            System.out.println(datos);
        }
    }





    public long downloadFile(Context context, String fileName, String fileExtension, String destinationDirectory, String url) {
        DownloadManager downloadmanager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalFilesDir(context, destinationDirectory, fileName + fileExtension);
        return downloadmanager.enqueue(request);
    }


    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            /*case R.id. :
                Intent intent=new Intent(this, reservaDatosClientesFragment.class);
                startActivity(intent);

                break;*/
            case R.id.btDescargaConsultaReserva:

                downloadFile(this.getApplicationContext(),Reserva.nombreTitular()+"-Salud",".pdf", Environment.DIRECTORY_DOWNLOADS,URLSalud);
                downloadFile(this.getApplicationContext(),Reserva.nombreTitular()+"-DNI",".pdf", Environment.DIRECTORY_DOWNLOADS,URLdni);

                break;
            case R.id.btEditarConsultaReserva:

                Intent intent = new Intent(this, nuevaReserva.class);
                intent.putExtra("reserva", Reserva);
                Bundle datoenvia = new Bundle();
                datoenvia.putString("bandera","editar");
                intent.putExtras(datoenvia);
                startActivity(intent);
                finish();

                break;
            case R.id.btEliminarConsultaReserva:

                break;



        }






    }
}