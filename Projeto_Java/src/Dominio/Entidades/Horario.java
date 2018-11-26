package Dominio.Entidades;

import Dominio.Enum.EDiaSemana;

import java.time.LocalTime;

/**
 * @author 20161BSI0314
 */
public class Horario {

    private int id;
    private EDiaSemana diaSemana;
    private LocalTime inicio;
    private LocalTime fim;
    private boolean livre;
    private int fkUsuario;
    private int fkDiaSemana;

    public Horario() { }

    public Horario(LocalTime horarioInicio, LocalTime horarioFim,
                   boolean livre, int fkUsuario, int fkDiaSemana) {
        this.setHorarioInicio(horarioInicio);
        this.setHorarioFim(horarioFim);
        this.setLivre(livre);
        this.setFkDiaSemana(fkDiaSemana);
        this.setFkUsuario(fkUsuario);
    }

    public Horario(int id, LocalTime horarioInicio, LocalTime horarioFim,
                   boolean livre, int fkUsuario, int fkDiaSemana) {
        this.setId(id);
        this.setHorarioInicio(horarioInicio);
        this.setHorarioFim(horarioFim);
        this.setLivre(livre);
        this.setFkDiaSemana(fkDiaSemana);
        this.setFkUsuario(fkUsuario);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EDiaSemana getDiaSemana() {
        return diaSemana;
    }

    private void setDiaSemana(EDiaSemana diaSemana) {
        this.diaSemana = diaSemana;
    }

    public LocalTime getHorarioInicio() {
        return inicio;
    }

    public void setHorarioInicio(LocalTime horarioInicio) {
        this.inicio = horarioInicio;
    }

    public LocalTime getHorarioFim() {
        return fim;
    }

    public void setHorarioFim(LocalTime horarioFim) {
        this.fim = horarioFim;
    }

    public boolean isLivre() {
        return livre;
    }

    public void setLivre(boolean livre) {
        this.livre = livre;
    }

    public int getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(int fkUsuario) {
        this.fkUsuario = fkUsuario;
    }

    public int getFkDiaSemana() {
        return fkDiaSemana;
    }

    public void setFkDiaSemana(int fkDiaSemana) {
        this.fkDiaSemana = fkDiaSemana;
        setDiaSemana(EDiaSemana.getById(fkDiaSemana));
    }

    @Override
    public String toString() {
        return String.format(
                "ID:\t\t\t\t%d;" +
                        "\nInício:\t\t\t%s;" +
                        "\nFim:\t\t\t%s;" +
                        "\nDisponível:\t\t%b;" +
                        "\nFK_usuario:\t\t%d;" +
                        "\nFK_dia_semana:\t%d",

                getId(), getHorarioInicio(), getHorarioFim(), isLivre(),
                getFkUsuario(), getFkDiaSemana());
    }
}
