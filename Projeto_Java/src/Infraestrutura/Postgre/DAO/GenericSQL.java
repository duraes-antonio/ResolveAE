package Infraestrutura.Postgre.DAO;

import Infraestrutura.Enum.ETab;
import Infraestrutura.Postgre.Util.SQLProdutor;

import java.util.List;

public class GenericSQL {

    //Métodos que montam cada query;
    public static String adicionar(ETab nomeTabela, List<String> nomeColunas) {
        SQLProdutor sqlProdutor = new SQLProdutor();
        sqlProdutor.insert(nomeTabela.get(), nomeColunas).values(nomeColunas);
        return sqlProdutor.toString();
    }

    public static String atualizar(ETab tabela, List<String> colunas, String colunaId) {

        SQLProdutor sqlProdutor = new SQLProdutor();
        sqlProdutor.update(tabela.get(), colunas).where(colunaId).eq();
        return sqlProdutor.toString();
    }

    public static String excluirPorId(ETab tabela, String colunaId) {
        SQLProdutor sqlProdutor = new SQLProdutor();
        sqlProdutor.delete().from(tabela.get()).where(colunaId).eq();
        return sqlProdutor.toString();
    }

    public static String obterPorId(ETab tabela, List<String> colunas, String colunaId) {

        SQLProdutor sqlProdutor = new SQLProdutor();
        sqlProdutor.select(colunas).from(tabela.get()).where(colunaId).eq();
        return sqlProdutor.toString();
    }

    public static String obterTodos(ETab tabela, List<String> colunas) {

        SQLProdutor sqlProdutor = new SQLProdutor();
        sqlProdutor.select(colunas).from(tabela.get());
        return sqlProdutor.toString();
    }
}