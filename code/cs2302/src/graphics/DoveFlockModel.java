package graphics;
//*******************************************************************
//  The DoveFlockModel class.
//*******************************************************************

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 * The DoveFlockModel Class - 
 * Extends the Dove class so that doves can move according to the Flocking
 * Algorithm rules.  Also implements a simple rule for rebounding off of
 * the boundaries of the borders of the screen.  Also allows doves to be
 * resized to any dimension by applying a given ratio to the normal Dove
 * class dimensions (a ratio of 2.0 doubles size, 0.5 halves it).
 */
public class DoveFlockModel extends Dove {
   // Final fields to be set in constructor
   private final int DEFWIDTH = 92;          // The dove's default width
   private final int DEFHEIGHT = 125;        // The dove's default height
   private final int WIDTH;                  // The dove's width
   private final int HEIGHT;                 // The dove's height
   private final double DIMRATIO;            // The dove's dimension ratio
   private final double FLOCKRADIUS;         // The radius that a flock extends to
   private final double TOOCLOSE;            // The distance of too close doves

   private final double MAXSPEED = 15;       // The maximum dove speed
   private final double BOUNDARY = 20;       // The margin to turn doves from
   private final double REBOUNDSPEED = 5;    // The speed doves turn from BOUNDARY

    /**
     * DoveFlockModel no arg constructor
     */ 
   public DoveFlockModel()
   {
      this(true,0,0,0,0,1);
   }

    /**
     * DoveFlockModel constructor
     * 
     * @param initIsLeft determines if the dove faces left
     * @param initX is the dove's top left x coordinate
     * @param initY is the dove's top left y coordinate
     * @param initXOff is the dove's x offset speed
     * @param initYOff is the dove's y offset speed
     * @param ratio is the ratio of the dove's size compared to the default
     */
   public DoveFlockModel(boolean initIsLeft, int initX, int initY,
                         int initXOff, int initYOff, double ratio)
   {
      super(initIsLeft, initX, initY, initXOff, initYOff);

      // Setting the flocking constants
      this.DIMRATIO = ratio; 
      this.WIDTH = (int)(DEFWIDTH * DIMRATIO);
      this.HEIGHT = (int)(DEFHEIGHT * DIMRATIO);
      this.FLOCKRADIUS = (int)(300 * DIMRATIO);
      this.TOOCLOSE = Math.min(this.WIDTH,this.HEIGHT) * (3.0 / 4.0);
   }

    /** 
     * Overloads the Dove move() method using the flocking algorithm
     * 
     * @param index is the current dove index in the doves array 
     * @param doves is the doves array
     * @param scrWidth is the screen width
     * @param scrHeight is the screen height
     */
   public void move(ArrayList<DoveFlockModel> doves, int scrWidth, int scrHeight)
   {
      double xMov1 = 0, yMov1 = 0;   // stores rule 1 movement factors
      double xMov2 = 0, yMov2 = 0;   // stores rule 2 movementfactors
      double xMov3 = 0, yMov3 = 0;   // stores rule 3 movementfactors
      int count = 0;                 // count of other doves in local flock

      // Rule #1: Separation - move away from any doves that are too close
      for (DoveFlockModel dove :  doves)
         if (dove != this && distance(dove) < TOOCLOSE) {
            xMov1 -= (dove.getX() - getX()) / 5;
            yMov1 -= (dove.getY() - getY()) / 5;
         }

      // Rule #2: Alignment try to match average velocity vectors of flock
      for (DoveFlockModel dove :  doves)
         if (dove != this && distance(dove) < FLOCKRADIUS) {
            xMov2 += dove.getXVelocity();
            yMov2 += dove.getYVelocity();
            ++count;
         }
      if (count > 0) {  // get 1/10th diff between current and avg velocity
         xMov2 = (xMov2/count - getXVelocity()) / 10;
         yMov2 = (yMov2/count - getYVelocity()) / 10;
      }

      // Rule #3: Cohesion - move toward the center of the flock
      for (DoveFlockModel dove : doves)
         if (dove != this && distance(dove) < FLOCKRADIUS) {
            xMov3 += dove.getX();
            yMov3 += dove.getY();
         }
      if (count > 0) {  // Get 1/100th diff between current and avg position
         xMov3 = ((xMov3+getX())/(count+1) - getX()) / 100;
         yMov3 = ((yMov3+getY())/(count+1) - getY()) / 100;
      }

      // Keep the dove from going too fast
      if (Math.abs(getXVelocity()) > MAXSPEED)
         setXVelocity((getXVelocity() / Math.abs(getXVelocity())) * MAXSPEED);
      if (Math.abs(getYVelocity()) > MAXSPEED)
         setYVelocity((getYVelocity() / Math.abs(getYVelocity())) * MAXSPEED);

      // Re-adjust dove velocity factoring in Rules 1 and 2
      setXVelocity(getXVelocity() + xMov1 + xMov2);
      setYVelocity(getYVelocity() + yMov1 + yMov2);

      // Move the dove a fraction closer to center factoring in Rule 3
      move(getXVelocity() + xMov3, getYVelocity() + yMov3);

      // Rules for rebounding when the Dove hits a BOUNDARY
      if (getX() < BOUNDARY)
         setXVelocity(REBOUNDSPEED);
      if (getX() > scrWidth - BOUNDARY - getWidth())
         setXVelocity(-REBOUNDSPEED);
      if (getY() < BOUNDARY)
         setYVelocity(REBOUNDSPEED);
      if (getY() > scrHeight - BOUNDARY - getHeight())
         setYVelocity(-REBOUNDSPEED);
   }

    /** 
     * Gets the width
     * 
     * @return Returns the width
     */
   public int getWidth()
   {
      return WIDTH;
   }

    /** 
     * Gets the height
     * 
     * @return Returns the height
     */
   public int getHeight()
   {
      return HEIGHT;
   }

    /** 
     * Draws the dove on the given page
     * 
     * @param page is the graphics page
     */
   public void draw(Graphics page)
   {
      draw(page,false);
   }

    /** 
     * Draws the dove on the given page
     * 
     * @param page is the graphics page
     * @param showFlockRadius determines if the flock radius is shown
     */
   public void draw(Graphics page, boolean showFlockRadius)
   {
      if (showFlockRadius) {
         page.setColor(Color.red);
         page.drawOval((int)(getX() + getWidth()/2 - FLOCKRADIUS),
                       (int)(getY() + getHeight()/2 - FLOCKRADIUS),
                       (int)(2*FLOCKRADIUS),(int)(2*FLOCKRADIUS));
         page.setColor(Color.black);
      }
      page.drawImage(getCurrentImage(),(int)getX(),(int)getY(),
                                       getWidth(),getHeight(),null);
   }

    /** 
     * Gets the distance to another Dove's coordinates
     * 
     * @param otherDove is the other dove
     * @return Returns the distance to the other dove
     */
   public double distance(DoveFlockModel otherDove)
   {
      return (Math.sqrt(Math.pow(otherDove.getX()-getX(),2) +
                           Math.pow(otherDove.getY()-getY(),2)));
   }
}