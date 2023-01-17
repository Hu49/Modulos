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
public class Actividad {
    private String codigo;
    private String nombre;
    private String detalle;

    public Actividad() {
    }

    public Actividad(String codigo, String nombre, String detalle) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.detalle = detalle;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }
    
    
}
