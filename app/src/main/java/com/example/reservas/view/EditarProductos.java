package com.example.reservas.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.reservas.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class EditarProductos extends AppCompatActivity {
String productoId,productoNombre,productoDescripcion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_productos);
        Button editar = findViewById(R.id.bottonGuardarP);
        EditText txtEditarProducto= findViewById(R.id.txtEditarProducto);
        EditText txtEditarPrecio=findViewById(R.id.txtEditarPrecio);
        EditText txtEditarDescripcion=findViewById(R.id.txtEditarDescripcion);
        productoId = getIntent().getStringExtra("productoId");
        productoNombre= getIntent().getStringExtra("productoN");
        productoDescripcion=getIntent().getStringExtra("productoD");
        txtEditarProducto.setText(productoNombre);
        txtEditarDescripcion.setText(productoDescripcion);

        System.out.println(productoId);
        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
String nombre=txtEditarProducto.getText().toString();
String precio=txtEditarPrecio.getText().toString();
String descripcion=txtEditarDescripcion.getText().toString();

                if (nombre.isEmpty() || precio.isEmpty()||descripcion.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Por favor complete todos los campos", Toast.LENGTH_SHORT).show();

                }else {
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("producto").child(productoId);
                    Map<String, Object> actualizacion = new HashMap<>();
                    actualizacion.put("nombre", nombre);
                    actualizacion.put("precio", precio);
                    actualizacion.put("descripcion", descripcion);
                    ref.updateChildren(actualizacion);

                    finish();

                }



            }
        });
    }

}