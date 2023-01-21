
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="ISO-8859-1">
        <title>Ingreso</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
        <link rel="stylesheet" href="/resources/demos/style.css">
    </head>
    <body>
        <h5>Instancia 2</h5>
        <div class="container">
            <div class="col-lg-6">
                <div>
                    <h1>Ingreso</h1>
                    <form action="LoginServ" method="post">
                        Usuario:<br> 
                        <input class="form-control" type="text" name="txtusuario" autocomplete="off" required><br> 
                        Contraseña:<br> 
                        <input class="form-control" type="password" name="txtpass" autocomplete="off" required><br>
                        <input class="btn btn-primary" type="submit" name="accion" value="Login">
                    </form>
                    <form action="LoginServ" method="post">
                        <input class="btn btn-secondary" type="submit" name="accion" value="Crear">
                    </form>
                    <h3>${info}</h3><br>
                </div>
            </div>
        </div>
    </body>
</html>
