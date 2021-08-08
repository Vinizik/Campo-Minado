package br.com.poli.CampoMinado.mapa;

import br.com.poli.CampoMinado.Dificuldade;

public class MapaDificil extends Mapa{ //Mapa na dificuldade Dificil
	
	public static final Dificuldade dificuldade = Dificuldade.DIFICIL;
	public static final int bombas = 99;

	public MapaDificil() { // Construtor de Mapa Dificil
		super(dificuldade, bombas);
		
	}

}
