/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author 20161BSI0314
 */
public class Cep {
    private String valorCep;

    public String getValorCep() {
        return valorCep;
    }

    public void setValorCep(String valorCep) {
        this.valorCep = valorCep;
    }
    
    private boolean validar() throws Exception{
        throw new Exception("NAO IMPLEMENTADO");
    } 
    public Endereco getEndercoByCep()throws Exception{
        throw new Exception("NAO IMPLEMENTADO");
    }
}
