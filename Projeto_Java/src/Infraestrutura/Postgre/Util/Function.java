package Infraestrutura.Postgre.Util;

import Dominio.Entidades.Endereco;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Classe que contém as chamadas paras as funções definidas no SGBD Postgre.
 * Todas funções definidas no Postgre devem estar aqui para uso da aplicação.
 */
public class Function {

    public static final String SALVAR_ENDERECO = "salvar_endereco";

    public static Endereco salvarEndereco(Endereco endereco, Persistencia persistencia)
            throws SQLException {

        /*FUNÇÃO SQL:
        "SELECT insert_endereco(bairro_nome, cidade_nome, estado_sigla,
                                cep_novo, usuario_id);"*/
        SQLProdutor sqlProdutor = new SQLProdutor();
        sqlProdutor.select().function(SALVAR_ENDERECO, 5);
        String sql = sqlProdutor.toString();

        PreparedStatement ps = persistencia.getConexao().prepareStatement(sql);

        ps.setString(1, endereco.getBairro());
        ps.setString(2, endereco.getCidade());
        ps.setString(3, endereco.getEstado());
        ps.setInt(4, endereco.getCep().getCep());
        ps.setInt(5, endereco.getFkUsuario());

        ResultSet rs = persistencia.executarSelecao(ps);

        if (!rs.isBeforeFirst()) return null;

        rs.next();
        endereco.setId(rs.getInt(SALVAR_ENDERECO));

        return endereco;
    }
}
