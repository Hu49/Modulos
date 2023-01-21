
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="ISO-8859-1">
        <title>CRUD Nomina</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    </head>
    <body>
        <div class="container" style="text-align: center">
            <div>
                <h3>Nominas</h3>
                <form action="NominaServ" method="post">
                    <input class="btn btn-success btn-lg" type="submit" name="accion" value="Nuevo">
                    <input class="btn btn-success btn-lg" type="submit" name="accion" value="Buscar">
                    <input type="text" name="buscarNominaCab">
                    <input class="btn btn-secondary btn-lg" type="submit" name="accion" value="Asiento">
                </form>
            </div>
            <br>
            <div>
                <table class="table table-bordered" style="margin: 0 auto;">
                    <thead>
                        <tr>

                            <th class="text-center">ACTUAL</th>
                            <th class="text-center">CODIGO</th>
                            <th class="text-center">FECHA</th>
                            <th class="text-center">RESPONSABLE</th>
                            <th class="text-center" colspan="4">ACCIONES</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="nominaCab" items="${nominaCab}">
                            <c:choose>
                                <c:when test="${nominaCab.getCodigoCab()==codigoNomina}">
                                <th class="text-center">X</th>
                                </c:when>    
                                <c:otherwise>
                                <th class="text-center"></th>
                                </c:otherwise>
                            </c:choose>
                        <th class="text-center">${nominaCab.getCodigoCab()}</th>
                        <th class="text-center">${nominaCab.getFechaCab()}</th>
                        <th class="text-center">${nominaCab.getEmpCab()}</th>
                        <th>
                            <form class="text-center" action="NominaServ" method="post">
                                <input type="hidden" name="id" value="${nominaCab.getCodigoCab()}">
                                <input class="btn btn-info" type="submit" name="accion" value="Mostrar">
                                <input class="btn btn-warning" type="submit" name="accion" value="Editar"> 
                                <input class="btn btn-danger" type="submit" name="accion" value="Eliminar">
                            </form>
                        </th>
                        <th>
                            <form class="text-center" action="NominaServ" method="post">
                                <input type="hidden" name="codigoNomina" value="${nominaCab.getCodigoCab()}">
                                <input class="btn btn-info" type="submit" name="accion" value="Detalles">
                            </form>
                        </th>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

            <div>
                <h3>Detalles Nomina</h3>
                <form action="NominaServ" method="post">
                    <input type="hidden" name="id2" value="${codigoNomina}">
                    <input class="btn btn-success btn-lg" type="submit" name="accion" value="Nuevo ">
                    <input class="btn btn-success btn-lg" type="submit" name="accion" value="Buscar ">
                    <input type="text" name="buscarNominaDet">
                </form>
            </div>
            <br>
            <div>
                <table class="table table-bordered" style="margin: 0 auto;">
                    <thead>
                        <tr>
                            <th class="text-center">CODIGO</th>
                            <th class="text-center">CODIGO DE CABECERA</th>
                            <th class="text-center">MOTIVO</th>
                            <th class="text-center">VALOR</th>
                            <th class="text-center" colspan="3">ACCIONES</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="nominaDet" items="${nominaDet}">
                            <tr>
                                <th class="text-center">${nominaDet.getCodigoDet()}</th>
                                <th class="text-center">${nominaDet.getCodigoCabDet()}</th>
                                <th class="text-center">${nominaDet.getMotivoDet()}</th>
                                <th class="text-center">${nominaDet.getValor()}</th>
                                <th>
                                    <form class="text-center" action="NominaServ" method="post">
                                        <input type="hidden" name="id2" value="${nominaDet.getCodigoDet()}">
                                        <input type="hidden" name="codigoNomina" value="<%= request.getParameter("codigoNomina")%>">
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
            <a href="MenuNomina.jsp">
                <br><button class="btn btn-info btn-lg">Regresar</button>
            </a>
        </div>

    </body>
</html>