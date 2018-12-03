package Infraestrutura.Postgre.DAO;

import Dominio.Entidades.Horario;
import Dominio.Enum.EDiaSemana;
import Dominio.Interfaces.IHorarioRepositorio;
import Infraestrutura.Enum.ETab;
import Infraestrutura.Postgre.Util.Persistencia;
import Infraestrutura.Postgre.Util.SQLProdutor;

import java.sql.*;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

public class HorarioDAO extends AGenericDAO<Horario> implements IHorarioRepositorio {

    public static final String ID = ETab.HORARIO_PREST.get() + ".id";
    public static final String INICIO = ETab.HORARIO_PREST.get() + ".inicio";
    public static final String FIM = ETab.HORARIO_PREST.get() + ".fim";
    public static final String DISPONIVEL = ETab.HORARIO_PREST.get() + ".disponivel";
    public static final String FK_USUARIO = ETab.HORARIO_PREST.get() + ".fk_usuario";
    public static final String FK_DIA_SEMANA = ETab.HORARIO_PREST.get() + ".fk_dia_semana";

    public static final List<String> COLUNAS = Arrays.asList(
            INICIO, FIM, DISPONIVEL, FK_USUARIO, FK_DIA_SEMANA);

    private Persistencia persistencia = Persistencia.get();
    private Connection conexao = persistencia.getConexao();

    private PreparedStatement psTodos;
    private PreparedStatement psTodosPorDia;
    private PreparedStatement psTodosLivres;
    private PreparedStatement psTodosOcupados;
    private PreparedStatement psTodosNoIntervalo;
    private PreparedStatement psTodosPorUsuario;


    private List<Horario> obterVarios(PreparedStatement ps)
            throws SQLException {

        System.out.println(ps);
        List<Horario> horarios = null;

        try {
            horarios = extrairTodos(persistencia.executarSelecao(ps));
        }

        catch (SQLException e) {
            e.printStackTrace();
        }

        finally {
            if (ps != null) ps.close();
        }

        return horarios;
    }

    /**
     * Monta a base das consultas que retornam uma lista de horarios.
     * Query pronta, para usar em cada consulta, basta adicionar as cláusulas
     * WHERE em diante.
     *
     * @return Produtor de SQL contendo a estrutura básica das consultas q
     */
    private SQLProdutor obterSQlInicial() {

        SQLProdutor sqlProd = new SQLProdutor();
        sqlProd.select(ID, INICIO, FIM, DISPONIVEL, FK_USUARIO, FK_DIA_SEMANA);
        sqlProd.from(ETab.HORARIO_PREST.get());
        return sqlProd;
    }

    /**
     * Busca e retorna todos objetos de um determinado tipo.
     *
     * @param limit  Quantidade de resultados a ser retornada.
     * @param offset Quantidade de resultados a pular.
     * @return Lista com todos objetos encontrados.
     * @throws SQLException
     */
    @Override
    public List<Horario> obterTodos(Integer limit, Integer offset) throws SQLException {
        SQLProdutor sqlProd = obterSQlInicial();
        sqlProd.orderBy(1).limit(limit).offset(offset);
        psTodos = conexao.prepareStatement(sqlProd.toString());
        return obterVarios(psTodos);
    }

    @Override
    public List<Horario> obterTodosPorDia(EDiaSemana dia, int usuarioId, Integer limit, Integer offset)
            throws SQLException {

        SQLProdutor sqlProd = obterSQlInicial();
        sqlProd.where(FK_USUARIO).eq().and(FK_DIA_SEMANA).eq();
        sqlProd.orderBy(1).limit(limit).offset(offset);
        psTodosPorDia = conexao.prepareStatement(sqlProd.toString());
        psTodosPorDia.setInt(1, usuarioId);
        psTodosPorDia.setInt(2, dia.getId());
        return obterVarios(psTodosPorDia);
    }

    @Override
    public List<Horario> obterTodosLivres(Integer limit, Integer offset)
            throws SQLException {

        SQLProdutor sqlProd = obterSQlInicial();
        sqlProd.where(DISPONIVEL).eq().orderBy(1).limit(limit).offset(offset);
        psTodosLivres = conexao.prepareStatement(sqlProd.toString());
        psTodosLivres.setBoolean(1, true);
        return obterVarios(psTodosLivres);
    }

    @Override
    public List<Horario> obterTodosOcupados(Integer limit, Integer offset)
            throws SQLException {

        SQLProdutor sqlProd = obterSQlInicial();
        sqlProd.where(DISPONIVEL).eq().orderBy(1).limit(limit).offset(offset);
        psTodosOcupados = conexao.prepareStatement(sqlProd.toString());
        psTodosOcupados.setBoolean(1, false);
        return obterVarios(psTodosOcupados);
    }

