import java.io.*;
import java.util.*;

public class musicList{

  private static final String csvFile = "Songs.csv";

  public static void main(String[] args) throws IOException {
    System.out.println("Welcome to your playlist!");

    do{
      showList();
      if(whetherAdd() == true){
        addSongs();
      }
    } while(quit());
  }

  public static void showList(){
    //reference
    SortInt RY = new SortInt();
    SortString SAG = new SortString();

    BufferedReader br = null;
    String line = "";
    String cvsSplitBy = ",";

    System.out.println("Here is your playlist: "); //add to class

      try {

      br = new BufferedReader(new FileReader(csvFile));
      System.out.println("Rank   Song   Artist   Year   Genre");
      while ((line = br.readLine()) != null) {

          // use comma as separator
          String[] Songs = line.split(cvsSplitBy);

          System.out.println(Songs[0] + "   " + Songs[1] + "   " + Songs[2] + "   " + Songs[3] + "   " + Songs[4]);
        }

      } catch (FileNotFoundException e) {
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
    Scanner option = new Scanner(System.in);
    System.out.println("How would you like to sort your music list?");
    System.out.println("Available options: Rank, Song, Artist, Year, Genre, Stop");
    try{
    while(option.hasNext()){
      String command = option.nextLine();
      if(command.equals("Rank")){
        RY.readRank();
      }
      else if (command.equals("Song")){
        SAG.readSong();
      }
      else if(command.equals("Artist")){
        SAG.readArtist();
      }
      else if(command.equals("Year")){
        RY.readYear();
      }
      else if(command.equals("Genre")){
        SAG.readGenre();
      }
      else if(command.equals("Stop")){
        break;
      }
      else{
        System.out.println("The commonds are Rank, Song, Artist, Year, Genre, and Stop");
      }
    }
  }catch (IOException e) {
    }
  }

  public static boolean whetherAdd(){
    Scanner ans = new Scanner(System.in);
    System.out.println("Do you want to add any songs?(y/n)");
    String anw = ans.next().toLowerCase();
    if (anw.equals("y"))
    {
        return true;
    }
    else if (anw.equals("n"))
    {
        return false;
    }
    else
    {
        System.out.println("Please answer y or n.");
        return whetherAdd();
    }
  }

  public static void addSongs(){
    Scanner scan = new Scanner(System.in);
    System.out.println("What is the name of your song?");
    String song = scan.next();
    System.out.println("What is the rank of the song?");
    int rank = scan.nextInt();
    System.out.println("Who is the artist of the song?");
    String artist = scan.next();
    System.out.println("When is the song published?");
    int year = scan.nextInt();
    System.out.println("What is the genre?");
    String genre = scan.next();

    data newSong = new data(rank, song, artist, year, genre);
    List<data> songs = new ArrayList<data>();
    songs.add(newSong);

    FileWriter fileWriter = null;
    try {
      fileWriter = new FileWriter(csvFile, true);

      for(data Songs : songs){
        fileWriter.append(String.valueOf(Songs.getRank()));
        fileWriter.append(",");
        fileWriter.append(Songs.getSong());
        fileWriter.append(",");
        fileWriter.append(Songs.getArtist());
        fileWriter.append(",");
        fileWriter.append(String.valueOf(Songs.getYear()));
        fileWriter.append(",");
        fileWriter.append(Songs.getGenre());
        fileWriter.append("\n");
      }
      fileWriter.flush();
    } catch (Exception e){
      System.out.println("Error in CsvFileWriter !!!");
      e.printStackTrace();
    } finally {
      try {
        fileWriter.close();
      } catch (IOException e){
        System.out.println("Error while flushing/closing fileWriter !!!");
        e.printStackTrace();
      }
    }
  }

  public static boolean quit(){
    Scanner b = new Scanner(System.in);

      System.out.println("Would you like to play again? (y/n)");
        String yn = b.nextLine().toLowerCase();
        if (yn.equals("y"))
        {
            return true;
        }
        else if (yn.equals("n"))
        {
            return false;
        }
        else
        {
            System.out.println("Please answer y or n.");
            return quit();
        }
  }
}
