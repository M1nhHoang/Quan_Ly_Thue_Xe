/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuanLiThueXePack;

import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.stream.IntStream;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HP
 */
public class FrameQuanLyThueXe extends JFrame implements ActionListener, ListSelectionListener{
    private MenuBar appMenu;
    private Menu qlConNguoi, qlXe, qlHopDong, thongKe;
    private MenuItem qlNhanVien, qlKhachHang, qlXeDuLich, qlXeChoHang, qlhopDong;
    private Label lbManv, lbNgayVaoCoQuan, lbSoCM, lbHoTen, lbDiaChi, lbDienThoai, lbMaKH, lbHangBangLai, lbBienSo, lbTenXe, lbTrongTai, lbNgayDangKiem, lbTieuChuanBang, lbSoChoNgoi, lbTrongTaiChoPhep, lbSoNgayThue, lbDonGia, lbNgayThue, lbLoaiXe;
    private TextField txtManv, txtNgayVaoCoQuan, txtSoCM, txtHoTen, txtDiaChi, txtDienThoai, txtMaKH, txtHangBangLai, txtBienSo, txtTenXe, txtTrongTai, txtNgayDangKiem, txtTieuChuanBang, txtSoChoNgoi, txtTrongTaiChoPhep, txtSoNgayThue, txtDonGia, txtNgayThue;
    private Choice cbLoaiXe;
    private Button btThem, btSua, btXoa, btLamMoi;
    private JPanel panel;
    private QuanLyThueXe qlTXe;
    private JScrollPane tablePanel;
    private JTable table;
    private DefaultTableModel model;
    private DateFormat d;
    
    public FrameQuanLyThueXe(){
        qlTXe = new QuanLyThueXe(".\\");
        d = new SimpleDateFormat("dd/MM/yyyy");
                
        this.setTitle("Quản Lí Thuê Xe");
        this.setLayout(null);
        this.setSize(600, 450);
        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        
        // Menu bar
        appMenu = new MenuBar();
        
        qlConNguoi = new Menu("Quản Lí Con Người");
        qlXe = new Menu("Quản Lí Xe");
        qlHopDong = new Menu("Quản Lí Hợp Đồng");
        thongKe = new Menu("Báo Cáo Và Thống Kê");
        
        appMenu.add(qlConNguoi);
        appMenu.add(qlXe);
        appMenu.add(qlHopDong);
        appMenu.add(thongKe);
        
        qlNhanVien = new MenuItem("Quản Lí Nhân Viên");
        qlKhachHang = new MenuItem("Quản Lí Khách Hàng");
        
        qlNhanVien.addActionListener(this);
        qlKhachHang.addActionListener(this);
        
        qlConNguoi.add(qlNhanVien);
        qlConNguoi.add(qlKhachHang);
        
        qlXeDuLich = new MenuItem("Quản Lí Xe Du Lịch");
        qlXeChoHang = new MenuItem("Quản Lí Xe Chở Hàng");
        
        qlXeDuLich.addActionListener(this);
        qlXeChoHang.addActionListener(this);
        
        qlXe.add(qlXeDuLich);
        qlXe.add(qlXeChoHang);
        
        qlhopDong = new MenuItem("Lập Hợp Đồng Cho Thuê");
        
        qlhopDong.addActionListener(this);
        
        qlHopDong.add(qlhopDong);
        
        this.setMenuBar(appMenu);
        
        // switch panel
        Dimension frameSize = this.getSize();
        
        panel = new JPanel(null);
        panel.setSize(frameSize.width, frameSize.height);
        
        // table
        model = new DefaultTableModel();
        
        table = new JTable(model);
        table.getSelectionModel().addListSelectionListener(this);
        
        // scr panel
        tablePanel = new JScrollPane(table);
    }
    
    private void resetModel(){
        if (model.getRowCount() > 0)
            model.setRowCount(0);
        if (model.getColumnCount() > 0)
            model.setColumnCount(0);
    }
    
    private void TableQuanLiNhanVien(){
        qlTXe.ReadFile();
        resetModel();
        Object[] row;
        String[] columnNames = {"MaNV",
                            "Ngày Vào Cơ Quan",
                            "Họ Tên",
                            "Số Chứng Minh",
                            "Địa Chỉ",
                            "Điện Thoại"};
        for (String str:columnNames)
            model.addColumn(str);
        for (int i = 0; i < qlTXe.getDsNv().size(); i++){
            if (qlTXe.getDsNv().get(i) instanceof NhanVien){
                row = new Object[] {((NhanVien)qlTXe.getDsNv().get(i)).getMaNv()
                        ,d.format(((NhanVien)qlTXe.getDsNv().get(i)).getNgayVaoCoQuan())
                        , qlTXe.getDsNv().get(i).getHoTen(),qlTXe.getDsNv().get(i).getSoCMND()
                        , qlTXe.getDsNv().get(i).getDiaChi(), qlTXe.getDsNv().get(i).getSoDt()};

                model.addRow(row);
            }
        }
        table.setModel(model);
        table.repaint();
    }
    
