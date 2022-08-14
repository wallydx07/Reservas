package com.example.reservas.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

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
        nombre=findViewById(R.id.editTexProducto);
        precio=findViewById(R.id.editTextPrecio);

        loadproducto();
        guardar.setOnClickListener(this);


    }


    public void loadproducto() {
        String precio1="0";
        final List<objProducto> productolist = new ArrayList<>();
        mDatabase.child("producto").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {//ver
                    System.out.println("se van a cargar los elementos");
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        String nombre = ds.child("nombre").getValue().toString();
                        String tipo = ds.child("tipo").getValue().toString();
                        boolean disponible = (Boolean) ds.child("disponible").getValue();
                        productolist.add(new objProducto(nombre, tipo, precio1, disponible));
                    }
                    ArrayAdapter<objProducto> miAdapter = new ArrayAdapter<>(Nuevo_Producto.this, android.R.layout.simple_dropdown_item_1line, productolist);
                    spin.setAdapter(miAdapter);
                    System.out.println("Se vana  mostrar los elementos");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }



    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.bottonGuardarProducto:
                String nombre1=String.valueOf(nombre.getText());
                String tipo1=String.valueOf(spin.getSelectedItemId());
                String precio1=String.valueOf(precio.getText());
                boolean disponible1=true;
                writeNewProducto(nombre1, tipo1,precio1,disponible1);
               break;

        }
    }

    public void writeNewProducto( String name, String type, String price,boolean avaliable) {



         objProducto producto = new objProducto(name, type,price,avaliable);
        mDatabase.child("producto").push().setValue(producto);
    }



}