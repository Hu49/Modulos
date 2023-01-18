/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg_servlt;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pkg_entidad.*;
import pkg_persistencia.Persistencia;
/**
 *
 * @author hudie
 */
@WebServlet(name = "ActividadServ", urlPatterns = {"/ActividadServ"})
public class ActividadServ extends HttpServlet {

    Persistencia port = new Persistencia();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        HttpSession session = request.getSession(false);
        response.setContentType("text/html;charset=UTF-8");
        String accion = request.getParameter("accion");
        String dato = port.listarActividad();
        String[] elemento = dato.split("/");
        List<Actividad> datos = new ArrayList<Actividad>();
        switch (accion) {
            case "Listar":
                for ( String v : elemento) {
                    Actividad ac = new Actividad();
                    String[] listaAc = v.split(";");
                    ac.setCodigo(listaAc[0]);
                    ac.setNombre(listaAc[1]);
                    datos.add(ac);
                }
                session.setAttribute("actividad", datos);
                response.sendRedirect("Actividad.jsp");
                break;
            case "Nuevo":
                response.sendRedirect("agregarActividad.jsp");
                break;
            case "Guardar":
                String nombre = request.getParameter("txtnombre");
                port.insertarActividad("0",nombre);
                response.sendRedirect("ActividadServ?accion=Listar");
                break;
            case "Editar":
                String ide = request.getParameter("id");
                Actividad pe = new Actividad();
                String info = port.mostrarActividad(ide);
                String[] infoac = info.split(";");
                pe.setCodigo(infoac[0]);
                pe.setNombre(infoac[1]);
                session.setAttribute("actividad", pe);
                response.sendRedirect("editarActividad.jsp");
                break;
            case "Actualizar":
                String codigo1 = request.getParameter("txtcodigo");
                String nombre1 = request.getParameter("txtnombre");
                port.actualizarActividad(codigo1, nombre1);
                response.sendRedirect("ActividadServ?accion=Listar");
                break;
            case "Eliminar":
                String id2 = request.getParameter("id");
                port.eliminarActividad(id2);
                response.sendRedirect("ActividadServ?accion=Listar");
                break;
            case "Regresar":
                response.sendRedirect("ActividadServ?accion=Listar");
                break;
            case "Mostrar":
                String ide2 = request.getParameter("id");
                Actividad pe2 = new Actividad();
                String info2 = port.mostrarActividad(ide2);
                String[] infoac2 = info2.split(";");
                pe2.setCodigo(infoac2[0]);
                pe2.setNombre(infoac2[1]);
                session.setAttribute("actividad", pe2);
                response.sendRedirect("mostrarActividad.jsp");
                break;   
            case "Buscar":
                String ide3 = request.getParameter("buscarActividad");
                Actividad pe3 = new Actividad();
                String info3 = port.mostrarActividad(ide3);
                String[] infoac3 = info3.split(";");
                pe3.setCodigo(infoac3[0]);
                pe3.setNombre(infoac3[1]);
                session.setAttribute("actividad", pe3);
                response.sendRedirect("mostrarActividad.jsp");
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ActividadServ.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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
        
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ActividadServ.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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
