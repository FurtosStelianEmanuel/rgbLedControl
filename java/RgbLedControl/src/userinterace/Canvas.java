/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterace;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class Canvas extends JPanel {

    BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    Point joystickPosition = new Point(WIDTH / 2, HEIGHT / 2);

    int rad = 500;
    BufferedImage img = new BufferedImage(rad, rad, BufferedImage.TYPE_INT_RGB);
    int centerX = img.getWidth() / 2;
    int centerY = img.getHeight() / 2;
    int radius = (img.getWidth() / 2) * (img.getWidth() / 2);

    int redX = img.getWidth();
    int redY = img.getHeight() / 2;
    int redRad = img.getWidth() * img.getWidth();

    // Green Source is (LEFT, MIDDLE)
    int greenX = 0;
    int greenY = img.getHeight() / 2;
    int greenRad = img.getWidth() * img.getWidth();

    // Blue Source is (MIDDLE, BOTTOM)
    int blueX = img.getWidth() / 2;
    int blueY = img.getHeight();
    int blueRad = img.getWidth() * img.getWidth();
    private final MainForm mainForm;

    public Canvas(MainForm mainForm) {

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {

                int a = e.getPoint().x - centerX;
                int b = e.getPoint().y - centerY;

                int distance = a * a + b * b;
                if (distance < radius) {
                    joystickPosition = e.getPoint();
                    repaint();

                    int rdx = e.getPoint().x - redX;
                    int rdy = e.getPoint().y - redY;
                    int redDist = (rdx * rdx + rdy * rdy);
                    int redVal = (int) (255 - ((redDist / (float) redRad) * 256));

                    int gdx = e.getPoint().x - greenX;
                    int gdy = e.getPoint().y - greenY;
                    int greenDist = (gdx * gdx + gdy * gdy);
                    int greenVal = (int) (255 - ((greenDist / (float) greenRad) * 256));

                    int bdx = e.getPoint().x - blueX;
                    int bdy = e.getPoint().y - blueY;
                    int blueDist = (bdx * bdx + bdy * bdy);
                    int blueVal = (int) (255 - ((blueDist / (float) blueRad) * 256));

                    Color c = new Color(redVal, greenVal, blueVal);
                    float hsbVals[] = Color.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(), null);
                    Color highlight = Color.getHSBColor(hsbVals[0], hsbVals[1], 1);

                    System.out.println(highlight);
                    
                    mainForm.jLabel1.setText(RGBtoHEXString(highlight) +"");
                }

            }

        });
        this.mainForm = mainForm;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage colorWheel = getColorWheel();
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(colorWheel, 0, 0, null);

        int joystickRadius = 5;
        int joystickX = joystickPosition.x - joystickRadius;
        int joystickY = joystickPosition.y - joystickRadius;
        g.setColor(Color.BLACK);
        g.drawOval(joystickX, joystickY, joystickRadius * 2, joystickRadius * 2);
    }

    public BufferedImage getColorWheel() {

        // Center Point (MIDDLE, MIDDLE)
        int centerX = img.getWidth() / 2;
        int centerY = img.getHeight() / 2;
        int radius = (img.getWidth() / 2) * (img.getWidth() / 2);

        // Red Source is (RIGHT, MIDDLE)
        int redX = img.getWidth();
        int redY = img.getHeight() / 2;
        int redRad = img.getWidth() * img.getWidth();

        // Green Source is (LEFT, MIDDLE)
        int greenX = 0;
        int greenY = img.getHeight() / 2;
        int greenRad = img.getWidth() * img.getWidth();

        // Blue Source is (MIDDLE, BOTTOM)
        int blueX = img.getWidth() / 2;
        int blueY = img.getHeight();
        int blueRad = img.getWidth() * img.getWidth();

        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {
                int a = i - centerX;
                int b = j - centerY;

                int distance = a * a + b * b;
                if (distance < radius) {
                    int rdx = i - redX;
                    int rdy = j - redY;
                    int redDist = (rdx * rdx + rdy * rdy);
                    int redVal = (int) (255 - ((redDist / (float) redRad) * 256));

                    int gdx = i - greenX;
                    int gdy = j - greenY;
                    int greenDist = (gdx * gdx + gdy * gdy);
                    int greenVal = (int) (255 - ((greenDist / (float) greenRad) * 256));

                    int bdx = i - blueX;
                    int bdy = j - blueY;
                    int blueDist = (bdx * bdx + bdy * bdy);
                    int blueVal = (int) (255 - ((blueDist / (float) blueRad) * 256));

                    Color c = new Color(redVal, greenVal, blueVal);

                    float hsbVals[] = Color.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(), null);

                    Color highlight = Color.getHSBColor(hsbVals[0], hsbVals[1], 1);

                    img.setRGB(i, j, RGBtoHEX(highlight));
                } else {
                    img.setRGB(i, j, 0xFFFFFF);
                }
            }
        }

//        try {
//            ImageIO.write(img, "png", new File("wheel.png"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return img;
        
    }

    public static String RGBtoHEXString(Color color) {
        String hex = Integer.toHexString(color.getRGB() & 0xffffff);
        if (hex.length() < 6) {
            if (hex.length() == 5) {
                hex = "0" + hex;
            }
            if (hex.length() == 4) {
                hex = "00" + hex;
            }
            if (hex.length() == 3) {
                hex = "000" + hex;
            }
        }
        hex = "#" + hex;
        return hex;
    }
    
    public static int RGBtoHEX(Color color) {
        String hex = Integer.toHexString(color.getRGB() & 0xffffff);
        if (hex.length() < 6) {
            if (hex.length() == 5) {
                hex = "0" + hex;
            }
            if (hex.length() == 4) {
                hex = "00" + hex;
            }
            if (hex.length() == 3) {
                hex = "000" + hex;
            }
        }
        hex = "#" + hex;
        return Integer.decode(hex);
    }

}
