package Infraestrutura.Postgre.DAO;

import Infraestrutura.Enum.ETab;
import Infraestrutura.Postgre.Util.SQLProdutor;
import Infraestrutura.Postgre.Util.View;

import java.util.Arrays;
import java.util.List;


public class AvaliacaoSQL {

    //Nome das colunas da tabela AVALIACAO (nomes usados para montar as querys);
    public static final String ID = ETab.AVALIACAO.get() + ".id";
    public static final String NOTA = ETab.AVALIACAO.get() + ".nota";
    public static final String FK_USUARIO = ETab.AVALIACAO.get() + ".fk_usuario";
    public static final String FK_SERVICO = ETab.AVALIACAO.get() + ".fk_servico";

    public static final List<String> COLUNAS = Arrays.asList(NOTA, FK_USUARIO, FK_SERVICO);

    //Querys prontas p/ ser usadas, apenas substituir os '?' pelo valor;
    public static final String ADICIONAR = adicionar();
    public static final String ATUALIZAR = atualizar();
    public static final String EXCLUIR = excluirPorId();
    public static final String OBTER_POR_ID = obterPorId();
    public static final String OBTER_TODOS = obterTodos();
    public static final String OBTER_TODOS_POR_USUARIO = obterTodosPorUsuario();
    public static final String OBTER_TODOS_POR_SERVICO = obterTodosPorServico();

    //Métodos que montam cada query;
    private static String adicionar() {
        SQLProdutor sqlProdutor = new SQLProdutor();
        sqlProdutor.insert(ETab.AVALIACAO.get(), COLUNAS).values(COLUNAS);
        return sqlProdutor.toString();
    }

    private static String atualizar() {
        SQLProdutor sqlProdutor = new SQLProdutor();
        sqlProdutor.update(ETab.AVALIACAO.get(), COLUNAS).where(ID).eq();
        return sqlProdutor.toString();
    }

    private static String excluirPorId() {
        SQLProdutor sqlProdutor = new SQLProdutor();
        sqlProdutor.delete().from(ETab.AVALIACAO.get()).where(ID).eq();
        return sqlProdutor.toString();
    }

    private static String obterPorId() {
        SQLProdutor sqlProdutor = new SQLProdutor();
        sqlProdutor.select(COLUNAS).from(ETab.AVALIACAO.get()).where(ID).eq();
        return sqlProdutor.toString();
    }

    private static String obterTodos() {

        SQLProdutor sqlProdutor = new SQLProdutor();
        sqlProdutor.selectAll().from(View.VIEW_AVALIACAO_COMENTARIO.name());
        return sqlProdutor.toString();
    }

    private static String obterTodosPorUsuario() {
        SQLProdutor sqlProd = new SQLProdutor();
        sqlProd.select(COLUNAS).from(ETab.AVALIACAO.get()).where(FK_USUARIO).eq();
        return sqlProd.toString();
    }

    private static String obterTodosPorServico() {
        SQLProdutor sqlProd = new SQLProdutor();
        sqlProd.select(COLUNAS).from(ETab.AVALIACAO.get()).where(FK_SERVICO).eq();
        return sqlProd.toString();
    }
}