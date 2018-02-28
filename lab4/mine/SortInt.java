import java.io.*;
import java.util.*;

public class SortInt{
  private ArrayList<data> dat;
  private static final String csvFile = "Songs.csv";

  public SortInt(){
    dat = new ArrayList<data>();
  }

  public void addSong(data name){
    dat.add(name);
  }

  public void readRank() throws IOException{
    int i;
    BufferedReader br = null;
    String line = "";
    String csvSplitBy = ",";
    String [] output = new String[1000];

    try{
      br = new BufferedReader(new FileReader(csvFile));
      while((line = br.readLine()) != null){
        String[] Songs = line.split(csvSplitBy);
        //here put into Array
        int n = 0;
        output[n] = Songs[3];
        n++;
      }
    } catch (FileNotFoundException e){
      e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        if (br != null) {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }//read rankNum

    if(!output[0].equals(null)){                                      //Alphabetical sorting
      sortString(output);
      for(int k=0; k<output.length; k++){
        System.out.println(output[k]);
      }
    }

  }

  public void readYear() throws IOException{
    int i;
    BufferedReader br = null;
    String line = "";
    String csvSplitBy = ",";
    String[] output = new String[1000];

    try{
      br = new BufferedReader(new FileReader(csvFile));
      while((line = br.readLine()) != null){
        String[] Songs = line.split(csvSplitBy);
        //here put into Array
        int n = 0;
        output[n] = Songs[3];
        n++;
      }
    } catch (FileNotFoundException e){
      e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        if (br != null) {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

        if(!output[0].equals(null)){                                      //Alphabetical sorting
          sortString(output);
          for(int k=0; k<output.length; k++){
            System.out.println(output[k]);
          }
        }
  }

  public void sortString(String x[ ]){
    int i,j;
    String temp;

    for ( i = 0;  i < x.length - 1;  i++ )
    {
      for ( j = i + 1;  j < x.length;  j++ )
      {
        if ( x [ i ].compareToIgnoreCase( x [ j ] ) > 0 )
          {                                             // ascending sort
            temp = x [ i ];
            x [ i ] = x [ j ];    // swapping
            x [ j ] = temp;
          }
        }
     }
  }
}
