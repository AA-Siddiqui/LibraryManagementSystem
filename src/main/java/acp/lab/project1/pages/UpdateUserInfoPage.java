/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package acp.lab.project1.pages;
import acp.lab.project1.utils.ConnectionManager;
import acp.lab.project1.utils.DefinedColors;
import java.sql.*;
/**
 *
 * @author addan
 */
public class UpdateUserInfoPage extends javax.swing.JFrame {
    private String userID;
    private String username;
    private String name;
    private String phone;
    private String address;
    /**
     * Creates new form AccountPage
     */
    public UpdateUserInfoPage(String uid) {
        userID = uid;
        preInit(uid);
        initComponents();
    }
    
    private void preInit(String uid) {
        Connection con = null;
        try {
            con = ConnectionManager.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (con == null) return;
        
        try {
            ResultSet rs = con.createStatement().executeQuery("select FullName, PhoneNo, Address, Username from UserDetails where UserId = " + uid);
            while (rs.next()) {
                name = rs.getString("FullName");
                phone = rs.getString("PhoneNo");
                address = rs.getString("Address");
                username = rs.getString("Username");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        nameField = new javax.swing.JTextField();
        phoneField = new javax.swing.JTextField();
        addressField = new javax.swing.JTextField();
        usernameField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel2.setBackground(DefinedColors.backgroundPrimaryColor);
        jPanel2.setMaximumSize(new java.awt.Dimension(1280, 720));
        jPanel2.setMinimumSize(new java.awt.Dimension(1280, 720));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jPanel1.setBackground(DefinedColors.backgroundLightColor);
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(DefinedColors.whiteColor));
        jPanel1.setMaximumSize(new java.awt.Dimension(640, 480));
        jPanel1.setMinimumSize(new java.awt.Dimension(640, 480));
        jPanel1.setPreferredSize(new java.awt.Dimension(640, 480));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel4.setBackground(DefinedColors.backgroundLightColor);
        jPanel4.setLayout(new java.awt.GridBagLayout());

        nameField.setFont(new java.awt.Font("Roboto Condensed", 0, 18)); // NOI18N
        nameField.setText(name);
        nameField.setPreferredSize(new java.awt.Dimension(400, 28));
        nameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameFieldActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 15, 15);
        jPanel4.add(nameField, gridBagConstraints);

        phoneField.setFont(new java.awt.Font("Roboto Condensed", 0, 18)); // NOI18N
        phoneField.setText(phone);
        phoneField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phoneFieldActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 15, 15);
        jPanel4.add(phoneField, gridBagConstraints);

        addressField.setFont(new java.awt.Font("Roboto Condensed", 0, 18)); // NOI18N
        addressField.setText(address);
        addressField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addressFieldActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 15, 15);
        jPanel4.add(addressField, gridBagConstraints);

