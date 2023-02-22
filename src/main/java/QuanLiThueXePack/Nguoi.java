/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuanLiThueXePack;

/**
 *
 * @author HP
 */
public abstract class Nguoi {
    private String hoTen;
    private String soCMND;
    private String diaChi;
    private String soDt;

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getSoCMND() {
        return soCMND;
    }

    public void setSoCMND(String soCMND) {
        this.soCMND = soCMND;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSoDt() {
        return soDt;
    }

    public void setSoDt(String soDt) {
        this.soDt = soDt;
    }

    public Nguoi() {
    }

    public Nguoi(String hoTen, String soCMND, String diaChi, String soDt) {
        this.hoTen = hoTen;
        this.soCMND = soCMND;
        this.diaChi = diaChi;
        this.soDt = soDt;
    }
    
    @Override
    public abstract String toString();
}
