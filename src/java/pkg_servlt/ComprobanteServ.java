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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pkg_entidad.Cuenta;
import pkg_entidad.TipoCuenta;
import pkg_entidad.DetalleComprobante;
import pkg_entidad.Comprobante;
import pkg_persistencia.PersistenciaContabilidad;

/**
 *
 * @author hudie
 */
@WebServlet(name = "ComprobanteServ", urlPatterns = {"/ComprobanteServ"})
public class ComprobanteServ extends HttpServlet {

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
        HttpSession session = request.getSession(false);
        PersistenciaContabilidad port = new PersistenciaContabilidad();
        String accion = request.getParameter("accion");
        String idComprobantes = request.getParameter("codigoComprobante");
        System.out.println("info: "+idComprobantes);
        String dato = port.listarComprobante();
        Integer idComprobante = null;
        if(idComprobantes != null){
            idComprobante = Integer.parseInt(idComprobantes);
        }
        String[] elemento = dato.split("/");
        String[] elementoD = null;

        if (idComprobantes == null) {
            for (String w : elemento) {
                String[] listaAc = w.split(";");
                if (idComprobantes == null) {
                    idComprobantes= listaAc[0];
                    idComprobante = Integer.parseInt(idComprobantes);
                }
            }
        }
        String ddato = port.listarDetalleComprobante(idComprobante);
        if (!ddato.equals("")) {
            elementoD = ddato.split("/");
        }
        List<Comprobante> datos = new ArrayList<>();
        List<DetalleComprobante> ddatos = new ArrayList<>();
        switch (accion) {
            case "Listar":
                for (String v : elemento) {
                    Comprobante ac = new Comprobante();
                    String[] listaAc = v.split(";");
                    ac.setCodigo(Integer.parseInt(listaAc[0]));
                    ac.setFecha(listaAc[1]);
                    ac.setObservaciones(listaAc[2]);
                    datos.add(ac);
                }
                request.setAttribute("comprobante", datos);
                request.setAttribute("codigoComprobante", idComprobante);
                request.getRequestDispatcher("Comprobante.jsp").forward(request, response);
                break;
            case "Nuevo":
                request.getRequestDispatcher("agregarComprobante.jsp").forward(request, response);
                break;
            case "Guardar":
                String fecha = request.getParameter("txtfecha");
                String responsable = request.getParameter("txtObservaciones");
                int index=elemento.length+1;
                System.out.println("FECHA: "+fecha);
                port.insertarComprobante(index, fecha, responsable);
                request.getRequestDispatcher("ComprobanteServ?accion=Detalles").forward(request, response);
                break;
            case "Editar":
                String ide = request.getParameter("id");
                Comprobante pe = new Comprobante();
                String info = port.mostrarComprobante(Integer.parseInt(ide));
                String[] infoac = info.split(";");
                pe.setCodigo(Integer.parseInt(infoac[0]));
                pe.setFecha(infoac[1]);
                pe.setObservaciones(infoac[2]);
                request.setAttribute("comprobante", pe);
                request.getRequestDispatcher("editarComprobante.jsp").forward(request, response);
                break;
            case "Actualizar":
                String codigo1 = request.getParameter("txtcodigo");
                String fecha1 = request.getParameter("txtfecha");
                String responsable1 = request.getParameter("txtObservaciones");
                port.actualizarComprobante(Integer.parseInt(codigo1), fecha1, responsable1);
                request.getRequestDispatcher("ComprobanteServ?accion=Detalles").forward(request, response);
                break;
            case "Eliminar":
                String id2 = request.getParameter("id");
                port.eliminarComprobante(Integer.parseInt(id2));
                request.getRequestDispatcher("ComprobanteServ?accion=Detalles").forward(request, response);
                break;
            case "Regresar":
                for (String v : elemento) {
                    Comprobante ac2 = new Comprobante();
                    String[] listaAc = v.split(";");
                    ac2.setCodigo(Integer.parseInt(listaAc[0]));
                    ac2.setFecha(listaAc[1]);
                    ac2.setObservaciones(listaAc[2]);
                    datos.add(ac2);
                }
                request.setAttribute("comprobante", datos);
                request.getRequestDispatcher("ComprobanteServ?accion=Detalles").forward(request, response);
                break;
            case "Mostrar":
                String ide2 = request.getParameter("id");
                Comprobante pe2 = new Comprobante();
                String info2 = port.mostrarComprobante(Integer.parseInt(ide2));
                String[] infoac2 = info2.split(";");
                pe2.setCodigo(Integer.parseInt(infoac2[0]));
                pe2.setFecha(infoac2[1]);
                pe2.setObservaciones(infoac2[2]);
                request.setAttribute("comprobante", pe2);
                request.getRequestDispatcher("mostrarComprobante.jsp").forward(request, response);
                break;
            case "Buscar":
                String ide3 = request.getParameter("buscarComprobante");
                Comprobante pe3 = new Comprobante();
                String info3 = port.mostrarComprobante(Integer.parseInt(ide3));
                String[] infoac3 = info3.split(";");
                pe3.setCodigo(Integer.parseInt(infoac3[0]));
                pe3.setFecha(infoac3[1]);
                pe3.setObservaciones(infoac3[2]);
                request.setAttribute("comprobante", pe3);
                request.getRequestDispatcher("mostrarComprobante.jsp").forward(request, response);
                break;
            case "Detalles":
                if (elementoD != null) {
                    for (String vd : elementoD) {
                        DetalleComprobante man = new DetalleComprobante();
                        String[] listaMan = vd.split(";");
                        man.setCodigo(listaMan[0]);
                        man.setCodigoComprobante(Integer.parseInt(listaMan[2]));
                        String activo2 = port.mostrarCuenta(listaMan[1]);
                        String[] nomActvo2 = activo2.split(";");
                        man.setCodigoCuenta(nomActvo2[2]);
                        man.setDebe(Float.parseFloat(listaMan[3]));
                        man.setHaber(Float.parseFloat(listaMan[4]));
                        ddatos.add(man);
                    }
                }
                request.setAttribute("comprobanteD", ddatos);
                request.getRequestDispatcher("ComprobanteServ?accion=Listar").forward(request, response);
                break;
            case "Mostrar ":
                String ide2d = request.getParameter("id2");
                DetalleComprobante pe2d = new DetalleComprobante();
                String info2d = port.mostrarDetalleComprobante(ide2d);
                String[] infoac2d = info2d.split(";");
                pe2d.setCodigo(infoac2d[0]);
                pe2d.setCodigoComprobante(Integer.parseInt(infoac2d[2]));
                String activo = port.mostrarCuenta(infoac2d[1]);
                String[] nomActvo = activo.split(";");
                pe2d.setCodigoCuenta(nomActvo[2]);
                pe2d.setDebe(Float.parseFloat(infoac2d[3]));
                pe2d.setHaber(Float.parseFloat(infoac2d[4]));
                request.setAttribute("detallecomprobante", pe2d);
                request.getRequestDispatcher("mostrarDetalleComprobante.jsp").forward(request, response);
                break;
            case "Buscar ":
                String ide3d = request.getParameter("buscarDetalleMantenimiento");
                DetalleComprobante pe3d = new DetalleComprobante();
                String info3d = port.mostrarDetalleComprobante(ide3d);
                String[] infoac3d = info3d.split(";");
                pe3d.setCodigo(infoac3d[0]);
                pe3d.setCodigoComprobante(Integer.parseInt(infoac3d[1]));
                String activo2 = port.mostrarCuenta(infoac3d[2]);
                String[] nomActvo2 = activo2.split(";");
                pe3d.setCodigoCuenta(nomActvo2[1]);
                pe3d.setDebe(Float.parseFloat(infoac3d[3]));
                pe3d.setHaber(Float.parseFloat(infoac3d[4]));
                request.setAttribute("detallecomprobante", pe3d);
                request.getRequestDispatcher("mostrarDetalleComprobante.jsp").forward(request, response);
                break;
            case "Nuevo ":
                String valor = request.getParameter("id2");
                String actividaddato = port.listarCuenta();
                String[] acvidad = actividaddato.split("/");
                List<Cuenta> datosacvidad = new ArrayList<>();
                for (String sacvidad : acvidad) {
                    Cuenta actvidad = new Cuenta();
                    String[] listaAc = sacvidad.split(";");
                    actvidad.setCodigo(listaAc[0]);
                    actvidad.setTipoCuenta(listaAc[1]);
                    actvidad.setNombre(listaAc[2]);
                    datosacvidad.add(actvidad);
                }
                request.setAttribute("SCuenta", datosacvidad);
                request.getRequestDispatcher("agregarDetalleComprobante.jsp?codigoComprobante=" + valor).forward(request, response);
                break;
            case "Guardar ":
                String dcomprobante = request.getParameter("txtcomprobante");
                String dcuenta = request.getParameter("txtcuenta");
                String ddebe = request.getParameter("txtdebe");
                String dhaber = request.getParameter("txthaber");
                String vmanint = port.mostrarComprobante(Integer.parseInt(dcomprobante));
                String[] vmaintsep = vmanint.split(";");
                String dddatos = port.listarDetalleComprobanteAll();
                String[] ele = null;
                if (!dddatos.equals("")) {
                    ele = dddatos.split("/");
                }    
                int index1=ele.length+1;
                port.insertarDetalleComprobante(""+index1, dcuenta, Integer.parseInt(dcomprobante), Float.parseFloat(ddebe), Float.parseFloat(dhaber));
                request.getRequestDispatcher("ComprobanteServ?accion=Detalles").forward(request, response);
                break;
            case "Editar ":
                String ided = request.getParameter("id2");
                String actividaddato2 = port.listarCuenta();
                String[] acvidad2 = actividaddato2.split("/");
                List<Cuenta> datosacvidad2 = new ArrayList<>();
                for (String sacvidad2 : acvidad2) {
                    Cuenta actvidad2 = new Cuenta();
                    String[] listaAc = sacvidad2.split(";");
                    actvidad2.setCodigo(listaAc[0]);
                    actvidad2.setNombre(listaAc[2]);
                    datosacvidad2.add(actvidad2);
                }
                DetalleComprobante ped = new DetalleComprobante();
                String infod = port.mostrarDetalleComprobante(ided);
                System.out.println("INFOD: "+infod);
                String[] infoacd = infod.split(";");
                ped.setCodigo(infoacd[0]);
                ped.setCodigoComprobante(Integer.parseInt(infoacd[2]));
                ped.setCodigoCuenta(infoacd[1]);
                ped.setDebe(Float.parseFloat(infoacd[3]));
                ped.setHaber(Float.parseFloat(infoacd[4]));
                request.setAttribute("SCuenta", datosacvidad2);
                request.setAttribute("detallecomprobante", ped);
                request.getRequestDispatcher("editarDetalleComprobante.jsp").forward(request, response);
                break;
            case "Actualizar ":
                String codigod = request.getParameter("txtcodigo");
                String comprobanted = request.getParameter("txtcomprobante");
                String cuentad = request.getParameter("txtcuenta");
                String debed = request.getParameter("txtdebe");
                String haberd = request.getParameter("txthaber");
                port.actualizarDetalleComprobante(codigod, Integer.parseInt(comprobanted), cuentad, Float.parseFloat(debed), Float.parseFloat(haberd));
                request.getRequestDispatcher("ComprobanteServ?accion=Detalles").forward(request, response);
                break;
            case "Eliminar ":
                String id2d = request.getParameter("id2");
                port.eliminarDetalleComprobante(id2d);
                request.getRequestDispatcher("ComprobanteServ?accion=Detalles").forward(request, response);
                break;
//            case "Reporte1":
//                String valrep1 = port.reporte1Man();
//                String[] rep1 = valrep1.split("/");
//                List<DetalleComprobante> reporte1 = new ArrayList<>();
//                int existe1 = 0;
//                DetalleComprobante totalrepo1 = new DetalleComprobante();
//                totalrepo1.setActivo("Total");
//                float sumaActivoRepor1 = 0;
//                int cant = 0;
//                for (String repor1 : rep1) {
//                    DetalleComprobante lsrepor1 = new DetalleComprobante();
//                    String[] listaRepo1 = repor1.split(";");
//                    String activo3 = port.mostrarActivo(listaRepo1[0]);
//                    String[] nomActvo3 = activo3.split(";");
//                    lsrepor1.setActivo(nomActvo3[1]);
//                    lsrepor1.setValor(Float.parseFloat(listaRepo1[1]));
//                    if (!reporte1.isEmpty()) {
//                        for (DetalleComprobante compar1 : reporte1) {
//                            if (compar1.getActivo().equals(lsrepor1.getActivo())) {
//                                existe1 = 1;
//                                compar1.setValor(compar1.getValor() + lsrepor1.getValor());
//                            }
//                        }
//                    }
//                    if (existe1 == 0) {
//                        reporte1.add(lsrepor1);
//                        cant++;
//                    }
//                    existe1 = 0;
//                }
//                for (int y = 0; y < cant; y++) {
//                    sumaActivoRepor1 += reporte1.get(y).getValor();
//                }
//                totalrepo1.setValor(sumaActivoRepor1);
//                reporte1.add(totalrepo1);
//                request.setAttribute("reporte1", reporte1);
//                request.getRequestDispatcher("ReporteMantenimiento1.jsp").forward(request, response);
//                break;
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
