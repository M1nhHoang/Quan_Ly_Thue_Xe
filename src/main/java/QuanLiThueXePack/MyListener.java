/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuanLiThueXePack;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author HP
 */
public class MyListener implements ActionListener{
    private Container container;
    private JPanel panel;
    private String text;
//    private FrameQuanLyThueXe f;
    
//    public MyListener(Container container, JPanel panel, String label){
//        this.container = container;
//        this.panel = panel;
//        this.text = label;
//        f = new FrameQuanLyThueXe();
//    }
    
    public MyListener(Container container, JPanel panel){
        this.container = container;
        this.panel = panel;
//        f = new FrameQuanLyThueXe();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof MenuComponent){
            MenuItem Item = (MenuItem)e.getSource();
            if (Item.getLabel().equals("Quản Lí Nhân Viên")){
                container.remove(panel);
//                f.IQuanLiNhanVien();
            }
            else if (Item.getLabel().equals("Quản Lí Khách Hàng")){
                
            }
            else if (Item.getLabel().equals("Quản Lí Xe Du Lịch")){
                
            }
            else if (Item.getLabel().equals("Quản Lí Xe Chở Hàng")){
                
            }
            else if (Item.getLabel().equals("Lập Hợp Đồng Cho Thuê")){
                
            }
        }
        if (e.getSource() instanceof Button){
            System.out.println(text);
        }
    }
    
}
