<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <title>Selección de módulo</title>
    </head>
    <body>
        <div class="container" style="text-align: center">
            
            <h3>Bienvenido/a: ${usuario}</h3><br>
            <h1>Selección de módulo</h1><br>
            <a href="MenuMantenimiento.jsp">
                <button class="btn btn-info btn-lg">Mantenimiento de activos</button>
            </a><br><br>
            <a href="MenuNomina.jsp">
                <button class="btn btn-info btn-lg">Nómina</button>
            </a><br><br>
            <a href="MenuContabilidad.jsp">
                <button class="btn btn-info btn-lg">Contabilidad</button>
            </a><br>
            <br>
            <form action="LoginServ" method="post">
                <input class="btn btn-secondary" type="submit" name="accion" value="Cerrar sesion">
            </form>
        </div>
    </body>
</html>