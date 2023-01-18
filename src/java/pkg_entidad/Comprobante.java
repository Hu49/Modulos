/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg_entidad;

/**
 *
 * @author hudie
 */
public class Comprobante {
    private Integer codigo;
    private String fecha;
    private String observaciones;

    public Comprobante() {
    }

    public Comprobante(Integer codigo, String fecha, String responsable) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.observaciones = responsable;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String responsable) {
        this.observaciones = responsable;
    }
    
}
