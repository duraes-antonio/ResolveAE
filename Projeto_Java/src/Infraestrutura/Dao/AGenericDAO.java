package Infraestrutura.Dao;

import Dominio.Interfaces.IRepositorioBase;
import Infraestrutura.UtilPostgres.Persistencia;
import Infraestrutura.UtilPostgres.SQLProdutor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class AGenericDAO<T> implements IRepositorioBase<T> {

    private Persistencia persistencia = Persistencia.get();
    private Connection conexao = persistencia.getConexao();

    /**Retorna o nome da Tabela responsável pela classe.
     * @return Nome da tabela.
     */
    protected abstract String obterNomeTabela();

    /**Retorna uma lista com o nome de cada coluna da tabela.
     * @return Lista com os nomes das colunas.
     */
    protected abstract List<String> obterNomeColunas();

    /**Substitui os '?' do PS de inserção pelos valores dos atributos da entidade.
     * @param ps P. Statement com SQL já pronto com os '?' para serem substituídos.
     * @return ps com os '?' substituídos, inclusíve o da cláusula WHERE;
     */
    protected abstract PreparedStatement preencherPSInsert(PreparedStatement ps);

    /**Substitui os '?' do PS de atualização pelos valores dos atributos da entidade.
     * @param ps P. Statement com SQL já pronto com os '?' para serem substituídos.
     * @return ps com os '?' substituídos, inclusíve o da cláusula WHERE;
     */
    protected abstract PreparedStatement preencherPSUpdate(PreparedStatement ps);

    /**Monta e retorna o objeto a partir de um resultSet.
     * @param rs ResultSet retornado de uma consulta já executada.
     * @return Objeto montado a partir dos resultados da consulta.
     */
    protected abstract T construir(ResultSet rs) throws SQLException;

    /**Monta e retorna uma lista de objetos a partir de um resultSet.
     * @param rs ResultSet retornado de uma consulta já executada.
     * @return Objeto montado a partir dos resultados da consulta.
     */
    protected List<T> extrairTodos(ResultSet rs) throws SQLException {

        List<T> objetos = new ArrayList<T>();

        while (rs.next()) objetos.add(this.construir(rs));

        return objetos;
    }

    /**Persiste o objeto em um meio não volátil de armazenamento.
     * @param entidade Objeto a ser persistido.
     * @return Id gerado após a inserção do objeto.
     * @throws SQLException
     */
    @Override
    public int adicionar(T entidade) throws SQLException {

        SQLProdutor sqlProdutor = new SQLProdutor();
        int resultado = 0;

        /*Monte a seguinte parte do SQL:
        INSERT INTO nomeTabela VALUES*/
        sqlProdutor.insert(this.obterNomeTabela(), this.obterNomeColunas());

        //Monte o seguinte trecho do SQL: (?, ?, ?, ...);
        sqlProdutor.values(this.obterNomeColunas());

        this.conexao.setAutoCommit(false);

        PreparedStatement ps = conexao.prepareStatement(sqlProdutor.toString());
        this.preencherPSInsert(ps);
        resultado = persistencia.executarAtualizacao(ps);

        this.conexao.commit();

        return resultado;
    }

    /**Atualiza os dados de um objeto já existente no meio de persistência.
     * @param entidade Objeto a ser atualizado.
     * @throws SQLException
     */
    @Override
    public void atualizar(T entidade) throws SQLException {

        SQLProdutor sqlProdutor = new SQLProdutor();

        /*Monte a seguinte parte do SQL:
        UPDATE nomeTabela SET col1 = ?, col2 = ?*/
        sqlProdutor.update(this.obterNomeTabela(), this.obterNomeColunas());

        //Monte o seguinte trecho do SQL: WHERE id = ?;
        sqlProdutor.where("id").eq();

        PreparedStatement ps = conexao.prepareStatement(sqlProdutor.toString());
        this.preencherPSUpdate(ps);

        persistencia.executarAtualizacao(ps);
    }

    /**Remove um objeto do meio de persistência dado seu identificador.
     * @param id Identificador do objeto a ser removido.
     * @throws SQLException
     */
    @Override
    public void excluirPorId(int id) throws SQLException {

        SQLProdutor sqlProdutor = new SQLProdutor();

        /*Monte a seguinte parte do SQL:
        DELETE * FROM nomeTabela WHERE nomeCol = ?*/
        sqlProdutor.delete().from(this.obterNomeTabela()).where("id").eq();

        PreparedStatement ps = conexao.prepareStatement(sqlProdutor.toString());
        ps.setInt(1, id);

        persistencia.executarAtualizacao(ps);
    }

    /**Busca e retorna o objeto que possuir o identificador recebido.
     * @param id Identificador do objeto a ser buscado.
     * @return Objeto já construído e com atributos preenchidos, inclusive ID.
     * @throws SQLException
     */
    @Override
    public T obterPorId(int id) throws SQLException {

        SQLProdutor sqlProdutor = new SQLProdutor();

        /*Monte a seguinte parte do SQL:
        SELECT nomeCol1, nomeCol2 FROM nomeTabela*/
        sqlProdutor.select(this.obterNomeColunas()).from(this.obterNomeTabela());

        /*Monte o seguinte trecho do SQL: WHERE nomeCol = ?*/
        sqlProdutor.where("id").eq();

        PreparedStatement ps = conexao.prepareStatement(sqlProdutor.toString());
        ps.setInt(1, id);

        return construir(persistencia.executarSelecao(ps));
    }

    /**Busca e retorna todos objetos de um determinado tipo.
     * @return Lista com todos objetos encontrados.
     * @throws SQLException
     */
    @Override
    public List<T> obterTodos() throws SQLException {
        SQLProdutor sqlProdutor = new SQLProdutor();

        /*Monte a seguinte parte do SQL:
        SELECT nomeCol1, nomeCol2 FROM nomeTabela*/
        sqlProdutor.select(this.obterNomeColunas()).from(this.obterNomeTabela());

        String sql = sqlProdutor.toString();
        return extrairTodos(persistencia.executarSelecao(sql));
    }
}
