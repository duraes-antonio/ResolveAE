package Dominio.Entidades;

/**
 *
 * @author 20161BSI0314
 */
public class Avaliacao {

    private int id;
    private int nota;
    private String comentario;

    public Avaliacao(){}

    public Avaliacao(int nota, String comentario){
        this.setNota(nota);
        this.setComentario(comentario);
    }

    public Avaliacao(int id, int nota, String comentario){
        this.setId(id);
        this.setNota(nota);
        this.setComentario(comentario);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {

        if (id < 1) Util.throwExceptNumeroInferior("id", 1);

        this.id = id;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
