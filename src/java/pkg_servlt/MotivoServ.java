/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg_servlt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pkg_entidad.*;
import pkg_persistencia.PersistenciaContabilidad;
import pkg_persistencia.PersistenciaNomina;
//import pkg_webservice.WebServiceServer;
//import pkg_webservice.WebServiceServer_Service;
/**
 *
 * @author josep
 */
@WebServlet(name = "MotivoServ", urlPatterns = {"/MotivoServ"})
public class MotivoServ extends HttpServlet{
    public PersistenciaNomina persis = new PersistenciaNomina();
    public PersistenciaContabilidad persis1 = new PersistenciaContabilidad();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        response.setContentType("text/html;charset=UTF-8");
        //WebServiceServer_Service service1 = new WebServiceServer_Service();
        //WebServiceServer port = service1.getWebServiceServerPort();
        
        String accion = request.getParameter("accion");
        String dato = persis.listarMotivo();
        String[] elemento = dato.split("/");
        List<Motivo> datos = new ArrayList<Motivo>();
        switch (accion) {
            case "Listar":
                for ( String v : elemento) {
                    Motivo mtv = new Motivo();
                    String[] listaMtv = v.split(";");
                    mtv.setCodigoMov(listaMtv[0]);
                    mtv.setNombreMov(listaMtv[1]);
                    datos.add(mtv);
                }
                request.setAttribute("motivo", datos);
                request.getRequestDispatcher("Motivo.jsp").forward(request, response);
                break;
            case "Nuevo":
                
                String datoCuenta = persis1.listarCuenta();
                String[] acvEmp = datoCuenta.split("/");
                List<Cuenta> datosCuenta = new ArrayList<>();

                for (String scab : acvEmp) {
                    Cuenta cuen = new Cuenta();
                    String[] listaCuenta = scab.split(";");
                    cuen.setCodigo(listaCuenta[0]);
                    cuen.setNombre(listaCuenta[2]);
                    datosCuenta.add(cuen);
                }
                request.setAttribute("SCuenta", datosCuenta);
                
                request.getRequestDispatcher("agregarMotivo.jsp").forward(request, response);
                break;
            case "Guardar":
                String nombre = request.getParameter("txtnombre");
                String signo = request.getParameter("txtsigno");
                String cuenta = request.getParameter("txtcuenta");
                persis.insertarMotivo(nombre, signo,cuenta);
                request.getRequestDispatcher("MotivoServ?accion=Listar").forward(request, response);
                break;
            case "Editar":
                String ide = request.getParameter("id");
                
                String datoCuenta1 = persis1.listarCuenta();
                String[] acvEmp1 = datoCuenta1.split("/");
                List<Cuenta> datosCuenta1 = new ArrayList<>();

                for (String scab : acvEmp1) {
                    Cuenta cuen1 = new Cuenta();
                    String[] listaCuenta1 = scab.split(";");
                    cuen1.setCodigo(listaCuenta1[0]);
                    cuen1.setNombre(listaCuenta1[2]);
                    datosCuenta1.add(cuen1);
                }
                request.setAttribute("SCuenta", datosCuenta1);
                
                Motivo pe = new Motivo();
                String info = persis.mostrarMotivo(ide);
                String[] infoac = info.split(";");
                pe.setCodigoMov(infoac[0]);
                pe.setNombreMov(infoac[1]);
                request.setAttribute("motivo", pe);
                request.getRequestDispatcher("editarMotivo.jsp").forward(request, response);
                break;
            case "Actualizar":
                String codigo1 = request.getParameter("txtcodigo");
                String nombre1 = request.getParameter("txtnombre");
                String cuenta1 = request.getParameter("txtcuenta");
                persis.actualizarMotivo(codigo1, nombre1, cuenta1);
                request.getRequestDispatcher("MotivoServ?accion=Listar").forward(request, response);
                break;
            case "Eliminar":
                String id2 = request.getParameter("id");
                persis.eliminarMotivo(id2);
                request.getRequestDispatcher("MotivoServ?accion=Listar").forward(request, response);
                break;
            case "Regresar":
                for ( String v : elemento) {
                    Motivo ac2 = new Motivo();
                    String[] listaAc = v.split(";");
                    ac2.setCodigoMov(listaAc[0]);
                    ac2.setNombreMov(listaAc[1]);
                    datos.add(ac2);
                }
                request.setAttribute("motivo", datos);
                request.getRequestDispatcher("Motivo.jsp").forward(request, response);
                break;
            case "Mostrar":
                String ide2 = request.getParameter("id");
                Motivo pe2 = new Motivo();
                String info2 = persis.mostrarMotivo(ide2);
                String[] infoac2 = info2.split(";");
                pe2.setCodigoMov(infoac2[0]);
                pe2.setNombreMov(infoac2[1]);
                pe2.setSigno(infoac2[2]);
                pe2.setCtacontable(infoac2[3]);
                request.setAttribute("motivo", pe2);
                request.getRequestDispatcher("mostrarMotivo.jsp").forward(request, response);
                break;   
            case "Buscar":
                String ide3 = request.getParameter("buscarMotivo");
                Motivo pe3 = new Motivo();
                String info3 = persis.mostrarMotivo(ide3);
                String[] infoac3 = info3.split(";");
                pe3.setCodigoMov(infoac3[0]);
                pe3.setNombreMov(infoac3[1]);
                pe3.setSigno(infoac3[2]);
                pe3.setCtacontable(infoac3[3]);
                request.setAttribute("motivo", pe3);
                request.getRequestDispatcher("mostrarMotivo.jsp").forward(request, response);
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
