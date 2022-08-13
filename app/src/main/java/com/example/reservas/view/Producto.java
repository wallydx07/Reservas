package com.example.reservas.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.reservas.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Producto#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Producto extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    ListViewAdapter adapter;

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


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Producto() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Producto.
     */
    // TODO: Rename and change types and number of parameters
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
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        v=inflater.inflate(R.layout.fragment_producto, container, false);
        final ListView lista =v.findViewById(R.id.listView1);
        adapter = new ListViewAdapter(getActivity().getApplicationContext(), titulo, imagenes);
        lista.setAdapter(adapter);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView adapterView, View view, int i, long l) {
                Intent intent=new Intent(getActivity(), Nuevo_Producto.class);
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





        return v;
    }
}