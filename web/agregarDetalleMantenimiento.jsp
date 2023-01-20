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
                    <h1>Agregar Detalle de Mantenimiento</h1>
                    <form action="MantenimientoServ" method="post">
                        <input class="form-control" type="hidden" name="txtmantenimiento" value="<%= request.getParameter("codigoMantenimiento") %>"><br> 
                        <input class="form-control" type="hidden" name="codigoMantenimiento" value="<%= request.getParameter("codigoMantenimiento") %>">
                        ACTIVO:<br> 
                        <select name="txtactivo" class="form-control">
                            <c:forEach var="SActivo" items="${SActivo}">
                                <option value="${SActivo.getCodigo()}">${SActivo.getNombre()}</option>
                            </c:forEach>
                        </select><br>
                        ACTIVIDAD:<br> 
                        <select name="txtactividad" class="form-control">
                            <c:forEach var="SActividad" items="${SActividad}">
                                <option value="${SActividad.getCodigo()}">${SActividad.getNombre()}</option>
                            </c:forEach>
                        </select><br>
                        VALOR:<br> 
                        <input class="form-control" type="number" step="0.01" name="txtvalor" autocomplete="off" required><br><br>
                        <input class="btn btn-primary" type="submit" name="accion" value="Guardar ">
                    </form>
                    <form action="MantenimientoServ" method="post">
                        <input class="form-control" type="hidden" name="codigoMantenimiento" value="<%= request.getParameter("codigoMantenimiento") %>">
                        <input class="btn btn-secondary" type="submit" name="accion" value="Regresar">
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>