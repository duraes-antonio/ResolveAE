package Dominio.Entidades;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

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

    @ManyToMany(mappedBy = "subtiposServico")
    private Collection<Servico> servicos = new ArrayList<>();


    public SubtipoServico() {}

    // Apenas Getters, devido a classe se comportar como Enum;
    public String getNome() {
        return nome;
    }

    public int getFkTipoServico() {
        return fkTipoServico;
    }

    public int getId() {
        return id;
    }
}
