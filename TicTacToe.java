import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;

// Amy Hynes
// 260716296

// a program that allows a user to play Tic Tac Toe with the AI
public class TicTacToe {
  public static void main(String[] args) {
    play();
  }
  
  // part A
  // creates an "empty" 2D array of n by n characters initialized with the space character
  public static char[][] createBoard(int n) {
    char[][] empty = new char[n][n];
    for (int i = 0; i < empty.length; i++) {
      for (int j = 0; j < empty[i].length; j++) {
        empty[i][j] = ' ';
      }
    }
    return empty;
  }
  
  // a helper method
  // displays a line of a given length of alternating + and -
  public static void printLine(int length) {
    for (int i = 0; i < length; i++) {
      if (i ==length-1) {
        System.out.println("+-+");
      } else {
        System.out.print("+-");
      }
    }
  }
  
  // part B
  // displays the board based on the array containing the current state of the game
  public static void displayBoard(char[][] board) {
    printLine(board.length);
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board.length; j++) {
        System.out.print("|" + board[i][j]);
      }
      System.out.println("|");
      printLine(board.length);
    }
  }
  
  // part C
  // writes a given character at a given position on the board
  public static void writeOnBoard(char[][] board, char c, int x, int y) {
    int size = board.length-1;
    if (x < 0 || x > size || y < 0 || y > size) {
      throw new IllegalArgumentException("This space is not on the board."); 
    } else if (board[x][y] != ' ') {
      throw new IllegalArgumentException("This space is not empty.");
    } else {
      board[x][y] = c;
      displayBoard(board);
    }
  }
  
  // part D
  // uses Scanner to get a move from the user
  public static void getUserMove(char[][] board) {
    Scanner move = new Scanner(System.in); 
    try {
      System.out.print("Please enter your move");
      int x = move.nextInt();
      int y = move.nextInt();
      writeOnBoard(board, 'x', x, y);
    } catch (IllegalArgumentException e) {
      System.err.println("IndexOutOfBoundsException: " + e.getMessage());
      getUserMove(board);
    }
  }
  
  // a helper method
  // returns a row of the board
  public static char[] row(char[][] board, int n) {
    char[] row = board[n];
    return row;
  }
  
  // a helper method
  // returns a column of the board
  public static char[] column(char[][] board, int n) {
    char[] column = new char[board.length];
    for (int i = 0; i < column.length; i++) {
      column[i] = board[i][n];
    }
    return column;
  }
  
  // a helper method
  // returns the diagonal starting from top left of the board
  public static char[] leftDiagonal(char[][] board) {
    char[] leftDiagonal = new char[board.length];
    for (int i = 0; i < leftDiagonal.length; i++) {
      leftDiagonal[i] = board[i][i];
    }
    return leftDiagonal;
  }
  
  // a helper method
  // returns the diagonal starting from top right of the board
  public static char[] rightDiagonal(char[][] board) {
    char[] rightDiagonal = new char[board.length];
    for (int i = 0; i < rightDiagonal.length; i++) {
      rightDiagonal[i] = board[i][rightDiagonal.length-1-i];
    }
    return rightDiagonal;
  }
  
  // a helper method
  // counts how many of a given char are in a 1D array
  public static int countChar(char[] array, char c) {
    int count = 0;
    for (int i = 0; i < array.length; i++) {
      if (array[i] == c) {
        count++; 
      }
    }
    return count;
  }
  
  // a helper method
  // finds a space char in a 1D array
  public static int findSpace(char[] array) {
    for (int i = 0; i < array.length; i++) {
      if (array[i] == ' ') {
        return i; 
      }
    }
    return 0;
  }
  
  // a helper method
  // checks if there is an empty space in a 1D array
  public static boolean isSpace(char[] array) {
    for (int i = 0; i < array.length; i++) {
      if (array[i] == ' ') {
        return true; 
      }
    }
    return false;
  }
  
  // a helper method
  // checks if AI is winning on a row
  // the AI is winning if there is only one empty space in the row and all the other spaces have 'o'
  // critical represents the number of spaces in the row minus the empty one
  public static boolean isWinningRow(char[] row) {
    int critical = row.length - 1;
    int numberOfOs = countChar(row, 'o');
    if (numberOfOs >= critical) {
      return true;
    }
    return false;
  }
  
  // a helper method
  // checks if AI is losing on a row
  public static boolean isLosingRow(char[] row) {
    int critical = row.length - 1;
    int numberOfXs = countChar(row, 'x');
    if (numberOfXs >= critical) {
      return true;
    }
    return false;
  }
  
  // a helper method
  // checks if AI is winning on a column
  public static boolean isWinningColumn(char[] column) {
    int critical = column.length - 1;
    int numberOfOs = countChar(column, 'o');
    if (numberOfOs >= critical) {
      return true;
    }
    return false;
  }
  
  // a helper method
  // checks if AI is losing on a column
  public static boolean isLosingColumn(char[] column) {
    int critical =  column.length - 1;
    int numberOfXs = countChar(column, 'x');
    if (numberOfXs >= critical) {
      return true;
    }
    return false;
  }
  
  // a helper method
  // checks if AI is winning on left diagonal
  public static boolean isWinningLeftDiagonal(char[] leftDiagonal) {
    int critical = leftDiagonal.length - 1;
    int numberOfOs = countChar(leftDiagonal, 'o');
    if (numberOfOs >= critical) {
      return true;
    }
    return false;
  }
  
  // a helper method
  // checks if AI is losing on left diagonal
  public static boolean isLosingLeftDiagonal(char[] leftDiagonal) {
    int critical = leftDiagonal.length - 1;
    int numberOfXs = countChar(leftDiagonal, 'x');
    if (numberOfXs >= critical) {
      return true;
    }
    return false;
  }
  
  
  // a helper method
  // checks if AI is winning on right diagonal
  public static boolean isWinningRightDiagonal(char[] rightDiagonal) {
    int critical = rightDiagonal.length - 1;
    int numberOfOs = countChar(rightDiagonal, 'o');
    if (numberOfOs >= critical) {
      return true;
    }
    return false;
  }
  
  // a helper method
  // checks if AI is losing on right diagonal
  public static boolean isLosingRightDiagonal(char[] rightDiagonal) {
    int critical = rightDiagonal.length - 1;
    int numberOfXs = countChar(rightDiagonal, 'x');
    if (numberOfXs >= critical) {
      return true;
    }
    return false;
  }
  
  // part E
  // checks for an obvious move for the AI
  public static boolean checkForObviousMove(char[][] board) {
    for (int i = 0; i < board.length; i++){
      // having the winning methods over the losing ones ensures the AI will choose to win
      if (isWinningRow(row(board, i)) && isSpace((row(board, i)))) {
        writeOnBoard(board, 'o', i, findSpace(row(board, i)));
        return true;
      } else if (isLosingRow(row(board, i)) && isSpace((row(board, i)))) {
        writeOnBoard(board, 'o', i, findSpace(row(board, i)));
        return true;
      } else if (isWinningColumn(column(board, i)) && isSpace((column(board, i)))) {
        writeOnBoard(board, 'o', findSpace(column(board, i)), i);
        return true;
      } else if ((isLosingColumn(column(board, i))) && isSpace((column(board, i)))) {
        writeOnBoard(board, 'o', findSpace(column(board, i)), i);
        return true;
      } else if (isWinningLeftDiagonal(leftDiagonal(board)) && isSpace((leftDiagonal(board)))) {
        writeOnBoard(board, 'o', findSpace(leftDiagonal(board)), findSpace(leftDiagonal(board)));
        return true;
      } else if (isLosingLeftDiagonal(leftDiagonal(board)) && isSpace((leftDiagonal(board)))) {
        writeOnBoard(board, 'o', findSpace(leftDiagonal(board)), findSpace(leftDiagonal(board)));
        return true;
      } else if (isWinningRightDiagonal(rightDiagonal(board)) && isSpace((rightDiagonal(board)))) {
        writeOnBoard(board, 'o', findSpace(rightDiagonal(board)), board.length - 1 - findSpace(rightDiagonal(board)));
        return true;
      } else if (isLosingRightDiagonal(rightDiagonal(board)) && isSpace((rightDiagonal(board)))) {
        writeOnBoard(board, 'o', findSpace(rightDiagonal(board)), board.length - 1 - findSpace(rightDiagonal(board)));
        return true;
      }
    }
    return false;
  }
  
  // part F
  // the AI makes an obvious move if one is available, or a random move otherwise
  public static void getAIMove(char[][] board) {
    if (checkForObviousMove(board)) {
    } else {
      try {
        Random moveGenerator = new Random();
        int n = board.length;
        // generating an (x,y) move with both ints from 0-n inclusive
        int x = moveGenerator.nextInt(n);
        int y = moveGenerator.nextInt(n);
        writeOnBoard(board, 'o', x, y);
      } catch (IllegalArgumentException e) {
        getAIMove(board);
      }
    }
  }
  
  // part G
  // checks for a winner
  public static char checkForWinner(char[][] board) {
    int n = board.length;
    for (int i = 0; i < board.length; i++) {
      // if a row, column, or diagonal is full of one player's character, they win the game
      if (countChar(row(board, i), 'o') == n || countChar(column(board, i), 'o') == n || countChar(leftDiagonal(board), 'o') == n || countChar(rightDiagonal(board), 'o') == n) {
        return 'o'; 
      } else if (countChar(row(board, i), 'x') == n || countChar(column(board, i), 'x') == n || countChar(leftDiagonal(board), 'x') == n || countChar(rightDiagonal(board), 'x') == n) {
        return 'x'; 
      }
    }
    return ' ';
  }
  
  // a helper method
  // if get winner reports no winner, this method checks if all the spaces are full resulting in an end-game tie
  public static boolean endGameTie(char[][] board) {
    char winner = checkForWinner(board);
    if (winner == ' ') {
      for (int i = 0; i < board.length; i++) {
        // checks if all spaces are full
        if (!isSpace(row(board, i)) && !isSpace(column(board, i)) && !isSpace(leftDiagonal(board)) && !isSpace(rightDiagonal(board))) {
          return true;
        }
      }
    }
    return false;
  }
  
  // part H
  // simulates a game
  public static void play() {
    Scanner name = new Scanner(System.in);
    System.out.println("Please enter your name:");
    String player = name.nextLine();
    System.out.println("Welcome, " + player + "! Are you ready to play?");
    
    Scanner size = new Scanner(System.in);
    System.out.print("Please choose the dimension of your board:");
    int n = size.nextInt();
    char[][] board = createBoard(n);
    int maxMoves = n * n;
    
    // a coin toss to determine who goes first
    Random coinToss = new Random();
    int result = coinToss.nextInt(2);
    System.out.println("The result of the coin toss is: " + result);
    
    // lastMove is a variable to know who went last. 0 for user, 1 for AI
    int lastMove;
    int numberOfMoves = 0;
    if (result == 0) {
      System.out.println(player + " has the first move");
      getUserMove(board);
      lastMove = 0;
      numberOfMoves++;
    } else {
      System.out.println("The AI has the first move");
      System.out.println("The AI has made its move:");
      getAIMove(board);
      lastMove = 1;
      numberOfMoves++;
    }
    
    // a while loop to ensure the program is in a state where the game can continue
    while (numberOfMoves < maxMoves) {
      char winner = checkForWinner(board);
      if (lastMove == 0) {
        System.out.println("The AI has made its move:");
        getAIMove(board);
        lastMove = 1;
        numberOfMoves++;
        winner = checkForWinner(board);
      } else {
        getUserMove(board);
        lastMove = 0;
        numberOfMoves++;
        winner = checkForWinner(board);
      }
      
      // if there is a winner, the program prints the winner and ends
      if (winner != ' ') {
        System.out.println("GAME OVER");
        if (winner == 'o') {
          System.out.println("You lost");
          break;
        } else {
          System.out.println("You won!");
          break;
        }
      }
    }
    
    // if there is no winner and all spaces are full, the program prints this and ends
    if (endGameTie(board)) {
      System.out.println("GAME OVER");
      System.out.println("It's a draw"); 
    }
  }
}







