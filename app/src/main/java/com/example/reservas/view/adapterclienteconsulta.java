package com.example.reservas.view;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.reservas.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.itextpdf.text.DocumentException;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class adapterclienteconsulta extends BaseAdapter {
    Context context;
    objPersona cliente;
    List<objPersona> clientelist;
    LayoutInflater inflater;
    RelativeLayout rlMainLayout;


    public adapterclienteconsulta(Context context, List<objPersona> clientelist) {
        this.context = context;
        this.clientelist = clientelist;
    }

    public List<objPersona> getData() {
        return clientelist;


    }
    @Override
    public int getCount() {
        return clientelist.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView txtnombre, txtdni,txtfecha, txtobs;
        ImageView imagen;
        ImageButton borrar;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.activity_adapterclienteconsulta, parent, false);
        txtnombre = (TextView) itemView.findViewById(R.id.txtNombreClienteAdapter1);
        txtdni = (TextView) itemView.findViewById(R.id.txtDNIClienteAdapter1);
        txtfecha = (TextView) itemView.findViewById(R.id.txtFechaClienteAdapter1);
        borrar=(ImageButton)itemView.findViewById(R.id.imageButton31);
        imagen=(ImageView)itemView.findViewById(R.id.imageViewClienteAdapter);
        txtobs=(TextView)itemView.findViewById(R.id.txtDescripcionClienteAdapter1);
        borrar.setImageResource(R.drawable.descargar);
        borrar.setAdjustViewBounds(true);
        borrar.setBackgroundResource(android.R.color.transparent);
        cliente = clientelist.get(position);
        String nombre = cliente.getNombre();
        String dni=  cliente.getDni();
        String fecha =cliente.getFechaN();
        String onservacion=cliente.getObs();
        String cab=cliente.getCaballo();
        txtnombre.setText(nombre);
        txtdni.setText(dni);
        txtfecha.setText(fecha);
        txtobs.setText(onservacion);
        if(cab.equals("Sin Cabalgata")){
            imagen.setImageResource(R.drawable.caminata);
        }
        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //=====
                    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
                    // Obtener una instancia de la base de datos por defecto
                    FirebaseDatabase database = FirebaseDatabase.getInstance();


                    // Obtén una referencia a la ubicación de los datos que quieres leer
                 //   DatabaseReference ref = database.getReference("1ojMZEeUI9yYKMtWABS522gfMTOUE3C2MYZOUED9W_9M/Respuestas de formulario 1/"+dni);

                    // Agrega un listener para leer los datos
                 //   ref.addListenerForSingleValueEvent(new ValueEventListener() {
                  //      @Override
                  //      public void onDataChange(DataSnapshot dataSnapshot) {

                    System.out.println("__________"+dni+"_________________");
                    mDatabase.child("1ojMZEeUI9yYKMtWABS522gfMTOUE3C2MYZOUED9W_9M/Respuestas de formulario 1/" + dni).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {


                                // Obtén los datos como un mapa
                                // Map<String, String> datos = (Map<String, String>) dataSnapshot.getValue();
                                Map<String, Object> datos = (Map<String, Object>) snapshot.getValue();

                                // Crea un nuevo objeto con los datos obtenidos

                                Long dni = datos.get("dni") != null ? Long.parseLong(datos.get("dni").toString()) : 0;
                                String ayn = datos.get("ayn") != null ? datos.get("ayn").toString() : "";
                                String fnac = datos.get("fnac") != null ? datos.get("fnac").toString() : "";
                                String edad = datos.get("edad") != null ? datos.get("edad").toString() : "";
                                String sexo = datos.get("sexo") != null ? datos.get("sexo").toString() : "";
                                String domicilio = datos.get("domicilio") != null ? datos.get("domicilio").toString() : "";
                                String celular = datos.get("celular") != null ? datos.get("celular").toString() : "";
                                String mail = datos.get("mail") != null ? datos.get("mail").toString() : "";
                                String profesion = datos.get("profesion") != null ? datos.get("profesion").toString() : "";
                                String ocupacion = datos.get("ocupacion") != null ? datos.get("ocupacion").toString() : "";
                                String gsanguineo = datos.get("gsanguineo") != null ? datos.get("gsanguineo").toString() : "";
                                String factor = datos.get("factor") != null ? datos.get("factor").toString() : "";
                                String altura = datos.get("altura") != null ? datos.get("altura").toString() : "";
                                String peso = datos.get("peso") != null ? datos.get("peso").toString() : "";
                                String expcab = datos.get("expcab") != null ? datos.get("expcab").toString() : "";
                                String expdur = datos.get("expdur") != null ? datos.get("expdur").toString() : "";
                                String emerayn = datos.get("emerayn") != null ? datos.get("emerayn").toString() : "";
                                String emerdom = datos.get("emerdom") != null ? datos.get("emerdom").toString() : "";
                                String emerparen = datos.get("emerparen") != null ? datos.get("emerparen").toString() : "";
                                String emertelefono = datos.get("emertelefono") != null ? datos.get("emertelefono").toString() : "";
                                String datosseguromedico = datos.get("datosseguromedico") != null ? datos.get("datosseguromedico").toString() : "";
                                String afeccionresp = datos.get("afeccionresp") != null ? datos.get("afeccionresp").toString() : "";
                                String cualafeccionresp = datos.get("cualafeccionresp") != null ? datos.get("cualafeccionresp").toString() : "";
                                String anginas = datos.get("anginas") != null ? datos.get("anginas").toString() : "";
                                String asma = datos.get("asma") != null ? datos.get("asma").toString() : "";
                                String otrasrespiratorio = datos.get("otrasrespiratorio") != null ? datos.get("otrasrespiratorio").toString() : "";
                                String coag = datos.get("coag") != null ? datos.get("coag").toString() : "";
                                String hemoragia = datos.get("hemoragia") != null ? datos.get("hemoragia").toString() : "";
                                String probpres = datos.get("probpres") != null ? datos.get("probpres").toString() : "";
                                String otrascirc = datos.get("otrascirc") != null ? datos.get("otrascirc").toString() : "";
                                String prbgastr = datos.get("prbgastr") != null ? datos.get("prbgastr").toString() : "";
                                String cualprobgast = datos.get("cualprobgast") != null ? datos.get("cualprobgast").toString() : "";
                                String otrosDigestivo = datos.get("otrosdigestivo") != null ? datos.get("otrosdigestivo").toString() : "";
                                String cardiopatia = datos.get("cardiopatia") != null ? datos.get("cardiopatia").toString() : "";
                                String soplo = datos.get("soplo") != null ? datos.get("soplo").toString() : "";
                                String tension = datos.get("tension") != null ? datos.get("tension").toString() : "";
                                String pulso = datos.get("pulso") != null ? datos.get("pulso").toString() : "";
                                String medAlerg = datos.get("medalerg") != null ? datos.get("medalerg").toString() : "";
                                String cualMed = datos.get("cualmed") != null ? datos.get("cualmed").toString() : "";
                                String lesCintura = datos.get("lescintura") != null ? datos.get("lescintura").toString() : "";
                                String cualCintura = datos.get("cualcintura") != null ? datos.get("cualcintura").toString() : "";
                                String lesHombro = datos.get("leshombro") != null ? datos.get("leshombro").toString() : "";
                                String cualHombro = datos.get("cualhombro") != null ? datos.get("cualhombro").toString() : "";
                                String diabetes = datos.get("diabetes") != null ? datos.get("diabetes").toString() : "";
                                String lesCabeza = datos.get("lescabeza") != null ? datos.get("lescabeza").toString() : "";
                                String cirugia = datos.get("cirugia") != null ? datos.get("cirugia").toString() : "";
                                String cualCirugia = datos.get("cualcigia") != null ? datos.get("cualcigia").toString() : "";
                                String otraAlerg = datos.get("otroalergia") != null ? datos.get("otroalergia").toString() : "";
                                String sufEnfermedad = datos.get("sufreenfermedad") != null ? datos.get("sufreenfermedad").toString() : "";
                                String cualEnfermedad = datos.get("cualenfermedad") != null ? datos.get("cualenfermedad").toString() : "";
                                String epilepcia = datos.get("epilepcia") != null ? datos.get("epilepcia").toString() : "";
                                String convulsion = datos.get("convulcion") != null ? datos.get("convulcion").toString() : "";
                                String queProvComb = datos.get("queprovcomb") != null ? datos.get("queprovcomb").toString() : "";
                                String friolenta = datos.get("friolenta") != null ? datos.get("friolenta").toString() : "";
                                String embarazo = datos.get("embarazo") != null ? datos.get("embarazo").toString() : "";
                                String mesesEmbarazo = datos.get("meses") != null ? datos.get("meses").toString() : "";
                                String tratMedico = datos.get("tratmedico") != null ? datos.get("tratmedico").toString() : "";
                                String cualTratamiento = datos.get("cualtratamiento") != null ? datos.get("cualtratamiento").toString() : "";
                                String fobias = datos.get("fobias") != null ? datos.get("fobias").toString() : "";
                                String probmusc = datos.get("probmusc") != null ? datos.get("probmusc").toString() : "";
                                String musculares = datos.get("cualesmusculres") != null ? datos.get("cualesmusculres").toString() : "";
                                String medicamentos = datos.get("cualesmedicamentos") != null ? datos.get("cualesmedicamentos").toString() : "";
                                String regimen = datos.get("regimen") != null ? datos.get("regimen").toString() : "";
                                String alimento = datos.get("alimento") != null ? datos.get("alimento").toString() : "";
                                String tomaMed = datos.get("tomamed") != null ? datos.get("tomamed").toString() : "";


                                objSalud salud = new objSalud(dni, ayn, fnac, edad, sexo, domicilio, celular, mail, profesion, ocupacion, gsanguineo, factor, altura, peso, expcab, expdur, emerayn, emerdom, emerparen, emertelefono, datosseguromedico, afeccionresp, cualafeccionresp, anginas, asma, otrasrespiratorio, coag, hemoragia, probpres, otrascirc, prbgastr, cualprobgast, otrosDigestivo, cardiopatia, soplo, tension, pulso, medAlerg, cualMed, lesCintura, cualCintura, lesHombro, cualHombro, diabetes, lesCabeza, cirugia, cualCirugia, otraAlerg, sufEnfermedad, cualEnfermedad, epilepcia, convulsion, queProvComb, friolenta, embarazo, mesesEmbarazo, tratMedico, cualTratamiento, fobias, probmusc, musculares, tomaMed, medicamentos, regimen, alimento);
                                try {
                                    new generarPDF().generatePDF(context.getApplicationContext(), salud);
                                    Toast.makeText(context.getApplicationContext(), "Se ha generado el pdf", Toast.LENGTH_SHORT).show();

                                } catch (IOException | DocumentException e) {
                                    e.printStackTrace();
                                    Toast.makeText(context.getApplicationContext(), "Ha ocurrido un erro al generar, reintente", Toast.LENGTH_SHORT).show();
                                }


                            } else {
                                Toast.makeText(context.getApplicationContext(), "No hay datos asociados a este cliente", Toast.LENGTH_SHORT).show();


                            }
                        }



                        @Override
                        public void onCancelled(DatabaseError error) {
                            System.out.println("Error no se encontro:"+error);
                            Toast.makeText(context.getApplicationContext(), "no existen esos datos", Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
                    //=====


        });
        return itemView;
    }
}