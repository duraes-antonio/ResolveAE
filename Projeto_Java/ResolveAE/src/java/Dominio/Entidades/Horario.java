package Dominio.Entidades;

import Dominio.Enum.EDiaSemana;

import java.time.LocalTime;

/**
 *
 * @author 20161BSI0314
 */
public class Horario {

    private int id;
    private EDiaSemana diaSemana;
    private LocalTime inicio;
    private LocalTime fim;
    private boolean livre;

    public Horario(){}

    public Horario(EDiaSemana diaSemana, LocalTime horarioInicio, LocalTime horarioFim,
            boolean livre) {
        this.setDiaSemana(diaSemana);
        this.setHorarioInicio(horarioInicio);
        this.setHorarioFim(horarioFim);
        this.setLivre(livre);
    }

    public Horario(int id, EDiaSemana diaSemana, LocalTime horarioInicio, LocalTime horarioFim,
            boolean livre) {
        this.setId(id);
        this.setDiaSemana(diaSemana);
        this.setHorarioInicio(horarioInicio);
        this.setHorarioFim(horarioFim);
        this.setLivre(livre);
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {

        if (id < 1) Util.throwExceptNumeroInferior("id", 1);

        this.id = id;
    }

    public EDiaSemana getDiaSemana() {
        return this.diaSemana;
    }

    public void setDiaSemana(EDiaSemana diaSemana) {
        this.diaSemana = diaSemana;
    }

    public LocalTime getHorarioInicio() {
        return this.inicio;
    }

    public void setHorarioInicio(LocalTime horarioInicio) {
        this.inicio = horarioInicio;
    }

    public LocalTime getHorarioFim() {
        return this.fim;
    }

    public void setHorarioFim(LocalTime horarioFim) {
        this.fim = horarioFim;
    }

    public boolean isLivre() {
        return this.livre;
    }

    public void setLivre(boolean livre) {
        this.livre = livre;
    }
}
