import java.util.*;
import java.io.*;


public class Main {
    static final int[] dirRow = {-1, 1, 0, 0};
    static final int[] dirCol = {0, 0, -1, 1};
    static int k;
    static int n;
    static int[][] grid;
    static int count;

    static void bfs(int[] start){
    if (grid[start[0]][start[1]] == 2) {
        return; 
    }
        ArrayDeque<int[]> queue = new ArrayDeque();
        queue.offer(start);
        count++;
        grid[start[0]][start[1]] = 2;

        while(!queue.isEmpty()){
            int[] curr = queue.poll();
            for(int i = 0 ; i < 4; i++){
                int nextRow = curr[0] + dirRow[i];
                int nextCol = curr[1] + dirCol[i];

                if(nextRow < 0 || nextCol < 0 || nextRow >= n || nextCol >= n){
                    continue;
                }
                if(grid[nextRow][nextCol] >= 1){
                    continue;
                }
                grid[nextRow][nextCol] = 2;
                queue.offer(new int[]{nextRow, nextCol});
                count++;
            }

        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        grid = new int[n][n];
        for (int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++){
                grid[i][j] = st.nextToken().charAt(0) - '0';
            }
        }

        int[][] starts = new int[k][2];
        count = 0;
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            starts[i][0] = Integer.parseInt(st.nextToken()) -1;
            starts[i][1] = Integer.parseInt(st.nextToken()) -1;
            bfs(starts[i]);
        }
        System.out.println(count);
    }
}