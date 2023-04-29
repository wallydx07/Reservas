package com.example.reservas.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.reservas.R;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class ConsultaReserva extends AppCompatActivity implements View.OnClickListener  {
    objReserva Reserva;
    objHorario horario;
    TextView circuito,fecha,horapartida,horallegada,deposito,pendiente,guia,procedencia,hospedaje,telefono,usuario,correo;
    ListView clientes, caballos;
    Button descarga,eliminar,editar;
    List<objPersona> Personalist;
    List<obProductos> Caballolist;
    objPersona persona;
    //ListClientesAdpater adapter;
    adapterclienteconsulta adapter;
    String URLdni, URLSalud;
    String horaih,horafh,fechah,guiah;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_reserva);
        Bundle parametros = this.getIntent().getExtras();
        if(parametros !=null){
            horaih = parametros.getString("horai");
            horafh= parametros.getString("horaf");
            fechah=parametros.getString("fecha");
            guiah=parametros.getString("guia");
            Reserva  = (objReserva) getIntent().getSerializableExtra("reserva");
            Personalist=Reserva.getPersonalist();


            caballos=(ListView)findViewById(R.id.lvConsutlaReservaCaballos);
            //adapter = new ListClientesAdpater(this,Personalist);
            adapter = new adapterclienteconsulta(this,Personalist);
           // URLdni=Reserva.getUrlDNI();
          //  URLSalud=Reserva.getUrlBuenaSalud();
           // System.out.println(URLSalud+"qwertyuiopñlkjhgfdsazxcvbnm");
            fecha=(TextView)findViewById(R.id.txtConsultaReservaCircuito);
            horapartida=(TextView)findViewById(R.id.txtConsutlaReservaHoraPartida);
            horallegada=(TextView)findViewById(R.id.txtConsutlaReservaHoraRegreso);
            deposito=(TextView)findViewById(R.id.txtConsutlaReservaDeposito);
            pendiente=(TextView)findViewById(R.id.txtConsutlaReservaPendiente);
            clientes=(ListView)findViewById(R.id.lvConsutlaReservaclientes);
            usuario=(TextView)findViewById(R.id.textView9);
            correo=(TextView)findViewById(R.id.txtCrr);
            hospedaje=(TextView)findViewById(R.id.textView14h);
            descarga=(Button)findViewById(R.id.btDescargaConsultaReserva);
            telefono=(TextView)findViewById(R.id.txttl);
            procedencia=(TextView)findViewById(R.id.txtproc);
            guia=(TextView)findViewById(R.id.textView11);
            circuito=(TextView)findViewById(R.id.txtcirc);
            descarga.setOnClickListener(this);
            editar=(Button)findViewById(R.id.btEditarConsultaReserva);
            editar.setOnClickListener(this);
            eliminar=(Button)findViewById(R.id.btEliminarConsultaReserva);
            eliminar.setOnClickListener(this);
           fecha.setText(fechah);
           horapartida.setText(horaih);
           horallegada.setText(horafh);
            deposito.setText(Reserva.getDeposito());
            pendiente.setText(Reserva.getPendiente());
            usuario.setText(Reserva.getUsuario());
            guia.setText(guiah);
            correo.setText(Reserva.getCorreo());
            hospedaje.setText(Reserva.getHospedaje());
            telefono.setText(Reserva.getTelefono());
            procedencia.setText(Reserva.getProcedencia());
           // ArrayAdapter<objPersona> adapter=new ArrayAdapter<objPersona>(this.getApplicationContext(),R.layout.clientesadapter,Personalist);

            clientes.setAdapter(adapter);
if(Caballolist==null){

}else {
    ArrayAdapter<obProductos> adapter1=new ArrayAdapter<obProductos>(getApplicationContext(),R.layout.listview_item,Caballolist);
    caballos.setAdapter(adapter1);
    System.out.println(Reserva);
}



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

                //downloadFile(this.getApplicationContext(),Reserva.nombreTitular()+"-Salud",".pdf", Environment.DIRECTORY_DOWNLOADS,URLSalud);
               // downloadFile(this.getApplicationContext(),Reserva.nombreTitular()+"-DNI",".pdf", Environment.DIRECTORY_DOWNLOADS,URLdni);

                break;
            case R.id.btEditarConsultaReserva:
                Intent intent = new Intent(this, crearReserv.class);
                intent.putExtra("reserva", Reserva);
                Bundle datoenvia = new Bundle();
                datoenvia.putString("bandera","editar");
                datoenvia.putString("horai",horaih);
                datoenvia.putString("horaf", horafh);
                datoenvia.putString("fecha", fechah);
                datoenvia.putString("guia", guiah);
                intent.putExtras(datoenvia);
                startActivity(intent);
                finish();
                break;
            case R.id.btEliminarConsultaReserva:
                DatabaseReference registroRef = FirebaseDatabase.getInstance().getReference("reserva/"+Reserva.getID());

// Eliminar el registro
                registroRef.removeValue(new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError error, DatabaseReference ref) {
                        if (error == null) {
                            System.out.println("Registro eliminado exitosamente.");
                        } else {
                            System.out.println("Error al eliminar el registro: " + error.getMessage());
                        }
                    }
                });

                finish();


                break;



        }






    }
}