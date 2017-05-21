/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;
import com.parse.starter.R;

import java.util.List;


public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
/*
      // Cadastro de usuários

      ParseUser usuario = new ParseUser();
      usuario.setUsername("lucas");
      usuario.setPassword("123456");
      usuario.setEmail("lucas@gmail.com");

      // cadastrar
      usuario.signUpInBackground(new SignUpCallback() {
          @Override
          public void done(ParseException e) {
              if ( e == null){ //deu certo
                  Log.i("CadastroUsuario", "Sucesso ao cadastrar usuario " + e.getMessage());
              }else{ // erro
                  Log.i("CadastroUsuario", "Erro ao cadastrar usuario " + e.getMessage());
              }

          }
      });
        */

      //Verificar usuario logado
      //deslogar usuario
      //ParseUser.logOut();
        /*
      if( ParseUser.getCurrentUser() != null){
          Log.i("LoginUsuario", "Usuario logado ");
      }else{// Não esta logado
          Log.i("LoginUsuario", "Usuario não logado " );
      }*/

      // Fazer login do usuario
      ParseUser.logInInBackground("lucas", "123456", new LogInCallback() {
          @Override
          public void done(ParseUser user, ParseException e) {
              if ( e == null){ //deu certo
                  Log.i("verificarLoginUsuario", "Login feito com sucesso " );
              }else{ // erro
                  Log.i("verificarLoginUsuario", "Login não feito   " + e.getMessage());
              }
          }
      });


  }


}
