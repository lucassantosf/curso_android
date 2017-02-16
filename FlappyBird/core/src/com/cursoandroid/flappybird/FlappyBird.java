package com.cursoandroid.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

public class FlappyBird extends ApplicationAdapter {

    private SpriteBatch batch;
	private Texture[] passaros;
    private Texture fundo;
    private Texture canoBaixo;
    private Texture canoTopo;
    private Random numeroRandomico;

    //Atributos de Configurações

    private int larguraDispositivo;
    private int alturaDispositivo;
    private float variacao = 0;
    private float velocidadeQueda = 0;
    private float posicaoInicialVertical;
    private float posicaoMovimentoCanoHorizontal;
    private float espacoEntreCanos;
    private float deltaTime;
    private float alturaEntreCanosRandom;
    @Override
	public void create () {

        batch = new SpriteBatch();
        passaros = new Texture[3];
        passaros[0] = new Texture("passaro1.png");
        passaros[1] = new Texture("passaro2.png");
        passaros[2] = new Texture("passaro3.png");
        canoBaixo = new Texture("cano_baixo.png");
        canoTopo = new Texture("cano_topo.png");
        numeroRandomico = new Random();

        fundo = new Texture("fundo.png");
        larguraDispositivo = Gdx.graphics.getWidth();
        alturaDispositivo = Gdx.graphics.getHeight();
        posicaoInicialVertical = alturaDispositivo/2;

        posicaoMovimentoCanoHorizontal = larguraDispositivo;
        espacoEntreCanos = 150 ;
	}

	@Override
	public void render () {

        deltaTime = Gdx.graphics.getDeltaTime();

        variacao+= deltaTime*10 ;
        posicaoMovimentoCanoHorizontal -= deltaTime*200;
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


        // Método de toque na tela - passaro subir
        if (Gdx.input.justTouched()){
            velocidadeQueda = -20;
        }

        // teste para terminar a caida do passaro
        if(posicaoInicialVertical > 0 || velocidadeQueda < 0){
            posicaoInicialVertical -= velocidadeQueda;
        }

        // Quando o cano sair da tela, ira voltar a aparecer novamente
        if(posicaoMovimentoCanoHorizontal < -canoTopo.getWidth() ){
            posicaoMovimentoCanoHorizontal = larguraDispositivo + canoTopo.getWidth();
            alturaEntreCanosRandom = numeroRandomico.nextInt(400) - 200;

        }

        batch.begin();
        batch.draw(fundo, 0 , 0, larguraDispositivo, alturaDispositivo );
        batch.draw(canoTopo, posicaoMovimentoCanoHorizontal - 100 , alturaDispositivo/2 + espacoEntreCanos + alturaEntreCanosRandom);
        batch.draw(canoBaixo, posicaoMovimentoCanoHorizontal - 100, alturaDispositivo/2 - canoBaixo.getHeight() - espacoEntreCanos + alturaEntreCanosRandom);
        batch.draw(passaros[(int)variacao], 120, posicaoInicialVertical);
        batch.end();

	}
	
	@Override
	public void dispose () {

	}
}
