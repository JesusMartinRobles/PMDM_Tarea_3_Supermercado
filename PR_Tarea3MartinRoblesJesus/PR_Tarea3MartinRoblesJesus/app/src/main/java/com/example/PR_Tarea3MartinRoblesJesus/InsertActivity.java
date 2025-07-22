package com.example.PR_Tarea3MartinRoblesJesus;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.PR_Tarea3MartinRoblesJesus.models.BaseDatos;
import com.example.PR_Tarea3MartinRoblesJesus.models.Producto;

import java.util.Objects;

public class InsertActivity extends AppCompatActivity {
    private BaseDatos db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        db = new BaseDatos(getApplicationContext());

        Toolbar toolbarInsertar = (Toolbar) findViewById(R.id.toolbarInsertar);
        setSupportActionBar(toolbarInsertar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Añadir un producto");

        final EditText nombreInput = findViewById(R.id.inpName);
        final EditText precioInput = findViewById(R.id.inpPrice);
        final EditText Ingredient = findViewById(R.id.inpIngredient);
        final EditText weightInput = findViewById(R.id.inpWeight);
        final EditText url = findViewById(R.id.inpUrl);
        final CheckBox disponibleInput = findViewById(R.id.chkAvialble);
        Button bInsertar = findViewById(R.id.btnSave);

        // definimos la función del botón de guardar el usuario
        bInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (nombreInput.length() == 0 || precioInput.length() == 0) {
                    AlertDialog alertDialog = new AlertDialog.Builder(InsertActivity.this).create();
                    alertDialog.setMessage("Datos incompletos");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    alertDialog.show();

                } else {

                    db.insertProducto(new Producto(nombreInput.getText().toString(), Double.parseDouble(precioInput.getText().toString()),
                            disponibleInput.isChecked() ? 1 : 0,
                            Double.parseDouble(weightInput.getText().toString()),
                            Ingredient.getText().toString(), url.getText().toString()
                            ));
                    NavigateList();
                }

            }
        });
    }
        public void NavigateList() {
            Toast.makeText(getApplicationContext(), "Producto añadido correctamente", Toast.LENGTH_LONG).show();
            this.finish();

        }
}
