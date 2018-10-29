package Infraestrutura.Dao;

import Dominio.Entidades.Avaliacao;
import Dominio.Interfaces.IAvaliacaoRepositorio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class AvaliacaoDAO extends AGenericDAO<Avaliacao> implements IAvaliacaoRepositorio<Avaliacao> {

    //Nome das cols. da tabela 'Avaliacao', facilita correções e centraliza os nomes;

    private final String NOME_TABELA = "avaliacao";
    private final String ID = "id";
    private final String NOTA = "nota";

    @Override
    public List<Avaliacao> obterTodasPorUsuario(int usuarioId) throws SQLException {
        return null;
    }

    @Override
    public List<Avaliacao> obterTodasPorServico(int servicoId) throws SQLException {
        return null;
    }

    /**
     * Retorna o nome da Tabela responsável pela classe.
     *
     * @return Nome da tabela.
     */
    @Override
    protected String obterNomeTabela() {
        return this.NOME_TABELA;
    }

    /**
     * Retorna uma lista com o nome de cada coluna da tabela.
     *
     * @return Lista com os nomes das colunas.
     */
    @Override
    protected List<String> obterNomeColunas() {
        return Arrays.asList(this.ID, this.NOTA);
    }

    /**
     * Substitui os '?' do PS de inserção pelos valores dos atributos da entidade.
     *
     * @param ps P. Statement com SQL já pronto com os '?' para serem substituídos.
     * @return ps com os '?' substituídos, inclusíve o da cláusula WHERE;
     */
    @Override
    protected PreparedStatement preencherPSInsert(PreparedStatement ps) {
        ps.setInt(1, );
        return null;
    }

    /**
     * Substitui os '?' do PS de atualização pelos valores dos atributos da entidade.
     *
     * @param ps P. Statement com SQL já pronto com os '?' para serem substituídos.
     * @return ps com os '?' substituídos, inclusíve o da cláusula WHERE;
     */
    @Override
    protected PreparedStatement preencherPSUpdate(PreparedStatement ps) {
        return null;
    }

    /**
     * Monta e retorna o objeto a partir de um resultSet.
     *
     * @param rs ResultSet retornado de uma consulta já executada.
     * @return Objeto montado a partir dos resultados da consulta.
     */
    @Override
    protected Avaliacao construir(ResultSet rs) throws SQLException {
        return null;
    }
}