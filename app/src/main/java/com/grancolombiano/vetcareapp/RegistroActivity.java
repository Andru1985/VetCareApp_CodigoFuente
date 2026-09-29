package com.grancolombiano.vetcareapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegistroActivity extends AppCompatActivity {

    private EditText edtNombre;
    private EditText edtApellido;
    private EditText edtCorreo;
    private EditText edtTelefono;
    private EditText edtPassword;
    private EditText edtConfirmarPassword;

    private Button btnCrearCuenta;
    private TextView txtVolverLogin;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_registro2);
        databaseHelper = new DatabaseHelper(this);

        edtNombre = findViewById(R.id.edtNombre);
        edtApellido = findViewById(R.id.edtApellido);
        edtCorreo = findViewById(R.id.edtCorreo);
        edtTelefono = findViewById(R.id.edtTelefono);
        edtPassword = findViewById(R.id.edtPassword);
        edtConfirmarPassword = findViewById(R.id.edtConfirmarPassword);

        btnCrearCuenta = findViewById(R.id.btnCrearCuenta);
        txtVolverLogin = findViewById(R.id.txtVolverLogin);

        btnCrearCuenta.setOnClickListener(v -> registrarUsuario());

        txtVolverLogin.setOnClickListener(v -> {
            finish();
        });
    }

    private void registrarUsuario() {

        String nombre = edtNombre.getText().toString().trim();
        String apellido = edtApellido.getText().toString().trim();
        String correo = edtCorreo.getText().toString().trim();
        String telefono = edtTelefono.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();
        String confirmarPassword = edtConfirmarPassword.getText().toString().trim();

        if (TextUtils.isEmpty(nombre)) {
            edtNombre.setError("Ingrese su nombre");
            edtNombre.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(apellido)) {
            edtApellido.setError("Ingrese su apellido");
            edtApellido.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(correo)) {
            edtCorreo.setError("Ingrese su correo");
            edtCorreo.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
            edtCorreo.setError("Ingrese un correo válido");
            edtCorreo.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(telefono)) {
            edtTelefono.setError("Ingrese su teléfono");
            edtTelefono.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            edtPassword.setError("Ingrese una contraseña");
            edtPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            edtPassword.setError("La contraseña debe tener mínimo 6 caracteres");
            edtPassword.requestFocus();
            return;
        }

        if (!password.equals(confirmarPassword)) {
            edtConfirmarPassword.setError("Las contraseñas no coinciden");
            edtConfirmarPassword.requestFocus();
            return;
        }

        boolean registrado = databaseHelper.registrarUsuario(
                nombre,
                apellido,
                correo,
                telefono,
                password
        );

        if (registrado) {

            Toast.makeText(
                    RegistroActivity.this,
                    "Usuario registrado correctamente",
                    Toast.LENGTH_LONG
            ).show();

            finish();

        } else {

            Toast.makeText(
                    RegistroActivity.this,
                    "El correo ya está registrado",
                    Toast.LENGTH_LONG
            ).show();
        }
    }
}