    private void TableQuanLiKhachHang(){
        qlTXe.ReadFile();
        resetModel();
        Object[] row;
        String[] columnNames = {"MaKH",
                            "Hạng Bằng Lái",
                            "Họ Tên",
                            "Số Chứng Minh",
                            "Địa Chỉ",
                            "Điện Thoại"};
        for (String str:columnNames)
            model.addColumn(str);
        for (int i = 0; i < qlTXe.getDsNv().size(); i++){
            if (qlTXe.getDsNv().get(i) instanceof KhachHang){
                row = new Object[] {((KhachHang)qlTXe.getDsNv().get(i)).getMaKh()
                        ,((KhachHang)qlTXe.getDsNv().get(i)).getHangBangLai()
                        , qlTXe.getDsNv().get(i).getHoTen(),qlTXe.getDsNv().get(i).getSoCMND()
                        , qlTXe.getDsNv().get(i).getDiaChi(), qlTXe.getDsNv().get(i).getSoDt()};

                model.addRow(row);
            }
        }
        table.setModel(model);
        table.repaint();
    }
    
    private void TableQuanLiXeChoHang(){
        qlTXe.ReadFile();
        resetModel();
        Object[] row;
        String[] columnNames = {"Biển Số",
                            "Tên Xe",
                            "Trọng Tải",
                            "Ngày Đăng Kiểm",
                            "Tiêu Chuẩn Bằng",
                            "Tải trọng Tối Đa"};
        for (String str:columnNames)
            model.addColumn(str);
        for (int i = 0; i < qlTXe.getDsXe().size(); i++){
            if (qlTXe.getDsXe().get(i) instanceof XeChoHang){
                row = new Object[] {qlTXe.getDsXe().get(i).getBienSo()
                        , qlTXe.getDsXe().get(i).getTenXe()
                        , qlTXe.getDsXe().get(i).getTrongTai()
                        , d.format(qlTXe.getDsXe().get(i).getNgayDangKiem())
                        , qlTXe.getDsXe().get(i).getLoaiBang()
                        , ((XeChoHang)qlTXe.getDsXe().get(i)).getTaiTrongToiDa()};

                model.addRow(row);
            }
        }
        table.setModel(model);
        table.repaint();
    }
    
    private void TableQuanLiXeDuLich(){
        qlTXe.ReadFile();
        resetModel();
        Object[] row;
        String[] columnNames = {"Biển Số",
                            "Tên Xe",
                            "Trọng Tải",
                            "Ngày Đăng Kiểm",
                            "Tiêu Chuẩn Bằng",
                            "Số Chỗ Ngồi"};
        for (String str:columnNames)
            model.addColumn(str);
        for (int i = 0; i < qlTXe.getDsXe().size(); i++){
            if (qlTXe.getDsXe().get(i) instanceof XeDuLich){
                row = new Object[] {qlTXe.getDsXe().get(i).getBienSo()
                        , qlTXe.getDsXe().get(i).getTenXe()
                        , qlTXe.getDsXe().get(i).getTrongTai()
                        , d.format(qlTXe.getDsXe().get(i).getNgayDangKiem())
                        , qlTXe.getDsXe().get(i).getLoaiBang()
                        , ((XeDuLich)qlTXe.getDsXe().get(i)).getSoChoNgoi()};

                model.addRow(row);
            }
        }
        table.setModel(model);
        table.repaint();
    }
    
    private void TableQuanLiHopDong(){
        qlTXe.ReadFile();
        resetModel();
        Object[] row;
        String[] columnNames = {"Biển Số",
                            "Loại Xe",
                            "Khách Hàng",
                            "Nhân Viên",
                            "Số Ngày Thuê",
                            "Đơn Giá",
                            "Ngày Thuê"};
        for (String str:columnNames)
            model.addColumn(str);
        for (int i = 0; i < qlTXe.getDsHd().size(); i++){
            row = new Object[] {qlTXe.getDsHd().get(i).getXe().getBienSo()
                    , qlTXe.getDsHd().get(i).getLoaiXe()
                    , qlTXe.getDsHd().get(i).getKhachHang().getMaKh()
                    , qlTXe.getDsHd().get(i).getNhanVien().getMaNv()
                    , qlTXe.getDsHd().get(i).getSoNgayThue()
                    , qlTXe.getDsHd().get(i).getDonGia()
                    , d.format(qlTXe.getDsHd().get(i).getNgayThue())};

            model.addRow(row);
        }
        table.setModel(model);
        table.repaint();
    }
    
