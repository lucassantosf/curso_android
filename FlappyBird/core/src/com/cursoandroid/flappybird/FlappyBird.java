package com.cursoandroid.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FlappyBird extends ApplicationAdapter {

    private SpriteBatch batch;
	private Texture[] passaros;
    private Texture fundo;

    //Atributos de Configurações
    private int larguraDispositivo;
    private int alturaDispositivo;
    private float variacao = 0;
    private float velocidadeQueda = 0;
    private float posicaoInicialVertical;

    @Override
	public void create () {

        batch = new SpriteBatch();
        passaros = new Texture[3];
        passaros[0] = new Texture("passaro1.png");
        passaros[1] = new Texture("passaro2.png");
        passaros[2] = new Texture("passaro3.png");
        fundo = new Texture("fundo.png");
        larguraDispositivo = Gdx.graphics.getWidth();
        alturaDispositivo = Gdx.graphics.getHeight();
        posicaoInicialVertical = alturaDispositivo/2;

	}

	@Override
	public void render () {
        variacao+= Gdx.graphics.getDeltaTime()*10 ;
        velocidadeQueda++;
        // Verificacao para permitir a troca de imagens correta dentro do vetor
        int variacaoInt;
        if(variacao >= 0 && variacao <0.66) {
            variacaoInt = 0;
        } else if(variacao >= 0.66 && variacao < 1.33) {
            variacaoInt = 1;
        } else if(variacao >= 1.33 && variacao < 2) {
            variacaoInt = 2;
        } else if(variacao >= 2) {
            variacao = 0;
            //variacaoInt = 2;
        }
        //fim verificacao

        // teste para terminar a caida do passaro
        if(posicaoInicialVertical > 0){
            posicaoInicialVertical -= velocidadeQueda;
        }else {

        }

        batch.begin();
        batch.draw(fundo, 0 , 0, larguraDispositivo, alturaDispositivo );
        batch.draw(passaros[(int)variacao], 50, posicaoInicialVertical);
        batch.end();

	}
	
	@Override
	public void dispose () {

	}
}
