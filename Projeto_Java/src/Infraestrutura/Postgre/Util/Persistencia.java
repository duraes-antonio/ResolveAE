package Infraestrutura.Postgre.Util;

import java.sql.*;

//Classe para agilizar criação de tabelas, seleção e inserção de valores;
public class Persistencia {

    private static Persistencia persistencia;
    private Connection conexao;
    private PreparedStatement preparedSt;

    //Alterar de acordo com sua base de dados, usuário e senha no postgresql;
    private Persistencia()
            throws SQLException, ClassNotFoundException {

        // Nome da sua base de dados no postgres;
        String nome_base_dados = "resolve_ae";

        // Nome do usuário de sua base;
        String nome_user_postgre = "postgres";

        // Senha para acessar sua base de dados;
        String senha = "postgres";

        String driver = "org.postgresql.Driver";
        String url = "jdbc:postgresql://localhost:5432/" + nome_base_dados;

        Class.forName(driver);
        this.conexao = DriverManager.getConnection(url, nome_user_postgre, senha);
    }

    public static synchronized Persistencia get() {

        try {
            if (persistencia == null || persistencia.conexao.isClosed()) {
                persistencia = new Persistencia();
            }
        }

        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        catch (SQLException e){
            e.printStackTrace();
        }

        return persistencia;
    }


    public boolean executar(String sql) throws SQLException {

        boolean result = false;

        if (persistencia == null) {
            throw new NullPointerException("Objeto não inicializado!");
        }

        else {
            try {
                result = this.conexao.prepareStatement(sql).execute();
            }

            catch (SQLException e) {
                e.printStackTrace();
            }

            finally {
                if (!this.conexao.isClosed()) this.conexao.close();
            }

            return result;
        }
    }

    public boolean executar(PreparedStatement preparedSt)
            throws SQLException {
        return this.executar(preparedSt.toString());
    }


    /**Executa uma seleção no banco definido como padrão.
     * @param preparedSt Comando a ser executado no banco.
     * @return ResultSet com os resultados da seleção.
     * @throws SQLException
     */
    public ResultSet executarSelecao(PreparedStatement preparedSt)
            throws SQLException {

        ResultSet result = null;

        if (persistencia == null) {
            throw new NullPointerException("Objeto não inicializado!");
        }

        else {
            try {
                result = preparedSt.executeQuery();
            }

            catch (SQLException e) {
                e.printStackTrace();
            }

            return result;
        }
    }

    /**Executa uma seleção no banco definido como padrão.
     * @param sql Comando a ser executado no banco.
     * @return ResultSet com os resultados da seleção.
     * @throws SQLException
     */

    public ResultSet executarSelecao(String sql)
            throws SQLException {
        return this.executarSelecao(getConexao().prepareStatement(sql));
    }


    /**Executa inserção, atualização ou exclusão no banco definido como padrão.
     * @param sql Comando a ser executado no banco.
     * @param nomeColunaId Nome da coluna que representa o identificador da tabela.
     * @return Id do objeto inserido, modificado ou atualizado
     * @throws SQLException
     */
    public int executarAtualizacao(String sql, String nomeColunaId)
            throws SQLException {

        int idObjeto;
        ResultSet rs;

        PreparedStatement ps = this.conexao.prepareStatement(sql);
        idObjeto = ps.executeUpdate();
        rs = ps.getGeneratedKeys();

        if (rs.isBeforeFirst()) {
            rs.next();
            idObjeto = rs.getInt(nomeColunaId);
        }

        return idObjeto;
    }

    /**Executa inserção, atualização ou exclusão no banco definido como padrão.
     * @param preparedSt Comando a ser executado no banco.
     * @param nomeColunaId Nome da coluna que representa o identificador da tabela.
     * @return Id do objeto inserido, modificado ou atualizado
     * @throws SQLException
     */
    public int executarAtualizacao(PreparedStatement preparedSt, String nomeColunaId)
            throws SQLException {
        return this.executarAtualizacao(preparedSt.toString(), nomeColunaId);
    }

    public Connection getConexao() {
        return this.conexao;
    }
}