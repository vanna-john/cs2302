package views;
//*******************************************************************
//  Demonstrates the use of graphical objects.
//*******************************************************************

import javax.swing.*;
//import java.awt.*;  // Commented because it's not used

public class Splat
{
   //----------------------------------------------------------------
   //  Presents a collection of circles.
   //----------------------------------------------------------------
   public static void main (String[] args)
   {
      JFrame frame = new JFrame ("Splat");
      frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

      frame.getContentPane().add(new SplatPanel());

      frame.pack();
      frame.setVisible(true);
   }
}
