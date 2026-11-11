import java.util.*;
import java.io.*;

public class Main {
    static final int[] dirRow = {-1, 1, 0, 0};
    static final int[] dirCol = {0, 0, -1, 1};
    static int n;
    static int m;
    static boolean[][] visited;
    static int[][] grid;
    static ArrayDeque<int[]> outerWater = new ArrayDeque();
    static int glacier;

    static void bfs(){
        ArrayDeque<int[]> queue = new ArrayDeque();
        queue.offer(new int[]{0, 0});
        visited[0][0] = true;

        while(!queue.isEmpty()){
            int[] curr = queue.poll();
            for(int i = 0; i < 4; i++){
                int nextRow = curr[0] + dirRow[i];
                int nextCol = curr[1] + dirCol[i];
                if(nextRow < 0 || nextCol < 0 || nextRow >= n || nextCol >= m){
                    continue;
                }
                if(visited[nextRow][nextCol]){
                    continue;
                }
                if(grid[nextRow][nextCol] == 1){
                    continue;
                }
                queue.offer(new int[]{nextRow, nextCol});
                visited[nextRow][nextCol] = true;
                outerWater.offer(new int[]{nextRow, nextCol});
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
                grid[i][j] = Integer.parseInt(st.nextToken());
                if(grid[i][j] == 1){
                    glacier++;
                }
            }
        }
        visited = new boolean[n][m];

        bfs();
        int meltIce = 0;
        int time = 0;

        while(glacier > 0){
            time++;
            ArrayDeque<int[]> nextWater = new ArrayDeque();
            meltIce = 0;
            while(!outerWater.isEmpty()){
                int[] curr = outerWater.poll();
                for(int i = 0; i < 4; i++){
                    int nextRow = curr[0] + dirRow[i];
                    int nextCol = curr[1] + dirCol[i];
                    if(nextRow < 0 || nextCol < 0 || nextRow >= n || nextCol >= m){
                        continue;
                    }
                    if(visited[nextRow][nextCol]){
                        continue;
                    }
                    visited[nextRow][nextCol] = true;
                    if(grid[nextRow][nextCol] == 0){
                        outerWater.offer(new int[]{nextRow, nextCol});
                    }
                    else{
                        grid[nextRow][nextCol] = 0;
                        nextWater.offer(new int[]{nextRow, nextCol});
                        meltIce++;
                    }
                }
            }
            outerWater = nextWater;
            glacier -= meltIce;
        }

        System.out.println(time + " " + meltIce);
    }
}