package utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageFit {
    public ImageIcon creatImg(String path, int width, int height) throws IOException {
        BufferedImage image = null;
        image = ImageIO.read(this.getClass().getResource(path));
        return new ImageIcon(fitimage(image, width, height));
    }

//    jLabel1.setIcon(imageIcon);
    public Image fitimage(Image img , int w , int h) {
        BufferedImage resizedimage = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = resizedimage.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.setBackground(Color.WHITE);
        g2.fillRect(0, 0, w, h);
        g2.drawImage(img, 0, 0,w,h,null);
        g2.dispose();
        return resizedimage;
    }
}
