package com.whatsappandroid.cursoandroid.whatsappclone.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.github.rtoshiro.util.format.MaskFormatter;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.whatsappandroid.cursoandroid.whatsappclone.R;

public class LoginActivity extends AppCompatActivity {

    private EditText telefone;
    private EditText cod_pais;
    private EditText cod_area;
    private EditText nome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        telefone    = (EditText) findViewById(R.id.edit_telefone);
        cod_pais    = (EditText) findViewById(R.id.edit_cod_pais);
        cod_area    = (EditText) findViewById(R.id.edit_area);
        nome        = (EditText) findViewById(R.id.edit_nome);

        /* Definir mascaras */
        SimpleMaskFormatter simpleMaskTelefone = new SimpleMaskFormatter("NNNNN-NNNN");
        SimpleMaskFormatter simpleMaskCodArea = new SimpleMaskFormatter("NN");
        SimpleMaskFormatter simpleMaskCodPais = new SimpleMaskFormatter("+NN");


        MaskTextWatcher maskCodPais = new MaskTextWatcher(cod_pais, simpleMaskCodPais);
        MaskTextWatcher maskCodArea = new MaskTextWatcher(cod_area, simpleMaskCodArea);
        MaskTextWatcher maskTelefone = new MaskTextWatcher(telefone, simpleMaskTelefone);

        cod_pais.addTextChangedListener( maskCodPais );
        cod_area.addTextChangedListener( maskCodArea );
        telefone.addTextChangedListener( maskTelefone );

    }
}
