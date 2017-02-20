package com.cursoandroid.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

public class FlappyBird extends ApplicationAdapter {

    private SpriteBatch batch;
	private Texture[] passaros;
    private Texture fundo;
    private Texture canoBaixo;
    private Texture canoTopo;
    private Texture gameOver;
    private Random numeroRandomico;
    private BitmapFont fonte;
    private BitmapFont mensagem;
    private Circle passaroCirculo;
    private Rectangle retangulocanoTopo;
    private Rectangle retangulocanoBaixo;
    //private ShapeRenderer shape;

    //Atributos de Configurações

    private int larguraDispositivo;
    private int alturaDispositivo;
    private int estadodoJogo=0; // 0 jogo não iniciado, 1 - jogo iniciado , 2 - jogo GameOVer
    private int pontuacao = 0;

    private float variacao = 0;
    private float velocidadeQueda = 0;
    private float posicaoInicialVertical;
    private float posicaoMovimentoCanoHorizontal;
    private float espacoEntreCanos;
    private float deltaTime;
    private float alturaEntreCanosRandom;
    private Boolean marcouPonto = false;

    @Override
	public void create () {

        batch = new SpriteBatch();
        passaros = new Texture[3];
        fonte = new BitmapFont();
        fonte.setColor(Color.WHITE);
        fonte.getData().setScale(6);

        mensagem = new BitmapFont();
        mensagem.setColor(Color.WHITE);
        mensagem.getData().setScale(3);

        passaroCirculo = new Circle();
        /*retangulocanoBaixo = new Rectangle();
        retangulocanoTopo = new Rectangle();
        shape = new ShapeRenderer();*/

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
        gameOver = new Texture("game_over.png");

        posicaoMovimentoCanoHorizontal = larguraDispositivo;
        espacoEntreCanos = 150 ;
	}

	@Override
	public void render () {

        deltaTime = Gdx.graphics.getDeltaTime();
        variacao += deltaTime * 10;
        // Verificacao para permitir a troca de imagens correta dentro do vetor
        int variacaoInt;
        if (variacao >= 0 && variacao < 0.66) {
            variacaoInt = 0;
        } else if (variacao >= 0.66 && variacao < 1.33) {
            variacaoInt = 1;
        } else if (variacao >= 1.33 && variacao < 2) {
            variacaoInt = 2;
        } else if (variacao >= 2) {
            variacao = 0;
            //variacaoInt = 2;
        }
        //fim verificacao


        if( estadodoJogo == 0){//Nao iniciado

            if(Gdx.input.justTouched()){
                estadodoJogo = 1;
            }
        }
        else { // Já iniciado

            velocidadeQueda++;

            // teste para terminar a caida do passaro
            if (posicaoInicialVertical > 0 || velocidadeQueda < 0) {
                posicaoInicialVertical -= velocidadeQueda;
            }


            if(estadodoJogo == 1){

                posicaoMovimentoCanoHorizontal -= deltaTime * 200;

                // Método de toque na tela - passaro subir
                if (Gdx.input.justTouched()) {
                    velocidadeQueda = -20;
                }

                // Quando o cano sair da tela, ira voltar a aparecer novamente
                if (posicaoMovimentoCanoHorizontal < -canoTopo.getWidth()) {
                    posicaoMovimentoCanoHorizontal = larguraDispositivo + canoTopo.getWidth();
                    alturaEntreCanosRandom = numeroRandomico.nextInt(400) - 200;
                    marcouPonto = false;
                }

                //Verifica pontuacao
                if(posicaoMovimentoCanoHorizontal < 120 ){
                    if( !marcouPonto ){
                        pontuacao++;
                        marcouPonto = true;
                    }
                }

            }else{ // Teste da tela de GameOver

                if(Gdx.input.justTouched()){

                    estadodoJogo = 0;
                    pontuacao = 0;
                    velocidadeQueda = 0;
                    posicaoInicialVertical = alturaDispositivo/2;
                    posicaoMovimentoCanoHorizontal = larguraDispositivo + canoBaixo.getWidth();

                }

            }

        }
        batch.begin();
        batch.draw(fundo, 0 , 0, larguraDispositivo, alturaDispositivo );
        batch.draw(canoTopo, posicaoMovimentoCanoHorizontal - 100 , alturaDispositivo/2 + espacoEntreCanos + alturaEntreCanosRandom);
        batch.draw(canoBaixo, posicaoMovimentoCanoHorizontal - 100, alturaDispositivo/2 - canoBaixo.getHeight() - espacoEntreCanos + alturaEntreCanosRandom);
        batch.draw(passaros[(int)variacao], 120, posicaoInicialVertical);
        fonte.draw(batch, String.valueOf(pontuacao), larguraDispositivo/2, alturaDispositivo - 50);

        if(estadodoJogo == 2){
            batch.draw(gameOver, larguraDispositivo / 2 - gameOver.getWidth()/2, alturaDispositivo / 2);
            mensagem.draw(batch, "Toque para reiniciar",larguraDispositivo / 2 - 200, alturaDispositivo / 2 - gameOver.getHeight()/2);
        }

        batch.end();

        passaroCirculo.set(120 + passaros[0].getWidth() / 2 , posicaoInicialVertical + passaros[0].getHeight(), passaros[0].getWidth() / 2);

        retangulocanoBaixo = new Rectangle(
                posicaoMovimentoCanoHorizontal - canoBaixo.getWidth(), alturaDispositivo/2 - canoBaixo.getHeight() - espacoEntreCanos + alturaEntreCanosRandom,
                canoBaixo.getWidth(), canoBaixo.getHeight()
        );

        retangulocanoTopo = new Rectangle(
                posicaoMovimentoCanoHorizontal - canoTopo.getWidth(), alturaDispositivo/2 + espacoEntreCanos + alturaEntreCanosRandom,
                canoTopo.getWidth(), canoTopo.getHeight()
        );

        // Desenhar formas para colisao

        /*shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.circle(passaroCirculo.x, passaroCirculo.y, passaroCirculo.radius);
        shape.rect(retangulocanoBaixo.x, retangulocanoBaixo.y, retangulocanoBaixo.width, retangulocanoBaixo.height );
        shape.rect(retangulocanoTopo.x, retangulocanoTopo.y, retangulocanoTopo.width, retangulocanoTopo.height);
        shape.setColor(Color.RED);
        shape.end();
        */


        // Testes para Colisão
        if(Intersector.overlaps(passaroCirculo, retangulocanoBaixo) || Intersector.overlaps(passaroCirculo, retangulocanoTopo)
                || posicaoInicialVertical <= 0 || posicaoInicialVertical >= alturaDispositivo){
            estadodoJogo = 2 ;

        }
	}
	
	@Override
	public void dispose () {

	}
}