    public void IQuanLiNhanVien(){
        add(panel);
        // label
        lbManv = new Label("Mã Nhân Viên");
        lbNgayVaoCoQuan = new Label("Ngày Vào Cơ Quan");
        lbSoCM = new Label("Số Chứng Minh");
        lbHoTen = new Label("Họ Tên");
        lbDiaChi = new Label("Địa Chỉ");
        lbDienThoai = new Label("Điện Thoại");
        
        lbManv.setBounds(30, 30, 100, 20);
        lbNgayVaoCoQuan.setBounds(30, 60, 100, 20);
        lbSoCM.setBounds(30, 90, 100, 20);
        lbHoTen.setBounds(300, 30, 100, 20);
        lbDiaChi.setBounds(300, 60, 100, 20);
        lbDienThoai.setBounds(300, 90, 100, 20);
        
        // text Field
        txtManv = new TextField();
        txtNgayVaoCoQuan = new TextField();
        txtSoCM = new TextField();
        txtHoTen = new TextField();
        txtDiaChi = new TextField();
        txtDienThoai = new TextField();
        
        txtManv.setBounds(140, 30, 100, 20);
        txtNgayVaoCoQuan.setBounds(140, 60, 100, 20);
        txtSoCM.setBounds(140, 90, 100, 20);
        txtHoTen.setBounds(430, 30, 100, 20);
        txtDiaChi.setBounds(430, 60, 100, 20);
        txtDienThoai.setBounds(430, 90, 100, 20);
        
        // button
        btLamMoi = new Button("Làm Mới");
        btThem = new Button("Thêm");
        btSua = new Button("Sửa");
        btXoa = new Button("Xóa");
        
        btThem.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Nguoi tempNv;
                try {
                    qlTXe.ReadFile();
                    tempNv = new NhanVien(txtManv.getText(), d.parse(txtNgayVaoCoQuan.getText())
                            , txtHoTen.getText(), txtSoCM.getText()
                            , txtDiaChi.getText(), txtDienThoai.getText());
                    qlTXe.addNguoi(tempNv);
                    TableQuanLiNhanVien();
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });
        btLamMoi.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                txtManv.setText("");
                txtNgayVaoCoQuan.setText("");
                txtSoCM.setText("");
                txtHoTen.setText("");
                txtDiaChi.setText("");
                txtDienThoai.setText("");
            }
        });
        btSua.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Nguoi tempNv;
                try {
                    qlTXe.ReadFile();
                    tempNv = new NhanVien(txtManv.getText(), d.parse(txtNgayVaoCoQuan.getText())
                            , txtHoTen.getText(), txtSoCM.getText()
                            , txtDiaChi.getText(), txtDienThoai.getText());
                    qlTXe.editNguoi(tempNv);
                    TableQuanLiNhanVien();
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });
        btXoa.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Nguoi tempNv;
                try {
                    qlTXe.ReadFile();
                    tempNv = new NhanVien(txtManv.getText(), d.parse(txtNgayVaoCoQuan.getText())
                            , txtHoTen.getText(), txtSoCM.getText()
                            , txtDiaChi.getText(), txtDienThoai.getText());
                    qlTXe.removeNguoi(tempNv);
                    TableQuanLiNhanVien();
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });
        
        TableQuanLiNhanVien();
        
        btThem.setBounds(30, 130, 100, 30);
        btSua.setBounds(160, 130, 100, 30);
        btXoa.setBounds(330, 130, 100, 30);
        btLamMoi.setBounds(460, 130, 100, 30);
        
        tablePanel.setViewportView(table);
        tablePanel.setBounds(20, 190, 550, 200);
        
        panel.add(lbManv);
        panel.add(lbNgayVaoCoQuan);
        panel.add(lbSoCM);
        panel.add(lbHoTen);
        panel.add(lbDiaChi);
        panel.add(lbDienThoai);
        panel.add(txtManv);
        panel.add(txtNgayVaoCoQuan);
        panel.add(txtSoCM);
        panel.add(txtHoTen);
        panel.add(txtDiaChi);
        panel.add(txtDienThoai);
        panel.add(btLamMoi);
        panel.add(btThem);
        panel.add(btSua);
        panel.add(btXoa);
        panel.add(tablePanel);
    }
    
    public void IQuanLiKhachHang(){
        add(panel);
        // label
        lbMaKH = new Label("Mã Khách Hàng");
        lbHangBangLai = new Label("Hạng Bằng Lái");
        lbSoCM = new Label("Số Chứng Minh");
        lbHoTen = new Label("Họ Tên");
        lbDiaChi = new Label("Địa Chỉ");
        lbDienThoai = new Label("Điện Thoại");
        
        lbMaKH.setBounds(30, 30, 100, 20);
        lbHangBangLai.setBounds(30, 60, 100, 20);
        lbSoCM.setBounds(30, 90, 100, 20);
        lbHoTen.setBounds(300, 30, 100, 20);
        lbDiaChi.setBounds(300, 60, 100, 20);
        lbDienThoai.setBounds(300, 90, 100, 20);
        
        // text Field
        txtMaKH = new TextField();
        txtHangBangLai = new TextField();
        txtSoCM = new TextField();
        txtHoTen = new TextField();
        txtDiaChi = new TextField();
        txtDienThoai = new TextField();
        
        txtMaKH.setBounds(140, 30, 100, 20);
        txtHangBangLai.setBounds(140, 60, 100, 20);
        txtSoCM.setBounds(140, 90, 100, 20);
        txtHoTen.setBounds(430, 30, 100, 20);
        txtDiaChi.setBounds(430, 60, 100, 20);
        txtDienThoai.setBounds(430, 90, 100, 20);
        
        // button
        btLamMoi = new Button("Làm Mới");
        btThem = new Button("Thêm");
        btSua = new Button("Sửa");
        btXoa = new Button("Xóa");
        
        btThem.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Nguoi tempNv;
                try {
                    qlTXe.ReadFile();
                    tempNv = new KhachHang(txtMaKH.getText(), Integer.parseInt(txtHangBangLai.getText())
                            , txtHoTen.getText(), txtSoCM.getText()
                            , txtDiaChi.getText(), txtDienThoai.getText());
                    qlTXe.addNguoi(tempNv);
                    TableQuanLiKhachHang();
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });
        btLamMoi.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                txtMaKH.setText("");
                txtHangBangLai.setText("");
                txtSoCM.setText("");
                txtHoTen.setText("");
                txtDiaChi.setText("");
                txtDienThoai.setText("");
            }
        });
        btSua.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Nguoi tempNv;
                try {
                    qlTXe.ReadFile();
                    tempNv = new KhachHang(txtMaKH.getText(), Integer.parseInt(txtHangBangLai.getText())
                            , txtHoTen.getText(), txtSoCM.getText()
                            , txtDiaChi.getText(), txtDienThoai.getText());
                    qlTXe.editNguoi(tempNv);
                    TableQuanLiKhachHang();
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });
        btXoa.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Nguoi tempNv;
                try {
                    qlTXe.ReadFile();
                    tempNv = new KhachHang(txtMaKH.getText(), Integer.parseInt(txtHangBangLai.getText())
                            , txtHoTen.getText(), txtSoCM.getText()
                            , txtDiaChi.getText(), txtDienThoai.getText());
                    qlTXe.removeNguoi(tempNv);
                    TableQuanLiKhachHang();
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });
        
        TableQuanLiKhachHang();
        
        btThem.setBounds(30, 130, 100, 30);
        btSua.setBounds(160, 130, 100, 30);
        btXoa.setBounds(330, 130, 100, 30);
        btLamMoi.setBounds(460, 130, 100, 30);
        
        tablePanel.setViewportView(table);
        tablePanel.setBounds(20, 190, 550, 200);
        
        panel.add(lbMaKH);
        panel.add(lbHangBangLai);
        panel.add(lbSoCM);
        panel.add(lbHoTen);
        panel.add(lbDiaChi);
        panel.add(lbDienThoai);
        panel.add(txtMaKH);
        panel.add(txtHangBangLai);
        panel.add(txtSoCM);
        panel.add(txtHoTen);
        panel.add(txtDiaChi);
        panel.add(txtDienThoai);
        panel.add(btLamMoi);
        panel.add(btThem);
        panel.add(btSua);
        panel.add(btXoa);
        panel.add(tablePanel);
    }
    
    public void IQuanLiXeChoHang(){
        add(panel);
        // label
        lbBienSo = new Label("Biển Số");
        lbTenXe = new Label("Tên Xe");
        lbTrongTai = new Label("Trọng Tải");
        lbNgayDangKiem = new Label("Ngày Đăng Kiểm");
        lbTieuChuanBang = new Label("Tiêu Chuẩn Bằng");
        lbTrongTaiChoPhep = new Label("Trọng Tải Cho Phép");
        
        lbBienSo.setBounds(30, 30, 100, 20);
        lbTenXe.setBounds(30, 60, 100, 20);
        lbTrongTai.setBounds(30, 90, 100, 20);
        lbNgayDangKiem.setBounds(300, 30, 100, 20);
        lbTieuChuanBang.setBounds(300, 60, 100, 20);
        lbTrongTaiChoPhep.setBounds(300, 90, 100, 20);
        
        // text Field
        txtBienSo = new TextField();
        txtTenXe = new TextField();
        txtTrongTai = new TextField();
        txtNgayDangKiem = new TextField();
        txtTieuChuanBang = new TextField();
        txtTrongTaiChoPhep = new TextField();
        
        txtBienSo.setBounds(140, 30, 100, 20);
        txtTenXe.setBounds(140, 60, 100, 20);
        txtTrongTai.setBounds(140, 90, 100, 20);
        txtNgayDangKiem.setBounds(430, 30, 100, 20);
        txtTieuChuanBang.setBounds(430, 60, 100, 20);
        txtTrongTaiChoPhep.setBounds(430, 90, 100, 20);
        
        // button
        btLamMoi = new Button("Làm Mới");
        btThem = new Button("Thêm");
        btSua = new Button("Sửa");
        btXoa = new Button("Xóa");
        
        btThem.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Xe tempXe;
                try {
                    qlTXe.ReadFile();
                    tempXe = new XeChoHang(Double.parseDouble(txtTrongTaiChoPhep.getText()), txtBienSo.getText()
                            , txtTenXe.getText(), Integer.parseInt(txtTrongTai.getText())
                            , d.parse(txtNgayDangKiem.getText()), txtTieuChuanBang.getText());
                    qlTXe.addXe(tempXe);
                    TableQuanLiXeChoHang();
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });
        btLamMoi.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                txtBienSo.setText("");
                txtTenXe.setText("");
                txtTrongTai.setText("");
                txtNgayDangKiem.setText("");
                txtTieuChuanBang.setText("");
                txtTrongTaiChoPhep.setText("");
            }
        });
        btSua.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Xe tempXe;
                try {
                    qlTXe.ReadFile();
                    tempXe = new XeChoHang(Double.parseDouble(txtTrongTaiChoPhep.getText()), txtBienSo.getText()
                            , txtTenXe.getText(), Integer.parseInt(txtTrongTai.getText())
                            , d.parse(txtNgayDangKiem.getText()), txtTieuChuanBang.getText());
                    qlTXe.editXe(tempXe);
                    TableQuanLiXeChoHang();
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });
        btXoa.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Xe tempXe;
                try {
                    qlTXe.ReadFile();
                    tempXe = new XeChoHang(Double.parseDouble(txtTrongTaiChoPhep.getText()), txtBienSo.getText()
                            , txtTenXe.getText(), Integer.parseInt(txtTrongTai.getText())
                            , d.parse(txtNgayDangKiem.getText()), txtTieuChuanBang.getText());
                    qlTXe.removeXe(tempXe);
                    TableQuanLiXeChoHang();
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });
        
        TableQuanLiXeChoHang();
        
        btThem.setBounds(30, 130, 100, 30);
        btSua.setBounds(160, 130, 100, 30);
        btXoa.setBounds(330, 130, 100, 30);
        btLamMoi.setBounds(460, 130, 100, 30);
        
        tablePanel.setViewportView(table);
        tablePanel.setBounds(20, 190, 550, 200);
        
        panel.add(lbBienSo);
        panel.add(lbTenXe);
        panel.add(lbTrongTai);
        panel.add(lbNgayDangKiem);
        panel.add(lbTieuChuanBang);
        panel.add(lbTrongTaiChoPhep);
        panel.add(txtBienSo);
        panel.add(txtTenXe);
        panel.add(txtTrongTai);
        panel.add(txtNgayDangKiem);
        panel.add(txtTieuChuanBang);
        panel.add(txtTrongTaiChoPhep);
        panel.add(btLamMoi);
        panel.add(btThem);
        panel.add(btSua);
        panel.add(btXoa);
        panel.add(tablePanel);
    }
    
    public void IQuanLiXeDuLich(){
        add(panel);
        // label
        lbBienSo = new Label("Biển Số");
        lbTenXe = new Label("Tên Xe");
        lbTrongTai = new Label("Trọng Tải");
        lbNgayDangKiem = new Label("Ngày Đăng Kiểm");
        lbTieuChuanBang = new Label("Tiêu Chuẩn Bằng");
        lbSoChoNgoi = new Label("Số Chỗ Ngồi");
        
        lbBienSo.setBounds(30, 30, 100, 20);
        lbTenXe.setBounds(30, 60, 100, 20);
        lbTrongTai.setBounds(30, 90, 100, 20);
        lbNgayDangKiem.setBounds(300, 30, 100, 20);
        lbTieuChuanBang.setBounds(300, 60, 100, 20);
        lbSoChoNgoi.setBounds(300, 90, 100, 20);
        
        // text Field
        txtBienSo = new TextField();
        txtTenXe = new TextField();
        txtTrongTai = new TextField();
        txtNgayDangKiem = new TextField();
        txtTieuChuanBang = new TextField();
        txtSoChoNgoi = new TextField();
        
        txtBienSo.setBounds(140, 30, 100, 20);
        txtTenXe.setBounds(140, 60, 100, 20);
        txtTrongTai.setBounds(140, 90, 100, 20);
        txtNgayDangKiem.setBounds(430, 30, 100, 20);
        txtTieuChuanBang.setBounds(430, 60, 100, 20);
        txtSoChoNgoi.setBounds(430, 90, 100, 20);
        
        // button
        btLamMoi = new Button("Làm Mới");
        btThem = new Button("Thêm");
        btSua = new Button("Sửa");
        btXoa = new Button("Xóa");
        
        btThem.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Xe tempXe;
                try {
                    qlTXe.ReadFile();
                    tempXe = new XeDuLich(Integer.parseInt(txtSoChoNgoi.getText()), txtBienSo.getText()
                            , txtTenXe.getText(), Integer.parseInt(txtTrongTai.getText())
                            , d.parse(txtNgayDangKiem.getText()), txtTieuChuanBang.getText());
                    qlTXe.addXe(tempXe);
                    TableQuanLiXeDuLich();
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });
        btLamMoi.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                txtBienSo.setText("");
                txtTenXe.setText("");
                txtTrongTai.setText("");
                txtNgayDangKiem.setText("");
                txtTieuChuanBang.setText("");
                txtSoChoNgoi.setText("");
            }
        });
        btSua.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Xe tempXe;
                try {
                    qlTXe.ReadFile();
                    tempXe = new XeDuLich(Integer.parseInt(txtSoChoNgoi.getText()), txtBienSo.getText()
                            , txtTenXe.getText(), Integer.parseInt(txtTrongTai.getText())
                            , d.parse(txtNgayDangKiem.getText()), txtTieuChuanBang.getText());
                    qlTXe.editXe(tempXe);
                    TableQuanLiXeDuLich();
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });
        btXoa.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Xe tempXe;
                try {
                    qlTXe.ReadFile();
                    tempXe = new XeDuLich(Integer.parseInt(txtSoChoNgoi.getText()), txtBienSo.getText()
                            , txtTenXe.getText(), Integer.parseInt(txtTrongTai.getText())
                            , d.parse(txtNgayDangKiem.getText()), txtTieuChuanBang.getText());
                    qlTXe.removeXe(tempXe);
                    TableQuanLiXeDuLich();
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });
        
        TableQuanLiXeDuLich();
        
        btThem.setBounds(30, 130, 100, 30);
        btSua.setBounds(160, 130, 100, 30);
        btXoa.setBounds(330, 130, 100, 30);
        btLamMoi.setBounds(460, 130, 100, 30);
        
        tablePanel.setViewportView(table);
        tablePanel.setBounds(20, 190, 550, 200);
        
        panel.add(lbBienSo);
        panel.add(lbTenXe);
        panel.add(lbTrongTai);
        panel.add(lbNgayDangKiem);
        panel.add(lbTieuChuanBang);
        panel.add(lbSoChoNgoi);
        panel.add(txtBienSo);
        panel.add(txtTenXe);
        panel.add(txtTrongTai);
        panel.add(txtNgayDangKiem);
        panel.add(txtTieuChuanBang);
        panel.add(txtSoChoNgoi);
        panel.add(btLamMoi);
        panel.add(btThem);
        panel.add(btSua);
        panel.add(btXoa);
        panel.add(tablePanel);
    }
    
    public void IQuanLiHopDong(){
        add(panel);
        // label
        lbBienSo = new Label("Biển Số");
        lbMaKH = new Label("Mã Khách Hàng");
        lbManv = new Label("Mã Nhân Viên");
        lbSoNgayThue = new Label("Số Ngày Thuê");
        lbDonGia = new Label("Đơn Giá");
        lbNgayThue = new Label("Ngày Thuê");
        lbLoaiXe = new Label("Chọn Loại Xe");
        
        lbBienSo.setBounds(30, 30, 100, 20);
        lbMaKH.setBounds(30, 60, 100, 20);
        lbManv.setBounds(30, 90, 100, 20);
        lbSoNgayThue.setBounds(300, 30, 100, 20);
        lbDonGia.setBounds(300, 60, 100, 20);
        lbNgayThue.setBounds(300, 90, 100, 20);
        lbLoaiXe.setBounds(30, 130, 100, 20);
        
        // text Field
        txtBienSo = new TextField();
        txtMaKH = new TextField();
        txtManv = new TextField();
        txtSoNgayThue = new TextField();
        txtDonGia = new TextField();
        txtNgayThue = new TextField();
        
        txtBienSo.setBounds(140, 30, 100, 20);
        txtMaKH.setBounds(140, 60, 100, 20);
        txtManv.setBounds(140, 90, 100, 20);
        txtSoNgayThue.setBounds(430, 30, 100, 20);
        txtDonGia.setBounds(430, 60, 100, 20);
        txtNgayThue.setBounds(430, 90, 100, 20);
        
        // button
        btLamMoi = new Button("Làm Mới");
        btThem = new Button("Thêm");
        btSua = new Button("Sửa");
        btXoa = new Button("Xóa");
        
        // combobox
        cbLoaiXe = new Choice();
        cbLoaiXe.add("Xe Chở Hàng");
        cbLoaiXe.add("Xe Du Lịch");
        
        cbLoaiXe.setBounds(140, 130, 100, 20);
        
        btThem.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                HopDongChoThue tempHd;
                try {
                    qlTXe.ReadFile();
                    Xe x = null;
                    for (int i  = 0; i < qlTXe.getDsXe().size(); i++)
                        if (cbLoaiXe.getSelectedItem().equals("Xe Chở Hàng") && qlTXe.getDsXe().get(i) instanceof XeChoHang){
                            if (txtBienSo.getText().equals(qlTXe.getDsXe().get(i).getBienSo()))
                                x = qlTXe.getDsXe().get(i);
                        }
                        else if (cbLoaiXe.getSelectedItem().equals("Xe Du Lịch") && qlTXe.getDsXe().get(i) instanceof XeDuLich){
                            if (txtBienSo.getText().equals(qlTXe.getDsXe().get(i).getBienSo()))
                                x = qlTXe.getDsXe().get(i);
                        }
                    KhachHang k = null;
                    NhanVien v = null;
                    for (int i  = 0; i < qlTXe.getDsNv().size(); i++)
                        if (qlTXe.getDsNv().get(i) instanceof KhachHang){
                            if (((KhachHang)qlTXe.getDsNv().get(i)).getMaKh().equals(txtMaKH.getText()))
                                k = (KhachHang)qlTXe.getDsNv().get(i);
                        }
                        else if (qlTXe.getDsNv().get(i) instanceof NhanVien){
                            if (((NhanVien)qlTXe.getDsNv().get(i)).getMaNv().equals(txtManv.getText()))
                                v = (NhanVien)qlTXe.getDsNv().get(i);
                        }
                    tempHd = new HopDongChoThue(x, k, v, Integer.parseInt(txtSoNgayThue.getText())
                            , Double.parseDouble(txtDonGia.getText()), d.parse(txtNgayThue.getText()));
                    qlTXe.addHopDong(tempHd);
                    TableQuanLiHopDong();
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });
        btLamMoi.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                txtBienSo.setText("");
                txtMaKH.setText("");
                txtManv.setText("");
                txtSoNgayThue.setText("");
                txtDonGia.setText("");
                txtNgayThue.setText("");
                cbLoaiXe.select(null);
            }
        });
        btSua.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                HopDongChoThue tempHd;
                try {
                    qlTXe.ReadFile();
                    Xe x = null;
                    for (int i  = 0; i < qlTXe.getDsXe().size(); i++)
                        if (cbLoaiXe.getSelectedItem().equals("Xe Chở Hàng") && qlTXe.getDsXe().get(i) instanceof XeChoHang){
                            if (txtBienSo.getText().equals(qlTXe.getDsXe().get(i).getBienSo()))
                                x = qlTXe.getDsXe().get(i);
                        }
                        else if (cbLoaiXe.getSelectedItem().equals("Xe Du Lịch") && qlTXe.getDsXe().get(i) instanceof XeDuLich){
                            if (txtBienSo.getText().equals(qlTXe.getDsXe().get(i).getBienSo()))
                                x = qlTXe.getDsXe().get(i);
                        }
                    KhachHang k = null;
                    NhanVien v = null;
                    for (int i  = 0; i < qlTXe.getDsNv().size(); i++)
                        if (qlTXe.getDsNv().get(i) instanceof KhachHang){
                            if (((KhachHang)qlTXe.getDsNv().get(i)).getMaKh().equals(txtMaKH.getText()))
                                k = (KhachHang)qlTXe.getDsNv().get(i);
                        }
                        else if (qlTXe.getDsNv().get(i) instanceof NhanVien){
                            if (((NhanVien)qlTXe.getDsNv().get(i)).getMaNv().equals(txtManv.getText()))
                                v = (NhanVien)qlTXe.getDsNv().get(i);
                        }
                    tempHd = new HopDongChoThue(x, k, v, Integer.parseInt(txtSoNgayThue.getText())
                            , Double.parseDouble(txtDonGia.getText()), d.parse(txtNgayThue.getText()));
                    qlTXe.editHopDong(tempHd);
                    TableQuanLiHopDong();
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });
        btXoa.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                HopDongChoThue tempHd;
                try {
                    qlTXe.ReadFile();
                    Xe x = null;
                    for (int i  = 0; i < qlTXe.getDsXe().size(); i++)
                        if (cbLoaiXe.getSelectedItem().equals("Xe Chở Hàng") && qlTXe.getDsXe().get(i) instanceof XeChoHang){
                            if (txtBienSo.getText().equals(qlTXe.getDsXe().get(i).getBienSo()))
                                x = qlTXe.getDsXe().get(i);
                        }
                        else if (cbLoaiXe.getSelectedItem().equals("Xe Du Lịch") && qlTXe.getDsXe().get(i) instanceof XeDuLich){
                            if (txtBienSo.getText().equals(qlTXe.getDsXe().get(i).getBienSo()))
                                x = qlTXe.getDsXe().get(i);
                        }
                    KhachHang k = null;
                    NhanVien v = null;
                    for (int i  = 0; i < qlTXe.getDsNv().size(); i++)
                        if (qlTXe.getDsNv().get(i) instanceof KhachHang){
                            if (((KhachHang)qlTXe.getDsNv().get(i)).getMaKh().equals(txtMaKH.getText()))
                                k = (KhachHang)qlTXe.getDsNv().get(i);
                        }
                        else if (qlTXe.getDsNv().get(i) instanceof NhanVien){
                            if (((NhanVien)qlTXe.getDsNv().get(i)).getMaNv().equals(txtManv.getText()))
                                v = (NhanVien)qlTXe.getDsNv().get(i);
                        }
                    tempHd = new HopDongChoThue(x, k, v, Integer.parseInt(txtSoNgayThue.getText())
                            , Double.parseDouble(txtDonGia.getText()), d.parse(txtNgayThue.getText()));
                    qlTXe.removeHopDong(tempHd);
                    TableQuanLiHopDong();
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });
        
        TableQuanLiHopDong();
        
        btThem.setBounds(30, 160, 100, 30);
        btSua.setBounds(160, 160, 100, 30);
        btXoa.setBounds(330, 160, 100, 30);
        btLamMoi.setBounds(460, 160, 100, 30);
        
        
        tablePanel.setViewportView(table);
        tablePanel.setBounds(20, 210, 550, 180);
        
        panel.add(lbBienSo);
        panel.add(lbMaKH);
        panel.add(lbManv);
        panel.add(lbSoNgayThue);
        panel.add(lbDonGia);
        panel.add(lbNgayThue);
        panel.add(txtBienSo);
        panel.add(txtMaKH);
        panel.add(txtManv);
        panel.add(txtSoNgayThue);
        panel.add(txtDonGia);
        panel.add(txtNgayThue);
        panel.add(btLamMoi);
        panel.add(btThem);
        panel.add(btSua);
        panel.add(btXoa);
        panel.add(tablePanel);
        panel.add(cbLoaiXe);
        panel.add(lbLoaiXe);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof MenuComponent){
            MenuItem Item = (MenuItem)e.getSource();
            if (Item.getLabel().equals("Quản Lí Nhân Viên")){
                panel.removeAll();
                this.remove(panel);
                IQuanLiNhanVien();
            }
            else if (Item.getLabel().equals("Quản Lí Khách Hàng")){
                panel.removeAll();
                this.remove(panel);
                IQuanLiKhachHang();
            }
            else if (Item.getLabel().equals("Quản Lí Xe Du Lịch")){
                panel.removeAll();
                this.remove(panel);
                IQuanLiXeDuLich();
            }
            else if (Item.getLabel().equals("Quản Lí Xe Chở Hàng")){
                panel.removeAll();
                this.remove(panel);
                IQuanLiXeChoHang();
            }
            else if (Item.getLabel().equals("Lập Hợp Đồng Cho Thuê")){
                panel.removeAll();
                this.remove(panel);
                IQuanLiHopDong();
            }
        }
    }
    
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (table.getSelectedRow() != -1) {
            for (int i = 0; i < table.getColumnCount(); i++)
                if (table.getColumnName(i).equals("MaNV")){
                    txtManv.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
                    txtNgayVaoCoQuan.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
                    txtHoTen.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
                    txtSoCM.setText(table.getValueAt(table.getSelectedRow(), 3).toString());
                    txtDiaChi.setText(table.getValueAt(table.getSelectedRow(), 4).toString());
                    txtDienThoai.setText(table.getValueAt(table.getSelectedRow(), 5).toString());
                }
                else if (table.getColumnName(i).equals("MaKH")){
                    txtMaKH.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
                    txtHangBangLai.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
                    txtHoTen.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
                    txtSoCM.setText(table.getValueAt(table.getSelectedRow(), 3).toString());
                    txtDiaChi.setText(table.getValueAt(table.getSelectedRow(), 4).toString());
                    txtDienThoai.setText(table.getValueAt(table.getSelectedRow(), 5).toString());
                }
                else if (table.getColumnName(i).equals("Tải trọng Tối Đa")){
                    txtBienSo.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
                    txtTenXe.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
                    txtTrongTai.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
                    txtNgayDangKiem.setText(table.getValueAt(table.getSelectedRow(), 3).toString());
                    txtTieuChuanBang.setText(table.getValueAt(table.getSelectedRow(), 4).toString());
                    txtTrongTaiChoPhep.setText(table.getValueAt(table.getSelectedRow(), 5).toString());
                }
                else if (table.getColumnName(i).equals("Số Chỗ Ngồi")){
                    txtBienSo.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
                    txtTenXe.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
                    txtTrongTai.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
                    txtNgayDangKiem.setText(table.getValueAt(table.getSelectedRow(), 3).toString());
                    txtTieuChuanBang.setText(table.getValueAt(table.getSelectedRow(), 4).toString());
                    txtSoChoNgoi.setText(table.getValueAt(table.getSelectedRow(), 5).toString());
                }
                else if (table.getColumnName(i).equals("Loại Xe")){
                    txtBienSo.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
                    cbLoaiXe.select(table.getValueAt(table.getSelectedRow(), 1).toString());
                    txtMaKH.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
                    txtManv.setText(table.getValueAt(table.getSelectedRow(), 3).toString());
                    txtSoNgayThue.setText(table.getValueAt(table.getSelectedRow(), 4).toString());
                    txtDonGia.setText(table.getValueAt(table.getSelectedRow(), 5).toString());
                    txtNgayThue.setText(table.getValueAt(table.getSelectedRow(), 6).toString());
                }
            
        }
    }
}