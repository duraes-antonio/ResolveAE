/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Infraestrutura.Dao;
import Dominio.Entidades.MidiaSocial;

import java.util.List;
/**
 *
 * @author elmr
 */
public interface IContatoDAO {
    public List<Telefone> getTelefones(int idUsuario);
    public List<MidiaSocial> getMidias(int idUsuario);
    public void InsertTelefone (Telefone tel);
    public void InsertMidias(MidiaSocial mSocial);
    public void updateTelefone(Telefone tel);
    public void updateMidias(MidiaSocial mSocial);
}
