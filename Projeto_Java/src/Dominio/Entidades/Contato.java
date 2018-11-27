package Dominio.Entidades;

import Dominio.Enum.ETipoContato;
import jdk.nashorn.internal.ir.annotations.Ignore;

import javax.persistence.*;

@Entity
@Table(name = "contato")
public class Contato {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "descricao")
    private String descricao;

    @Ignore
    private ETipoContato tipo;

    @Column(name = "fk_usuario")
    private int fkUsuario;

    @Column(name = "fk_tipo_contato")
    @JoinColumn(referencedColumnName = "id")
    private int fkTipoContato;

    public Contato() {
    }

    public Contato(String descricao, int fkUsuario, int fkTipoContato) {
        this.setDescricao(descricao);
        this.setFkUsuario(fkUsuario);
        this.setFkTipoContato(fkTipoContato);
    }

    public Contato(int id, String descricao, int fkUsuario, int fkTipoContato) {
        this.setId(id);
        this.setDescricao(descricao);
        this.setFkUsuario(fkUsuario);
        this.setFkTipoContato(fkTipoContato);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ETipoContato getTipo() {
        return tipo;
    }

    private void setTipo(ETipoContato tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {

        //Se for um contato do tipo numérico, verifique se só há dígitos na descrição;
        if (this.tipo == ETipoContato.CELULAR || this.tipo == ETipoContato.TELEFONE
                || this.tipo == ETipoContato.TELEGRAM || this.tipo == ETipoContato.WHATSAPP) {

            //O tamanho do núm. deve ser 10 ou 11 (2 p/ DDD e 8/9 p/ o número);
            //8 para telefone fixo E 9 para telefone celular
            if (descricao.length() != 10 && descricao.length() != 11) {
                Util.throwExceptQtdInvalida("caracteres", this.getTipo().getNome());
            }

            //Se possuir a qtd. correta de chars, verifique se são dígitos;
            else if (!Util.isNumero(descricao)) {
                Util.throwExceptCampoNumerico(this.getTipo().getNome());
            }
        } else if (!Util.isStringValida(descricao)) {
            Util.throwExceptCampoVazio(this.getTipo().getNome());
        }

        this.descricao = descricao;
    }

    public int getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(int fkUsuario) {
        this.fkUsuario = fkUsuario;
    }

    public int getFkTipoContato() {
        return fkTipoContato;
    }

    public void setFkTipoContato(int fkTipoContato) {
        this.fkTipoContato = fkTipoContato;
        this.atualizarTipoContato();
    }

    @PostLoad
    private void atualizarTipoContato() {
        this.setTipo(ETipoContato.getTipoContatoPorId(this.getFkTipoContato()));
    }

    @Override
    public String toString() {
        return String.format(
                "ID:\t\t\t\t\t%d;" +
                        "\nDescrição:\t\t\t%s;" +
                        "\nTipo:\t\t\t\t%s;" +
                        "\nFK_usuario:\t\t\t%d;" +
                        "\nFK_tipo_contato:\t%d",
                id, descricao, tipo.getNome(), fkUsuario, fkTipoContato);
    }

}
