package acp.lab.project1.utils;
import acp.lab.project1.LoggedInUser;
import acp.lab.project1.utils.DefinedColors;
import java.sql.*;

public class TransactionHistoryRow extends javax.swing.JPanel {
  private String bookID;
  private javax.swing.JLabel titleLabel;
  private javax.swing.JLabel borrowDateLabel;
  private javax.swing.JLabel returnDateLabel;
  private javax.swing.JButton returnButton;
  private javax.swing.JPanel self;
  
  public TransactionHistoryRow(String title, java.util.Date borrowDate, java.util.Date returnDate, String BID) {
    self = this;
    bookID = BID;
    titleLabel = new javax.swing.JLabel();
    borrowDateLabel = new javax.swing.JLabel();
    returnDateLabel = new javax.swing.JLabel();

    setBackground(DefinedColors.backgroundSecondaryColor);
    setBorder(javax.swing.BorderFactory.createLineBorder(DefinedColors.whiteColor));
    setPreferredSize(new java.awt.Dimension(1260, 75));

    java.awt.GridBagLayout jPanel10Layout = new java.awt.GridBagLayout();
    jPanel10Layout.columnWidths = new int[] { 756, 256, 256 };
    setLayout(jPanel10Layout);

    titleLabel.setFont(new java.awt.Font("Roboto Condensed", 0, 18)); // NOI18N
    titleLabel.setForeground(DefinedColors.whiteColor);
    titleLabel.setText(title);
    java.awt.GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
    gridBagConstraints.insets = new java.awt.Insets(0, 45, 0, 0);
    add(titleLabel, gridBagConstraints);

    borrowDateLabel.setFont(new java.awt.Font("Roboto Condensed", 0, 18)); // NOI18N
    borrowDateLabel.setForeground(DefinedColors.whiteColor);
    borrowDateLabel.setText(new java.text.SimpleDateFormat("MMM dd, yyyy").format(borrowDate));
    add(borrowDateLabel, new java.awt.GridBagConstraints());

    returnDateLabel.setFont(new java.awt.Font("Roboto Condensed", 0, 18)); // NOI18N
    returnDateLabel.setForeground(DefinedColors.whiteColor);
    returnDateLabel.setText(new java.text.SimpleDateFormat("MMM dd, yyyy").format(returnDate));
    
    returnButton = new javax.swing.JButton();
    returnButton.setBackground(DefinedColors.backgroundSecondaryColor);
    returnButton.setFont(new java.awt.Font("Roboto Condensed", 0, 14)); // NOI18N
    returnButton.setForeground(DefinedColors.whiteColor);
    returnButton.setText("Return Now");

    returnButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            Connection con = null;
            try {
                con = acp.lab.project1.utils.ConnectionManager.getConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            int recID = -1;
            try {
                ResultSet rs = con.createStatement().executeQuery("select RecordId from Record where UserId = " + LoggedInUser.userID + " and BookId = " + BID + " and ReturnDate = \'1999-12-31\'");
                while (rs.next()) {
                    recID = rs.getInt("RecordId");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (recID == -1) return;
            try {
                con.createStatement().executeUpdate("insert into Request(RequestType, RecordId, UserId) values(2, " + Integer.valueOf(recID).toString() + ", " + LoggedInUser.userID + ")");
            } catch (SQLException e) {
                e.printStackTrace();
                return;
            }
            javax.swing.JOptionPane.showMessageDialog(javax.swing.SwingUtilities.getWindowAncestor(self), "Admin will confirm and correct your return within 3 business days!", 
                                   "Info", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        }
    });
    
    java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd");
    java.util.Date refDate = null;
    try {
        refDate = formatter.parse("1999-12-31");
    } catch (java.text.ParseException e) {
        e.printStackTrace();
    }
    
    if (returnDate.compareTo(refDate) == 0) {
        if (LoggedInUser.userType == 3) {
            returnDateLabel.setText("Yet to return");
            add(returnDateLabel, new java.awt.GridBagConstraints());
        } else {
            add(returnButton, new java.awt.GridBagConstraints());
        }
    } else {
        add(returnDateLabel, new java.awt.GridBagConstraints());
    }
    if (LoggedInUser.userType != 3) {
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e){
                acp.lab.project1.pages.BookPage.main(new String[]{bookID});
            }
        });
    }

    // gridBagConstraints = new java.awt.GridBagConstraints();
    // gridBagConstraints.gridx = 0;
    // gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;

  }

  public void setTitle(String title) {
    titleLabel.setText(title);
  }

  public void setBorrowDate(String borrowDate) {
    borrowDateLabel.setText(borrowDate);
  }

  public void setReturnDate(String returnDate) {
    returnDateLabel.setText(returnDate);
  }
}