/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;
import Model.Cep;
import Model.Endereco;
/**
 *
 * @author elmr
 */
public interface IEnderecoDAO {
    public Cep getCep(int idUsuario);
    public String getBairro(int idUsuario);
    public String getCidade(int idUsuario);
    public String getEstado(int idUsuario);
    public void insertEndereco(int idUsuario,Endereco end);
    public void updateEndereco(Endereco end);
}
