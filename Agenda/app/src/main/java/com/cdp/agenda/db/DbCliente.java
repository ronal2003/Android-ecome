package com.cdp.agenda.db;

//julianestebanramirezcordoba@gmail.com

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.cdp.agenda.entidades.CLiente;

import java.util.ArrayList;

public class DbCliente extends DbHelper {

    Context context;

    public DbCliente(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarcliente(String nombre, String apellido, String fechaNacimiento, String cedula, String telefono, String correo_electronico, String edad) {

        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("cli_nombre", nombre);
            values.put("cli_apelli", apellido);
            values.put("cli_cedula", cedula);
            values.put("cli_telefo", telefono);
            values.put("cli_email", correo_electronico);
            values.put("cli_fechaNacimiento", fechaNacimiento);
            values.put("cli_edad", edad);

            id = db.insert(TABLE_CLIENTE, null, values);

        } catch (Exception ex) {
            ex.toString();
        }

        return id;
    }

    public ArrayList<CLiente> mostrarclientes() {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<CLiente> listaclientes = new ArrayList<>();
        CLiente cliente;
        Cursor cursorCliente;

        cursorCliente = db.rawQuery("SELECT * FROM " + TABLE_CLIENTE + " ORDER BY cli_nombre ASC", null);

        while (cursorCliente.moveToNext()) {

            cliente = new CLiente();
            cliente.setId(cursorCliente.getInt(0));
            cliente.setNombre(cursorCliente.getString(1));
            cliente.setApellido(cursorCliente.getString(2));
            cliente.setCedula(cursorCliente.getString(3));
            cliente.setTelefono(cursorCliente.getString(4));
            cliente.setEdad(cursorCliente.getInt(5));
            cliente.setFechaNacimiento(cursorCliente.getString(6));
            cliente.setCorreo_electornico(cursorCliente.getString(7));
            listaclientes.add(cliente);
        }

        cursorCliente.close();
        db.close();
        return listaclientes;
    }

    public CLiente verclientes(int id) {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        CLiente cliente = null;
        Cursor cursorCliente;

        cursorCliente = db.rawQuery(" SELECT * FROM " + TABLE_CLIENTE + " WHERE cli_id = " + id , null);

        if (cursorCliente.moveToFirst()) {

            cliente = new CLiente();
            cliente.setId(cursorCliente.getInt(0));
            cliente.setNombre(cursorCliente.getString(1));
            cliente.setApellido(cursorCliente.getString(2));
            cliente.setCedula(cursorCliente.getString(3));
            cliente.setTelefono(cursorCliente.getString(4));
            cliente.setEdad(cursorCliente.getInt(5));
            cliente.setFechaNacimiento(cursorCliente.getString(6));
            cliente.setCorreo_electornico(cursorCliente.getString(7));
        }

        cursorCliente.close();
        return cliente;
    }


    public boolean editarContacto(int id, String nombre, String apellido, String fechaNacimiento, String cedula, String telefono, String correo_electronico, String edad) {

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL(" UPDATE " + TABLE_CLIENTE + " SET cli_id = '" + id + "', cli_nombre = '" + nombre + "', cli_apelli = '" + apellido + "', cli_fechaNacimiento = '" + fechaNacimiento + "', cli_cedula = '" + cedula + "', cli_telefo = '" + telefono + "', cli_email = '" + correo_electronico + "', cli_edad = '" + edad + "' WHERE cli_id = '" + id + "'");
            correcto = true;

        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }

    public boolean Eliminar(int id) {
        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL(" DELETE FROM " + TABLE_CLIENTE + " WHERE  cli_id = '" + id + "'");
            correcto = true;
        } catch (Exception e) {
            e.toString();
            correcto = false;

        } finally {
            db.close();
        }
        return correcto;
    }
}