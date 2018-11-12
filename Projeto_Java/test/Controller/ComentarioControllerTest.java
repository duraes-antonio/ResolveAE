package Controller;

import Dominio.Entidades.Comentario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class ComentarioControllerTest {
    ComentarioController controler;
    Comentario comentario;
    List<Comentario> comentarios;
    int idComentario;
    int idServico;
    int idAvaliacao;
    int idUser;
    @BeforeEach
    void setUp(){
        controler = new ComentarioController();
        comentario = new Comentario();
        idComentario = 1;
        idServico = 11;
        idAvaliacao = 1;
        idUser = 5;
    }

    @Test
    void searchComentarioById() {
        comentario = controler.searchById(idComentario);
        System.out.println(comentario);
    }

    @Test
    void searchComentarioByIdServico() {
        comentarios = controler.searchByIdServico(idServico);
        for (Comentario output: comentarios){
            System.out.println(output);
        }
    }

    @Test
    void searchComentarioByIdAvaliacao() {
        comentario = controler.searchByIdAvaliacao(idAvaliacao);
        System.out.println(comentario);
    }

    @Test
    void searchComentariosByIdUser() {
        comentarios = controler.searchByIdUser(idUser);
        for(Comentario output:comentarios){
            System.out.println(output);
        }
    }

    @Test
    void searchAllComentarios() {
        comentarios = controler.searchAll();
        for(Comentario output:comentarios){
            System.out.println(output);
            System.out.println("--------------");
        }
    }
}