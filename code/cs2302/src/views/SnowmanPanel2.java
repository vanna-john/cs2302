package views;
//********************************************************
//  Demonstrates creating the Snowman Panel
//********************************************************

import javax.swing.JPanel;
import java.awt.*;

public class SnowmanPanel2 extends JPanel {
  private static final long serialVersionUID = 1L;

  public SnowmanPanel2() {
    setBackground (Color.cyan);
    setPreferredSize (new Dimension(400, 400));
  }

  //-----------------------------------------------------
  //  Draw the Snowman
  //-----------------------------------------------------
  public void paintComponent (Graphics page) {
    super.paintComponent (page);

    final int XMID = 150;
    final int YTOP = 50;

    page.setColor (Color.blue);
    page.fillRect (0, 175, 300, 50);  // ground

    page.setColor (Color.yellow);
    page.fillOval (-40, -40, 80, 80);  // sun

    page.setColor (Color.white);
    page.fillOval (XMID-20, YTOP, 40, 40);      // head
    page.fillOval (XMID-35, YTOP+35, 70, 50);   // upper torso
    page.fillOval (XMID-50, YTOP+80, 100, 60);  // lower torso

    page.setColor (Color.black);
    page.fillOval (XMID-10, YTOP+10, 5, 5);   // left eye
    page.fillOval (XMID+5, YTOP+10, 5, 5);    // right eye

    page.drawArc (XMID-10, YTOP+20, 20, 10, 190, 160);   // smile

    page.drawLine (XMID-25, YTOP+60, XMID-50, YTOP+40);  // left arm
    page.drawLine (XMID+25, YTOP+60, XMID+55, YTOP+60);  // right arm

    page.drawLine (XMID-20, YTOP+5, XMID+20, YTOP+5);  // brim of hat
    page.fillRect (XMID-15, YTOP-20, 30, 25);        // top of hat
  }
}
