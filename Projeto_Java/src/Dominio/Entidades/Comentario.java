package Dominio.Entidades;

import javax.persistence.*;

@Entity
@Table(name = "comentario")
public class Comentario {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "comentario", length = 500)
    private String comentario;

    @Column(name = "fk_avaliacao")
    private int fkAvaliacao;


    public Comentario() { }

    public Comentario(String comentario, int fkAvalicao) {
        this.setComentario(comentario);
        this.setFkAvalicao(fkAvalicao);
    }

    public Comentario(int id, String comentario, int fkAvalicao) {
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
        return fkAvaliacao;
    }

    public void setFkAvalicao(int fkAvalicao) {
        this.fkAvaliacao = fkAvalicao;
    }

    @Override
    public String toString() {
        return String.format(
                "ID:\t\t\t\t%d;\nComentário:\t\t'%s';\nFK_avaliacao:\t%d;",
                this.getId(), this.getComentario(), this.getFkAvalicao());
    }
}
