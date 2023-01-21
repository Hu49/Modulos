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
import pkg_entidad.*;
import pkg_persistencia.PersistenciaNomina;

/**
 *
 * @author josep
 */
@WebServlet(name = "NominaServ", urlPatterns = {"/NominaServ"})
public class NominaServ extends HttpServlet {

    public PersistenciaNomina persis = new PersistenciaNomina();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //WebServiceServer_Service service1 = new WebServiceServer_Service();
        //WebServiceServer port = service1.getWebServiceServerPort();

        String accion = request.getParameter("accion");
        String idNomina = request.getParameter("codigoNomina");
        String dato = persis.listarNominaCab();
        String datoVP = persis.reporte1Man();
        String datoNomina = persis.AsientoContable();
        String[] elemento = dato.split("/");
        String[] elementoD = null;
        String[] elementoVP = datoVP.split("/");
        String[] elementoNomina = datoNomina.split("/");

        if (idNomina == null) {
            for (String w : elemento) {
                String[] listaAc = w.split(";");
                if (idNomina == null) {
                    idNomina = listaAc[0];
                }
            }
        }
        String ddato = persis.listarNominaDetalle(idNomina);
        if (!ddato.equals("")) {
            elementoD = ddato.split("/");
        }
        
        List<NominaCabecera> datosCab = new ArrayList<>();
        List<NominaDetalle> datosDet = new ArrayList<>();
        List<ValoresPago> datosVP = new ArrayList<>();
        List<AsientoContableNomina> datosNomina = new ArrayList<>();
        
