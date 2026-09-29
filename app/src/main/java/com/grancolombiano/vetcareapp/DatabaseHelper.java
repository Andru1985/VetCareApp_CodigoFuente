package com.grancolombiano.vetcareapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "VetCareApp.db";
    private static final int DATABASE_VERSION = 2;

    public static final String TABLE_USUARIOS = "usuarios";
    public static final String TABLE_CITAS = "citas";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Tabla de usuarios
        String crearUsuarios = "CREATE TABLE " + TABLE_USUARIOS + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre TEXT NOT NULL, " +
                "apellido TEXT NOT NULL, " +
                "correo TEXT UNIQUE NOT NULL, " +
                "telefono TEXT NOT NULL, " +
                "password TEXT NOT NULL" +
                ")";

        db.execSQL(crearUsuarios);

        // Tabla de citas
        String crearCitas = "CREATE TABLE " + TABLE_CITAS + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "mascota TEXT NOT NULL, " +
                "fecha TEXT NOT NULL, " +
                "hora TEXT NOT NULL, " +
                "motivo TEXT, " +
                "estado TEXT NOT NULL" +
                ")";

        db.execSQL(crearCitas);
    }

    @Override
    public void onUpgrade(
            SQLiteDatabase db,
            int oldVersion,
            int newVersion) {

        db.execSQL(
                "DROP TABLE IF EXISTS " + TABLE_CITAS
        );

        db.execSQL(
                "DROP TABLE IF EXISTS " + TABLE_USUARIOS
        );

        onCreate(db);
    }

    // ==========================
    // REGISTRAR USUARIO
    // ==========================

    public boolean registrarUsuario(
            String nombre,
            String apellido,
            String correo,
            String telefono,
            String password) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues valores = new ContentValues();

        valores.put("nombre", nombre);
        valores.put("apellido", apellido);
        valores.put("correo", correo);
        valores.put("telefono", telefono);
        valores.put("password", password);

        long resultado = db.insert(
                TABLE_USUARIOS,
                null,
                valores
        );

        db.close();

        return resultado != -1;
    }

    // ==========================
    // VERIFICAR USUARIO
    // ==========================

    public boolean verificarUsuario(
            String correo,
            String password) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT id FROM " + TABLE_USUARIOS +
                        " WHERE correo = ? AND password = ?",
                new String[]{
                        correo,
                        password
                }
        );

        boolean existe = cursor.moveToFirst();

        cursor.close();
        db.close();

        return existe;
    }

    // ==========================
    // GUARDAR CITA
    // ==========================

    public boolean guardarCita(
            String mascota,
            String fecha,
            String hora,
            String motivo,
            String estado) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues valores = new ContentValues();

        valores.put("mascota", mascota);
        valores.put("fecha", fecha);
        valores.put("hora", hora);
        valores.put("motivo", motivo);
        valores.put("estado", estado);

        long resultado = db.insert(
                TABLE_CITAS,
                null,
                valores
        );

        db.close();

        return resultado != -1;
    }

    // ==========================
    // OBTENER CITAS
    // ==========================

    public Cursor obtenerCitas() {

        SQLiteDatabase db = this.getReadableDatabase();

        return db.rawQuery(
                "SELECT * FROM " + TABLE_CITAS +
                        " ORDER BY id DESC",
                null
        );
    }
}