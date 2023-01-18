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
public class DetalleComprobante {
    private String codigo;
    private Integer codigoComprobante;
    private String codigoCuenta;
    private String cuenta;
    private float debe;
    private float haber;

    public DetalleComprobante() {
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigoComprobante() {
        return codigoComprobante;
    }

    public void setCodigoComprobante(Integer codigoComprobante) {
        this.codigoComprobante = codigoComprobante;
    }

    public String getCodigoCuenta() {
        return codigoCuenta;
    }

    public void setCodigoCuenta(String codigoCuenta) {
        this.codigoCuenta = codigoCuenta;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public float getDebe() {
        return debe;
    }

    public void setDebe(float debe) {
        this.debe = debe;
    }

    public float getHaber() {
        return haber;
    }

    public void setHaber(float haber) {
        this.haber = haber;
    }


    
    
}
