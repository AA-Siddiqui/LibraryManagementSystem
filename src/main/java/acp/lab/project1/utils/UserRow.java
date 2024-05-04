package acp.lab.project1.utils;

import java.sql.*;
import acp.lab.project1.utils.DefinedColors;

public class UserRow extends javax.swing.JPanel {
  private javax.swing.JLabel nameLabel;
  private javax.swing.JLabel chargesLabel;
  private javax.swing.JLabel booksDueLabel;
  private javax.swing.JButton editButton;
  private javax.swing.JButton removeButton;
  private javax.swing.JPanel self;
  
  public UserRow(String uid, String username, String fine) {
    self = this;
    nameLabel = new javax.swing.JLabel();
    chargesLabel = new javax.swing.JLabel();
    booksDueLabel = new javax.swing.JLabel();
    editButton = new javax.swing.JButton();
    removeButton = new javax.swing.JButton();

    setBackground(DefinedColors.backgroundSecondaryColor);
    setBorder(javax.swing.BorderFactory.createLineBorder(DefinedColors.whiteColor));
    setForeground(DefinedColors.whiteColor);
    setPreferredSize(new java.awt.Dimension(975, 75));
    java.awt.GridBagLayout jPanel6Layout = new java.awt.GridBagLayout();
//    jPanel6Layout.columnWidths = new int[] {243, 243, 244, 245};
    jPanel6Layout.columnWidths = new int[] {195, 195, 195, 195, 195};
    setLayout(jPanel6Layout);
    
    nameLabel.setFont(new java.awt.Font("Roboto Condensed", 1, 14)); // NOI18N
    nameLabel.setForeground(DefinedColors.whiteColor);
    nameLabel.setText(username);
    add(nameLabel, new java.awt.GridBagConstraints());
    
    int books = -1;
    Connection con = null;
    try {
        con = ConnectionManager.getConnection();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    if (con == null) return;

    try {
        ResultSet rs = con.createStatement().executeQuery("select count(RecordId) as bc from Record where ReturnDate = \'1999-12-31\' and UserId = " + uid);
        while (rs.next()) {
            books = rs.getInt("bc");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    booksDueLabel.setFont(new java.awt.Font("Roboto Condensed", 1, 14)); // NOI18N
    booksDueLabel.setForeground(DefinedColors.whiteColor);
    booksDueLabel.setText(books + " books to return");
    add(booksDueLabel, new java.awt.GridBagConstraints());
    
    chargesLabel.setFont(new java.awt.Font("Roboto Condensed", 1, 14)); // NOI18N
    chargesLabel.setForeground(DefinedColors.whiteColor);
    chargesLabel.setText("$" + fine + " Due");
    add(chargesLabel, new java.awt.GridBagConstraints());
    
    editButton.setBackground(DefinedColors.backgroundPrimaryColor);
    editButton.setFont(new java.awt.Font("Roboto Condensed", 0, 14)); // NOI18N
    editButton.setForeground(DefinedColors.whiteColor);
    editButton.setText("Edit");
    editButton.setForeground(DefinedColors.whiteColor);
    editButton.setMaximumSize(new java.awt.Dimension(100, 25));
    editButton.setMinimumSize(new java.awt.Dimension(100, 25));
    editButton.setPreferredSize(new java.awt.Dimension(100, 25));
    editButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            acp.lab.project1.pages.UpdateUserInfoPage.main(new String[]{uid});
            javax.swing.SwingUtilities.getWindowAncestor(self).setVisible(false);
            javax.swing.SwingUtilities.getWindowAncestor(self).dispose();
        }
    });
    add(editButton, new java.awt.GridBagConstraints());
    
    removeButton.setBackground(DefinedColors.backgroundPrimaryColor);
    removeButton.setFont(new java.awt.Font("Roboto Condensed", 0, 14)); // NOI18N
    removeButton.setForeground(DefinedColors.whiteColor);
    removeButton.setText("Remove");
    removeButton.setForeground(DefinedColors.whiteColor);
    removeButton.setMaximumSize(new java.awt.Dimension(100, 25));
    removeButton.setMinimumSize(new java.awt.Dimension(100, 25));
    removeButton.setPreferredSize(new java.awt.Dimension(100, 25));
    removeButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            Connection con = null;
            try {
                con = ConnectionManager.getConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (con == null) return;

            try {
                int rs = con.createStatement().executeUpdate("delete from UserDetails where UserId = " + uid);
                System.out.println(rs + " rows deleted!");
                javax.swing.JOptionPane.showMessageDialog(javax.swing.SwingUtilities.getWindowAncestor(self), "User Removed! Refresh to see.", 
                                   "Info", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            javax.swing.SwingUtilities.updateComponentTreeUI(javax.swing.SwingUtilities.getWindowAncestor(self));
        }
    });
    add(removeButton, new java.awt.GridBagConstraints());
    
    addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            acp.lab.project1.pages.UserManagementPage.main(new String[]{uid});
            javax.swing.SwingUtilities.getWindowAncestor(self).setVisible(false);
            javax.swing.SwingUtilities.getWindowAncestor(self).dispose();
        }
    });
  }

  public void setName(String name) {
    nameLabel.setText(name);
  }

  public void setCharges(double charges) {
    chargesLabel.setText("$" + Double.toString(charges) + " Due");
  }
}