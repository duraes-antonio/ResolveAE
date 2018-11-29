package Dominio.Entidades;

import javax.persistence.*;

@Entity
@Table(name = "bairro")
public class Bairro {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "nome", length = 60)
    private String nome;

    @Column(name = "fk_cidade")
    private int fkCidade;

    @OneToOne(fetch = FetchType.LAZY)
    private Cidade cidade;


    public Bairro() {}

    public Bairro(String nome, int fkCidade){
        setNome(nome);
        setFkCidade(fkCidade);
    }

    public Bairro(int id, String nome, int fkCidade){
        setId(id);
        setNome(nome);
        setFkCidade(fkCidade);
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

    public int getFkCidade() {
        return fkCidade;
    }

    public void setFkCidade(int fkCidade) {
        this.fkCidade = fkCidade;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
}
