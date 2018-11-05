package Infraestrutura.Postgre.DAO;

import Infraestrutura.Enum.ETab;
import Infraestrutura.Postgre.Util.SQLProdutor;
import Infraestrutura.Postgre.Util.View;

import java.util.Arrays;
import java.util.List;


public class EnderecoSQL {

    //Nome das colunas da VIEW_ENDERECO (nomes usados para montar as querys);
    public static final String ID = ETab.ENDERECO.get() + "_id";
    public static final String BAIRRO = ETab.ENDERECO.get() + "_bairro";
    public static final String CIDADE = ETab.ENDERECO.get() + "_cidade";
    public static final String ESTADO = ETab.ENDERECO.get() + "_estado";
    public static final String CEP = ETab.ENDERECO.get() + "_cep";
    public static final String FK_USUARIO = ETab.ENDERECO.get() + "_fk_usuario";

    //As colunas abaixo seguem a ordem de retorno da VIEW_ENDERECO;
    //Se alterar a ordem dos param da VIEW, alterar aqui também;
    public static final List<String> COLUNAS = Arrays.asList(CEP, BAIRRO, CIDADE, ESTADO, FK_USUARIO);

    //Colunas da tabela bairro;
    private static final String BAIRRO_NOME = ETab.BAIRRO.get() + ".nome";
    private static final String FK_CIDADE = ETab.BAIRRO.get() + ".fk_cidade";

    //Colunas da tabela cidade;
    private static final String CIDADE_NOME = ETab.CIDADE.get() + ".nome";
    private static final String FK_ESTADO = ETab.CIDADE.get() + ".fk_estado";

    private static final List<String> COLS_BAIRRO = Arrays.asList(BAIRRO_NOME, FK_CIDADE);
    private static final List<String> COLS_CIDADE = Arrays.asList(CIDADE_NOME, FK_ESTADO);

    //Querys prontas p/ ser usadas, apenas substituir os '?' pelo valor;
    public static final String EXCLUIR = excluirPorId();
    public static final String OBTER_POR_ID = obterPorId();
    public static final String OBTER_TODOS = obterTodos();
    public static final String OBTER_TODOS_POR_BAIRRO = obterTodosPorBairro();
    public static final String OBTER_TODOS_POR_CIDADE = obterTodosPorCidade();
    public static final String OBTER_TODOS_POR_ESTADO = obterTodosPorEstado();
    public static final String OBTER_TODOS_POR_CEP = obterTodosPorCep();


    //Métodos que montam cada query;
    private static String excluirPorId() {
        SQLProdutor sqlProdutor = new SQLProdutor();
        sqlProdutor.delete().from(ETab.ENDERECO.get()).where(ID).eq();
        return sqlProdutor.toString();
    }

    private static String obterPorId() {

        SQLProdutor sqlProdutor = new SQLProdutor();
        sqlProdutor.selectAll().from(View.VIEW_ENDERECO.name()).where(ID).eq();
        return sqlProdutor.toString();
    }

    private static String obterTodos() {

        SQLProdutor sqlProdutor = new SQLProdutor();
        sqlProdutor.selectAll().from(View.VIEW_ENDERECO.name());
        return sqlProdutor.toString();
    }

    private static String obterTodosPorBairro() {
        SQLProdutor sqlProdutor = new SQLProdutor();

        //SELECT * FROM VIEW_ENDERECO WHERE bairro ILIKE ?;
        sqlProdutor.selectAll().from(View.VIEW_ENDERECO.name())
                .where(BAIRRO).ilike();

        return sqlProdutor.toString();
    }

    private static String obterTodosPorCidade() {

        SQLProdutor sqlProdutor = new SQLProdutor();

        //SELECT * FROM VIEW_ENDERECO WHERE cidade ILIKE ?;
        sqlProdutor.selectAll().from(View.VIEW_ENDERECO.name())
                .where(CIDADE).ilike();

        return sqlProdutor.toString();
    }

    private static String obterTodosPorEstado() {
        SQLProdutor sqlProdutor = new SQLProdutor();

        //SELECT * FROM VIEW_ENDERECO WHERE estado ILIKE ?;
        sqlProdutor.selectAll().from(View.VIEW_ENDERECO.name())
                .where(ESTADO).ilike();

        return sqlProdutor.toString();
    }

    private static String obterTodosPorCep() {

        SQLProdutor sqlProdutor = new SQLProdutor();

        //SELECT * FROM VIEW_ENDERECO WHERE cep = ?;
        sqlProdutor.selectAll().from(View.VIEW_ENDERECO.name()).where(CEP).eq();

        return sqlProdutor.toString();
    }
}