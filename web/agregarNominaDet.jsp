
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="ISO-8859-1">
        <title>Agregar</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    </head>
    <body>
        <div class="container">
            <div class="col-lg-6">
                <div>
                    <h1>Agregar Detalle de Nomina</h1>
                    <form action="NominaServ" method="post">
                        <input class="form-control" type="hidden" name="txtnominaCab" value="<%= request.getParameter("codigoNomina") %>"><br> 
                        <input class="form-control" type="hidden" name="codigoNomina" value="<%= request.getParameter("codigoNomina") %>">
                        MOTIVO:<br> 
                        <select name="txtmotivo" class="form-control">
                            <c:forEach var="SMotivo" items="${SMotivo}">
                                <option value="${SMotivo.getCodigoMov()}">${SMotivo.getNombreMov()}</option>
                            </c:forEach>
                        </select><br>
                        VALOR:<br> 
                        <input class="form-control" type="text" name="txtvalor" autocomplete="off" required><br><br>
                        <input class="btn btn-primary" type="submit" name="accion" value="Guardar ">
                    </form>
                    <form action="NominaServ" method="post">
                        <input class="form-control" type="hidden" name="codigoNomina" value="<%= request.getParameter("codigoNomina") %>">
                        <input class="btn btn-secondary" type="submit" name="accion" value="Regresar">
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
