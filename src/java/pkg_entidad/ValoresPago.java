/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg_entidad;

/**
 *
 * @author josep
 */
public class ValoresPago {
    private String codigoEmp;
    private String nombreEmp;
    private String netoRecibido;

    public ValoresPago() {
    }

    public ValoresPago(String codigoEmp, String nombreEmp, String netoRecibido) {
        this.codigoEmp = codigoEmp;
        this.nombreEmp = nombreEmp;
        this.netoRecibido = netoRecibido;
    }

    public String getCodigoEmp() {
        return codigoEmp;
    }

    public void setCodigoEmp(String codigoEmp) {
        this.codigoEmp = codigoEmp;
    }

    public String getNombreEmp() {
        return nombreEmp;
    }

    public void setNombreEmp(String nombreEmp) {
        this.nombreEmp = nombreEmp;
    }

    public String getNetoRecibido() {
        return netoRecibido;
    }

    public void setNetoRecibido(String netoRecibido) {
        this.netoRecibido = netoRecibido;
    }
}
