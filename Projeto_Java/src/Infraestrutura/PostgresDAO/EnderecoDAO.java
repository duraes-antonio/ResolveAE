package Infraestrutura.PostgresDAO;

import Dominio.Entidades.Endereco;
import Dominio.Enum.EEstado;
import Dominio.Interfaces.IEnderecoRepositorio;
import Infraestrutura.Enum.ETab;
import Infraestrutura.UtilPostgres.Function;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class EnderecoDAO extends AGenericDAO<Endereco>
        implements IEnderecoRepositorio {

    public static final String ID = ETab.ENDERECO.get() + ".id";
    public static final String BAIRRO = ETab.ENDERECO.get() + ".bairro";
    public static final String CIDADE = ETab.ENDERECO.get() + ".cidade";
    public static final String ESTADO = ETab.ENDERECO.get() + ".estado";
    public static final String CEP = ETab.ENDERECO.get() + ".cep";
    public static final String FK_USUARIO = ETab.ENDERECO.get() + ".fk_usuario";

    private final List<String> COLUNAS = Arrays.asList(
            BAIRRO, CIDADE, ESTADO, CEP, FK_USUARIO);

    @Override
    public int adicionar(Endereco entidade) throws SQLException {
        return Function.insertEndereco(entidade, this.persistencia);
    }

    @Override
    public void atualizar(Endereco entidade) throws SQLException {
        Function.insertEndereco(entidade, this.persistencia);
    }

    @Override
    public List<Endereco> obterTodosPorBairro(String bairro) {
        return null;
    }

    @Override
    public List<Endereco> obterTodosPorCidade(String cidade) {
        return null;
    }

    @Override
    public List<Endereco> obterTodosPorEstado(EEstado estado) {
        return null;
    }

    @Override
    public List<Endereco> obterTodosPorCep(int cep) {
        return null;
    }

    @Override
    protected ETab obterNomeTabela() {
        return null;
    }

    /**
     * Retorna uma lista com o nome de cada coluna da tabela COM EXCEÇÃO do ID.
     *
     * @return Lista com os nomes das colunas.
     */
    @Override
    protected List<String> obterNomeColunas() {
        return null;
    }

    /**
     * Substitui os '?' do PS pelos valores dos atributos da entidade.
     *
     * @param ps     P. Statement com SQL já pronto com os '?' para serem substituídos.
     * @param objeto Objeto com os atributos para preencher o statement.
     * @return ps com os '?' substituídos, inclusíve o da cláusula WHERE;
     */
    @Override
    protected PreparedStatement preencherPS(PreparedStatement ps, Endereco objeto) throws SQLException {
        return null;
    }

    /**
     * Monta e retorna o objeto a partir de um resultSet.
     *
     * @param rs ResultSet retornado de uma consulta já executada.
     * @return Objeto montado a partir dos resultados da consulta.
     */
    @Override
    protected Endereco construir(ResultSet rs) throws SQLException {

        if (!rs.isBeforeFirst()) return null;

        rs.next();

        return new Endereco(
                rs.getInt(ID),
                null,
                rs.getString(BAIRRO),
                rs.getString(CIDADE),
                rs.getString(ESTADO),
                rs.getInt(CEP),
                rs.getInt(FK_USUARIO)
        );
    }
}
