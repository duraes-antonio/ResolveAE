/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author 20161BSI0314
 */
public class Telefone extends MeioContato{
    //enum
    private String numero;
    private int idUsario;
    private int tipoContato;
    private int idTel;

    public int getIdTel() {
        return idTel;
    }

    public void setIdTel(int idTel) {
        this.idTel = idTel;
    }

    public int getIdUsario() {
        return idUsario;
    }

    public void setIdUsario(int idUsario) {
        this.idUsario = idUsario;
    }

    public int getTipoContato() {
        return tipoContato;
    }

    public void setTipoContato(int tipoContato) {
        this.tipoContato = tipoContato;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
