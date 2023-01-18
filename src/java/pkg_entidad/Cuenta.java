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
public class Cuenta {
    private String codigo;
    private String nombre;
    private String tipoCuenta;

    public Cuenta() {
    }

    public Cuenta(String codigo, String nombre, String tipoCuenta) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.tipoCuenta = tipoCuenta;
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

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }
    
    
}
