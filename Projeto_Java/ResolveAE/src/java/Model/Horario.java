/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Date;

/**
 *
 * @author 20161BSI0314
 */
public class Horario {
    enum EDiaSemana{
        DOMINGO,SEGUNDA,TERCA,QUARTA,QUINTA,SEXTA,SABADO
    }
    private EDiaSemana diaSemana;
    private Date horarioInicio;
    private Date horarioFim;
    private boolean livre;
    private int idHorario;
    private int idAgenda;

    public int getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(int idHorario) {
        this.idHorario = idHorario;
    }

    public int getIdAgenda() {
        return idAgenda;
    }

    public void setIdAgenda(int idAgenda) {
        this.idAgenda = idAgenda;
    }

    
    public EDiaSemana getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(EDiaSemana diaSemana) {
        this.diaSemana = diaSemana;
    }
    public Date getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(Date horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public Date getHorarioFim() {
        return horarioFim;
    }

    public void setHorarioFim(Date horarioFim) {
        this.horarioFim = horarioFim;
    }

    public boolean isLivre() {
        return livre;
    }

    public void setLivre(boolean livre) {
        this.livre = livre;
    }
}
