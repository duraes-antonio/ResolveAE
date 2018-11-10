//package Infraestrutura.Postgre.DAO;
//
//import Dominio.Entidades.InfoProfissional;
//import Dominio.Entidades.Servico;
//import Dominio.Interfaces.IServicoRepositorio;
//import Infraestrutura.Enum.ETab;
//import Infraestrutura.Postgre.Util.Persistencia;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//public class ServicoDAO extends AGenericDAO<Servico>
//        implements IServicoRepositorio {
//
//
//    public static final String ID = ETab.INFO_PRO.get() + ".id";
//    public static final String VALOR = ETab.INFO_PRO.get() + ".descricao";
//    public static final String DATA_INICIO = ETab.INFO_PRO.get() + ".data_inicio";
//    public static final String DATA_FIM = ETab.INFO_PRO.get() + ".data_fim";
//    public static final String FK_USUARIO = ETab.INFO_PRO.get() + ".fk_usuario";
//    public static final String FK_TIPO_INFO_PROF = ETab.INFO_PRO.get() + ".fk_tipo_info_prof";
//
//    public static final List<String> COLUNAS = Arrays.asList(
//            DESCRICAO, DATA_INICIO, DATA_FIM, FK_USUARIO, FK_TIPO_INFO_PROF);
//
//    private Persistencia persistencia = Persistencia.get();
//    private Connection conexao = persistencia.getConexao();
//    private PreparedStatement psTodos;
//    private PreparedStatement psTodosPorTipo;
//    private PreparedStatement psTodosPorUsuario;
//    private PreparedStatement psTodosPorTipoEUsuario;
//    private PreparedStatement psTodosPorData;
//
//
//    private List<InfoProfissional> obterGenerico(PreparedStatement ps)
//            throws SQLException {
//
//        List<InfoProfissional> infos = null;
//
//        try {
//            infos = extrairTodos(persistencia.executarSelecao(ps));
//        }
//
//        catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        finally {
//            if (ps != null) ps.close();
//        }
//
//        return infos;
//    }
//
//    /**
//     * Busca e retorna todos objetos de um determinado tipo.
//     *
//     * @param limit  Quantidade de resultados a ser retornada.
//     * @param offset Quantidade de resultados a pular.
//     * @return Lista com todos objetos encontrados.
//     * @throws SQLException
//     */
//    @Override
//    public List<InfoProfissional> obterTodos(Integer limit, Integer offset)
//            throws SQLException {
//
//        String sql = GenericSQL.obterTodos(ETab.INFO_PRO, COLUNAS, ID, limit, offset);
//        psTodos = conexao.prepareStatement(sql);
//        return obterGenerico(psTodos);
//    }
//
//}
