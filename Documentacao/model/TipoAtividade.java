package model;

public class TipoAtividade {
    private String tipo;
    private int dificPadrao;
    private int priorPadrao;

    public TipoAtividade(){

    }

    public int getPriorPadrao() {
        return priorPadrao;
    }

    public int getDificPadrao() {
        return dificPadrao;
    }

    public void setDificPadrao(int dificPadrao) {
        this.dificPadrao = dificPadrao;
    }

    public void setPriorPadrao(int priorPadrao) {
        this.priorPadrao = priorPadrao;
    }
}
