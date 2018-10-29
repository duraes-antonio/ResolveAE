package Dominio.Entidades;

import java.util.List;

/**
 *
 * @author 20161BSI0314
 */
public abstract class APessoa {

    private String nome;
    private String email;
    private Endereco endereco;
    private List<Contato> contatos;

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
}
