import Controller.ControllerFactory;
import Controller.Interfaces.IController; 
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author elmr
 */
public class ResolveaeApi extends HttpServlet {

    //ATRIBUTO
    private IController controller = null;
    private String controllerName = null;
    private String jsonResult = null;
    
    //METODODS
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        try{
            requestHandler(request);
            
            if(request.getMethod().equalsIgnoreCase("GET")){
                this.jsonResult = this.controller.executeMethodGet(request.getParameterMap());
                out.println(this.jsonResult);
            }
            
            else if (request.getMethod().equalsIgnoreCase("POST")){
                this.controller.executeMethodPost(request.getParameterMap());
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
        this.controllerName = (path.replace("/", ""));
        this.controller = ControllerFactory.createController(this.controllerName);
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
