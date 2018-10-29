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
    private int fkUsuario;

    public Horario(){}

    public Horario(EDiaSemana diaSemana, LocalTime horarioInicio, LocalTime horarioFim,
                   boolean livre, int fkUsuario) {
        this.setDiaSemana(diaSemana);
        this.setHorarioInicio(horarioInicio);
        this.setHorarioFim(horarioFim);
        this.setLivre(livre);
        this.setFkUsuario(fkUsuario);
    }

    public Horario(int id, EDiaSemana diaSemana, LocalTime horarioInicio, LocalTime horarioFim,
                   boolean livre, int fkUsuario) {
        this.setId(id);
        this.setDiaSemana(diaSemana);
        this.setHorarioInicio(horarioInicio);
        this.setHorarioFim(horarioFim);
        this.setLivre(livre);
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

    public void setDiaSemana(EDiaSemana diaSemana) {
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
}
