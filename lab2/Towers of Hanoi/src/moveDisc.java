import java.util.Arrays;
import java.util.Scanner;

public class moveDisc{
  static ArrayStack A;
  static ArrayStack B;
  static ArrayStack C;
static void move(int n, ArrayStack source, ArrayStack target, ArrayStack auxiliary){
    if(n > 0){
        //move n - 1 disks from source to auxiliary, so they are out of the way
        move(n - 1, source, auxiliary, target);

        //move the nth disk from source to target
        target.push(source.pop());

        //Display our progress
        System.out.print(A+ " ");
        System.out.print(B+ " ");
        System.out.print(C+"\n");

        //move the n - 1 disks that we left on auxiliary onto target
        move(n - 1, auxiliary, target, source);
}
}
public static void main(String[] args){
System.out.println("Please enter how many discs need moved");
Scanner scan = new Scanner(System.in);
int n = scan.nextInt();
A = new ArrayStack(n);
B = new ArrayStack(n);
C = new ArrayStack(n);
for(int i=n; i>0; i--){
  A.push(i);
}
move(n, A, C, B);
}
}
