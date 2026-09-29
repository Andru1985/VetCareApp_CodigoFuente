package com.grancolombiano.vetcareapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * AgendamientoFragment
 *
 * Permite al paciente registrar una cita veterinaria.
 * La información queda guardada en SQLite mediante DatabaseHelper.
 */
public class AgendamientoFragment extends Fragment {

    // Elementos de la pantalla
    private TextView tvDoctorSeleccionado;
    private EditText etNombreMascota;
    private EditText etFechaCita;
    private EditText etHoraCita;
    private EditText etMotivoCita;
    private Button btnConfirmarCita;
    private Button btnCancelarCita;
    private TextView tvMensajeAgendamiento;

    // Base de datos
    private DatabaseHelper databaseHelper;

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        // Cargar diseño
        View vista = inflater.inflate(
                R.layout.fragment_agendamiento,
                container,
                false
        );

        // Conectar con la base de datos
        databaseHelper = new DatabaseHelper(requireContext());

        // Vincular elementos del XML
        tvDoctorSeleccionado =
                vista.findViewById(R.id.tvDoctorSeleccionado);

        etNombreMascota =
                vista.findViewById(R.id.etNombreMascota);

        etFechaCita =
                vista.findViewById(R.id.etFechaCita);

        etHoraCita =
                vista.findViewById(R.id.etHoraCita);

        etMotivoCita =
                vista.findViewById(R.id.etMotivoCita);

        btnConfirmarCita =
                vista.findViewById(R.id.btnConfirmarCita);

        btnCancelarCita =
                vista.findViewById(R.id.btnCancelarCita);

        tvMensajeAgendamiento =
                vista.findViewById(R.id.tvMensajeAgendamiento);

        // Botón confirmar
        btnConfirmarCita.setOnClickListener(
                v -> confirmarCita()
        );

        // Botón cancelar
        btnCancelarCita.setOnClickListener(
                v -> cancelarCita()
        );

        return vista;
    }

    /**
     * Valida los datos y guarda la cita en SQLite.
     */
    private void confirmarCita() {

        String mascota =
                etNombreMascota.getText().toString().trim();

        String fecha =
                etFechaCita.getText().toString().trim();

        String hora =
                etHoraCita.getText().toString().trim();

        String motivo =
                etMotivoCita.getText().toString().trim();

        // Validar mascota
        if (mascota.isEmpty()) {

            etNombreMascota.setError(
                    "Ingrese el nombre de la mascota"
            );

            etNombreMascota.requestFocus();
            return;
        }

        // Validar fecha
        if (fecha.isEmpty()) {

            etFechaCita.setError(
                    "Ingrese la fecha de la cita"
            );

            etFechaCita.requestFocus();
            return;
        }

        // Validar hora
        if (hora.isEmpty()) {

            etHoraCita.setError(
                    "Ingrese la hora de la cita"
            );

            etHoraCita.requestFocus();
            return;
        }

        // Guardar cita en la base de datos
        boolean guardada =
                databaseHelper.guardarCita(
                        mascota,
                        fecha,
                        hora,
                        motivo,
                        "Confirmada"
                );

        if (guardada) {

            // Mostrar confirmación
            tvMensajeAgendamiento.setText(
                    "✅ CITA AGENDADA CORRECTAMENTE\n\n" +
                            "🐾 Mascota: " + mascota + "\n" +
                            "📅 Fecha: " + fecha + "\n" +
                            "🕐 Hora: " + hora + "\n" +
                            "🩺 Motivo: " + motivo + "\n" +
                            "📌 Estado: Confirmada"
            );

            // Limpiar formulario
            etNombreMascota.setText("");
            etFechaCita.setText("");
            etHoraCita.setText("");
            etMotivoCita.setText("");

        } else {

            tvMensajeAgendamiento.setText(
                    "❌ No fue posible guardar la cita."
            );
        }
    }

    /**
     * Limpia el formulario.
     */
    private void cancelarCita() {

        etNombreMascota.setText("");
        etFechaCita.setText("");
        etHoraCita.setText("");
        etMotivoCita.setText("");

        tvMensajeAgendamiento.setText(
                "Agendamiento cancelado."
        );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        // Liberar referencias a las vistas
        tvDoctorSeleccionado = null;
        etNombreMascota = null;
        etFechaCita = null;
        etHoraCita = null;
        etMotivoCita = null;
        btnConfirmarCita = null;
        btnCancelarCita = null;
        tvMensajeAgendamiento = null;
    }
}
