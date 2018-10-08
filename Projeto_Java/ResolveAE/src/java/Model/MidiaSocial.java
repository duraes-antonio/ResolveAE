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
public class MidiaSocial extends MeioContato {
    //enum
    private String link;
    private int idUsario;
    private int tipoMidia;
    private int idMidia;

    public int getIdMidia() {
        return idMidia;
    }

    public void setIdMidia(int idMidia) {
        this.idMidia = idMidia;
    }

    public int getIdUsario() {
        return idUsario;
    }

    public void setIdUsario(int idUsario) {
        this.idUsario = idUsario;
    }

    public int getTipoMidia() {
        return tipoMidia;
    }

    public void setTipoMidia(int tipoMidia) {
        this.tipoMidia = tipoMidia;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
