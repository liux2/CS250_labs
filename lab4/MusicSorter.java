import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;
public class MusicSorter{

public static void main(String args[]) {
String[] list = new String[15];
String[] output = new String[15];
String fileName = "Songs.txt";
Scanner scan = new Scanner(System.in);
System.out.println("How would you like to sort your music? (Artist, Genre, Rank, Title, Year)");
try (Scanner scanner = new Scanner(new File(fileName))) {

    for(int j=0; j<15; j++){


    list[j] = (scanner.nextLine());
  }

} catch (IOException e) {
  e.printStackTrace();
}
String input = scan.next();
if(input.equals("Rank")){             //Rank sorting
  for(int j=0; j<15; j++){
  System.out.println(list[j]);
}
System.out.println("\nIgnore non-fatal error, the program is exiting...");
    }
else if(input.equals("Artist")){
System.out.println("Artist: Rank, Title, Artist, Year, Genre");
for(int i=0; i<15; i++){
Scanner skip = new Scanner(list[i]).useDelimiter("\\s*,\\s*");
skip.next();
skip.next();
output[i] = (skip.next() + ": " + list[i]);
}
}
else if(input.equals("Genre")){                                           //Genre sorting
System.out.println("Genre: Rank, Title, Artist, Year, Genre");
  for(int i=0; i<15; i++){
  Scanner skip = new Scanner(list[i]).useDelimiter("\\s*,\\s*");
  skip.next();
  skip.next();
  skip.next();
  skip.next();
  output[i] = (skip.next() + ": " + list[i]);
  }
}
else if(input.equals("Title")){                                         //Title sorting
  System.out.println("Title: Rank, Title, Artist, Year, Genre");
  for(int i=0; i<15; i++){
  Scanner skip = new Scanner(list[i]).useDelimiter("\\s*,\\s*");
  skip.next();
  output[i] = (skip.next() + ": " + list[i]);
  }
}
else if(input.equals("Year")){                                        //Year sorting
  String[] year = new String[15];
  System.out.println("Year: Rank, Title, Artist, Year, Genre");
  for(int i=0; i<15; i++){
  Scanner skip = new Scanner(list[i]).useDelimiter("\\s*,\\s*");
  skip.next();
  skip.next();
  skip.next();
  year[i] = (skip.next() + ": " + list[i]);
  System.out.println(year[i]);
  }
System.out.println("\nIgnore non-fatal error, the program is exiting...");
}
if(!output[0].equals(null)){                                      //Alphabetical sorting
sortStringExchange(output);
for(int k=0; k<15; k++){
  System.out.println(output[k]);
}
}
}

//Sorting methods:
public static void sortStringBubble( String  x [ ] )//Bubble method
      {
            int j;
            boolean flag = true;  // will determine when the sort is finished
            String temp;

            while ( flag )
            {
                  flag = false;
                  for ( j = 0;  j < x.length - 1;  j++ )
                  {
                          if ( x [ j ].compareToIgnoreCase( x [ j+1 ] ) > 0 )
                          {                                             // ascending sort
                                      temp = x [ j ];
                                      x [ j ] = x [ j+1];     // swapping
                                      x [ j+1] = temp;
                                      flag = true;
                           }
                   }
            }
      }
      public static void sortStringExchange( String  x [ ] ) //Exchange Method
      {
            int i, j;
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
