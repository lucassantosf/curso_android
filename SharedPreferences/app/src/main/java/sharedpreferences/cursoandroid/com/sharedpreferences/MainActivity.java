package sharedpreferences.cursoandroid.com.sharedpreferences;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private EditText textoNome;
    private Button botaoSalvar;
    private TextView textoExibicao;

    private static final String ARQUIVO_PREFERENCIA = "ArquivoPreferencia";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textoNome = (EditText) findViewById(R.id.textonomeId);
        textoExibicao = (TextView) findViewById(R.id.textoExibicaoId);
        botaoSalvar = (Button) findViewById(R.id.botaoSalvarId);

        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                if( textoNome.getText().toString().equals("") ){
                    Toast.makeText(getApplicationContext()," Preencher o Campo Nome",Toast.LENGTH_SHORT).show();
                }else {
                    editor.putString("nome", textoNome.getText().toString() );
                    editor.commit();
                    textoExibicao.setText("Olá, "+textoNome.getText().toString());
                }
            }
        });

        // Recuperar dados salvos
        SharedPreferences sharedPreferences = getSharedPreferences(ARQUIVO_PREFERENCIA,0);
        if( sharedPreferences.contains("nome")){
            String nomeUsuario = sharedPreferences.getString("nome", "Usuário Não Definido");
            textoExibicao.setText("Olá, "+nomeUsuario);
        }else{
            textoExibicao.setText("Olá Usuario não definido");
        }

    }
}
