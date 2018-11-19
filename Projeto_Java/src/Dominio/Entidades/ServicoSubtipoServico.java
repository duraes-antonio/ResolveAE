package Dominio.Entidades;

public class ServicoSubtipoServico {

    private int id;
    private int fkServico;
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
}
