import java.util.*;
import java.io.*;

public class Main {
    static final int[] dirRow = {-1, 0, 1, 0};
    static final int[] dirCol = {0, -1, 0, 1};
    static int n;
    static int m;
    static int[][] grid;

    static void bfs(){
        ArrayDeque<int[]> queue = new ArrayDeque();
        queue.offer(new int[]{0, 0});
        grid[0][0] = -1;

        while(!queue.isEmpty()){
            int[] curr = queue.poll();
            for(int i = 0; i < 4; i++){
                int nextRow = curr[0] + dirRow[i];
                int nextCol = curr[1] + dirCol[i];
                if(nextRow < 0 || nextCol < 0 || nextRow >= n || nextCol >= m){
                    continue;
                }
                if(grid[nextRow][nextCol] < 1){
                    continue;
                }
                grid[nextRow][nextCol] = -1;
                queue.offer(new int[]{nextRow, nextCol});
            }
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        grid = new int[n][m];
        for (int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++){
                grid[i][j] = st.nextToken().charAt(0) - '0';
            }
        }

        bfs();

        if(grid[n-1][m-1] == -1){
            System.out.println(1);
        }
        else{
            System.out.println(0);
        }
    }
}