package com.whatsappandroid.cursoandroid.whatsappclone.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.whatsappandroid.cursoandroid.whatsappclone.Adapter.MensagemAdapter;
import com.whatsappandroid.cursoandroid.whatsappclone.R;
import com.whatsappandroid.cursoandroid.whatsappclone.config.ConfiguracaoFirebase;
import com.whatsappandroid.cursoandroid.whatsappclone.helper.Base64Custom;
import com.whatsappandroid.cursoandroid.whatsappclone.helper.Preferencias;
import com.whatsappandroid.cursoandroid.whatsappclone.model.Conversa;
import com.whatsappandroid.cursoandroid.whatsappclone.model.Mensagem;

import java.util.ArrayList;

public class ConversaActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText editMensagem;
    private ImageButton btMensagem;
    private DatabaseReference firebase;
    private ListView listView;
    private ArrayList<Mensagem> mensagens;
    private ArrayAdapter<Mensagem> adapter;
    private ValueEventListener valueEventListenerMensagem;


    // dados do destinatario
    private String nomeUsuarioDestinatario;
    private String idUsuarioDestinatario;

    //dados do remetente
    private String idUsuarioRemetente;
    private String nomeUsuarioRemetente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversa);

        toolbar = (Toolbar) findViewById(R.id.tb_conversa);
        editMensagem = (EditText) findViewById(R.id.edit_mensagem);
        btMensagem = (ImageButton) findViewById(R.id.bt_enviar);
        listView = (ListView) findViewById(R.id.lv_conversas);


        // recuperar dados do usuario logado
        Preferencias preferencias = new Preferencias(ConversaActivity.this);
        idUsuarioRemetente = preferencias.getIdentificador();
        nomeUsuarioRemetente = preferencias.getNome();

        Bundle extra = getIntent().getExtras();

        if (extra != null) {
            nomeUsuarioDestinatario = extra.getString("nome");
            String emailDestinatario = extra.getString("email");
            idUsuarioDestinatario = Base64Custom.codificarBase64(emailDestinatario);
        }

        //Configurar toolbar
        toolbar.setTitle(nomeUsuarioDestinatario);
        toolbar.setNavigationIcon(R.drawable.ic_action_arrow_left);
        setSupportActionBar(toolbar);

        // Montar a listview e adapter
        mensagens = new ArrayList<>();

        adapter = new MensagemAdapter(ConversaActivity.this, mensagens);

        //Recuperar mensagens do firebase
        listView.setAdapter(adapter);
        //Recuparar mensagens do Firebase
        firebase = ConfiguracaoFirebase.getFirebase()
                .child("mensagens")
                .child(idUsuarioRemetente)
                .child(idUsuarioDestinatario);

        //Criar listener para mensagens
        valueEventListenerMensagem = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                // Limpar mensagens
                mensagens.clear();

                // Recuperar mensagens
                for (DataSnapshot dados : dataSnapshot.getChildren()) {
                    Mensagem mensagem = dados.getValue(Mensagem.class);
                    mensagens.add(mensagem);
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        firebase.addValueEventListener(valueEventListenerMensagem);

        //Eviar mensagem
        btMensagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String textoMensagem = editMensagem.getText().toString();

                if (textoMensagem.isEmpty()) {
                    Toast.makeText(ConversaActivity.this, "Digite uma mensagem para enviar ", Toast.LENGTH_LONG).show();
                } else {

                    Mensagem mensagem = new Mensagem();
                    mensagem.setIdUsuario(idUsuarioRemetente);
                    mensagem.setMensagem(textoMensagem);

                    // salvamos  mensgem para o remetente
                    Boolean retornoMensagemRemetente = salvarMensagem(idUsuarioRemetente, idUsuarioDestinatario, mensagem);

                    if (!retornoMensagemRemetente) {
                        Toast.makeText(ConversaActivity.this, "Problema ao salvar mensagem . Tente novamente", Toast.LENGTH_LONG).show();
                    } else {
                        // só ira salvar a mensagem para o destinatario caso não dê nada errado para o remetente
                        // salvamos mensagem para o destinatario
                        Boolean retornoMensagemDestinatario = salvarMensagem(idUsuarioDestinatario, idUsuarioRemetente, mensagem);

                        if (!retornoMensagemDestinatario) {
                            Toast.makeText(ConversaActivity.this, "Problema ao enviar mensagem ao destinatário, tente novamente !", Toast.LENGTH_LONG).show();
                        }
                    }

                    // salvamos Conversa para o remetente

                    Conversa conversa = new Conversa();
                    conversa.setIdUsuario(idUsuarioDestinatario);
                    conversa.setNome(nomeUsuarioDestinatario);
                    conversa.setMensagem(textoMensagem);
                    salvarConversa(idUsuarioRemetente, idUsuarioDestinatario, conversa);
                    Boolean retornoConversaRemetente = salvarConversa(idUsuarioRemetente, idUsuarioDestinatario, conversa);
                    if (!retornoConversaRemetente) {
                        Toast.makeText(ConversaActivity.this, "Problema ao salvar conversa . Tente novamente", Toast.LENGTH_LONG).show();
                    } else {

                        // salvamos Conversa para o Destinatário
                        Conversa conversaDestinatario = new Conversa();
                        conversaDestinatario.setIdUsuario(idUsuarioRemetente);
                        conversaDestinatario.setNome(nomeUsuarioRemetente);
                        conversaDestinatario.setMensagem(textoMensagem);

                        Boolean retornoConversaDestinario = salvarConversa(idUsuarioDestinatario, idUsuarioRemetente, conversaDestinatario);

                        if (!retornoConversaDestinario) {
                            Toast.makeText(ConversaActivity.this, "Problema ao salvar conversa para o destinatário . Tente novamente", Toast.LENGTH_LONG).show();
                            salvarConversa(idUsuarioDestinatario, idUsuarioRemetente, conversaDestinatario);
                        }

                    }

                    editMensagem.setText("");
                }

            };

        });
    }

    private boolean salvarMensagem(String idRemetente, String idDestinatario, Mensagem mensagem){
        try{
            firebase = ConfiguracaoFirebase.getFirebase().child("mensagens");

            firebase.child(idRemetente)
                    .child(idDestinatario)
                    .push()
                    .setValue(mensagem);
            // push - utilizado para gerar identificadores únicos(nunca se repetem)
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    private boolean salvarConversa(String idRemetente, String idDestinatario , Conversa conversa){

        try{
            firebase = ConfiguracaoFirebase.getFirebase().child("conversas");
            firebase.child( idRemetente)
                    .child( idDestinatario )
                    .setValue( conversa );

            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        firebase.removeEventListener(valueEventListenerMensagem);
    }
}
