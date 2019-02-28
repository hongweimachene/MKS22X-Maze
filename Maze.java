import java.util.*;
import java.io.*;

public class Maze{
  private char[][] maze;
  private boolean animate;
  public Maze(String filename){
    try {
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
    } catch (FileNotFoundException e) {
      System.out.println("File not Found");
      System.exit(1);
    }
  }

  public void setAnimate(boolean b) {
    animate = b;
  }

  public void clearTerminal(){
    //erase terminal, go to top left of screen.
    System.out.println("\033[2J\033[1;1H");
  }

  private boolean validState(char[][] maze) {
    int start = 0;
    int end = 0;
    for (int i = 0; i < maze.length; i++) {
      for (int j = 0; j < maze[i].length; j++) {
        if (maze[i][j] == 'S') start++;
        if (maze[i][j] == 'E') end++;
      }
    }
    return (start == 1 && end == 1);
  }

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

  public int solve(){
    
  }
}
