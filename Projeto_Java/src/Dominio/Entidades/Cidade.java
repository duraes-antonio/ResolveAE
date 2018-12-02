package Dominio.Entidades;

import Dominio.Enum.EEstado;

import javax.persistence.*;

@Entity
@Table(name = "cidade")
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "nome", length = 40)
    private String nome;

    @Column(name = "fk_estado")
    private int fkEstado;

    private EEstado estado;


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
        this.setEstado();
    }

    @PostLoad
    private void setEstado() {
        this.estado = EEstado.getById(this.fkEstado);
    }

    public EEstado getEstado() {
        return estado;
    }
}
