package objectoriented;
//******************************************************************
//  Testing the The StackOfIntegers Class
//******************************************************************

public class TestStackOfIntegers {
  public static void main(String[] args) {
    StackOfIntegers stack = new StackOfIntegers();
    
    for (int i = 0; i < 10; i++) {
//      stack.push(2 * i);	// Push doubles
    	stack.push(i);
    }
    
    while (!stack.empty()){
      System.out.print(stack.pop() + " ");  
    }
  }
}