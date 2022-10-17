package com.cdp.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cdp.agenda.db.DbCliente;

public class NuevoActivity extends AppCompatActivity {

     TextView viewCedula, viewNombre, viewApellido, viewTelefono, viewfechaNacimiento;
     EditText txtNombre, txtCedula, txtApellido, txtTelefono, txtCorreoElectronico, txtEdad, txtfechaNacimiento;
     Button btnGuarda;
     String html = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);

        viewCedula = findViewById(R.id.viewCedula);
        txtCedula = findViewById(R.id.txtCedula);
        viewfechaNacimiento = findViewById(R.id.viewfechaNacimiento);
        txtfechaNacimiento = findViewById(R.id.txtfechaNacimiento);
        viewNombre = findViewById(R.id.viewNombre);
        viewApellido = findViewById(R.id.viewApellido);
        txtNombre = findViewById(R.id.txtNombre);
        txtApellido = findViewById(R.id.txtApellido);
        viewTelefono = findViewById(R.id.viewTelefono);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtEdad = findViewById(R.id.txtEdad);
        txtCorreoElectronico = findViewById(R.id.txtCorreoElectronico);

        btnGuarda = findViewById(R.id.btnGuarda);

        html = "<span style='color:red;'>*</span>";




        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!txtCedula.getText().toString().equals("") && !txtNombre.getText().toString().equals("") && !txtTelefono.getText().toString().equals("") && !txtApellido.getText().toString().equals("") != txtfechaNacimiento.getText().toString().equals("")) {
                    DbCliente dbclientes = new DbCliente(NuevoActivity.this);
                    long id = dbclientes.insertarcliente(txtNombre.getText().toString(), txtApellido.getText().toString(), txtCedula.getText().toString(), txtTelefono.getText().toString(), txtCorreoElectronico.getText().toString(), txtEdad.getText().toString(), txtfechaNacimiento.getText().toString());

                    if (id > 0) {
                        Toast.makeText(NuevoActivity.this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
                        limpiar();
                    } else {
                        Toast.makeText(NuevoActivity.this, "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(NuevoActivity.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                    viewCedula.setText(Html.fromHtml(viewCedula.getText().toString() + html));
                    viewNombre.setText(Html.fromHtml(viewNombre.getText().toString() + html));
                    viewTelefono.setText(Html.fromHtml(viewTelefono.getText().toString() + html));
                    viewApellido.setText(Html.fromHtml(viewApellido.getText().toString() + html));
                }
            }
        });
    }

    private void limpiar() {
        setResult(RESULT_OK);
        finish();
        txtCedula.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtTelefono.setText("");
        txtCorreoElectronico.setText("");
        txtfechaNacimiento.setText("");
        txtEdad.setText("");
    }
}