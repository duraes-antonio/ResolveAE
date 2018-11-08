package Infraestrutura.Postgre.DAO;

import Infraestrutura.Enum.ETab;
import Infraestrutura.Postgre.Util.SQLProdutor;

import java.util.Arrays;
import java.util.List;


public class ContatoSQL {

    //Nome das colunas da tabela CONTATO (nomes usados para montar as querys);
    public static final String ID = ETab.CONTATO + ".id";
    public static final String DESCRICAO = ETab.CONTATO + ".descricao";
    public static final String FK_USUARIO = ETab.CONTATO + ".fk_usuario";
    public static final String FK_TIPO_CONTATO = ETab.CONTATO + ".fk_tipo_contato";

    public static final List<String> COLUNAS = Arrays.asList(DESCRICAO, FK_USUARIO, FK_TIPO_CONTATO);

    //Querys prontas p/ ser usadas, apenas substituir os '?' pelo valor;
    public static final String ADICIONAR = adicionar();
    public static final String ATUALIZAR = atualizar();
    public static final String EXCLUIR = excluirPorId();
    public static final String OBTER_POR_ID = obterPorId();
    public static final String OBTER_TODOS = obterTodos();
    public static final String OBTER_TODOS_POR_TIPO = obterTodosPorTipo();
    public static final String OBTER_TODOS_POR_USUARIO = obterTodosPorUsuario();
    public static final String OBTER_TODOS_POR_TIPO_E_USUARIO = obterTodosPorTipoEUsuario();

    //Métodos que montam cada query;
    private static String adicionar() {
        return GenericSQL.adicionar(ETab.CONTATO, COLUNAS);
    }

    private static String atualizar() {
        return GenericSQL.atualizar(ETab.CONTATO, COLUNAS, ID);
    }

    private static String excluirPorId() {
        return GenericSQL.excluirPorId(ETab.CONTATO, ID);
    }

    private static String obterPorId() {
        return GenericSQL.obterPorId(ETab.CONTATO, COLUNAS, ID);
    }

    private static String obterTodos() {
        return GenericSQL.obterTodos(ETab.CONTATO, COLUNAS, 1);
    }

    private static String obterTodosPorTipo() {
        SQLProdutor sqlProd = new SQLProdutor();
        sqlProd.select(COLUNAS).from(ETab.CONTATO.get())
                .where(FK_TIPO_CONTATO).eq().limit().offset();
        return sqlProd.toString();
    }

    private static String obterTodosPorUsuario() {
        SQLProdutor sqlProd = new SQLProdutor();
        sqlProd.select(COLUNAS).from(ETab.CONTATO.get()).where(FK_USUARIO)
                .eq().limit().offset();
        return sqlProd.toString();
    }

    private static String obterTodosPorTipoEUsuario() {
        SQLProdutor sqlProd = new SQLProdutor();
        sqlProd.select(COLUNAS).from(ETab.CONTATO.get())
                .where(FK_USUARIO).eq().and(FK_TIPO_CONTATO).eq().limit().offset();
        return null;
    }
}