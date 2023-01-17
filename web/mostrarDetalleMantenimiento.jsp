

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
                    <h1>Información Detalle Mantenimiento</h1>
                    <form action="MantenimientoServ" method="post">
                        CODIGO:<br>
                        <input class="form-control" type="text" name="txtcodigo" value="${detallemantenimiento.getCodigo()}" readonly><br> 
                        MANTENIMIENTO:<br>
                        <input class="form-control" type="text" name="txtmantenimiento" value="${detallemantenimiento.getCodigoMantenimiento()}" readonly><br> 
                        ACTIVO:<br>
                        <input class="form-control" type="text" name="txtactivo" value="${detallemantenimiento.getActivo()}" readonly><br> 
                        ACTIVIDAD:<br>
                        <input class="form-control" type="text" name="txtactividad" value="${detallemantenimiento.getActividad()}" readonly><br> 
                        VALOR:<br>
                        <input class="form-control" type="text" name="txtvalor" value="${detallemantenimiento.getValor()}" readonly><br> 
                    </form>
                    <form action="MantenimientoServ" method="post">
                        <input class="form-control" type="hidden" name="codigoMantenimiento" value="${detallemantenimiento.getCodigoMantenimiento()}">
                        <input class="btn btn-secondary" type="submit" name="accion" value="Regresar">
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
