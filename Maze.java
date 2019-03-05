import java.util.*;
import java.io.*;

public class Maze{

  //instance variables
  private char[][] maze;
  private boolean animate; //false by default

  /*Constructor loads a maze text file, and sets animate to false by default.
      When the file is not found then:
         throw a FileNotFoundException

      You may assume the file contains a rectangular ascii maze, made with the following 4 characters:
      '#' - Walls - locations that cannot be moved onto
      ' ' - Empty Space - locations that can be moved onto
      'E' - the location of the goal (exactly 1 per file)
      'S' - the location of the start(exactly 1 per file)

      You ma also assume the maze has a border of '#' around the edges.
      So you don't have to check for out of bounds!
  */
  public Maze(String filename) throws FileNotFoundException{
    File file = new File(filename);
    Scanner dimensions = new Scanner(file);
    int rows = 0;
    int cols = 0;
    while (dimensions.hasNextLine()) {
      String line = dimensions.nextLine();
      rows++;
      cols = line.length();
    }
    maze = new char[rows][cols];
    Scanner data = new Scanner(file);
    int row = 0;
    while (data.hasNextLine()){
      String line = data.nextLine();
      for (int i = 0; i < line.length(); i++){
        maze[row][i] = line.charAt(i);
      }
      row++;
    }
    animate = false;
  }

  public void setAnimate(boolean b) {
    animate = b;
  }

  private void wait(int millis){
    try {
      Thread.sleep(millis);
    }
    catch (InterruptedException e) {
    }
  }

  public void clearTerminal(){
    //erase terminal, go to top left of screen.
    System.out.println("\033[2J\033[1;1H");
  }

  /*Return the string that represents the maze.
     It should look like the text file with some characters replaced.
  */
  public String toString() {
    String s = "";
    for (int i = 0; i < maze.length; i++) {
      for (int j = 0; j < maze[i].length; j++) {
        s+=maze[i][j];
      }
      s+="\n";
    }
    return s;
  }

  /*Wrapper Solve Function returns the helper function
      Note the helper function has the same name, but different parameters.
      Since the constructor exits when the file is not found or is missing an E or S, we can assume it exists.
  */
  public int solve(){
    int row = 0;
    int col = 0;
    for (int i = 0; i < maze.length; i++){
      for (int j = 0; j < maze[i].length; j++) {
        if (maze[i][j] == 'S') {
          row = i;
          col = j;
        }
      }
    }
    return solve(row, col);
  }

  /*
      Recursive Solve function:

      A solved maze has a path marked with '@' from S to E.

      Returns the number of @ symbols from S to E when the maze is solved,
      Returns -1 when the maze has no solution.

      Postcondition:
        The S is replaced with '@' but the 'E' is not.
        All visited spots that were not part of the solution are changed to '.'
        All visited spots that are part of the solution are changed to '@'
  */
  private int solve(int row, int col){
    //animation
    if(animate){
      clearTerminal();
      System.out.println(this);
      wait(20);
    }
    if (maze[row][col] == 'E') {
      // int move = 0;
      // for (int h = 0; h < maze.length; h++) {
      //   for (int j = 0; j < maze[h].length; j++) {
      //     if (maze[h][j] == '@') {
      //       move++;
      //     }
      //   }
      // }
      return move;
    }
    //operation of moves: up, down, left, right
    int[][] moves = new int[][] {
      {row-1,col},
      {row+1,col},
      {row,col-1},
      {row,col+1}
    };
    //run through the four possible moves
    maze[row][col] = '@';
    for (int i = 0; i < moves.length; i++){
      if (placeAt(moves[i][0], moves[i][1])) {
        solve(moves[i][0], moves[i][1], move+1);
      }
    }
    //if four possible moves from that spot are not valid, places a '.' in its place
    placeDot(row, col);
    //if no solution, return -1
    return -1;
  }

  //places an '@' at a location
  private boolean placeAt(int row, int col) {
    if (maze[row][col] == ' ' || maze[row][col] == 'S' || maze[row][col] == 'E') {
      return true;
    }
    return false;
  }

  //places an '.' at a location
  private void placeDot(int row, int col) {
    maze[row][col] = '.';
  }
}
