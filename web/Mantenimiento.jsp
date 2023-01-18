
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="ISO-8859-1">
        <title>CRUD Mantenimiento</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    </head>
    <body>
        <div class="container" style="text-align: center">
            <div>
                <h3>Mantenimientos</h3>
                <form action="MantenimientoServ" method="post">
                    <input class="btn btn-success btn-lg" type="submit" name="accion" value="Nuevo">
                    <input class="btn btn-success btn-lg" type="submit" name="accion" value="Buscar">
                    <input type="text" name="buscarMantenimiento">
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
                            <th class="text-center">RESPONSABLE</th>
                            <th class="text-center" colspan="4">ACCIONES</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="mantenimiento" items="${mantenimiento}">
                            <tr>
                                <c:choose>
                                    <c:when test="${mantenimiento.getCodigo()==mantenimientoD[0].getCodigoMantenimiento()}">
                                        <th class="text-center">S</th>
                                    </c:when>    
                                    <c:otherwise>
                                        <th class="text-center"></th>
                                    </c:otherwise>
                                </c:choose>
                                <th class="text-center">${mantenimiento.getCodigo()}</th>
                                <th class="text-center">${mantenimiento.getFecha()}</th>
                                <th class="text-center">${mantenimiento.getResponsable()}</th>
                                <th>
                                    <form class="text-center" action="MantenimientoServ" method="post">
                                        <input type="hidden" name="id" value="${mantenimiento.getCodigo()}">
                                        <input class="btn btn-info" type="submit" name="accion" value="Mostrar">
                                        <input class="btn btn-warning" type="submit" name="accion" value="Editar"> 
                                        <input class="btn btn-danger" type="submit" name="accion" value="Eliminar">
                                    </form>
                                </th>
                                <th>
                                    <form class="text-center" action="MantenimientoServ" method="post">
                                        <input type="hidden" name="codigoMantenimiento" value="${mantenimiento.getCodigo()}">
                                        <input class="btn btn-info" type="submit" name="accion" value="Detalles">
                                    </form>
                                </th>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <div>
                <h3>Detalles Mantenimiento</h3>
                <form action="MantenimientoServ" method="post">
                    <input type="hidden" name="id2" value="${codigoMantenimiento}">
                    <input class="btn btn-success btn-lg" type="submit" name="accion" value="Nuevo ">
                    <input class="btn btn-success btn-lg" type="submit" name="accion" value="Buscar ">
                    <input type="text" name="buscarDetalleMantenimiento">
                </form>
            </div>
            <br>
            <div>
                <table class="table table-bordered" style="margin: 0 auto;">
                    <thead>
                        <tr>
                            <th class="text-center">CODIGO</th>
                            <th class="text-center">ACTIVO</th>
                            <th class="text-center">ACTIVIDAD</th>
                            <th class="text-center">VALOR</th>
                            <th class="text-center" colspan="3">ACCIONES</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="mantenimientoD" items="${mantenimientoD}">
                            <tr>
                                <th class="text-center">${mantenimientoD.getCodigo()}</th>
                                <th class="text-center">${mantenimientoD.getActivo()}</th>
                                <th class="text-center">${mantenimientoD.getActividad()}</th>
                                <th class="text-center">${mantenimientoD.getValor()}</th>
                                <th>
                                    <form class="text-center" action="MantenimientoServ" method="post">
                                        <input type="hidden" name="id2" value="${mantenimientoD.getCodigo()}">
                                        <input type="hidden" name="codigoMantenimiento" value="<%= request.getParameter("codigoMantenimiento")%>">
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
            <a href="MenuMantenimiento.jsp">
                <br><button class="btn btn-info btn-lg">Regresar</button>
            </a>
        </div>
    </body>
</html>