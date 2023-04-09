package com.example.reservas.view;

import static android.app.Activity.RESULT_OK;

import static java.lang.Thread.sleep;

import android.animation.ObjectAnimator;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.reservas.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link reservaDProductoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class reservaDProductoFragment extends Fragment implements View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    View v;
    List<objPersona> personalist;
    List<obProductos> cabalgatalist;
    List<obProductos> pcircuito;
    List<String> Circuito;
    objReserva miReserva;
    objPersona persona;
    Spinner spinCircuito,spinCaballo, spinhoraFin,spinhoraInicio,spinGuia;
    EditText nombre,dni,fechanNacimiento, total, anticipo, pendiente, etFecha;
    String correo,telefono,hospedaje, procedencia;
    ListView datoscaballos;
    DatabaseReference mDatabase;
    private ProgressBar miprogress;
    private ObjectAnimator anim;
    Button finalizar;
    ImageButton agregar, subirsalud,subirDNI;
    FirebaseUser user;
    String horaInicio,fecha,guia,User;
    int cant;
    TextView sDNI,sSalud;
    Date date;
    Uri imageuri = null;
    Uri imageuri2 = null;
    ProgressDialog dialog;
    public reservaDProductoFragment() {
        // Required empty publifffc constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment reservaDProductoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static reservaDProductoFragment newInstance(String param1, String param2) {
        reservaDProductoFragment fragment = new reservaDProductoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        horaInicio =((nuevaReserva)this.getActivity()).horaInicio;
        System.out.println("______________________________"+horaInicio+"______________________________");
        fecha=((nuevaReserva)this.getActivity()).fecha;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
           date = dateFormat.parse(fecha);
           System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        guia=((nuevaReserva)this.getActivity()).guia;
        user = FirebaseAuth.getInstance().getCurrentUser();
        super.onCreate(savedInstanceState);
        personalist = new ArrayList<>();
        mDatabase= FirebaseDatabase.getInstance().getReference();
        cabalgatalist=new ArrayList<>();
        getParentFragmentManager().setFragmentResultListener("key",this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String nombreO=result.getString("nombre");
                String dni=result.getString("dni");
                String nacimineto=result.getString("nacimiento");
                persona=new objPersona(nombreO,dni,nacimineto,"Cliente");
                personalist.add(persona);
                System.out.println("se agrego nueov");
                }
        });
        getParentFragmentManager().setFragmentResultListener("K",this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                correo=result.getString("correo");
                telefono=result.getString("telefono");
                procedencia=result.getString("procedencia");
                hospedaje=result.getString("hospedaje");

            }
        });
      }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        System.out.println("Se crea la view______________________________________________________");
        // Inflate the layout for this fragment
        //
        v=inflater.inflate(R.layout.fragment_reserva_d_producto, container, false);
        etFecha =v.findViewById(R.id.etFechaReservaProducto);
        etFecha.setText(fecha);
        etFecha.setOnClickListener(this);
        sDNI=v.findViewById(R.id.txtDNIReservaDeProducto);
        sSalud=v.findViewById(R.id.txtBuenaSaludReservaDeProducto);
        miprogress =v.findViewById(R.id.circularProgress);
        anim = ObjectAnimator.ofInt(miprogress, "progress", 0, 100);
        spinCircuito=v.findViewById(R.id.spinnerReservaProductoCircuito);
        spinCaballo=v.findViewById(R.id.spinnerReservaProductoCaballo);
        spinhoraFin =v.findViewById(R.id.spinnerhorafinReservaProducto);
        try {
            spinhoraFin.setSelection(getpos(horaInicio)+1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        spinhoraInicio=v.findViewById(R.id.spinnerhorainicioReservaProducto);
        try {
            spinhoraInicio.setSelection(getpos(horaInicio));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        spinGuia =v.findViewById(R.id.spinnerGuiaReservaProducto);
        spinGuia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                loadcircuito();
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
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinhoraFin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                loadcircuito();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        total=v.findViewById(R.id.txtReservaProductoTotal);
        anticipo=v.findViewById(R.id.txtReservaProductoAnticipo);
        pendiente=v.findViewById(R.id.txtReservaProductoPendiente);
        agregar=v.findViewById(R.id.buttonReservaProductoagregar);
        agregar.setOnClickListener(this);
        subirsalud=v.findViewById(R.id.btBuenaSaludReservaDeProducto);
        subirsalud.setOnClickListener(this);
        subirDNI=v.findViewById(R.id.btDNIReservaDeProducto);
        subirDNI.setOnClickListener(this);
        datoscaballos=v.findViewById(R.id.listViewReservaProducto);
     //   cabalgata=v.findViewById(R.id.switchCaballo);
        finalizar=v.findViewById(R.id.buttonReservaProductoFinalizar);
        finalizar.setOnClickListener(this);
    //    cabalgata=v.findViewById(R.id.switchCaballo);
        agregar.setEnabled(true);
        spinCaballo.setEnabled(true);
    /*    cabalgata.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    agregar.setEnabled(isChecked);
                    spinCaballo.setEnabled(isChecked);
                    if(isChecked){
                        loadcaballo();
                    }
            }
        });
*/

        loadproducto();
        loadGuias();
        loadcaballo();
        return v;
    }
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
            case R.id.buttonReservaProductoagregar:
                List<obProductos> cabalgata=new ArrayList<>();
                String nombre=spinCaballo.getSelectedItem().toString();
                for (int i = 0; i< cabalgatalist.size(); i++) {
                    obProductos productosss= cabalgatalist.get(i);
                    if(productosss.getNombre().equals(nombre)){
                        cabalgata.add(productosss);
                    }
                }
                ArrayAdapter<obProductos> adapter=new ArrayAdapter<obProductos>(getActivity().getApplicationContext(),R.layout.listview_item,cabalgata);
                datoscaballos.setAdapter(adapter);
                break;
            case R.id.btBuenaSaludReservaDeProducto:
                System.out.println("accionaste el boton salud");
                Intent galleryIntent = new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                // We will be redirected to choose pdf
                galleryIntent.setType("application/pdf");
                startActivityForResult(galleryIntent, 1);
                break;
            case R.id.buttonReservaProductoFinalizar:

               String pend=pendiente.getText().toString();
                String tot=total.getText().toString();
                String ant=anticipo.getText().toString();
                String hora0=spinhoraInicio.getSelectedItem().toString();
                String hora1=spinhoraFin.getSelectedItem().toString();


                if (pend.isEmpty() || tot.isEmpty() ||ant.isEmpty()) {
                    Toast.makeText(getContext(), "Por favor complete todos los campos", Toast.LENGTH_SHORT).show();



                }else {
                   // int Fin=Transforma(spinhoraFin.getSelectedItem().toString());
                 //   Fin=Transforma(horaInicio)+Fin;
                    objReserva reserva=new objReserva(fecha, hora0, hora1,  correo,  telefono,  hospedaje,   user.getDisplayName(),guia, spinCircuito.getSelectedItem().toString(), personalist,cabalgatalist,pend,ant,procedencia);
                    reserva.setTotal(tot);
                    //  Subir("Salud", imageuri, reserva.nombreTitular(), reserva);
                    reserva.setUrlBuenaSalud("---");
                    reserva.setUrlDNI("---");
                    writeNewReserva(reserva);
                }





               break;
            case R.id.btDNIReservaDeProducto:
                System.out.println("accionaste el boton dni");
                Intent galleryIntent1 = new Intent();
                galleryIntent1.setAction(Intent.ACTION_GET_CONTENT);
                // We will be redirected to choose pdf
                galleryIntent1.setType("application/pdf");
                startActivityForResult(galleryIntent1, 2);
                break;
        }
    }
    private void mostrarProgress(){
        miprogress.setVisibility(View.VISIBLE);
        anim.setDuration(15000);
        anim.setInterpolator(new DecelerateInterpolator());
       // anim.start();
    }
    private void detenerProgress(){
        miprogress.setVisibility(View.GONE);
        anim.cancel();
    }
    private void writeNewReserva(objReserva reserva) {

        mDatabase.child("reserva").push().setValue(reserva);
        requireActivity().finish();
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
       // final List<obProductos> pcabalgata = new ArrayList<>();
        //final List<obProductos> pcircuito = new ArrayList<>();
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
                   // ArrayAdapter<obProductos> adaptercaballo = new ArrayAdapter<>(getActivity().getApplication(), android.R.layout.simple_dropdown_item_1line, reservaDProductoFragment.this.cabalgatalist);
                    ArrayAdapter<obProductos> adaptercircuito = new ArrayAdapter<>(getActivity().getApplication(), android.R.layout.simple_dropdown_item_1line, pcircuito);
                 //   spinCaballo.setAdapter(adaptercaballo);
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
       // spinCaballo.setAdapter(null);
        String h1=spinhoraInicio.getSelectedItem().toString();
        String h2=spinhoraFin.getSelectedItem().toString();
        String f=etFecha.toString();
        final List<obProductos> pcabalgata = new ArrayList<>();
      //  pcircuito = new ArrayList<>();
        mDatabase.child("reserva").orderByChild("fecha").equalTo(f).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {//ver

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

                            if ((H1< hora1 && H2 > hora1) || (H1 > hora1 && H1 < hora2) || (H2 > hora1 && H2 < hora2)) {
                                for (DataSnapshot dsi : ds.child("caballolist").getChildren()) {
                                    try {
                                        caballonombre = dsi.child("nombre").getValue().toString();
                                        precio = dsi.child("precio").getValue().toString();
                                        tipo = dsi.child("tipo").getValue().toString();
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
                    List<obProductos> auxadapter = new ArrayList<>();
                    for (int i = 0; i < cabalgatalist.size(); i++) {
                        obProductos auxpro = cabalgatalist.get(i);
                        for (int j = 0; j < pcabalgata.size(); j++) {
                            obProductos auxpro1 = pcabalgata.get(j);
                            if ((auxpro.getNombre()).equals(auxpro1.getNombre())) {
                                disponible = false;
                            } else {
                                disponible = true;
                            }

                        }
                        if (disponible) {
                            auxadapter.add(auxpro);
                        }

                    }
                    ArrayAdapter<obProductos> adaptercaballo = new ArrayAdapter<>(getActivity().getApplication(), android.R.layout.simple_dropdown_item_1line, auxadapter);
                    spinCaballo.setAdapter(adaptercaballo);



                }else{

                    ArrayAdapter<obProductos> adaptercaballo = new ArrayAdapter<>(getActivity().getApplication(), android.R.layout.simple_dropdown_item_1line, cabalgatalist);
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
        String f=etFecha.getText().toString();
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
        mDatabase.child("reserva").orderByChild("fecha").equalTo(f).addListenerForSingleValueEvent(new ValueEventListener() {
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
 /*
                            if ((horarec > hora1) && (horarec < hora2)) {//hay citas programadas en el itervalo
                                haycitas=true;
                                resul="Horario no disponible";
                                System.out.println("Hoy citas entre las"+hora1+" "+horarec+" "+hora2);
                            }

*/


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
                        ArrayAdapter<String> adapterNo = new ArrayAdapter<String>(getActivity().getApplication(), android.R.layout.simple_dropdown_item_1line, Nodisp);
                        spinCircuito.setAdapter(adapterNo);
                    }else{
                        ArrayAdapter<obProductos> adaptercircuito2 = new ArrayAdapter<obProductos>(getActivity().getApplication(), android.R.layout.simple_dropdown_item_1line, pcircuito);
                        spinCircuito.setAdapter(adaptercircuito2);


                    }


                }else{
                   System.out.println("no existen datos en la fecha"+f);
                    ArrayAdapter<obProductos> adaptercircuito2 = new ArrayAdapter<obProductos>(getActivity().getApplication(), android.R.layout.simple_dropdown_item_1line, pcircuito);
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
            ArrayAdapter<String> adapterNo = new ArrayAdapter<String>(getActivity().getApplication(), android.R.layout.simple_dropdown_item_1line, Nodisp);
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
                    ArrayAdapter<String> adapterGuia = new ArrayAdapter<String>(getActivity().getApplication(), android.R.layout.simple_dropdown_item_1line, pguia);
                    spinGuia.setAdapter(adapterGuia);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode==1) {
                imageuri = data.getData();
                sSalud.setText("Listo");
            }
            if (requestCode==2){

                imageuri2 = data.getData();
                sDNI.setText("Listo");
            }

        }
    }




        public void Subir(String act, Uri uri, String titular, objReserva reserva) {
        objAux aux =new objAux ("");
            final String messagePushID = act+titular+fecha;
        StorageReference storageReference = FirebaseStorage.getInstance().getReference();
            dialog = new ProgressDialog(getActivity().getApplicationContext());
        // Here we are uploading the pdf in firebase storage with the name of current time
            //String
            final StorageReference filepath = storageReference.child(messagePushID+ "." + "pdf");
            //Toast.makeText(getActivity().getApplicationContext(), filepath.getName(), Toast.LENGTH_SHORT).show();
            UploadTask uploadTask =filepath.putFile(uri);
            System.out.println("foikuhjvbfodevbnji22222222222222222222222");

            Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }

                    // Continue with the task to get the download URL
                    return filepath.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()){
                        Uri downloadUrl = task.getResult();
                        aux.setUrl(downloadUrl.toString());

                        if(act.equals("Salud")) {
                            reserva.setUrlBuenaSalud(downloadUrl.toString());
                            Subir("DNI", imageuri2, reserva.nombreTitular(), reserva);
                        }
                        if(act.equals("DNI")){
                            reserva.setUrlDNI(downloadUrl.toString());
                            writeNewReserva(reserva);
                        }

                    }
                }

            });

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
        DatePickerDialog datePickerDialog=new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int dia, int mes, int año) {
                EditText etPlannedDate = etFecha;
                etPlannedDate.setText("");
                final String selectedDate = twoDigits(año) + "-" + twoDigits(mes+1) + "-" + dia;
                etPlannedDate.setText(selectedDate);
                loadcircuito();

            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        //,dia,mes,año);
        datePickerDialog.show();
        loadcircuito();


    }








}