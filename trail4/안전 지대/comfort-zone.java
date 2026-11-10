import java.util.*;
import java.io.*;

public class Main {
    static int[] dirRow = {-1, 0, 1, 0};
    static int[] dirCol = {0, -1, 0, 1};

    static int n;
    static int m;
    static int[][] grid;
    static boolean[][] visited;
    static int[] maxSafe;

    static void dfs(int row, int col, int k){
        for(int i = 0 ; i < 4; i++){
            int nextRow = row + dirRow[i];
            int nextCol = col + dirCol[i];

            if(nextRow < 0 || nextCol < 0 || nextRow >= n || nextCol >= m){
                continue;
            }
            if(grid[nextRow][nextCol] <= k){
                continue;
            }

            if(visited[nextRow][nextCol]){
                continue;
            }

            visited[nextRow][nextCol] = true;
            dfs(nextRow, nextCol, k);
        }
    }

    static void findArea(int k){
        visited = new boolean[n][m];
        int safe = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(grid[i][j] > k && !visited[i][j]){
                    visited[i][j] = true;
                    safe++;
                    dfs(i, j , k);
                }
            }
        }
        
        if(maxSafe[1] < safe){
            maxSafe[0] = k;
            maxSafe[1] = safe;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        grid = new int[n][m];
        int maxHeight = 0;
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                grid[i][j] = Integer.parseInt(st.nextToken());
                maxHeight = Math.max(maxHeight, grid[i][j]);
            }
        }

        maxSafe = new int[]{0, 0};
        for(int i = 1 ; i < maxHeight; i++){
            findArea(i);
        }
        if(maxSafe[1] == 0){
            maxSafe[0] = 1;
        }
        System.out.println(maxSafe[0] + " " + maxSafe[1]);
    }
}