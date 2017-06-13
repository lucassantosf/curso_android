package com.parse.starter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.parse.ParseUser;
import com.parse.starter.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucas on 13/06/2017.
 */
public class UsuariosAdapter extends ArrayAdapter<ParseUser>{

    private Context context;
    private ArrayList<ParseUser> usuarios;

    public UsuariosAdapter(Context c,ArrayList<ParseUser> objects) {
        super(c, 0, objects);
        this.context = c;
        this.usuarios = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        /*
        * Verificar se não existe o objeto view criado,
        * pois a view utilizada é armazenada no cache do android e fica na variável converView
        * */

        if( view == null){

            //Inicializa objeto para montagem do layout
            LayoutInflater inflater = (LayoutInflater) context.getSystemService( context.LAYOUT_INFLATER_SERVICE );

            //montar a view apartir do XML
            view = inflater.inflate(R.layout.lista_usuarios, parent, false);

        }

        //Recuperar elementos para exibição
        TextView username = (TextView) view.findViewById(R.id.text_username);

        //Configurar o textview para exibir os usuários
        ParseUser parseUser = usuarios.get(position);
        username.setText(parseUser.getUsername());

        return view;
    }


}
