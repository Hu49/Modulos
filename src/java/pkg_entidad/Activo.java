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
public class Activo {
    private String codigo;
    private String nombre;
    private String fechaAdq;

    public Activo() {
    }

    public Activo(String codigo, String nombre, String fechaAdq) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.fechaAdq = fechaAdq;
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

    public String getFechaAdq() {
        return fechaAdq;
    }

    public void setFechaAdq(String fechaAdq) {
        this.fechaAdq = fechaAdq;
    }
    
}
