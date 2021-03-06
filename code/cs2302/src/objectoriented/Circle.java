package objectoriented;
//******************************************************************
//  The Circle class
//******************************************************************

public class Circle
{
  /** The radius of the circle */
  private double radius;

  /** Construct a circle with radius 1 */
  public Circle() {
    this(1.0);	// Implicitly call another constructor (the one below that accepts a double)
    			// Need to specify 'this' as the first line of the method.
    			// Also, cannot call 'Circle(1.0)' explicitly as that will throw an error. Must use 'this'
  }

  /** Construct a circle with a specified radius */
  public Circle(double newRadius) {
    this.radius = newRadius;
  }

  /** Return the area of this circle */
  public double getArea() {
    return this.radius * this.radius * Math.PI;
  }

  /** Sets the radius */
  public void setRadius(double newRadius) {
    this.radius = newRadius;
  }

  /** Gets the radius */
  public double getRadius() {
    return this.radius;
  }
}
