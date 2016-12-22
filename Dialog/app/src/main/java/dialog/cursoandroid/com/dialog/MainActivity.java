package dialog.cursoandroid.com.dialog;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    private Button botao;
    private AlertDialog.Builder dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botao = (Button) findViewById(R.id.botaoId);

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Criar alert Dialog
                dialog = new AlertDialog.Builder(MainActivity.this);

                //configurar o titulo
                dialog.setTitle("Titulo da Dialog");

                //configurar a mensagem
                dialog.setMessage("Mensagem da Dialog");

                dialog.setCancelable(false);
                dialog.setIcon(android.R.drawable.ic_delete);

                //configurar Botao Negativo
                dialog.setNegativeButton("Nao", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"Pressionado NÃO",Toast.LENGTH_SHORT).show();
                    }
                });

                //configurar Botao Positivo
                dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"Pressionado SIM",Toast.LENGTH_SHORT).show();
                    }
                });

                dialog.create();
                dialog.show();

            }
        });

    }



}
