package caraoucoroa.cursoandroid.com.caraoucoroa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView botaojogar;
    private String[] opcao = {"cara","coroa"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botaojogar = (ImageView) findViewById(R.id.botaoJogarId);

        botaojogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Ao clicar no botao , deve gerar numeroa aleatorio
                Random randomico = new Random();
                int numeroAleatorio = randomico.nextInt(2);

                // 0 cara - 1 coroa
                //opcao[numeroAleatorio]

                Intent intent = new Intent(MainActivity.this,DetalheActivity.class);
                intent.putExtra("opcao",opcao[numeroAleatorio]);

                startActivity(intent);

            }
        });

    }
}
