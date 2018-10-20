/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio.Entidades;

import java.util.Date;

/**
 *
 * @author 20161BSI0314
 */
public class Contrato {
    private String descricao;
    private Date dataCriacao;
    private Date dataUltimaModificacao;
    private Date dataInicioPrestacao;
    private Date dataFimPrestacao;
    private int idUsuario;
    private int idContrato;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataUltimaModificacao() {
        return dataUltimaModificacao;
    }

    public void setDataUltimaModificacao(Date dataUltimaModificacao) {
        this.dataUltimaModificacao = dataUltimaModificacao;
    }

    public Date getDataInicioPrestacao() {
        return dataInicioPrestacao;
    }

    public void setDataInicioPrestacao(Date dataInicioPrestacao) {
        this.dataInicioPrestacao = dataInicioPrestacao;
    }

    public Date getDataFimPrestacao() {
        return dataFimPrestacao;
    }

    public void setDataFimPrestacao(Date dataFimPrestacao) {
        this.dataFimPrestacao = dataFimPrestacao;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(int idContrato) {
        this.idContrato = idContrato;
    }
}
