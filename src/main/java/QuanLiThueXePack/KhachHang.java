/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuanLiThueXePack;

/**
 *
 * @author HP
 */
public class KhachHang extends Nguoi{
    private String maKh;
    private int hangBangLai;

    public KhachHang(String maKh, int hangBangLai, String hoTen, String soCMND, String diaChi, String soDt) {
        super(hoTen, soCMND, diaChi, soDt);
        this.maKh = maKh;
        this.hangBangLai = hangBangLai;
    }

    @Override
    public String toString() {
        return "2"+","+super.getHoTen()+","+super.getSoCMND()+","+super.getDiaChi()
                +","+super.getSoDt()+","+maKh+","+hangBangLai;
    }

    public String getMaKh() {
        return maKh;
    }

    public void setMaKh(String maKh) {
        this.maKh = maKh;
    }

    public int getHangBangLai() {
        return hangBangLai;
    }

    public void setHangBangLai(int hangBangLai) {
        this.hangBangLai = hangBangLai;
    }
    
    
}
