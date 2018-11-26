package Dominio.Entidades;

import java.util.List;

public class Usuario extends APessoa {

    private int id;
    private String senha;
    private String sobre;
    private List<InfoProfissional> infoProfissionais;
    private List<Horario> horarios;
    private List<Avaliacao> avaliacoes;

    public Usuario(String nome, String email, String senha, String sobre) {
        super(nome, email, null, null);
        this.setSenha(senha);
        this.setSobre(sobre);
    }

    public Usuario(String nome, String email, Endereco endereco,
                   List<Contato> contatos, String senha, String sobre) {
        super(nome, email, endereco, contatos);
        this.setSenha(senha);
        this.setSobre(sobre);
    }

    public Usuario(int id, String nome, String email, Endereco endereco,
                   List<Contato> contatos, String senha, String sobre) {
        super(nome, email, endereco, contatos);
        this.setId(id);
        this.setSenha(senha);
        this.setSobre(sobre);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {

        if (!Util.isStringValida(senha)) {
            Util.throwExceptCampoVazio("senha");
        }
        this.senha = senha;
    }

    public String getSobre() {
        return sobre;
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

    @Override
    public String toString() {

        StringBuilder usuarioStr = new StringBuilder("\nID:\t\t" + getId());
        usuarioStr.append("\nNome:\t").append(getNome());
        usuarioStr.append("\nEmail:\t").append(getEmail());
        usuarioStr.append("\nSenha:\t").append(getSenha());
        usuarioStr.append("\nSobre:\t").append(getSobre());

        if (getEndereco() != null) {
            usuarioStr.append("\n\n---ENDEREÇO---");
            usuarioStr.append(getEndereco());
            usuarioStr.append("\n");
        }

        if (getInfoProfissionais() != null && getInfoProfissionais().size() > 0) {
            usuarioStr.append("\n---INFORMAÇÕES PROFISSIONAIS---\n");

            for (int i = 0; i < getInfoProfissionais().size(); i++) {
                usuarioStr.append(String.format("\n---Informação #%d", i+1));
                usuarioStr.append(getInfoProfissionais().get(i).toString());
                usuarioStr.append("\n");
            }
        }

        if (getAvaliacoes() != null && getAvaliacoes().size() > 0) {
            usuarioStr.append("\n---AVALIAÇÕES---\n");

            for (int i = 0; i < getAvaliacoes().size(); i++) {
                usuarioStr.append(String.format("\n---Avaliação #%d\n", i+1));
                usuarioStr.append(getAvaliacoes().get(i).toString());
                usuarioStr.append("\n");
            }
        }

        if (getContatos() != null && getContatos().size() > 0) {
            usuarioStr.append("\n---MEIOS DE CONTATO---\n");

            for (int i = 0; i < getContatos().size(); i++) {
                usuarioStr.append(String.format("\n---Contato #%d\n", i+1));
                usuarioStr.append(getContatos().get(i).toString());
                usuarioStr.append("\n");
            }
        }

        if (getHorarios() != null && getHorarios().size() > 0) {
            usuarioStr.append("\n\n---HORÁRIOS---\n");

            for (int i = 0; i < getAvaliacoes().size(); i++) {
                usuarioStr.append(getHorarios().get(i).toString());
            }
        }

        return usuarioStr.toString();
    }
}
