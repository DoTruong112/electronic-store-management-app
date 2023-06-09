/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

/**
 *
 * @author 84375
 */
import Controller.DB_Connection;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class TimKiem_Hang extends javax.swing.JFrame {

    static String role;
    static String id;
    DefaultTableModel dtm = new DefaultTableModel();
    Vector v_header = new Vector();
    Vector v_content = new Vector();
    DateFormat df = new SimpleDateFormat("yyyy/MM/dd");

    public Date addDaysToJavaUtilDate(Date date, int hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, hours);
        return calendar.getTime();
    }

    public TimKiem_Hang(String role,String id) {
        initComponents();
        setResizable(false);
        setLocationRelativeTo(null);
        this.role=role;
        this.id = id;
        v_header.add("Mã hàng");
        v_header.add("Tên hàng");
        v_header.add("Tên hãng sản xuất");
        v_header.add("Số lượng tồn"); 
        v_header.add("Đơn giá nhập");
        v_header.add("Đơn giá bán");
        v_header.add("Ngày nhập");
        dtm.setDataVector(v_content, v_header);
        jTable1.setModel(dtm);
    }

    SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
    boolean isValidDate(String input) {
        df.setLenient(false); // set false để kiểm tra tính hợp lệ của date. VD: tháng 2 phải có 28-29 ngày, năm có 12 tháng,....
        try {
            df.parse(input); // parse dateString thành kiểu Date
        }
        catch (ParseException e) { // quăng lỗi nếu dateString ko hợp lệ
            return false;
        }
        return true;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        rbdt_ngayNhap = new javax.swing.JRadioButton();
        txt_ngayNhap = new javax.swing.JTextField();
        rdbt_tenhsx = new javax.swing.JRadioButton();
        txt_hsx = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        jLabel1.setText("SEARCH COMODITY");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(200, 200, 200))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 2, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Tìm kiếm chức năng");

        buttonGroup1.add(rbdt_ngayNhap);
        rbdt_ngayNhap.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        rbdt_ngayNhap.setForeground(new java.awt.Color(255, 255, 255));
        rbdt_ngayNhap.setSelected(true);
        rbdt_ngayNhap.setText("Import Date");
        rbdt_ngayNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbdt_ngayNhapActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdbt_tenhsx);
        rdbt_tenhsx.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        rdbt_tenhsx.setForeground(new java.awt.Color(255, 255, 255));
        rdbt_tenhsx.setText(" Manufacturer's name");
        rdbt_tenhsx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbt_tenhsxActionPerformed(evt);
            }
        });

        txt_hsx.setEnabled(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(rbdt_ngayNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(rdbt_tenhsx, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_hsx)
                    .addComponent(txt_ngayNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(68, 68, 68))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbdt_ngayNhap)
                    .addComponent(txt_ngayNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdbt_tenhsx)
                    .addComponent(txt_hsx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 689, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 264, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE))
        );

        jButton1.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Icon search.png"))); // NOI18N
        jButton1.setText("SEARCH");
        jButton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Exit icon.png"))); // NOI18N
        jButton2.setText("BACK");
        jButton2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
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
                .addGap(65, 65, 65)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68))
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rbdt_ngayNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbdt_ngayNhapActionPerformed
        // TODO add your handling code here:
        v_content.removeAllElements();
        dtm.setDataVector(v_content, v_header);
        txt_ngayNhap.setText("");
        txt_hsx.setText("");
        txt_hsx.setEnabled(false);
        txt_ngayNhap.setEnabled(true);
    }//GEN-LAST:event_rbdt_ngayNhapActionPerformed

    private void rdbt_tenhsxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbt_tenhsxActionPerformed
        // TODO add your handling code here:
        v_content.removeAllElements();
        dtm.setDataVector(v_content, v_header);
        txt_ngayNhap.setText("");
        txt_hsx.setText("");
        txt_hsx.setEnabled(true);
        txt_ngayNhap.setEnabled(false);
    }//GEN-LAST:event_rdbt_tenhsxActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        if (rbdt_ngayNhap.isSelected()) {
            v_content.removeAllElements();
            if (txt_ngayNhap.getText().equals("")) {
                int n = JOptionPane.showConfirmDialog(this, "Vui lòng nhập ngày !!!",
                        "Thông báo", JOptionPane.CLOSED_OPTION, 1);
            } else {
                if (isValidDate(txt_ngayNhap.getText())) {
                    Connection cn = DB_Connection.getCon();
                    String sql = String.format("exec sp_TimKiem_Hang_NgayNhap '%s'",
                            txt_ngayNhap.getText());
                    try {
                        PreparedStatement ps = (PreparedStatement) cn.prepareCall(sql);
                        ResultSet rs = ps.executeQuery();
                        while (rs.next()) {
                            Vector<String> v_record = new Vector<>();
                            v_record.add(rs.getString("MaHang"));
                            v_record.add(rs.getString("TenHang"));
                            v_record.add(rs.getString("TenHangSanXuat"));
                            v_record.add(String.valueOf(rs.getInt("SoLuongTon")));
                            v_record.add(String.valueOf(rs.getInt("DonGiaNhap")));
                            v_record.add(String.valueOf(rs.getInt("DonGiaBan")));
                            Date date = addDaysToJavaUtilDate(rs.getDate("NgayNhapHang"), 0);
                            v_record.add(df.format(date));
                            v_content.add(v_record);

                        }
                        cn.close();
                        if (v_content.isEmpty()) {
                            int n = JOptionPane.showConfirmDialog(this, "Không có mặt hàng !!!",
                                    "Thông báo", JOptionPane.CLOSED_OPTION, 1);
                        }
                        dtm.setDataVector(v_content, v_header);

                    } catch (Exception e) {
                        System.out.println("Error: " + e);
                    }
                } else {
                    int n = JOptionPane.showConfirmDialog(this, "Vui lòng nhập đúng ngày theo định dạng yyyy/mm/dd!!!",
                            "Thông báo", JOptionPane.CLOSED_OPTION, 1);
                }
            }

        } else if (rdbt_tenhsx.isSelected()) {
            v_content.removeAllElements();
            if (txt_hsx.getText().equals("")) {
                int n = JOptionPane.showConfirmDialog(this, "Vui lòng nhập tên hãng sản xuất!!!",
                        "Thông báo", JOptionPane.CLOSED_OPTION, 1);
            } else {
                Connection cn = DB_Connection.getCon();
                String sql = String.format("exec sp_TimKiem_Hang_TenHangSX N'%s'",
                        txt_hsx.getText());
                try {
                    PreparedStatement ps = (PreparedStatement) cn.prepareCall(sql);
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        Vector<String> v_record = new Vector<>();
                        v_record.add(rs.getString("MaHang"));
                        v_record.add(rs.getString("TenHang"));
                        v_record.add(rs.getString("TenHangSanXuat"));
                        v_record.add(String.valueOf(rs.getInt("SoLuongTon")));
                        v_record.add(String.valueOf(rs.getInt("DonGiaNhap")));
                        v_record.add(String.valueOf(rs.getInt("DonGiaBan")));
                        Date date = addDaysToJavaUtilDate(rs.getDate("NgayNhapHang"), 0);
                        v_record.add(df.format(date));
                        v_content.add(v_record);

                    }
                    cn.close();
                    if (v_content.isEmpty()) {
                        int n = JOptionPane.showConfirmDialog(this, "Không có hóa đơn !!!",
                                "Thông báo", JOptionPane.CLOSED_OPTION, 1);
                    }
                    dtm.setDataVector(v_content, v_header);

                } catch (Exception e) {
                }
            }

        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        TimKiem_user_main tk = new TimKiem_user_main(role,id);
        tk.show();
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(TimKiem_Hang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TimKiem_Hang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TimKiem_Hang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TimKiem_Hang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TimKiem_Hang(role,id).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JRadioButton rbdt_ngayNhap;
    private javax.swing.JRadioButton rdbt_tenhsx;
    private javax.swing.JTextField txt_hsx;
    private javax.swing.JTextField txt_ngayNhap;
    // End of variables declaration//GEN-END:variables
}
