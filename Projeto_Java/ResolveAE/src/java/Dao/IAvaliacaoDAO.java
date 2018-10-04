/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Avaliacao;
import java.util.List;

/**
 *
 * @author 20161BSI0314
 */
public interface IAvaliacaoDAO {
    public List<Avaliacao> getAvaliacoesByUser(int idUsuario);
    public List<Avaliacao> getAvaliacaoesByServico(int idServicoPrestado);
    public void insertAvaliacao(Avaliacao avaliacao);
}
