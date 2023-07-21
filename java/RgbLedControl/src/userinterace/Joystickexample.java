
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Joystickexample {

    private static final int WIDTH = 100;
    private static final int HEIGHT = 100;
    private BufferedImage image;
    private Point joystickPosition;

    public Joystickexample() {
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        joystickPosition = new Point(WIDTH / 2, HEIGHT / 2);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, null);

                int joystickRadius = 20;
                int joystickX = joystickPosition.x - joystickRadius;
                int joystickY = joystickPosition.y - joystickRadius;

                g.setColor(Color.RED);
                g.fillOval(joystickX, joystickY, joystickRadius * 2, joystickRadius * 2);
            }
        };

        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        panel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                joystickPosition = e.getPoint();
                panel.repaint();
            }
        });

        JFrame frame = new JFrame("Joystick Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Joystickexample::new);
    }
}
