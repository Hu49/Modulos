/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg_entidad;

/**
 *
 * @author josep
 */
public class Motivo {
    private String codigoMov;
    private String nombreMov;
    private String signo;
    private String ctacontable;

    public Motivo() {
    }

    public Motivo(String codigoMov, String nombreMov, String signo, String ctacontable) {
        this.codigoMov = codigoMov;
        this.nombreMov = nombreMov;
        this.signo = signo;
        this.ctacontable = ctacontable;
    }

    public String getCodigoMov() {
        return codigoMov;
    }

    public void setCodigoMov(String codigoMov) {
        this.codigoMov = codigoMov;
    }

    public String getNombreMov() {
        return nombreMov;
    }

    public void setNombreMov(String nombreMov) {
        this.nombreMov = nombreMov;
    }

    public String getSigno() {
        return signo;
    }

    public void setSigno(String signo) {
        this.signo = signo;
    }

    public String getCtacontable() {
        return ctacontable;
    }

    public void setCtacontable(String ctacontable) {
        this.ctacontable = ctacontable;
    }    
}
