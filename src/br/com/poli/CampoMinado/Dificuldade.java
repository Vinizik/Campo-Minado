package br.com.poli.CampoMinado;

public enum Dificuldade { // Enum para a definição da dificuldade do jogo

    FACIL(9), MEDIO(16), DIFICIL(32);

    private int dificuldade;
    private int valor;

    Dificuldade(int valor) { // Construtor do Enum Dificuldade
                            // Se for FACIL, será atribuido o valor de 9 a "k"
                            // Se for MEDIO, será atribuido o valor de 16 a "k"
                            // Se for DIFICIL, será atribuido o valor de 32 a "k"
        dificuldade = valor;

    }

    public int getDificuldade() { // Get e Set do Enum Dificuldade
        return dificuldade;
    }

    public void setDificuldade(int dificuldade) {
        this.dificuldade = dificuldade;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

}