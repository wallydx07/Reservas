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
    List<obProductos> producto1;
    LayoutInflater inflater;

    public ListViewAdapter(Context context, List<obProductos> Producto) {
        this.context = context;
        this.producto1 = Producto;
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
        TextView txtTitle, txtSubTitle;
        ImageView imgImg;

        //http://developer.android.com/intl/es/reference/android/view/LayoutInflater.html
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.list_row, parent, false);

        // Locate the TextViews in listview_item.xml
        txtTitle = (TextView) itemView.findViewById(R.id.list_row_title);
        txtSubTitle = (TextView) itemView.findViewById(R.id.list_row_subtitle);
        imgImg = (ImageView) itemView.findViewById(R.id.list_row_image);

        // Capture position and set to the TextViews
        producto = producto1.get(position);
        String nombre = producto.getNombre();
        int precio = producto.getPrecio();
        txtTitle.setText(nombre);
        txtSubTitle.setText(String.valueOf(precio));
        imgImg.setImageResource(R.drawable.ic_person);

        return itemView;
    }
}
