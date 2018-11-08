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

    /**Monta o SQL de uma query genérica para pegar as X colunas de todas registros.
     * @param tabela Nome da tabela que a query será executada.
     * @param colunas Nome das colunas a serem retornadas.
     * @param qtdParamOrderBy Quantidade de parâmetros para a cláusula Order By;
     * @return String no formato: SELECT col1, col2, ... FROM tabela LIMIT ? OFFSET ?
     */
    public static String obterTodos(ETab tabela, List<String> colunas, int qtdParamOrderBy) {

        SQLProdutor sqlProdutor = new SQLProdutor();
        sqlProdutor.select(colunas).from(tabela.get()).limit().offset();
        return sqlProdutor.toString();
    }
}