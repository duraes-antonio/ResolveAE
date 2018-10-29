package Dominio.Entidades;

public class Comentario {

    private int id;
    private String comentario;
    private int fkAvalicao;

    public Comentario(String comentario, int fkAvalicao){
        this.setComentario(comentario);
        this.setFkAvalicao(fkAvalicao);
    }

    public Comentario(int id, String comentario, int fkAvalicao){
        this.setId(id);
        this.setComentario(comentario);
        this.setFkAvalicao(fkAvalicao);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getFkAvalicao() {
        return fkAvalicao;
    }

    public void setFkAvalicao(int fkAvalicao) {
        this.fkAvalicao = fkAvalicao;
    }

    @Override
    public String toString() {
        return String.format(
                "ID:\t\t\t\t%d;\nComentário:\t\t'%s';\nFK_avaliacao:\t%d;",
                this.getId(), this.getComentario(), this.getFkAvalicao());
    }
}
