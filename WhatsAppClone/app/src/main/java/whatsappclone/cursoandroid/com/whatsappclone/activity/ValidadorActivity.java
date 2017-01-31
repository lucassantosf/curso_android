package whatsappclone.cursoandroid.com.whatsappclone.activity;

import android.preference.Preference;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.rtoshiro.util.format.MaskFormatter;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import java.util.HashMap;

import whatsapp.cursoandroid.com.whatsapp.R;
import whatsappclone.cursoandroid.com.whatsappclone.helper.Preferencias;


public class ValidadorActivity extends AppCompatActivity {

    private EditText codigoValidacao;
    private Button validar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validador);

        codigoValidacao = (EditText) findViewById(R.id.edit_cod_validacao);
        validar         = (Button) findViewById(R.id.bt_validar);

        SimpleMaskFormatter simpleMaskCodigoValidacao = new SimpleMaskFormatter("NNNN");
        MaskTextWatcher mascaraCodigoValidacao = new MaskTextWatcher(codigoValidacao, simpleMaskCodigoValidacao);

        codigoValidacao.addTextChangedListener( mascaraCodigoValidacao );

        validar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Recuperar dados da preferencia do usuario
                Preferencias preferencias = new Preferencias( ValidadorActivity.this );
                HashMap<String, String> usuario = preferencias.getDadosUsuario();

                String tokengerado = usuario.get("token");
                String tokendigitado = codigoValidacao.getText().toString();

                if( tokendigitado.equals(tokengerado) ){
                    Toast.makeText(getApplicationContext(),"Token Validado", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(),"Token Invalidado", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
