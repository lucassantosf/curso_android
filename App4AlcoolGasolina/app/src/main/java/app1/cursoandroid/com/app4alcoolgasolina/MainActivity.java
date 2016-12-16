package app1.cursoandroid.com.app4alcoolgasolina;

import android.renderscript.Double2;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText precoAlcool;
    private EditText precoGasolina;
    private Button botaoVerificar;
    private TextView textoResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Localizar componentes na tela
        precoAlcool = (EditText) findViewById(R.id.precoAlcoolId);
        precoGasolina = (EditText) findViewById(R.id.precoGasolinaId);
        botaoVerificar = (Button) findViewById(R.id.verificarBotaoId);
        textoResultado = (TextView) findViewById(R.id.textoResultadoId);

        botaoVerificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //recuperar valores digitados
                String textoPrecoAlccol = precoAlcool.getText().toString();
                String textoPrecoGasolina = precoGasolina.getText().toString();

                //Converter valores strings para numeros
                Double valorAlcool = Double.parseDouble(textoPrecoAlccol);
                Double valorGasolina = Double.parseDouble(textoPrecoGasolina);

                //alcool / gasolina
                double resultado = valorAlcool / valorGasolina;
                if(resultado >= 0.7){
                    //Gasolina
                    textoResultado.setText("Utilizar Gasolina");
                }else{
                    //Alccol
                    textoResultado.setText("Utilizar Alccol");
                }
            }
        });

    }
}
