package com.example.reservas.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.reservas.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Producto#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Producto extends Fragment implements View.OnClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    ListViewAdapter adapter;
    FloatingActionButton bottomfl;
    Spinner spin;
    DatabaseReference mDatabase;

    String[] titulo = new String[]{
            "titulo1",
            "titulo2",
            "titulo3",
            "titulo4"
    };

    int[] imagenes = {
            R.drawable.ic_add,
            R.drawable.ic_add,
            R.drawable.ic_add,
            R.drawable.ic_add,
    };
    View v;


    public Producto() {
        // Required empty public constructor
    }



    public static Producto newInstance(String param1, String param2) {
        Producto fragment = new Producto();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDatabase= FirebaseDatabase.getInstance().getReference();

            }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v=inflater.inflate(R.layout.fragment_producto, container, false);
        bottomfl = v.findViewById(R.id.floatingActionButton2);
        bottomfl.setOnClickListener(this);
//      spin=v.findViewById(R.id.floatingActionButton2);
        loadproducto();
        return v;

    }

    public void loadproducto() {
        final List<obProductos> productolist = new ArrayList<>();
        final ListView lista =v.findViewById(R.id.listView1);
        mDatabase.child("producto").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {//ver
                    System.out.println("se van a cargar los elementos");
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        String nombre = ds.child("nombre").getValue().toString();
                        String tipo = ds.child("tipo").getValue().toString();
                        int precio=Integer.valueOf(ds.child("precio").getValue().toString());
                        productolist.add(new obProductos(nombre, precio,tipo));
                    }


                    try {
                        adapter = new ListViewAdapter(getActivity().getApplicationContext(), productolist);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    lista.setAdapter(adapter);
                    lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView adapterView, View view, int i, long l) {
                            Intent intent=new Intent(getActivity(), EditarProductos.class);
                            startActivity(intent);
                            Toast.makeText(getContext(), "presiono " + i, Toast.LENGTH_SHORT).show();
                        }
                    });
                    lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                        @Override
                        public boolean onItemLongClick(AdapterView adapterView, View view, int i, long l) {
                            Toast.makeText(getContext(), "presiono LARGO " + i, Toast.LENGTH_SHORT).show();
                            return false;
                        }
                    });

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
            case R.id.floatingActionButton2:
                Intent intent=new Intent(getActivity(), Nuevo_Producto.class);
                startActivity(intent);

                break;

        }
    }









    }



