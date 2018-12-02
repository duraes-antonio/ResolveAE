package Dominio.Entidades;

import Dominio.Util.Util;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class APessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "nome", length = 150)
    private String nome;

    @Column(name = "email", length = 100)
    private String email;

    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, targetEntity = Endereco.class)
    @JoinColumn(name = "id", referencedColumnName = "fk_usuario")
    private Endereco endereco;

    @Transient
    private List<Contato> contatos;

    APessoa() {}

    APessoa(String nome, String email, Endereco endereco, List<Contato> contatos) {
        this.setNome(nome);
        this.setEmail(email);
        this.setEndereco(endereco);
        this.setContatos(contatos);
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {

        if (Util.isStringValida(nome)) this.nome = nome;

        else Util.throwExceptCampoVazio("nome");
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {

        if (Util.isStringValida(email)) this.email = email;

        else Util.throwExceptCampoVazio("email");
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<Contato> getContatos() {
        return contatos;
    }

    public void setContatos(List<Contato> contatos) {
        this.contatos = contatos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
