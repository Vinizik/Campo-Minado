package br.com.poli.CampoMinado.mapa;

import br.com.poli.CampoMinado.Dificuldade;

public class MapaFacil extends Mapa { //Mapa na dificuldade Facil
	
	private static final Dificuldade tamanho = Dificuldade.FACIL;
	private static final int bombas = 10;

	public MapaFacil() { // Construtor de Mapa Facil
		super(tamanho, bombas);
		
	}

}
