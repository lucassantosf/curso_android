package whatsappclone.cursoandroid.com.whatsappclone.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.IdRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import java.util.HashMap;
import java.util.Random;

import whatsapp.cursoandroid.com.whatsapp.R;
import whatsappclone.cursoandroid.com.whatsappclone.helper.Permissao;
import whatsappclone.cursoandroid.com.whatsappclone.helper.Preferencias;


public class LoginActivity extends AppCompatActivity {

    private EditText nome;
    private EditText telefone;
    private EditText codPais;
    private EditText codArea;
    private Button cadastrar;
    private String[] permissoesNecessarias = new String[]{
            Manifest.permission.SEND_SMS,
            Manifest.permission.INTERNET
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Permissao.validaPermissoes(1, this, permissoesNecessarias);

        nome        = (EditText) findViewById(R.id.edit_Nome);
        telefone    = (EditText) findViewById(R.id.edit_telefone);
        codArea     = (EditText) findViewById(R.id.edit_cod_area);
        codPais     = (EditText) findViewById(R.id.edit_cod_pais);
        cadastrar   = (Button) findViewById(R.id.bt_cadastrar);

        /* Definir as mascaras */
        SimpleMaskFormatter simpleMaskCodPais = new SimpleMaskFormatter("+NN");
        SimpleMaskFormatter simpleMaskCodArea = new SimpleMaskFormatter("NN");
        SimpleMaskFormatter simpleMaskTelefone = new SimpleMaskFormatter("NNNNN-NNNN");


        MaskTextWatcher maskCodPais = new MaskTextWatcher(codPais, simpleMaskCodPais);
        MaskTextWatcher maskCodArea = new MaskTextWatcher(codArea, simpleMaskCodArea);
        MaskTextWatcher maskTelefone = new MaskTextWatcher(telefone, simpleMaskTelefone);

        telefone.addTextChangedListener(maskTelefone);
        codPais.addTextChangedListener(maskCodPais);
        codArea.addTextChangedListener(maskCodArea);

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nomeUsuario = nome.getText().toString();
                String telefoneCompleto = codPais.getText().toString() + codArea.getText().toString() + telefone.getText().toString();

                String telefoneSemFormatacao = telefoneCompleto.replace("+","");
                telefoneSemFormatacao = telefoneSemFormatacao.replace("-","");

                // Gerar TOKEN
                Random randomico = new Random();
                int numeroRandomico = randomico.nextInt(9999 - 1000) + 1000;
                String token = String.valueOf(numeroRandomico);
                String mensagemEnvio = "WhatsApp Código de Confirmação: "+ token;
                // Salvar dados para validação
                Preferencias preferencias = new Preferencias(LoginActivity.this);
                preferencias.salvarUsuarioPreferencias(nomeUsuario, telefoneSemFormatacao, token);

                /*
                HashMap<String, String> usuario = preferencias.getDadosUsuario();
                Log.i("TOKEN", "NOME: " + usuario.get("nome") + " FONE " + usuario.get("telefone"));
                */

                // Envio do sms
                telefoneSemFormatacao = "5554"; // esta linha é necessário para envio de SMS para emuladores
                boolean enviadoSMS = enviaSMS("+"+ telefoneSemFormatacao, mensagemEnvio);

                if( enviadoSMS ){

                    Intent intent = new Intent(LoginActivity.this, ValidadorActivity.class);
                    startActivity(intent);
                    finish();

                }else {
                    Toast.makeText(LoginActivity.this, "Problema ao enviar SMS, tente novamente !", Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    // Enviar SMS
    public boolean enviaSMS(String telefone, String mensagem) {

        try{
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(telefone, null, mensagem, null, null);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return  false;
        }

    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResult){

        super.onRequestPermissionsResult(requestCode, permissions, grantResult);
        for (int resultado: grantResult){

            if( resultado == PackageManager.PERMISSION_DENIED){
                alertaValidacaoPermissao();
            }

        }

    }

    private void alertaValidacaoPermissao(){

        AlertDialog.Builder builder = new AlertDialog.Builder( this );
        builder.setTitle("Permissão Negada");
        builder.setMessage("Para utilizar este app, é necessário aceitas as permissões");

        builder.setPositiveButton("CONFIRMAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
