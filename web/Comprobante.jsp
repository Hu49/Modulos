
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="ISO-8859-1">
        <title>CRUD Comprobante</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    </head>
    <body>
        <div class="container" style="text-align: center">
            <div>
                <h3>Comprobantes</h3>
                <form action="ComprobanteServ" method="post">
                    <input class="btn btn-success btn-lg" type="submit" name="accion" value="Nuevo">
                    <input class="btn btn-success btn-lg" type="submit" name="accion" value="Buscar">
                    <input type="text" name="buscarComprobante">
                </form>
            </div>
            <br>
            <div>
                <table class="table table-bordered" style="margin: 0 auto;">
                    <thead>
                        <tr>
                            <th class="text-center">SELECCIONADO</th>
                            <th class="text-center">CODIGO</th>
                            <th class="text-center">FECHA</th>
                            <th class="text-center">OBSERVACIONES</th>
                            <th class="text-center" colspan="4">ACCIONES</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="comprobante" items="${comprobante}">
                            <tr>
                                <c:choose>
                                    <c:when test="${comprobante.getCodigo()==codigoComprobante}">
                                        <th class="text-center">S</th>
                                    </c:when>    
                                    <c:otherwise>
                                        <th class="text-center"></th>
                                    </c:otherwise>
                                </c:choose>
                                <th class="text-center">${comprobante.getCodigo()}</th>
                                <th class="text-center">${comprobante.getFecha()}</th>
                                <th class="text-center">${comprobante.getObservaciones()}</th>
                                <th>
                                    <form class="text-center" action="ComprobanteServ" method="post">
                                        <input type="hidden" name="id" value="${comprobante.getCodigo()}">
                                        <input class="btn btn-info" type="submit" name="accion" value="Mostrar">
                                        <input class="btn btn-warning" type="submit" name="accion" value="Editar"> 
                                        <input class="btn btn-danger" type="submit" name="accion" value="Eliminar">
                                    </form>
                                </th>
                                <th>
                                    <form class="text-center" action="ComprobanteServ" method="post">
                                        <input type="hidden" name="codigoComprobante" value="${comprobante.getCodigo()}">
                                        <input class="btn btn-info" type="submit" name="accion" value="Detalles">
                                    </form>
                                </th>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <div>
                <h3>Detalles Comprobante</h3>
                <form action="ComprobanteServ" method="post">
                    <input type="hidden" name="id2" value="${codigoComprobante}">
                    <input class="btn btn-success btn-lg" type="submit" name="accion" value="Nuevo ">
                    <input class="btn btn-success btn-lg" type="submit" name="accion" value="Buscar ">
                    <input type="text" name="buscarDetalleComprobante">
                </form>
            </div>
            <br>
            <div>
                <table class="table table-bordered" style="margin: 0 auto;">
                    <thead>
                        <tr>
                            <th class="text-center">CODIGO</th>
                            <th class="text-center">CUENTA</th>
                            <th class="text-center">DEBE</th>
                            <th class="text-center">HABER</th>
                            <th class="text-center" colspan="3">ACCIONES</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="comprobanteD" items="${comprobanteD}">
                            <tr>
                                <th class="text-center">${comprobanteD.getCodigo()}</th>
                                <th class="text-center">${comprobanteD.getCodigoCuenta()}</th>
                                <th class="text-center">${comprobanteD.getDebe()}</th>
                                <th class="text-center">${comprobanteD.getHaber()}</th>
                                <th>
                                    <form class="text-center" action="ComprobanteServ" method="post">
                                        <input type="hidden" name="id2" value="${comprobanteD.getCodigo()}">
                                        <input type="hidden" name="codigoComprobante" value="<%= request.getParameter("codigoComprobante")%>">
                                        <input class="btn btn-info" type="submit" name="accion" value="Mostrar ">
                                        <input class="btn btn-warning" type="submit" name="accion" value="Editar "> 
                                        <input class="btn btn-danger" type="submit" name="accion" value="Eliminar ">
                                    </form>
                                </th>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <a href="MenuContabilidad.jsp">
                <br><button class="btn btn-info btn-lg">Regresar</button>
            </a>
        </div>
    </body>
</html>