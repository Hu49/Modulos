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
import pkg_entidad.TipoCuenta;
import pkg_persistencia.PersistenciaContabilidad;


/**
 *
 * @author hudie
 */
@WebServlet(name = "TipoCuentaServ", urlPatterns = {"/TipoCuentaServ"})
public class TipoCuentaServ extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PersistenciaContabilidad port = new PersistenciaContabilidad();
        String accion = request.getParameter("accion");
        String dato = port.listarTipoCuenta();
        String[] elemento = dato.split("/");
        List<TipoCuenta> datos = new ArrayList<TipoCuenta>();
        switch (accion) {
            case "Listar":
                for ( String v : elemento) {
                    TipoCuenta ac = new TipoCuenta();
                    String[] listaAc = v.split(";");
                    ac.setCodigo(listaAc[0]);
                    ac.setNombre(listaAc[1]);
                    datos.add(ac);
                }
                request.setAttribute("tipoCuenta", datos);
                request.getRequestDispatcher("TipoCuenta.jsp?").forward(request, response);
                break;
            case "Nuevo":
                request.getRequestDispatcher("agregarTipoCuenta.jsp").forward(request, response);
                break;
            case "Guardar":
                String nombrea = request.getParameter("txtnombre");
                int index=elemento.length+1;
                port.insertarTipoCuenta(""+index, nombrea);
                request.getRequestDispatcher("TipoCuentaServ?accion=Listar").forward(request, response);
                break;
            case "Editar":
                String ide = request.getParameter("id");
                TipoCuenta pe = new TipoCuenta();
                String info = port.mostrarTipoCuenta(ide);
                String[] infoac = info.split(";");
                pe.setCodigo(infoac[0]);
                pe.setNombre(infoac[1]);
                request.setAttribute("tipoCuenta", pe);
                request.getRequestDispatcher("editarTipoCuenta.jsp").forward(request, response);
                break;
            case "Actualizar":
                String codigo1 = request.getParameter("txtcodigo");
                String nombre1 = request.getParameter("txtnombre");
                port.actualizarTipoCuenta(codigo1, nombre1);
                request.getRequestDispatcher("TipoCuentaServ?accion=Listar").forward(request, response);
                break;
            case "Eliminar":
                String id2 = request.getParameter("id");
                port.eliminarTipoCuenta(id2);
                request.getRequestDispatcher("TipoCuentaServ?accion=Listar").forward(request, response);
                break;
            case "Regresar":
                for ( String v : elemento) {
                    TipoCuenta ac = new TipoCuenta();
                    String[] listaAc = v.split(";");
                    ac.setCodigo(listaAc[0]);
                    ac.setNombre(listaAc[1]);
                    datos.add(ac);
                }
                request.setAttribute("tipoCuenta", datos);
                request.getRequestDispatcher("TipoCuenta.jsp").forward(request, response);
                break;
            case "Mostrar":
                String ide2 = request.getParameter("id");
                TipoCuenta pe2 = new TipoCuenta();
                String info2 = port.mostrarTipoCuenta(ide2);
                String[] infoac2 = info2.split(";");
                pe2.setCodigo(infoac2[0]);
                pe2.setNombre(infoac2[1]);
                request.setAttribute("tipoCuenta", pe2);
                request.getRequestDispatcher("mostrarTipoCuenta.jsp").forward(request, response);
                break;
            case "Buscar":
                String ide3 = request.getParameter("buscarTipoCuenta");
                TipoCuenta pe3 = new TipoCuenta();
                String info3 = port.mostrarTipoCuenta(ide3);
                String[] infoac3 = info3.split(";");
                pe3.setCodigo(infoac3[0]);
                pe3.setNombre(infoac3[1]);
                request.setAttribute("tipoCuenta", pe3);
                request.getRequestDispatcher("mostrarTipoCuenta.jsp").forward(request, response);
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
