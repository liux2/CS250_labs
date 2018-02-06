import java.util.Arrays;
import java.util.Scanner;

public class StockSpan{
static void calculateSpan(int price[], int n, int S[]){
    // Creates and initializes stack
    ArrayStack stack = new ArrayStack(6);
    stack.push(0);

    // Span of first stock is 1
    S[0] = 1;

    // Calculate span values for the rest of the stocks
    for (int i=1; i<n; i++)
    {
      int top = (int) stack.top(); //inherting the object from top as an integer
        // Pops stock from the stack while the stack is not empty and the top of
        // the stack is smaller than price[i]
    	while (!stack.isEmpty() && price[top] <= price[i])
            stack.pop();

        // If the stack is empty, then price[i] is greater than all stocks
        // to the left of it, Else price[i] is greater than stocks after the top
        // of the stack
        S[i] = (stack.isEmpty())? (i + 1) : (i - top);

        // Push this element to stack
        stack.push(i);
    }
}

//Print Array Method
static void printArray(int arr[])
{
    System.out.print(Arrays.toString(arr));
}

//Main method
public static void main(String[] args){
	Scanner scan = new Scanner(System.in);
	System.out.println("Please type in a stocks price, followed by the enter key. (6 stocks)");
	int a = scan.nextInt();
	int b = scan.nextInt();
	int c = scan.nextInt();
	int d = scan.nextInt();
	int e = scan.nextInt();
	int f = scan.nextInt();
    int price[] = {a, b, c, d, e, f};
    int n = price.length;
    int S[]=new int[n];

    // Fill the span values in array S[]
    calculateSpan(price, n, S);

    // print the calculated span values
     printArray(S);
    System.out.println("\nWhich additional stock span would you like printed? (1,2,3,4,5)");
    	int t = scan.nextInt();
    	if(t==1){
    		System.out.println( "1: $"+ price[0] + ": " + S[0]);
    	}
    	else if(t==2){
    		System.out.println( "2: $"+ price[1] + ": " + S[1]);
    	}
    	else if(t==3){
    		System.out.println( "3: $"+ price[2] + ": " + S[2]);
    	}
    	else if(t==4){
    		System.out.println( "4: $"+ price[3] + ": " + S[3]);
    	}
    	else if(t==5){
    		System.out.println( "5: $"+ price[4] + ": " +S[4]);
    	}

    	else{
    		System.out.println("Error, exiting...");

    		System.exit(0);
    	}
    }
}
