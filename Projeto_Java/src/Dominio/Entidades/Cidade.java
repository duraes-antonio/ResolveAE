package Dominio.Entidades;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cidade")
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "nome", length = 40)
    private String nome;

    @Column(name = "fk_estado")
    private int fkEstado;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Bairro> bairros;


    public Cidade() {}

    public Cidade(String nome, int fkEstado){
        setNome(nome);
        setFkEstado(fkEstado);
    }

    public Cidade(int id, String nome, int fkEstado){
        setId(id);
        setNome(nome);
        setFkEstado(fkEstado);
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

    public int getFkEstado() {
        return fkEstado;
    }

    public void setFkEstado(int fkEstado) {
        this.fkEstado = fkEstado;
    }

    public List<Bairro> getBairros() {
        return bairros;
    }

    public void setBairros(List<Bairro> bairros) {
        this.bairros = bairros;
    }
}
