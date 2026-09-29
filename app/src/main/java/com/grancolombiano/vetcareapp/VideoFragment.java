package com.grancolombiano.vetcareapp;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * VideoFragment (Opción 3 del menú)
 * Integra un VideoView con MediaController para reproducir tutoriales
 * informativos sobre salud y cuidado de mascotas (RF-04).
 */
public class VideoFragment extends Fragment {

    // Variables de vista vinculadas a los identificadores del layout
    private VideoView videoTutorial;
    private TextView tvEstadoVideo;

    // Controlador de reproducción (play, pausa, adelantar, retroceder)
    private MediaController mediaController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                              @Nullable Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_video, container, false);

        // Vinculación de variables con identificadores (findViewById)
        videoTutorial = vista.findViewById(R.id.videoTutorial);
        tvEstadoVideo = vista.findViewById(R.id.tvEstadoVideo);

        // Configuración del MediaController y la fuente del video
        mediaController = new MediaController(requireContext());
        mediaController.setAnchorView(videoTutorial);
        videoTutorial.setMediaController(mediaController);

        // NOTA: para usar un video propio, coloca un archivo .mp4 en res/raw/
        // (por ejemplo tutorial_cuidado_mascotas.mp4) y reemplaza la URI por:
        // Uri.parse("android.resource://" + requireContext().getPackageName() + "/" + R.raw.tutorial_cuidado_mascotas)
        Uri uriVideo = Uri.parse(
                "android.resource://" +
                        requireContext().getPackageName() +
                        "/" +
                        R.raw.cuidado_mascotas
        );

        videoTutorial.setVideoURI(uriVideo);

        // Declaración de eventos: inicio y finalización de la reproducción
        videoTutorial.setOnPreparedListener(mp -> tvEstadoVideo.setText("Video listo. Usa los controles para reproducir."));
        videoTutorial.setOnCompletionListener(mp -> tvEstadoVideo.setText("Reproducción finalizada."));

        return vista;
    }
}
