package com.example.reservas.view;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.reservas.R;

import java.util.List;

public class ListViewAdapter extends BaseAdapter {
    // Declare Variables
    Context context;
    obProductos producto;
    static List<obProductos> producto1;
    LayoutInflater inflater;


    public ListViewAdapter(Context context, List<obProductos> Producto) {
        this.context = context;
        this.producto1 = Producto;
    }

    public static Object getItemAtPosition(int i) {

        return producto1.get(i);
    }

    @Override
    public int getCount() {

        return producto1.size();
    }

    @Override
    public Object getItem(int position) {

        return null;
    }

    @Override
    public long getItemId(int position) {

        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        // Declare Variables
        TextView txtTitle, txtSubTitle, txtDescription;
        ImageView imgImg;

        //http://developer.android.com/intl/es/reference/android/view/LayoutInflater.html
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.list_row, parent, false);

        // Locate the TextViews in listview_item.xml
        txtTitle = (TextView) itemView.findViewById(R.id.list_row_title);
        txtSubTitle = (TextView) itemView.findViewById(R.id.list_row_subtitle);
        txtDescription=(TextView) itemView.findViewById(R.id.list_row_description);
        imgImg = (ImageView) itemView.findViewById(R.id.list_row_image);

        // Capture position and set to the TextViews
        producto = producto1.get(position);
        String nombre = producto.getNombre();
        String precio = producto.getPrecio();
        String descripcion= producto.getDescripcion();
        txtTitle.setText(nombre);
        txtSubTitle.setText(String.valueOf(precio));
        txtDescription.setText(String.valueOf(descripcion));
        if(producto.getTipo().equals("Circuito")) {
            imgImg.setImageResource(R.drawable.caminata);
        }
        if(producto.getTipo().equals("Caballo")) {
            imgImg.setImageResource(R.drawable.caballo);
        }

        return itemView;
    }
}