    @Override
    public List<Horario> obterTodosPorUsuario(int usuarioId, Integer limit, Integer offset) throws SQLException {

        SQLProdutor sqlProd = obterSQlInicial();
        sqlProd.where(FK_USUARIO).eq().orderBy(1).limit(limit).offset(offset);
        psTodosPorUsuario = conexao.prepareStatement(sqlProd.toString());
        psTodosPorUsuario.setInt(1, usuarioId);
        return obterVarios(psTodosPorUsuario);
    }

    @Override
    public List<Horario> obterTodosNoIntervalo(LocalTime inicio, LocalTime fim, Integer limit, Integer offset)
            throws SQLException {

        SQLProdutor sqlProd = obterSQlInicial();

        //WHERE inicio >= ? AND fim <= ?;
        sqlProd.where(INICIO).grteq().and(FIM).leq();
        sqlProd.orderBy(1).limit(limit).offset(offset);

        psTodosNoIntervalo = conexao.prepareStatement(sqlProd.toString());
        psTodosNoIntervalo.setTime(1, Time.valueOf(inicio));
        psTodosNoIntervalo.setTime(2, Time.valueOf(fim));
        return obterVarios(psTodosNoIntervalo);
    }


    /**
     * Substitui os '?' do PS pelos valores dos atributos da objeto.
     * Substitui os valores para operações de INSERT e UPDATE apenas.
     *
     * @param ps     P. Statement com SQL já pronto com os '?' para serem substituídos.
     * @param objeto Objeto com os atributos para preencher o statement.
     * @return ps com os '?' substituídos, pronto para execução.
     */
    @Override
    protected PreparedStatement preencherPS(PreparedStatement ps, Horario objeto) throws SQLException {

        ps.setTime(1, Time.valueOf(objeto.getHorarioInicio()));
        ps.setTime(2, Time.valueOf(objeto.getHorarioFim()));
        ps.setBoolean(3, objeto.isLivre());
        ps.setInt(4, objeto.getFkUsuario());
        ps.setInt(5, objeto.getFkDiaSemana());

        if (objeto.getId() > 0) {
            ps.setInt(COLUNAS.size() + 1, objeto.getId());
        }

        return ps;
    }

    /**
     * Monta e retorna o objeto a partir de um resultSet.
     * Não faz validações sobre o resultSet.
     *
     * @param rs ResultSet retornado de uma consulta já executada.
     * @return Objeto montado a partir dos resultados da consulta.
     */
    @Override
    protected Horario construir(ResultSet rs) throws SQLException {
        return new Horario(
                rs.getInt(ID),
                rs.getTime(INICIO).toLocalTime(),
                rs.getTime(FIM).toLocalTime(),
                rs.getBoolean(DISPONIVEL),
                rs.getInt(FK_USUARIO),
                rs.getInt(FK_DIA_SEMANA)
        );
    }

    /**
     * Retorna uma string com query de INSERT, com '?' p/ ser substituído.
     *
     * @return String com comando SQL para adicionar um novo objeto.
     */
    @Override
    protected String obterSqlAdicionar() {
        return GenericSQL.adicionar(ETab.HORARIO_PREST, COLUNAS);
    }

    /**
     * Retorna uma string com query de UPDATE, com '?' p/ ser substituído.
     *
     * @return String com comando SQL para atualizar um objeto.
     */
    @Override
    protected String obterSqlAtualizar() {
        return GenericSQL.atualizar(ETab.HORARIO_PREST, COLUNAS, ID);
    }

    /**
     * Retorna uma string com query de DELETE, com '?' p/ ser substituído.
     *
     * @return String com comando SQL para deletar um objeto.
     */
    @Override
    protected String obterSqlExcluir() {
        return GenericSQL.excluirPorId(ETab.HORARIO_PREST, ID);
    }

    /**
     * Retorna uma string com query de SELECT, com '?' p/ ser substituído.
     *
     * @return String com comando SQL para buscar um objeto.
     */
    @Override
    protected String obterSqlSelecionar() {
        return GenericSQL.obterPorId(ETab.HORARIO_PREST, COLUNAS, ID);
    }

    /**
     * Define o Id de um objeto.
     *
     * @param objeto Objeto a ter seu ID atualizado.
     * @param id     Id a ser inserido no objeto.
     */
    @Override
    protected void definirId(Horario objeto, int id) {
        objeto.setId(id);
    }
}
