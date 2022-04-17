import java.util.LinkedList;
import java.util.Queue;

// QItem for current location and distance
// from source location
class QItem {
    int row;
    int col;
    int dist;
    public QItem(int row, int col, int dist)
    {
        this.row = row;
        this.col = col;
        this.dist = dist;
    }
}

public class Main {
    private static int minDistance(char[][] grid)
    {
        QItem source = new QItem(0, 0, 0);

        // To keep track of visited QItems. Marking
        // blocked cells as visited.
        firstLoop:
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++)
            {

                // Finding source
                if (grid[i][j] == 'S') {
                    source.row = i;
                    source.col = j;
                    break firstLoop;
                }
            }
        }

        // applying BFS on matrix cells starting from source
        Queue<QItem> queue = new LinkedList<>();
        queue.add(new QItem(source.row, source.col, 0));

        boolean[][] visited
                = new boolean[grid.length][grid[0].length];
        visited[source.row][source.col] = true;

        while (!queue.isEmpty()) {
            QItem p = queue.remove();

            // Destination found;
            if (grid[p.row][p.col] == 'E')
                return p.dist;

            // moving up
            if (isValid(p.row - 1, p.col, grid, visited)) {
                queue.add(new QItem(p.row - 1, p.col,
                        p.dist + 1));
                visited[p.row - 1][p.col] = true;
            }

            // moving down
            if (isValid(p.row + 1, p.col, grid, visited)) {
                queue.add(new QItem(p.row + 1, p.col,
                        p.dist + 1));
                visited[p.row + 1][p.col] = true;
            }

            // moving left
            if (isValid(p.row, p.col - 1, grid, visited)) {
                queue.add(new QItem(p.row, p.col - 1,
                        p.dist + 1));
                visited[p.row][p.col - 1] = true;
            }

            // moving right
            if (isValid(p.row, p.col + 1, grid,
                    visited)) {
                queue.add(new QItem(p.row, p.col + 1,
                        p.dist + 1));
                visited[p.row][p.col + 1] = true;
            }
        }
        return -1;
    }

    // checking where it's valid or not
    private static boolean isValid(int x, int y,
                                   char[][] grid,
                                   boolean[][] visited)
    {
        return x >= 0 && y >= 0 && x < grid.length
                && y < grid[0].length && grid[x][y] != '#'
                && !visited[x][y];
    }

    public static void main(String[] args) {
        // To check or test with different input please change the values of int mat[] and char input[] accordingly!!
        int[] mat = {3,4,5};

        char[][][] input = {
                {
                        {'S', '.', '.', '.', '.'}, {'.', '#', '#', '#', '.'}, {'.', '#', '#', '.', '.'}, {'#', '#', '#', '.', '#'}

                },
                {
                        {'#', '#', '#', '#', '#'}, {'#', '#', '#', '#', '#'}, {'#', '#', '.', '#', '#'}, {'#', '#', '.', '.', '.'}

                },
                {
                        {'#', '#', '#', '#', '#'}, {'#', '#', '#', '#', '#'}, {'#', '.', '#', '#', '#'}, {'#', '#', '#', '#', 'E'}

                }
        };



        int resultMatrixLength = mat[0]*mat[1];
        char[][] grid = new char[resultMatrixLength][];

        System.arraycopy(input[0], 0, grid, 0, input[0].length);
        int destPosition = 0;
        for(int i = 1; i<input.length; i++) {
            destPosition += input[i-1].length;
            System.arraycopy(input[i], 0, grid, destPosition, input[i].length);
        }
        int distance = minDistance(grid);
        if(distance == -1 || distance > 11) {
            System.out.println("Sorry! You are caught :-(");
        } else {
            System.out.println("Escaped in "+ distance +" minute(s)!");
        }
    }
}

