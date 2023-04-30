/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Controller.HangSanXuatDataBase;
import Controller.ChiTietHDDataBase;
import Controller.DB_Process;
import Controller.HangDatabase;
import Controller.HoaDonDataBase;
import Controller.KhachHangDatabase;
import Controller.NhanVienDataBase;
import Controller.ReadTien;
import Model.HangSanXuat;
import Model.ChiTietHD;
import Model.Hang;
import Model.HoaDon;
import Model.KhachHang;
import Model.NhanVien;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class User_Main extends javax.swing.JFrame {

    ReadTien readTien = new ReadTien();
    Vector<String> v_header = new Vector<>();
    Vector v_content = new Vector<>();
    Vector<String> v_header_Hang = new Vector<>();
    Vector v_content_Hang = new Vector<>();
    Vector<String> v_header_HangSanXuat = new Vector<>();
    Vector v_content_HangSanXuat = new Vector<>();
    Vector<String> v_header_HoaDon = new Vector<>();
    Vector v_content_HoaDon = new Vector<>();
    Vector<String> v_header_Hang_HD = new Vector<>();
    Vector v_content_Hang_HD = new Vector<>();

    DefaultTableModel dtm = new DefaultTableModel();
    DefaultTableModel dtm_Hang = new DefaultTableModel();
    DefaultTableModel dtm_HangSanXuat = new DefaultTableModel();
    DefaultTableModel dtm_hd = new DefaultTableModel();
    DefaultTableModel dtm_hang_hd = new DefaultTableModel();

    ArrayList<NhanVien> list_NhanVien = new ArrayList<>();
    ArrayList<KhachHang> list_KhachHang = new ArrayList<>();
    ArrayList<HangSanXuat> list_HangSanXuat = new ArrayList<>();
    ArrayList<Hang> list_Hang = new ArrayList<>();
    ArrayList<HoaDon> list_HD = new ArrayList<>();
    ArrayList<ChiTietHD> list_ChiTietHD = new ArrayList<>();
    DB_Process p = new DB_Process();
    HangSanXuatDataBase hangSanXuatDataBase = new HangSanXuatDataBase();
    HangDatabase hangDatabase = new HangDatabase();
    KhachHangDatabase khachHangDatabase = new KhachHangDatabase();
    HoaDonDataBase hoaDonDataBase = new HoaDonDataBase();
    ChiTietHDDataBase chiTietHDDataBase = new ChiTietHDDataBase();
    NhanVienDataBase nhanVienDataBase = new NhanVienDataBase();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
    Date date = new Date();
    boolean check = true;
    static String role;
    static String id;
    String strAnhHang = null;

    public User_Main(String role, String id) {
        initComponents();
        setLocationRelativeTo(null);

        this.role = role;
        this.id = id;

        this.setTitle("User_UI");
        v_header.add("Mã khách");
        v_header.add("Họ tên");
        v_header.add("Giới tính");
        v_header.add("Địa chỉ");
        v_header.add("SĐT");
        dtm.setDataVector(v_content, v_header);
        tb_khachHang.setModel(dtm);
        list_NhanVien = nhanVienDataBase.GetNhanVien();
        list_KhachHang = khachHangDatabase.getKhachHang();
        list_HangSanXuat = hangSanXuatDataBase.getHangSanXuat();
        list_Hang = hangDatabase.GetHang();
        list_HD = hoaDonDataBase.getHoaDon();
        for (int i = 0; i < list_KhachHang.size(); i++) {
            Vector<String> v_record = new Vector<>();
            v_record.add(list_KhachHang.get(i).getMaKhach());
            v_record.add(list_KhachHang.get(i).getTenKhach());
            v_record.add(list_KhachHang.get(i).getGioiTinh());
            v_record.add(list_KhachHang.get(i).getDiaChi());
            v_record.add(list_KhachHang.get(i).getSDT());
            v_content.add(v_record);
        }
        dtm.setDataVector(v_content, v_header);
        tb_khachHang.setModel(dtm);

        v_header_HangSanXuat.add("Mã hãng sản xuất");
        v_header_HangSanXuat.add("Tên hãng sản xuất");
        for (int i = 0; i < list_HangSanXuat.size(); i++) {
            Vector<String> v_record = new Vector<>();
            v_record.add(list_HangSanXuat.get(i).getMaHangSanXuat());
            v_record.add(list_HangSanXuat.get(i).getTenHangSanXuat());
            v_content_HangSanXuat.add(v_record);
        }
        dtm_HangSanXuat.setDataVector(v_content_HangSanXuat, v_header_HangSanXuat);
        tb_HangSanXuat.setModel(dtm_HangSanXuat);

        v_header_Hang.add("Mã hàng");
        v_header_Hang.add("Tên hàng");
        v_header_Hang.add("Tên hãng sản xuất");
        v_header_Hang.add("Số lượng");
        v_header_Hang.add("Đơn giá nhập");
        v_header_Hang.add("Đơn giá bán");
        v_header_Hang.add("Ngày nhập");
        v_header_Hang.add("Ảnh hàng");

        for (int i = 0; i < list_Hang.size(); i++) {
            Vector<String> v_record = new Vector<>();
            v_record.add(list_Hang.get(i).getMaHang());
            v_record.add(list_Hang.get(i).getTenHang());
            v_record.add(hangSanXuatDataBase.getTenHangSanXuat(list_Hang.get(i).getMaHangSanXuat()));
            v_record.add(String.valueOf(list_Hang.get(i).getSoLuong()));
            v_record.add(String.valueOf(list_Hang.get(i).getDonGiaNhap()));
            v_record.add(String.valueOf(list_Hang.get(i).getDonGiaBan()));
            v_record.add(formatter.format(list_Hang.get(i).getNgayNhap()));
            v_record.add(list_Hang.get(i).getAnhHang());
            v_content_Hang.add(v_record);
        }
        dtm_Hang.setDataVector(v_content_Hang, v_header_Hang);
        tb_Hang.setModel(dtm_Hang);

        v_header_HoaDon.add("Mã hóa đơn");
        v_header_HoaDon.add("Tên nhân viên");
        v_header_HoaDon.add("Tên khách ");
        v_header_HoaDon.add("Ngày lập");
        v_header_HoaDon.add("Tổng tiền");

        for (HoaDon hd : list_HD) {
            Vector<String> v_record = new Vector<>();
            v_record.add(hd.getMaHD());
            v_record.add(nhanVienDataBase.getTenNhanVien(hd.getMaNV()));
            v_record.add(khachHangDatabase.getTenKhachHang(hd.getMaKhach()));
            v_record.add(formatter.format(hd.getNgayBan()));
            v_record.add(String.valueOf(hd.getTongTien()));
            v_content_HoaDon.add(v_record);
        }
        dtm_hd.setDataVector(v_content_HoaDon, v_header_HoaDon);
        tb_hd.setModel(dtm_hd);

        if (tb_khachHang.getRowCount() == 0) {
            bt_xoa_kh.setEnabled(false);
            bt_sua_kh.setEnabled(false);
        }

        for (HangSanXuat i : list_HangSanXuat) {
            cbb_mhsx.addItem(i.getMaHangSanXuat());
        }

        for (int i = 0; i < list_KhachHang.size(); i++) {
            cbb_mk_hd.addItem(list_KhachHang.get(i).getMaKhach());
        }

//        for(int i=0;i<list_NhanVien.size();i++){
//            cbb_mnv.addItem(list_NhanVien.get(i).getMaNV());
//        }  
        for (int i = 0; i < list_Hang.size(); i++) {
            cbb_mh_hd.addItem(list_Hang.get(i).getMaHang());
        }

        for (HoaDon hd : list_HD) {
            cbb_mhd_cthd.addItem(hd.getMaHD());
        }

        int index_cl = cbb_mhsx.getSelectedIndex();
        int index_kh = cbb_mk_hd.getSelectedIndex();
        //int index_nv=cbb_mnv.getSelectedIndex();
        int index_mh = cbb_mh_hd.getSelectedIndex();
        if (index_cl >= 0) {
            txt_tenHangSanXuat_MatHang.setText(list_HangSanXuat.get(index_cl).getTenHangSanXuat());
        }

        if (index_kh >= 0) {
            txt_tenKhach_hd.setText(list_KhachHang.get(index_kh).getTenKhach());
            txt_dc_hd.setText(list_KhachHang.get(index_kh).getDiaChi());
            txt_sdt_hd.setText(list_KhachHang.get(index_kh).getSDT());
        }
        if (index_mh >= 0) {
            txt_tenHang_hd.setText(list_Hang.get(index_mh).getTenHang());
            txt_donGia_hd.setText(String.valueOf(list_Hang.get(index_mh).getDonGiaBan()));

        }
        if (tb_HangSanXuat.getRowCount() == 0) {
            bt_xoa_HangSanXuat.setEnabled(false);
            bt_sua_HangSanXuat.setEnabled(false);
        }
        if (tb_Hang.getRowCount() == 0) {
            bt_xoa_mh.setEnabled(false);
            bt_sua_mh.setEnabled(false);
        }


        txt_mhd_hd.setText(p.ID() + p.getID_HD(list_HD.size(), p.v_ID_HD(list_HD)));
        txt_tenNhanVien_hd.setText(id);

        v_header_Hang_HD.add("Mã hàng");
        v_header_Hang_HD.add("Tên hàng");
        v_header_Hang_HD.add("Số lượng");
        v_header_Hang_HD.add("Đơn giá");
        v_header_Hang_HD.add("Giảm giá");
        v_header_Hang_HD.add("Thành tiền");
        v_content_Hang_HD.removeAllElements();
        list_ChiTietHD = chiTietHDDataBase.getChiTietHD(String.valueOf(cbb_mhd_cthd.getSelectedItem()));
        for (ChiTietHD cthd : list_ChiTietHD) {
            Vector<String> v_record = new Vector<>();
            v_record.add(cthd.getMaHang());
            v_record.add(hangDatabase.GetHang(cthd.getMaHang()).getTenHang());
            v_record.add(String.valueOf(cthd.getSoLuong()));
            v_record.add(String.valueOf(hangDatabase.GetHang(cthd.getMaHang()).getDonGiaBan()));
            v_record.add(String.valueOf(cthd.getGiamGia()));
            v_record.add(String.valueOf(cthd.getThanhTien()));
            v_content_Hang_HD.add(v_record);
        }
        dtm_hang_hd.setDataVector(v_content_Hang_HD, v_header_Hang_HD);
        tb_mh_hd.setModel(dtm_hang_hd);
        int TongTien = hoaDonDataBase.getHoaDon(String.valueOf(cbb_mhd_cthd.getSelectedItem())).getTongTien();
        txt_TongTien_HD.setText(String.valueOf(TongTien));
        lable_tongtien.setText(ReadTien.numberToString(new BigDecimal(TongTien)));
    }

    boolean check_Hang() {
        for (int i = 0; i < dtm_hang_hd.getRowCount(); i++) {
            if (dtm_hang_hd.getValueAt(i, 0).equals(String.valueOf(cbb_mh_hd.getSelectedItem()))) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_ht_kh = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        rdbt_nam = new javax.swing.JRadioButton();
        rdbt_nu = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        txt_dc_kh = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_sdt_kh = new javax.swing.JTextField();
        bt_them_kh = new javax.swing.JButton();
        bt_sua_kh = new javax.swing.JButton();
        bt_xoa_kh = new javax.swing.JButton();
        bt_dong_kh = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_khachHang = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txt_HangSanXuat = new javax.swing.JTextField();
        bt_them_HangSanXuat = new javax.swing.JButton();
        bt_sua_HangSanXuat = new javax.swing.JButton();
        bt_xoa_HangSanXuat = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tb_HangSanXuat = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txt_tenHang_mh = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txt_soLuong_mh = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        cbb_mhsx = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        txt_giaNhap_mh = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txt_GiaBan_mh = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txt_tenHangSanXuat_MatHang = new javax.swing.JTextField();
        lblAnhHang = new javax.swing.JLabel();
        bt_them_mh = new javax.swing.JButton();
        bt_sua_mh = new javax.swing.JButton();
        bt_xoa_mh = new javax.swing.JButton();
        bt_dong_mh = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_Hang = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txt_mhd_hd = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        txt_NgayLap_hd = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        txt_tenNhanVien_hd = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        cbb_mk_hd = new javax.swing.JComboBox<>();
        jLabel43 = new javax.swing.JLabel();
        txt_tenKhach_hd = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        txt_dc_hd = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        txt_sdt_hd = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        bt_them_hd = new javax.swing.JButton();
        bt_sua_hd = new javax.swing.JButton();
        bt_xoa_hd = new javax.swing.JButton();
        jPanel17 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tb_hd = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        cbb_mhd_cthd = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();
        cbb_mh_hd = new javax.swing.JComboBox<>();
        jLabel33 = new javax.swing.JLabel();
        txt_tenHang_hd = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        txt_soLuong_hd = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        txt_donGia_hd = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        txt_giamGia_hd = new javax.swing.JTextField();
        bt_them_cthd = new javax.swing.JButton();
        bt_sua_cthd = new javax.swing.JButton();
        bt_xoa_hang_cthd = new javax.swing.JButton();
        jPanel19 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tb_mh_hd = new javax.swing.JTable();
        jLabel46 = new javax.swing.JLabel();
        txt_TongTien_HD = new javax.swing.JTextField();
        jPanel18 = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        lable_tongtien = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel8.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("DANH MỤC KHÁCH HÀNG");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(315, 315, 315)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 2, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 51, 0));
        jLabel2.setText("Thông tin khách hàng");

        jLabel3.setText("Họ tên: ");

        jLabel4.setText("Giới tính: ");

        rdbt_nam.setBackground(new java.awt.Color(255, 255, 255));
        rdbt_nam.setSelected(true);
        rdbt_nam.setText("Nam");

        rdbt_nu.setBackground(new java.awt.Color(255, 255, 255));
        rdbt_nu.setText("Nữ");

        jLabel5.setText("Địa chỉ: ");

        jLabel6.setText("SDT: ");

        txt_sdt_kh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_sdt_khActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rdbt_nam, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(rdbt_nu, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(txt_ht_kh, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_dc_kh, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_sdt_kh, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(83, 83, 83))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_ht_kh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(rdbt_nam)
                                .addComponent(rdbt_nu))
                            .addComponent(jLabel4)))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txt_dc_kh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txt_sdt_kh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        bt_them_kh.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        bt_them_kh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Icon add.png"))); // NOI18N
        bt_them_kh.setText("ADD");
        bt_them_kh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        bt_them_kh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_them_khActionPerformed(evt);
            }
        });

        bt_sua_kh.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        bt_sua_kh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Icon update.png"))); // NOI18N
        bt_sua_kh.setText("UPDATE");
        bt_sua_kh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        bt_sua_kh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_sua_khActionPerformed(evt);
            }
        });

        bt_xoa_kh.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        bt_xoa_kh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Icon del.png"))); // NOI18N
        bt_xoa_kh.setText("DELETE");
        bt_xoa_kh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        bt_xoa_kh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_xoa_khActionPerformed(evt);
            }
        });

        bt_dong_kh.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        bt_dong_kh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Exit icon.png"))); // NOI18N
        bt_dong_kh.setText("BACK");
        bt_dong_kh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        bt_dong_kh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_dong_khActionPerformed(evt);
            }
        });

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        tb_khachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã", "Họ tên", "Giới tính", "Địa chỉ", "SĐT"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tb_khachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_khachHangMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tb_khachHang);

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 902, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 357, Short.MAX_VALUE)
            .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(90, Short.MAX_VALUE)
                .addComponent(bt_them_kh, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(bt_sua_kh, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bt_xoa_kh, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bt_dong_kh, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(83, 83, 83))
            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_them_kh)
                    .addComponent(bt_sua_kh, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_xoa_kh, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_dong_kh))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Khách hàng", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel7.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel7.setText("DANH MỤC HÃNG SẢN XUẤT");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(302, 302, 302)
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addContainerGap())
        );

        jPanel10.setBackground(new java.awt.Color(0, 0, 0));

        jLabel8.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Tên hãng sản xuất");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGap(34, 34, 34)
                .addComponent(txt_HangSanXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 542, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(91, 91, 91))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txt_HangSanXuat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        bt_them_HangSanXuat.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        bt_them_HangSanXuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Icon add.png"))); // NOI18N
        bt_them_HangSanXuat.setText("ADD");
        bt_them_HangSanXuat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        bt_them_HangSanXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_them_HangSanXuatActionPerformed(evt);
            }
        });

        bt_sua_HangSanXuat.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        bt_sua_HangSanXuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Icon update.png"))); // NOI18N
        bt_sua_HangSanXuat.setText("UPDATE");
        bt_sua_HangSanXuat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        bt_sua_HangSanXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_sua_HangSanXuatActionPerformed(evt);
            }
        });

        bt_xoa_HangSanXuat.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        bt_xoa_HangSanXuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Icon del.png"))); // NOI18N
        bt_xoa_HangSanXuat.setText("DELETE");
        bt_xoa_HangSanXuat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        bt_xoa_HangSanXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_xoa_HangSanXuatActionPerformed(evt);
            }
        });

        jButton8.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Exit icon.png"))); // NOI18N
        jButton8.setText("BACK");
        jButton8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tb_HangSanXuat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã hãng sản xuất", "Tên hãng sản xuất"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tb_HangSanXuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_HangSanXuatMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tb_HangSanXuat);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 904, Short.MAX_VALUE)
            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 904, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 405, Short.MAX_VALUE)
            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bt_them_HangSanXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bt_sua_HangSanXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bt_xoa_HangSanXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(83, 83, 83))
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bt_sua_HangSanXuat, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_xoa_HangSanXuat, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(bt_them_HangSanXuat)
                        .addComponent(jButton8)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Hãng sãn xuất", jPanel3);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel9.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel9.setText("DANH MỤC MẶT HÀNG");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addGap(336, 336, 336))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel10.setFont(new java.awt.Font("Segoe UI Black", 2, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 0, 0));
        jLabel10.setText("Thông tin mặt hàng");

        jLabel11.setText("Tên hàng: ");

        jLabel12.setText("Số lượng: ");

        jLabel13.setText("Mã HSX:");

        cbb_mhsx.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbb_mhsxItemStateChanged(evt);
            }
        });

        jLabel14.setText("Giá nhập: ");

        jLabel15.setText("Giá bán: ");

        jLabel16.setText("Tên HSX:");

        txt_tenHangSanXuat_MatHang.setEnabled(false);

        lblAnhHang.setText("Không có");
        lblAnhHang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        lblAnhHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAnhHangMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(18, 18, 18)
                                .addComponent(cbb_mhsx, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_soLuong_mh, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_tenHang_mh)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_tenHangSanXuat_MatHang))
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_giaNhap_mh))
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_GiaBan_mh, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(62, 62, 62)))
                .addComponent(lblAnhHang, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(lblAnhHang, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txt_tenHang_mh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)
                            .addComponent(txt_giaNhap_mh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txt_soLuong_mh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15)
                            .addComponent(txt_GiaBan_mh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel16)
                                    .addComponent(txt_tenHangSanXuat_MatHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel13)
                                    .addComponent(cbb_mhsx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(13, Short.MAX_VALUE))))))
        );

        bt_them_mh.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        bt_them_mh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Icon add.png"))); // NOI18N
        bt_them_mh.setText("ADD");
        bt_them_mh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        bt_them_mh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_them_mhActionPerformed(evt);
            }
        });

        bt_sua_mh.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        bt_sua_mh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Icon update.png"))); // NOI18N
        bt_sua_mh.setText("UPDATE");
        bt_sua_mh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        bt_sua_mh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_sua_mhActionPerformed(evt);
            }
        });

        bt_xoa_mh.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        bt_xoa_mh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Icon del.png"))); // NOI18N
        bt_xoa_mh.setText("DELETE");
        bt_xoa_mh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        bt_xoa_mh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_xoa_mhActionPerformed(evt);
            }
        });

        bt_dong_mh.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        bt_dong_mh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Exit icon.png"))); // NOI18N
        bt_dong_mh.setText("BACK");
        bt_dong_mh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        bt_dong_mh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_dong_mhActionPerformed(evt);
            }
        });

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        tb_Hang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã hàng", "Tên hàng", "Tên hãng sản xuất", "Số lượng", "Giá nhập", "Giá bán", "Ngày nhập hàng"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tb_Hang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_HangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_Hang);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(74, Short.MAX_VALUE)
                .addComponent(bt_them_mh, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(bt_sua_mh, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(bt_xoa_mh, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(bt_dong_mh, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_them_mh)
                    .addComponent(bt_sua_mh, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_xoa_mh, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_dong_mh))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Mặt hàng", jPanel4);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel18.setFont(new java.awt.Font("Segoe UI Black", 2, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 0, 0));
        jLabel18.setText("Thông tin hóa đơn");

        jLabel19.setText("Mã hóa đơn ");

        txt_mhd_hd.setEnabled(false);

        jLabel40.setText("Ngày lập");

        txt_NgayLap_hd.setText(formatter.format(date)
        );
        txt_NgayLap_hd.setEnabled(false);

        jLabel41.setText("Mã nhân viên");

        txt_tenNhanVien_hd.setEnabled(false);
        txt_tenNhanVien_hd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tenNhanVien_hdActionPerformed(evt);
            }
        });

        jLabel42.setText("Mã khách");

        cbb_mk_hd.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbb_mk_hdItemStateChanged(evt);
            }
        });

        jLabel43.setText("Tên khách");

        txt_tenKhach_hd.setEnabled(false);

        jLabel44.setText("Địa chỉ");

        txt_dc_hd.setEnabled(false);

        jLabel45.setText("SDT");

        txt_sdt_hd.setEnabled(false);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Exit icon.png"))); // NOI18N
        jButton1.setText("Back");
        jButton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        bt_them_hd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Icon add.png"))); // NOI18N
        bt_them_hd.setText("Add");
        bt_them_hd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        bt_them_hd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_them_hdActionPerformed(evt);
            }
        });

        bt_sua_hd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Icon update.png"))); // NOI18N
        bt_sua_hd.setText("Update");
        bt_sua_hd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        bt_sua_hd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_sua_hdActionPerformed(evt);
            }
        });

        bt_xoa_hd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Icon del.png"))); // NOI18N
        bt_xoa_hd.setText("Del");
        bt_xoa_hd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        bt_xoa_hd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_xoa_hdActionPerformed(evt);
            }
        });

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        tb_hd.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã hóa đơn", "Tên nhân viên", "Tên khách", "Ngày lập", "Tổng tiền"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tb_hd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_hdMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tb_hd);

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bt_them_hd, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bt_sua_hd, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bt_xoa_hd, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel40)
                            .addComponent(jLabel42)
                            .addComponent(jLabel43)
                            .addComponent(jLabel44)
                            .addComponent(jLabel45)
                            .addComponent(jLabel41)
                            .addComponent(jLabel19))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_dc_hd)
                            .addComponent(txt_tenNhanVien_hd)
                            .addComponent(txt_NgayLap_hd, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                            .addComponent(txt_tenKhach_hd)
                            .addComponent(cbb_mk_hd, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_sdt_hd)
                            .addComponent(txt_mhd_hd))))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txt_mhd_hd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(txt_NgayLap_hd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(txt_tenNhanVien_hd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42)
                    .addComponent(cbb_mk_hd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43)
                    .addComponent(txt_tenKhach_hd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44)
                    .addComponent(txt_dc_hd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45)
                    .addComponent(txt_sdt_hd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_them_hd, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_sua_hd, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_xoa_hd, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel20.setFont(new java.awt.Font("Segoe UI Black", 2, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 0, 0));
        jLabel20.setText("Chi tiết hóa đơn");

        jLabel21.setText("Mã HD");

        cbb_mhd_cthd.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbb_mhd_cthdItemStateChanged(evt);
            }
        });

        jLabel22.setText("Mã hàng");

        cbb_mh_hd.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbb_mh_hdItemStateChanged(evt);
            }
        });

        jLabel33.setText("Tên hàng");

        txt_tenHang_hd.setEnabled(false);

        jLabel34.setText("Số lượng");

        jLabel35.setText("Đơn giá");

        txt_donGia_hd.setEnabled(false);

        jLabel36.setText("Giảm giá (%)");

        bt_them_cthd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Icon add.png"))); // NOI18N
        bt_them_cthd.setText("Add");
        bt_them_cthd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        bt_them_cthd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_them_cthdActionPerformed(evt);
            }
        });

        bt_sua_cthd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Icon update.png"))); // NOI18N
        bt_sua_cthd.setText("Update");
        bt_sua_cthd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        bt_sua_cthd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_sua_cthdActionPerformed(evt);
            }
        });

        bt_xoa_hang_cthd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Exit icon.png"))); // NOI18N
        bt_xoa_hang_cthd.setText("Del");
        bt_xoa_hang_cthd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        bt_xoa_hang_cthd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_xoa_hang_cthdActionPerformed(evt);
            }
        });

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));
        jPanel19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        tb_mh_hd.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã hàng ", "Tên hàng", "Số lượng", "Đơn giá", "Giảm giá", "Thành tiền"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tb_mh_hd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_mh_hdMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tb_mh_hd);

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jLabel46.setText("Tổng tiền");

        txt_TongTien_HD.setEnabled(false);

        jLabel39.setText("Bằng chữ");

        lable_tongtien.setText("\n");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel39)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lable_tongtien, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lable_tongtien)
                    .addComponent(jLabel39))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addComponent(bt_them_cthd, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(bt_sua_cthd, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(bt_xoa_hang_cthd, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel46)
                                .addGap(18, 18, 18)
                                .addComponent(txt_TongTien_HD, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel35)
                                    .addComponent(jLabel34)
                                    .addComponent(jLabel21)
                                    .addComponent(jLabel36))
                                .addGap(35, 35, 35)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_soLuong_hd)
                                    .addComponent(txt_donGia_hd)
                                    .addComponent(cbb_mhd_cthd, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txt_giamGia_hd)))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel33)
                                    .addComponent(jLabel22))
                                .addGap(54, 54, 54)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbb_mh_hd, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txt_tenHang_hd)))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(cbb_mhd_cthd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(cbb_mh_hd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(txt_tenHang_hd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(txt_soLuong_hd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(txt_donGia_hd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(txt_giamGia_hd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_them_cthd, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_sua_cthd, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_xoa_hang_cthd, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46)
                    .addComponent(txt_TongTien_HD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Hóa đơn", jPanel5);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbb_mk_hdItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbb_mk_hdItemStateChanged
        // TODO add your handling code here:
        int index = cbb_mk_hd.getSelectedIndex();
        txt_tenKhach_hd.setText(list_KhachHang.get(index).getTenKhach());
        txt_dc_hd.setText(list_KhachHang.get(index).getDiaChi());
        txt_sdt_hd.setText(list_KhachHang.get(index).getSDT());
    }//GEN-LAST:event_cbb_mk_hdItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        ChucNang_Main cnm = new ChucNang_Main(role, id);
        cnm.show();
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void bt_them_hdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_them_hdActionPerformed
        if (role.equals("Quản lý")) {

            if (cbb_mk_hd.getSelectedIndex() != -1) {
                bt_xoa_hd.setEnabled(true);
                bt_sua_hd.setEnabled(true);
                String ID = p.ID() + p.getID_HD(list_HD.size(), p.v_ID_HD(list_HD));
                HoaDon hd = new HoaDon(ID, id, String.valueOf(cbb_mk_hd.getSelectedItem()), date, 0);
                Vector<String> v_record = new Vector<>();
                v_record.add(ID);
                v_record.add(nhanVienDataBase.getTenNhanVien(id));
                v_record.add(txt_tenKhach_hd.getText());
                v_record.add(formatter.format(date));
                v_record.add("0");
                v_content_HoaDon.add(v_record);
                dtm_hd.setDataVector(v_content_HoaDon, v_header_HoaDon);
                hoaDonDataBase.insertHoaDon(hd);
                list_HD.add(hd);
                cbb_mhd_cthd.addItem(ID);
                String ID1 = p.ID() + p.getID_HD(list_HD.size(), p.v_ID_HD(list_HD));
                txt_mhd_hd.setText(ID1);

                cbb_mhd_cthd.setSelectedIndex(list_HD.size() - 1);
            } else {
                int n = JOptionPane.showConfirmDialog(this, "Vui lòng chọn khách hàng!!!",
                        "Thông báo", JOptionPane.CLOSED_OPTION, 1);
            }
        } else {
            if (cbb_mk_hd.getSelectedIndex() != -1) {
                bt_xoa_hd.setEnabled(true);
                bt_sua_hd.setEnabled(true);
                String ID = p.ID() + p.getID_HD(list_HD.size(), p.v_ID_HD(list_HD));
                HoaDon hd = new HoaDon(ID, id, String.valueOf(cbb_mk_hd.getSelectedItem()), date, 0);
                Vector<String> v_record = new Vector<>();
                v_record.add(ID);
                v_record.add(nhanVienDataBase.getTenNhanVien(id));
                v_record.add(txt_tenKhach_hd.getText());
                v_record.add(formatter.format(date));
                v_record.add("0");
                v_content_HoaDon.add(v_record);
                dtm_hd.setDataVector(v_content_HoaDon, v_header_HoaDon);
                hoaDonDataBase.insertHoaDon(hd);
                list_HD.add(hd);
                cbb_mhd_cthd.addItem(ID);
                String ID1 = p.ID() + p.getID_HD(list_HD.size(), p.v_ID_HD(list_HD));
                txt_mhd_hd.setText(ID1);

                cbb_mhd_cthd.setSelectedIndex(list_HD.size() - 1);
            } else {
                int n = JOptionPane.showConfirmDialog(this, "Vui lòng chọn khách hàng!!!",
                        "Thông báo", JOptionPane.CLOSED_OPTION, 1);
            }
        }

    }//GEN-LAST:event_bt_them_hdActionPerformed

    private void bt_sua_hdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_sua_hdActionPerformed
        // TODO add your handling code here:
        int index = tb_hd.getSelectedRow();
        if (index != -1) {
            dtm_hd.setValueAt(txt_tenKhach_hd.getText(), index, 2);
            HoaDon hd = new HoaDon(txt_mhd_hd.getText(), role, String.valueOf(cbb_mk_hd.getSelectedItem()), date,
                    Integer.valueOf((String.valueOf(dtm_hd.getValueAt(index, 4)))));
            hoaDonDataBase.UpdateHD(hd);
            list_HD.set(index, hd);
        } else {
            int n = JOptionPane.showConfirmDialog(this, "Vui lòng chọn hóa đơn!!!", "Thông báo", JOptionPane.CLOSED_OPTION, 1);
        }
    }//GEN-LAST:event_bt_sua_hdActionPerformed

    private void bt_xoa_hdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_xoa_hdActionPerformed
        // TODO add your handling code here:
        if (role.equals("Quản lý")) {
            int index = tb_hd.getSelectedRow();
            if (index != -1) {
                dtm_hd.removeRow(index);
                p.deleteHD(list_HD.get(index).getMaHD());
                list_HD.remove(index);
                cbb_mhd_cthd.removeItemAt(index);
                list_Hang = hangDatabase.GetHang();
                for (int i = 0; i < list_Hang.size(); i++) {
                    dtm_Hang.setValueAt(list_Hang.get(i).getSoLuong(), i, 3);
                }
                if (list_HD.size() == 0) {
                    bt_xoa_hd.setEnabled(false);
                    bt_sua_hd.setEnabled(false);
                }

            } else {
                int n = JOptionPane.showConfirmDialog(this, "Vui lòng chọn hóa đơn!!!", "Thông báo", JOptionPane.CLOSED_OPTION, 1);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Nhân viên không có quyền xóa hóa đơn!");

        }
    }//GEN-LAST:event_bt_xoa_hdActionPerformed

    private void tb_hdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_hdMouseClicked
        // TODO add your handling code here:
        int index = tb_hd.getSelectedRow();
        txt_mhd_hd.setText(String.valueOf(tb_hd.getValueAt(index, 0)));
        txt_NgayLap_hd.setText(String.valueOf(tb_hd.getValueAt(index, 3)));
        txt_tenNhanVien_hd.setText(String.valueOf(tb_hd.getValueAt(index, 1)));
        cbb_mk_hd.setSelectedItem(list_HD.get(index).getMaKhach());
        cbb_mhd_cthd.setSelectedItem(list_HD.get(index).getMaHD());
    }//GEN-LAST:event_tb_hdMouseClicked

    private void cbb_mhd_cthdItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbb_mhd_cthdItemStateChanged
        // TODO add your handling code here:

        v_content_Hang_HD.removeAllElements();
        //dtm_hang_hd.getDataVector().removeAllElements();
        list_ChiTietHD = chiTietHDDataBase.getChiTietHD(String.valueOf(cbb_mhd_cthd.getSelectedItem()));
        for (ChiTietHD cthd : list_ChiTietHD) {
            Vector<String> v_record = new Vector<>();
            v_record.add(cthd.getMaHang());
            v_record.add(hangDatabase.GetHang(cthd.getMaHang()).getTenHang());
            v_record.add(String.valueOf(cthd.getSoLuong()));
            v_record.add(String.valueOf(hangDatabase.GetHang(cthd.getMaHang()).getDonGiaBan()));
            v_record.add(String.valueOf(cthd.getGiamGia()));
            v_record.add(String.valueOf(cthd.getThanhTien()));
            v_content_Hang_HD.add(v_record);
        }
        dtm_hang_hd.setDataVector(v_content_Hang_HD, v_header_Hang_HD);
        int TongTien = hoaDonDataBase.getHoaDon(String.valueOf(cbb_mhd_cthd.getSelectedItem())).getTongTien();
        txt_TongTien_HD.setText(String.valueOf(TongTien));
        lable_tongtien.setText(ReadTien.numberToString(new BigDecimal(TongTien)));
    }//GEN-LAST:event_cbb_mhd_cthdItemStateChanged

    private void cbb_mh_hdItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbb_mh_hdItemStateChanged
        // TODO add your handling code here:
        int index = cbb_mh_hd.getSelectedIndex();
        txt_tenHang_hd.setText(list_Hang.get(index).getTenHang());
        txt_donGia_hd.setText(String.valueOf(list_Hang.get(index).getDonGiaBan()));
    }//GEN-LAST:event_cbb_mh_hdItemStateChanged

    private void bt_them_cthdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_them_cthdActionPerformed
        //        // TODO add your handling code here:
        if (cbb_mh_hd.getSelectedIndex() != -1 && cbb_mhd_cthd.getSelectedIndex() != -1) {
            if (txt_giamGia_hd.getText().equals("")) {
                int n = JOptionPane.showConfirmDialog(this, "Nhập đúng thông tin giảm giá!!!",
                        "Thông báo", JOptionPane.CLOSED_OPTION, 1);
            } else if (txt_soLuong_hd.getText().equals("")) {
                int n = JOptionPane.showConfirmDialog(this, "Mặt đúng thông tin số lượng!!!",
                        "Thông báo", JOptionPane.CLOSED_OPTION, 1);
            } else {
                bt_sua_cthd.setEnabled(true);
                bt_xoa_hang_cthd.setEnabled(true);
                float GiamGia = Integer.valueOf(txt_giamGia_hd.getText());
                String ID = String.valueOf(cbb_mhd_cthd.getSelectedItem());
                Vector<String> v_record = new Vector<>();
                float ThanhTien;
                int SoLuong = Integer.valueOf(txt_soLuong_hd.getText());
                int SoLuongTon = hangDatabase.getSoLuongTon(String.valueOf(cbb_mh_hd.getSelectedItem()));
                int DonGia = Integer.valueOf(txt_donGia_hd.getText());
                ThanhTien = SoLuong * DonGia * (1 - GiamGia / 100);
                ChiTietHD cthd = new ChiTietHD(ID, String.valueOf(cbb_mh_hd.getSelectedItem()),
                        Integer.parseInt(txt_soLuong_hd.getText()), (int) GiamGia, (int) ThanhTien);
                if (chiTietHDDataBase.insertCHTHD(cthd)) {
                    v_record.add(String.valueOf(cbb_mh_hd.getSelectedItem()));
                    v_record.add(txt_tenHang_hd.getText());
                    v_record.add(txt_soLuong_hd.getText());
                    v_record.add(txt_donGia_hd.getText());
                    v_record.add(txt_giamGia_hd.getText());
                    v_record.add(String.valueOf((int) ThanhTien));
                    v_content_Hang_HD.add(v_record);
                    dtm_hang_hd.setDataVector(v_content_Hang_HD, v_header_Hang_HD);
                    int TongTien = 0;
                    for (int i = 0; i < tb_mh_hd.getRowCount(); i++) {
                        TongTien += Integer.parseInt((String.valueOf(tb_mh_hd.getValueAt(i, 5))));
                    }
                    txt_TongTien_HD.setText(String.valueOf(TongTien));
                    lable_tongtien.setText(ReadTien.numberToString(new BigDecimal(TongTien)));
                    dtm_hd.setValueAt(String.valueOf(TongTien), cbb_mhd_cthd.getSelectedIndex(), 4);
                    dtm_Hang.setValueAt(hangDatabase.getSoLuongTon(String.valueOf(cbb_mh_hd.getSelectedItem())),
                            cbb_mh_hd.getSelectedIndex(), 3);
                } else {
                    if (check_Hang()) {
                        int n = JOptionPane.showConfirmDialog(this, "Mặt hàng này đã được mua!!!",
                                "Thông báo", JOptionPane.CLOSED_OPTION, 1);
                    } else if (Integer.parseInt(txt_soLuong_hd.getText()) < 0) {
                        int n = JOptionPane.showConfirmDialog(this, "Nhập sai định dạng dữ liệu!!!",
                                "Thông báo", JOptionPane.CLOSED_OPTION, 1);
                    } else if (Integer.parseInt(txt_giamGia_hd.getText()) < 0) {
                        int n = JOptionPane.showConfirmDialog(this, "Nhập sai định dạng dữ liệu!!!",
                                "Thông báo", JOptionPane.CLOSED_OPTION, 1);
                    } else {
                        int n = JOptionPane.showConfirmDialog(this, "Mặt hàng này không đủ số lượng!!!",
                                "Thông báo", JOptionPane.CLOSED_OPTION, 1);
                    }
                }
            }
        } else {
            int n = JOptionPane.showConfirmDialog(this, "Vui chọn hóa đơn để thêm!!!",
                    "Thông báo", JOptionPane.CLOSED_OPTION, 1);
        }
    }//GEN-LAST:event_bt_them_cthdActionPerformed

    private void bt_sua_cthdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_sua_cthdActionPerformed
        // TODO add your handling code here:
        int index = tb_mh_hd.getSelectedRow();
        if (index != -1) {
            float ThanhTien;
            float GiamGia = Integer.valueOf(txt_giamGia_hd.getText());
            int SoLuong = Integer.valueOf(txt_soLuong_hd.getText());
            int DonGia = Integer.valueOf(txt_donGia_hd.getText());
            ThanhTien = SoLuong * DonGia * (1 - GiamGia / 100);

            ChiTietHD cthd = new ChiTietHD(String.valueOf(cbb_mhd_cthd.getSelectedItem()), String.valueOf(cbb_mh_hd.getSelectedItem()),
                    SoLuong, (int) GiamGia, (int) ThanhTien);
            if (chiTietHDDataBase.updateChiTietHD(cthd)) {
                dtm_hang_hd.setValueAt(String.valueOf(SoLuong), index, 2);
                dtm_hang_hd.setValueAt(String.valueOf((int) GiamGia), index, 4);
                dtm_hang_hd.setValueAt(String.valueOf((int) ThanhTien), index, 5);
                dtm_Hang.setValueAt(hangDatabase.getSoLuongTon(String.valueOf(cbb_mh_hd.getSelectedItem())),
                        cbb_mh_hd.getSelectedIndex(), 3);
                int TongTien = hoaDonDataBase.getHoaDon(String.valueOf(cbb_mhd_cthd.getSelectedItem())).getTongTien();
                dtm_Hang.setValueAt(hangDatabase.getSoLuongTon(String.valueOf(cbb_mh_hd.getSelectedItem())),
                        cbb_mh_hd.getSelectedIndex(), 3);
                dtm_hd.setValueAt(TongTien, cbb_mhd_cthd.getSelectedIndex(), 4);
                txt_TongTien_HD.setText(String.valueOf(TongTien));
                lable_tongtien.setText(ReadTien.numberToString(new BigDecimal(TongTien)));
            } else {
                int n = JOptionPane.showConfirmDialog(this, "Mặt hàng không còn đủ số lượng!!!",
                        "Thông báo", JOptionPane.CLOSED_OPTION, 1);
            }
        } else {
            int n = JOptionPane.showConfirmDialog(this, "Vui lòng chọn hàng để sửa!!!",
                    "Thông báo", JOptionPane.CLOSED_OPTION, 1);
        }
    }//GEN-LAST:event_bt_sua_cthdActionPerformed

    private void bt_xoa_hang_cthdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_xoa_hang_cthdActionPerformed
        // TODO add your handling code here:
        if (role.equals("Quản lý")) {

            int index = tb_mh_hd.getSelectedRow();
            if (index != -1) {

                chiTietHDDataBase.deleteCHTHD(String.valueOf(cbb_mhd_cthd.getSelectedItem()), String.valueOf(cbb_mh_hd.getSelectedItem()));
                dtm_hang_hd.removeRow(index);
                int TongTien = hoaDonDataBase.getHoaDon(String.valueOf(cbb_mhd_cthd.getSelectedItem())).getTongTien();
                dtm_hd.setValueAt(TongTien, cbb_mhd_cthd.getSelectedIndex(), 4);
                dtm_Hang.setValueAt(hangDatabase.getSoLuongTon(String.valueOf(cbb_mh_hd.getSelectedItem())),
                        cbb_mh_hd.getSelectedIndex(), 3);
                dtm_hd.setValueAt(TongTien, cbb_mhd_cthd.getSelectedIndex(), 4);
                txt_TongTien_HD.setText(String.valueOf(TongTien));
                lable_tongtien.setText(ReadTien.numberToString(new BigDecimal(TongTien)));

            } else {
                int n = JOptionPane.showConfirmDialog(this, "Vui lòng chọn hàng để xóa!!!",
                        "Thông báo", JOptionPane.CLOSED_OPTION, 1);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Nhân viên không có quyền xóa CTHD!");
        }
    }//GEN-LAST:event_bt_xoa_hang_cthdActionPerformed

    private void tb_mh_hdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_mh_hdMouseClicked
        // TODO add your handling code here:
        int index = tb_mh_hd.getSelectedRow();
        cbb_mh_hd.setSelectedItem(dtm_hang_hd.getValueAt(index, 0));
        //  txt_tenHang_hd.setText(String.valueOf(dtm_hang_hd.getValueAt(index, 1)));
        txt_soLuong_hd.setText(String.valueOf(dtm_hang_hd.getValueAt(index, 2)));
        //  txt_donGia_hd.setText(String.valueOf(dtm_hang_hd.getValueAt(index, 3)));
        int GiamGia = Integer.parseInt((String.valueOf(dtm_hang_hd.getValueAt(index, 4))));
        txt_giamGia_hd.setText(String.valueOf(GiamGia));
    }//GEN-LAST:event_tb_mh_hdMouseClicked

    private void cbb_mhsxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbb_mhsxItemStateChanged
        // TODO add your handling code here:
        int index = cbb_mhsx.getSelectedIndex();
        txt_tenHangSanXuat_MatHang.setText(list_HangSanXuat.get(index).getTenHangSanXuat());
    }//GEN-LAST:event_cbb_mhsxItemStateChanged

    private void lblAnhHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAnhHangMouseClicked
        // TODO add your handling code here:
        JFileChooser jfc = new JFileChooser("D:\\Duan1\\ManagerStore1\\src\\Image");
        jfc.showOpenDialog(null);
        File file = jfc.getSelectedFile();
        try {
            Image img = ImageIO.read(file);
            strAnhHang = file.getName();
            lblAnhHang.setText("");
            int width = lblAnhHang.getWidth();
            int height = lblAnhHang.getHeight();
            lblAnhHang.setIcon(new ImageIcon(img.getScaledInstance(width, height, 0)));
        } catch (IOException ex) {
            System.out.println("Error: " + ex.toString());
        }
    }//GEN-LAST:event_lblAnhHangMouseClicked

    private void bt_them_mhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_them_mhActionPerformed
        // TODO add your handling code here:
        if (txt_tenHang_mh.getText().equals("") && txt_soLuong_mh.getText().equals("") && txt_giaNhap_mh.getText().equals("") && txt_GiaBan_mh.getText().equals("")) {
            int n = JOptionPane.showConfirmDialog(this, "Vui lòng nhập đủ thông tin mặt hàng!!!", "Thông báo", JOptionPane.CLOSED_OPTION, 1);
        } else if (txt_tenHang_mh.getText().equals("")) {
            int n = JOptionPane.showConfirmDialog(this, "Vui lòng thông tin tên mặt hàng!!!", "Thông báo", JOptionPane.CLOSED_OPTION, 1);
        } else if (txt_soLuong_mh.getText().equals("")) {
            int n = JOptionPane.showConfirmDialog(this, "Vui lòng thông tin số lượng mặt hàng!!!", "Thông báo", JOptionPane.CLOSED_OPTION, 1);
        } else if (txt_GiaBan_mh.getText().equals("")) {
            int n = JOptionPane.showConfirmDialog(this, "Vui lòng thông tin giá bán mặt hàng!!!", "Thông báo", JOptionPane.CLOSED_OPTION, 1);
        } else if (txt_giaNhap_mh.getText().equals("")) {
            int n = JOptionPane.showConfirmDialog(this, "Vui lòng thông tin giá nhập mặt hàng!!!", "Thông báo", JOptionPane.CLOSED_OPTION, 1);
        } else if (txt_tenHangSanXuat_MatHang.getText().equals("")) {
            int n = JOptionPane.showConfirmDialog(this, "Vui lòng thông tin hãng sản xuất!!!", "Thông báo", JOptionPane.CLOSED_OPTION, 1);
        } else {
            bt_them_mh.setEnabled(true);
            bt_xoa_mh.setEnabled(true);
            if (Integer.parseInt(txt_soLuong_mh.getText()) > 0 && Integer.parseInt(txt_GiaBan_mh.getText()) > 0
                    && Integer.parseInt(txt_giaNhap_mh.getText()) > 0) {

                String ID = "MH" + p.getID(list_Hang.size(), p.v_ID_MH(list_Hang));
                try {
                    Hang h = new Hang(ID, txt_tenHang_mh.getText(), String.valueOf(cbb_mhsx.getSelectedItem()), Integer.parseInt(txt_soLuong_mh.getText()),
                            Integer.parseInt(txt_giaNhap_mh.getText()), Integer.parseInt(txt_GiaBan_mh.getText()),
                            date, lblAnhHang.getText());
                    if (strAnhHang == null) {
                        h.setAnhHang("Không có");
                    } else {
                        h.setAnhHang(strAnhHang);
                    }
                    Vector<String> v_record = new Vector<>();
                    v_record.add(h.getMaHang());
                    v_record.add(h.getTenHang());
                    v_record.add(txt_tenHangSanXuat_MatHang.getText());
                    v_record.add(String.valueOf(h.getSoLuong()));
                    v_record.add(String.valueOf(h.getDonGiaNhap()));
                    v_record.add(String.valueOf(h.getDonGiaBan()));
                    v_record.add(formatter.format(h.getNgayNhap()));
                    v_record.add(h.getAnhHang());
                    v_content_Hang.add(v_record);
                    list_Hang.add(h);
                    hangDatabase.insertHang(h);
                    cbb_mh_hd.addItem(ID);
                    dtm_Hang.setDataVector(v_content_Hang, v_header_Hang);
                } catch (Exception e) {
                }
            } else {
                int n = JOptionPane.showConfirmDialog(this, "Nhập thông tin chính xác!!!", "Thông báo", JOptionPane.CLOSED_OPTION, 1);
            }
        }
    }//GEN-LAST:event_bt_them_mhActionPerformed

    private void bt_sua_mhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_sua_mhActionPerformed
        // TODO add your handling code here:
        int index = tb_Hang.getSelectedRow();
        if (index != -1) {
            try {
                Hang h = new Hang(list_Hang.get(index).getMaHang(), txt_tenHang_mh.getText(), String.valueOf(cbb_mhsx.getSelectedItem()),
                        Integer.parseInt(txt_soLuong_mh.getText()), Integer.parseInt(txt_giaNhap_mh.getText()),
                        Integer.parseInt(txt_GiaBan_mh.getText()),
                        date, lblAnhHang.getText());
                if (strAnhHang == null) {
                    h.setAnhHang("Không có");
                } else {
                    h.setAnhHang(strAnhHang);
                }
                if (hangDatabase.updateHang(h)) {
                    String tenhang = String.valueOf(dtm_Hang.getValueAt(index, 1));
                    dtm_Hang.setValueAt(txt_tenHang_mh.getText(), index, 1);
                    dtm_Hang.setValueAt(txt_tenHangSanXuat_MatHang.getText(), index, 2);
                    dtm_Hang.setValueAt(txt_soLuong_mh.getText(), index, 3);
                    dtm_Hang.setValueAt(txt_giaNhap_mh.getText(), index, 4);
                    dtm_Hang.setValueAt(txt_GiaBan_mh.getText(), index, 5);
                    dtm_Hang.setValueAt(lblAnhHang.getText(), index, 7);
                    list_Hang.set(index, h);
                    txt_tenHang_hd.setText(list_Hang.get(cbb_mh_hd.getSelectedIndex()).getTenHang());
                    txt_donGia_hd.setText(String.valueOf(list_Hang.get(cbb_mh_hd.getSelectedIndex()).getDonGiaBan()));

                    for (int i = 0; i < list_ChiTietHD.size(); i++) {
                        if (dtm_hang_hd.getValueAt(i, 1).equals(tenhang)) {
                            dtm_hang_hd.setValueAt(txt_tenHang_mh.getText(), i, 1);
                        }
                    }
                } else {
                    int n = JOptionPane.showConfirmDialog(this, "Sửa không thành công!!!", "Thông báo", JOptionPane.CLOSED_OPTION, 1);
                }

            } catch (Exception e) {
            }

        } else {
            int n = JOptionPane.showConfirmDialog(this, "Vui lòng chọn  mặt hàng!!!", "Thông báo", JOptionPane.CLOSED_OPTION, 1);
        }
    }//GEN-LAST:event_bt_sua_mhActionPerformed

    private void bt_xoa_mhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_xoa_mhActionPerformed
        // TODO add your handling code here:
        int index = tb_Hang.getSelectedRow();
        if (index != -1) {
            if (hangDatabase.deleteHang(list_Hang.get(index).getMaHang())) {
                dtm_Hang.removeRow(index);
                list_Hang.remove(index);
                cbb_mh_hd.removeItemAt(index);

                if (tb_Hang.getRowCount() == 0) {
                    bt_xoa_mh.setEnabled(false);
                    bt_sua_mh.setEnabled(false);
                }
            } else {
                int n = JOptionPane.showConfirmDialog(this, "Mặt hàng đang được bán không thể xóa!!!", "Thông báo", JOptionPane.CLOSED_OPTION, 1);
            }

        } else {
            int n = JOptionPane.showConfirmDialog(this, "Vui lòng chọn  mặt hàng!!!", "Thông báo", JOptionPane.CLOSED_OPTION, 1);
        }
    }//GEN-LAST:event_bt_xoa_mhActionPerformed

    private void bt_dong_mhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_dong_mhActionPerformed
        // TODO add your handling code here:
        this.dispose();
        ChucNang_Main cnm = new ChucNang_Main(role, id);
        cnm.show();
    }//GEN-LAST:event_bt_dong_mhActionPerformed

    private void tb_HangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_HangMouseClicked
        // TODO add your handling code here:
        int index = tb_Hang.getSelectedRow();
        txt_tenHang_mh.setText(list_Hang.get(index).getTenHang());
        txt_soLuong_mh.setText(String.valueOf(dtm_Hang.getValueAt(index, 3)));
        txt_giaNhap_mh.setText(String.valueOf(list_Hang.get(index).getDonGiaNhap()));
        txt_GiaBan_mh.setText(String.valueOf(list_Hang.get(index).getDonGiaBan()));
        cbb_mhsx.setSelectedItem(list_Hang.get(index).getMaHangSanXuat());
        if (list_Hang.get(index).getAnhHang().equalsIgnoreCase("Không có")) {
            lblAnhHang.setText("Không có");
            lblAnhHang.setIcon(null);

        } else {
            lblAnhHang.setText("");
            ImageIcon imgIcon = new ImageIcon(getClass().getResource("/Image/" + list_Hang.get(index).getAnhHang()));
            Image img = imgIcon.getImage();
            Image newImg = img.getScaledInstance(lblAnhHang.getWidth(), lblAnhHang.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon image = new ImageIcon(newImg);
            lblAnhHang.setIcon(image);
        }
    }//GEN-LAST:event_tb_HangMouseClicked

    private void bt_them_HangSanXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_them_HangSanXuatActionPerformed
        // TODO add your handling code here:
        int ID = p.getID(list_HangSanXuat.size(), p.v_ID_MHSX(list_HangSanXuat));
        if (txt_HangSanXuat.getText().equals("")) {
            int n = JOptionPane.showConfirmDialog(this, "Vui lòng thông tin tên hãng sản xuất!!!", "Thông báo", JOptionPane.CLOSED_OPTION, 1);
        } else {
            bt_them_HangSanXuat.setEnabled(true);
            bt_xoa_HangSanXuat.setEnabled(true);
            HangSanXuat hsx = new HangSanXuat("HSX" + ID, txt_HangSanXuat.getText());
            Vector<String> v_record = new Vector<>();
            v_record.add(hsx.getMaHangSanXuat());
            v_record.add(hsx.getTenHangSanXuat());
            v_content_HangSanXuat.add(v_record);
            hangSanXuatDataBase.InsertHangSanXuat(hsx);
            list_HangSanXuat.add(hsx);
            cbb_mhsx.addItem(hsx.getMaHangSanXuat());
            dtm_HangSanXuat.setDataVector(v_content_HangSanXuat, v_header_HangSanXuat);
        }
    }//GEN-LAST:event_bt_them_HangSanXuatActionPerformed

    private void bt_sua_HangSanXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_sua_HangSanXuatActionPerformed
        // TODO add your handling code here:
        if (tb_HangSanXuat.getSelectedRow() != -1) {
            int index = tb_HangSanXuat.getSelectedRow();
            String Ten = String.valueOf(dtm_HangSanXuat.getValueAt(index, 1));
            HangSanXuat hsx = new HangSanXuat(String.valueOf(dtm_HangSanXuat.getValueAt(index, 0)), txt_HangSanXuat.getText());
            dtm_HangSanXuat.setValueAt(txt_HangSanXuat.getText(), index, 1);
            list_HangSanXuat.set(index, hsx);
            hangSanXuatDataBase.UpdateHangSanXuat(hsx);

            for (int i = 0; i < list_Hang.size(); i++) {
                if (String.valueOf(dtm_Hang.getValueAt(i, 2)).equals(Ten)) {
                    dtm_Hang.setValueAt(hangSanXuatDataBase.getTenHangSanXuat(list_Hang.get(i).getMaHangSanXuat()), i, 2);
                }
            }
            txt_tenHangSanXuat_MatHang.setText(hangSanXuatDataBase.getTenHangSanXuat(String.valueOf(cbb_mhsx.getSelectedItem())));

        } else {
            int n = JOptionPane.showConfirmDialog(this, "Vui lòng chọn hãng sản xuất!!!", "Thông báo", JOptionPane.CLOSED_OPTION, 1);
        }
    }//GEN-LAST:event_bt_sua_HangSanXuatActionPerformed

    private void bt_xoa_HangSanXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_xoa_HangSanXuatActionPerformed
        // TODO add your handling code here:
        int index = tb_HangSanXuat.getSelectedRow();
        if (index != -1) {
            if (hangSanXuatDataBase.DeleteHangSanXuat(list_HangSanXuat.get(index).getMaHangSanXuat())) {
                list_HangSanXuat.remove(index);
                dtm_HangSanXuat.removeRow(index);
                cbb_mhsx.removeItemAt(index);
            } else {
                int n = JOptionPane.showConfirmDialog(this, "Không thể xóa hãng sản xuất!!!", "Thông báo", JOptionPane.CLOSED_OPTION, 1);
            }

            if (tb_HangSanXuat.getRowCount() == 0) {
                bt_xoa_HangSanXuat.setEnabled(false);
                bt_sua_HangSanXuat.setEnabled(false);
            }
        } else {
            int n = JOptionPane.showConfirmDialog(this, "Chọn hãng sản xuất!!!", "Thông báo", JOptionPane.CLOSED_OPTION, 1);
        }
    }//GEN-LAST:event_bt_xoa_HangSanXuatActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        ChucNang_Main cnm = new ChucNang_Main(role, id);
        cnm.show();
        this.dispose();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void tb_HangSanXuatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_HangSanXuatMouseClicked
        // TODO add your handling code here:
        int index = tb_HangSanXuat.getSelectedRow();
        txt_HangSanXuat.setText(list_HangSanXuat.get(index).getTenHangSanXuat());
    }//GEN-LAST:event_tb_HangSanXuatMouseClicked

    private void txt_sdt_khActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_sdt_khActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_sdt_khActionPerformed

    private void bt_them_khActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_them_khActionPerformed
        // TODO add your handling code here:

        if (txt_dc_kh.getText().equals("") && txt_ht_kh.getText().equals("") && txt_sdt_kh.getText().equals("")) {
            int n = JOptionPane.showConfirmDialog(this, "Vui lòng nhập đủ thông tin khách hàng!!!", "Thông báo", JOptionPane.CLOSED_OPTION, 1);
        } else if (txt_ht_kh.getText().equals("")) {
            int n = JOptionPane.showConfirmDialog(this, "Vui lòng nhập họ tên khách hàng!!!", "Thông báo", JOptionPane.CLOSED_OPTION, 1);
        } else if (txt_dc_kh.getText().equals("")) {
            int n = JOptionPane.showConfirmDialog(this, "Vui lòng nhập địa chỉ khách hàng!!!", "Thông báo", JOptionPane.CLOSED_OPTION, 1);
        } else if (txt_sdt_kh.getText().equals("")) {
            int n = JOptionPane.showConfirmDialog(this, "Vui lòng nhập số điện thoaị khách hàng!!!", "Thông báo", JOptionPane.CLOSED_OPTION, 1);
        } else if (!p.isSDT(txt_sdt_kh.getText())) {
            int n = JOptionPane.showConfirmDialog(this, "Vui lòng nhập đúng số điện thoaị khách hàng!!!", "Thông báo", JOptionPane.CLOSED_OPTION, 1);
        } else {

            String ID = "KH" + p.getID(list_KhachHang.size(), p.v_ID_KH(list_KhachHang));
            KhachHang kh = new KhachHang(ID, txt_ht_kh.getText(), rdbt_nam.isSelected() ? "Nam" : "Nữ", txt_dc_kh.getText(),
                    txt_sdt_kh.getText());

            if (khachHangDatabase.InsertKhachHang(kh)) {
                bt_them_kh.setEnabled(true);
                bt_sua_kh.setEnabled(true);
                Vector<String> v_record = new Vector<>();
                v_record.add(kh.getMaKhach());
                v_record.add(kh.getTenKhach());
                v_record.add(kh.getGioiTinh());
                v_record.add(kh.getDiaChi());
                v_record.add(kh.getSDT());
                list_KhachHang.add(kh);
                v_content.add(v_record);
                dtm.setDataVector(v_content, v_header);

                cbb_mk_hd.addItem(kh.getMaKhach());
            } else {
                int n = JOptionPane.showConfirmDialog(this, "Số điện thoaị của khách hàng đã tồn tại!!!", "Thông báo", JOptionPane.CLOSED_OPTION, 1);
            }

        }
    }//GEN-LAST:event_bt_them_khActionPerformed

    private void bt_sua_khActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_sua_khActionPerformed
        // TODO add your handling code here:

        int index = tb_khachHang.getSelectedRow();

        if (index != -1 && index < khachHangDatabase.IndexKhachHang()) {
            String ht = String.valueOf(dtm.getValueAt(index, 1));
            dtm.setValueAt(txt_ht_kh.getText(), index, 1);
            dtm.setValueAt(rdbt_nam.isSelected() ? "Nam" : "Nữ", index, 2);
            dtm.setValueAt(txt_dc_kh.getText(), index, 3);
            dtm.setValueAt(txt_sdt_kh.getText(), index, 4);
            KhachHang kh = new KhachHang(String.valueOf(dtm.getValueAt(index, 0)), txt_ht_kh.getText(), rdbt_nam.isSelected() ? "Nam" : "Nữ",
                    txt_dc_kh.getText(), txt_sdt_kh.getText());
            khachHangDatabase.UpdateKhachHang(kh);
            list_KhachHang.set(index, kh);

            txt_tenKhach_hd.setText(list_KhachHang.get(cbb_mk_hd.getSelectedIndex()).getTenKhach());
            txt_dc_hd.setText(list_KhachHang.get(cbb_mk_hd.getSelectedIndex()).getDiaChi());
            txt_sdt_hd.setText(list_KhachHang.get(cbb_mk_hd.getSelectedIndex()).getSDT());
            for (int i = 0; i < list_HD.size(); i++) {
                if (tb_hd.getValueAt(i, 2).equals(ht)) {
                    tb_hd.setValueAt(txt_ht_kh.getText(), i, 2);
                }
            }

        } else {
            int n = JOptionPane.showConfirmDialog(this, "Vui lòng chọn đối tượng để sửa!!!", "Thông báo", JOptionPane.CLOSED_OPTION, 1);
        }
    }//GEN-LAST:event_bt_sua_khActionPerformed

    private void bt_xoa_khActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_xoa_khActionPerformed
        // TODO add your handling code here:

        int index = tb_khachHang.getSelectedRow();
        if (index != -1) {

            if (khachHangDatabase.DeleteKhachHang(list_KhachHang.get(index).getMaKhach())) {
                dtm.removeRow(index);
                list_KhachHang.remove(index);
                cbb_mk_hd.removeItemAt(index);
            } else {
                int n = JOptionPane.showConfirmDialog(this, "Khách hàng đang mua hàng không thể xóa!!!", "Thông báo", JOptionPane.CLOSED_OPTION, 1);
            }
            if (tb_khachHang.getRowCount() == 0) {
                bt_xoa_kh.setEnabled(false);
                bt_sua_kh.setEnabled(false);
            }
        } else {
            int n = JOptionPane.showConfirmDialog(this, "Vui lòng chọn đối tượng để xóa!!!", "Thông báo", JOptionPane.CLOSED_OPTION, 1);
        }
    }//GEN-LAST:event_bt_xoa_khActionPerformed

    private void bt_dong_khActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_dong_khActionPerformed
        // TODO add your handling code here:

        this.dispose();
        ChucNang_Main chucNang_Main = new ChucNang_Main("user", id);
        chucNang_Main.show();
    }//GEN-LAST:event_bt_dong_khActionPerformed

    private void tb_khachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_khachHangMouseClicked
        // TODO add your handling code here:
        int index = tb_khachHang.getSelectedRow();
        txt_ht_kh.setText(list_KhachHang.get(index).getTenKhach());
        txt_dc_kh.setText(list_KhachHang.get(index).getDiaChi());
        txt_sdt_kh.setText(list_KhachHang.get(index).getSDT());
        if (list_KhachHang.get(index).getGioiTinh().equals("Nam")) {
            rdbt_nam.setSelected(true);
        } else {
            rdbt_nu.setSelected(true);
        }
    }//GEN-LAST:event_tb_khachHangMouseClicked

    private void txt_tenNhanVien_hdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tenNhanVien_hdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tenNhanVien_hdActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(User_Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(User_Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(User_Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(User_Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new User_Main(role, id).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_dong_kh;
    private javax.swing.JButton bt_dong_mh;
    private javax.swing.JButton bt_sua_HangSanXuat;
    private javax.swing.JButton bt_sua_cthd;
    private javax.swing.JButton bt_sua_hd;
    private javax.swing.JButton bt_sua_kh;
    private javax.swing.JButton bt_sua_mh;
    private javax.swing.JButton bt_them_HangSanXuat;
    private javax.swing.JButton bt_them_cthd;
    private javax.swing.JButton bt_them_hd;
    private javax.swing.JButton bt_them_kh;
    private javax.swing.JButton bt_them_mh;
    private javax.swing.JButton bt_xoa_HangSanXuat;
    private javax.swing.JButton bt_xoa_hang_cthd;
    private javax.swing.JButton bt_xoa_hd;
    private javax.swing.JButton bt_xoa_kh;
    private javax.swing.JButton bt_xoa_mh;
    private javax.swing.JComboBox<String> cbb_mh_hd;
    private javax.swing.JComboBox<String> cbb_mhd_cthd;
    private javax.swing.JComboBox<String> cbb_mhsx;
    private javax.swing.JComboBox<String> cbb_mk_hd;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lable_tongtien;
    private javax.swing.JLabel lblAnhHang;
    private javax.swing.JRadioButton rdbt_nam;
    private javax.swing.JRadioButton rdbt_nu;
    private javax.swing.JTable tb_Hang;
    private javax.swing.JTable tb_HangSanXuat;
    private javax.swing.JTable tb_hd;
    private javax.swing.JTable tb_khachHang;
    private javax.swing.JTable tb_mh_hd;
    private javax.swing.JTextField txt_GiaBan_mh;
    private javax.swing.JTextField txt_HangSanXuat;
    private javax.swing.JTextField txt_NgayLap_hd;
    private javax.swing.JTextField txt_TongTien_HD;
    private javax.swing.JTextField txt_dc_hd;
    private javax.swing.JTextField txt_dc_kh;
    private javax.swing.JTextField txt_donGia_hd;
    private javax.swing.JTextField txt_giaNhap_mh;
    private javax.swing.JTextField txt_giamGia_hd;
    private javax.swing.JTextField txt_ht_kh;
    private javax.swing.JTextField txt_mhd_hd;
    private javax.swing.JTextField txt_sdt_hd;
    private javax.swing.JTextField txt_sdt_kh;
    private javax.swing.JTextField txt_soLuong_hd;
    private javax.swing.JTextField txt_soLuong_mh;
    private javax.swing.JTextField txt_tenHangSanXuat_MatHang;
    private javax.swing.JTextField txt_tenHang_hd;
    private javax.swing.JTextField txt_tenHang_mh;
    private javax.swing.JTextField txt_tenKhach_hd;
    private javax.swing.JTextField txt_tenNhanVien_hd;
    // End of variables declaration//GEN-END:variables
}
