

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="ISO-8859-1">
        <title>Mostrar Detalle</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    </head>
    <body>
        <div class="container">
            <div class="col-lg-6">
                <div>
                    <h1>Información Detalle Comprobante</h1>
                    <form action="ComprobanteServ" method="post">
                        CODIGO:<br>
                        <input class="form-control" type="text" name="txtcodigo" value="${detallecomprobante.getCodigo()}" readonly><br> 
                        COMPROBANTE:<br>
                        <input class="form-control" type="text" name="txtmantenimiento" value="${detallecomprobante.getCodigoComprobante()}" readonly><br> 
                        CUENTA:<br>
                        <input class="form-control" type="text" name="txtactivo" value="${detallecomprobante.getCodigoCuenta()}" readonly><br> 
                        DEBE:<br>
                        <input class="form-control" type="text" name="txtactividad" value="${detallecomprobante.getDebe()}" readonly><br> 
                        HABER:<br>
                        <input class="form-control" type="text" name="txtvalor" value="${detallecomprobante.getHaber()}" readonly><br> 
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
