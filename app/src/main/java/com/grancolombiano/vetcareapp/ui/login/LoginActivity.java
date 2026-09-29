package com.grancolombiano.vetcareapp.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.grancolombiano.vetcareapp.DatabaseHelper;
import com.grancolombiano.vetcareapp.MainActivity;
import com.grancolombiano.vetcareapp.RegistroActivity;
import com.grancolombiano.vetcareapp.databinding.ActivityInicioBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityInicioBinding binding;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityInicioBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Conectar con la base de datos
        databaseHelper = new DatabaseHelper(this);

        // Botón iniciar sesión
        binding.login.setOnClickListener(v -> {

            String usuario = binding.username.getText().toString().trim();
            String contraseña = binding.password.getText().toString().trim();

            // Validar correo
            if (usuario.isEmpty()) {

                binding.username.setError("Ingrese su correo");
                binding.username.requestFocus();
                return;
            }

            // Validar contraseña
            if (contraseña.isEmpty()) {

                binding.password.setError("Ingrese su contraseña");
                binding.password.requestFocus();
                return;
            }

            // Verificar usuario en SQLite
            boolean usuarioExiste =
                    databaseHelper.verificarUsuario(
                            usuario,
                            contraseña
                    );

            if (usuarioExiste) {

                Toast.makeText(
                        LoginActivity.this,
                        "Inicio de sesión exitoso",
                        Toast.LENGTH_SHORT
                ).show();

                Intent intent = new Intent(
                        LoginActivity.this,
                        MainActivity.class
                );

                startActivity(intent);

                finish();

            } else {

                Toast.makeText(
                        LoginActivity.this,
                        "Correo o contraseña incorrectos",
                        Toast.LENGTH_LONG
                ).show();
            }
        });

        // Botón para crear cuenta
        if (binding.btnRegistro != null) {

            binding.btnRegistro.setOnClickListener(v -> {

                Intent intent = new Intent(
                        LoginActivity.this,
                        RegistroActivity.class
                );

                startActivity(intent);
            });
        }

    }
}