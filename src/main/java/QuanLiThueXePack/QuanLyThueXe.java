/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuanLiThueXePack;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * @author HP
 */
public class QuanLyThueXe implements IQuanLyThueXe{
    private List<HopDongChoThue> DsHd;
    private List<Xe> DsXe;
    private List<Nguoi> DsNv;
    private String path;
    private DateFormat p;

    public QuanLyThueXe(String path) {
        DsHd = new ArrayList<HopDongChoThue>();
        DsXe = new ArrayList<Xe>();
        DsNv = new ArrayList<Nguoi>();
        this.path = path;
        this.p = new SimpleDateFormat("dd/MM/yyyy");
    }
    
    @Override
    public void ReadFile() {
        FileReader frConNguoi, frXe, frHopDong;
        BufferedReader brConNguoi, brXe, brHopDong;
        String s;
        DsHd.clear();
        DsXe.clear();
        DsNv.clear();
        try {
            // Con nguoi
            frConNguoi = new FileReader(path+"QuanLyConNguoi.txt");
            brConNguoi = new BufferedReader(frConNguoi);
            while((s = brConNguoi.readLine())!=null){
                String[] strInfor = s.split("[,]+");
                Nguoi ng = null;
                String SoCM = strInfor[2];
                String hoTen = strInfor[1];
                String diaChi = strInfor[3];
                String dienThoai = strInfor[4];
                if (strInfor[0].equals("1")){
                    ng = new NhanVien(strInfor[5], p.parse(strInfor[6]), hoTen, SoCM, diaChi, dienThoai);
                }
                else if (strInfor[0].equals("2")){
                    ng = new KhachHang(strInfor[5], Integer.parseInt(strInfor[6]), hoTen, SoCM, diaChi, dienThoai);
                }
                this.DsNv.add(ng);
            }
            frConNguoi.close();
            // Xe
            frXe = new FileReader(path+"QuanLyXe.txt");
            brXe = new BufferedReader(frXe);
            while((s = brXe.readLine())!=null){
                String[] strInfor = s.split("[,]+");
                Xe xe = null;
                String bienSo = strInfor[1];
                String tenXe = strInfor[2];
                String trongTai = strInfor[3];
                String ngayDangKiem = strInfor[4];
                String tieuChuanBang = strInfor[5];
                if (strInfor[0].equals("1")){
                    xe = new XeChoHang(Double.parseDouble(strInfor[6]), bienSo, tenXe, Integer.parseInt(trongTai), p.parse(ngayDangKiem), tieuChuanBang);
                }
                else if (strInfor[0].equals("2")){
                    xe = new XeDuLich(Integer.parseInt(strInfor[6]), bienSo, tenXe, Integer.parseInt(trongTai), p.parse(ngayDangKiem), tieuChuanBang);
                }
                this.DsXe.add(xe);
            }
            frXe.close();
            // Hop Dong
            frHopDong = new FileReader(path+"QuanHopDong.txt");
            brHopDong = new BufferedReader(frHopDong);
            while((s = brHopDong.readLine())!=null){
                String[] strInfor = s.split("[,]+");
                HopDongChoThue hd = null;
                String bienSo = strInfor[0];
                String loaiXe = strInfor[1];
                String maKH = strInfor[2];
                String maNV = strInfor[3];
                int soNgayThue = Integer.parseInt(strInfor[4]);
                double donGia = Double.parseDouble(strInfor[5]);
                Date ngayThue = p.parse(strInfor[6]);
                Xe x = null;
                KhachHang k = null;
                NhanVien v = null;
                for(Xe xe:DsXe){
                    if (xe.getBienSo().equals(bienSo)){
                        if (loaiXe.equals("Xe Chở Hàng"))
                            x = (XeChoHang)xe;
                        else if (loaiXe.equals("Xe Du Lịch"))
                            x = (XeDuLich)xe;
                    }
                }
                for(Nguoi ng:DsNv){
                    if (ng instanceof KhachHang)
                        if (((KhachHang)ng).getMaKh().equals(maKH))
                            k = (KhachHang)ng;
                    if (ng instanceof NhanVien)
                        if (((NhanVien)ng).getMaNv().equals(maNV))
                            v = (NhanVien)ng;
                }
                hd = new HopDongChoThue(x, k, v, soNgayThue, donGia, ngayThue);
                this.DsHd.add(hd);
            }
            frHopDong.close();
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void WriteFile() {
        FileWriter fwConNguoi, fwXe, fwHopDong;
        
        try{
            // Con Nguoi
            String strNvInfor = "";
            fwConNguoi = new FileWriter(path+"QuanLyConNguoi.txt");
            for (Nguoi ng:DsNv){
                strNvInfor += ng.toString() + "\n";
            }
            fwConNguoi.write(strNvInfor);
            fwConNguoi.close();
            // Xe
            strNvInfor = "";
            fwXe = new FileWriter(path+"QuanLyXe.txt");
            for (Xe xe:DsXe){
                strNvInfor += xe.toString() + "\n";
            }
            fwXe.write(strNvInfor);
            fwXe.close();
            // HopDong
            strNvInfor = "";
            fwHopDong = new FileWriter(path+"QuanHopDong.txt");
            for (HopDongChoThue hd:DsHd){
                strNvInfor += hd.toString() + "\n";
            }
            fwHopDong.write(strNvInfor);
            fwHopDong.close();
        }
        catch (Exception e){
            System.out.print(e);
        }
    }
    
    public void addNguoi(Nguoi ng){
        DsNv.add(ng);
        WriteFile();
    }
    
    public void removeNguoi(Nguoi ng){
        for (int i = 0; i < DsNv.size(); i++){
            if (ng instanceof NhanVien && DsNv.get(i) instanceof NhanVien){
                if (((NhanVien)DsNv.get(i)).getMaNv().equals(((NhanVien)ng).getMaNv()))
                    DsNv.remove(i);
            }
            else if (ng instanceof KhachHang && DsNv.get(i) instanceof KhachHang){
                if (((KhachHang)DsNv.get(i)).getMaKh().equals(((KhachHang)ng).getMaKh()))
                    DsNv.remove(i);
            }
        }
        WriteFile();
    }
    
    public void editNguoi(Nguoi ng){
        for (int i = 0; i < DsNv.size(); i++){
            if (ng instanceof NhanVien && DsNv.get(i) instanceof NhanVien){
                if (((NhanVien)DsNv.get(i)).getMaNv().equals(((NhanVien)ng).getMaNv()))
                    DsNv.set(i, ng);
            }
            else if (ng instanceof KhachHang && DsNv.get(i) instanceof KhachHang){
                if (((KhachHang)DsNv.get(i)).getMaKh().equals(((KhachHang)ng).getMaKh()))
                    DsNv.set(i, ng);
            }
        }
        WriteFile();
    }
    
    public void addXe(Xe xe){
        DsXe.add(xe);
        WriteFile();
    }
    
    public void removeXe(Xe xe){
        for (int i = 0; i < DsXe.size(); i++){
            if (xe instanceof XeChoHang && DsXe.get(i) instanceof XeChoHang){
                if (DsXe.get(i).getBienSo().equals(((XeChoHang)xe).getBienSo()))
                    DsXe.remove(i);
            }
            else if (xe instanceof XeDuLich && DsXe.get(i) instanceof XeDuLich){
                if (DsXe.get(i).getBienSo().equals(((XeDuLich)xe).getBienSo()))
                    DsXe.remove(i);
            }
        }
        WriteFile();
    }
    
    public void editXe(Xe xe){
        for (int i = 0; i < DsXe.size(); i++){
            if (xe instanceof XeChoHang && DsXe.get(i) instanceof XeChoHang){
                if (DsXe.get(i).getBienSo().equals(((XeChoHang)xe).getBienSo()))
                    DsXe.set(i, xe);
            }
            else if (xe instanceof XeDuLich && DsXe.get(i) instanceof XeDuLich){
                if (DsXe.get(i).getBienSo().equals(((XeDuLich)xe).getBienSo()))
                    DsXe.set(i, xe);
            }
        }
        WriteFile();
    }
    
    public void addHopDong(HopDongChoThue hd){
        DsHd.add(hd);
        WriteFile();
    }
    
    public void editHopDong(HopDongChoThue hd){
        for (int i = 0; i < DsHd.size(); i++){
            if (DsHd.get(i).getXe().getBienSo().equals(hd.getXe().getBienSo()))
                DsHd.set(i, hd);
        }
        WriteFile();
    }
    
    public void removeHopDong(HopDongChoThue hd){
        for (int i = 0; i < DsHd.size(); i++){
            if (DsHd.get(i).getXe().getBienSo().equals(hd.getXe().getBienSo()))
                DsHd.remove(i);
        }
        WriteFile();
    }

    public List<HopDongChoThue> getDsHd() {
        return DsHd;
    }

    public void setDsHd(List<HopDongChoThue> DsHd) {
        this.DsHd = DsHd;
    }

    public List<Xe> getDsXe() {
        return DsXe;
    }

    public void setDsXe(List<Xe> DsXe) {
        this.DsXe = DsXe;
    }

    public List<Nguoi> getDsNv() {
        return DsNv;
    }

    public void setDsNv(List<Nguoi> DsNv) {
        this.DsNv = DsNv;
    }
    
    
}
