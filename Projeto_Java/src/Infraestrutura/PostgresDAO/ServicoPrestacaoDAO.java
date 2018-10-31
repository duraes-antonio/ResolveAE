///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package Infraestrutura.PostgresDAO;
//
//import Dominio.Entidades.Servico;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// *
// * @author elmr
// */
//public class ServicoPrestacaoDAO implements IServicoPrestacaoDAO{
//
//    @Override
//    public List<Servico> getServicosByIdUser(int idUsuario) {
//        String query = "SELECT * FROM Servico_Prestacao "
//                + "WHERER fk_usuario ="+idUsuario+" ";
//        //EXECUCAO DA QUERY
//        //FECHAMENTO DA CONEXAO
//        List<Servico> srvs = new ArrayList<>();
//        //LEITURA DO DATASET
//        return srvs;
//    }
//
//    @Override
//    public void insertServico(Servico serv) {
//        String query = "INSERT INTO Servico_Prestacao (valor,titulo,fk_usuario,fk_agenda_prestacao,fk_tipo_servico,fk_subtipo_servico,fk_contrato) "
//                + "VALUES "
//                + "( "+serv.getValor()+", "+serv.getTitulo()+", "+serv.getIdUsario()+", "+serv.getIdAgendaPrestacao()+", "+serv.getTipo() +", "+serv.getIdSubTipo()+", "+serv.getIdContrato()+" )";
//        //EXECUCAO DA QUERY
//        //FECHAMENTO DA CONEXAO
//    }
//
//    @Override
//    public void updateServico(Servico serv) {
//        String query = "UPDATE Servico_Prestacao SET  valor= "+serv.getValor()+", titulo="+serv.getTitulo()+", fk_usuario = "+serv.getIdUsario()+" , fk_agenda_prestacao = "+ serv.getIdAgendaPrestacao()+""
//                + ", fk_tipo_servico = "+serv.getTipo()+", fk_subtipo_servico = "+serv.getIdSubTipo()+", fk_contrato = "+serv.getIdContrato()+" "
//                + "WHERE id = "+serv.getIdBase()+" ";
//    }
//
//}
