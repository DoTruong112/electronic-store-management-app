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

public class TimKiem_HD extends javax.swing.JFrame {

    /**
     * Creates new form TimKiem_HD
     */
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

    public TimKiem_HD(String role,String id) {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        this.role=role;
        this.id =id;
         v_header.add("Mã hóa đơn");
        v_header.add("Mã nhân viên");
        v_header.add("Tên nhân viên");
        v_header.add("Mã khách ");
        v_header.add("Ngày bán");
        v_header.add("Tổng tiền");
        dtm.setDataVector(v_content, v_header);
        jTable2.setModel(dtm);
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
        rdbt_Ngay = new javax.swing.JRadioButton();
        txt_ngay1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_ngay2 = new javax.swing.JTextField();
        rdbt_tennv = new javax.swing.JRadioButton();
        txt_tennv = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("SEARCH BILLS");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(245, 245, 245)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 51));
        jLabel2.setText("Chức năng tìm kiếm");

        buttonGroup1.add(rdbt_Ngay);
        rdbt_Ngay.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        rdbt_Ngay.setSelected(true);
        rdbt_Ngay.setText("Start date");
        rdbt_Ngay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbt_NgayActionPerformed(evt);
            }
        });

        txt_ngay1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel3.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel3.setText("Last Date");

        txt_ngay2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        buttonGroup1.add(rdbt_tennv);
        rdbt_tennv.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        rdbt_tennv.setText("Name of Staff");
        rdbt_tennv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbt_tennvActionPerformed(evt);
            }
        });

        txt_tennv.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        txt_tennv.setEnabled(false);
        txt_tennv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tennvActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(rdbt_tennv)
                                .addGap(18, 18, 18)
                                .addComponent(txt_tennv, javax.swing.GroupLayout.DEFAULT_SIZE, 535, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(rdbt_Ngay)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_ngay1, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_ngay2, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(57, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdbt_Ngay)
                    .addComponent(txt_ngay1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txt_ngay2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdbt_tennv)
                    .addComponent(txt_tennv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 668, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 233, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE))
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
                .addGap(81, 81, 81)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 10, Short.MAX_VALUE))
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

    private void rdbt_NgayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbt_NgayActionPerformed
        // TODO add your handling code here:

        v_content.removeAllElements();
        dtm.setDataVector(v_content, v_header);
        txt_tennv.setEnabled(false);
        txt_ngay1.setEnabled(true);
        txt_ngay2.setEnabled(true);
        txt_ngay1.setText("");
        txt_ngay2.setText("");
        txt_tennv.setText("");
    }//GEN-LAST:event_rdbt_NgayActionPerformed

    private void rdbt_tennvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbt_tennvActionPerformed
        // TODO add your handling code here:
        v_content.removeAllElements();
        dtm.setDataVector(v_content, v_header);
        txt_ngay1.setEnabled(false);
        txt_ngay2.setEnabled(false);
        txt_tennv.setEnabled(true);
        txt_ngay1.setText("");
        txt_ngay2.setText("");
        txt_tennv.setText("");
    }//GEN-LAST:event_rdbt_tennvActionPerformed

    private void txt_tennvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tennvActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tennvActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        if (rdbt_Ngay.isSelected()) {
            v_content.removeAllElements();
            txt_tennv.setText("");
            if (txt_ngay1.getText().equals("") || txt_ngay2.getText().equals("")) {
                int n = JOptionPane.showConfirmDialog(this, "Vui lòng nhập  ngày !!!",
                        "Thông báo", JOptionPane.CLOSED_OPTION, 1);
            } else {

                if (isValidDate(txt_ngay1.getText()) && isValidDate(txt_ngay2.getText())) {
                    Connection cn = DB_Connection.getCon();
                    String sql = String.format("exec sp_TimKiem_HoaDon_TheoNgay '%s','%s'",
                            txt_ngay1.getText(), txt_ngay2.getText());
                    try {
                        PreparedStatement ps = (PreparedStatement) cn.prepareCall(sql);
                        ResultSet rs = ps.executeQuery();
                        while (rs.next()) {
                            Vector<String> v_record = new Vector<>();
                            v_record.add(rs.getString("MaHD"));
                            v_record.add(rs.getString("MaNV"));
                            v_record.add(rs.getString("TenNV"));
                            v_record.add(rs.getString("MaKhach"));
                            Date date = addDaysToJavaUtilDate(rs.getDate("NgayBan"), 0);
                            v_record.add(df.format(date));
                            v_record.add(String.valueOf(rs.getInt("TongTien")));
                            v_content.add(v_record);
                        }
                        if (v_content.isEmpty()) {
                            int n = JOptionPane.showConfirmDialog(this, "Không có hóa đơn !!!",
                                    "Thông báo", JOptionPane.CLOSED_OPTION, 1);
                        }
                        cn.close();
                        dtm.setDataVector(v_content, v_header);
                    } catch (Exception e) {
                    }
                } else {
                    int n = JOptionPane.showConfirmDialog(this, "Vui lòng nhập đúng ngày theo định dạng yyyy/mm/dd!!!",
                            "Thông báo", JOptionPane.CLOSED_OPTION, 1);
                }
            }

        } else if (rdbt_tennv.isSelected()) {
            v_content.removeAllElements();
            if (txt_tennv.getText().equals("")) {
                int n = JOptionPane.showConfirmDialog(this, "Vui lòng nhập tên nhân viên!!!",
                        "Thông báo", JOptionPane.CLOSED_OPTION, 1);
            } else {
                Connection cn = DB_Connection.getCon();
                String sql = String.format("exec sp_TimKiem_HoaDon_TenNV N'%s'",
                        txt_tennv.getText());
                try {

                    PreparedStatement ps = (PreparedStatement) cn.prepareCall(sql);
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        Vector<String> v_record = new Vector<>();
                        v_record.add(rs.getString("MaHD"));
                        v_record.add(rs.getString("MaNV"));
                        v_record.add(rs.getString("TenNV"));
                        v_record.add(rs.getString("MaKhach"));
                        Date date = addDaysToJavaUtilDate(rs.getDate("NgayBan"), 0);
                        v_record.add(df.format(date));
                        v_record.add(String.valueOf(rs.getInt("TongTien")));
                        v_content.add(v_record);

                    }
                    if (v_content.isEmpty()) {
                        int n = JOptionPane.showConfirmDialog(this, "Không có hóa đơn !!!",
                                "Thông báo", JOptionPane.CLOSED_OPTION, 1);
                    }
                    cn.close();
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
            java.util.logging.Logger.getLogger(TimKiem_HD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TimKiem_HD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TimKiem_HD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TimKiem_HD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TimKiem_HD(role,id).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable2;
    private javax.swing.JRadioButton rdbt_Ngay;
    private javax.swing.JRadioButton rdbt_tennv;
    private javax.swing.JTextField txt_ngay1;
    private javax.swing.JTextField txt_ngay2;
    private javax.swing.JTextField txt_tennv;
    // End of variables declaration//GEN-END:variables
}
