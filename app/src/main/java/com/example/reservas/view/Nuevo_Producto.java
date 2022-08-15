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

import com.example.reservas.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Nuevo_Producto extends AppCompatActivity implements View.OnClickListener {
    Spinner spin;
    EditText nombre, precio, tipo;
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

       // loadproducto();
        guardar.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.bottonGuardarProducto:
                String nombre1=String.valueOf(nombre.getText());
                String tipo1=spin.getSelectedItem().toString();
                int precio1=Integer.valueOf(String.valueOf(precio.getText()));
                writeNewProducto(nombre1,precio1,tipo1);
               break;

        }
    }

    public void writeNewProducto( String name, int price, String type) {

         obProductos producto = new obProductos(name,price,type);
        mDatabase.child("producto").push().setValue(producto);
    }



}