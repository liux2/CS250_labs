import java.io.IOException;//importing necessary packages (lines 1-2)
import java.util.Scanner;
import java.util.List;

public test{
public static void main(String[] args) throws IOException //main program, also has the "throws" in here signaling an expected error
{
    System.out.println("Welcome to the Music Playlist Sorter");//lines 7-9 messages to user, telling them there options etc.
    System.out.println("How would you like to view your playlist?");
    System.out.println("Available options: Artist, Genere, Song, Rank, Year, Quit");
    ArrayList<String> list = new ArrayList<String>();

Scanner inFile = null;
try {
Scanner inFile = new Scanner(new File("Songs.txt"));
} catch (FileNotFoundException e) {

e.printStackTrace();
}

while(inFile.hasNextLine()){
    list.add(inFile.nextLine());
}
  }
}
