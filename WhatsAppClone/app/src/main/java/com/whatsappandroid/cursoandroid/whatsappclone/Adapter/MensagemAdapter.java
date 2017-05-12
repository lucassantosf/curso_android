package com.whatsappandroid.cursoandroid.whatsappclone.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.whatsappandroid.cursoandroid.whatsappclone.R;
import com.whatsappandroid.cursoandroid.whatsappclone.helper.Preferencias;
import com.whatsappandroid.cursoandroid.whatsappclone.model.Contato;
import com.whatsappandroid.cursoandroid.whatsappclone.model.Mensagem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucas on 12/05/2017.
 */
public class MensagemAdapter extends ArrayAdapter<Mensagem> {

    private Context context;
    private ArrayList<Mensagem> mensagens;

    public MensagemAdapter(Context c, ArrayList<Mensagem> objects) {
        super(c, 0, objects);
        this.context = c;
        this.mensagens = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = null;

        //verifica se a lista está preenchida
        if(mensagens != null){

            // Recupera dados do usuário remetente
            Preferencias preferencias = new Preferencias(context);
            String idUsuarioRemetente = preferencias.getIdentificador();

            //Inicializa objeto para montagem do layout
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

            // Recupera mensagem
            Mensagem mensagem = mensagens.get(position);

            //Monta view apartir do xml
            if(idUsuarioRemetente.equals(mensagem.getIdUsuario())) {
                view = inflater.inflate(R.layout.item_mensagem_direita, parent, false);
            }else {
                view = inflater.inflate(R.layout.item_mensagem_esquerda, parent, false);
            }

            //recupera elemento para exibição
            TextView texto_mensagem = (TextView) view.findViewById(R.id.tv_mensagem);
            texto_mensagem.setText(mensagem.getMensagem());

        }
        return view;
    }
}
