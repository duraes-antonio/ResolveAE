package model;

public class Aluno {
    private String nome;
    private String matricula;
    private HorarioSemana horario;

    public Aluno(){

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public HorarioSemana getHorario() {
        return horario;
    }

    public void setHorario(HorarioSemana horario) {
        this.horario = horario;
    }
}
