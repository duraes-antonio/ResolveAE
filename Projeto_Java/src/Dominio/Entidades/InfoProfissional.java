package Dominio.Entidades;

import Dominio.Enum.ETipoInfoProfissional;

import java.util.Date;

/**
 *
 * @author 20161BSI0314
 */
public class InfoProfissional {

    private int id;
    private String descricao;
    private Date dataInicio;
    private Date dataFim;
    private ETipoInfoProfissional tipoInfo;
    private int fkUsuario;

    public InfoProfissional(){}

    public InfoProfissional(String descricao, Date dataInicio, Date dataFim,
                            ETipoInfoProfissional tipoInfo) {
        this.setDescricao(descricao);
        this.setDataInicio(dataInicio);
        this.setDataFim(dataFim);
        this.setTipoInfo(tipoInfo);
    }

    public InfoProfissional(int id, String descricao, Date dataInicio, Date dataFim,
                            ETipoInfoProfissional tipoInfo) {
        this.setId(id);
        this.setDescricao(descricao);
        this.setDataInicio(dataInicio);
        this.setDataFim(dataFim);
        this.setTipoInfo(tipoInfo);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ETipoInfoProfissional getTipoInfoProfissional() {
        return tipoInfo;
    }

    public void setTipoInfo(ETipoInfoProfissional tipoInfo) {
        this.tipoInfo = tipoInfo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {

        if (Util.isStringValida(descricao)) this.descricao = descricao;

        else Util.throwExceptCampoVazio("descrição");
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public int getFkUsuario() {
        return fkUsuario;
    }

    private void setFkUsuario(int fkUsuario) {
        this.fkUsuario = fkUsuario;
    }

}
