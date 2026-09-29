package com.grancolombiano.vetcareapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;
import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.widget.ImageView;

public class FotosFragment extends Fragment {

    private ListView listFotos;
    private TextView tvDescripcionFoto;

    private List<Foto> listaFotos;
    private FotoAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        View vista = inflater.inflate(
                R.layout.fragment_fotos,
                container,
                false
        );

        listFotos = vista.findViewById(R.id.listFotos);
        tvDescripcionFoto = vista.findViewById(R.id.tvDescripcionFoto);

        listaFotos = construirListaFotos();

        adapter = new FotoAdapter(
                requireContext(),
                listaFotos
        );

        listFotos.setAdapter(adapter);

        listFotos.setOnItemClickListener((parent, view, position, id) -> {

            Foto fotoSeleccionada = listaFotos.get(position);

            // Mostrar descripción
            tvDescripcionFoto.setText(fotoSeleccionada.getDescripcion());

            // Crear imagen grande
            ImageView imagenGrande = new ImageView(requireContext());

            imagenGrande.setImageResource(fotoSeleccionada.getImagenResId());
            imagenGrande.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imagenGrande.setBackgroundColor(Color.WHITE);
            imagenGrande.setPadding(20, 20, 20, 20);

            // Crear ventana
            AlertDialog dialogo = new AlertDialog.Builder(requireContext())
                    .setView(imagenGrande)
                    .create();

            // Fondo redondeado/transparente
            if (dialogo.getWindow() != null) {
                dialogo.getWindow().setBackgroundDrawable(
                        new ColorDrawable(Color.TRANSPARENT)
                );
            }

            dialogo.show();

            // Tamaño de la ventana
            if (dialogo.getWindow() != null) {
                dialogo.getWindow().setLayout(
                        (int) (getResources().getDisplayMetrics().widthPixels * 0.90),
                        (int) (getResources().getDisplayMetrics().heightPixels * 0.70)
                );

                dialogo.getWindow().setGravity(Gravity.CENTER);
            }
        });
        return vista;
    }

    private List<Foto> construirListaFotos() {

        List<Foto> lista = new ArrayList<>();

        lista.add(
                new Foto(
                        "Consultorio Principal",
                        "Consultorio veterinario equipado para la valoración general de perros y gatos.",
                        R.drawable.consultorio
                )
        );

        lista.add(
                new Foto(
                        "Paciente: Max",
                        "Max, paciente canino, durante una valoración veterinaria.",
                        R.drawable.perro_max
                )
        );

        lista.add(
                new Foto(
                        "Sala de Cirujía",
                        "Sala equipada para procedimientos quirúrgicos veterinarios.",
                        R.drawable.sala_cirujia
                )
        );

        lista.add(
                new Foto(
                        "Paciente: Luna",
                        "Luna, paciente felina durante una consulta veterinaria.",
                        R.drawable.gato_luna
                )
        );

        lista.add(
                new Foto(
                        "Hospitalización",
                        "Área destinada a pacientes que requieren observación y cuidados veterinarios.",
                        R.drawable.hospitalizacion
                )
        );

        return lista;
    }
}