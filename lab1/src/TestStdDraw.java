public class TestStdDraw{
  public static void main(String[] args){
    int n=100;
    StdDraw.setCanvasSize(500,500);
    StdDraw.setXscale(0,n);
    StdDraw.setYscale(0,n*n);
    StdDraw.setPenRadius(0.01);
    for (int i=1; i<=n; i++){
    	StdDraw.point(i,100*i);
    	

    }

    
  }
  
}
