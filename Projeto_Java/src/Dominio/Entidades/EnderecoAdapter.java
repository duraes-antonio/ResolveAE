package Dominio.Entidades;

import Dominio.Enum.EEstado;

import javax.persistence.*;

public class EnderecoAdapter extends Endereco {

    /* Atributos usados apenas p/ Hibernate, não é o mais adequado, porém,
     * como já há outros componentes usando a classe endereço, tal recurso
     * evita mudanças drásticas nesses componentes.
     */
    @OneToOne(fetch = FetchType.LAZY)
    private Bairro bairroObj;

    @Column(name = "cep")
    private int cepNumero;

    @Column(name = "fk_bairro")
    private int fkBairro;

    EnderecoAdapter() { }

    @PostLoad
    private void setBairroNome() {
        setBairro(this.bairroObj.getNome());
    }

    @PostLoad
    private void setCidadeNome() {
        setBairro(this.bairroObj.getCidade().getNome());
    }

    @PostLoad
    private void setEstado() {
        setEstado(EEstado.getById(this.bairroObj.getCidade().getFkEstado()).getNomeExtenso());
    }
}
