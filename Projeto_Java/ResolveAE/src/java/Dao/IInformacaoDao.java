/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.util.List;
import Model.Telefone;
import Model.Experiencia;
import Model.Endereco;
/**
 *
 * @author 20161BSI0314
 */
public interface IInformacaoDao {
    public Endereco getEndereco(int idInformacao);
    public List<Telefone> getContatos(int idInformacao);
    public List<Experiencia> getInfosProfissionais(int idInformacao);
}
