
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="ISO-8859-1">
        <title>CRUD Actividad</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    </head>
    <body>
        <div class="container" style="text-align: center">
            <div>
                <h3>Actividades</h3>
                <form action="ActividadServ" method="post"> 
                    <input class="btn btn-success btn-lg" type="submit" name="accion" value="Nuevo">
                    <input class="btn btn-success btn-lg" type="submit" name="accion" value="Buscar">
                    <input type="text" name="buscarActividad">
                </form>
            </div>
            <br>
            <div>
                <table class="table table-bordered" style="margin: 0 auto;">
                    <thead>
                        <tr>
                            <th class="text-center">CODIGO</th>
                            <th class="text-center">NOMBRE</th>
                            <th class="text-center" colspan="3">ACCIONES</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="actividad" items="${actividad}">
                            <tr>
                                <th class="text-center">${actividad.getCodigo()}</th>
                                <th class="text-center">${actividad.getNombre()}</th>
                                <th>
                                    <form class="text-center" action="ActividadServ" method="post">
                                        <input type="hidden" name="id" value="${actividad.getCodigo()}">
                                        <input class="btn btn-info" type="submit" name="accion" value="Mostrar">
                                        <input class="btn btn-warning" type="submit" name="accion" value="Editar"> 
                                        <input class="btn btn-danger" type="submit" name="accion" value="Eliminar">
                                    </form>
                                </th>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <a href="Menu.jsp">
                <br><button class="btn btn-info btn-lg">Regresar</button>
            </a>
        </div>
    </body>
</html>