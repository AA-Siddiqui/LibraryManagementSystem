package acp.lab.project1.utils;

import acp.lab.project1.utils.DefinedColors;

public class SearchListRow extends javax.swing.JPanel {
  private javax.swing.JLabel titleLabel;
  private javax.swing.JLabel authorLabel;
  private javax.swing.JLabel categoryLabel;
  private javax.swing.JPanel self;

  public SearchListRow(String title, String author, String category, int BID) {
    self = this;
    titleLabel = new javax.swing.JLabel();
    authorLabel = new javax.swing.JLabel();
    categoryLabel = new javax.swing.JLabel();
    
    setBackground(DefinedColors.backgroundSecondaryColor);
    setBorder(javax.swing.BorderFactory.createLineBorder(DefinedColors.whiteColor));
    setPreferredSize(new java.awt.Dimension(1260, 75));
    java.awt.GridBagLayout jPanel10Layout = new java.awt.GridBagLayout();
    jPanel10Layout.columnWidths = new int[] { 504, 378, 378 };
    setLayout(jPanel10Layout);

    titleLabel.setText(title);
    titleLabel.setFont(new java.awt.Font("Roboto Condensed", 0, 18)); // NOI18N
    titleLabel.setForeground(DefinedColors.whiteColor);
    titleLabel.setAlignmentX(javax.swing.SwingConstants.LEFT);
    java.awt.GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
    gridBagConstraints.insets = new java.awt.Insets(0, 45, 0, 0);
    add(titleLabel, gridBagConstraints);

    authorLabel.setFont(new java.awt.Font("Roboto Condensed", 0, 18)); // NOI18N
    authorLabel.setForeground(DefinedColors.whiteColor);
    authorLabel.setText(author);
    authorLabel.setAlignmentX(javax.swing.SwingConstants.LEFT);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
    gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
    add(authorLabel, gridBagConstraints);
    
    categoryLabel.setFont(new java.awt.Font("Roboto Condensed", 0, 18)); // NOI18N
    categoryLabel.setForeground(DefinedColors.whiteColor);
    categoryLabel.setText(category);
    categoryLabel.setAlignmentX(javax.swing.SwingConstants.RIGHT);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
    gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
    add(categoryLabel, gridBagConstraints);
    
    addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            acp.lab.project1.pages.BookPage.main(new String[]{Integer.valueOf(BID).toString()});
            javax.swing.SwingUtilities.getWindowAncestor(self).setVisible(false);
            javax.swing.SwingUtilities.getWindowAncestor(self).dispose();
        }
    });
  }

  public void setTitle(String title) {
    titleLabel.setText(title);
  }

  public void setAuthor(String author) {
    authorLabel.setText(author);
  }
}
