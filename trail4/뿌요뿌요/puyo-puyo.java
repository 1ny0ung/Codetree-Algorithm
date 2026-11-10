import java.util.*;
import java.io.*;

public class Main {
    static boolean[][] visited;
    static final int[] dirRow = {-1, 0, 1, 0};
    static final int[] dirCol = {0, -1, 0, 1};
    static int n;
    static int count;
    static int maxSize;
    static int blocks;
    static int[][] grid;

    static void dfs(int row, int col, int target){
        for(int i = 0; i < 4; i++){
            int nextRow = row + dirRow[i];
            int nextCol = col + dirCol[i];

            if(nextRow < 0 || nextCol < 0 || nextRow >= n || nextCol >= n){
                continue;
            }
            if(visited[nextRow][nextCol]){
                continue;
            }
            if(grid[nextRow][nextCol] == target){
                visited[nextRow][nextCol] = true;
                count++;
                dfs(nextRow, nextCol, target);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        grid = new int[n][n];
        for (int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++){
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        visited= new boolean[n][n];
        maxSize = 0;

        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                if(!visited[i][j]){
                    count = 1;
                    visited[i][j] = true;
                    dfs(i, j, grid[i][j]);
                    maxSize = Math.max(maxSize, count);
                    if(count >= 4){
                        blocks++;
                    }
                }
            }
        }

        System.out.println(blocks + " " + maxSize);
    }
}