package com.whatsappandroid.cursoandroid.whatsappclone.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.github.rtoshiro.util.format.MaskFormatter;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.whatsappandroid.cursoandroid.whatsappclone.R;

import java.util.Random;

public class LoginActivity extends AppCompatActivity {

    private EditText telefone;
    private EditText cod_pais;
    private EditText cod_area;
    private EditText nome;
    private Button cadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        telefone    = (EditText) findViewById(R.id.edit_telefone);
        cod_pais    = (EditText) findViewById(R.id.edit_cod_pais);
        cod_area    = (EditText) findViewById(R.id.edit_area);
        nome        = (EditText) findViewById(R.id.edit_nome);
        cadastrar   = (Button) findViewById(R.id.bt_cadastrar);

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

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nomeUsuario = nome.getText().toString();
                String telefoneCompleto =
                        cod_pais.getText().toString() +
                        cod_area.getText().toString() +
                        telefone.getText().toString();
                String telefoneSemFormatacao = telefoneCompleto.replace("+","");
                telefoneSemFormatacao = telefoneSemFormatacao.replace("-","");

                //GERAR token
                Random randomico = new Random();
                int numeroRandomico = randomico.nextInt(9999 - 1000 ) + 1000;
                String token = String.valueOf(numeroRandomico);
                Log.i("TOKEN", "T:"+token);

            }
        });
    }
}
