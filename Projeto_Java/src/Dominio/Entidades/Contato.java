package Dominio.Entidades;

import Dominio.Enum.ETipoContato;

/**
 *
 * @author 20161BSI0314
 */
public class Contato {

    private int id;
    private String descricao;
    private ETipoContato tipo;
    private int fkUsuario;

    public Contato(ETipoContato tipo, String descricao){
        this.setTipo(tipo);
        this.setDescricao(descricao);
    }

    public Contato(int id, ETipoContato tipo, String descricao, int fkUsuario){
        this.setId(id);
        this.setTipo(tipo);
        this.setDescricao(descricao);
        this.setFkUsuario(fkUsuario);
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

    public void setTipo(ETipoContato tipo) {
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
        }

        else if (!Util.isStringValida(descricao)) {
            Util.throwExceptCampoVazio(this.getTipo().getNome());
        }
    }

    public int getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(int fkUsuario) {
        this.fkUsuario = fkUsuario;
    }
}
