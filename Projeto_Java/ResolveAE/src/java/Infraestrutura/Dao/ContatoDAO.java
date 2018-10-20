/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Infraestrutura.Dao;

import Dominio.Entidades.MidiaSocial;
import Dominio.Entidades.Telefone;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elmr
 */
public class ContatoDAO implements IContatoDAO{

    @Override
    public List<Telefone> getTelefones(int idUsuario) {
        String query = "SELECT C.* FROM Usuario AS U"
                + "INNER JOIN Contato AS C ON C.fk_usuario = U.id "
                + "WHERE C.fk_tipo IN (1,2,3) AND U.id ="+idUsuario+"";
         //EXECUCAO DA QUERY
        //FECHAR CONEXAO
        List<Telefone> tels = new ArrayList<>();
        //LEITURA DO DATASET
        return tels;
    }

    @Override
    public List<MidiaSocial> getMidias(int idUsuario) {
        String query = "SELECT C.* FROM Usuario AS U"
                + "INNER JOIN Contato AS C ON C.fk_usuario = U.id "
                + "WHERE C.fk_tipo NOT (1,2,3) AND U.id ="+idUsuario+"";
         //EXECUCAO DA QUERY
        //FECHAR CONEXAO
        List<MidiaSocial> midias = new ArrayList<>();
        //LEITURA DO DATASET
        return midias;
    }

    @Override
    public void InsertTelefone(Telefone tel) {
        String query = "INSER INTO Contato (descricao,fk_usuario,fk_tipo) "
                + "VALUES "
                + "("+tel.getNumero()+","+tel.getIdUsario()+","+tel.getTipoContato()+")";
         //EXECUCAO DA QUERY
        //FECHAR CONEXAO
    }

    @Override
    public void InsertMidias(MidiaSocial mSocial) {
        String query = "INSER INTO Contato (descricao,fk_usuario,fk_tipo) "
                + "VALUES "
                + "("+mSocial.getLink()+","+mSocial.getIdUsario()+","+mSocial.getTipoMidia()+")";
         //EXECUCAO DA QUERY
        //FECHAR CONEXAO
    }

    @Override
    public void updateTelefone(Telefone tel) {
        String query = "UPDATE Contato SET descricao="+tel.getNumero()+", fk_tipo= "+tel.getTipoContato()+" "
                + "WHERE id = "+tel.getIdTel()+" AND fk_usuario ="+tel.getIdUsario()+" ";
         //EXECUCAO DA QUERY
        //FECHAR CONEXAO
    }

    @Override
    public void updateMidias(MidiaSocial mSocial) {
        String query = "UPDATE Contato SET descricao="+mSocial.getLink()+", fk_tipo= "+mSocial.getTipoMidia()+" "
                + "WHERE id = "+mSocial.getIdMidia()+" AND fk_usuario ="+mSocial.getIdUsario()+" ";
        //EXECUCAO DA QUERY
        //FECHAR CONEXAO
    }
    
}
