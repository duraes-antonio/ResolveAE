/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Infraestrutura.Dao;

import Dominio.Entidades.Usuario;
import java.util.List;

/**
 *
 * @author 20161BSI0314
 */
public interface IUsuarioDAO {
    public List<Usuario> getAll();
    public List<Usuario> getAllByBairro(String bairro);
    public List<Usuario> getAllByCidade(String cidade);
    public List<Usuario> getAllByEstado(String estado);
    public Usuario getEmail(String email);
    public void updateUsuario(Usuario user);
    public void insertUsario(Usuario user);
    
}
