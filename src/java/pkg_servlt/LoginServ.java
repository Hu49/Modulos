/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg_servlt;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pkg_persistencia.Persistencia;

/**
 *
 * @author Hu
 */
@WebServlet(name = "LoginServ", urlPatterns = {"/LoginServ"})
public class LoginServ extends HttpServlet {

    Persistencia port = new Persistencia();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String accion = request.getParameter("accion");
        switch (accion) {
            case "Login":
                String usuario = request.getParameter("txtusuario");
                String pass = request.getParameter("txtpass");
                Boolean respuesta = port.findUser(usuario, pass);
                if (respuesta == true){
                    HttpSession session = request.getSession();
                    session.setAttribute("usuario", usuario);
                    request.getRequestDispatcher("Modulos.jsp").forward(request, response);
                } else {
                    request.setAttribute("info", "Credenciales no válidas");
                    request.getRequestDispatcher("Login.jsp").forward(request, response);
                }
                break;
            case "Crear":
                request.getRequestDispatcher("crearUsuario.jsp").forward(request, response);
                break;
            case "Guardar":
                String usuario2 = request.getParameter("txtuser");
                String pass2 = request.getParameter("txtpass");
                String dato = port.crearUsuario("0",usuario2, pass2);
                request.setAttribute("info", dato);
                request.getRequestDispatcher("Login.jsp").forward(request, response);
                break;
            case "Regresar":
                request.getRequestDispatcher("Login.jsp").forward(request, response);
                break;
            default:
                throw new AssertionError();
        }
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
