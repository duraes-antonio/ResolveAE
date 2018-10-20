package Dominio.Entidades;

/**
 *
 * @author 20161BSI0314
 */
public class MidiaSocial extends MeioContato {
    enum ETipoMidia{
        FACEBOOK,INSTAGRAM,LINKEDIN,SKYPE,TWITTER,LATTES,OUTROS
    }
    private String link;
    private int idUsario;
    private ETipoMidia tipoMidia;
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

    public ETipoMidia getTipoMidia() {
        return tipoMidia;
    }

    public void setTipoMidia(ETipoMidia tipoMidia) {
        this.tipoMidia = tipoMidia;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
