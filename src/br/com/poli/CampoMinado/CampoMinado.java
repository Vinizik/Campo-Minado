
package br.com.poli.CampoMinado;


import br.com.poli.CampoMinado.mapa.*;
import java.util.Scanner;



public class CampoMinado {

    private Jogador jogador;
    private Mapa mapa;
    private Dificuldade dificuldade;

    public CampoMinado(String nome, Dificuldade dificuldade) { // Construtor da classe CampoMinado
        this.jogador = new Jogador(nome);
        this.dificuldade = dificuldade;
        if (dificuldade == Dificuldade.FACIL) {
            this.mapa = new MapaFacil();
        }
        else if (dificuldade == Dificuldade.MEDIO) {
            this.mapa = new MapaMedio();
        }
        else {
            this.mapa = new MapaDificil();
        }
    }

    
    public void iniciarJogo() {//Nesse método o jogo acontece

        mapa.imprimeTela(false);
        while (mapa.isGanhouJogo() == false && mapa.isFimDeJogo() == false) {
            Scanner scan = new Scanner(System.in);//Introdução do scanner
            System.out.print("Informe qual será a linha:");
            int linha = scan.nextInt();
            System.out.print("Informe qual será a coluna:");
            int coluna = scan.nextInt();
            mapa.escolherPosicao(linha, coluna);//Atribuiu o valor dado pelo usuário à linha e coluna
            //System.out.println("celulas visiveis: " + this.mapa.getCelulasVisiveis());
        }
        mapa.imprimeTela(true);
    }

   
     
    public Jogador getJogador() { // Getters e Setters dos atributos da classe Campo Minado
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public Mapa getMapa() {
        return mapa;
    }

    public void setMapa(Mapa mapa) {
        this.mapa = mapa;
    }

    public Dificuldade getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(Dificuldade dificuldade) {
        this.dificuldade = dificuldade;
    }

}
