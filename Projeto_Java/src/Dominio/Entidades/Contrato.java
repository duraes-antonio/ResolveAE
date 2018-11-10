package Dominio.Entidades;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author 20161BSI0314
 */
public class Contrato {

    private int id;
    private String descricao;
    private LocalDateTime dataUltimaModif;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private int horasContratadas;
    private int fkUsuario;

    public Contrato(String descricao, LocalDate dataInicio, LocalDate dataFim,
                    LocalDateTime dataUltimaModif, int horasContratadas, int fkUsuario){
        this.setDescricao(descricao);
        this.setDataUltimaModif(dataUltimaModif);
        this.setDataInicio(dataInicio);
        this.setDataFim(dataFim);
        this.setHorasContratadas(horasContratadas);
        this.setFkUsuario(fkUsuario);
    }

    public Contrato(int id, String descricao, LocalDate dataInicio, LocalDate dataFim,
                    LocalDateTime dataUltimaModif, int horasContratadas, int fkUsuario){
        this.setId(id);
        this.setDescricao(descricao);
        this.setDataUltimaModif(dataUltimaModif);
        this.setDataInicio(dataInicio);
        this.setDataFim(dataFim);
        this.setHorasContratadas(horasContratadas);
        this.setFkUsuario(fkUsuario);
    }


    public int getId() {
        return id;
    }

    public void setId(int idContrato) {
        this.id = idContrato;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {

        if (!Util.isStringValida(descricao)) Util.throwExceptCampoVazio("descrição");

        this.descricao = descricao;
    }

    public LocalDateTime getDataUltimaModif() {
        return dataUltimaModif;
    }

    public void setDataUltimaModif(LocalDateTime dataUltimaModificacao) {
        this.dataUltimaModif = dataUltimaModificacao;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicioPrestacao) {
        this.dataInicio = dataInicioPrestacao;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFimPrestacao) {
        this.dataFim = dataFimPrestacao;
    }

    public int getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(int fkUsuario) {
        this.fkUsuario = fkUsuario;
    }

    public int getHorasContratadas() {
        return horasContratadas;
    }

    public void setHorasContratadas(int horasContratadas) {
        this.horasContratadas = horasContratadas;
    }
}
