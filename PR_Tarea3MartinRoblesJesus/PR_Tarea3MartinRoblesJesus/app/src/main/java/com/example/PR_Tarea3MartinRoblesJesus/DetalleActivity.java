package com.example.PR_Tarea3MartinRoblesJesus;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.PR_Tarea3MartinRoblesJesus.models.BaseDatos;
import com.example.PR_Tarea3MartinRoblesJesus.models.Producto;

import java.util.ArrayList;
import java.util.Objects;

public class DetalleActivity extends AppCompatActivity {
    private BaseDatos db;
    private  Intent intent;
    private Producto producto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        db = new BaseDatos(getApplicationContext());
        intent = getIntent();
        producto = (Producto) intent.getSerializableExtra("product");
        Toolbar toolbarInsertar = (Toolbar) findViewById(R.id.toolbarInsertar);
        setSupportActionBar(toolbarInsertar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Detalle un producto");

        final EditText nombreInput = findViewById(R.id.inpName);
        final EditText precioInput = findViewById(R.id.inpPrice);
        final EditText Ingredient = findViewById(R.id.inpIngredient);
        final EditText InpUrl = findViewById(R.id.inpUrl);
        final EditText weightInput = findViewById(R.id.inpWeight);
        final ImageView urlIm = findViewById(R.id.imgUrl);
        final CheckBox disponibleInput = findViewById(R.id.chkAvialble);
        Button bInsertar = findViewById(R.id.btnSave);
        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });

        // definimos la función del botón de guardar el usuario
        bInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (nombreInput.length() == 0 || precioInput.length() == 0) {
                    AlertDialog alertDialog = new AlertDialog.Builder(DetalleActivity.this).create();
                    alertDialog.setMessage("Datos incompletos");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    alertDialog.show();

                } else {

                    db.actualizarProducto(new Producto(producto.getId(),nombreInput.getText().toString(), Double.parseDouble(precioInput.getText().toString()),
                            disponibleInput.isChecked() ? 1 : 0,
                            Double.parseDouble(weightInput.getText().toString()),
                            Ingredient.getText().toString(), InpUrl.getText().toString())
                            );
                    NavigateList();
                }

            }
        });

        nombreInput.setText(producto.getName());
        precioInput.setText(String.valueOf(producto.getPrice()));
        Ingredient.setText(producto.getIngredient());
        weightInput.setText(String.valueOf(producto.getWeight()));
        if (producto.getURL()!= ""&& producto.getURL()!=null) {
            // Cargar la imagen con Glide
            InpUrl.setText(producto.getURL());
            Glide.with(this)
                    .load(producto.getURL())
                    .apply(new RequestOptions().centerCrop())
                    .into(urlIm);
            urlIm.setOnClickListener((new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                showAlertDialog();
                }
                    })
                        );

        }else{
            urlIm.setImageResource(android.R.drawable.ic_menu_gallery);
        }
        disponibleInput.setChecked(producto.isAvilable()==1);
    }
        public void NavigateList() {
            Toast.makeText(getApplicationContext(), "Producto actualizado correctamente", Toast.LENGTH_LONG).show();
            this.finish();

        }
    public void back() {
        this.finish();
    }
    public void showAlertDialog(){
        AlertDialog.Builder alertadd = new AlertDialog.Builder(this);
        LayoutInflater factory = LayoutInflater.from(this);
        final View view = factory.inflate(R.layout.alert_img, null);
         ImageView urlImAlert = view.findViewById(R.id.imgAlert);

        Glide.with(this)
                .load(producto.getURL())
                .apply(new RequestOptions().centerCrop())
                .into(urlImAlert);

        alertadd.setView(view);
        alertadd.setNeutralButton("Cerrar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dlg, int sumthin) {

            }
        });

        alertadd.show();
    }

}
