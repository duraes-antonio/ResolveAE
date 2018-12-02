package Infraestrutura.Postgre.DAO;

import Dominio.Interfaces.IBaseRepositorio;
import Infraestrutura.Postgre.Util.Persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class AGenericDAO<T> implements IBaseRepositorio<T> {

    private Persistencia persistencia = Persistencia.get();
    private Connection conexao = persistencia.getConexao();

    /*1 PreparedStatement para cada consulta: O ps é feito para ser reusado
     * diversas vezes, então, 1 para cada SQL garante reuso e desempenho*/
    private PreparedStatement psAdicionar = null;
    private PreparedStatement psAtualizar = null;
    private PreparedStatement psExcluir = null;
    private PreparedStatement psSelecionar = null;

    /**
     * Substitui os '?' do PS pelos valores dos atributos da objeto.
     * Substitui os valores para operações de INSERT e UPDATE apenas.
     *
     * @param ps     P. Statement com SQL já pronto com os '?' para serem substituídos.
     * @param objeto Objeto com os atributos para preencher o statement.
     * @return ps com os '?' substituídos, pronto para execução.
     */
    protected abstract PreparedStatement preencherPS(PreparedStatement ps, T objeto) throws SQLException;

    //Métodos que recebem o resultado de uma query e montam o objeto ou uma lista;

    /**
     * Monta e retorna o objeto a partir de um resultSet.
     * Não faz validações sobre o resultSet.
     *
     * @param rs ResultSet retornado de uma consulta já executada.
     * @return Objeto montado a partir dos resultados da consulta.
     */
    protected abstract T construir(ResultSet rs) throws SQLException;

    /**
     * Monta e retorna uma lista de objetos a partir de um resultSet.
     *
     * @param rs ResultSet retornado de uma consulta já executada.
     * @return Objeto montado a partir dos resultados da consulta.
     */
    public List<T> extrairTodos(ResultSet rs) throws SQLException {

        List<T> objetos = new ArrayList<T>();

        while (rs.next()) {
            objetos.add(construir(rs));
        }

        rs.close();

        return objetos;
    }

    /**
     * Monta e retorna o objeto a partir de um resultSet(Valida o RS).
     *
     * @param rs ResultSet retornado de uma consulta já executada.
     * @return Objeto montado a partir dos resultados da consulta.
     */
    private T obterObjeto(ResultSet rs) throws SQLException {
        T objeto = (rs != null && rs.next()) ? construir(rs) : null;

        if (rs != null && !rs.isClosed()) rs.close();

        return objeto;
    }


    //Métodos que montam as Strings das querys básicas de CRUD;

    /**
     * Retorna uma string com query de INSERT, com '?' p/ ser substuído.
     *
     * @return String com comando SQL para adicionar um novo objeto.
     */
    protected abstract String obterSqlAdicionar();

    /**
     * Retorna uma string com query de UPDATE, com '?' p/ ser substuído.
     *
     * @return String com comando SQL para atualizar um objeto.
     */
    protected abstract String obterSqlAtualizar();

    /**
     * Retorna uma string com query de DELETE, com '?' p/ ser substuído.
     *
     * @return String com comando SQL para deletar um objeto.
     */
    protected abstract String obterSqlExcluir();

    /**
     * Retorna uma string com query de SELECT, com '?' p/ ser substuído.
     *
     * @return String com comando SQL para buscar um objeto.
     */
    protected abstract String obterSqlSelecionar();

    /**
     * Define o Id de um objeto.
     *
     * @param objeto Objeto a ter seu ID atualizado.
     * @param id     Id a ser inserido no objeto.
     */
    protected abstract void definirId(T objeto, int id);

    //Métodos CRUD GENÉRICOS;
    /**
     * Persiste o objeto em um meio não volátil de armazenamento.
     *
     * @param objeto Objeto a ser persistido.
     * @return T objeto atualizado com Id.
     * @throws SQLException
     */
    @Override
    public T adicionar(T objeto) throws SQLException {

        int result = 0;

        try {
            psAdicionar = conexao.prepareStatement(obterSqlAdicionar());
            preencherPS(psAdicionar, objeto);
            result = persistencia.executarAtualizacao(psAdicionar);
        }

        catch (SQLException e) {
            e.printStackTrace();
        }

        finally {
            if (psAdicionar != null) psAdicionar.close();
        }

        this.definirId(objeto, result);

        return objeto;
    }

    /**
     * Atualiza os dados de um objeto já existente no meio de persistência.
     *
     * @param objeto Objeto a ser atualizado.
     * @throws SQLException
     */
    @Override
    public void atualizar(T objeto) throws SQLException {

        try {
            psAtualizar = conexao.prepareStatement(obterSqlAtualizar());
            preencherPS(psAtualizar, objeto);
            persistencia.executarAtualizacao(psAtualizar);
        }

        catch (SQLException e) {
            e.printStackTrace();
        }

        finally {
            if (psAtualizar != null) psAtualizar.close();
        }
    }

    /**
     * Remove um objeto do meio de persistência dado seu identificador.
     *
     * @param id Identificador do objeto a ser removido.
     * @throws SQLException
     */
    @Override
    public void excluirPorId(int id) throws SQLException {

        try {
            psExcluir = conexao.prepareStatement(obterSqlExcluir());
            psExcluir.setInt(1, id);
            persistencia.executarAtualizacao(psExcluir);
        }

        catch (SQLException e) {
            e.printStackTrace();
        }

        finally {
            if (psExcluir != null) psExcluir.close();
        }
    }

    /**
     * Busca e retorna o objeto que possuir o identificador recebido.
     *
     * @param id Identificador do objeto a ser buscado.
     * @return Objeto já construído e com atributos preenchidos, inclusive ID.
     * @throws SQLException
     */
    @Override
    public T obterPorId(int id) throws SQLException {

        T objeto = null;

        try {
            psSelecionar = conexao.prepareStatement(obterSqlSelecionar());
            psSelecionar.setInt(1, id);
            objeto = obterObjeto(persistencia.executarSelecao(psSelecionar));
        }

        catch (SQLException e) {
            e.printStackTrace();
        }

        finally {
            if (psSelecionar != null) psSelecionar.close();
        }

        return objeto;
    }
}
