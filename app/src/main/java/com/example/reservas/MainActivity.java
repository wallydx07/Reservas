package com.example.reservas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.example.reservas.view.Calendario;
import com.example.reservas.view.Producto;
import com.example.reservas.view.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
      Button buttoniniciarsecion;
      Calendario calendario= new Calendario();
      Producto producto= new Producto();
      Usuario usuario=new Usuario();
    private int dia, mes, año;
    AwesomeValidation awesomevalidation;
    FirebaseAuth firebasauth;
    EditText email,password;



   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        email=(EditText)findViewById(R.id.Email);
       password=(EditText)findViewById(R.id.Password);
       buttoniniciarsecion=(Button)findViewById(R.id.buttoniniciarsesion);
       firebasauth=FirebaseAuth.getInstance();
       awesomevalidation=new AwesomeValidation(ValidationStyle.BASIC);

    }

    private final BottomNavigationView.OnNavigationItemSelectedListener onNav=new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.usuario:
                    loadFragment(usuario);
                    return true;
                case R.id.calendario:
                    loadFragment(calendario);
                    return true;
                case R.id.producto:
                    loadFragment(producto);
                    return true;
            }
            return false;
        }
    };

        public void onclick(View view) {
            System.out.println("este es: "+view);
            String Semail=(String.valueOf(email.getText()));
            String Spassword=(String.valueOf(password.getText()));

            firebasauth.signInWithEmailAndPassword(Semail,Spassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        setContentView(R.layout.activity_main);
                        BottomNavigationView navigation=(BottomNavigationView)findViewById(R.id.navigation);
                        navigation.setOnNavigationItemSelectedListener(onNav);
                        loadFragment(producto);
                    }else{
                        String errocode=((FirebaseAuthException)task.getException()).getErrorCode();
                        System.out.println(errocode);
                        mError(errocode);
                                            }


                }
            });






        }
        public void mError(String error){



        }

        public void loadFragment(Fragment fragment){
            FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_container,fragment);
                transaction.commit();




        }
    public void onclick1(View view) {
        final EditText[] txtfecha = new EditText[1];
        final Calendar c= Calendar.getInstance();
        final Calendar newCalendar = Calendar.getInstance();
        dia=c.get(Calendar.DAY_OF_MONTH);
        mes=c.get(Calendar.MONTH);
        año=c.get(Calendar.YEAR);
        System.out.println(dia+" "+mes+" "+año+" sgrfdrhgbdnbh");
        DatePickerDialog datePickerDialog=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int dia, int mes, int año) {
                                //txtfecha[0]
                EditText etPlannedDate = (EditText) findViewById(R.id.txtCalendarioFecha);


              //  txtfecha[0].setText(i+"/"+i1+"/"+i2);
                final String selectedDate = twoDigits(año) + "/" + twoDigits(mes+1) + "/" + dia;
                etPlannedDate.setText(selectedDate);

            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        //,dia,mes,año);
        datePickerDialog.show();


    }
    private String twoDigits(int n) {
        return (n<=9) ? ("0"+n) : String.valueOf(n);
    }
    }

