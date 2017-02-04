package whatsappclone.cursoandroid.com.whatsappclone.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import whatsapp.cursoandroid.com.whatsapp.R;
import whatsappclone.cursoandroid.com.whatsappclone.config.ConfiguracaoFirebase;


public class MainActivity extends AppCompatActivity {

    private Button botao_sair;
    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botao_sair = (Button) findViewById(R.id.bt_logout);

        botao_sair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // recuperar a referencia do Firebase
                autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
                autenticacao.signOut();
                // após sair, voltar para tela inicial
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });
    }
}
