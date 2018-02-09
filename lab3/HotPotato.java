import java.util.*;

public class HotPotato{
  public static int winner(Queue q){
    int a = 0;
    //We assume that it takes each player one second to pass the potato, and that
    //One round of hot potato takes 8 seconds
      while(q.peek() != null){
        a = (int) q.remove();
        if(q.peek() == null){
          a = a;
          q.remove();
        }
        else{
          q.add(a);
          int b = (int) q.remove();
          q.add(b);
          int c = (int) q.remove();
          q.add(c);
          int d = (int) q.remove();
          q.add(d);
          int e = (int) q.remove();
          q.add(e);
          int f = (int) q.remove();
          q.add(f);
          int g = (int) q.remove();
          q.add(g);
          q.remove();
        }
      }
      return a;
  }
  public static void main(String[] args){
    Scanner scan = new Scanner(System.in);

    Queue<Integer> q = new LinkedList<>();
    System.out.println("How many players are playing?");
    int p = scan.nextInt();
    for (int i=1; p+1>i; i++){
        q.add(i);
    }
    Stream.of(q.toString()).forEach(System.out::println);
    int winner = winner(q);
    System.out.println("The winner is " + winner);
  }
}
