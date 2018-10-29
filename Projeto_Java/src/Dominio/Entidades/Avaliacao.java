package Dominio.Entidades;

/**
 *
 * @author 20161BSI0314
 */
public class Avaliacao {

    private int id;
    private int nota;
    private int fkUsuario;
    private int fkServico;
    private Comentario comentario;

    public Avaliacao(){}

    public Avaliacao(int nota, int fkUsuario, int fkServico, Comentario comentario){
        this.setNota(nota);
        this.setComentario(comentario);
        this.setFkUsuario(fkUsuario);
        this.setFkServico(fkServico);
    }

    public Avaliacao(int id, int nota, int fkUsuario, int fkServico,
                     Comentario comentario){
        this.setId(id);
        this.setNota(nota);
        this.setComentario(comentario);
        this.setFkUsuario(fkUsuario);
        this.setFkServico(fkServico);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {

        if (nota < 1) Util.throwExceptNumeroInferior("nota", 1);

        this.nota = nota;
    }

    public int getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(int fkUsuario) {
        this.fkUsuario = fkUsuario;
    }

    public int getFkServico() {
        return fkServico;
    }

    public void setFkServico(int fkServico) {
        this.fkServico = fkServico;
    }

    public Comentario getComentario() {
        return comentario;
    }

    public void setComentario(Comentario comentario) {
        this.comentario = comentario;
    }
}
