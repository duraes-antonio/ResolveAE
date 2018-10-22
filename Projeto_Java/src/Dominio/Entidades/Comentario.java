package Dominio.Entidades;

public class Comentario {

    private int id;
    private String comentario;
    private int fkAvalicao;

    public Comentario(){}

    public Comentario(String comentario, int fkAvalicao){
        this.setComentario(comentario);
    }

    public Comentario(int id, String comentario, int fkAvalicao){
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

    public int getFkAvalicao() {
        return this.fkAvalicao;
    }

    public void setFkAvalicao(int fkAvalicao) {

        if (fkAvalicao < 1) Util.throwExceptNumeroInferior("id", 1);

        this.fkAvalicao = fkAvalicao;
    }

    @Override
    public String toString() {
        return String.format(
                "ID:\t\t\t\t%d;\nComentário:\t\t'%s';\nFK_avaliacao:\t%d;",
                this.getId(), this.getComentario(), this.getFkAvalicao());
    }
}
