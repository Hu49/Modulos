/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg_entidad;

/**
 *
 * @author josep
 */
public class NominaDetalle {
    private String codigoDet;
    private String codigoCabDet;
    private String motivoDet;
    private String valor;

    public NominaDetalle() {
    }

    public NominaDetalle(String codigoDet, String codigoCabDet, String motivoDet, String valor) {
        this.codigoDet = codigoDet;
        this.codigoCabDet = codigoCabDet;
        this.motivoDet = motivoDet;
        this.valor = valor;
    }

    public String getCodigoDet() {
        return codigoDet;
    }

    public void setCodigoDet(String codigoDet) {
        this.codigoDet = codigoDet;
    }

    public String getCodigoCabDet() {
        return codigoCabDet;
    }

    public void setCodigoCabDet(String codigoCabDet) {
        this.codigoCabDet = codigoCabDet;
    }

    public String getMotivoDet() {
        return motivoDet;
    }

    public void setMotivoDet(String motivoDet) {
        this.motivoDet = motivoDet;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }    
}
