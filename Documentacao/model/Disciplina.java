package model;

public class Disciplina {
    private String nome;
    private int cargaHor;
    private int dificuldade;
    private int semestre;
    private Disciplina preReq;

    public Disciplina(){

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCargaHor() {
        return cargaHor;
    }

    public void setCargaHor(int cargaHor) {
        this.cargaHor = cargaHor;
    }

    public int getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(int dificuldade) {
        this.dificuldade = dificuldade;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public Disciplina getPreReq() {
        return preReq;
    }

    public void setPreReq(Disciplina preReq) {
        this.preReq = preReq;
    }
}
