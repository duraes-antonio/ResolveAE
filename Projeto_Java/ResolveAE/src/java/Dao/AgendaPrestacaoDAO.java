/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.AgendaPrestacao;
import Model.Horario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elmr
 */
public class AgendaPrestacaoDAO implements IAgendaPrestacaoDAO{

    @Override
    public List<Horario> getHorarios(AgendaPrestacao agenda) {
        String query = "SELECT * FROM Horario_Prestacao "
                + "WHERER fk_agenda_prestacao = "+ agenda.getIdAgenda()+" ";
        //EXECUCAO DA QUERY
        //FECHAR CONEXAO
        List<Horario> horarios = new ArrayList<>();
        //LEITURA DO DATASET
        return horarios;
    }

    @Override
    public void insertAgenda(int idUsuario) {
        String query = "INSERT INTO Agenda_Prestacao (fk_usuario) "
                + "VALUES "
                + "("+idUsuario+")";
        //EXECUCAO DA QUERY
        //FECHAR CONEXAO
        List<Horario> horarios = new ArrayList<>();
    }

    @Override
    public void insertHorario(AgendaPrestacao agenda, Horario horario) {
        int disponivel = 0;
        if(horario.isLivre()){
            disponivel =1;
        }
        String query = "INSERT INTO Horario_Prestacao (horario_prestacao_inicio, horario_prestacao_fim,disponivel,fk_agenda_prestacao,fk_dia_semana) "
                + "VALUES "
                + "("+horario.getHorarioInicio() +", "+horario.getHorarioFim()+", "+disponivel+", "+agenda.getIdAgenda()+", "+horario.getDiaSemana()+")";
        //EXECUCAO DA QUERY
        //FECHAR CONEXAO
    }

    @Override
    public void deleteHorario(Horario horario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateHorario(Horario horario) {
        int disponivel = 0;
        if(horario.isLivre()){
            disponivel =1;
        }
        String query = "UPDATE Horario_Prestacao SET horario_prestacao_inicio = "+horario.getHorarioInicio()+", horario_prestacao_fim = "+horario.getHorarioFim()+", disponivel = "+disponivel+" "
                + "fk_dia_semana = "+horario.getDiaSemana()+" ";
    }
    
}
