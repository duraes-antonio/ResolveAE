package Dominio.Entidades;

import Dominio.Enum.ESubtipoServico;
import Dominio.Enum.ETipoServico;

import java.util.List;

/**
 * @author 20161BSI0314
 */
public class Servico {

    private int id;
    private String titulo;
    private String descricao;
    private ETipoServico tipo;
    private List<ESubtipoServico> subtipos;
    private double valor;
    private int fkUsuario;
    private int fkTipoServico;
    private int fkContrato;


    public Servico() {
    }

    public Servico(String titulo, String descricao, List<ESubtipoServico> subtipos,
                   double valor, int fkTipoServico, int fkUsuario, int fkContrato) {
        this.setTitulo(titulo);
        this.setDescricao(descricao);
        this.setSubtipoServico(subtipos);
        this.setValor(valor);
        this.setFkTipoServico(fkTipoServico);
        this.setFkUsuario(fkUsuario);
        this.setFkContrato(fkContrato);
    }

    public Servico(int id, String titulo, String descricao,
                   List<ESubtipoServico> subtipos, double valor, int fkTipoServico,
                   int fkUsuario, int fkContrato) {
        this.setId(id);
        this.setTitulo(titulo);
        this.setDescricao(descricao);
        this.setSubtipoServico(subtipos);
        this.setValor(valor);
        this.setFkTipoServico(fkTipoServico);
        this.setFkUsuario(fkUsuario);
        this.setFkContrato(fkContrato);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {

        if (!Util.isStringValida(titulo)) Util.throwExceptCampoVazio("título");

        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {

        if (!Util.isStringValida(descricao)) Util.throwExceptCampoVazio("descrição");

        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {

        if (valor < 0) Util.throwExceptNumeroInferior("preço", 0);

        this.valor = valor;
    }

    public ETipoServico getTipo() {
        return tipo;
    }

    public void setTipo(ETipoServico tipo) {
        this.tipo = tipo;
    }

    public List<ESubtipoServico> getSubtipos() {
        return subtipos;
    }

    public void setSubtipoServico(List<ESubtipoServico> subtipos) {
        this.subtipos = subtipos;
    }

    public int getFkUsuario() {
        return fkUsuario;
    }

    private void setFkUsuario(int fkUsuario) {
        this.fkUsuario = fkUsuario;
    }

    public int getFkTipoServico() {
        return fkTipoServico;
    }

    public void setFkTipoServico(int fkTipoServico) {
        this.fkTipoServico = fkTipoServico;
        this.tipo = ETipoServico.getById(fkTipoServico);
    }

    public int getFkContrato() {
        return fkContrato;
    }

    public void setFkContrato(int fkContrato) {
        this.fkContrato = fkContrato;
    }
}
