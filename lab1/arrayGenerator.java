import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class arrayGenerator{
  //private static int[] set; //= new int[10000];

  public static int[] getSet(int num) {

    Random r = new Random();
    int[] set = new int[num];
    for(int i = 0; i < set.length; i++){
      set[i] = r.nextInt(10000000);
    }

    return set;
  }

  public static int getMax(int[] set) {
      int max = set[0];

      for(int i =1; i < set.length; i++){
        if (set[i] > max)
        max = set[i];
      }

      //System.out.println(timeElapsed);
      return max;
  }

  public static void timer() {
    int[] size = new int[11];
    for(int i=1; i < 10; i++) {
      long startTime = System.nanoTime();

      getMax(getSet(i*1000000));
      long endTime = System.nanoTime();
      long timeElapsed = endTime-startTime;

      size[i] = (int)timeElapsed;
    }
    StdDraw.setCanvasSize(500,500);
    StdDraw.setXscale(0,10);
    StdDraw.setYscale(0,getMax(size));
    StdDraw.setPenRadius(0.01);
    for (int i=1; i<10; i++){
    	StdDraw.point(i,size[i]);
    }
  }

  public static void main(String[] args){

    int[] set=getSet(1000);
    timer();
    getMax(set);
    /*
    for(int i = 0; i < set.length; i++){
      System.out.println(set[i]);
    }
    */
    System.out.println("I have generated the array!");
    //System.out.println("Max is "+getMax(set));
  }

}
