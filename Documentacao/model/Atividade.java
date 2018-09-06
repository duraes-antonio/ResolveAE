package model;

public class Atividade {
    private TipoAtividade tipoAtividade;
    private int prioridade;
    private int dificudlade;

    public Atividade(){

    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }

    public int getDificudlade() {
        return dificudlade;
    }

    public void setDificudlade(int dificudlade) {
        this.dificudlade = dificudlade;
    }

    public TipoAtividade getTipoAtividade() {
        return tipoAtividade;
    }

    public void setTipoAtividade(TipoAtividade tipoAtividade) {
        this.tipoAtividade = tipoAtividade;
    }
}
