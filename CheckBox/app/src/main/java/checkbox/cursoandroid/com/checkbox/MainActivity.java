package checkbox.cursoandroid.com.checkbox;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends Activity {

    private CheckBox cao;
    private CheckBox gato;
    private CheckBox papagaio;

    private Button botaoMostrar;
    private TextView textoExibicao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cao = (CheckBox) findViewById(R.id.checkBoxCaoId);
        gato = (CheckBox) findViewById(R.id.checkBoxGatoId);
        papagaio = (CheckBox) findViewById(R.id.checkBoxPapagaioId);

        textoExibicao = (TextView) findViewById(R.id.textoExibicaoId);
        botaoMostrar = (Button) findViewById(R.id.botaoMostrarId);

        botaoMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itensSelecionados = "";

                itensSelecionados += "Item : " + cao.getText() + " " + cao.isChecked() +"\n";
                itensSelecionados += "Item : " + gato.getText() +  " " +gato.isChecked() +"\n";
                itensSelecionados += "Item : " + papagaio.getText()+ " "+ papagaio.isChecked() + "\n";

                textoExibicao.setText(itensSelecionados);

            }
        });
    }
}
