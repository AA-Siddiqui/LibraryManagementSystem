package acp.lab.project1.utils;

import java.io.File;
import acp.lab.project1.utils.DefinedColors;

public class SearchBlockItem extends javax.swing.JPanel {
  private javax.swing.JPanel outerPanel;
  private javax.swing.JLabel titleLabel;
  private javax.swing.JLabel authorLabel;
  private javax.swing.JPanel innerPanel;
  private javax.swing.JLabel imageLabel;
  private javax.swing.JPanel self;
  
  public SearchBlockItem() {
    outerPanel = new javax.swing.JPanel();
    titleLabel = new javax.swing.JLabel();
    authorLabel = new javax.swing.JLabel();
    innerPanel = new javax.swing.JPanel();
    imageLabel = new javax.swing.JLabel();

    setBackground(DefinedColors.backgroundLightColor);
    setBorder(javax.swing.BorderFactory.createLineBorder(DefinedColors.blackColor));
    setPreferredSize(new java.awt.Dimension(310, 310));
    setLayout(new java.awt.GridBagLayout());

    outerPanel.setBackground(DefinedColors.backgroundLightColor);
    outerPanel.setBorder(javax.swing.BorderFactory.createLineBorder(DefinedColors.whiteColor, 2));
    outerPanel.setLayout(new java.awt.GridBagLayout());

    titleLabel.setFont(new java.awt.Font("Roboto Condensed", 1, 22)); // NOI18N
    titleLabel.setForeground(DefinedColors.whiteColor);
    titleLabel.setText("Title");
    java.awt.GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
    gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 32);
    outerPanel.add(titleLabel, gridBagConstraints);

    authorLabel.setFont(new java.awt.Font("Roboto Condensed", 0, 14)); // NOI18N
    authorLabel.setForeground(DefinedColors.whiteColor);
    authorLabel.setText("By Author");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
    gridBagConstraints.insets = new java.awt.Insets(0, 10, 10, 10);
    outerPanel.add(authorLabel, gridBagConstraints);

    innerPanel.setBackground(DefinedColors.backgroundLightColor);
    innerPanel.setBorder(javax.swing.BorderFactory.createLineBorder(DefinedColors.whiteColor));
    innerPanel.setLayout(new java.awt.GridBagLayout());

    // jLabel4.setIcon(new javax.swing.ImageIcon(new
    // javax.swing.ImageIcon(getClass().getResource("/acpl/assignment/assignment5/imgNotFound.jpg").getImage().getScaledInstance(150,
    // 150, java.awt.Image.SCALE_SMOOTH))));
    if (new File("/temp/123456789.png").exists()) {
        imageLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/temp/123456798.png")));
    } else {
        imageLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/acp/lab/project1/resources/imgNotFound.jpg")));
    } // NOI18N
    innerPanel.add(imageLabel, new java.awt.GridBagConstraints());

    outerPanel.add(innerPanel, new java.awt.GridBagConstraints());

    add(outerPanel, new java.awt.GridBagConstraints());
    addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            // to book page with book id or isbn
            System.out.println("COKS");
        }
    });
  }

  public SearchBlockItem(String title, String author, String ISBN, String URL, int BID) {
    self = this;
    outerPanel = new javax.swing.JPanel();
    titleLabel = new javax.swing.JLabel();
    authorLabel = new javax.swing.JLabel();
    innerPanel = new javax.swing.JPanel();
    imageLabel = new javax.swing.JLabel();

    setBackground(DefinedColors.backgroundLightColor);
    setBorder(javax.swing.BorderFactory.createLineBorder(DefinedColors.blackColor));
    setPreferredSize(new java.awt.Dimension(310, 310));
    setLayout(new java.awt.GridBagLayout());

    outerPanel.setBackground(DefinedColors.backgroundLightColor);
    outerPanel.setBorder(javax.swing.BorderFactory.createLineBorder(DefinedColors.whiteColor, 2));
    outerPanel.setLayout(new java.awt.GridBagLayout());

    titleLabel.setFont(new java.awt.Font("Roboto Condensed", 1, 22)); // NOI18N
    titleLabel.setForeground(DefinedColors.whiteColor);
    titleLabel.setText(title.substring(0, Math.min(15, title.length())));
    java.awt.GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
    gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 32);
    outerPanel.add(titleLabel, gridBagConstraints);

    authorLabel.setFont(new java.awt.Font("Roboto Condensed", 0, 14)); // NOI18N
    authorLabel.setForeground(DefinedColors.whiteColor);
    authorLabel.setText("By " + author.substring(0, Math.min(15, author.length())));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
    gridBagConstraints.insets = new java.awt.Insets(0, 10, 10, 10);
    outerPanel.add(authorLabel, gridBagConstraints);

    innerPanel.setBackground(DefinedColors.backgroundLightColor);
    innerPanel.setBorder(javax.swing.BorderFactory.createLineBorder(DefinedColors.whiteColor));
    innerPanel.setLayout(new java.awt.GridBagLayout());

    // jLabel4.setIcon(new javax.swing.ImageIcon(new
    // javax.swing.ImageIcon(getClass().getResource("/acpl/assignment/assignment5/imgNotFound.jpg").getImage().getScaledInstance(150,
    // 150, java.awt.Image.SCALE_SMOOTH))));
    
    ThumbnailManager.main(new String[]{URL, ISBN});
    String pathToBe = "src\\main\\java\\acp\\lab\\project1\\resources\\temp\\" + ISBN + ".png";
    System.out.println(URL);
    try {
        //imageLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource(pathToBe)));
        imageLabel.setIcon(new javax.swing.ImageIcon(pathToBe));
    } catch (Exception e) {
        //imageLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("\\acp\\lab\\project1\\resources\\imgNotFound.jpg")));
        imageLabel.setIcon(new javax.swing.ImageIcon("src\\main\\java\\acp\\lab\\project1\\resources\\imgNotFound.jpg"));
        e.printStackTrace();
    } // NOI18N
    innerPanel.add(imageLabel, new java.awt.GridBagConstraints());

    outerPanel.add(innerPanel, new java.awt.GridBagConstraints());

    add(outerPanel, new java.awt.GridBagConstraints());
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

  public void setImage(String imagePath) {
    imageLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource(imagePath)));
  }
}
