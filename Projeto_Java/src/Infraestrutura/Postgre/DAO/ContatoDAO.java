package Infraestrutura.Postgre.DAO;


import Dominio.Entidades.Contato;
import Dominio.Enum.ETipoContato;
import Dominio.Interfaces.IContatoRepositorio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ContatoDAO extends AGenericDAO<Contato>
        implements IContatoRepositorio
{

    @Override
    public List<Contato> obterTodosPorTipo(ETipoContato tipo) {
        return null;
    }

    @Override
    public List<Contato> obterTodosPorUsuario(int usuarioId) {
        return null;
    }

    @Override
    public List<Contato> obterTodosPorTipoEUsuario(ETipoContato tipo, int usuarioId) {
        return null;
    }

    /**
     * Substitui os '?' do PS pelos valores dos atributos da objeto.
     *
     * @param ps     P. Statement com SQL já pronto com os '?' para serem substituídos.
     * @param objeto Objeto com os atributos para preencher o statement.
     * @return ps com os '?' substituídos, pronto para execução.
     */
    @Override
    protected PreparedStatement preencherPS(PreparedStatement ps, Contato objeto) throws SQLException {
        return null;
    }

    /**
     * Monta e retorna o objeto a partir de um resultSet.
     * Não faz validações sobre o resultSet.
     *
     * @param rs ResultSet retornado de uma consulta já executada.
     * @return Objeto montado a partir dos resultados da consulta.
     */
    //TODO finalizar
    @Override
    protected Contato construir(ResultSet rs) throws SQLException {
        return null;
    }

    /**
     * Retorna uma string com query de INSERT, com '?' p/ ser substuído.
     *
     * @return String com comando SQL para adicionar um novo objeto.
     */
    @Override
    public String obterSqlAdicionar() {
        return ContatoSQL.ADICIONAR;
    }

    /**
     * Retorna uma string com query de UPDATE, com '?' p/ ser substuído.
     *
     * @return String com comando SQL para atualizar um objeto.
     */
    @Override
    public String obterSqlAtualizar() {
        return ContatoSQL.ATUALIZAR;
    }

    /**
     * Retorna uma string com query de DELETE, com '?' p/ ser substuído.
     *
     * @return String com comando SQL para deletar um objeto.
     */
    @Override
    public String obterSqlExcluir() {
        return ContatoSQL.EXCLUIR;
    }

    /**
     * Retorna uma string com query de SELECT, com '?' p/ ser substuído.
     *
     * @return String com comando SQL para buscar um objeto.
     */
    @Override
    public String obterSqlSelecionar() {
        return ContatoSQL.OBTER_POR_ID;
    }

    /**
     * Retorna uma string com query de SELECT *, com '?' p/ ser substuído.
     *
     * @return String com comando SQL para buscar múltiplos itens.
     */
    @Override
    public String obterSqlSelecionarTodos() {
        return ContatoSQL.OBTER_TODOS;
    }

    /**
     * Define o Id de um objeto.
     *
     * @param objeto Objeto a ter seu ID atualizado.
     * @param id     Id a ser inserido no objeto.
     */
    @Override
    public void definirId(Contato objeto, int id) {
        objeto.setId(id);
    }
}

