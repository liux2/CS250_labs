import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;

public class stackSorting{
  public static ArrayStack sortstack(ArrayStack input)
  {
    int top;
    ArrayStack tmpStack = new ArrayStack();
      while(!input.isEmpty())
      {
          // pop out the first element
          int tmp = (int) input.pop();

          // while temporary stack is not empty and
          // top of stack is greater than temp

          while(!tmpStack.isEmpty() && (int) tmpStack.top() > tmp)
          {
              // pop item from temporary stack and
              // push it to the input stack
          input.push(tmpStack.pop());
          }

          // push temp variable into tempory of stack
          tmpStack.push(tmp);
      }
      return tmpStack;
  }
//main method
  public static void main(String[] args){
    Random rand = new Random();
    Scanner scan = new Scanner(System.in);
    System.out.println("How many elements would you like to sort? (1024 max. elements)");
    int n = scan.nextInt();
    System.out.println("Generating random elements...");
    ArrayStack input = new ArrayStack(n);
    for(int i=n; i>0; i--){ //for loop fills array
      int j = rand.nextInt(100);
      input.push(j);
    }
    System.out.println("Stack elements before sorting: ");
    System.out.println(input);
    ArrayStack tmpStack = sortstack(input);
    System.out.println(" \n\nStack elements after sorting:");
      System.out.println(tmpStack);

  }
}
