/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.AgendaPrestacao;
import Model.Horario;
import java.util.List;

/**
 *
 * @author elmr
 */
public interface IAgendaPrestacaoDAO {
    public List<Horario> getHorarios(AgendaPrestacao agenda);
    public void insertAgenda(int idUsuario);
    public void insertHorario(AgendaPrestacao agenda,Horario horario);
    public void deleteHorario(Horario horario);
    public void updateHorario(Horario horario);
}
