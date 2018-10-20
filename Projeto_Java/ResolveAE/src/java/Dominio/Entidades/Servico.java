/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio.Entidades;

/**
 *
 * @author 20161BSI0314
 */
public class Servico {
    enum ETipoServico{
        BANCO_DE_DADOS,DESENVOLVIMENTO,DESING,GESTAO,INFRAESTRUTURA,SEGURANCA,SUPORTE_MANUTENCAO,REDES
    }
    
    private String titulo;
    private String descricao;
    private ETipoServico tipo;
    private SubTipo subtipoServico;
    private float valor;
    private int idAgendaPrestacao;
    private int idContrato;
    private int idSubTipo;
    private int idUsario;
    private int idBase;

    public SubTipo getSubtipoServico() {
        return subtipoServico;
    }

    public void setSubtipoServico(SubTipo subtipoServico) {
        this.subtipoServico = subtipoServico;
    }

    
    public int getIdBase() {
        return idBase;
    }

    public void setIdBase(int idBase) {
        this.idBase = idBase;
    }

    public ETipoServico getTipo() {
        return tipo;
    }

    public void setTipo(ETipoServico tipo) {
        this.tipo = tipo;
    }

    public int getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(int idContrato) {
        this.idContrato = idContrato;
    }

    public int getIdSubTipo() {
        return idSubTipo;
    }

    public void setIdSubTipo(int idSubTipo) {
        this.idSubTipo = idSubTipo;
    }

    public int getIdAgendaPrestacao() {
        return idAgendaPrestacao;
    }

    public void setIdAgendaPrestacao(int idAgendaPrestacao) {
        this.idAgendaPrestacao = idAgendaPrestacao;
    }

    public int getIdUsario() {
        return idUsario;
    }

    public void setIdUsario(int idUsario) {
        this.idUsario = idUsario;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
    
    
}
