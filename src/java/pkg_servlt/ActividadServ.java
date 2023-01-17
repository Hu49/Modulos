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
import pkg_entidad.*;
import pkg_webservice.Exception_Exception;
import pkg_webservice.WebServiceServer;
import pkg_webservice.WebServiceServer_Service;

/**
 *
 * @author hudie
 */
@WebServlet(name = "ActividadServ", urlPatterns = {"/ActividadServ"})
public class ActividadServ extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception_Exception {
        response.setContentType("text/html;charset=UTF-8");
        WebServiceServer_Service service1 = new WebServiceServer_Service();
        WebServiceServer port = service1.getWebServiceServerPort();
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
                request.setAttribute("actividad", datos);
                request.getRequestDispatcher("Actividad.jsp").forward(request, response);
                break;
            case "Nuevo":
                request.getRequestDispatcher("agregarActividad.jsp").forward(request, response);
                break;
            case "Guardar":
                String nombre = request.getParameter("txtnombre");
                port.insertarActividad("0",nombre);
                request.getRequestDispatcher("ActividadServ?accion=Listar").forward(request, response);
                break;
            case "Editar":
                String ide = request.getParameter("id");
                Actividad pe = new Actividad();
                String info = port.mostrarActividad(ide);
                String[] infoac = info.split(";");
                pe.setCodigo(infoac[0]);
                pe.setNombre(infoac[1]);
                request.setAttribute("actividad", pe);
                request.getRequestDispatcher("editarActividad.jsp").forward(request, response);
                break;
            case "Actualizar":
                String codigo1 = request.getParameter("txtcodigo");
                String nombre1 = request.getParameter("txtnombre");
                port.actualizarActividad(codigo1, nombre1);
                request.getRequestDispatcher("ActividadServ?accion=Listar").forward(request, response);
                break;
            case "Eliminar":
                String id2 = request.getParameter("id");
                port.eliminarActividad(id2);
                request.getRequestDispatcher("ActividadServ?accion=Listar").forward(request, response);
                break;
            case "Regresar":
                for ( String v : elemento) {
                    Actividad ac2 = new Actividad();
                    String[] listaAc = v.split(";");
                    ac2.setCodigo(listaAc[0]);
                    ac2.setNombre(listaAc[1]);
                    datos.add(ac2);
                }
                request.setAttribute("actividad", datos);
                request.getRequestDispatcher("Actividad.jsp").forward(request, response);
                break;
            case "Mostrar":
                String ide2 = request.getParameter("id");
                Actividad pe2 = new Actividad();
                String info2 = port.mostrarActividad(ide2);
                String[] infoac2 = info2.split(";");
                pe2.setCodigo(infoac2[0]);
                pe2.setNombre(infoac2[1]);
                request.setAttribute("actividad", pe2);
                request.getRequestDispatcher("mostrarActividad.jsp").forward(request, response);
                break;   
            case "Buscar":
                String ide3 = request.getParameter("buscarActividad");
                Actividad pe3 = new Actividad();
                String info3 = port.mostrarActividad(ide3);
                String[] infoac3 = info3.split(";");
                pe3.setCodigo(infoac3[0]);
                pe3.setNombre(infoac3[1]);
                request.setAttribute("actividad", pe3);
                request.getRequestDispatcher("mostrarActividad.jsp").forward(request, response);
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
        } catch (Exception_Exception ex) {
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
        } catch (Exception_Exception ex) {
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
