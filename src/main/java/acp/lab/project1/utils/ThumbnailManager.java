/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package acp.lab.project1.utils;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.RenderingHints;
/**
 *
 * @author addan
 */
public class ThumbnailManager {
    public static void main(String[] args) {
        try {
            saveImage(args[0], args[1]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void saveImage(String URL, String ISBN) throws IOException {
        String dirName = "src\\main\\java\\acp\\lab\\project1\\resources\\temp\\";
        File directory = new File(dirName);

        if (!directory.exists()) {
            directory.mkdirs();
        }
        
        if (new File(dirName + ISBN + ".png").exists()) return;
        
        saveImage(URL, 150, 150, dirName + ISBN + ".png");
        
        System.out.println(dirName + ISBN + ".png downloaded!");
    }
    private static void saveImage(String imageUrl, int targetWidth, int targetHeight, String outputFilePath) throws IOException {
        URL url = new URL(imageUrl);
        BufferedImage originalImage = ImageIO.read(url);

        float aspectRatio = (float) originalImage.getWidth() / (float) originalImage.getHeight();
        if (targetWidth == 0 && targetHeight > 0) {
          targetWidth = (int) (targetHeight * aspectRatio);
        } else if (targetWidth > 0 && targetHeight == 0) {
          targetHeight = (int) (targetWidth / aspectRatio);
        }
      
        // Create a new image with the desired dimensions
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, originalImage.getType());
        Graphics2D g = resizedImage.createGraphics();
      
        // Use bilinear interpolation for smoother scaling
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        g.dispose();

        ImageIO.write(resizedImage, "png", new File(outputFilePath));
    }
}
