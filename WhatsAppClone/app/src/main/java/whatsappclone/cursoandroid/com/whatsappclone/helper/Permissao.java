package whatsappclone.cursoandroid.com.whatsappclone.helper;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucas on 30/01/2017.
 */

public class Permissao {

    public static boolean validaPermissoes(int requestCode, Activity activity, String[] permissoes){

        if(Build.VERSION.SDK_INT >= 23){

            List<String> listaPermissoes = new ArrayList<String>();

            /* Percorrer todas as permissões passadas, para verificar se a mesma tem sido liberada */
            for (String permissao : permissoes){
                Boolean validaPermissao = ContextCompat.checkSelfPermission(activity, permissao) == PackageManager.PERMISSION_GRANTED;
                if( !validaPermissao){
                    listaPermissoes.add(permissao);
                }
            }

            /* Verificar caso a lista esteja vazia, pois não seria necessário solicitar permissão */
            if( listaPermissoes.isEmpty() )
                return true;

            String[] novaspermissões = new String[listaPermissoes.size()];
            listaPermissoes.toArray(novaspermissões);

            // Solicitar Permissão
            ActivityCompat.requestPermissions(activity, novaspermissões, requestCode);
        }

        return true;
    }
}
