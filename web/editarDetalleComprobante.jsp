
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
            $(function(){
                $("#txtcuenta").val( '${detallecomprobante.getCodigoCuenta()}' );
            });
        </script>
    </head>
    <body>
        <div class="container">
            <div class="col-lg-6">
                <div>
                    <h1>Editar Detalle de Comprobante</h1>
                    <form action="ComprobanteServ" method="post">
                        CODIGO:<br> 
                        <input class="form-control" type="text" name="txtcodigo" value="${detallecomprobante.getCodigo()}" readonly><br> 
                        <input class="form-control" type="hidden" name="codigoComprobante" value="${detallecomprobante.getCodigoComprobante()}">
                        COMPROBANTE:<br>
                        <input class="form-control" type="text" name="txtcomprobante" value="${detallecomprobante.getCodigoComprobante()}" readonly><br> 
                        CUENTA:<br> 
                        <select name="txtcuenta" class="form-control" id="txtcuenta">
                            <c:forEach var="SCuenta" items="${SCuenta}">
                                <option value="${SCuenta.getCodigo()}">${SCuenta.getNombre()}</option>
                            </c:forEach>
                        </select><br>
                        DEBE:<br> 
                        <input class="form-control" type="number" name="txtdebe" step="0.01" value="${detallecomprobante.getDebe()}" autocomplete="off" required><br><br>
                        HABER:<br> 
                        <input class="form-control" type="number" name="txthaber" step="0.01" value="${detallecomprobante.getHaber()}" autocomplete="off" required><br><br>
                        <input class="btn btn-primary" type="submit" name="accion" value="Actualizar ">
                    </form>
                    <form action="ComprobanteServ" method="post">
                        <input class="form-control" type="hidden" name="codigoComprobante" value="${detallecomprobante.getCodigoComprobante()}">
                        <input class="btn btn-secondary" type="submit" name="accion" value="Regresar">
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>

