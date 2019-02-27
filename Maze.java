import java.util.*;
import java.io.*;

public class Maze{
  private char[][] maze;
  private boolean animate;
  public Maze(String filename){
    try {
      File file = new File(filename);
      Scanner data = new Scanner(file);
    } catch (FileNotFoundException e) {
      System.out.println("File not Found");
      System.exit(1);
    }
    if (data.hasNextLine())
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
}
