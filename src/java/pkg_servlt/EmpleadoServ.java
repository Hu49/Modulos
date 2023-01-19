/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg_servelet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pkg_entidad.Empleado;
import pkg_persistencia.PersistenciaNomina;
//import pkg_webservice.WebServiceServer;
//import pkg_webservice.WebServiceServer_Service;
/**
 *
 * @author josep
 */
@WebServlet(name = "EmpleadoServ", urlPatterns = {"/EmpleadoServ"})
public class EmpleadoServ extends HttpServlet{
    public PersistenciaNomina persis = new PersistenciaNomina();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //WebServiceServer_Service service1 = new WebServiceServer_Service();
        //WebServiceServer port = service1.getWebServiceServerPort();
        String accion = request.getParameter("accion");
        String dato = persis.listarEmpleado();
        String[] elemento = dato.split("/");
        List<Empleado> datos = new ArrayList<Empleado>();
        switch (accion) {
            case "Listar":
                for ( String v : elemento) {
                    Empleado emp = new Empleado();
                    String[] listaEmp = v.split(";");
                    emp.setCodigo(listaEmp[0]);
                    emp.setCedula(listaEmp[1]);
                    emp.setNombre(listaEmp[2]);
                    emp.setFechaIngreso(listaEmp[3]);
                    emp.setSueldo(listaEmp[4]);
                    datos.add(emp);
                }
                request.setAttribute("empleado", datos);
                request.getRequestDispatcher("Empleado.jsp").forward(request, response);
                break;
            case "Nuevo":
                request.getRequestDispatcher("agregarEmpleado.jsp").forward(request, response);
                break;
            case "Guardar":
                String cedula = request.getParameter("txtcedula");
                String nombre = request.getParameter("txtnombre");
                String fechaIngreso = request.getParameter("txtfechaIngreso");
                String sueldo = request.getParameter("txtsueldo");
                persis.insertarEmpleado(cedula, nombre, fechaIngreso, sueldo);
                request.getRequestDispatcher("EmpleadoServ?accion=Listar").forward(request, response);
                break;
            case "Editar":
                String ide = request.getParameter("id");
                Empleado pe = new Empleado();
                String info = persis.mostrarEmpleado(ide);
                String[] infoac = info.split(";");
                pe.setCodigo(infoac[0]);
                pe.setCedula(infoac[1]);
                pe.setNombre(infoac[2]);
                pe.setFechaIngreso(infoac[3]);
                pe.setSueldo(infoac[4]);
                request.setAttribute("empleado", pe);
                request.getRequestDispatcher("editarEmpleado.jsp").forward(request, response);
                break;
            case "Actualizar":
                String codigo1 = request.getParameter("txtcodigo");
                String cedula1 = request.getParameter("txtcedula");
                String nombre1 = request.getParameter("txtnombre");
                String fechaIngreso1 = request.getParameter("txtfechaIngreso");
                String sueldo1 = request.getParameter("txtsueldo");
                persis.actualizarEmpleado(codigo1, cedula1, nombre1,fechaIngreso1,sueldo1);
                request.getRequestDispatcher("EmpleadoServ?accion=Listar").forward(request, response);
                break;
            case "Eliminar":
                String id2 = request.getParameter("id");
                persis.eliminarEmpleado(id2);
                request.getRequestDispatcher("EmpleadoServ?accion=Listar").forward(request, response);
                break;
            case "Regresar":
                for ( String v : elemento) {
                    Empleado ac2 = new Empleado();
                    String[] listaAc = v.split(";");
                    ac2.setCodigo(listaAc[0]);
                    ac2.setCedula(listaAc[1]);
                    ac2.setNombre(listaAc[2]);
                    ac2.setFechaIngreso(listaAc[3]);
                    ac2.setSueldo(listaAc[4]);
                    datos.add(ac2);
                }
                request.setAttribute("empleado", datos);
                request.getRequestDispatcher("Empleado.jsp").forward(request, response);
                break;
            case "Mostrar":
                String ide2 = request.getParameter("id");
                Empleado pe2 = new Empleado();
                String info2 = persis.mostrarEmpleado(ide2);
                String[] infoac2 = info2.split(";");
                pe2.setCodigo(infoac2[0]);
                pe2.setCedula(infoac2[1]);
                pe2.setNombre(infoac2[2]);
                pe2.setFechaIngreso(infoac2[3]);
                pe2.setSueldo(infoac2[4]);
                request.setAttribute("empleado", pe2);
                request.getRequestDispatcher("mostrarEmpleado.jsp").forward(request, response);
                break;   
            case "Buscar":
                String ide3 = request.getParameter("buscarEmpleado");
                Empleado pe3 = new Empleado();
                String info3 = persis.mostrarEmpleado(ide3);
                String[] infoac3 = info3.split(";");
                pe3.setCodigo(infoac3[0]);
                pe3.setCedula(infoac3[1]);
                pe3.setNombre(infoac3[2]);
                pe3.setFechaIngreso(infoac3[3]);
                pe3.setSueldo(infoac3[4]);
                request.setAttribute("empleado", pe3);
                request.getRequestDispatcher("mostrarEmpleado.jsp").forward(request, response);
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
