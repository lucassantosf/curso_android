package signos.cursoandroid.com.signos;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private ListView listaSignos;
    private String[] signos = {
            "Áries","Touro","Gêmeos","Câncer","Leão","Virgem",
            "Libra","Escorpião","Sagitário","Capricórnio","Aquário",
            "Peixes"
    };

    private String[] perfis = {
            "Arianos são animados, independentes, individualistas e dinamicos",
            "Taurinos são positivos, pacientes, fiéis, determinados, noturnos e romanticos",
            "Geminianos são positivos, mutáveis, racionais e bastante voláteis",
            "Cancerinos são dinamicos, corajosos e aventureiros",
            "Leoninos são aventureiros e pacientes",
            "Virgens são fiéis e bastante voláteis",
            "Librianos são positivos, mutáveis e racionais",
            "Escorpião são pacientes e positivos",
            "Sagitárianos são noturnos, romanticos e positivos",
            "Capricórnianos são aventureiros e fiéis",
            "Aquário são positivos e romanticos",
            "Peixes são mutáveis, racionais e bastante voláteis"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaSignos = (ListView) findViewById(R.id.listViewId);
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                signos
        );

        listaSignos.setAdapter( adaptador );

        listaSignos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int codigoPosicao = position;
                Toast.makeText(getApplicationContext(), perfis[codigoPosicao] , Toast.LENGTH_LONG);
            }
        });

    }
}
