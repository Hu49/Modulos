/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg_entidad;

/**
 *
 * @author josep
 */
public class NominaCabecera {
    private String codigoCab;
    private String fechaCab;
    private String empCab;

    public NominaCabecera() {
    }

    public NominaCabecera(String codigoCab, String fechaCab, String empCab) {
        this.codigoCab = codigoCab;
        this.fechaCab = fechaCab;
        this.empCab = empCab;
    }

    public String getCodigoCab() {
        return codigoCab;
    }

    public void setCodigoCab(String codigoCab) {
        this.codigoCab = codigoCab;
    }

    public String getFechaCab() {
        return fechaCab;
    }

    public void setFechaCab(String fechaCab) {
        this.fechaCab = fechaCab;
    }

    public String getEmpCab() {
        return empCab;
    }

    public void setEmpCab(String empCab) {
        this.empCab = empCab;
    }

    
}
