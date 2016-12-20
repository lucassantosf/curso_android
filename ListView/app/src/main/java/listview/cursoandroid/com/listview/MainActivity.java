package listview.cursoandroid.com.listview;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

    private ListView listaItens;
    private String[] itens = {
            "Hong Kong","Istambul","Detroit","Vegas","Los Angeles",
            "Adelaide","Manaus","Barranquilha","Medellin","Cairo",
            "Berlim","Milano","Gales","Santiago","Cardume","Ilheus"
            ,"Athenas","São Luis","Caracas"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaItens = (ListView) findViewById(R.id.listviewId);

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                itens
        );

        listaItens.setAdapter( adaptador );
    }
}
