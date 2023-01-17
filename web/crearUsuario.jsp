
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="ISO-8859-1">
        <title>Crear usuario</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    </head>
    <body>
        <div class="container">
            <div class="col-lg-6">
                <div>
                    <h1>Creación de usuario</h1>
                    <form action="LoginServ" method="post">
                        USUARIO:<br> 
                        <input class="form-control" type="text" name="txtuser" autocomplete="off" required><br>
                        PASSWORD:<br> 
                        <input class="form-control" type="text" name="txtpass" autocomplete="off" required><br>
                        <input class="btn btn-primary" type="submit" name="accion" value="Guardar">
                    </form>
                    <form action="LoginServ" method="post">
                        <input class="btn btn-secondary" type="submit" name="accion" value="Regresar">
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>