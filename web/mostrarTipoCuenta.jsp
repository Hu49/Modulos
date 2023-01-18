

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
                    <h1>Información Tipo de Cuenta</h1>
                    <form action="TipoCuentaServ" method="post">
                        CODIGO:<br>
                        <input class="form-control" type="text" name="txtcodigo" value="${tipoCuenta.getCodigo()}" readonly><br> 
                        NOMBRE:<br> 
                        <input class="form-control" type="text" name="txtnombre" value="${tipoCuenta.getNombre()}" readonly><br> 
                        
                    </form>
                    <form action="TipoCuentaServ" method="post">
                        <input class="btn btn-secondary" type="submit" name="accion" value="Regresar">
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>