package autenticacaousuario.cursoandroid.com.autenticacaousuario;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();

        // Realiza o LogOf do usuario firebaseAuth.signOut();

        //Verifica tem usuario logado
        if (firebaseAuth.getCurrentUser() != null){
            Log.i("verificaUsuario","Usuario esta logado ! " );
        }else{
            Log.i("verificaUsuario","Usuario não esta logado ! " );
        }

        /*
        //Codigo realiza o login da autenticacao
        firebaseAuth.signInWithEmailAndPassword("lucas@gmail.com","lucas123")
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful() ){
                            Log.i("signin","Sucesso ao logar");
                        }else {
                            Log.i("signin","Erro ao logar");
                        }
                    }
                });
        */
        /*
        //Codigo realiza o cadastro da Autenticação
        firebaseAuth.createUserWithEmailAndPassword("lucas@gmail.com","lucas123")
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful() ){
                            Log.i("createServer","Sucesso ao cadastrar autenticação");
                        }else {
                            Log.i("createServer","Erro ao cadastrar autenticação");
                        }
                    }
                });*/


    }
}
