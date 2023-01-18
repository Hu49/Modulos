/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg_servlt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pkg_entidad.Activo;
import pkg_persistencia.Persistencia;

/**
 *
 * @author hudie
 */
@WebServlet(name = "ActivoServ", urlPatterns = {"/ActivoServ"})
public class ActivoServ extends HttpServlet {

    Persistencia port = new Persistencia();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String accion = request.getParameter("accion");
        String dato = port.listarActivo();
        String[] elemento = dato.split("/");
        List<Activo> datos = new ArrayList<Activo>();
        switch (accion) {
            case "Listar":
                for ( String v : elemento) {
                    Activo ac = new Activo();
                    String[] listaAc = v.split(";");
                    ac.setCodigo(listaAc[0]);
                    ac.setNombre(listaAc[1]);
                    ac.setFechaAdq(listaAc[2]);
                    datos.add(ac);
                }
                request.setAttribute("activo", datos);
                request.getRequestDispatcher("Activo.jsp?").forward(request, response);
                break;
            case "Nuevo":
                request.getRequestDispatcher("agregarActivo.jsp").forward(request, response);
                break;
            case "Guardar":
                String nombrea = request.getParameter("txtnombre");
                String fechaAdqa = request.getParameter("txtfechaAdq");
                port.insertarActivo("0", nombrea, fechaAdqa);
                request.getRequestDispatcher("ActivoServ?accion=Listar").forward(request, response);
                break;
            case "Editar":
                String ide = request.getParameter("id");
                Activo pe = new Activo();
                String info = port.mostrarActivo(ide);
                String[] infoac = info.split(";");
                pe.setCodigo(infoac[0]);
                pe.setNombre(infoac[1]);
                pe.setFechaAdq(infoac[2]);
                request.setAttribute("activo", pe);
                request.getRequestDispatcher("editarActivo.jsp").forward(request, response);
                break;
            case "Actualizar":
                String codigo1 = request.getParameter("txtcodigo");
                String nombre1 = request.getParameter("txtnombre");
                String fechaAdq1 = request.getParameter("txtfechaAdq");
                port.actualizarActivo(codigo1, nombre1, fechaAdq1);
                request.getRequestDispatcher("ActivoServ?accion=Listar").forward(request, response);
                break;
            case "Eliminar":
                String id2 = request.getParameter("id");
                port.eliminarActivo(id2);
                request.getRequestDispatcher("ActivoServ?accion=Listar").forward(request, response);
                break;
            case "Regresar":
                for ( String v : elemento) {
                    Activo ac = new Activo();
                    String[] listaAc = v.split(";");
                    ac.setCodigo(listaAc[0]);
                    ac.setNombre(listaAc[1]);
                    ac.setFechaAdq(listaAc[2]);
                    datos.add(ac);
                }
                request.setAttribute("activo", datos);
                request.getRequestDispatcher("Activo.jsp").forward(request, response);
                break;
            case "Mostrar":
                String ide2 = request.getParameter("id");
                Activo pe2 = new Activo();
                String info2 = port.mostrarActivo(ide2);
                String[] infoac2 = info2.split(";");
                pe2.setCodigo(infoac2[0]);
                pe2.setNombre(infoac2[1]);
                pe2.setFechaAdq(infoac2[2]);
                request.setAttribute("activo", pe2);
                request.getRequestDispatcher("mostrarActivo.jsp").forward(request, response);
                break;
            case "Buscar":
                String ide3 = request.getParameter("buscarActivo");
                Activo pe3 = new Activo();
                String info3 = port.mostrarActivo(ide3);
                String[] infoac3 = info3.split(";");
                pe3.setCodigo(infoac3[0]);
                pe3.setNombre(infoac3[1]);
                pe3.setFechaAdq(infoac3[2]);
                request.setAttribute("activo", pe3);
                request.getRequestDispatcher("mostrarActivo.jsp").forward(request, response);
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
