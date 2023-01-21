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
                    <h1>Agregar Motivo</h1>
                    <form action="MotivoServ" method="post">
                        DESCRIPCIÓN:<br> 
                        <input class="form-control" type="text" name="txtnombre" autocomplete="off" required><br>
                        <div class="form-check">
                            <input type="radio" name="txtsigno" value="+" required>
                            <label for="+">Ingreso</label>
                        </div>
                        <div class="form-check">
                            <input type="radio" name="txtsigno" value="-" required>
                            <label for="+">Egreso</label><br><br>
                        </div>
                        CUENTA:<br> 
                        <select name="txtcuenta" class="form-control">
                            <c:forEach var="SCuenta" items="${SCuenta}">
                                <option value="${SCuenta.getCodigo()}">${SCuenta.getNombre()}</option>
                            </c:forEach>
                        </select><br>
                        <input class="btn btn-primary" type="submit" name="accion" value="Guardar">
                    </form>
                    <form action="MotivoServ" method="post">
                        <input class="btn btn-secondary" type="submit" name="accion" value="Regresar">
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>