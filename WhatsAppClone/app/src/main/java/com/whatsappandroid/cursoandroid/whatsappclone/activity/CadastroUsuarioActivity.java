package com.whatsappandroid.cursoandroid.whatsappclone.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.whatsappandroid.cursoandroid.whatsappclone.R;
import com.whatsappandroid.cursoandroid.whatsappclone.config.ConfiguracaoFirebase;
import com.whatsappandroid.cursoandroid.whatsappclone.model.Usuario;

public class CadastroUsuarioActivity extends AppCompatActivity {

    private EditText nome;
    private EditText email;
    private EditText senha;
    private Button botaoCadastrar;
    private Usuario usuario;

    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        nome = (EditText) findViewById(R.id.edit_cadastro_nome);
        email = (EditText) findViewById(R.id.edit_cadastro_email);
        senha = (EditText) findViewById(R.id.edit_cadastro_senha);
        botaoCadastrar = (Button) findViewById(R.id.bt_cadastrar);


        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                usuario = new Usuario();
                usuario.setNome(nome.getText().toString());
                usuario.setEmail(email.getText().toString());
                usuario.setSenha(email.getText().toString());

                if(nome.getText().length() == 0 || email.getText().length() == 0 || senha.getText().length() == 0){
                    Toast.makeText(CadastroUsuarioActivity.this, "Preencha todos os campos!", Toast.LENGTH_LONG).show();
                }else{
                    cadastrarUsuario();
                }

            }
        });
    }

    private void cadastrarUsuario(){

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword(
            usuario.getEmail(), usuario.getSenha()
        ).addOnCompleteListener(CadastroUsuarioActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(CadastroUsuarioActivity.this, "Sucesso ao cadastrar usuário !", Toast.LENGTH_LONG).show();
                    FirebaseUser usuarioFirebase = task.getResult().getUser();
                    usuario.setId( usuarioFirebase.getUid() );
                    usuario.salvar();

                    autenticacao.signOut();
                    finish();

                }else{

                    String erroExcessao = "";
                    try{
                        throw task.getException();
                    } catch (FirebaseAuthWeakPasswordException e){
                        erroExcessao = "Digite um senha mais forte, contendo mais caracteres e com letras e números!";
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        erroExcessao = "O e-mail digitado é inválido, digite um novo email!";
                    } catch (FirebaseAuthUserCollisionException e) {
                        erroExcessao = "Esse e-mail já está em uso no App !";
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    Toast.makeText(CadastroUsuarioActivity.this, "Erro: " + erroExcessao, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
