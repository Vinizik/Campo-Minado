package br.com.poli.CampoMinado.mapa;

import java.util.List;
import java.util.ArrayList;

public class Celula {// Classe que define a célula (a unidade e tipo de nossa matriz) e suas características

    private boolean bandeira;
    private boolean bomba;
    private int qtdBombasVizinhas;
    private boolean visivel;
    private int linha;
    private int coluna;
    private List<Celula> vizinhos = new ArrayList<Celula>();

    public Celula(int linha, int coluna){// Construtor de classe Celula

        this.linha = linha;
        this.coluna = coluna;
        
     }
    
    private void buscarVizinhos(Celula[][] campo) {
        for (int i = linha - 1; i < linha + 1; i++) {
            for (int j = coluna - 1; j < coluna + 1; j++) {
                if (i >= 0 && i < campo.length && j >= 0 && j < campo.length) {
                    vizinhos.add(campo[i][j]);
                }
            }
        }

    }

      public boolean isEmBranco() {
		if (qtdBombasVizinhas == 0) {

			return true;
		} else {
			return false;
		}
	}

    public boolean getBandeira() { // Getters e Setters da classe Celula
        return bandeira;
    }
    public void setBandeira(boolean bandeira) {
        this.bandeira = bandeira;
    }
    public boolean getBomba() {
        return bomba;
    }
    public void setBomba(boolean bomba) {
        this.bomba = bomba;
    }
    public int getQtdBombasVizinhas() {
        return qtdBombasVizinhas;
    }
    public void setQtdBombasVizinhas(int qtdBombasVizinhas) {
        this.qtdBombasVizinhas = qtdBombasVizinhas;
    }
    public boolean getVisivel() {
        return visivel;
    }
    public void setVisivel(boolean visivel) {
        this.visivel = visivel;
    }

	public int getLinha() {
		return linha;
	}

	public void setLinha(int linha) {
		this.linha = linha;
	}

	public int getColuna() {
		return coluna;
	}

	public void setColuna(int coluna) {
		this.coluna = coluna;
	}

}