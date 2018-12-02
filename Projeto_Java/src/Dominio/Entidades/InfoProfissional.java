package Dominio.Entidades;

import Dominio.Enum.ETipoInfoProfissional;
import Dominio.Util.Util;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "info_profissional")
public class InfoProfissional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "descricao", length = 200)
    private String descricao;

    @Column(name = "data_inicio")
    private LocalDate dataInicio;

    @Column(name = "data_fim")
    private LocalDate dataFim;

    @Transient
    private ETipoInfoProfissional tipoInfo;

    @Column(name = "fk_tipo_info_prof")
    private int fkTipoInfo;

    @Column(name = "fk_usuario")
    private int fkUsuario;


    public InfoProfissional() { }

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
        setTipoInfo(ETipoInfoProfissional.getById(fkTipoInfo));
        this.fkTipoInfo = fkTipoInfo;
    }

    @Override
    public String toString() {

        String enderecoStr = "\nID:\t\t\t\t" + getId();
        enderecoStr += "\nDescrição:\t\t" + getDescricao();
        enderecoStr += "\nData início:\t" + getDataInicio();
        enderecoStr += "\nData fim:\t\t" + getDataFim();
        enderecoStr += "\nTipo info.:\t\t" + getTipoInfoProfissional().getTipo();
        enderecoStr += "\nFK_tipo_info:\t" + getFkTipoInfo();
        enderecoStr += "\nFK_usuario:\t\t" + getFkUsuario();

        return enderecoStr;
    }
}
