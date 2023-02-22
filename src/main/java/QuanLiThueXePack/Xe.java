/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuanLiThueXePack;

import java.util.Date;

/**
 *
 * @author HP
 */
public abstract class Xe {
    private String bienSo;
    private String tenXe;
    private int trongTai;
    private Date ngayDangKiem;
    private String loaiBang;

    public Xe() {
    }
    
    public Xe(String bienSo, String tenXe, int trongTai, Date ngayDangKiem, String loaiBang) {
        this.bienSo = bienSo;
        this.tenXe = tenXe;
        this.trongTai = trongTai;
        this.ngayDangKiem = ngayDangKiem;
        this.loaiBang = loaiBang;
    }

    public int getTrongTai() {
        return trongTai;
    }

    public void setTrongTai(int trongTai) {
        this.trongTai = trongTai;
    }
    
    @Override
    public abstract String toString();

    public String getBienSo() {
        return bienSo;
    }

    public void setBienSo(String bienSo) {
        this.bienSo = bienSo;
    }

    public String getTenXe() {
        return tenXe;
    }

    public void setTenXe(String tenXe) {
        this.tenXe = tenXe;
    }

    public Date getNgayDangKiem() {
        return ngayDangKiem;
    }

    public void setNgayDangKiem(Date ngayDangKiem) {
        this.ngayDangKiem = ngayDangKiem;
    }

    public String getLoaiBang() {
        return loaiBang;
    }

    public void setLoaiBang(String loaiBang) {
        this.loaiBang = loaiBang;
    }
    
    
}
