package app1.cursoandroid.com.app3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText caixaTexto;
    private Button botaoIdade;
    private TextView resultadoIdade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        caixaTexto = (EditText) findViewById(R.id.caixaTextoId);
        botaoIdade = (Button) findViewById(R.id.botaoIdadeId);
        resultadoIdade = (TextView) findViewById(R.id.resultadoIdadeId);

        botaoIdade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Recuperar o que foi digitado
                String textoDigitado = caixaTexto.getText().toString();

                // Teste para ver se a caixa de texto esta vazia
                if( textoDigitado.isEmpty() ){
                    //String vazia
                    resultadoIdade.setText(" Nenhum número digitado.");
                }else{

                    int valorDigitado = Integer.parseInt(textoDigitado);
                    int resultadoFinal = valorDigitado * 7;

                    resultadoIdade.setText("A idade do cachorro em anos humanos é : " + resultadoFinal);

                }
            }
        });
    }
}
