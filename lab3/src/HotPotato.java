import java.util.*;

public class HotPotato{

  public static void main(String[] args){
    ArrayQueue<String> q = new ArrayQueue<String>();
    String name;

    Scanner scan = new Scanner(System.in);

    //get index of player
    System.out.println("How many players do you want in this game?");
    int num = scan.nextInt();
    scan.nextLine();

    //add players
    System.out.println("Please enter the names of players");
    while(q.size < num)
    {
      name = scan.nextLine();
      q.add(name);
    }

    //choose mode
    System.out.println("Do you want to be an admin ot a player? (type 1 for admin, 2 for user)");
    int answer = scan.nextInt();
    if (answer == 1){
      admin(q);
    }
    else if (answer == 2){
      player(q);
    }
    else{
      System.out.println("Please enter 1 or 2");
    }

    //check correctness
    if(player(q) == admin(q)){
      System.out.println("The prediction was correct!");
    }

    try {
      q.remove();
    } catch (QueueEmptyException e) {
      System.out.println("Guess the queue was empty...");
    } //try-catch
  }

  public static String admin(ArrayQueue<String> p){
    /*Here is the part admin will
    *predict winner with another queue
    */
    //The whole song is 8 person long

    while(p.size > 1) {
      for(int i = 0; i < 7; i++){
        String take = p.peek();
        p.remove();
        p.add(take);
      }
      p.remove();
    }
    String prediction = p.peek();
    //System.out.println("The winner is: " + prediction);

    return prediction;
  }

  public static String player(ArrayQueue<String> q){
    /*Before there is only one person left,
    *remove and readd 7 times and remove the 8th person
    */
    while(q.size > 1) {
      for(int i = 0; i < 7; i++){
        String take = q.peek();
        q.remove();
        q.add(take);
        }
        q.remove();
    }
    String winner = q.peek();
    System.out.println("The winner is: " + winner);
    return winner;
  }
}
