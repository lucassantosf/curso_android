package com.parse.starter.util;

import java.util.HashMap;

/**
 * Created by lucas on 23/05/2017.
 */
public class ParseErros {

    private HashMap<Integer, String> erros;

    public ParseErros() {
        this.erros = new HashMap<>();
        this.erros.put(202, "Usuário já existe, escolha um outro nome de usuário !");
        this.erros.put(201, "A senha não foi preenchida !");
    }

    public String getErro(int codErro){
        return this.erros.get(codErro);
    }
}
