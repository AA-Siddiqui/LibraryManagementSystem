/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package acp.lab.project1.pages;
import acp.lab.project1.LoggedInUser;
import acp.lab.project1.utils.ConnectionManager;
import acp.lab.project1.utils.DefinedColors;
import java.sql.*;
import java.math.BigDecimal;
/**
 *
 * @author addan
 */
public class DashboardPage extends javax.swing.JFrame {
    private String earliestBookToReturn;
    private String daysLeftToEarliestReturnDate;
    private String totalBooksToReturn;
    private String totalDue;
    private String booksLeftToRate;
    /**
     * Creates new form DashboardPage
     */
    public DashboardPage() {
        preInit();
        initComponents();
        java.awt.event.MouseAdapter redirect = new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                UserTransactionHistory.main(new String[]{});
            }
        };
        jPanel12.addMouseListener(redirect);
        jPanel13.addMouseListener(redirect);
        jPanel14.addMouseListener(redirect);
        jPanel15.addMouseListener(redirect);
        
        postInit();
    }
    
    private void postInit() {
        Connection con = null;
        try {
            con = ConnectionManager.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (con == null) return;
        
        java.util.LinkedList<Integer> toDel = new java.util.LinkedList<>();
        try {
            ResultSet rs = con.createStatement().executeQuery("select NotificationId, RequestType, Approval from Notifications where UserId = " + LoggedInUser.userID);
            while (rs.next()) {
                toDel.add(rs.getInt("NotificationId"));
                javax.swing.JOptionPane.showMessageDialog(this, notificationGenerator(rs.getInt("RequestType"), rs.getBoolean("Approval")), 
                                   "Info", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        for (Integer del: toDel) {
            try {
                int rs = con.createStatement().executeUpdate("delete Notifications where NotificationId = " + del.intValue());
                System.out.println(rs + " notifications shown!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private String notificationGenerator(int reqType, boolean approval) {
        String itemType = "";
        switch (reqType) {
            case 1:
                itemType = "upgrade.";
                break;
            
            case 2:
                itemType = "book return.";
                break;
            
            case 3:
                itemType = "fine payment.";
                break;
        }
        return "Admin has " + (approval ? "approved" : "denied") + " your request for " + itemType;
    }
    
    private void preInit() {
        earliestBookToReturn = "";
        daysLeftToEarliestReturnDate = "0";
        totalBooksToReturn = "0";
        totalDue = "0.00";
        booksLeftToRate = "0";
        
        Connection con = null;

        int ratingCount = 0;
        try {
            con = ConnectionManager.getConnection();
            ResultSet rs = con.createStatement().executeQuery("select count(RatingId) as c from Rating where UserId = " + LoggedInUser.userID + " and Rating = 0");
            while (rs.next()) {
                ratingCount = rs.getInt("c");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (con == null) return;

        java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd");
        java.util.Date refDate = null;
        java.util.Date maxDate = null;
        try {
            refDate = formatter.parse("1999-12-31");
            maxDate = formatter.parse("2037-12-31");
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        
        int index = -1;
        int returnCount = 0;
        BigDecimal fine = new BigDecimal(0);
        for (int i = 0; i < LoggedInUser.booksBorrowed.size(); i++) {
            if (LoggedInUser.booksBorrowed.get(i).returnDate.compareTo(refDate) == 0) {
                returnCount++;
                if (LoggedInUser.booksBorrowed.get(i).borrowDate.before(maxDate)) {
                    maxDate = LoggedInUser.booksBorrowed.get(i).borrowDate;
                    index = i;
                }
            }
            fine.add(LoggedInUser.booksBorrowed.get(i).fine);
            
        }
        try {
            con = ConnectionManager.getConnection();
            ResultSet rs = con.createStatement().executeQuery("select sum(fine) as c from Record where UserId = " + LoggedInUser.userID);
            while (rs.next()) {
                fine = rs.getBigDecimal("c");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (fine.compareTo(new BigDecimal(0)) == 1) {
            totalDue = fine.toString();
        }
        totalBooksToReturn = Integer.valueOf(returnCount).toString();
        booksLeftToRate = Integer.valueOf(ratingCount).toString();
        earliestBookToReturn = LoggedInUser.booksBorrowed.get(index).title;
        daysLeftToEarliestReturnDate = Long.valueOf(((LoggedInUser.booksBorrowed.get(index).borrowDate.getTime() + (LoggedInUser.userType == 1 ? (3 * 24 * 60 * 60 * 1000) : (5 * 24 * 60 * 60 * 1000))) - new java.util.Date().getTime()) / 86400000).toString();
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
        jPanel3 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        earliestBookToReturnLabel = new javax.swing.JLabel();
        daysLeftToEarliestReturnDateLabel = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        totalBooksToReturnLabel = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        totalDueLabel = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        booksLeftToRateLabel = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setBackground(new java.awt.Color(33, 37, 41));
        jPanel1.setMaximumSize(new java.awt.Dimension(1280, 720));
        jPanel1.setMinimumSize(new java.awt.Dimension(1280, 720));
        jPanel1.setPreferredSize(new java.awt.Dimension(1280, 720));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel3.setBackground(new java.awt.Color(33, 37, 41));
        jPanel3.setMaximumSize(new java.awt.Dimension(1280, 360));
        jPanel3.setMinimumSize(new java.awt.Dimension(1280, 360));
        jPanel3.setPreferredSize(new java.awt.Dimension(1280, 360));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        jPanel9.setBackground(new java.awt.Color(33, 37, 41));
        jPanel9.setMaximumSize(new java.awt.Dimension(640, 360));
        jPanel9.setMinimumSize(new java.awt.Dimension(640, 360));
        jPanel9.setPreferredSize(new java.awt.Dimension(640, 360));
        jPanel9.setLayout(new java.awt.GridBagLayout());

        jPanel11.setBackground(new java.awt.Color(33, 37, 41));
        jPanel11.setMaximumSize(new java.awt.Dimension(640, 360));
        jPanel11.setMinimumSize(new java.awt.Dimension(640, 360));
        jPanel11.setPreferredSize(new java.awt.Dimension(640, 360));
        java.awt.GridBagLayout jPanel11Layout = new java.awt.GridBagLayout();
        jPanel11Layout.columnWidths = new int[] {132, 133};
        jPanel11.setLayout(jPanel11Layout);

        jLabel1.setFont(new java.awt.Font("Roboto Condensed", 1, 32)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Welcome " + LoggedInUser.fullName + ",");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel11.add(jLabel1, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Roboto Condensed", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Welcome to the Library Management System!");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        jPanel11.add(jLabel2, gridBagConstraints);

        jButton1.setBackground(new java.awt.Color(52, 58, 64));
        jButton1.setFont(new java.awt.Font("Roboto Condensed", 0, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Search for your favourite books");
        jButton1.setPreferredSize(new java.awt.Dimension(205, 28));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(50, 0, 0, 0);
        jPanel11.add(jButton1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 50);
        jPanel9.add(jPanel11, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        jPanel3.add(jPanel9, gridBagConstraints);

        jPanel10.setBackground(new java.awt.Color(33, 37, 41));
        jPanel10.setMaximumSize(new java.awt.Dimension(640, 360));
        jPanel10.setMinimumSize(new java.awt.Dimension(640, 360));
        jPanel10.setPreferredSize(new java.awt.Dimension(640, 360));
        jPanel10.setLayout(new java.awt.BorderLayout());

        jLabel3.setFont(new java.awt.Font("Roboto Condensed", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("<html>\n<div style=\"top:50%; left:50%; transform:translate(-50%,-50%);\">\nWelcome to your personalized library hub!<br>Explore our vast collection, conveniently renew your borrowed items,<br>and keep track of upcoming due dates – all from here.\n</div>\n</html>");
        jPanel10.add(jLabel3, java.awt.BorderLayout.CENTER);

        jPanel16.setBackground(new java.awt.Color(33, 37, 41));
        jPanel16.setPreferredSize(new java.awt.Dimension(640, 45));
        jPanel16.setLayout(new java.awt.GridBagLayout());

        jPanel18.setBackground(new java.awt.Color(33, 37, 41));
        jPanel18.setLayout(new java.awt.GridBagLayout());

        jLabel17.setFont(new java.awt.Font("Roboto Condensed", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText(LoggedInUser.fullName);
        jLabel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel17MouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        jPanel18.add(jLabel17, gridBagConstraints);

        jLabel18.setFont(new java.awt.Font("Roboto Condensed", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText(LoggedInUser.userType == 1 ? "Upgrade" : "");
        jLabel18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel18MouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel18.add(jLabel18, gridBagConstraints);

        jLabel5.setFont(new java.awt.Font("Roboto Condensed", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Logout");
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        jPanel18.add(jLabel5, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(0, 250, 0, 0);
        jPanel16.add(jPanel18, gridBagConstraints);

        jPanel10.add(jPanel16, java.awt.BorderLayout.PAGE_START);

        jPanel17.setBackground(new java.awt.Color(33, 37, 41));
        jPanel17.setPreferredSize(new java.awt.Dimension(75, 315));

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 75, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 315, Short.MAX_VALUE)
        );

        jPanel10.add(jPanel17, java.awt.BorderLayout.WEST);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHEAST;
        jPanel3.add(jPanel10, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(jPanel3, gridBagConstraints);

        jPanel2.setBackground(new java.awt.Color(52, 58, 64));
        jPanel2.setMaximumSize(new java.awt.Dimension(1280, 360));
        jPanel2.setMinimumSize(new java.awt.Dimension(1280, 360));
        jPanel2.setPreferredSize(new java.awt.Dimension(1280, 360));
        jPanel2.setRequestFocusEnabled(false);
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jPanel8.setBackground(new java.awt.Color(52, 58, 64));
        jPanel8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 35, 0));

        jPanel4.setBackground(new java.awt.Color(188, 202, 208));
        jPanel4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel4.setMaximumSize(new java.awt.Dimension(280, 280));
        jPanel4.setMinimumSize(new java.awt.Dimension(280, 280));
        jPanel4.setLayout(new java.awt.GridBagLayout());

        jPanel12.setBackground(new java.awt.Color(188, 202, 208));
        jPanel12.setMaximumSize(new java.awt.Dimension(280, 280));
        jPanel12.setMinimumSize(new java.awt.Dimension(280, 280));
        jPanel12.setPreferredSize(new java.awt.Dimension(280, 280));
        jPanel12.setLayout(new java.awt.GridBagLayout());

        jLabel13.setFont(new java.awt.Font("Roboto Condensed", 0, 24)); // NOI18N
        jLabel13.setText("You have");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel12.add(jLabel13, gridBagConstraints);

        earliestBookToReturnLabel.setFont(new java.awt.Font("Roboto Condensed", 1, 24)); // NOI18N
        earliestBookToReturnLabel.setText(earliestBookToReturn);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        jPanel12.add(earliestBookToReturnLabel, gridBagConstraints);

        daysLeftToEarliestReturnDateLabel.setFont(new java.awt.Font("Roboto Condensed", 1, 24)); // NOI18N
        daysLeftToEarliestReturnDateLabel.setText(daysLeftToEarliestReturnDate + " Days");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        jPanel12.add(daysLeftToEarliestReturnDateLabel, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Roboto Condensed", 0, 24)); // NOI18N
        jLabel4.setText("to return");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        jPanel12.add(jLabel4, gridBagConstraints);

        jPanel4.add(jPanel12, new java.awt.GridBagConstraints());

        jPanel8.add(jPanel4);

        jPanel5.setBackground(new java.awt.Color(188, 202, 208));
        jPanel5.setMaximumSize(new java.awt.Dimension(280, 280));
        jPanel5.setMinimumSize(new java.awt.Dimension(280, 280));
        jPanel5.setLayout(new java.awt.GridBagLayout());

        jPanel13.setBackground(new java.awt.Color(188, 202, 208));
        jPanel13.setMaximumSize(new java.awt.Dimension(280, 280));
        jPanel13.setMinimumSize(new java.awt.Dimension(280, 280));
        jPanel13.setPreferredSize(new java.awt.Dimension(280, 280));
        jPanel13.setLayout(new java.awt.GridBagLayout());

        jLabel7.setFont(new java.awt.Font("Roboto Condensed", 0, 24)); // NOI18N
        jLabel7.setText("You have");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel13.add(jLabel7, gridBagConstraints);

        totalBooksToReturnLabel.setFont(new java.awt.Font("Roboto Condensed", 1, 24)); // NOI18N
        totalBooksToReturnLabel.setText(totalBooksToReturn + " Books");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        jPanel13.add(totalBooksToReturnLabel, gridBagConstraints);

        jLabel9.setFont(new java.awt.Font("Roboto Condensed", 0, 24)); // NOI18N
        jLabel9.setText("to return");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        jPanel13.add(jLabel9, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel5.add(jPanel13, gridBagConstraints);

        jPanel8.add(jPanel5);

        jPanel6.setBackground(new java.awt.Color(188, 202, 208));
        jPanel6.setMaximumSize(new java.awt.Dimension(280, 280));
        jPanel6.setMinimumSize(new java.awt.Dimension(280, 280));

        jPanel14.setBackground(new java.awt.Color(188, 202, 208));
        jPanel14.setMaximumSize(new java.awt.Dimension(280, 280));
        jPanel14.setMinimumSize(new java.awt.Dimension(280, 280));
        jPanel14.setPreferredSize(new java.awt.Dimension(280, 280));
        jPanel14.setLayout(new java.awt.GridBagLayout());

        jLabel14.setFont(new java.awt.Font("Roboto Condensed", 0, 24)); // NOI18N
        jLabel14.setText("You have");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel14.add(jLabel14, gridBagConstraints);

        totalDueLabel.setFont(new java.awt.Font("Roboto Condensed", 1, 24)); // NOI18N
        totalDueLabel.setText("$" + totalDue);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        jPanel14.add(totalDueLabel, gridBagConstraints);

        jLabel12.setFont(new java.awt.Font("Roboto Condensed", 0, 24)); // NOI18N
        jLabel12.setText("left to pay");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        jPanel14.add(jLabel12, gridBagConstraints);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 280, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 280, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel8.add(jPanel6);

        jPanel7.setBackground(new java.awt.Color(188, 202, 208));
        jPanel7.setMaximumSize(new java.awt.Dimension(280, 280));
        jPanel7.setMinimumSize(new java.awt.Dimension(280, 280));

        jPanel15.setBackground(new java.awt.Color(188, 202, 208));
        jPanel15.setMaximumSize(new java.awt.Dimension(280, 280));
        jPanel15.setMinimumSize(new java.awt.Dimension(280, 280));
        jPanel15.setPreferredSize(new java.awt.Dimension(280, 280));
        jPanel15.setLayout(new java.awt.GridBagLayout());

        jLabel15.setFont(new java.awt.Font("Roboto Condensed", 0, 24)); // NOI18N
        jLabel15.setText("You have");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel15.add(jLabel15, gridBagConstraints);

        booksLeftToRateLabel.setFont(new java.awt.Font("Roboto Condensed", 1, 24)); // NOI18N
        booksLeftToRateLabel.setText(booksLeftToRate + " Books");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        jPanel15.add(booksLeftToRateLabel, gridBagConstraints);

        jLabel16.setFont(new java.awt.Font("Roboto Condensed", 0, 24)); // NOI18N
        jLabel16.setText("left to rate");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        jPanel15.add(jLabel16, gridBagConstraints);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 280, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 280, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel8.add(jPanel7);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel2.add(jPanel8, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel1.add(jPanel2, gridBagConstraints);

        getContentPane().add(jPanel1, new java.awt.GridBagConstraints());

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        SearchPage.main(new String[]{});
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jLabel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseClicked
        // TODO add your handling code here:
        UpdateAccountPage.main(new String[]{});
        setVisible(false);
        dispose();
    }//GEN-LAST:event_jLabel17MouseClicked

    private void jLabel18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseClicked
        // TODO add your handling code here:
        if (LoggedInUser.userType == 2) return;
        UpgradePage.main(new String[]{});
        setVisible(false);
        dispose();
    }//GEN-LAST:event_jLabel18MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:
        acp.lab.project1.pages.LoginPage.main(new String[]{});
        setVisible(false);
        dispose();
    }//GEN-LAST:event_jLabel5MouseClicked

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
            java.util.logging.Logger.getLogger(DashboardPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DashboardPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DashboardPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DashboardPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DashboardPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel booksLeftToRateLabel;
    private javax.swing.JLabel daysLeftToEarliestReturnDateLabel;
    private javax.swing.JLabel earliestBookToReturnLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel totalBooksToReturnLabel;
    private javax.swing.JLabel totalDueLabel;
    // End of variables declaration//GEN-END:variables
}
