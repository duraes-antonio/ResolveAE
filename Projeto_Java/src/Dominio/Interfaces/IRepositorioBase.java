package Dominio.Interfaces;

import java.sql.SQLException;
import java.util.List;

public interface IRepositorioBase<T> {

    /**Persiste o objeto em um meio não volátil de armazenamento.
     * @param entidade Objeto a ser persistido.
     * @return Id gerado após a inserção do objeto.
     * @throws SQLException
     */
    int adicionar(T entidade) throws SQLException;

    /**Atualiza os dados de um objeto já existente no meio de persistência.
     * @param entidade Objeto a ser atualizado.
     * @throws SQLException
     */
    void atualizar(T entidade) throws SQLException;

    /**Remove um objeto do meio de persistência dado seu identificador.
     * @param id Identificador do objeto a ser removido.
     * @throws SQLException
     */
    void excluirPorId(int id) throws SQLException;

    /**Busca e retorna o objeto que possuir o identificador recebido.
     * @param id Identificador do objeto a ser buscado.
     * @return Objeto já construído e com atributos preenchidos, inclusive ID.
     * @throws SQLException
     */
    T obterPorId(int id) throws SQLException;

    /**Busca e retorna todos objetos de um determinado tipo.
     * @return Lista com todos objetos encontrados.
     * @throws SQLException
     */
    List<T> obterTodos() throws SQLException;
}
