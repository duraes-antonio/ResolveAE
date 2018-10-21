package Dominio.Entidades;

import java.util.List;

/**
 *
 * @author 20161BSI0314
 */

public class Usuario extends Pessoa {

    private int id;
    private String senha;
    private String sobre;
    private List<InfoProfissional> infoProfissionais;
    private List<Horario> horarios;
    private List<Avaliacao> avaliacoes;

    public Usuario(String nome, String email, Endereco endereco,
                   List<Contato> contatos, String senha, String sobre){
        super(nome, email, endereco, contatos);
        this.setSenha(senha);
        this.setSobre(sobre);
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {

        if (id < 1) Util.throwExceptNumeroInferior("id", 1);

        this.id = id;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {

        if (!Util.isStringValida(senha)) {
            Util.throwExceptCampoVazio("senha");
        }
        this.senha = senha;
    }

    public String getSobre() {
        return this.sobre;
    }

    public void setSobre(String sobre) {
        this.sobre = sobre;
    }

    public List<InfoProfissional> getInfoProfissionais() {
        return infoProfissionais;
    }

    public void setInfoProfissionais(List<InfoProfissional> infoProfissionais) {
        this.infoProfissionais = infoProfissionais;
    }

    public List<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
    }

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }
}
