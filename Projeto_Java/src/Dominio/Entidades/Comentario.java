package Dominio.Entidades;

public class Comentario {

    private int id;
    private String comentario;

    public Comentario(){}

    public Comentario(String comentario){
        this.setComentario(comentario);
    }

    public Comentario(int id, String comentario){
        this.setId(id);
        this.setComentario(comentario);
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {

        if (id < 1) Util.throwExceptNumeroInferior("id", 1);

        this.id = id;
    }

    public String getComentario() {
        return this.comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
