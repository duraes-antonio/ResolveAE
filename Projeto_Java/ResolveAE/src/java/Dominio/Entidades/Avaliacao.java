/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio.Entidades;

/**
 *
 * @author 20161BSI0314
 */
public class Avaliacao {
    private float nota;
    private String comentario;
    private int idUsuario;
    private int idServicoPrestado;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdServicoPrestado() {
        return idServicoPrestado;
    }

    public void setIdServicoPrestado(int idServicoPrestado) {
        this.idServicoPrestado = idServicoPrestado;
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
