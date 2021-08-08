package br.com.poli.CampoMinado.mapa;

import br.com.poli.CampoMinado.Dificuldade;

public class MapaMedio extends Mapa { //Mapa na dificuldade Medio
	
	public static final Dificuldade tamanho = Dificuldade.MEDIO;
	public static final int bombas = 40;

	public MapaMedio() { //Construtor de Mapa Medio
		
		super(tamanho, bombas);
		
	}

}
