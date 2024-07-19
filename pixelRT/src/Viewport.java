import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

class Viewport extends JFrame {
  private int width;
  private int height;
  private JPanel panel;
  private Graphics g;

  public Viewport(int width, int height) {
    this.width = width;
    this.height = height;

    panel = new JPanel();
    panel.setPreferredSize(new Dimension(this.width, this.height));
    panel.setDoubleBuffered(true);

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(false);
    add(panel);
    pack();
    setLocationRelativeTo(null);
    setVisible(true);

    g = panel.getGraphics();
  }

  public void draw(BufferedImage image) {
    g.drawImage(image,0, 0, this.width, this.height, null);
  }
}