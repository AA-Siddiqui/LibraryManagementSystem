/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package acp.lab.project1.pages;
import acp.lab.project1.Book;
import acp.lab.project1.utils.ConnectionManager;
import acp.lab.project1.utils.DefinedColors;
import java.sql.*;
import java.util.LinkedList;
/**
 *
 * @author addan
 */

class LoggedInUser {
    public static int userID;
    public static int userType;
    public static String fullName;
    public static String phoneNo;
    public static String address;
    public static String username;
    public static String password;
    public static LinkedList<Book> booksBorrowed;
    public static LinkedList<Integer> cart;
    
    public static int borrowedBookCount;
    
    public static long ms3 = 259200000;
    public static long ms5 = 432000000;
    
    public static void reset() {
        userID = -1;
        userType = -1;
        fullName = new String("FullName");
        phoneNo = new String("0123456789123");
        address = new String("308 Negra Arrayo Lane, ABQ, NM, 87104");
        username = new String("user");
        password = new String("password");
        booksBorrowed = new LinkedList<>();
        cart = new LinkedList<>();
        
        borrowedBookCount = 0;
    }
    
    public static void update(int userID) {
        Connection conn = null;
        try {
            conn = ConnectionManager.getConnection();
        } catch(SQLException e) {
            e.printStackTrace();
            return;
        }
        
        try {
            ResultSet result = conn.createStatement().executeQuery("select UserId, UserType, FullName, PhoneNo, Address, Username, Password from UserDetails where UserId = " + Integer.valueOf(userID).toString());
            while (result.next()) {
                LoggedInUser.userType = result.getInt("UserType");
                LoggedInUser.userID = result.getInt("UserId");
                LoggedInUser.fullName = result.getString("FullName");
                LoggedInUser.phoneNo = result.getString("PhoneNo");
                LoggedInUser.address = result.getString("Address");
                LoggedInUser.username = result.getString("Username");
                LoggedInUser.password = result.getString("Password");
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        
        java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd");
        java.util.Date refDate = null;
        try {
            refDate = formatter.parse("1999-12-31");
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        
        LinkedList<Book> books = new LinkedList<>();
        try {
            ResultSet result = conn.createStatement().executeQuery("select * from Record where UserId = " + LoggedInUser.userID);
            while (result.next()) {
                Book temp = new Book();
                temp.ID = result.getInt("BookId");
                temp.borrowDate = result.getDate("BorrowDate");
                temp.returnDate = result.getDate("ReturnDate");
                temp.fine = result.getBigDecimal("Fine");
                books.add(temp);
                
                if (temp.returnDate.compareTo(refDate) == 0) LoggedInUser.borrowedBookCount++;
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        
        try {
            for (int i = 0; i < books.size(); i++) {
                ResultSet result = conn.createStatement().executeQuery("select ISBN, Title, Description, YearPublished, Rating, Pages, ThumbnailURL from Book where BookId = " + books.get(i).ID);
                while (result.next()) {
                    Book temp = books.get(i);
                    temp.ISBN = result.getString("ISBN");
                    temp.title = result.getString("Title");
                    temp.description = result.getString("Description");
                    temp.yearPublished = result.getInt("YearPublished");
                    temp.rating = result.getFloat("Rating");
                    temp.thumbnailURL = result.getString("ThumbnailURL");
                }
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        
        LoggedInUser.booksBorrowed = books;
    }
}

public class UserManagementPage extends javax.swing.JFrame {

    /**
     * Creates new form LoginForm
     */
    public UserManagementPage(String uid) {
        LoggedInUser.update(Integer.parseInt(uid));
        initComponents();
        postInit();
    }
    
    private void postInit() {
        for (int i = 0; i < LoggedInUser.booksBorrowed.size(); i++) {
            java.awt.GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = i;
            jPanel8.add(new acp.lab.project1.utils.TransactionHistoryRow(LoggedInUser.booksBorrowed.get(i).title, LoggedInUser.booksBorrowed.get(i).borrowDate, LoggedInUser.booksBorrowed.get(i).returnDate, Integer.valueOf(LoggedInUser.booksBorrowed.get(i).ID).toString()), gridBagConstraints);
        }
        jLabel1.setText(LoggedInUser.username);
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

        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jPanel8 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(33, 37, 41));
        setResizable(false);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setBackground(new java.awt.Color(33, 37, 41));
        jPanel1.setMaximumSize(new java.awt.Dimension(1280, 720));
        jPanel1.setMinimumSize(new java.awt.Dimension(1280, 720));
        jPanel1.setPreferredSize(new java.awt.Dimension(1280, 720));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel4.setBackground(new java.awt.Color(33, 37, 41));
        jPanel4.setMaximumSize(new java.awt.Dimension(1280, 120));
        jPanel4.setMinimumSize(new java.awt.Dimension(1280, 120));
        jPanel4.setPreferredSize(new java.awt.Dimension(1280, 120));
        jPanel4.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Roboto Condensed", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Transaction History");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        jPanel4.add(jLabel1, gridBagConstraints);

        jButton1.setBackground(new java.awt.Color(52, 58, 64));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(0, 50, 0, 0);
        jPanel4.add(jButton1, gridBagConstraints);

        jCheckBox1.setFont(new java.awt.Font("Roboto Condensed", 1, 12)); // NOI18N
        jCheckBox1.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox1.setText("Only show returnable books");
        jCheckBox1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCheckBox1MouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        jPanel4.add(jCheckBox1, gridBagConstraints);

        jCheckBox2.setFont(new java.awt.Font("Roboto Condensed", 1, 12)); // NOI18N
        jCheckBox2.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox2.setText("Only show unrated books");
        jCheckBox2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCheckBox2MouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        jPanel4.add(jCheckBox2, gridBagConstraints);

        jPanel1.add(jPanel4, new java.awt.GridBagConstraints());

        jPanel5.setBackground(new java.awt.Color(33, 37, 41));
        jPanel5.setMaximumSize(new java.awt.Dimension(1280, 600));
        jPanel5.setMinimumSize(new java.awt.Dimension(1280, 600));
        jPanel5.setPreferredSize(new java.awt.Dimension(1280, 600));
        jPanel5.setLayout(new java.awt.GridBagLayout());

        jScrollPane4.setBackground(new java.awt.Color(73, 80, 87));
        jScrollPane4.setMinimumSize(new java.awt.Dimension(1280, 600));
        jScrollPane4.setPreferredSize(new java.awt.Dimension(1280, 602));

        jPanel8.setBackground(new java.awt.Color(73, 80, 87));
        jPanel8.setLayout(new java.awt.GridBagLayout());
        jScrollPane4.setViewportView(jPanel8);

        jPanel5.add(jScrollPane4, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        jPanel1.add(jPanel5, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        getContentPane().add(jPanel1, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        AdminManagementPage.main(new String[]{});
        setVisible(false);
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jCheckBox2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBox2MouseClicked
        // TODO add your handling code here:
        jPanel8.removeAll();
        
        Connection conn = null;
        try {
            conn = ConnectionManager.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        if (conn == null) return;
        java.util.LinkedList<Integer> bid = new java.util.LinkedList<>();
        try {
            ResultSet rs = conn.createStatement().executeQuery("select BookId as c from Rating where UserId = " + LoggedInUser.userID + " and Rating > 0");
            while (rs.next()) {
                bid.add(rs.getInt("c"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        
        int skip = 0;
        boolean cont = false;
        for (int i = 0; i < LoggedInUser.booksBorrowed.size(); i++) {
            cont = false;
            for (int j = 0; j < bid.size(); j++) {
                if (LoggedInUser.booksBorrowed.get(i).ID == bid.get(j).intValue() && jCheckBox2.isSelected()) {
                    cont = true;
                    skip++;
                    break;
                }
            }
            if (cont) continue;
            java.awt.GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = i - skip;
            jPanel8.add(new acp.lab.project1.utils.TransactionHistoryRow(LoggedInUser.booksBorrowed.get(i).title, LoggedInUser.booksBorrowed.get(i).borrowDate, LoggedInUser.booksBorrowed.get(i).returnDate, Integer.valueOf(LoggedInUser.booksBorrowed.get(i).ID).toString()), gridBagConstraints);
        }
        javax.swing.SwingUtilities.updateComponentTreeUI(this);
    }//GEN-LAST:event_jCheckBox2MouseClicked

    private void jCheckBox1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBox1MouseClicked
        // TODO add your handling code here:
        jPanel8.removeAll();
        java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd");
        java.util.Date refDate = null;
        try {
            refDate = formatter.parse("1999-12-31");
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
    
        int skip = 0;
        for (int i = 0; i < LoggedInUser.booksBorrowed.size(); i++) {
            if (LoggedInUser.booksBorrowed.get(i).returnDate.compareTo(refDate) != 0 && jCheckBox1.isSelected()) {
                skip++;
                continue;
            }
            java.awt.GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = i - skip;
            jPanel8.add(new acp.lab.project1.utils.TransactionHistoryRow(LoggedInUser.booksBorrowed.get(i).title, LoggedInUser.booksBorrowed.get(i).borrowDate, LoggedInUser.booksBorrowed.get(i).returnDate, Integer.valueOf(LoggedInUser.booksBorrowed.get(i).ID).toString()), gridBagConstraints);
        }
        javax.swing.SwingUtilities.updateComponentTreeUI(this);
    }//GEN-LAST:event_jCheckBox1MouseClicked

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
            java.util.logging.Logger.getLogger(UserManagementPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserManagementPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserManagementPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserManagementPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserManagementPage(args[0]).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane4;
    // End of variables declaration//GEN-END:variables
}
