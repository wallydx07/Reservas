package com.example.reservas.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.animation.ObjectAnimator;
import android.app.DatePickerDialog;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.reservas.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class crearReserv extends AppCompatActivity implements View.OnClickListener  {
    TabLayout tablayout;
    ViewPager viewpager;
    EditText tNombre, tNacimiento,tDNI, tcorreo, ttelefono, thospedaje,total, tanticipo, tpendiente, tFecha,ttotal, tprocedencia;
    String horaInicio,fecha,guia,bandera,correo,telefono,hospedaje, procedencia,User;;
    objReserva reserva;
    objPersona persona;
    ListViewAdapter adapter1;
    View v;
    Button agregar,siguiente,finalizar;
    List<objPersona> Personalist,personafinal;
    ListClientesAdpater adapter;
    ListView lista;
    List<objPersona> personalist;
    List<obProductos> cabalgatalist;
    List<obProductos> pcircuito;
    List<objReserva> reservalist;
    List<String> Circuito;
    objReserva miReserva;
    Spinner spinCircuito,spinCaballo, spinhoraFin,spinhoraInicio,spinGuia;
    ListView datoscaballos;
    adapterCaballo adaptercaballo;
    DatabaseReference mDatabase;
    ImageButton biagregar, subirsalud,subirDNI;
    FirebaseUser user;
    int cant;
    TextView sDNI,sSalud;
    Date date;
    Uri imageuri = null;
    Uri imageuri2 = null;
    public crearReserv() {




    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle parametros = this.getIntent().getExtras();
        setContentView(R.layout.activity_crear_reserv);
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
                reserva=new objReserva();
            }
            if(bandera.equals("editar")){
                reserva  = (objReserva) getIntent().getSerializableExtra("reserva");
                String horaih = parametros.getString("horai");
                String horafh= parametros.getString("horaf");
                String fechah=parametros.getString("fecha");
                String guiah=parametros.getString("guia");
                horaInicio =horaih;
                fecha = fechah;
                guia = guiah;
                Personalist=reserva.getPersonalist();
            }

        }


        tcorreo=(EditText) findViewById(R.id.txtCorreoDatosClientes);
        tprocedencia=(EditText) findViewById(R.id.txtProcedenciaDatosCliente);
        thospedaje=(EditText) findViewById(R.id.txtHospedajeDatosCliente);
        ttelefono=(EditText) findViewById(R.id.txtTelefonoDatosClientes);
        tNombre=(EditText) findViewById(R.id.txtDatosApellidoyNombre);
        tNacimiento=(EditText) findViewById(R.id.txtDAtosFechaNacimiento);
        tNacimiento.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // No es necesario hacer nada antes de cambiar el texto
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Formatear el texto según tus necesidades
                if (s.length() == 2 || s.length() == 5) {
                    tNacimiento.setText(s + "/");
                    tNacimiento.setSelection(s.length() + 1);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                // No es necesario hacer nada después de cambiar el texto
            }
        });
        tDNI=(EditText) findViewById(R.id.txtDatosDNI);
        agregar=(Button) findViewById(R.id.bottomDatosnAgregar);
        agregar.setOnClickListener(this);
        lista =(ListView) findViewById(R.id.listViewDatosClientes);
        if(bandera.equals("editar")){
            Personalist=reserva.getPersonalist();
           // correo.setText(reserva.getCorreo());
            //hospedaje.setText(reserva.getHospedaje());
           // telefono.setText(((nuevaReserva)getActivity()).reserva.getTelefono());
           // procedencia.setText(((nuevaReserva)getActivity()).reserva.getProcedencia());
        }
        if(!Personalist.isEmpty()){
            adapter = new ListClientesAdpater(this,Personalist);
        }
        if (adapter!=null){
            lista.setAdapter(adapter);
        }

