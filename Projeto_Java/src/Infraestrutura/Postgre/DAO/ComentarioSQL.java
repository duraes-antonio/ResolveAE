package Infraestrutura.Postgre.DAO;

import Infraestrutura.Enum.ETab;
import Infraestrutura.Postgre.Util.SQLProdutor;

import java.util.Arrays;
import java.util.List;


public class ComentarioSQL {

    //Nome das colunas da tabela COMENTARIO (nomes usados para montar as querys);
    private static final String _ID = "id";
    private static final String _COMENTARIO = "comentario";
    private static final String _FK_AVALIACAO = "fk_avaliacao";

    public static final String ID = ETab.COMENTARIO.get() + "_id";
    public static final String COMENTARIO = ETab.COMENTARIO.get() + "_comentario";
    public static final String FK_AVALIACAO = ETab.COMENTARIO.get() + "_fk_avaliacao";

    public static final List<String> COLUNAS = Arrays.asList(COMENTARIO, FK_AVALIACAO);
    public static final List<String> _COLUNAS = Arrays.asList(
            _ID + " AS " + ID,
            _COMENTARIO + " AS " + COMENTARIO,
            _FK_AVALIACAO + " AS " + FK_AVALIACAO);

    //Querys prontas p/ ser usadas, apenas substituir os '?' pelo valor;
    public static final String ADICIONAR = adicionar();
    public static final String ATUALIZAR = atualizar();
    public static final String EXCLUIR = excluirPorId();
    public static final String OBTER_POR_ID = obterPorId();
    public static final String OBTER_TODOS = obterTodos();
    public static final String OBTER_POR_AVALIACAO = obterPorAvaliacao();
    public static final String OBTER_TODOS_POR_USUARIO = obterTodosPorUsuario();
    public static final String OBTER_TODOS_POR_SERVICO = obterTodosPorServico();

    //Métodos que montam cada query;
    private static String adicionar() {
        SQLProdutor sqlProdutor = new SQLProdutor();
        sqlProdutor.insert(ETab.COMENTARIO.get(), COLUNAS).values(COLUNAS);
        return sqlProdutor.toString();
    }

    private static String atualizar() {

        SQLProdutor sqlProdutor = new SQLProdutor();
        sqlProdutor.update(ETab.COMENTARIO.get(), COLUNAS).where(ID).eq();
        return sqlProdutor.toString();
    }

    private static String excluirPorId() {
        SQLProdutor sqlProdutor = new SQLProdutor();
        sqlProdutor.delete().from(ETab.COMENTARIO.get()).where(ID).eq();
        return sqlProdutor.toString();
    }

    private static String obterPorId() {

        SQLProdutor sqlProdutor = new SQLProdutor();
        sqlProdutor.select(_COLUNAS).from(ETab.COMENTARIO.get()).where(ID).eq();
        return sqlProdutor.toString();
    }

    private static String obterTodos() {

        SQLProdutor sqlProdutor = new SQLProdutor();
        sqlProdutor.select(_COLUNAS).from(ETab.COMENTARIO.get());
        return sqlProdutor.toString();
    }

    private static String obterPorAvaliacao() {

        SQLProdutor sqlProdutor = new SQLProdutor();
        sqlProdutor.select(_COLUNAS).from(ETab.COMENTARIO.get()).where(FK_AVALIACAO).eq();
        return sqlProdutor.toString();
    }

    //TODO finalizar após ter as colunas da AVALIACAO
    private static String obterTodosPorUsuario() {

        SQLProdutor sqlProd = new SQLProdutor();
        sqlProd.select(_COLUNAS).from(ETab.COMENTARIO.get());
        sqlProd.innerJoin(ETab.AVALIACAO.get()).on(FK_AVALIACAO, "avaliacao.id");
        sqlProd.where("fk_usuario").eq();

        return sqlProd.toString();
    }

    //TODO finalizar após ter as colunas da AVALIACAO
    private static String obterTodosPorServico() {

        SQLProdutor sqlProd = new SQLProdutor();
        sqlProd.select(_COLUNAS).from(ETab.COMENTARIO.get())
                .innerJoin(ETab.AVALIACAO.get()).on(FK_AVALIACAO, "avaliacao.id")
                .where("avaliacao.fk_usuario").eq();

        return sqlProd.toString();
    }
}