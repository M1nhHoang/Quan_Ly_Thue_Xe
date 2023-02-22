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
public class NhanVien extends Nguoi{
    private String maNv;
    private Date ngayVaoCoQuan;
    private DateFormat d;

    public NhanVien(String maNv, Date ngayVaoCoQuan, String hoTen, String soCMND, String diaChi, String soDt) {
        super(hoTen, soCMND, diaChi, soDt);
        this.maNv = maNv;
        this.ngayVaoCoQuan = ngayVaoCoQuan;
        d = new SimpleDateFormat("dd/MM/yyyy");
    }

    @Override
    public String toString() {
        return "1"+","+super.getHoTen()+","+super.getSoCMND()+","+super.getDiaChi()
                +","+super.getSoDt()+","+maNv+","+d.format(ngayVaoCoQuan);
    }

    public String getMaNv() {
        return maNv;
    }

    public void setMaNv(String maNv) {
        this.maNv = maNv;
    }

    public Date getNgayVaoCoQuan() {
        return ngayVaoCoQuan;
    }

    public void setNgayVaoCoQuan(Date ngayVaoCoQuan) {
        this.ngayVaoCoQuan = ngayVaoCoQuan;
    }
    
    
}
