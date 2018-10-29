package Dominio.Entidades;

import java.util.Date;

/**
 *
 * @author 20161BSI0314
 */
public class Contrato {

    private int id;
    private String descricao;
    private Date dataCriacao;
    private Date dataUltimaModif;
    private Date dataInicio;
    private Date dataFim;
    private int fkUsuario;

    Contrato(String descricao, Date dataCriacao, Date dataUltimaModif,
             Date dataInicio, Date dataFim, int fkUsuario){
        this.setDescricao(descricao);
        this.setDataCriacao(dataCriacao);
        this.setDataUltimaModif(dataUltimaModif);
        this.setDataInicio(dataInicio);
        this.setDataFim(dataFim);
        this.setFkUsuario(fkUsuario);
    }

    Contrato(int id, String descricao, Date dataCriacao, Date dataUltimaModif,
             Date dataInicio, Date dataFim, int fkUsuario){
        this.setId(id);
        this.setDescricao(descricao);
        this.setDataCriacao(dataCriacao);
        this.setDataUltimaModif(dataUltimaModif);
        this.setDataInicio(dataInicio);
        this.setDataFim(dataFim);
        this.setFkUsuario(fkUsuario);
    }


    public int getId() {
        return id;
    }

    private void setId(int idContrato) {
        this.id = idContrato;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {

        if (!Util.isStringValida(descricao)) Util.throwExceptCampoVazio("descrição");

        this.descricao = descricao;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataUltimaModif() {
        return dataUltimaModif;
    }

    public void setDataUltimaModif(Date dataUltimaModificacao) {
        this.dataUltimaModif = dataUltimaModificacao;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicioPrestacao) {
        this.dataInicio = dataInicioPrestacao;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFimPrestacao) {
        this.dataFim = dataFimPrestacao;
    }

    public int getFkUsuario() {
        return fkUsuario;
    }

    private void setFkUsuario(int fkUsuario) {
        this.fkUsuario = fkUsuario;
    }
}
