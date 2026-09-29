package com.grancolombiano.vetcareapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import java.util.List;

/**
 * Adaptador personalizado que vincula la lista de objetos Foto
 * con las vistas de cada fila (item_foto.xml) dentro del ListView (RF-03).
 */
public class FotoAdapter extends ArrayAdapter<Foto> {

    private final Context contexto;
    private final List<Foto> fotos;

    public FotoAdapter(Context contexto, List<Foto> fotos) {
        super(contexto, R.layout.item_foto, fotos);
        this.contexto = contexto;
        this.fotos = fotos;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View vista = convertView;
        if (vista == null) {
            vista = LayoutInflater.from(contexto).inflate(R.layout.item_foto, parent, false);
        }

        // Vinculación de variables con los identificadores del item
        ImageView imgItemFoto = vista.findViewById(R.id.imgItemFoto);
        TextView tvItemTitulo = vista.findViewById(R.id.tvItemTitulo);

        Foto foto = fotos.get(position);
        tvItemTitulo.setText(foto.getTitulo());
        imgItemFoto.setImageResource(foto.getImagenResId());

        return vista;
    }
}
