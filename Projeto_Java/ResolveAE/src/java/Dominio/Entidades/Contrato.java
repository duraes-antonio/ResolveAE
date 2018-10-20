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

    Contrato(){}

    Contrato(String descricao, Date dataCriacao, Date dataUltimaModif,
             Date dataInicio, Date dataFim){
        this.setDescricao(descricao);
        this.setDataCriacao(dataCriacao);
        this.setDataUltimaModif(dataUltimaModif);
        this.setDataInicio(dataInicio);
        this.setDataFim(dataFim);
    }

    Contrato(int id, String descricao, Date dataCriacao, Date dataUltimaModif,
             Date dataInicio, Date dataFim){
        this.setId(id);
        this.setDescricao(descricao);
        this.setDataCriacao(dataCriacao);
        this.setDataUltimaModif(dataUltimaModif);
        this.setDataInicio(dataInicio);
        this.setDataFim(dataFim);
    }
    public int getId() {
        return this.id;
    }

    public void setId(int idContrato) {

        if (idContrato < 1) Util.throwExceptNumeroInferior("id", 1);

        this.id = idContrato;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {

        if (!Util.isStringValida(descricao)) Util.throwExceptCampoVazio(descricao);

        this.descricao = descricao;
    }

    public Date getDataCriacao() {
        return this.dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataUltimaModif() {
        return this.dataUltimaModif;
    }

    public void setDataUltimaModif(Date dataUltimaModificacao) {
        this.dataUltimaModif = dataUltimaModificacao;
    }

    public Date getDataInicio() {
        return this.dataInicio;
    }

    public void setDataInicio(Date dataInicioPrestacao) {
        this.dataInicio = dataInicioPrestacao;
    }

    public Date getDataFim() {
        return this.dataFim;
    }

    public void setDataFim(Date dataFimPrestacao) {
        this.dataFim = dataFimPrestacao;
    }
}
