package whatsappclone.cursoandroid.com.whatsappclone.activity;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DatabaseReference;

import whatsapp.cursoandroid.com.whatsapp.R;
import whatsappclone.cursoandroid.com.whatsappclone.config.ConfiguracaoFirebase;


public class LoginActivity extends AppCompatActivity {

    private DatabaseReference referenciaFirebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        referenciaFirebase = ConfiguracaoFirebase.getFirebase();
        referenciaFirebase.child("pontos").setValue("800");
    }

    public void abrirCadastroUsuario(View view){
        Intent intent = new Intent(LoginActivity.this, CadastroUsuarioActivity.class);
        startActivity( intent );
    }
}