        switch (accion) {
            case "Listar":
                for (String v : elemento) {
                    NominaCabecera cab = new NominaCabecera();
                    String[] listaAc = v.split(";");
                    cab.setCodigoCab(listaAc[0]);
                    cab.setFechaCab(listaAc[1]);

                    String activo2 = persis.mostrarEmpleado(listaAc[2]);
                    String[] nomActvo2 = activo2.split(";");
                    //cab.setMotivoDet();

                    cab.setEmpCab(nomActvo2[2]);
                    datosCab.add(cab);
                }
                request.setAttribute("nominaCab", datosCab);
                request.setAttribute("codigoNomina", idNomina);
                request.getRequestDispatcher("Nomina.jsp").forward(request, response);
                break;
            case "Nuevo":
                String datoEmp = persis.listarEmpleado();
                String[] acvEmp = datoEmp.split("/");
                List<Empleado> datosEmp = new ArrayList<>();

                for (String scab : acvEmp) {
                    Empleado actvo = new Empleado();
                    String[] listaEmp = scab.split(";");
                    actvo.setCodigo(listaEmp[0]);
                    actvo.setNombre(listaEmp[2]);
                    datosEmp.add(actvo);
                }
                request.setAttribute("SEmpleado", datosEmp);
                request.getRequestDispatcher("agregarNominaCab.jsp").forward(request, response);
                break;
            case "Guardar":
                String fecha = request.getParameter("txtfecha");
                String responsable = request.getParameter("txtresponsable");
                persis.insertarNominaCab(fecha, responsable);
                request.getRequestDispatcher("NominaServ?accion=Detalles").forward(request, response);
                break;
            case "Editar":
                String ide = request.getParameter("id");
                
                String datoEmp1 = persis.listarEmpleado();
                String[] acvEmp1 = datoEmp1.split("/");
                List<Empleado> datosEmp1 = new ArrayList<>();

                for (String scab : acvEmp1) {
                    Empleado actvo1 = new Empleado();
                    String[] listaEmp1 = scab.split(";");
                    actvo1.setCodigo(listaEmp1[0]);
                    actvo1.setNombre(listaEmp1[2]);
                    datosEmp1.add(actvo1);
                }
                request.setAttribute("SEmpleado", datosEmp1);
                
                NominaCabecera pe = new NominaCabecera();
                String info = persis.mostrarNominaCab(ide);
                String[] infoac = info.split(";");
                pe.setCodigoCab(infoac[0]);
                pe.setFechaCab(infoac[1]);
                pe.setEmpCab(infoac[2]);
                request.setAttribute("nominaCab", pe);
                request.getRequestDispatcher("editarNominaCab.jsp").forward(request, response);
                break;
            case "Actualizar":
                String codigo1 = request.getParameter("txtcodigo");
                String fecha1 = request.getParameter("txtfecha");
                String responsable1 = request.getParameter("txtresponsable");
                persis.actualizarNominaCab(codigo1, fecha1, responsable1);
                request.getRequestDispatcher("NominaServ?accion=Detalles").forward(request, response);
                break;
            case "Eliminar":
                String id2 = request.getParameter("id");
                persis.eliminarNominaCab(id2);
                request.getRequestDispatcher("NominaServ?accion=Detalles").forward(request, response);
                break;
            case "Regresar":
                for (String v : elemento) {
                    NominaCabecera ac2 = new NominaCabecera();
                    String[] listaAc = v.split(";");
                    ac2.setCodigoCab(listaAc[0]);
                    ac2.setFechaCab(listaAc[1]);
                    ac2.setEmpCab(listaAc[2]);
                    datosCab.add(ac2);
                }
                request.setAttribute("nominaCab", datosCab);
                request.getRequestDispatcher("NominaServ?accion=Detalles").forward(request, response);
                break;
            case "Mostrar":
                String ide2 = request.getParameter("id");
                NominaCabecera pe2 = new NominaCabecera();
                String info2 = persis.mostrarNominaCab(ide2);
                String[] infoac2 = info2.split(";");
                pe2.setCodigoCab(infoac2[0]);
                pe2.setFechaCab(infoac2[1]);

                String activo1 = persis.mostrarEmpleado(infoac2[2]);
                String[] nomActvo1 = activo1.split(";");

                pe2.setEmpCab(nomActvo1[2]);
                request.setAttribute("nominaCab", pe2);
                request.getRequestDispatcher("mostrarNominaCab.jsp").forward(request, response);
                break;
            case "Buscar":
                String ide3 = request.getParameter("buscarNominaCab");
                NominaCabecera pe3 = new NominaCabecera();
                String info3 = persis.mostrarNominaCab(ide3);
                String[] infoac3 = info3.split(";");
                pe3.setCodigoCab(infoac3[0]);
                pe3.setFechaCab(infoac3[1]);
                pe3.setEmpCab(infoac3[2]);
                request.setAttribute("nominaCab", pe3);
                request.getRequestDispatcher("mostrarNominaCab.jsp").forward(request, response);
                break;
            case "Detalles":
                if (elementoD != null) {
                    for (String vd : elementoD) {
                        NominaDetalle man = new NominaDetalle();
                        String[] listaMan = vd.split(";");
                        man.setCodigoDet(listaMan[0]);
                        man.setCodigoCabDet(listaMan[1]);

                        String activo2 = persis.mostrarMotivo(listaMan[2]);
                        String[] nomActvo2 = activo2.split(";");
                        man.setMotivoDet(nomActvo2[1]);

                        man.setValor(listaMan[3]);
                        datosDet.add(man);
                    }
                }
                request.setAttribute("nominaDet", datosDet);
                request.getRequestDispatcher("NominaServ?accion=Listar").forward(request, response);
                break;
            case "Mostrar ":
                String ide2d = request.getParameter("id2");
                NominaDetalle pe2d = new NominaDetalle();
                String info2d = persis.mostrarNominaDetalle(ide2d);
                String[] infoac2d = info2d.split(";");
                pe2d.setCodigoDet(infoac2d[0]);
                pe2d.setCodigoCabDet(infoac2d[1]);

                String activo = persis.mostrarMotivo(infoac2d[2]);
                String[] nomActvo = activo.split(";");
                pe2d.setMotivoDet(nomActvo[1]);

                pe2d.setValor(infoac2d[3]);
                request.setAttribute("nominaDet", pe2d);
                request.getRequestDispatcher("mostrarNominaDet.jsp").forward(request, response);
                break;
            case "Buscar ":
                String ide3d = request.getParameter("buscarNominaDet");
                NominaDetalle pe3d = new NominaDetalle();
                String info3d = persis.mostrarNominaDetalle(ide3d);
                String[] infoac3d = info3d.split(";");
                pe3d.setCodigoDet(infoac3d[0]);
                pe3d.setCodigoCabDet(infoac3d[1]);

                String activo2 = persis.mostrarMotivo(infoac3d[2]);
                String[] nomActvo2 = activo2.split(";");

                pe3d.setMotivoDet(nomActvo2[1]);
                pe3d.setValor(infoac3d[3]);
                request.setAttribute("nominaDet", pe3d);
                request.getRequestDispatcher("mostrarNominaDet.jsp").forward(request, response);
                break;
            case "Nuevo ":
                String valor = request.getParameter("id2");
                String activodato = persis.listarMotivo();
                String[] acvo = activodato.split("/");
                List<Motivo> datosmov = new ArrayList<>();

                for (String sacvo : acvo) {
                    Motivo actvo = new Motivo();
                    String[] listaAc = sacvo.split(";");
                    actvo.setCodigoMov(listaAc[0]);
                    actvo.setNombreMov(listaAc[1]);
                    datosmov.add(actvo);
                }
                request.setAttribute("SMotivo", datosmov);

                request.getRequestDispatcher("agregarNominaDet.jsp?codigoNomina=" + valor).forward(request, response);
                break;
            case "Guardar ":
                String dnominaCab = request.getParameter("txtnominaCab");
                String dmotivo = request.getParameter("txtmotivo");
                String dvalor = request.getParameter("txtvalor");
                String vmanint = persis.mostrarNominaCab(dnominaCab);
                String[] vmaintsep = vmanint.split(";");
                persis.insertarNominaDetalle(dnominaCab, dmotivo, dvalor);
                request.getRequestDispatcher("NominaServ?accion=Detalles").forward(request, response);
                break;
            case "Editar ":
                String ided = request.getParameter("id2");
                String activodato2 = persis.listarMotivo();
                String[] acvo2 = activodato2.split("/");
                List<Motivo> datosacvo2 = new ArrayList<>();

                for (String sacvo2 : acvo2) {
                    Motivo actvo2 = new Motivo();
                    String[] listaAc = sacvo2.split(";");
                    actvo2.setCodigoMov(listaAc[0]);
                    actvo2.setNombreMov(listaAc[1]);
                    datosacvo2.add(actvo2);
                }

                NominaDetalle ped = new NominaDetalle();
                String infod = persis.mostrarNominaDetalle(ided);
                String[] infoacd = infod.split(";");
                ped.setCodigoDet(infoacd[0]);
                ped.setCodigoCabDet(infoacd[1]);
                ped.setMotivoDet(infoacd[2]);
                ped.setValor(infoacd[3]);

                request.setAttribute("SMotivo", datosacvo2);

                request.setAttribute("nominaDet", ped);
                request.getRequestDispatcher("editarNominaDet.jsp").forward(request, response);
                break;
            case "Actualizar ":
                String codigod = request.getParameter("txtcodigo");
                String codigoCab = request.getParameter("txtnominaCab");
                String motivod = request.getParameter("txtmotivo");
                String valord = request.getParameter("txtvalor");

                persis.actualizarNominaDetalle(codigod, codigoCab, motivod, valord);
                request.getRequestDispatcher("NominaServ?accion=Detalles").forward(request, response);
                break;
            case "Eliminar ":
                String id2d = request.getParameter("id2");
                persis.eliminarNominaDetalle(id2d);
                request.getRequestDispatcher("NominaServ?accion=Detalles").forward(request, response);
                break;
            case "Reporte1":
                for (String vr : elementoVP) {
                    ValoresPago vpg = new ValoresPago();
                    String[] listaVp = vr.split(";");
                    vpg.setCodigoEmp(listaVp[0]);
                    vpg.setNombreEmp(listaVp[1]);
                    vpg.setNetoRecibido(listaVp[2]);
                    datosVP.add(vpg);
                }
                
                request.setAttribute("reporte1", datosVP);
                request.getRequestDispatcher("ReporteNomina1.jsp").forward(request, response);
                break;
            case "Asiento":
                persis.generarCabecera();
                persis.generarDetalle();
                
                for (String nom : elementoNomina) {
                    AsientoContableNomina acn = new AsientoContableNomina();
                    String[] listaNom = nom.split(";");
                    acn.setNumeroComprobante(listaNom[0]);
                    acn.setFechaComprobante(listaNom[1]);
                    acn.setObservacionComprobante(listaNom[2]);
                    acn.setNombreCuentaComprobante(listaNom[3]);
                    acn.setDebeComprobante(listaNom[4]);
                    acn.setHaberComprobante(listaNom[5]);
                    datosNomina.add(acn);
                }
                
                request.setAttribute("AsientoCont", datosNomina);
                request.getRequestDispatcher("AsientoContableNomina.jsp").forward(request, response);
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
