/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuanLiThueXePack;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author HP
 */
public class HopDongChoThue {
    private Xe xe;
    private KhachHang KhachHang;
    private NhanVien NhanVien;
    private int soNgayThue;
    private double donGia;
    private Date ngayThue;
    private DateFormat d;

    public HopDongChoThue(Xe xe, KhachHang KhachHang, NhanVien NhanVien, int soNgayThue, double donGia, Date ngayThue) {
        this.xe = xe;
        this.KhachHang = KhachHang;
        this.NhanVien = NhanVien;
        this.soNgayThue = soNgayThue;
        this.donGia = donGia;
        this.ngayThue = ngayThue;
        d = new SimpleDateFormat("dd/MM/yyyy");
    }
    
    public double soTienThanhToan(){
        if (xe instanceof XeChoHang){
            return 20000*((XeChoHang)xe).getTaiTrongToiDa()*soNgayThue;
        }
        else if (xe instanceof XeDuLich){
            if (((XeDuLich)xe).getSoChoNgoi() == 4)
                return soNgayThue*400000;
            else if (((XeDuLich)xe).getSoChoNgoi() == 7)
                return soNgayThue*600000;
            else if (((XeDuLich)xe).getSoChoNgoi() == 12)
                return soNgayThue*700000;
            else if (((XeDuLich)xe).getSoChoNgoi() >= 30)
                return soNgayThue*1000000;
        }
        return 0;
    }
    
    private double yearDiff(Date date){
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date NgayHienTai = new Date();

        long diffInMillies = Math.abs(NgayHienTai.getTime() - date.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        
        return (int)diff;
    }
    
    public boolean kTraNamLuuHanh(){
        if (xe instanceof XeChoHang){
            if (yearDiff(((XeChoHang)xe).getNgayDangKiem())/360 <= 5)
                return true;
            else
                return false;
        }
        else if (xe instanceof XeDuLich){
            if (yearDiff(((XeDuLich)xe).getNgayDangKiem())/360 <= 5)
                return true;
            else
                return false;
        }
        return false;
    }

    @Override
    public String toString() {
        String loaiXe = (xe instanceof XeChoHang)?"Xe Chở Hàng":"Xe Du Lịch";
        return xe.getBienSo()+","+loaiXe+","+KhachHang.getMaKh()+","+NhanVien.getMaNv()+","+soNgayThue+","+donGia+","+d.format(ngayThue);
    }
    
    public Xe getXe() {
        return xe;
    }

    public void setXe(Xe xe) {
        this.xe = xe;
    }
    
    public String getLoaiXe() {
        return (xe instanceof XeChoHang)?"Xe Chở Hàng":"Xe Du Lịch";
    }

    public KhachHang getKhachHang() {
        return KhachHang;
    }

    public void setKhachHang(KhachHang KhachHang) {
        this.KhachHang = KhachHang;
    }

    public NhanVien getNhanVien() {
        return NhanVien;
    }

    public void setNhanVien(NhanVien NhanVien) {
        this.NhanVien = NhanVien;
    }

    public int getSoNgayThue() {
        return soNgayThue;
    }

    public void setSoNgayThue(int soNgayThue) {
        this.soNgayThue = soNgayThue;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public Date getNgayThue() {
        return ngayThue;
    }

    public void setNgayThue(Date ngayThue) {
        this.ngayThue = ngayThue;
    }
}
