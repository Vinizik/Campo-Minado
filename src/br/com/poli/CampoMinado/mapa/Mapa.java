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

        private void distribuirBombas(int bombas) { // Método para gerar aleatoriamente as bombas através do campo.

            Random posicaoAleatoria = new Random();
            int i = 0; // i representa as linhas
            int j = 0; // j representa as colunas
            do {
                do {
                    i = posicaoAleatoria.nextInt(this.campo.length);
                    j = posicaoAleatoria.nextInt(this.campo.length);
                } while (campo[i][j].getBomba() == true); //faz com que nao seja atribuida uma bomba em uma posição que já a contem
                campo[i][j].setBomba(true);
                bombas--;
            } while (bombas > 0);

        }

        private void inicializarCelulas() { //Método para inicializar uma matriz (campo) de células

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
    public void imprimeTela(boolean teste) {//Método que imprime no console a matriz com as células
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
public void escolherPosicao(int linha, int coluna) {//Método para escolher a posição na matriz na qual a célula será aberta

                if (campo[linha][coluna].getBomba() == true) {
                    this.fimDeJogo = true;
                    System.out.println("Fim de Jogo. Você perdeu");
                } else if (campo[linha][coluna].getQtdBombasVizinhas() != 0) {
                    campo[linha][coluna].setVisivel(true);
                    this.celulasVisiveis++;
                } else if (campo[linha][coluna].getQtdBombasVizinhas() == 0) {//Aqui ocorrerá uma expansão de células vazias, ou seja, as sem bombas e sem números
                    for (int i2 = -1; i2 <= 1; i2++) {//Variáveis auxiliares para a análise de vizinhos da célula
                        for (int j2 = -1; j2 <= 1; j2++) {
                            if (linha + i2 >= 0 && linha + i2 < campo.length && coluna + j2 >= 0 && coluna + j2 < campo.length) {
                                if (campo[linha + i2][coluna + j2].getBomba() == false) {
                                    if (campo[linha + i2][coluna + j2].getQtdBombasVizinhas() == 0 && campo[linha + i2][coluna + j2].getVisivel() == false) {
                                        campo[linha + i2][coluna + j2].setVisivel(true);
                                        this.celulasVisiveis++;
                                        revelarEspaços(getCelula(linha + i2, coluna + j2));//Esse método encontra-se abaixo e servirá para a recursividade
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
public void revelarEspaços(Celula celulaEscolhida) {//Método para a recursividade do método escolherPosição funcionar
                for (int i2 = -1; i2 <= 1; i2++) {
                    for (int j2 = -1; j2 <= 1; j2++) {
                        if (celulaEscolhida.getLinha() + i2 >= 0 && celulaEscolhida.getLinha() + i2 < campo.length && celulaEscolhida.getColuna() + j2 >= 0 && celulaEscolhida.getColuna() + j2 < campo.length) {
                            if (campo[celulaEscolhida.getLinha() + i2][celulaEscolhida.getColuna() + j2].getBomba() == false) {
                                if (campo[celulaEscolhida.getLinha() + i2][celulaEscolhida.getColuna() + j2].getQtdBombasVizinhas() == 0 && campo[celulaEscolhida.getLinha() + i2][celulaEscolhida.getColuna() + j2].getVisivel() == false) {
                                    //A condição acima faz com que células que já foram examinadas e tornadas vizíveis não sejam novamente examinadas, evitando o erro de stackoverflow
                                    campo[celulaEscolhida.getLinha() + i2][celulaEscolhida.getColuna() + j2].setVisivel(true);
                                    this.celulasVisiveis++;
                                    //escolherPosicao(celulaEscolhida.getLinha(), celulaEscolhida.getColuna());
                                    revelarEspaços(getCelula(celulaEscolhida.getLinha() + i2, celulaEscolhida.getColuna() + j2));
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
public void contarBombas() {//Método que conta as bombas ao redor de uma célula

            for (int i = 0; i < campo.length; i++) {
                for (int j = 0; j < campo.length; j++) {
                    if (campo[i][j].getBomba() == false) {
                        for (int z = (i - 1); z <= (i + 1); z++) {//Para evitar erro em células que não tem oito vizinhas, tais como as das quinas e as das bordas
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
                   System.out.println("Você ganhou o jogo");
                   return true;
               }
               else {
                   return false;
               }
           }

               public Celula getCelula(int linha, int coluna) {//Pegar uma única celula

                   return campo[linha][coluna];
               }



           public int getBombas() {//Getters e Setters dos métodos dessa classe
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