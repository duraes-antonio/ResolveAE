package Dominio.Interfaces;

import Dominio.Entidades.Avaliacao;

import java.sql.SQLException;
import java.util.List;

public interface IAvaliacaoRepositorio extends IRepositorioBase<Avaliacao> {

    List<Avaliacao> obterTodasPorUsuario(int usuarioId) throws SQLException;
    List<Avaliacao> obterTodasPorServico(int servicoId) throws SQLException;
}
