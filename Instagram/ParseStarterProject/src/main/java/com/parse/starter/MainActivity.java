/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.List;


public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);


    /*
      ParseObject pontuacao = new ParseObject("Pontuacao");
      pontuacao.put("nome","Tiana");
      pontuacao.put("pontos", 888);
      //pontuacao.saveInBackground(); // comando simples para salvar

      pontuacao.saveInBackground(new SaveCallback() {
          @Override
          public void done(ParseException e) {
                if(e == null){// não temos erro, pois objeto esta nulo
                    Log.i("salvarPontos", "Dados salvos com sucesso");
                }else{
                    Log.i("salvarPontos", "Erro ao salvar ");
                }
          }
      });

*/
      /*
      ParseQuery<ParseObject> consulta = ParseQuery.getQuery("Pontuacao");
      consulta.getInBackground("qzhwW1ISgH", new GetCallback<ParseObject>() {
          @Override
          public void done(ParseObject object, ParseException e) {

              if(e == null){// não temos erro, pois objeto esta nulo

                  object.put("pontos", 666);
                  object.saveInBackground();

              }else{
                  Log.i("consultaObjeto", "Erro ao consultar objeto  ");
              }

          }
      });
        */

      ParseQuery<ParseObject> filtro = ParseQuery.getQuery("Pontuacao");

      //Aplciando filtros na listagem de dados
      filtro.whereGreaterThan("pontos", 800);
      //filtro.whereGreaterThanOrEqualTo("pontos", 800);
      //filtro.whereLessThan("pontos", 700);
      //filtro.whereEndsWith("nome", "fer");
      //filtro.whereStartsWith("nome", "lu");
      filtro.addAscendingOrder("pontos");
      filtro.setLimit(3);

      //listar os dados
      filtro.findInBackground(new FindCallback<ParseObject>() {
          @Override
          public void done(List<ParseObject> objects, ParseException e) {

              if(e == null){// efetuada a lsitagem sem erros

                  for( ParseObject object : objects){
                      Log.i("ListarDados", "Objetos - " + object.get("nome") + " pontos : " + object.get("pontos"));
                  }

              }else{//Erro ao listar
                  Log.i("ListarDados", "Erro ao consultar objeto - " + e.getMessage());
              }

          }
      });

  }


}
