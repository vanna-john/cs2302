package inheritance;
//******************************************************************
//  Demonstrates creating a subclass called Circle
//******************************************************************

public class Circle extends GeometricObject {
  private double radius;

  public Circle() {
  }

  public Circle(double radius) {
// Default behavior is to call the no-arg constructor
//  super();
    this.radius = radius;
  }

  public Circle(double radius, String color, boolean filled) {
    // Explicitly calling super() with arguments
	// Also, needs to be first line in constructor
	super(color, filled);
    this.radius = radius;
    //setColor(color);
    //setFilled(filled);
  }

  /** Return radius */
  public double getRadius() {
    return radius;
  }

  /** Set a new radius */
  public void setRadius(double radius) {
    this.radius = radius;
  }

  /** Return area */
  public double getArea() {
    return radius * radius * Math.PI;
  }
  
  /** Return diameter */
  public double getDiameter() {
    return 2 * radius;
  }
  
  /** Return perimeter */
  public double getPerimeter() {
    return 2 * radius * Math.PI;
  }

  /* Print the circle info */
  public void printCircle() {
    System.out.println("The circle was created " + getDateCreated() +
      " and the radius is " + radius);
  }
  
  /* Override the toString method defined in GeometricObject */
  public String toString() {
	// Explicitly calling superclass' "toString()" method to get details.
    return "Circle details: " + super.toString() + 
            "\narea: " + getArea() +
             " and circumference: " + getPerimeter() + "\n"
             // Implicitly calling super.getColor() because no getColor() method is defined in this class
             + "(the color is: " + getColor() + ")";
  }
}