//============================================
        System.out.println("______________________________"+horaInicio+"______________________________");
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            date = dateFormat.parse(fecha);
            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user = FirebaseAuth.getInstance().getCurrentUser();
        mDatabase= FirebaseDatabase.getInstance().getReference();
        cabalgatalist=new ArrayList<>();

        //========================================================================
        tFecha =(EditText) findViewById(R.id.etFechaReservaProducto);
        tFecha.setText(fecha);
        tFecha.setOnClickListener(this);
        spinCircuito=(Spinner) findViewById(R.id.spinnerReservaProductoCircuito);
        spinCaballo=(Spinner) findViewById(R.id.spinnerReservaProductoCaballo);
        spinhoraFin =(Spinner) findViewById(R.id.spinnerhorafinReservaProducto);
        try {
            spinhoraFin.setSelection(getpos(horaInicio)+1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        spinhoraInicio=(Spinner) findViewById(R.id.spinnerhorainicioReservaProducto);
        try {
            spinhoraInicio.setSelection(getpos(horaInicio));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        spinGuia =(Spinner) findViewById(R.id.spinnerGuiaReservaProducto);
        spinGuia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                loadcircuito();
                loadcaballo();
                System.out.println("sew ha llamado a loadcircuito");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinhoraInicio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                loadcircuito();
                loadcaballo();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinhoraFin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                loadcircuito();
                loadcaballo();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        ttotal=(EditText) findViewById(R.id.txtReservaProductoTotal);
        tanticipo=(EditText)findViewById(R.id.txtReservaProductoAnticipo);
        tpendiente=(EditText)findViewById(R.id.txtReservaProductoPendiente);
        finalizar=(Button) findViewById(R.id.buttonReservaProductoFinalizar);
        finalizar.setOnClickListener(this);
        spinCaballo.setEnabled(true);

        if(bandera.equals("editar")){

            cabalgatalist=(reserva.getCaballolist());
            tanticipo.setText(reserva.getDeposito());
            tpendiente.setText(reserva.getPendiente());
            ttotal.setText(reserva.getTotal());

            if(cabalgatalist!=null){
                adaptercaballo = new adapterCaballo(this, cabalgatalist);
                datoscaballos.setAdapter(adaptercaballo);

            }
        }















        loadproducto();
        loadGuias();
        loadcaballo();


    }







    //=====================================================================================

    private void writeNewReserva(objHorario cita) {

        mDatabase.child("cita").push().setValue(cita);
        finish();
    }


    private void updateReserva(String reservaKey, objReserva updatedReserva) {
        mDatabase.child("reserva").child(reservaKey).setValue(updatedReserva)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Reserva actualizada exitosamente
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Error al actualizar la reserva
                        Toast.makeText(getApplicationContext(), "Error al actualizar la reserva", Toast.LENGTH_SHORT).show();
                    }
                });
    }




    public int Transforma(String Hora){
        int fin=0;
        try {
            DateFormat inFormat = new SimpleDateFormat("HH:mm");
            Date horainicio = inFormat.parse(Hora);
            long hora1 = horainicio.getTime();
            fin= (int) ((hora1/3600000)-3);
        } catch (ParseException e ) {
            e.printStackTrace();
        }
        return fin;
    }
    public void loadproducto() {
        System.out.println(("________________________________loadPorducto"));
        pcircuito = new ArrayList<>();
        cabalgatalist = new ArrayList<>();
        mDatabase.child("producto").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        String nombre = ds.child("nombre").getValue().toString();
                        String tipo = ds.child("tipo").getValue().toString();
                        String precio= ds.child("precio").getValue().toString();

                        if(tipo.equals("Circuito")){
                            pcircuito.add(new obProductos(nombre, precio,tipo));
                        }
                        if(tipo.equals("Caballo")){
                            cabalgatalist.add(new obProductos(nombre, precio,tipo));
                        }
                    }
                    ArrayAdapter<obProductos> adaptercircuito = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, pcircuito);
                    spinCircuito.setAdapter(adaptercircuito);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


    public void loadcaballo() {
        System.out.println("se van a ller los caballos disp_______________________");
        String h1=spinhoraInicio.getSelectedItem().toString();
        String h2=spinhoraFin.getSelectedItem().toString();
        String f=tFecha.getText().toString();
        final List<obProductos> pcabalgata = new ArrayList<>();
        System.out.println("antes de mdatabse_______________________");
        mDatabase.child("reserva").orderByChild("fecha").equalTo(f).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                System.out.println("sepaso el override_______________________");
                System.out.println("Número de datos encontrados en la búsqueda: " + snapshot.getChildrenCount());
                if (snapshot.exists()) {//ver
                    System.out.println("el porducto existe_______________________");
                    String precio = "0";
                    String tipo = "";
                    String caballonombre = "";
                    long hora1 = 0;
                    long hora2 = 0;
                    try {
                        DateFormat inFormat = new SimpleDateFormat("HH:mm");
                        // Date horainicio = inFormat.parse(horaInicio);
                        //Date horafin = inFormat.parse(spinhoraFin.getSelectedItem().toString());
                        Date horainicio = inFormat.parse(h1);
                        Date horafin = inFormat.parse(h2);
                        hora1 = (horainicio.getTime() / 3600000) - 3;//hora d einicio desde el listviewanterior
                        hora2 = (horafin.getTime() / 3600000) - 3; //+ hora1;//+hora1;//hora inicio desde el spiner actual
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    for (DataSnapshot ds : snapshot.getChildren()) {

                        System.out.println(ds.child("circuito").getValue().toString());
                        long H1 = 0;
                        long H2 = 0;
                        String horaI = ds.child("horaInicio").getValue().toString();
                        String horaII= ds.child("horaFin").getValue().toString();

                        try {
                            DateFormat inFormat = new SimpleDateFormat("HH:mm");
                            Date horainicio = inFormat.parse(horaI);
                            Date horafin=inFormat.parse(horaII);
                            H1 = (horainicio.getTime() / 3600000) - 3;
                            H2 = (horafin.getTime() / 3600000) - 3;

                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        if(hora1<hora2) {
                            if ((H1< hora1 && H2 > hora1) || (H1 > hora1 && H1 < hora2) || (H2 > hora1 && H2 < hora2)) {//hay citas dentro de ese rango
                                for (DataSnapshot dsi : ds.child("caballolist").getChildren()) {
                                    try {
                                        caballonombre = dsi.child("nombre").getValue().toString();
                                        precio = dsi.child("precio").getValue().toString();
                                        tipo = dsi.child("tipo").getValue().toString();
                                        System.out.println("____________________caballo ---"+caballonombre+"NO disponbler");
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    System.out.println(caballonombre);
                                    obProductos caballo = new obProductos(caballonombre, precio, tipo);
                                    pcabalgata.add(caballo);
                                }

                            }
                        }
                    }


                    boolean disponible = true;
                    List<obProductos> auxadapter = new ArrayList<>(cabalgatalist);
                    auxadapter.removeAll(pcabalgata);
                    ArrayAdapter<obProductos> adaptercaballo = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, auxadapter);
                    spinCaballo.setAdapter(adaptercaballo);




                }else{
                    System.out.println("no existe_______________________");
                    ArrayAdapter<obProductos> adaptercaballo = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, cabalgatalist);
                    spinCaballo.setAdapter(adaptercaballo);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }



    public void loadcircuito() {
        String h11=spinhoraInicio.getSelectedItem().toString();
        String h22=spinhoraFin.getSelectedItem().toString();
        String f=tFecha.getText().toString();
        System.out.println("se ha inicado load Circuito con fecha: "+f);
        long h1w1=0;
        long h1w2=0 ;
        try {
            DateFormat inFormat = new SimpleDateFormat("HH:mm");
            Date horainicio = inFormat.parse(h11);
            Date horafin = inFormat.parse(h22);
            h1w1 = (horainicio.getTime() / 3600000) - 3;//hora d einicio desde el listviewanterior
            h1w2 = (horafin.getTime() / 3600000) - 3;//+hora1;//hora inicio desde el spiner actual
        } catch (ParseException e) {
            System.out.println("error"+e);
            e.printStackTrace();
        }
        if(h1w1<h1w2){
            mDatabase.child("cita").orderByChild("fecha").equalTo(f).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    long h1=0;
                    long h2=0 ;


                    if (snapshot.exists()) {//ver
                        try {
                            DateFormat inFormat = new SimpleDateFormat("HH:mm");
                            Date horainicio = inFormat.parse(h11);
                            Date horafin = inFormat.parse(h22);
                            h1 = (horainicio.getTime() / 3600000) - 3;//hora d einicio desde el listviewanterior
                            h2 = (horafin.getTime() / 3600000) - 3;//+hora1;//hora inicio desde el spiner actual
                        } catch (ParseException e) {
                            System.out.println("error"+e);
                            e.printStackTrace();
                        }
                        String resul="";

                        boolean haycitas=false;
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            String guiaaxu=ds.child("guia").getValue().toString();
                            if(guiaaxu.equals(spinGuia.getSelectedItem().toString())) {
                                //System.out.println(h1+"-"+h2);




                                long H1= 0;
                                long H2=0;
                                String horaI = ds.child("horaInicio").getValue().toString();
                                String horaII= ds.child("horaFin").getValue().toString();
                                try {
                                    DateFormat inFormat = new SimpleDateFormat("HH:mm");
                                    Date horainicio = inFormat.parse(horaI);
                                    Date horafin= inFormat.parse(horaII);

                                    H1 = (horainicio.getTime() / 3600000) - 3;
                                    H2=(horafin.getTime() / 3600000) - 3;
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                                if ((H1< h1 && H2 > h1) || (H1 > h1 && H1 < h2) || (H2 > h1 && H2 < h2)) {
                                    haycitas=true;
                                    resul="Guia no disponible";
                                    System.out.println("Hoy citas entre las"+h1+"-"+h2+"---- "+H1+"-"+H2);
                                } else {
                                    resul="Disponible";
                                    System.out.println("no hay citas entre las"+h1+"-"+h2+"---- "+H1+"-"+H2);
                                    haycitas=false;
                                }




                            }
                        }
                        if(haycitas) {
                            ArrayList<String> Nodisp = new ArrayList<>();
                            Nodisp.add(resul);
                            ArrayAdapter<String> adapterNo = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, Nodisp);
                            spinCircuito.setAdapter(adapterNo);
                        }else{
                            ArrayAdapter<obProductos> adaptercircuito2 = new ArrayAdapter<obProductos>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, pcircuito);
                            spinCircuito.setAdapter(adaptercircuito2);


                        }


                    }else{
                        System.out.println("no existen datos en la fecha"+f);
                        ArrayAdapter<obProductos> adaptercircuito2 = new ArrayAdapter<obProductos>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, pcircuito);
                        spinCircuito.setAdapter(adaptercircuito2);

                    }




                }


                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }

            });
        }else{
            ArrayList<String> Nodisp = new ArrayList<>();
            Nodisp.add("Horario no permitido");
            ArrayAdapter<String> adapterNo = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, Nodisp);
            spinCircuito.setAdapter(adapterNo);
        }


    }
    public void loadGuias(){
        String ID="";
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String usuariuo=user.getDisplayName();
        if (user != null) {
            ID = user.getUid();
        }
        final List<String> pguia = new ArrayList<>();
        pguia.add(usuariuo);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        String finalID = ID;
        mDatabase.child("guia").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        String nombre = ds.child("nombre").getValue().toString();
                        if(!(nombre.equals(usuariuo))) {

                            pguia.add(nombre);
                            System.out.println("pguia es: " + nombre);
                        }
                    }
                }
                try {
                    ArrayAdapter<String> adapterGuia = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, pguia);
                    spinGuia.setAdapter(adapterGuia);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        loadcaballo();
    }







    private String twoDigits(int n) {
        return (n<=9) ? ("0"+n) : String.valueOf(n);
    }
    public void loadCalendario() {
        int dia, mes, año;
        final Calendar c= Calendar.getInstance();
        final Calendar newCalendar = Calendar.getInstance();
        dia=c.get(Calendar.DAY_OF_MONTH);
        mes=c.get(Calendar.MONTH);
        año=c.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int dia, int mes, int año) {
                EditText etPlannedDate = tFecha;
                etPlannedDate.setText("");
                final String selectedDate = twoDigits(año) + "-" + twoDigits(mes+1) + "-" + dia;
                etPlannedDate.setText(selectedDate);
                loadcircuito();

            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        //,dia,mes,año);
        datePickerDialog.show();
        loadcircuito();
        loadcaballo();


    }



    //==================================================================================










    public int getpos(String horaf) throws ParseException {
        int n=0;
        String hora0="0:00";
        DateFormat inFormat = new SimpleDateFormat("HH:mm");
        // Date horainicio = inFormat.parse(horaInicio);
        //Date horafin = inFormat.parse(spinhoraFin.getSelectedItem().toString());
        Date horainicio = inFormat.parse(hora0);
        Date horafin = inFormat.parse(horaf);
        long hora1 = (horainicio.getTime() / 3600000) - 3;//hora d einicio desde el listviewanterior
        long hora2 = (horafin.getTime() / 3600000) - 3; //+ hora1;//+hora1;//hora inicio desde el spiner actual
        n= (int) (hora2-hora1);
        return n;
    }








    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.etFechaReservaProducto:
                loadCalendario();
                break;

          /*  case R.id.btSiguienteReservaDatosCliente:
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

                break;*/
            case R.id.bottomDatosnAgregar:
                String nombre=tNombre.getText().toString();
                String DNI=tDNI.getText().toString();
                String fNacimiento=tNacimiento.getText().toString();

                if (nombre.isEmpty() || DNI.isEmpty() ||fNacimiento.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Por favor complete todos los campos", Toast.LENGTH_SHORT).show();

                }else{
                    persona=new objPersona(nombre,DNI,fNacimiento,"cliente");
                    //    cliente.bund(nombre,DNI,fNacimiento);
                    Personalist.add(persona);
                    adapter = new ListClientesAdpater(getApplicationContext(), Personalist);
                    lista.setAdapter(adapter);
                    tNombre.setText("");
                     tDNI.setText("");
                    tNacimiento.setText("");

                }
                break;
            case R.id.buttonReservaProductoFinalizar:
                System.out.println("GUIA_______"+(spinGuia.getSelectedItem().toString()));
                if ((spinCircuito.getSelectedItem().toString()).equals("Guia no disponible")) {
                    Toast.makeText(getApplicationContext(), "Guia no dispònible!", Toast.LENGTH_SHORT).show();


                } else {

                    personalist = personafinal;
                    reservalist = new ArrayList<>();
                    System.out.println("finalreser");
                    System.out.println("=============" + personalist.size() + "==========");

                    String pend = tpendiente.getText().toString();
                    String tot = ttotal.getText().toString();
                    String ant = tanticipo.getText().toString();
                    String hora0 = spinhoraInicio.getSelectedItem().toString();
                    String hora1 = spinhoraFin.getSelectedItem().toString();
                    String circ = spinCircuito.getSelectedItem().toString();
                    if (pend.isEmpty() || tot.isEmpty() || ant.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Por favor complete todos los campos", Toast.LENGTH_SHORT).show();

                    } else {

                        objReserva reserva = new objReserva(correo, telefono, hospedaje, user.getDisplayName(), personalist, cabalgatalist, pend, ant, procedencia);

                        reserva.setTotal(tot);
                        reservalist.add(reserva);
                        objHorario cita = new objHorario(fecha, hora0, hora1, guia, circ, reservalist);
                        if (bandera.equals("editar")) {
                            updateReserva(reserva.getID(), reserva);
                        } else {
                            writeNewReserva(cita);
                        }
                    }
                }
                break;

        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}








