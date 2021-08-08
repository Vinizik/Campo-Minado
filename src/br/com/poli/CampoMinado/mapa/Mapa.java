package br.com.poli.CampoMinado.mapa;

import br.com.poli.CampoMinado.*;

import java.util.Random;

    public abstract class Mapa {

        private int bombas;
        private Celula[][] campo;
        private Dificuldade dificuldade;
        private boolean fimDeJogo;
        private boolean ganhouJogo;
        private int celulasVisiveis;

        public Mapa(Dificuldade dificuldade, int bombas) {//Construtor de Mapa
            this.dificuldade = dificuldade;
            this.bombas = bombas;
            campo = new Celula[this.dificuldade.getDificuldade()][this.dificuldade.getDificuldade()];
            inicializarCelulas();
            distribuirBombas(bombas);
            contarBombas();
        }

        private void distribuirBombas(int bombas) { // M�todo para gerar aleatoriamente as bombas atrav�s do campo.

            Random posicaoAleatoria = new Random();
            int i = 0; // i representa as linhas
            int j = 0; // j representa as colunas
            do {
                do {
                    i = posicaoAleatoria.nextInt(this.campo.length);
                    j = posicaoAleatoria.nextInt(this.campo.length);
                } while (campo[i][j].getBomba() == true); //faz com que nao seja atribuida uma bomba em uma posi��o que j� a contem
                campo[i][j].setBomba(true);
                bombas--;
            } while (bombas > 0);

        }

        private void inicializarCelulas() { //M�todo para inicializar uma matriz (campo) de c�lulas

        for (int i = 0; i < campo.length; i++) {
            for (int j = 0; j < campo.length; j++) {

                campo[i][j] = new Celula(i, j);
                campo[i][j].setBandeira(false);
                campo[i][j].setBomba(false);
                campo[i][j].setQtdBombasVizinhas(0);
                campo[i][j].setVisivel(false);

            }
        }
    }
    public void imprimeTela(boolean teste) {//M�todo que imprime no console a matriz com as c�lulas
                char x = '?';
                for (int i = 0; i < campo.length; i++) {
                    for (int j = 0; j < campo.length; j++) {
                        if (teste == true) {
                            if (campo[i][j].getBomba() == true) {
                                System.out.print(" -1" + "|");
                            } 
                            else {
                                System.out.print(" " + campo[i][j].getQtdBombasVizinhas() + " |");
                            }
                        } 
                        else {
                            if(campo[i][j].getVisivel() == true) {
                                System.out.print(campo[i][j].getQtdBombasVizinhas() + "|");
                            }
                            else if (campo[i][j].getVisivel() == false) {
                            System.out.print(x + "|");
                            }
                        }
                    }
                    System.out.println();
                }
            }
public void escolherPosicao(int linha, int coluna) {//M�todo para escolher a posi��o na matriz na qual a c�lula ser� aberta

                if (campo[linha][coluna].getBomba() == true) {
                    this.fimDeJogo = true;
                    System.out.println("Fim de Jogo. Voc� perdeu");
                } else if (campo[linha][coluna].getQtdBombasVizinhas() != 0) {
                    campo[linha][coluna].setVisivel(true);
                    this.celulasVisiveis++;
                } else if (campo[linha][coluna].getQtdBombasVizinhas() == 0) {//Aqui ocorrer� uma expans�o de c�lulas vazias, ou seja, as sem bombas e sem n�meros
                    for (int i2 = -1; i2 <= 1; i2++) {//Vari�veis auxiliares para a an�lise de vizinhos da c�lula
                        for (int j2 = -1; j2 <= 1; j2++) {
                            if (linha + i2 >= 0 && linha + i2 < campo.length && coluna + j2 >= 0 && coluna + j2 < campo.length) {
                                if (campo[linha + i2][coluna + j2].getBomba() == false) {
                                    if (campo[linha + i2][coluna + j2].getQtdBombasVizinhas() == 0 && campo[linha + i2][coluna + j2].getVisivel() == false) {
                                        campo[linha + i2][coluna + j2].setVisivel(true);
                                        this.celulasVisiveis++;
                                        revelarEspa�os(getCelula(linha + i2, coluna + j2));//Esse m�todo encontra-se abaixo e servir� para a recursividade
                                    } 
                                    else {
}
                                } 
                                else if (campo[linha + i2][coluna + j2].getBomba() == false) {

                                }
                            }
                        }
                    }

                }
                imprimeTela(false);
                System.out.println();
                this.ganhouJogo = verificarGanhouJogo();

            }
public void revelarEspa�os(Celula celulaEscolhida) {//M�todo para a recursividade do m�todo escolherPosi��o funcionar
                for (int i2 = -1; i2 <= 1; i2++) {
                    for (int j2 = -1; j2 <= 1; j2++) {
                        if (celulaEscolhida.getLinha() + i2 >= 0 && celulaEscolhida.getLinha() + i2 < campo.length && celulaEscolhida.getColuna() + j2 >= 0 && celulaEscolhida.getColuna() + j2 < campo.length) {
                            if (campo[celulaEscolhida.getLinha() + i2][celulaEscolhida.getColuna() + j2].getBomba() == false) {
                                if (campo[celulaEscolhida.getLinha() + i2][celulaEscolhida.getColuna() + j2].getQtdBombasVizinhas() == 0 && campo[celulaEscolhida.getLinha() + i2][celulaEscolhida.getColuna() + j2].getVisivel() == false) {
                                    //A condi��o acima faz com que c�lulas que j� foram examinadas e tornadas viz�veis n�o sejam novamente examinadas, evitando o erro de stackoverflow
                                    campo[celulaEscolhida.getLinha() + i2][celulaEscolhida.getColuna() + j2].setVisivel(true);
                                    this.celulasVisiveis++;
                                    //escolherPosicao(celulaEscolhida.getLinha(), celulaEscolhida.getColuna());
                                    revelarEspa�os(getCelula(celulaEscolhida.getLinha() + i2, celulaEscolhida.getColuna() + j2));
                                } 
                                else if (campo[celulaEscolhida.getLinha() + i2][celulaEscolhida.getColuna() + j2].getQtdBombasVizinhas() != 0 && campo[celulaEscolhida.getLinha() + i2][celulaEscolhida.getColuna() + j2].getVisivel() == false) {
                                    campo[celulaEscolhida.getLinha() + i2][celulaEscolhida.getColuna() + j2].setVisivel(true);
                                    this.celulasVisiveis++;
}
                                else {

                                }
                            } 
                            else if (campo[celulaEscolhida.getLinha() + i2][celulaEscolhida.getColuna() + j2].getBomba() == false) {

                            }
                        }
                    }
                }
            }
public void contarBombas() {//M�todo que conta as bombas ao redor de uma c�lula

            for (int i = 0; i < campo.length; i++) {
                for (int j = 0; j < campo.length; j++) {
                    if (campo[i][j].getBomba() == false) {
                        for (int z = (i - 1); z <= (i + 1); z++) {//Para evitar erro em c�lulas que n�o tem oito vizinhas, tais como as das quinas e as das bordas
                            for (int k = (j - 1); k <= (j + 1); k++) {
                                if (z >= 0 && z < campo.length && k >= 0 && k < campo.length) {
                                    if (campo[z][k].getBomba() == true) {
                                        campo[i][j].setQtdBombasVizinhas(campo[i][j].getQtdBombasVizinhas() + 1);
                                    }
                                }
                            }
                        }
                    }

                }

            }
        }
public boolean verificarGanhouJogo() {//Verifica se o jogo foi ganho
               if((celulasVisiveis) >= ((campo.length * campo.length) - bombas)) {
                   System.out.println("Voc� ganhou o jogo");
                   return true;
               }
               else {
                   return false;
               }
           }

               public Celula getCelula(int linha, int coluna) {//Pegar uma �nica celula

                   return campo[linha][coluna];
               }



           public int getBombas() {//Getters e Setters dos m�todos dessa classe
                return bombas;
            }

            public void setBombas(int bombas) {
                this.bombas = bombas;
            }

            public Celula[][] getCampo() {
                return campo;
            }
            public boolean isFimDeJogo() {
    			return fimDeJogo;
    		}

    		

    		public boolean isGanhouJogo() {
    			return ganhouJogo;
    		}
    		public Dificuldade getDificuldade() {
    			return dificuldade;
    		}
    		
    		public void setDificuldade(Dificuldade dificuldade) {
    			this.dificuldade = dificuldade;
    		}

    		
    } 