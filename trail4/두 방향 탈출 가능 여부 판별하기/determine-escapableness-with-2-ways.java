import java.util.*;
import java.io.*;

public class Main {
    static int[] dirRow = {0, 1};
    static int[] dirCol = {1, 0};
    static boolean[][] visited;
    static int[][] grid;
    static int n;
    static int m;

    static void dfs(int row, int col){
        for(int i = 0 ; i < 2; i++){
            int nextRow = row + dirRow[i];
            int nextCol = col + dirCol[i];
            if(nextRow >= 0 && nextCol >= 0 && nextRow < n && nextCol < m){
                if(visited[nextRow][nextCol] || grid[nextRow][nextCol] == 0){
                    continue;
                }
                visited[nextRow][nextCol] = true;
                dfs(nextRow, nextCol);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        grid = new int[n][m];
        for (int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++){
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        visited = new boolean[n][m];
        dfs(0 , 0);
        if(visited[n-1][m-1]){
            System.out.println(1);
        }
        else{
            System.out.println(0);
        }
    }
}