package com.example.reservas.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.example.reservas.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Nuevo_Producto extends AppCompatActivity implements View.OnClickListener {
    Spinner spin;
    EditText nombre, precio, tipo,descripcion;
    DatabaseReference mDatabase;
    Button guardar;

    int cant;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_nuevo_producto);
        mDatabase= FirebaseDatabase.getInstance().getReference();
        spin = findViewById(R.id.spinTipo);
        guardar=findViewById(R.id.bottonGuardarProducto);
        nombre=findViewById(R.id.txtEditarProducto);
        precio=findViewById(R.id.txtEditarPrecio);
        descripcion=findViewById(R.id.txtEditarDescripcion);

       // loadproducto();
        guardar.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.bottonGuardarProducto:
                String nombre1=String.valueOf(nombre.getText());
                String tipo1=spin.getSelectedItem().toString();
                String precio1=precio.getText().toString();
                String d=descripcion.getText().toString();

                if (nombre1.isEmpty() || precio1.isEmpty() ||d.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Por favor complete todos los campos", Toast.LENGTH_SHORT).show();

                }else {
                    writeNewProducto(nombre1,precio1,tipo1,d);
                    finish();

                }











               break;

        }
    }

    public void writeNewProducto( String name, String price, String type, String desc) {

         obProductos producto = new obProductos(name,price,type);
         producto.setDescripcion(desc);
        mDatabase.child("producto").push().setValue(producto);
    }



}