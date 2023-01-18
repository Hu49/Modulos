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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pkg_entidad.*;
import pkg_persistencia.PersistenciaContabilidad;

/**
 *
 * @author hudie
 */
@WebServlet(name = "CuentaServ", urlPatterns = {"/CuentaServ"})
public class CuentaServ extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(false);
        PersistenciaContabilidad port = new PersistenciaContabilidad();
        String accion = request.getParameter("accion");
        String dato = port.listarCuenta();
        String[] elemento = dato.split("/");
        List<Cuenta> datos = new ArrayList<Cuenta>();
        switch (accion) {
            case "Listar":
                for ( String v : elemento) {
                    Cuenta ac = new Cuenta();
                    String[] listaAc = v.split(";");
                    ac.setCodigo(listaAc[0]);
                    ac.setNombre(listaAc[2]);
                    ac.setTipoCuenta(listaAc[1]);
                    datos.add(ac);
                }
                request.setAttribute("cuenta", datos);
                request.getRequestDispatcher("Cuenta.jsp").forward(request, response);
                break;
            case "Nuevo":
                request.getRequestDispatcher("agregarCuenta.jsp").forward(request, response);
                break;
            case "Guardar":
                String nombre = request.getParameter("txtnombre");
                String cuenta = request.getParameter("cuenta");
                int index=elemento.length+1;
                System.out.println("información: "+index);
                port.insertarCuenta(""+index,cuenta, nombre);
                request.getRequestDispatcher("CuentaServ?accion=Listar").forward(request, response);
                break;
            case "Editar":
                String ide = request.getParameter("id");
                Cuenta pe = new Cuenta();
                String info = port.mostrarCuenta(ide);
                String[] infoac = info.split(";");
                pe.setCodigo(infoac[0]);
                pe.setNombre(infoac[2]);
                pe.setTipoCuenta(infoac[1]);
                request.setAttribute("cuenta", pe);
                request.getRequestDispatcher("editarCuenta.jsp").forward(request, response);
                break;
            case "Actualizar":
                String codigo1 = request.getParameter("txtcodigo");
                String nombre1 = request.getParameter("txtnombre");
                String cuenta1 = request.getParameter("cuenta");
                port.actualizarCuenta(codigo1,cuenta1, nombre1);
                request.getRequestDispatcher("CuentaServ?accion=Listar").forward(request, response);
                break;
            case "Eliminar":
                String id2 = request.getParameter("id");
                port.eliminarCuenta(id2);
                request.getRequestDispatcher("CuentaServ?accion=Listar").forward(request, response);
                break;
            case "Regresar":
                for ( String v : elemento) {
                    Cuenta ac2 = new Cuenta();
                    String[] listaAc = v.split(";");
                    ac2.setCodigo(listaAc[0]);
                    ac2.setNombre(listaAc[1]);
                    ac2.setTipoCuenta(listaAc[2]);
                    datos.add(ac2);
                }
                request.setAttribute("cuenta", datos);
                request.getRequestDispatcher("Cuenta.jsp").forward(request, response);
                break;
            case "Mostrar":
                String ide2 = request.getParameter("id");
                Cuenta pe2 = new Cuenta();
                String info2 = port.mostrarCuenta(ide2);
                String[] infoac2 = info2.split(";");
                pe2.setCodigo(infoac2[0]);
                pe2.setNombre(infoac2[1]);
                pe2.setTipoCuenta(infoac2[2]);
                request.setAttribute("cuenta", pe2);
                request.getRequestDispatcher("mostrarCuenta.jsp").forward(request, response);
                break;   
            case "Buscar":
                String ide3 = request.getParameter("buscarCuenta");
                Cuenta pe3 = new Cuenta();
                String info3 = port.mostrarCuenta(ide3);
                System.out.println("informacion: "+info3);
                if(info3!=null){
                    String[] infoac3 = info3.split(";");
                    pe3.setCodigo(infoac3[0]);
                    pe3.setNombre(infoac3[1]);
                    pe3.setTipoCuenta(infoac3[2]);
                }
                request.setAttribute("cuenta", pe3);
                request.getRequestDispatcher("mostrarCuenta.jsp").forward(request, response);
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
