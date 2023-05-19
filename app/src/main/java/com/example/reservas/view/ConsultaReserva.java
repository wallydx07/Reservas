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
    int index;
    String horaih,horafh,fechah,guiah;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_reserva);
        Bundle parametros = this.getIntent().getExtras();
        if(parametros !=null){
            index = getIntent().getIntExtra("index", 0);
            horaih = parametros.getString("horai");
            horafh= parametros.getString("horaf");
            fechah=parametros.getString("fecha");
            guiah=parametros.getString("guia");
            horario=(objHorario) getIntent().getSerializableExtra("horario");
            Reserva  = (objReserva) getIntent().getSerializableExtra("reserva");
            Personalist=Reserva.getPersonalist();
            adapter = new adapterclienteconsulta(this,Personalist);
            deposito=(TextView)findViewById(R.id.txtConsutlaReservaDeposito);
            pendiente=(TextView)findViewById(R.id.txtConsutlaReservaPendiente);
            clientes=(ListView)findViewById(R.id.lvConsutlaReservaclientes);
            usuario=(TextView)findViewById(R.id.textView9);
            correo=(TextView)findViewById(R.id.txtCrr);
            hospedaje=(TextView)findViewById(R.id.textView14h);
          //  descarga=(Button)findViewById(R.id.btDescargaConsultaReserva);
            telefono=(TextView)findViewById(R.id.txttl);
            procedencia=(TextView)findViewById(R.id.txtproc);
            guia=(TextView)findViewById(R.id.textView11);
//            descarga.setOnClickListener(this);
            editar=(Button)findViewById(R.id.btEditarConsultaReserva);
            editar.setOnClickListener(this);
            eliminar=(Button)findViewById(R.id.btEliminarConsultaReserva);
            eliminar.setOnClickListener(this);
            deposito.setText(Reserva.getDeposito());
            pendiente.setText(Reserva.getPendiente());
            usuario.setText(Reserva.getUsuario());
            guia.setText(guiah);
            correo.setText(Reserva.getCorreo());
            hospedaje.setText(Reserva.getHospedaje());
            telefono.setText(Reserva.getTelefono());
            procedencia.setText(Reserva.getProcedencia());
            clientes.setAdapter(adapter);
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
          //  case R.id.btDescargaConsultaReserva:

                //downloadFile(this.getApplicationContext(),Reserva.nombreTitular()+"-Salud",".pdf", Environment.DIRECTORY_DOWNLOADS,URLSalud);
               // downloadFile(this.getApplicationContext(),Reserva.nombreTitular()+"-DNI",".pdf", Environment.DIRECTORY_DOWNLOADS,URLdni);

               // break;
            case R.id.btEditarConsultaReserva:
                Intent intent = new Intent(this, crearReserv.class);
                intent.putExtra("reserva", Reserva);
                intent.putExtra("horario", horario);
                Bundle datoenvia = new Bundle();
                datoenvia.putString("bandera","editar");
                datoenvia.putString("horai",horaih);
                datoenvia.putString("horaf", horafh);
                datoenvia.putString("fecha", fechah);
                datoenvia.putString("guia", guiah);
                datoenvia.putInt("index",index);
                intent.putExtras(datoenvia);
                startActivity(intent);
                finish();
                break;
            case R.id.btEliminarConsultaReserva:
                if(horario.getReservalist().size()==1){
                    DatabaseReference citaRef = FirebaseDatabase.getInstance().getReference("cita").child(horario.getID());

                    citaRef.removeValue(new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(DatabaseError error, DatabaseReference ref) {
                            if (error == null) {
                                System.out.println("Nodo cita eliminado exitosamente.");
                            } else {
                                System.out.println("Error al eliminar el nodo cita: " + error.getMessage());
                            }
                        }
                    });




                }else {

                    DatabaseReference citaRef = FirebaseDatabase.getInstance().getReference("cita").child(horario.getID());
                    DatabaseReference reservalistRef = citaRef.child("reservalist").child(Reserva.getID());

                    reservalistRef.removeValue(new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(DatabaseError error, DatabaseReference ref) {
                            if (error == null) {
                                System.out.println("Nodo reservalist eliminado exitosamente.");
                            } else {
                                System.out.println("Error al eliminar el nodo reservalist: " + error.getMessage());
                            }
                        }
                    });



                }
                finish();


                break;



        }






    }
}