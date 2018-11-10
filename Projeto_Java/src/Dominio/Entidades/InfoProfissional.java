package Dominio.Entidades;

import Dominio.Enum.ETipoInfoProfissional;

import java.time.LocalDate;

/**
 *
 * @author 20161BSI0314
 */
public class InfoProfissional {

    private int id;
    private String descricao;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private ETipoInfoProfissional tipoInfo;
    private int fkTipoInfo;
    private int fkUsuario;

    public InfoProfissional(){}

    public InfoProfissional(String descricao, LocalDate dataInicio, LocalDate dataFim,
                            int fkTipoInfo, int fkUsuario) {
        this.setDescricao(descricao);
        this.setDataInicio(dataInicio);
        this.setDataFim(dataFim);
        this.setFkTipoInfo(fkTipoInfo);
        this.setFkUsuario(fkUsuario);
    }

    public InfoProfissional(int id, String descricao, LocalDate dataInicio, LocalDate dataFim,
                            int fkTipoInfo, int fkUsuario) {
        this.setId(id);
        this.setDescricao(descricao);
        this.setDataInicio(dataInicio);
        this.setDataFim(dataFim);
        this.setFkTipoInfo(fkTipoInfo);
        this.setFkUsuario(fkUsuario);
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

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public int getFkUsuario() {
        return fkUsuario;
    }

    private void setFkUsuario(int fkUsuario) {
        this.fkUsuario = fkUsuario;
    }

    public int getFkTipoInfo() {
        return fkTipoInfo;
    }

    public void setFkTipoInfo(int fkTipoInfo) {
        this.fkTipoInfo = fkTipoInfo;
    }
}
