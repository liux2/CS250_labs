import java.util.*;
import java.lang.*;
import java.io.*;

public class EightQueens{
  static private int N ;
  static private int counter;
  static private int number;

  public EightQueens(int N, int counter, int number){
    this.N=N;
    this.counter=counter;
    this.number=number;
    solQueenUtil(createBoard(N,N),0);
  }

  public static void main(String[] args) {
    EightQueens eight = new EightQueens(8,0,5);
    EightQueens six = new EightQueens(6,0,1);
    EightQueens tw = new EightQueens(12,0,1);
  }

  public static void printSol(int board[][]){
    for (int i = 0; i < N; i++){
      for (int j = 0; j < N; j++)
        System.out.print(" " + board[i][j] + " ");
        System.out.println();
    }
  }

  public static boolean checkSafety(int board[][], int row, int col){
    int i, j;
    //printSol(board);
    for (i = row; i >= 0; i--){
      if(board[i][col] == 1)
        return false;
    }//columns
    for (i = 0; i < N; i++){
      if(board[row][i] == 1)
        return false;
    }//columns
    for (i=row, j=col; i>=0 && j>=0; i--, j--){
      if (board[i][j] == 1)
        return false;
    }//main diagnol
    for (i=row, j=col; i>=0 && j<N; i--, j++){
      if (board[i][j] == 1)
        return false;
    }//minor dignol
    // for (i=row, j=col; j>=0 && i<N; i++, j--){
    //   if (board[i][j] == 1)
    //     return false;
    // }

    return true;
  }

  public static void addQueen(int board[][], int row, int col){
    board[row][col] = 1;
  }

  public static void removeQueen(int board[][], int row, int col){
    board[row][col] = 0;
  }

  public static void solQueenUtil(int board[][], int row){
    // if (col >= N)
    // return true;

    for (int col = 0; col < N; col++){


      if(checkSafety(board, row, col)){
        // board[i][col] = 1;
        addQueen(board, row, col);
        if (row == N-1){
          counter++;
          System.out.println(counter);
          printSol(board);
          if(counter == number){
            break;
          }
          removeQueen(board,row,col);

        }
        else{
          solQueenUtil(board, row + 1);
        }
        removeQueen(board,row,col);
        // if(solQueenUtil(board, col + 1) == true)
        //   return true;
        //
        // board[i][col] = 0;

      }
    }
  }

  public static int[][] createBoard(int row, int col){
    int board[][] = new int[row][col];
    for(int i = 0; i < N; i++){
      for(int j = 0; j < N; j++){
        board[i][j] = 0;
      }
    }
    return board;
  }
}
