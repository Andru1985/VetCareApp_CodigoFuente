package com.grancolombiano.vetcareapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity
        implements MenuFragment.OnOpcionSeleccionadaListener {

    private TextView btnPerfil;
    private TextView btnFotos;
    private TextView btnVideo;
    private TextView btnCitas;
    private TextView btnMisCitas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // Botones del menú inferior
        btnPerfil = findViewById(R.id.btnPerfil);
        btnFotos = findViewById(R.id.btnFotos);
        btnVideo = findViewById(R.id.btnVideo);
        btnCitas = findViewById(R.id.btnCitas);
        btnMisCitas = findViewById(R.id.btnMisCitas);

        // Perfil
        btnPerfil.setOnClickListener(v ->
                mostrarFragmento(new PerfilFragment()));

        // Fotos
        btnFotos.setOnClickListener(v ->
                mostrarFragmento(new FotosFragment()));

        // Videos
        btnVideo.setOnClickListener(v ->
                mostrarFragmento(new VideoFragment()));

        // Citas
        btnCitas.setOnClickListener(v ->
                mostrarFragmento(new AgendamientoFragment()));

        btnMisCitas.setOnClickListener(v ->
                mostrarFragmento(new MisCitasFragment()));

        // Fragmento inicial
        if (savedInstanceState == null) {
            mostrarFragmento(new PerfilFragment());
        }
    }

    /**
     * Opciones provenientes del MenuFragment.
     */
    @Override
    public void onOpcionSeleccionada(int opcion) {

        switch (opcion) {

            case MenuFragment.OPCION_PERFIL:
                mostrarFragmento(new PerfilFragment());
                break;

            case MenuFragment.OPCION_FOTOS:
                mostrarFragmento(new FotosFragment());
                break;

            case MenuFragment.OPCION_VIDEO:
                mostrarFragmento(new VideoFragment());
                break;

            case MenuFragment.OPCION_WEB:
                // Abrir página web
                mostrarFragmento(new WebFragment());
                break;

            case MenuFragment.OPCION_BOTONES:
                mostrarFragmento(new AgendamientoFragment());
                break;
        }
    }

    /**
     * Cambia el Fragment mostrado.
     */
    private void mostrarFragmento(Fragment fragmento) {

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameContenido, fragmento)
                .commit();
    }
}