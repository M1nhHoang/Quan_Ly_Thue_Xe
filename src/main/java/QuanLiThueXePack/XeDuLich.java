/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuanLiThueXePack;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author HP
 */
public class XeDuLich extends Xe{
    private int soChoNgoi;
    private DateFormat d;

    public XeDuLich(int soChoNgoi, String bienSo, String tenXe, int trongTai, Date ngayDangKiem, String loaiBang) {
        super(bienSo, tenXe, trongTai, ngayDangKiem, loaiBang);
        this.soChoNgoi = soChoNgoi;
        d = new SimpleDateFormat("dd/MM/yyyy");
    }

    @Override
    public String toString() {
        return "2"+","+super.getBienSo()+","+super.getTenXe()+","+super.getTrongTai()+","+d.format(super.getNgayDangKiem())+","+super.getLoaiBang()+","+this.soChoNgoi;
    }

    public int getSoChoNgoi() {
        return soChoNgoi;
    }

    public void setSoChoNgoi(int soChoNgoi) {
        this.soChoNgoi = soChoNgoi;
    }
    
    
}
