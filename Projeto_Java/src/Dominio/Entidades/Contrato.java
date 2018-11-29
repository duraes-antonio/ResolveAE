package Dominio.Entidades;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "contrato")
public class Contrato {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "data_inicio")
    private LocalDate dataInicio;

    @Column(name = "data_fim")
    private LocalDate dataFim;

    @Column(name = "data_ult_modif")
    private LocalDateTime dataUltimaModif;

    @Column(name = "descricao", length = 1000)
    private String descricao;

    @Column(name = "horas_contratadas")
    private int horasContratadas;

    @Column(name = "fk_usuario")
    private int fkUsuario;


    public Contrato(int fkUsuario) {
        descricao = "";
        dataInicio = null;
        dataFim = null;
        dataUltimaModif = null;
        horasContratadas = 0;
        this.fkUsuario = fkUsuario;
    }

    public Contrato(String descricao, LocalDate dataInicio, LocalDate dataFim,
                    int horasContratadas, int fkUsuario) {
        this.setDescricao(descricao);
        this.setDataInicio(dataInicio);
        this.setDataFim(dataFim);
        this.setHorasContratadas(horasContratadas);
        this.setFkUsuario(fkUsuario);
    }

    public Contrato(int id, String descricao, LocalDate dataInicio, LocalDate dataFim,
                    int horasContratadas, int fkUsuario) {
        this.setId(id);
        this.setDescricao(descricao);
        this.setDataInicio(dataInicio);
        this.setDataFim(dataFim);
        this.setHorasContratadas(horasContratadas);
        this.setFkUsuario(fkUsuario);
    }

    public Contrato(String descricao, LocalDate dataInicio, LocalDate dataFim,
                    LocalDateTime dataUltimaModif, int horasContratadas, int fkUsuario) {
        this.setDescricao(descricao);
        this.setDataUltimaModif(dataUltimaModif);
        this.setDataInicio(dataInicio);
        this.setDataFim(dataFim);
        this.setHorasContratadas(horasContratadas);
        this.setFkUsuario(fkUsuario);
    }

    public Contrato(int id, String descricao, LocalDate dataInicio, LocalDate dataFim,
                    LocalDateTime dataUltimaModif, int horasContratadas, int fkUsuario) {
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

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String contrato = "\nID:\t\t\t\t\t" + this.getId();
        contrato += "\nDescrição:\t\t\t" + this.getDescricao();
        contrato += "\nData Ult. Modif.:\t" + this.getDataUltimaModif().format(formatter);
        contrato += "\nData Início:\t\t" + this.getDataInicio();
        contrato += "\nData Fim:\t\t\t" + this.getDataFim();
        contrato += "\nHoras Contratadas:\t" + this.getHorasContratadas();
        contrato += "\nFK_usuario:\t\t\t" + this.getFkUsuario();
        return contrato;
    }
}
