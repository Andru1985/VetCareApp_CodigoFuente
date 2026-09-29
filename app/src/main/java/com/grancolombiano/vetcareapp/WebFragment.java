package com.grancolombiano.vetcareapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class WebFragment extends Fragment {

    private EditText etUrl;
    private Button btnCargarUrl;
    private WebView webViewClinica;

    @SuppressLint("SetJavaScriptEnabled")
    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        View vista = inflater.inflate(
                R.layout.fragment_web,
                container,
                false
        );

        etUrl = vista.findViewById(R.id.etUrl);
        btnCargarUrl = vista.findViewById(R.id.btnCargarUrl);
        webViewClinica = vista.findViewById(R.id.webViewClinica);

        // Configurar WebView
        webViewClinica.getSettings().setJavaScriptEnabled(true);
        webViewClinica.getSettings().setDomStorageEnabled(true);

        // Abrir páginas dentro de la aplicación
        webViewClinica.setWebViewClient(new WebViewClient());

        // Página inicial
        String pagina = "https://www.politecnico.edu.co";

        etUrl.setText(pagina);
        webViewClinica.loadUrl(pagina);

        // Botón Cargar
        btnCargarUrl.setOnClickListener(v -> {

            String url = etUrl.getText().toString().trim();

            if (url.isEmpty()) {
                Toast.makeText(
                        requireContext(),
                        "Ingresa una URL",
                        Toast.LENGTH_SHORT
                ).show();
                return;
            }

            if (!url.startsWith("http://")
                    && !url.startsWith("https://")) {
                url = "https://" + url;
            }

            webViewClinica.loadUrl(url);
        });

        return vista;
    }

    @Override
    public void onDestroyView() {

        if (webViewClinica != null) {
            webViewClinica.destroy();
            webViewClinica = null;
        }

        super.onDestroyView();
    }
}
