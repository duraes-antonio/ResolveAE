/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Infraestrutura.Dao;

import Dominio.Entidades.Contrato;
import java.util.List;

/**
 *
 * @author elmr
 */
public interface IContratoDAO {
    public List<Contrato> getContratosByUser(int idUsuario);
    public void insertContrato(Contrato cnt);
    
}
