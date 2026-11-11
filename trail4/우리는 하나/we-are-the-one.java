import java.util.*;
import java.io.*;

public class Main {
    static final int[] dirRow = {-1, 1, 0, 0}; 
    static final int[] dirCol = {0, 0, -1, 1}; 

    static int k;
    static int n;
    static int u;
    static int d;

    static int[][] grid;
    static boolean[][] isSelected;
    static boolean[][] visited;
    static int count;
    static int maxCount;

    static void select(int startRow, int startCol, int idx){
        if(idx == k){
            visited = new boolean[n][n];
            List<int[]> startPoints = new ArrayList<>();
            count = 0;
            for(int i = 0 ; i < n; i++){
                for(int j = 0; j < n; j++){
                    if(isSelected[i][j]){
                        bfs(i, j);
                    }
                }
            }
            maxCount = Math.max(maxCount, count);
            return;
        }
        if(k - idx > (n-startCol + n * (n-1-startRow))){
            return;
        }
        for(int i = startRow; i < n; i++){
            for(int j = startCol; j < n; j++){
                if(isSelected[i][j]){
                    continue;
                }
                isSelected[i][j] = true;
                int nextRow;
                int nextCol;
                if(j == n-1){
                    nextRow = i+1;
                    nextCol = 0;
                }
                else{
                    nextRow = i;
                    nextCol = j+1;
                }
                select(nextRow, nextCol, idx+1);
                isSelected[i][j] = false;
            }
        }
    }

    static void bfs(int startRow, int startCol){
        if(visited[startRow][startCol]){
            return;
        }
        ArrayDeque<int[]> queue = new ArrayDeque();
        queue.offer(new int[]{startRow, startCol, grid[startRow][startCol]});
        visited[startRow][startCol] = true;
        count++;

        while(!queue.isEmpty()){
            int[] curr = queue.poll();
            for(int i = 0; i < 4; i++){
                int nextRow = curr[0] + dirRow[i];
                int nextCol = curr[1] + dirCol[i];
                if(nextRow < 0 || nextCol < 0 || nextRow >= n || nextCol >= n){
                    continue;
                }
                if(visited[nextRow][nextCol]){
                    continue;
                }
                int diff = Math.abs(curr[2]-grid[nextRow][nextCol]); 
                if(diff < u || diff > d){
                    continue;
                }
                queue.offer(new int[]{nextRow, nextCol, grid[nextRow][nextCol]});
                visited[nextRow][nextCol] = true;
                count++;
            }
        }

    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = st.nextToken().charAt(0) - '0';
        k = st.nextToken().charAt(0) - '0';
        u = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        grid = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        isSelected = new boolean[n][n];
        maxCount = 0;

        select(0, 0, 0);

        System.out.println(maxCount);
    }
}