package app1.cursoandroid.com.app2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView textoNovaFrase;
    private Button botaoNovaFrase;

    private String[] frases = {
            "Apressa-te a viver bem e pensa que cada dia é, por si só, uma vida.",
            "As casas são construídas para que se viva nelas, não para serem olhadas.",
            "É difícil viver com as pessoas porque calar é muito difícil.",
            "Viver sem filosofar é o que se chama ter os olhos fechados sem nunca os haver tentado abrir.",
            "Temos de nos tornar na mudança que queremos ver.",
            "Não é o mais forte que sobrevive, nem o mais inteligente, mas o que melhor se adapta às mudanças.",
            "Toda reforma interior e toda mudança para melhor dependem exclusivamente da aplicação do nosso próprio esforço.",
            "Não espere por uma crise para descobrir o que é importante em sua vida.",
            "Não te interesses sobre a quantidade, mas sim sobre a qualidade dos vossos amigos.",
            "Não basta termos um bom espírito, o mais importante é aplicá-lo bem.",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textoNovaFrase = (TextView) findViewById(R.id.textoNovaFraseId);
        botaoNovaFrase = (Button) findViewById(R.id.botaoNovaFraseId);

        botaoNovaFrase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Random randomico = new Random();
                int numeroAleatorio = randomico.nextInt(frases.length);

                textoNovaFrase.setText( frases[numeroAleatorio] );

            }
        });

    }
}
