package sqllite.cursoandroid.com.sqlite;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            SQLiteDatabase bancoDados = openOrCreateDatabase("app", MODE_PRIVATE, null);

            //Comando CREATE TABLE - criar tabela
            bancoDados.execSQL("CREATE TABLE IF NOT EXISTS pessoas(id INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR, idade INT(3) )");

            //Comando DROP TABLE - apagar tabela
            //bancoDados.execSQL("DROP TABLE pessoas");


            //Comando INSERT INTO - Inserir dados na tabela
            bancoDados.execSQL("INSERT INTO pessoas (nome,idade) VALUES('Lucas ',20)");
            bancoDados.execSQL("INSERT INTO pessoas (nome,idade) VALUES('Ana ',22)");
            bancoDados.execSQL("INSERT INTO pessoas (nome,idade) VALUES('Reginaldo ',53)");
            bancoDados.execSQL("INSERT INTO pessoas (nome,idade) VALUES('Beatriz ',29)");

            // Comando UPDATE - atualizar dados
            //bancoDados.execSQL("UPDATE pessoas SET nome = 'Ana' WHERE nome = 'Ana ' ");

            // Comando DELETE - apagar dados
            //bancoDados.execSQL("DELETE FROM pessoas WHERE nome = 'Reginaldo '");

            Cursor cursor = bancoDados.rawQuery("SELECT * FROM pessoas", null);

            int indiceColunaId = cursor.getColumnIndex("id");
            int indiceColunaNome = cursor.getColumnIndex("nome");
            int indiceColunaIdade = cursor.getColumnIndex("idade");

            cursor.moveToFirst();

            while (cursor != null) {

                Log.i("RESULTADO - ID: ", cursor.getString(indiceColunaId));
                Log.i("RESULTADO - nome: ", cursor.getString(indiceColunaNome));
                Log.i("RESULTADO - idade: ", cursor.getString(indiceColunaIdade));
                cursor.moveToNext();

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
