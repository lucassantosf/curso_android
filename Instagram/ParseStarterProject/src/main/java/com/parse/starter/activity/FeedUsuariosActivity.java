package com.parse.starter.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.parse.starter.R;

public class FeedUsuariosActivity extends AppCompatActivity {


    private Toolbar toolbar;
    private ListView listView;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_usuarios);

        //Recuperar dados enviados pela Intent
        Intent intent = getIntent();
        userName = intent.getStringExtra("username");

        //configurar Toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar_feed_usuario);
        toolbar.setTitle( userName );
        toolbar.setTitleTextColor(R.color.preto);


    }

}
