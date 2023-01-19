<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="ISO-8859-1">
        <title>Valores a pagar Empleados</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/html2pdf.js/0.10.1/html2pdf.bundle.min.js" integrity="sha512-GsLlZN/3F2ErC5ifS5QtgpiJtWd43JWSuIgh7mbzZ8zBps+dvLusV+eNQATqgA/HdeKFVgA5v3S/cIrLF7QnIg==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
        <script>
            function downloadPDF() {
                const element = document.getElementById("imprimir");
                var opt = {
                    margin: [15, 0, 15, 0],
                    filename: `Valores a pagar.pdf`,
                    image: { type: 'jpeg', quality: 1 },
                    html2canvas: {
                      dpi: 192,
                      scale:4,
                      letterRendering: true,
                      useCORS: true,
                      width: 1170
                    },
                    jsPDF: { unit: 'mm', format: 'a4', orientation: 'portrait' }
                };
                html2pdf().set(opt).from(element).save();
                
            }
            
        </script>
    </head>
    <body>
        <div class="container" style="text-align: center" id="imprimir">
            <div>
                <h3>Valores a Pagar</h3>
            </div>
            <br>
            <div>
                <table class="table table-bordered" style="margin: 0 auto;">
                    <thead>
                        <tr>
                            <th class="text-center">CODIGO</th>
                            <th class="text-center">EMPLEADO</th>
                            <th class="text-center">NETO RECIBIDO</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="reporte1" items="${reporte1}">
                            <tr>
                                <th class="text-center">${reporte1.getCodigoEmp()}</th>
                                <th class="text-center">${reporte1.getNombreEmp()}</th>
                                <th class="text-center">${reporte1.getNetoRecibido()}</th>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <div  class="container" style="text-align: center">
            <br>
            <button class="btn btn-warning btn-lg generate-pdf" onclick="downloadPDF()">Imprimir</button><br><br>
            <a href="MenuNomina.jsp">
                <button class="btn btn-info btn-lg">Regresar</button>
            </a>
        </div>
    </body>
</html>
