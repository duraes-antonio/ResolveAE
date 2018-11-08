package Dominio.Entidades;

import javax.persistence.*;

@Entity
@Table(name = "avaliacao")
public class Avaliacao {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nota")
    private int nota;

    @Column(name = "fk_usuario")
    private int fkUsuario;

    @Column(name = "fk_servico")
    private int fkServico;

    @OneToOne(fetch = FetchType.LAZY, targetEntity = Comentario.class)
    @JoinColumn(name = "id", referencedColumnName = "id")
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

    @Override
    public String toString() {
        return String.format(
                "ID:\t\t\t\t%d;\nNota:\t\t'%s';\nFK_usuario:\t%d;\nFK_servico:\t%d;" +
                        "\n\n---COMENTÁRIO---\n",
                this.getId(), this.getNota(), this.getFkUsuario(), getFkServico()) + (comentario != null ? comentario.toString() : "~NÃO HÁ COMENTÁRIO!");
    }
}
