package com.grancolombiano.vetcareapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * PerfilFragment (Opción 1 del menú)
 * Despliega la información del médico veterinario dentro de un ScrollView (RF-02).
 */
public class PerfilFragment extends Fragment {

    // Variables de vista vinculadas a los identificadores del layout
    private ScrollView scrollPerfil;
    private ImageView imgVeterinario;
    private TextView tvNombreVeterinario;
    private TextView tvEspecialidad;
    private TextView tvEstudios;
    private TextView tvExperiencia;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                              @Nullable Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_perfil, container, false);

        // Vinculación de variables con identificadores (findViewById)
        scrollPerfil = vista.findViewById(R.id.scrollPerfil);
        imgVeterinario = vista.findViewById(R.id.imgVeterinario);
        tvNombreVeterinario = vista.findViewById(R.id.tvNombreVeterinario);
        tvEspecialidad = vista.findViewById(R.id.tvEspecialidad);
        tvEstudios = vista.findViewById(R.id.tvEstudios);
        tvExperiencia = vista.findViewById(R.id.tvExperiencia);

        // Este fragmento es de solo lectura: no requiere eventos de click,
        // el ScrollView gestiona automáticamente el desplazamiento (RNF-02)
        cargarDatosVeterinario();

        return vista;
    }

    /** Método que asigna los datos del veterinario a las vistas correspondientes */
    private void cargarDatosVeterinario() {
        tvNombreVeterinario.setText("Dr. Carlos Mendoza");
        tvEspecialidad.setText("Especialista en Medicina Canina y Felina");
    }
}
