
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="ISO-8859-1">
        <title>Editar</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
        <script>
            $(function () {
                $("#txtactivo").val('${detallemantenimiento.getCodigoActivo()}');
                $("#txtactividad").val('${detallemantenimiento.getCodigoActividad()}');
            });
        </script>
    </head>
    <body>
        <div class="container">
            <div class="col-lg-6">
                <div>
                    <h1>Editar Detalle de Nomina</h1>
                    <form action="NominaServ" method="post">
                        CODIGO:<br> 
                        <input class="form-control" type="text" name="txtcodigo" value="${nominaDet.getCodigoDet()}" readonly><br> 
                        <input class="form-control" type="hidden" name="codigoNomina" value="${nominaDet.getCodigoCabDet()}">
                        CODIGO CABECERA:<br>
                        <input class="form-control" type="text" name="txtnominaCab" value="${nominaDet.getCodigoCabDet()}" readonly><br> 

                        MOTIVO:<br> 
                        <select name="txtmotivo" class="form-control" id="txtmotivo">
                            <c:forEach var="SMotivo" items="${SMotivo}">
                                <option value="${SMotivo.getCodigoMov()}">${SMotivo.getNombreMov()}</option>
                            </c:forEach>
                        </select><br>
                        VALOR:<br> 
                        <input class="form-control" type="text" name="txtvalor" value="${nominaDet.getValor()}" autocomplete="off" required><br><br>
                        <input class="btn btn-primary" type="submit" name="accion" value="Actualizar ">
                    </form>
                    <form action="NominaServ" method="post">
                        <input class="form-control" type="hidden" name="codigoMantenimiento" value="${nominaDet.getCodigoCabDet()}">
                        <input class="btn btn-secondary" type="submit" name="accion" value="Regresar">
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>

