/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Asus
 */
public class Playstation extends BaseModel{
    
    private int idPs;
    private String namaPs;
    private String tipePs;
    private String statusPs;
    private int hargaPerJam;

    public Playstation() {
        super();
    }

    public int getIdPs() {
        return idPs;
    }

    public void setIdPs(int idPs) {
        this.idPs = idPs;
    }

    public String getNamaPs() {
        return namaPs;
    }

    public void setNamaPs(String namaPs) {
        this.namaPs = namaPs;
    }

    public String getTipePs() {
        return tipePs;
    }

    public void setTipePs(String tipePs) {
        this.tipePs = tipePs;
    }

    public String getStatusPs() {
        return statusPs;
    }

    public void setStatusPs(String statusPs) {
        this.statusPs = statusPs;
    }

    public int getHargaPerJam() {
        return hargaPerJam;
    }

    public void setHargaPerJam(int hargaPerJam) {
        this.hargaPerJam = hargaPerJam;
    }
}
