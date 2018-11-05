///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package Infraestrutura.Postgre.DAO;
//
//import Dominio.Entidades.Experiencia;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// *
// * @author elmr
// */
//public class InfoProfissionalDAO implements IInfoProfissionalDAO{
//
//    @Override
//    public List<Experiencia> getExperiencias(int idUsuario) {
//        String query = "SELECT IP.* FROM Usuario AS U "
//                + "INNER JOIN Info_Profissional AS IP ON IP.fk_usuario = U.id "
//                + "WHERE IP.fk_tipo = 1 AND U.id ="+idUsuario+"";
//         //EXECUCAO DA QUERY
//        //FECHAR CONEXAO
//        List<Experiencia> exps = new ArrayList<>();
//        //LEITURA DO DATASET
//
//        return exps;
//    }
//
//    @Override
//    public List<Curso> getCursos(int idUsuario) {
//        String query = "SELECT IP.* FROM Usuario AS U "
//                + "INNER JOIN Info_Profissional AS IP ON IP.fk_usuario = U.id "
//                + "WHERE IP.fk_tipo = 2 AND U.id ="+idUsuario+"";
//         //EXECUCAO DA QUERY
//        //FECHAR CONEXAO
//        List<Curso> cursos = new ArrayList<>();
//        //LEITURA DO DATASET
//
//        return cursos;
//    }
//
//    @Override
//    public void insertCurso(int idUsuario, Curso curso) {
//        String query = "INSERT INTO Info_Profissional (descricao,data_inicio,data_fim,fk_usuario,fk_tipo_info_profissional) "
//                + "VALUES "
//                + "("+curso.getNomeCurso()+", "+curso.getDataInicio()+", "+curso.getDataFim()+", "+curso.getTipoInfo()+","+idUsuario+") ";
//         //EXECUCAO DA QUERY
//        //FECHAR CONEXAO
//    }
//
//    @Override
//    public void insertExperiencia(int idUsuario, Experiencia exp) {
//        String query = "INSERT INTO Info_Profissional (descricao,data_inicio,data_fim,fk_usuario,fk_tipo_info_profissional) "
//                + "VALUES "
//                + "("+exp.getTitulo()+", "+exp.getDataInicio()+", "+exp.getDataFim()+", "+exp.getTipoInfo()+","+idUsuario+") ";
//         //EXECUCAO DA QUERY
//        //FECHAR CONEXAO
//    }
//
//    @Override
//    public void updateCurso(Curso curso) {
//        String query = "UPDATE INTO Info_Profissional SET descricao = "+curso.getNomeCurso()+"data_inicio = "+curso.getDataInicio() +",data_fim = "+curso.getDataFim()+",fk_usuario,fk_tipo_info_profissional) "
//                + "WHERE fk_usuario = "+curso.getIdUsuario()+" AND id"+curso.getIdBase()+" ";
//         //EXECUCAO DA QUERY
//        //FECHAR CONEXAO
//    }
//
//    @Override
//    public void updateExperiencia(Experiencia exp) {
//        String query = "UPDATE INTO Info_Profissional SET descricao = "+exp.getTitulo()+"data_inicio = "+exp.getDataInicio() +",data_fim = "+exp.getDataFim()+",fk_usuario,fk_tipo_info_profissional) "
//                + "WHERE fk_usuario = "+exp.getIdUsuario()+" AND id"+exp.getIdBase()+" ";
//         //EXECUCAO DA QUERY
//        //FECHAR CONEXAO
//    }
//
//}
