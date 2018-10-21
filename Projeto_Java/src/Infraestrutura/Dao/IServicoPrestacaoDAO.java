/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Infraestrutura.Dao;

import Dominio.Entidades.Servico;
import java.util.List;

/**
 *
 * @author elmr
 */
public interface IServicoPrestacaoDAO {
    public List<Servico> getServicosByIdUser(int idUsuario);
    public void insertServico(Servico serv);
    public void updateServico(Servico serv);
}
