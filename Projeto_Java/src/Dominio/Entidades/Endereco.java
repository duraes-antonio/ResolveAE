package Dominio.Entidades;

public class Endereco {

    private int id;
    private String bairro;
    private String cidade;
    private String estado;
    private Cep cep;
    private int fkUsuario;

    public Endereco(){}

    public Endereco(String bairro, String cidade, String estado,
                    int cep, int fkUsuario){
        this.setBairro(bairro);
        this.setCidade(cidade);
        this.setEstado(estado);
        this.cep = new Cep(cep);
        this.setFkUsuario(fkUsuario);
    }

    public Endereco(int id, String bairro, String cidade,
                    String estado, int cep, int fkUsuario){
        this.setId(id);
        this.setBairro(bairro);
        this.setCidade(cidade);
        this.setEstado(estado);
        this.cep = new Cep(cep);
        this.setFkUsuario(fkUsuario);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {

        if (Util.isStringValida(bairro)) this.bairro = bairro;

        else Util.throwExceptCampoVazio("bairro");
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {

        if (Util.isStringValida(cidade)) this.cidade = cidade;

        else Util.throwExceptCampoVazio("cidade");
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {

        if (Util.isStringValida(estado)) this.estado = estado;

        else Util.throwExceptCampoVazio("estado");
    }

    public Cep getCep() {
        return cep;
    }

    public void setCep(Cep cep) {
        this.cep = cep;
    }

    public int getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(int fkUsuario) {
        this.fkUsuario = fkUsuario;
    }


    @Override
    public String toString() {
        return String.format(
                "CEP:\t\t%d;\nBairro:\t\t%s;\nCidade:\t\t%s;\nEstado:\t\t%s;",
                this.cep.getCep(), this.getBairro(), this.getCidade(), this.getEstado());
    }
}
