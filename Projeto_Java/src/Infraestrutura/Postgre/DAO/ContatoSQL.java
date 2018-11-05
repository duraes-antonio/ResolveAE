package Infraestrutura.Postgre.DAO;

import Infraestrutura.Enum.ETab;

import java.util.Arrays;
import java.util.List;


public class ContatoSQL {

    //Nome das colunas da tabela CONTATO (nomes usados para montar as querys);
    private static final String _ID = "id";
    private static final String _DESCRICAO = "descricao";
    private static final String _FK_USUARIO = "fk_usuario";
    private static final String _FK_TIPO_CONTATO = "fk_tipo_contato";

    public static final String ID = ETab.COMENTARIO.get() + "_" + _ID;
    public static final String DESCRICAO = ETab.COMENTARIO.get() + "_" + _DESCRICAO;
    public static final String FK_USUARIO = ETab.COMENTARIO.get() + "_" + _FK_USUARIO;
    public static final String FK_TIPO_CONTATO = ETab.COMENTARIO.get() + "_" + _FK_TIPO_CONTATO;

    public static final List<String> COLUNAS = Arrays.asList(DESCRICAO, FK_USUARIO, FK_TIPO_CONTATO);
    public static final List<String> _COLUNAS = Arrays.asList(
            _ID + " AS " + ID,
            _DESCRICAO + " AS " + DESCRICAO,
            _FK_USUARIO + " AS " + FK_USUARIO,
            _FK_TIPO_CONTATO + " AS " + FK_TIPO_CONTATO);

    //Querys prontas p/ ser usadas, apenas substituir os '?' pelo valor;
    public static final String ADICIONAR = adicionar();
    public static final String ATUALIZAR = atualizar();
    public static final String EXCLUIR = excluirPorId();
    public static final String OBTER_POR_ID = obterPorId();
    public static final String OBTER_TODOS = obterTodos();

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
        return GenericSQL.obterPorId(ETab.CONTATO, _COLUNAS, ID);
    }

    private static String obterTodos() {
        return GenericSQL.obterTodos(ETab.CONTATO, _COLUNAS);
    }
}