package com.example.reservas.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.reservas.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Calendario#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Calendario extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters

    private int dia, mes, año;
    private TextView textview;
    private ListView listview;
    private String horas[]={"8:00","9:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00","20:00"};
    private View v;
    String formattedDate;
    public Calendario() {
        // Required empty public constructor

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Calendario.
     */
    // TODO: Rename and change types and number of parameters
    public Calendario newInstance(String param1, String param2) {
        Calendario fragment = new Calendario();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        Button buttonfecha;


        buttonfecha=(Button)getView().findViewById(R.id.buttonfecha);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("dd/MMM/yyyy");
        formattedDate = df.format(c);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        v=inflater.inflate(R.layout.fragment_calendario, container, false);
       textview=v.findViewById(R.id.txtfecha);
       textview.setText(formattedDate);
        listview=v.findViewById(R.id.listviewhoras);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity().getApplicationContext() ,R.layout.listview_item,horas);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent=new Intent(getActivity(), nuevaReserva.class);
                startActivity(intent);

            }
        });
        return v;
       // return inflater.inflate(R.layout.fragment_calendario, container, false);
    }

}