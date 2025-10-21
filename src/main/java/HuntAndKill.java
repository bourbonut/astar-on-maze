// Inspired by: https://github.com/professor-l/mazes/blob/master/scripts/hunt-and-kill.js

import java.util.ArrayList;
import java.util.Arrays;

public class HuntAndKill {

  public int[][] maze;

  HuntAndKill(int width, int height) throws Exception {
    width -= width % 2;
    height -= height % 2;

    width++;
    height++;

    maze = new int[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        maze[i][j] = 1;
      }
    }

    // Opening at top - start of maze
    maze[0][1] = 0;
    maze[1][1] = 0;

    int[] on = {1, 1};

    while (!complete()) {
      ArrayList<int[]> n = neighbors(on[0], on[1]);
      if (n.size() == 0) {
        int[][] t = findCoord();
        on = t[0];

        maze[on[0]][on[1]] = 0;
        maze[(on[0] + t[1][0]) / 2][(on[1] + t[1][1]) / 2] = 0;
      } else {
        int i = (int) Math.floor(Math.random() * n.size());
        int[] nb = n.get(i);
        maze[nb[0]][nb[1]] = 0;
        maze[(nb[0] + on[0]) / 2][(nb[1] + on[1]) / 2] = 0;

        on = nb.clone();
      }
    }

    maze[height - 2][width - 1] = 0;
  }

  private ArrayList<int[]> neighbors(int ic, int jc) {
    ArrayList<int[]> final_value = new ArrayList<int[]>();
    for (int i = 0; i < 4; i++) {
      int[] n = {ic, jc};
      // Iterates through four neighbors
      // [i][j - 2] 
      // [i][j + 2]
      // [i - 2][j]
      // [i + 2][j]
      int value = Math.floorDiv(i, 2) * 2;
      n[i % 2] += value != 0 ? value: -2;
      if (
          n[0] < maze.length &&
          n[1] < maze[0].length &&
          n[0] > 0 &&
          n[1] > 0
      ) {
        if (maze[n[0]][n[1]] == 1) {
          final_value.add(n);
        }
      }
    }
    return final_value;
  }

  private ArrayList<int[]> neighborsAB(int ic, int jc) {
    ArrayList<int[]> final_value = new ArrayList<int[]>();
    for (int i = 0; i < 4; i++) {
      int[] n = {ic, jc};
      // Iterates through four neighbors
      // [i][j - 2] 
      // [i][j + 2]
      // [i - 2][j]
      // [i + 2][j]
      int value = Math.floorDiv(i, 2) * 2;
      n[i % 2] += value != 0 ? value: -2;
      if (
          n[0] < maze.length &&
          n[1] < maze[0].length &&
          n[0] > 0 &&
          n[1] > 0
      )
      {
        final_value.add(n);
      }
    }
    return final_value;
  }

  private boolean complete() {
    for (int i = 1; i < maze.length; i += 2) {
      for (int j = 1; j < maze[0].length; j += 2) {
        if (maze[i][j] != 0) {
          return false;
        }
      }
    }
    return true;
  }

  private int[][] findCoord() throws Exception {
    for (int i = 1; i < maze.length; i += 2) {
      for (int j = 1; j < maze[0].length; j += 2) {
        if (maze[i][j] == 1) {
          ArrayList<int[]> n = neighborsAB(i, j);

          for (int k = 0; k < n.size(); k++) {
            int[] idx = n.get(k);
            if (maze[idx[0]][idx[1]] == 0) {
              return new int[][] {{i, j}, idx};
            }
          }
        }
      }
    }
    throw new Exception("Coordinates not found in maze.");
  }

  @Override
  public String toString() {
    String string = new String();
    for (int[] rows : maze) {
      string += "\n" + Arrays.toString(rows);
    }
    return string.substring(1);
  }
}
