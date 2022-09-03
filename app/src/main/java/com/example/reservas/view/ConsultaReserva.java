package com.example.reservas.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.reservas.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;
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
            deposito.setText(Reserva.getDeposito());
            pendiente.setText(Reserva.getPendiente());
            ArrayAdapter<objPersona> adapter=new ArrayAdapter<objPersona>(this.getApplicationContext(),R.layout.listview_item,Personalist);
            clientes.setAdapter(adapter);






            System.out.println(Reserva);


            //Reserva=parametros.getString("datos");
            System.out.println(datos);
        }
    }





    public long downloadFile(Context context, String fileName, String fileExtension, String destinationDirectory, String url) {


        DownloadManager downloadmanager = (DownloadManager) context.
                getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);

        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalFilesDir(context, destinationDirectory, fileName + fileExtension);

        return downloadmanager.enqueue(request);
    }





}