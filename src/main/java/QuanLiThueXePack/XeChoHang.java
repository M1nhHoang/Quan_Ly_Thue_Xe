/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuanLiThueXePack;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author HP
 */
public class XeChoHang extends Xe{
    private double taiTrongToiDa;
    private DateFormat d;
    

    public XeChoHang(double taiTrongToiDa, String bienSo, String tenXe, int trongTai, Date ngayDangKiem, String loaiBang) {
        super(bienSo, tenXe, trongTai, ngayDangKiem, loaiBang);
        this.taiTrongToiDa = taiTrongToiDa;
        d = new SimpleDateFormat("dd/MM/yyyy");
    }

    @Override
    public String toString() {
        return "1"+","+super.getBienSo()+","+super.getTenXe()+","+super.getTrongTai()+","+d.format(super.getNgayDangKiem())+","+super.getLoaiBang()+","+this.taiTrongToiDa;
    }

    public double getTaiTrongToiDa() {
        return taiTrongToiDa;
    }

    public void setTaiTrongToiDa(double taiTrongToiDa) {
        this.taiTrongToiDa = taiTrongToiDa;
    }
    
    
}
