<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <title>Menú de mantenimiento</title>
    </head>
    <body>
        <div class="container" style="text-align: center">
            <h1>Menú de Contabilidad</h1>
            <h3>Bienvenido/a: ${usuario}</h3><br>
            <a href="TipoCuentaServ?accion=Listar">
                <button class="btn btn-info btn-lg">Tipo de Cuenta</button>
            </a><br><br>
            <a href="CuentaServ?accion=Listar">
                <button class="btn btn-info btn-lg">Cuenta</button>
            </a><br><br>
            <a href="ComprobanteServ?accion=Detalles">
                <button class="btn btn-danger btn-lg">Comprobante</button>
            </a><br><br>
<!--            <h2>Reportes</h2><br>
            <a href="MantenimientoServ?accion=Reporte1">
                <button class="btn btn-warning btn-lg">Gastos por activo</button>
            </a><br><br>
            <a href="Modulos.jsp">
                <button class="btn btn-primary btn-lg">Regresar</button>
            </a>-->
            <br><br>
        </div>
    </body>
</html>
