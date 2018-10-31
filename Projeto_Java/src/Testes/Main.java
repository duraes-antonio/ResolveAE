package Testes;

import Dominio.Entidades.Cep;
import Dominio.Entidades.Endereco;
import Infraestrutura.PostgresDAO.EnderecoDAO;
import Infraestrutura.UtilPostgres.Persistencia;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {


    public static void insertX(String nome) throws SQLException {

        Connection conexao = Persistencia.get().getConexao();

        Persistencia persistencia = Persistencia.get();


            String sql = "insert into x(idade, nome) values(1, '" + nome + "');";
            persistencia.executarAtualizacao(sql, null);

    }

    public static void insertY(String nome) {

        Connection conexao = Persistencia.get().getConexao();
        Persistencia persistencia = Persistencia.get();
        int re = 0;


        try {
            conexao.setAutoCommit(false);

            insertX(nome);

            String sql2 = "insert into y(altura, sobre) values(3, '644444444444444444444444444444444444444444444');";
            re = persistencia.executarAtualizacao(sql2, null);

            conexao.commit();
        }

        catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }



    public static void main(String[] args) throws SQLException, IOException {

        long startTime = System.currentTimeMillis();

        EnderecoDAO enderecoDAO = new EnderecoDAO();
        Endereco endereco = new Cep(29161699).getEnderecoPorCep();
        endereco.setFkUsuario(1);
        enderecoDAO.adicionar(endereco);


        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("TEMPO de EXECUÇÃO:\t" + elapsedTime/1000.00 + " sec.");
    }
}
