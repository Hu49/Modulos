

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="ISO-8859-1">
        <title>Actualizar</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
        <link rel="stylesheet" href="/resources/demos/style.css">
        <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
        <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
        
    </head>
    <body>
        <div class="container">
            <div class="col-lg-6">
                <div>
                    <h1>Actualizar Tipo de Cuenta</h1>
                    <form action="TipoCuentaServ" method="post">
                        <input class="form-control" type="hidden" name="txtcodigo" value="${tipoCuenta.getCodigo()}"><br> 
                        NOMBRE:<br> 
                        <input class="form-control" type="text" name="txtnombre" value="${tipoCuenta.getNombre()}" required><br> 
                        
                        <input class="btn btn-primary" type="submit" name="accion" value="Actualizar">
                    </form>
                    <form action="TipoCuentaServ" method="post">
                        <input class="btn btn-secondary" type="submit" name="accion" value="Regresar">
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>