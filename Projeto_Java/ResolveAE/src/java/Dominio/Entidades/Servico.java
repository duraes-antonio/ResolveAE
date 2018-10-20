package Dominio.Entidades;

import Dominio.Enum.ESubtipoServico;
import Dominio.Enum.ETipoServico;

import java.util.List;

/**
 *
 * @author 20161BSI0314
 */
public class Servico {

    private int id;
    private String titulo;
    private String descricao;
    private ETipoServico tipo;
    private List<ESubtipoServico> subtipos;
    private double valor;

    public Servico(){}

    public Servico(String titulo, String descricao, ETipoServico tipoServico,
                   List<ESubtipoServico> subtipos, double valor) {
        this.setTitulo(titulo);
        this.setDescricao(descricao);
        this.setTipo(tipoServico);
        this.setSubtipoServico(subtipos);
        this.setValor(valor);
    }

    public Servico(int id, String titulo, String descricao, ETipoServico tipoServico,
                   List<ESubtipoServico> subtipos, double valor) {
        this.setId(id);
        this.setTitulo(titulo);
        this.setDescricao(descricao);
        this.setTipo(tipoServico);
        this.setSubtipoServico(subtipos);
        this.setValor(valor);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {

        if (id < 1) Util.throwExceptNumeroInferior("id", 1);

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
        return this.descricao;
    }

    public void setDescricao(String descricao) {

        if (!Util.isStringValida(descricao)) Util.throwExceptCampoVazio("descrição");

        this.descricao = descricao;
    }

    public double getValor() {
        return this.valor;
    }

    public void setValor(double valor) {

        if (valor < 0) Util.throwExceptNumeroInferior("preço", 0);

        this.valor = valor;
    }

    public ETipoServico getTipo() {
        return this.tipo;
    }

    public void setTipo(ETipoServico tipo) {
        this.tipo = tipo;
    }

    public List<ESubtipoServico> getSubtipos() {
        return this.subtipos;
    }

    public void setSubtipoServico(List<ESubtipoServico> subtipos) {
        this.subtipos = subtipos;
    }
}
