package com.grancolombiano.vetcareapp;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MisCitasFragment extends Fragment {

    private LinearLayout contenedorCitas;
    private DatabaseHelper databaseHelper;

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        View vista = inflater.inflate(
                R.layout.fragment_mis_citas,
                container,
                false
        );

        contenedorCitas =
                vista.findViewById(R.id.contenedorCitas);

        databaseHelper =
                new DatabaseHelper(requireContext());

        cargarCitas();

        return vista;
    }

    private void cargarCitas() {

        Cursor cursor = databaseHelper.obtenerCitas();

        if (cursor.getCount() == 0) {

            TextView mensaje = new TextView(requireContext());

            mensaje.setText(
                    "📅\n\n" +
                            "No tienes citas agendadas.\n\n" +
                            "Cuando agendes una cita aparecerá aquí."
            );

            mensaje.setTextSize(18);
            mensaje.setTextColor(Color.DKGRAY);
            mensaje.setGravity(android.view.Gravity.CENTER);
            mensaje.setPadding(30, 80, 30, 80);

            contenedorCitas.addView(mensaje);

            cursor.close();
            return;
        }

        while (cursor.moveToNext()) {

            String mascota =
                    cursor.getString(
                            cursor.getColumnIndexOrThrow("mascota")
                    );

            String fecha =
                    cursor.getString(
                            cursor.getColumnIndexOrThrow("fecha")
                    );

            String hora =
                    cursor.getString(
                            cursor.getColumnIndexOrThrow("hora")
                    );

            String motivo =
                    cursor.getString(
                            cursor.getColumnIndexOrThrow("motivo")
                    );

            String estado =
                    cursor.getString(
                            cursor.getColumnIndexOrThrow("estado")
                    );

            // Tarjeta de la cita
            LinearLayout tarjeta =
                    new LinearLayout(requireContext());

            tarjeta.setOrientation(
                    LinearLayout.VERTICAL
            );

            tarjeta.setPadding(
                    30,
                    30,
                    30,
                    30
            );

            LinearLayout.LayoutParams parametros =
                    new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                    );

            parametros.setMargins(
                    0,
                    0,
                    0,
                    25
            );

            tarjeta.setLayoutParams(parametros);

            // Mascota
            TextView titulo =
                    new TextView(requireContext());

            titulo.setText(
                    "🐾 " + mascota
            );

            titulo.setTextSize(23);
            titulo.setTextColor(
                    Color.rgb(25, 118, 210)
            );
            titulo.setTypeface(
                    null,
                    android.graphics.Typeface.BOLD
            );

            tarjeta.addView(titulo);

            // Fecha
            TextView textoFecha =
                    new TextView(requireContext());

            textoFecha.setText(
                    "📅 Fecha: " + fecha
            );

            textoFecha.setTextSize(17);
            textoFecha.setPadding(0, 15, 0, 0);

            tarjeta.addView(textoFecha);

            // Hora
            TextView textoHora =
                    new TextView(requireContext());

            textoHora.setText(
                    "🕐 Hora: " + hora
            );

            textoHora.setTextSize(17);
            textoHora.setPadding(0, 10, 0, 0);

            tarjeta.addView(textoHora);

            // Motivo
            TextView textoMotivo =
                    new TextView(requireContext());

            textoMotivo.setText(
                    "🩺 Motivo: " +
                            (motivo.isEmpty()
                                    ? "No especificado"
                                    : motivo)
            );

            textoMotivo.setTextSize(17);
            textoMotivo.setPadding(0, 10, 0, 0);

            tarjeta.addView(textoMotivo);

            // Estado
            TextView textoEstado =
                    new TextView(requireContext());

            textoEstado.setText(
                    "📌 Estado: " + estado
            );

            textoEstado.setTextSize(17);
            textoEstado.setTextColor(
                    Color.rgb(46, 125, 50)
            );

            textoEstado.setTypeface(
                    null,
                    android.graphics.Typeface.BOLD
            );

            textoEstado.setPadding(
                    0,
                    15,
                    0,
                    0
            );

            tarjeta.addView(textoEstado);

            contenedorCitas.addView(tarjeta);
        }

        cursor.close();
    }
}