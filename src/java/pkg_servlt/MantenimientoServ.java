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
import pkg_entidad.Actividad;
import pkg_entidad.Activo;
import pkg_entidad.DetalleMantenimiento;
import pkg_entidad.Mantenimiento;
import pkg_persistencia.Persistencia;
/**
 *r
 * @author hudie
 */
@WebServlet(name = "MantenimientoServ", urlPatterns = {"/MantenimientoServ"})
public class MantenimientoServ extends HttpServlet {

    Persistencia port = new Persistencia();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        String accion = request.getParameter("accion");
        String idMantenimiento = request.getParameter("codigoMantenimiento");
        String dato = port.listarMantenimiento();
        String[] elemento = dato.split("/");
        String[] elementoD = null;

        if (idMantenimiento == null) {
            for (String w : elemento) {
                String[] listaAc = w.split(";");
                if (idMantenimiento == null) {
                    idMantenimiento = listaAc[0];
                }
            }
        }
        String ddato = port.listarDetalleMantenimiento(idMantenimiento);
        if (!ddato.equals("")) {
            elementoD = ddato.split("/");
        }
        List<Mantenimiento> datos = new ArrayList<>();
        List<DetalleMantenimiento> ddatos = new ArrayList<>();
        switch (accion) {
            case "Listar":
                for (String v : elemento) {
                    Mantenimiento ac = new Mantenimiento();
                    String[] listaAc = v.split(";");
                    ac.setCodigo(listaAc[0]);
                    ac.setFecha(listaAc[1]);
                    ac.setResponsable(listaAc[2]);
                    datos.add(ac);
                }
                request.setAttribute("mantenimiento", datos);
                request.setAttribute("codigoMantenimiento", idMantenimiento);
                request.getRequestDispatcher("Mantenimiento.jsp").forward(request, response);
                break;
            case "Nuevo":
                request.getRequestDispatcher("agregarMantenimiento.jsp").forward(request, response);
                break;
            case "Guardar":
                String fecha = request.getParameter("txtfecha");
                String responsable = request.getParameter("txtresponsable");
                port.insertarMantenimiento("0", fecha, responsable);
                port.crearContabilidad("0", fecha, responsable);
                request.getRequestDispatcher("MantenimientoServ?accion=Detalles").forward(request, response);
                break;
            case "Editar":
                String ide = request.getParameter("id");
                Mantenimiento pe = new Mantenimiento();
                String info = port.mostrarMantenimiento(ide);
                String[] infoac = info.split(";");
                pe.setCodigo(infoac[0]);
                pe.setFecha(infoac[1]);
                pe.setResponsable(infoac[2]);
                request.setAttribute("mantenimiento", pe);
                request.getRequestDispatcher("editarMantenimiento.jsp").forward(request, response);
                break;
            case "Actualizar":
                String codigo1 = request.getParameter("txtcodigo");
                String fecha1 = request.getParameter("txtfecha");
                String responsable1 = request.getParameter("txtresponsable");
                port.actualizarMantenimiento(codigo1, fecha1, responsable1);
                request.getRequestDispatcher("MantenimientoServ?accion=Detalles").forward(request, response);
                break;
            case "Eliminar":
                String id2 = request.getParameter("id");
                port.eliminarMantenimiento(id2);
                request.getRequestDispatcher("MantenimientoServ?accion=Detalles").forward(request, response);
                break;
            case "Regresar":
                for (String v : elemento) {
                    Mantenimiento ac2 = new Mantenimiento();
                    String[] listaAc = v.split(";");
                    ac2.setCodigo(listaAc[0]);
                    ac2.setFecha(listaAc[1]);
                    ac2.setResponsable(listaAc[2]);
                    datos.add(ac2);
                }
                request.setAttribute("mantenimiento", datos);
                request.getRequestDispatcher("MantenimientoServ?accion=Detalles").forward(request, response);
                break;
            case "Mostrar":
                String ide2 = request.getParameter("id");
                Mantenimiento pe2 = new Mantenimiento();
                String info2 = port.mostrarMantenimiento(ide2);
                String[] infoac2 = info2.split(";");
                pe2.setCodigo(infoac2[0]);
                pe2.setFecha(infoac2[1]);
                pe2.setResponsable(infoac2[2]);
                request.setAttribute("mantenimiento", pe2);
                request.getRequestDispatcher("mostrarMantenimiento.jsp").forward(request, response);
                break;
            case "Buscar":
                String ide3 = request.getParameter("buscarMantenimiento");
                Mantenimiento pe3 = new Mantenimiento();
                String info3 = port.mostrarMantenimiento(ide3);
                String[] infoac3 = info3.split(";");
                pe3.setCodigo(infoac3[0]);
                pe3.setFecha(infoac3[1]);
                pe3.setResponsable(infoac3[2]);
                request.setAttribute("mantenimiento", pe3);
                request.getRequestDispatcher("mostrarMantenimiento.jsp").forward(request, response);
                break;
            case "Detalles":
                if (elementoD != null) {
                    for (String vd : elementoD) {
                        DetalleMantenimiento man = new DetalleMantenimiento();
                        String[] listaMan = vd.split(";");
                        man.setCodigo(listaMan[0]);
                        man.setCodigoMantenimiento(listaMan[1]);
                        String activo2 = port.mostrarActivo(listaMan[2]);
                        String[] nomActvo2 = activo2.split(";");
                        man.setActivo(nomActvo2[1]);
                        String activi2 = port.mostrarActividad(listaMan[3]);
                        String[] nomActvi2 = activi2.split(";");
                        man.setActividad(nomActvi2[1]);
                        man.setValor(Float.parseFloat(listaMan[4]));
                        ddatos.add(man);
                    }
                }
                request.setAttribute("mantenimientoD", ddatos);
                request.getRequestDispatcher("MantenimientoServ?accion=Listar").forward(request, response);
                break;
            case "Mostrar ":
                String ide2d = request.getParameter("id2");
                DetalleMantenimiento pe2d = new DetalleMantenimiento();
                String info2d = port.mostrarDetalleMantenimiento(ide2d);
                String[] infoac2d = info2d.split(";");
                pe2d.setCodigo(infoac2d[0]);
                pe2d.setCodigoMantenimiento(infoac2d[1]);
                String activo = port.mostrarActivo(infoac2d[2]);
                String[] nomActvo = activo.split(";");
                pe2d.setActivo(nomActvo[1]);
                String activi = port.mostrarActividad(infoac2d[3]);
                String[] nomActvi = activi.split(";");
                pe2d.setActividad(nomActvi[1]);
                pe2d.setValor(Float.parseFloat(infoac2d[4]));
                request.setAttribute("detallemantenimiento", pe2d);
                request.getRequestDispatcher("mostrarDetalleMantenimiento.jsp").forward(request, response);
                break;
            case "Buscar ":
                String ide3d = request.getParameter("buscarDetalleMantenimiento");
                DetalleMantenimiento pe3d = new DetalleMantenimiento();
                String info3d = port.mostrarDetalleMantenimiento(ide3d);
                String[] infoac3d = info3d.split(";");
                pe3d.setCodigo(infoac3d[0]);
                pe3d.setCodigoMantenimiento(infoac3d[1]);
                String activo2 = port.mostrarActivo(infoac3d[2]);
                String[] nomActvo2 = activo2.split(";");
                pe3d.setActivo(nomActvo2[1]);
                String activi2 = port.mostrarActividad(infoac3d[3]);
                String[] nomActvi2 = activi2.split(";");
                pe3d.setActividad(nomActvi2[1]);
                pe3d.setValor(Float.parseFloat(infoac3d[4]));
                request.setAttribute("detallemantenimiento", pe3d);
                request.getRequestDispatcher("mostrarDetalleMantenimiento.jsp").forward(request, response);
                break;
            case "Nuevo ":
                String valor = request.getParameter("id2");
                String activodato = port.listarActivo();
                String[] acvo = activodato.split("/");
                List<Activo> datosacvo = new ArrayList<>();
                String actividaddato = port.listarActividad();
                String[] acvidad = actividaddato.split("/");
                List<Actividad> datosacvidad = new ArrayList<>();
                for (String sacvo : acvo) {
                    Activo actvo = new Activo();
                    String[] listaAc = sacvo.split(";");
                    actvo.setCodigo(listaAc[0]);
                    actvo.setNombre(listaAc[1]);
                    datosacvo.add(actvo);
                }
                for (String sacvidad : acvidad) {
                    Actividad actvidad = new Actividad();
                    String[] listaAc = sacvidad.split(";");
                    actvidad.setCodigo(listaAc[0]);
                    actvidad.setNombre(listaAc[1]);
                    datosacvidad.add(actvidad);
                }
                request.setAttribute("SActivo", datosacvo);
                request.setAttribute("SActividad", datosacvidad);
                request.getRequestDispatcher("agregarDetalleMantenimiento.jsp?codigoMantenimiento=" + valor).forward(request, response);
                break;
            case "Guardar ":
                String dmantenimiento = request.getParameter("txtmantenimiento");
                String dactivo = request.getParameter("txtactivo");
                String dactividad = request.getParameter("txtactividad");
                String dvalor = request.getParameter("txtvalor");
                String vmanint = port.mostrarMantenimiento(dmantenimiento);
                String[] vmaintsep = vmanint.split(";");
                port.insertarDetalleMantenimiento("0", dmantenimiento, dactivo, dactividad, dvalor);
                String containt = port.obtenerContabilidad(vmaintsep[2]);
                port.crearDetalleContabilidad("0", dvalor, containt);
                request.getRequestDispatcher("MantenimientoServ?accion=Detalles").forward(request, response);
                break;
            case "Editar ":
                String ided = request.getParameter("id2");
                String activodato2 = port.listarActivo();
                String[] acvo2 = activodato2.split("/");
                List<Activo> datosacvo2 = new ArrayList<>();
                String actividaddato2 = port.listarActividad();
                String[] acvidad2 = actividaddato2.split("/");
                List<Actividad> datosacvidad2 = new ArrayList<>();
                for (String sacvo2 : acvo2) {
                    Activo actvo2 = new Activo();
                    String[] listaAc = sacvo2.split(";");
                    actvo2.setCodigo(listaAc[0]);
                    actvo2.setNombre(listaAc[1]);
                    datosacvo2.add(actvo2);
                }
                for (String sacvidad2 : acvidad2) {
                    Actividad actvidad2 = new Actividad();
                    String[] listaAc = sacvidad2.split(";");
                    actvidad2.setCodigo(listaAc[0]);
                    actvidad2.setNombre(listaAc[1]);
                    datosacvidad2.add(actvidad2);
                }
                DetalleMantenimiento ped = new DetalleMantenimiento();
                String infod = port.mostrarDetalleMantenimiento(ided);
                String[] infoacd = infod.split(";");
                ped.setCodigo(infoacd[0]);
                ped.setCodigoMantenimiento(infoacd[1]);
                ped.setCodigoActivo(infoacd[2]);
                ped.setCodigoActividad(infoacd[3]);
                ped.setValor(Float.parseFloat(infoacd[4]));
                request.setAttribute("SActivo", datosacvo2);
                request.setAttribute("SActividad", datosacvidad2);
                request.setAttribute("detallemantenimiento", ped);
                request.getRequestDispatcher("editarDetalleMantenimiento.jsp").forward(request, response);
                break;
            case "Actualizar ":
                String codigod = request.getParameter("txtcodigo");
                String mantenimientod = request.getParameter("txtmantenimiento");
                String activod = request.getParameter("txtactivo");
                String actividadd = request.getParameter("txtactividad");
                String valord = request.getParameter("txtvalor");
                port.actualizarDetalleMantenimiento(codigod, mantenimientod, activod, actividadd, valord);
                request.getRequestDispatcher("MantenimientoServ?accion=Detalles").forward(request, response);
                break;
            case "Eliminar ":
                String id2d = request.getParameter("id2");
                port.eliminarDetalleMantenimiento(id2d);
                request.getRequestDispatcher("MantenimientoServ?accion=Detalles").forward(request, response);
                break;
            case "Reporte1":
                String valrep1 = port.reporte1Man();
                String[] rep1 = valrep1.split("/");
                List<DetalleMantenimiento> reporte1 = new ArrayList<>();
                int existe1 = 0;
                DetalleMantenimiento totalrepo1 = new DetalleMantenimiento();
                totalrepo1.setActivo("Total");
                float sumaActivoRepor1 = 0;
                int cant = 0;
                for (String repor1 : rep1) {
                    DetalleMantenimiento lsrepor1 = new DetalleMantenimiento();
                    String[] listaRepo1 = repor1.split(";");
                    String activo3 = port.mostrarActivo(listaRepo1[0]);
                    String[] nomActvo3 = activo3.split(";");
                    lsrepor1.setActivo(nomActvo3[1]);
                    lsrepor1.setValor(Float.parseFloat(listaRepo1[1]));
                    if (!reporte1.isEmpty()) {
                        for (DetalleMantenimiento compar1 : reporte1) {
                            if (compar1.getActivo().equals(lsrepor1.getActivo())) {
                                existe1 = 1;
                                compar1.setValor(compar1.getValor() + lsrepor1.getValor());
                            }
                        }
                    }
                    if (existe1 == 0) {
                        reporte1.add(lsrepor1);
                        cant++;
                    }
                    existe1 = 0;
                }
                for (int y = 0; y < cant; y++) {
                    sumaActivoRepor1 += reporte1.get(y).getValor();
                }
                totalrepo1.setValor(sumaActivoRepor1);
                reporte1.add(totalrepo1);
                request.setAttribute("reporte1", reporte1);
                request.getRequestDispatcher("ReporteMantenimiento1.jsp").forward(request, response);
                break;
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
            Logger.getLogger(MantenimientoServ.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(MantenimientoServ.class.getName()).log(Level.SEVERE, null, ex);
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