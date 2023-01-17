

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="ISO-8859-1">
        <title>Mostrar</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    </head>
    <body>
        <div class="container">
            <div class="col-lg-6">
                <div>
                    <h1>Información Mantenimiento</h1>
                    <form action="MantenimientoServ" method="post">
                        CODIGO:<br>
                        <input class="form-control" type="text" name="txtcodigo" value="${mantenimiento.getCodigo()}" readonly><br> 
                        FECHA:<br> 
                        <input class="form-control" type="text" name="txtfecha" value="${mantenimiento.getFecha()}" readonly><br> 
                        RESPONSABLE:<br>
                        <input class="form-control" type="text" name="txtresponsable" value="${mantenimiento.getResponsable()}" readonly><br> 
                    </form>
                    <form action="MantenimientoServ" method="post">
                        <input class="btn btn-secondary" type="submit" name="accion" value="Regresar">
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>