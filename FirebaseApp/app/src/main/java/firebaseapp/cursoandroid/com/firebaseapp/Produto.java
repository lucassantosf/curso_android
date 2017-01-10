package firebaseapp.cursoandroid.com.firebaseapp;

/**
 * Created by lucas on 09/01/2017.
 */

public class Produto {

    private String descricao;
    private String cor;
    private String fabricante;

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public Produto() {
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
