package com.example.PR_Tarea3MartinRoblesJesus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbarMain = (Toolbar) findViewById(R.id.toolbarMain);
        setSupportActionBar(toolbarMain);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Supermercados FrescoMercado");
        ImageView logoConsume = findViewById(R.id.imgLogo);
        logoConsume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(context, R.anim.logo_click));
                NavigateList();
            }
        });

    }
    public void NavigateList() {
        Intent intent = new Intent(this, ListaActivity.class);
        startActivity(intent);
    }
}