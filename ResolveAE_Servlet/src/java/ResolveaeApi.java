/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.json.JSONObject;
import Controller.ComentarioController;
import Controller.ControllerFactory;
import Controller.Interfaces.IController;
import Dominio.Entidades.Comentario;
import Infraestrutura.Postgre.DAO.ComentarioDAO;
import Infraestrutura.Postgre.Util.Persistencia;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author elmr
 */
public class ResolveaeApi extends HttpServlet {

    private String model = null;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        try{
            requestHandler(request);
            /* TODO output your page here. You may use following sample code. */
            if(request.getMethod().equalsIgnoreCase("GET")){
                IController controler = ControllerFactory.createController("comentario");
                List<Comentario> tt2 = controler.searchAll();
                JSONObject objJson = new JSONObject();
                for (Comentario inset: tt2){
                    
                    objJson.put("ID",inset.getId());
                    objJson.put("Comentario",inset.getComentario());
                    objJson.put("FK_Avaliacao",inset.getFkAvalicao());
                    out.println(objJson.toString());
                    out.println("<br>");
                }
                
                
            }
            else if (request.getMethod().equalsIgnoreCase("POST")){
                out.println("METODO POST");
            }
            else{
                out.print("NAO EH POST E NEM GET");
            }
        }
        catch(Exception erro){
            out.println("Aconteceu um problema: "+erro.getMessage());
        }
        finally{
            out.close();
        }
    }
    //VAI GERAR O CONTROLLER COM BASE NA REQUISICAO
    private void requestHandler(HttpServletRequest request){
        String path = request.getRequestURI().substring(request.getContextPath().length());
        System.out.println(path.replace("/", ""));
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
