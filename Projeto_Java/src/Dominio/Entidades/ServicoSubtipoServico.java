package Dominio.Entidades;

import javax.persistence.*;

@Entity
@Table(name = "servico_subtipo_servico")
public class ServicoSubtipoServico implements Comparable<ServicoSubtipoServico> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "fk_servico")
    private int fkServico;

    @Column(name = "fk_subtipo_servico")
    private int fkSubtipoServico;


    public ServicoSubtipoServico() {};

    public ServicoSubtipoServico(int fkServico, int fkSubtipoServico) {
        this.setFkServico(fkServico);
        this.setFkSubtipoServico(fkSubtipoServico);
    }

    public ServicoSubtipoServico(int id, int fkServico, int fkSubtipoServico) {
        this.setId(id);
        this.setFkServico(fkServico);
        this.setFkSubtipoServico(fkSubtipoServico);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFkServico() {
        return fkServico;
    }

    public void setFkServico(int fkServico) {
        this.fkServico = fkServico;
    }

    public int getFkSubtipoServico() {
        return fkSubtipoServico;
    }

    public void setFkSubtipoServico(int fkSubtipoServico) {
        this.fkSubtipoServico = fkSubtipoServico;
    }

    @Override
    public int compareTo(ServicoSubtipoServico servicoSubtipoServico) {
        return Integer.compare(this.getId(), servicoSubtipoServico.getId());
    }
}