        usernameField.setFont(new java.awt.Font("Roboto Condensed", 0, 18)); // NOI18N
        usernameField.setText(username);
        usernameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameFieldActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 15, 15);
        jPanel4.add(usernameField, gridBagConstraints);

        jLabel1.setFont(new java.awt.Font("Roboto Condensed", 0, 22)); // NOI18N
        jLabel1.setForeground(DefinedColors.whiteColor);
        jLabel1.setText("Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 15, 15);
        jPanel4.add(jLabel1, gridBagConstraints);

        jLabel5.setFont(new java.awt.Font("Roboto Condensed", 0, 22)); // NOI18N
        jLabel5.setForeground(DefinedColors.whiteColor);
        jLabel5.setText("Phone");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 15, 15);
        jPanel4.add(jLabel5, gridBagConstraints);

        jLabel7.setFont(new java.awt.Font("Roboto Condensed", 0, 22)); // NOI18N
        jLabel7.setForeground(DefinedColors.whiteColor);
        jLabel7.setText("Address");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 15, 15);
        jPanel4.add(jLabel7, gridBagConstraints);

        jLabel8.setFont(new java.awt.Font("Roboto Condensed", 0, 22)); // NOI18N
        jLabel8.setForeground(DefinedColors.whiteColor);
        jLabel8.setText("Username");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 15, 15);
        jPanel4.add(jLabel8, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Roboto Condensed", 1, 24)); // NOI18N
        jLabel2.setForeground(DefinedColors.whiteColor);
        jLabel2.setText("User info");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
        jPanel4.add(jLabel2, gridBagConstraints);

        jPanel1.add(jPanel4, new java.awt.GridBagConstraints());

        jPanel3.setBackground(DefinedColors.backgroundLightColor);
        jPanel3.setLayout(new java.awt.GridBagLayout());

        jButton4.setBackground(DefinedColors.backgroundSecondaryColor);
        jButton4.setFont(new java.awt.Font("Roboto Condensed", 0, 14)); // NOI18N
        jButton4.setForeground(DefinedColors.whiteColor);
        jButton4.setText("Update");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 15, 15);
        jPanel3.add(jButton4, gridBagConstraints);

        jButton5.setBackground(DefinedColors.backgroundSecondaryColor);
        jButton5.setFont(new java.awt.Font("Roboto Condensed", 0, 14)); // NOI18N
        jButton5.setForeground(DefinedColors.whiteColor);
        jButton5.setText("Back");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 15, 15);
        jPanel3.add(jButton5, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        jPanel1.add(jPanel3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 25;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(246, 544, 247, 545);
        jPanel2.add(jPanel1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        getContentPane().add(jPanel2, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameFieldActionPerformed

    private void phoneFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phoneFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_phoneFieldActionPerformed

    private void addressFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addressFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addressFieldActionPerformed

    private void usernameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameFieldActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        Connection con = null;
        try {
            con = ConnectionManager.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (con == null) return;
        
        if (!nameField.getText().equals(name)) {
            try {
                int updated = con.createStatement().executeUpdate("update UserDetails set FullName = \'" + nameField.getText() + "\' where UserId = " + userID);
                System.out.println(Integer.valueOf(updated).toString() + " rows updated!");
                name = nameField.getText();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        if (!addressField.getText().equals(address)) {
            try {
                int updated = con.createStatement().executeUpdate("update UserDetails set FullName = \'" + addressField.getText() + "\' where UserId = " + userID);
                System.out.println(Integer.valueOf(updated).toString() + " rows updated!");
                address = addressField.getText();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        if (!usernameField.getText().equals(username)) {
            boolean uniqueUsername = true;
            if (usernameField.getText().length() != usernameField.getText().replace(" ", "").length()) {
                javax.swing.JOptionPane.showMessageDialog(this, "Username can't have spaces!", 
                                       "ERROR", javax.swing.JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                uniqueUsername = !con.createStatement().executeQuery("select Username from UserDetails where Username = \'" + usernameField.getText() + "\'").next();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (!uniqueUsername) {
                javax.swing.JOptionPane.showMessageDialog(this, "Username already in use!", 
                                       "ERROR", javax.swing.JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            try {
                int updated = con.createStatement().executeUpdate("update UserDetails set Username = \'" + usernameField.getText() + "\' where UserId = " + userID);
                System.out.println(Integer.valueOf(updated).toString() + " rows updated!");
                username = usernameField.getText();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        if (!phoneField.getText().equals(phone)) {
            boolean uniquePhone = true;
            
            for (char c: phoneField.getText().toCharArray()) {
                if ((c < '0' || c > '9')) {
                    javax.swing.JOptionPane.showMessageDialog(this, "Phone No should include numbers only", 
                                       "ERROR", javax.swing.JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            
            try {
                uniquePhone = !con.createStatement().executeQuery("select PhoneNo from UserDetails where PhoneNo = \'" + phoneField.getText() + "\'").next();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (!uniquePhone) {
                javax.swing.JOptionPane.showMessageDialog(this, "Phone No already in use!", 
                                       "ERROR", javax.swing.JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            try {
                int updated = con.createStatement().executeUpdate("update UserDetails set PhoneNo = \'" + phoneField.getText() + "\' where UserId = " + userID);
                System.out.println(Integer.valueOf(updated).toString() + " rows updated!");
                phone = phoneField.getText();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        AdminManagementPage.main(new String[]{});
        setVisible(false);
        dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

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
            java.util.logging.Logger.getLogger(UpdateUserInfoPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UpdateUserInfoPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UpdateUserInfoPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UpdateUserInfoPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UpdateUserInfoPage(args[0]).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField addressField;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField nameField;
    private javax.swing.JTextField phoneField;
    private javax.swing.JTextField usernameField;
    // End of variables declaration//GEN-END:variables
}
