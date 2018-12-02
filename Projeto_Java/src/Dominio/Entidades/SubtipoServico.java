package Dominio.Entidades;

import javax.persistence.*;

/**
 * Classe criada com a finalidade de permitir que o Hibernate tenha acesso a
 * todos subtipos de um serviço.
 */
@Entity
@Table(name = "subtipo_servico")
public class SubtipoServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "nome", length = 50)
    private String nome;

    @Column(name = "fk_tipo_servico")
    private int fkTipoServico;

    public SubtipoServico() {}

    public SubtipoServico(String nome, int fkTipoServico) {
        setNome(nome);
        setFkTipoServico(fkTipoServico);
    }

    public SubtipoServico(int id, String nome, int fkTipoServico) {
        setId(id);
        setNome(nome);
        setFkTipoServico(fkTipoServico);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getFkTipoServico() {
        return fkTipoServico;
    }

    public void setFkTipoServico(int fkTipoServico) {
        this.fkTipoServico = fkTipoServico;
    }

    @Override
    public String toString() {

        String subtipoStr = "\nID:\t\t\t\t\t" + getId();
        subtipoStr += "\nNome:\t\t\t\t" + getNome();
        subtipoStr += "\nFK_tipo_servico:\t" + getFkTipoServico();

        return subtipoStr;
    }

}
