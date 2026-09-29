package com.grancolombiano.vetcareapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * MenuFragment (Fragmento Izquierdo)
 * Contiene las 5 opciones de navegación (RF-01). Cada botón notifica a la
 * MainActivity, mediante la interfaz OnOpcionSeleccionadaListener, cuál
 * fragmento debe mostrarse en el Fragmento Derecho.
 */
public class MenuFragment extends Fragment {

    // Constantes de identificación de cada opción del menú
    public static final int OPCION_PERFIL = 1;
    public static final int OPCION_FOTOS = 2;
    public static final int OPCION_VIDEO = 3;
    public static final int OPCION_WEB = 4;
    public static final int OPCION_BOTONES = 5;

    // Variables de vista, vinculadas a los identificadores del layout
    private Button btnPerfil;
    private Button btnFotos;
    private Button btnVideo;
    private Button btnWeb;
    private Button btnAgendar;

    // Referencia al listener implementado por la Activity contenedora
    private OnOpcionSeleccionadaListener listener;

    /** Interfaz de comunicación Fragmento -> Activity */
    public interface OnOpcionSeleccionadaListener {
        void onOpcionSeleccionada(int opcion);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnOpcionSeleccionadaListener) {
            listener = (OnOpcionSeleccionadaListener) context;
        } else {
            throw new RuntimeException(context + " debe implementar OnOpcionSeleccionadaListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                              @Nullable Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_menu, container, false);

        // Vinculación de variables con los identificadores (findViewById)
        btnPerfil = vista.findViewById(R.id.btnPerfil);
        btnFotos = vista.findViewById(R.id.btnFotos);
        btnVideo = vista.findViewById(R.id.btnVideo);
        btnWeb = vista.findViewById(R.id.btnWeb);
        btnAgendar = vista.findViewById(R.id.btnAgendar);

        // Declaración de los métodos/eventos de cada botón (OnClickListener)
        btnPerfil.setOnClickListener(v -> listener.onOpcionSeleccionada(OPCION_PERFIL));
        btnFotos.setOnClickListener(v -> listener.onOpcionSeleccionada(OPCION_FOTOS));
        btnVideo.setOnClickListener(v -> listener.onOpcionSeleccionada(OPCION_VIDEO));
        btnWeb.setOnClickListener(v -> listener.onOpcionSeleccionada(OPCION_WEB));
        btnAgendar.setOnClickListener(v -> listener.onOpcionSeleccionada(OPCION_BOTONES));

        return vista;
    }
}
