/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Controller.DB_Process;
import Controller.KhachHangDatabase;
import Controller.NhanVienDataBase;
import Model.KhachHang;
import Model.NhanVien;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Admin_Main extends javax.swing.JFrame {

    DefaultTableModel dtm_nhanVien = new DefaultTableModel();
    NhanVien nv1 = new NhanVien();
    String strHinhAnh = null;
    Vector v_header = new Vector<>();
    Vector v_content = new Vector<>();
    ArrayList<NhanVien> List_nv = new ArrayList<>();
    DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
    DB_Process p = new DB_Process();
    NhanVienDataBase nhanVienDataBase = new NhanVienDataBase();
    static String role = "";
    static String id = "";

    public Admin_Main(String role, String id) {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        this.role = role;
        this.id = id;
        ButtonGroup group = new ButtonGroup();
        group.add(rdbt_nam);
        group.add(rdbt_nu);
        DefaulTable();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txt_tennv = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_sdt = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        rdbt_nam = new javax.swing.JRadioButton();
        rdbt_nu = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_ns = new javax.swing.JTextField();
        txt_dc = new javax.swing.JTextField();
        cbb_cv = new javax.swing.JComboBox<>();
        lblAnhNV = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_nhanvien = new javax.swing.JTable();
        bt_them = new javax.swing.JButton();
        bt_sua = new javax.swing.JButton();
        bt_xoa = new javax.swing.JButton();
        bt_dong = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        jLabel1.setText("STAFF INFORMATION");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(252, 252, 252)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Tên:");

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("SĐT:");

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Giới tính:");

        rdbt_nam.setForeground(new java.awt.Color(255, 255, 255));
        rdbt_nam.setSelected(true);
        rdbt_nam.setText("Nam");

        rdbt_nu.setForeground(new java.awt.Color(255, 255, 255));
        rdbt_nu.setText("Nữ");
        rdbt_nu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbt_nuActionPerformed(evt);
            }
        });

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Ngày sinh:");

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Địa chỉ:");

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Chức vụ");

        cbb_cv.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nhân viên", "Quản lý" }));

        lblAnhNV.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        lblAnhNV.setForeground(new java.awt.Color(255, 255, 255));
        lblAnhNV.setText("Không có");
        lblAnhNV.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        lblAnhNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAnhNVMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(rdbt_nam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdbt_nu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(69, 69, 69))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txt_tennv)
                        .addComponent(txt_sdt, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txt_dc, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_ns, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbb_cv, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addComponent(lblAnhNV, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAnhNV, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txt_ns, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txt_dc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(cbb_cv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_tennv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txt_sdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(rdbt_nam)
                            .addComponent(rdbt_nu))))
                .addGap(25, 25, 25))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        tb_nhanvien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã nhân viên", "Tên nhân viên", "Giới tính", "Ngày sinh", "Địa chỉ", "SĐT", "Chức vụ"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tb_nhanvien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_nhanvienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_nhanvien);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
        );

        bt_them.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        bt_them.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Icon add.png"))); // NOI18N
        bt_them.setText("ADD");
        bt_them.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        bt_them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_themActionPerformed(evt);
            }
        });

        bt_sua.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        bt_sua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Icon update.png"))); // NOI18N
        bt_sua.setText("UPDATE");
        bt_sua.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        bt_sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_suaActionPerformed(evt);
            }
        });

        bt_xoa.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        bt_xoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Icon del.png"))); // NOI18N
        bt_xoa.setText("DELETE");
        bt_xoa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        bt_xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_xoaActionPerformed(evt);
            }
        });

        bt_dong.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        bt_dong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Exit icon.png"))); // NOI18N
        bt_dong.setText("BACK");
        bt_dong.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        bt_dong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_dongActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(bt_them, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75)
                .addComponent(bt_sua, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75)
                .addComponent(bt_xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bt_dong, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bt_xoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(bt_them, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(bt_sua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bt_dong, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rdbt_nuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbt_nuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdbt_nuActionPerformed

    private void lblAnhNVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAnhNVMouseClicked
        selectImage();
    }//GEN-LAST:event_lblAnhNVMouseClicked

    private void tb_nhanvienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_nhanvienMouseClicked
        fillTable();
    }//GEN-LAST:event_tb_nhanvienMouseClicked

    private void bt_themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_themActionPerformed
        addStaff();

    }//GEN-LAST:event_bt_themActionPerformed

    private void bt_suaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_suaActionPerformed
        updateStaff();
    }//GEN-LAST:event_bt_suaActionPerformed

    private void bt_xoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_xoaActionPerformed
        delStaff();
    }//GEN-LAST:event_bt_xoaActionPerformed

    private void bt_dongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_dongActionPerformed
        openAdmin_Role();

    }//GEN-LAST:event_bt_dongActionPerformed

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
            java.util.logging.Logger.getLogger(Admin_Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Admin_Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Admin_Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Admin_Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Admin_Main(role, id).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_dong;
    private javax.swing.JButton bt_sua;
    private javax.swing.JButton bt_them;
    private javax.swing.JButton bt_xoa;
    private javax.swing.JComboBox<String> cbb_cv;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAnhNV;
    private javax.swing.JRadioButton rdbt_nam;
    private javax.swing.JRadioButton rdbt_nu;
    private javax.swing.JTable tb_nhanvien;
    private javax.swing.JTextField txt_dc;
    private javax.swing.JTextField txt_ns;
    private javax.swing.JTextField txt_sdt;
    private javax.swing.JTextField txt_tennv;
    // End of variables declaration//GEN-END:variables
    private void DefaulTable() {
        v_header.add("Mã nhân viên");
        v_header.add("Họ tên");
        v_header.add("Ngày sinh");
        v_header.add("Giới tính");
        v_header.add("Địa chỉ");
        v_header.add("SĐT");
        v_header.add("Chức vụ");
        v_header.add("AnhNV");
        List_nv = nhanVienDataBase.GetNhanVien();
        for (int i = 0; i < List_nv.size(); i++) {
            Vector<String> v_record = new Vector<>();
            v_record.add(List_nv.get(i).getMaNV());
            v_record.add(List_nv.get(i).getTenNV());
            v_record.add(df.format(List_nv.get(i).getNgaySinh()));
            v_record.add(List_nv.get(i).getGioiTinh());
            v_record.add(List_nv.get(i).getDiaChi());
            v_record.add(List_nv.get(i).getSDT());
            v_record.add(List_nv.get(i).getChucVu());
            v_record.add(List_nv.get(i).getAnhNV());
            v_content.add(v_record);
        }
        dtm_nhanVien.setDataVector(v_content, v_header);
        tb_nhanvien.setModel(dtm_nhanVien);
    }

    boolean isValidDate(String input) {
        df.setLenient(false); // set false để kiểm tra tính hợp lệ của date. VD: tháng 2 phải có 28-29 ngày, năm có 12 tháng,....
        try {
            df.parse(input); // parse dateString thành kiểu Date
        } catch (ParseException e) { // quăng lỗi nếu dateString ko hợp lệ
            return false;
        }
        return true;
    }

    private void selectImage() {
        JFileChooser jfc = new JFileChooser("D:\\Duan1\\ANTKStore\\src\\Image");
        jfc.showOpenDialog(null);
        File file = jfc.getSelectedFile();
        try {
            Image img = ImageIO.read(file);
            strHinhAnh = file.getName();
            lblAnhNV.setText("");
            int width = lblAnhNV.getWidth();
            int height = lblAnhNV.getHeight();
            lblAnhNV.setIcon(new ImageIcon(img.getScaledInstance(width, height, 0)));
        } catch (IOException ex) {
            System.out.println("Error: " + ex.toString());
        }
    }

    private void fillTable() {
        if (tb_nhanvien.getSelectedRow() >= 0) {

            int index = tb_nhanvien.getSelectedRow();

            txt_tennv.setText(List_nv.get(index).getTenNV());
            txt_ns.setText(df.format(List_nv.get(index).getNgaySinh()));
            if (List_nv.get(index).getGioiTinh().equals("Nam")) {
                rdbt_nam.setSelected(true);
            } else {
                rdbt_nu.setSelected(true);
            }
            txt_dc.setText(List_nv.get(index).getDiaChi());
            txt_sdt.setText(List_nv.get(index).getSDT());
            cbb_cv.setSelectedItem(List_nv.get(index).getChucVu());
            if (List_nv.get(index).getAnhNV().equalsIgnoreCase("Không có")) {
                lblAnhNV.setText("Không có");
                lblAnhNV.setIcon(null);

            } else {
                lblAnhNV.setText("");
                ImageIcon imgIcon = new ImageIcon(getClass().getResource("/Image/" + List_nv.get(index).getAnhNV()));
                Image img = imgIcon.getImage();
                Image newImg = img.getScaledInstance(lblAnhNV.getWidth(), lblAnhNV.getHeight(), Image.SCALE_SMOOTH);
                ImageIcon image = new ImageIcon(newImg);
                lblAnhNV.setIcon(image);
            }
        }
    }

    private void addStaff() {
        if (txt_tennv.getText().equals("") && txt_dc.getText().equals("") && txt_ns.getText().equals("") && txt_sdt.getText().equals("")) {
            int n = JOptionPane.showConfirmDialog(this, "Vui lòng nhập đủ thông tin nhân viên!!!", "Thông báo", JOptionPane.CLOSED_OPTION, 1);

        } else {
            if (txt_tennv.getText().equals("")) {
                int n = JOptionPane.showConfirmDialog(this, "Vui lòng nhập họ tên nhân viên!!!", "Thông báo", JOptionPane.CLOSED_OPTION, 1);
            } else if (txt_ns.getText().equals("")) {
                int n = JOptionPane.showConfirmDialog(this, "Vui lòng nhập ngày sinh nhân viên!!!", "Thông báo", JOptionPane.CLOSED_OPTION, 1);
            } else if (txt_dc.getText().equals("")) {
                int n = JOptionPane.showConfirmDialog(this, "Vui lòng nhập địa chỉ nhân viên!!!", "Thông báo", JOptionPane.CLOSED_OPTION, 1);
            } else if (txt_sdt.getText().equals("")) {
                int n = JOptionPane.showConfirmDialog(this, "Vui lòng nhập số điện thoaị nhân viên!!!", "Thông báo", JOptionPane.CLOSED_OPTION, 1);
            } else if (!p.isSDT(txt_sdt.getText())) {
                int n = JOptionPane.showConfirmDialog(this, "Vui lòng nhập đúng số điện thoaị nhân viên!!!", "Thông báo", JOptionPane.CLOSED_OPTION, 1);
            } else {
                try {

                    Vector<String> v_record = new Vector<>();
                    String id = "TCLKT" + p.getID(List_nv.size(), nhanVienDataBase.v_ID_NV(List_nv));
                    if (p.isSDT(txt_sdt.getText())) {
                        Date d = new Date();
                        NhanVien nv = new NhanVien(id, txt_tennv.getText(), rdbt_nam.isSelected() ? "Nam" : "Nữ",
                                df.parse(txt_ns.getText()), txt_dc.getText(), txt_sdt.getText(), String.valueOf(cbb_cv.getSelectedItem()), lblAnhNV.getText());
                        if (strHinhAnh == null) {
                            nv.setAnhNV("Không có");
                        } else {
                            nv.setAnhNV(strHinhAnh);
                        }
                        if (isValidDate(txt_ns.getText()) && df.parse(txt_ns.getText()).compareTo(d) < 0) {
                            if (nhanVienDataBase.InsertNhanVien(nv)) {
                                List_nv.add(nv);
                                v_record.add(nv.getMaNV());
                                v_record.add(nv.getTenNV());
                                v_record.add(df.format(nv.getNgaySinh()));
                                v_record.add(nv.getGioiTinh());
                                v_record.add(nv.getDiaChi());
                                v_record.add(nv.getSDT());
                                v_record.add(nv.getChucVu());
                                v_record.add(nv.getAnhNV());
                                v_content.add(v_record);
                                List_nv.add(nv);
                                dtm_nhanVien.setDataVector(v_content, v_header);
                            } else {
                                int n = JOptionPane.showConfirmDialog(this, "SDT của nhân viên đã tồn tại!!!",
                                        "Thông báo", JOptionPane.CLOSED_OPTION, 1);
                            }
                        } else {
                            int n = JOptionPane.showConfirmDialog(this, "Vui lòng nhập đúng ngày sinh theo định dạng yyyy/mm/dd!!!",
                                    "Thông báo", JOptionPane.CLOSED_OPTION, 1);
                        }
                    } else {
                        int n = JOptionPane.showConfirmDialog(this, "Vui lòng nhập đúng SDT!!!",
                                "Thông báo", JOptionPane.CLOSED_OPTION, 1);
                    }
                    if (strHinhAnh == null) {
                        nv1.setAnhNV("Không có");
                    } else {
                        nv1.setAnhNV(strHinhAnh);
                    }

                } catch (Exception e) {
                    int n = JOptionPane.showConfirmDialog(this, "Vui lòng nhập đúng ngày sinh theo định dạng yyyy/mm/dd!!!",
                            "Thông báo", JOptionPane.CLOSED_OPTION, 1);
                }
            }

        }
    }

    private void updateStaff() {
        if (tb_nhanvien.getSelectedRow() >= 0) {
            int index = tb_nhanvien.getSelectedRow();

            try {
                NhanVien nv = new NhanVien(String.valueOf(tb_nhanvien.getValueAt(index, 0)),
                        txt_tennv.getText(), rdbt_nam.isSelected() ? "Nam" : "Nữ",
                        df.parse(txt_ns.getText()), txt_dc.getText(), txt_sdt.getText(), String.valueOf(cbb_cv.getSelectedItem()), lblAnhNV.getText());
                if (strHinhAnh == null) {
                    nv.setAnhNV("Không có");
                } else {
                    nv.setAnhNV(strHinhAnh);
                }
                if (p.isSDT(txt_sdt.getText())) {
                    Date d = new Date();
                    if (isValidDate(txt_ns.getText()) && df.parse(txt_ns.getText()).compareTo(d) < 0) {
                        if (nhanVienDataBase.UpdateNhanVien(nv)) {
                            List_nv.set(index, nv);
                            dtm_nhanVien.setValueAt(txt_tennv.getText(), index, 1);
                            dtm_nhanVien.setValueAt(txt_ns.getText(), index, 2);
                            dtm_nhanVien.setValueAt(rdbt_nam.isSelected() ? "Nam" : "Nữ", index, 3);
                            dtm_nhanVien.setValueAt(txt_dc.getText(), index, 4);
                            dtm_nhanVien.setValueAt(txt_sdt.getText(), index, 5);
                            dtm_nhanVien.setValueAt(String.valueOf(cbb_cv.getSelectedItem()), index, 6);
                            dtm_nhanVien.setValueAt(lblAnhNV.getText(), index, 7);
                        } else {
                            int n = JOptionPane.showConfirmDialog(this, "SDT của nhân viên đã tồn tại!!!",
                                    "Thông báo", JOptionPane.CLOSED_OPTION, 1);
                        }
                    } else {
                        int n = JOptionPane.showConfirmDialog(this, "Vui lòng nhập đúng ngày sinh theo định dạng yyyy/mm/dd!!!",
                                "   Thông báo", JOptionPane.CLOSED_OPTION, 1);
                    }
                } else {
                    int n = JOptionPane.showConfirmDialog(this, "Vui lòng nhập đúng SDT!!!",
                            "Thông báo", JOptionPane.CLOSED_OPTION, 1);
                }

            } catch (Exception e) {
                int n = JOptionPane.showConfirmDialog(this, "Vui lòng nhập đúng ngày sinh theo định dạng yyyy/mm/dd!!!",
                        "Thông báo", JOptionPane.CLOSED_OPTION, 1);
            }

        } else {
            int n = JOptionPane.showConfirmDialog(this, "Vui lòng chọn nhân viên !!!",
                    "Thông báo", JOptionPane.CLOSED_OPTION, 1);
        }
    }

    private void delStaff() {
        if (tb_nhanvien.getSelectedRow() >= 0) {
            int index = tb_nhanvien.getSelectedRow();
            if (nhanVienDataBase.DeleteNhanVien(List_nv.get(index).getMaNV())) {
                dtm_nhanVien.removeRow(index);
                List_nv.remove(index);
            } else {
                int n = JOptionPane.showConfirmDialog(this, "Nhân viên đang làm việc không thể xóa!!!",
                        "Thông báo", JOptionPane.CLOSED_OPTION, 1);
            }

        } else {
            int n = JOptionPane.showConfirmDialog(this, "Vui lòng chọn nhân viên !!!",
                    "Thông báo", JOptionPane.CLOSED_OPTION, 1);
        }
    }

    private void openAdmin_Role() {
        Admin_Role ar = new Admin_Role(role, id);
        ar.show();
        this.dispose();
    }

}
