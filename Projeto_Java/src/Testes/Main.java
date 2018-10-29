package Testes;

import Infraestrutura.UtilPostgres.Persistencia;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {


    public static void insertX(String nome) throws SQLException {

        Connection conexao = Persistencia.get().getConexao();

        Persistencia persistencia = Persistencia.get();


            String sql = "insert into x(idade, nome) values(1, '" + nome + "');";
            persistencia.executarAtualizacao(sql);

    }

    public static void insertY(String nome) {

        Connection conexao = Persistencia.get().getConexao();
        Persistencia persistencia = Persistencia.get();
        int re = 0;


        try {
            conexao.setAutoCommit(false);

            insertX(nome);

            String sql2 = "insert into y(altura, sobre) values(3, '644444444444444444444444444444444444444444444');";
            re = persistencia.executarAtualizacao(sql2);

            conexao.commit();
        }

        catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }



    public static void main(String[] args) throws SQLException {

        long startTime = System.currentTimeMillis();

        insertY("2");
//        AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();
//        avaliacaoDAO.adicionar(null);
//        avaliacaoDAO.atualizar(null);
//        avaliacaoDAO.obterPorId(1);
//        avaliacaoDAO.obterTodos();


        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("TEMPO de EXECUÇÃO:\t" + elapsedTime/1000.00 + " sec.");
    }
}
