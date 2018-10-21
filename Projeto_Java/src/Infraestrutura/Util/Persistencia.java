package Infraestrutura.Util;

import java.sql.*;

//Classe para agilizar criação de tabelas, seleção e inserção de valores;
public class Persistencia {

    private static Persistencia persistencia;
    private Connection conexao;
    private PreparedStatement preparedSt;
    private ResultSet resultSet;

    // ALTERAR de acordo com sua base de dados, usuário e senha no postgresql;
    private Persistencia()
            throws SQLException, ClassNotFoundException {

        // ALTERAR de acordo com sua base de dados, usuário e senha no postgresql;

        // Nome da sua base de dados no postgres;
        String nome_base_dados = "resolve_ae";

        // Nome do usuário de sua base;
        String nome_user_postgre = "postgres";

        // Senha para acessar sua base de dados;
        String senha = "postgre";

        String driver = "org.postgresql.Driver";
        String url = "jdbc:postgresql://localhost:5432/" + nome_base_dados;

        Class.forName(driver);
        this.conexao = DriverManager.getConnection(url, nome_user_postgre, senha);
    }

    public static synchronized Persistencia getPersistencia()
            throws SQLException, ClassNotFoundException {

        if (persistencia == null) {
            persistencia = new Persistencia();
        }

        return persistencia;
    }

    /**
     *Dado um PreparedStatement já executado, retorna o ID do item gravado na tabela;
     *@return Inteiro que representa o ID do item inserido em uma tabela;
     *@param st PreparedStatement já executado;
     * @throws java.sql.SQLException
     */
    public static int getIdAtCreate(PreparedStatement st) throws SQLException {
        ResultSet rs = st.getGeneratedKeys();
        rs.next();

        return rs.getInt("id");
    }

    public void executeQuery(String sql) {

        if (persistencia == null) {
            throw new NullPointerException("Objeto não inicializado!");
        }

        else {

            try {
                this.preparedSt = this.conexao.prepareStatement(sql);
                this.resultSet = this.preparedSt.executeQuery();
            }

            catch (SQLException ex) {
                System.out.println("Deu erro ao executar a QUERY, ResolveAE!");
            }
        }
    }
}