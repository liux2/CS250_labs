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
if(input.equals("Rank")){
  for(int j=0; j<15; j++){
  System.out.println(list[j]);
}
    }
else if(input.equals("Artist")){
System.out.println("Artist: Rank, Title, Artist, Year, Genre");
for(int i=0; i<15; i++){
Scanner skip = new Scanner(list[i]).useDelimiter("\\s*,\\s*");      // prints: red
skip.next();
skip.next();
output[i] = (skip.next() + ": " + list[i]);
}
}
else if(input.equals("Genre")){
System.out.println("Genre: Rank, Title, Artist, Year, Genre");
  for(int i=0; i<15; i++){
  Scanner skip = new Scanner(list[i]).useDelimiter("\\s*,\\s*");      // prints: red
  skip.next();
  skip.next();
  skip.next();
  skip.next();
  output[i] = (skip.next() + ": " + list[i]);
  }
}
else if(input.equals("Title")){
  System.out.println("Title: Rank, Title, Artist, Year, Genre");
  for(int i=0; i<15; i++){
  Scanner skip = new Scanner(list[i]).useDelimiter("\\s*,\\s*");      // prints: red
  skip.next();
  output[i] = (skip.next() + ": " + list[i]);
  }
}
else if(input.equals("Year")){
  String[] year = new String[15];
  System.out.println("Year: Rank, Title, Artist, Year, Genre");
  for(int i=0; i<15; i++){
  Scanner skip = new Scanner(list[i]).useDelimiter("\\s*,\\s*");      // prints: red
  skip.next();
  skip.next();
  skip.next();
  year[i] = (skip.next() + ": " + list[i]);
  System.out.println(year[i]);
  }
}

sortStringBubble(output);
for(int k=0; k<15; k++){
  System.out.println(output[k]);
}
}
public static void sortStringBubble( String  x [ ] )
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
  	}
