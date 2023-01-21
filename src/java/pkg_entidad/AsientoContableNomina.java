/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg_entidad;

/**
 *
 * @author josep
 */
public class AsientoContableNomina {
    private String numeroComprobante;
    private String fechaComprobante;
    private String observacionComprobante;
    private String nombreCuentaComprobante;
    private String debeComprobante;
    private String haberComprobante;

    public AsientoContableNomina() {
    }

    public AsientoContableNomina(String numeroComprobante, String fechaComprobante, String observacionComprobante, String nombreCuentaComprobante, String debeComprobante, String haberComprobante) {
        this.numeroComprobante = numeroComprobante;
        this.fechaComprobante = fechaComprobante;
        this.observacionComprobante = observacionComprobante;
        this.nombreCuentaComprobante = nombreCuentaComprobante;
        this.debeComprobante = debeComprobante;
        this.haberComprobante = haberComprobante;
    }

    public String getNumeroComprobante() {
        return numeroComprobante;
    }

    public void setNumeroComprobante(String numeroComprobante) {
        this.numeroComprobante = numeroComprobante;
    }

    public String getFechaComprobante() {
        return fechaComprobante;
    }

    public void setFechaComprobante(String fechaComprobante) {
        this.fechaComprobante = fechaComprobante;
    }

    public String getObservacionComprobante() {
        return observacionComprobante;
    }

    public void setObservacionComprobante(String observacionComprobante) {
        this.observacionComprobante = observacionComprobante;
    }

    public String getNombreCuentaComprobante() {
        return nombreCuentaComprobante;
    }

    public void setNombreCuentaComprobante(String nombreCuentaComprobante) {
        this.nombreCuentaComprobante = nombreCuentaComprobante;
    }

    public String getDebeComprobante() {
        return debeComprobante;
    }

    public void setDebeComprobante(String debeComprobante) {
        this.debeComprobante = debeComprobante;
    }

    public String getHaberComprobante() {
        return haberComprobante;
    }

    public void setHaberComprobante(String haberComprobante) {
        this.haberComprobante = haberComprobante;
    }
}
