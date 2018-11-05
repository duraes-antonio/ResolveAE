package Infraestrutura.Hibernate.DAO;

import Dominio.Entidades.Avaliacao;
import Dominio.Interfaces.IAvaliacaoRepositorio;

import java.sql.SQLException;
import java.util.List;

public class AvaliacaoDAOH extends AGenericDAOH<Avaliacao> implements IAvaliacaoRepositorio {


    public AvaliacaoDAOH() {
        super(Avaliacao.class);
    }

    @Override
    public List<Avaliacao> obterTodasPorUsuario(int usuarioId) throws SQLException {
        return null;
    }

    @Override
    public List<Avaliacao> obterTodasPorServico(int servicoId) throws SQLException {
        return null;
    }
}