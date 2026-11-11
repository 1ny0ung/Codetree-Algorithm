import java.util.*;
import java.io.*;

public class Main {
    static int[][] grid;
    static int n;
    static int[] pos;
    static boolean[][] visited;

    static final int[] dirRow = {-1, 1, 0, 0};
    static final int[] dirCol = {0, 0, -1, 1};
    static boolean bfs(int r, int c, int num){
        ArrayDeque<int[]> queue = new ArrayDeque();
        visited[r][c] = true;
        queue.add(new int[]{r, c});

        int count = 0;

        while(!queue.isEmpty()){
            int[] curr = queue.poll();
            for(int i = 0; i < 4; i++){
                int nextRow = curr[0] + dirRow[i];
                int nextCol = curr[1] + dirCol[i];

                if(nextRow < 0 || nextCol < 0 || nextRow >= n || nextCol >=n){
                    continue;
                }
                if(grid[nextRow][nextCol] >= num){
                    continue;
                }
                if(visited[nextRow][nextCol]){
                    continue;
                }

                if(count == 0){
                    pos[0] = nextRow;
                    pos[1] = nextCol;
                }
                else if(grid[nextRow][nextCol] > grid[pos[0]][pos[1]]){
                    pos[0] = nextRow;
                    pos[1] = nextCol;
                }
                else if(grid[nextRow][nextCol] == grid[pos[0]][pos[1]]) {
                    if(nextRow < pos[0]){
                        pos[0] = nextRow;
                        pos[1] = nextCol;
                    }
                    else if(nextRow == pos[0] && nextCol < pos[1]){
                        pos[1] = nextCol;
                    }
                }
                count++;
                visited[nextRow][nextCol] = true;
                queue.add(new int[]{nextRow, nextCol});
             }
        }
        if(count == 0){
            return false;
        }
        return true;
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        grid = new int[n][n];
        for (int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++){
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken()) -1;
        int c = Integer.parseInt(st.nextToken()) -1;

        pos = new int[]{r, c};

        for(int i = 0; i < k; i++){
            visited = new boolean[n][n];
            boolean result = bfs(pos[0], pos[1], grid[pos[0]][pos[1]]);
            if(!result){
                break;
            }
        }
        System.out.println((pos[0]+1) + " " + (pos[1]+1));
    }
}