import java.util.*;
import java.io.*;

public class Main {
    static final int[] dirRow = {-1, 1, 0, 0};
    static final int[] dirCol = {0, 0, -1, 1};
    static int n;
    static int k;
    static int m;
    static List<int[]> stones;
    static boolean[] isUsed;
    static int[][] grid;
    static int[][] startPoints;
    static boolean[][] visited;
    static int count; 
    static int maxCount;

    static void combine(int start, int idx){
        if(idx == m){ 
            visited = new boolean[n][n];
            count = 0;
            for(int i= 0; i < stones.size(); i++){
                if(isUsed[i]){
                    grid[stones.get(i)[0]][stones.get(i)[1]] = 0;
                }
            }
            for(int i = 0; i < k; i++){
                bfs(startPoints[i][0], startPoints[i][1]);
            }
            for(int i= 0; i < stones.size(); i++){
                if(isUsed[i]){
                    grid[stones.get(i)[0]][stones.get(i)[1]] = 1;
                }
            }
            maxCount = Math.max(count, maxCount);
            return;
        }
        for(int i = start; i < stones.size(); i++){
            if(isUsed[i]){
                continue;
            }
            if(stones.size()-start < m-idx){
                return;
            }
            isUsed[i] = true;
            combine(i+1, idx+1);
            isUsed[i] = false;
        }
    }

    static void bfs(int row, int col){
        ArrayDeque<int[]> queue = new ArrayDeque();
        if(visited[row][col]){
            return;
        }
        visited[row][col] = true;
        count++;
        queue.offer(new int[]{row, col});

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
                if(grid[nextRow][nextCol] == 1){
                    continue;
                }
                queue.offer(new int[]{nextRow, nextCol});
                visited[nextRow][nextCol] = true;
                count++;
            }
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        grid = new int[n][n];
        stones = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                grid[i][j] = st.nextToken().charAt(0) - '0';
                if(grid[i][j] == 1){
                    stones.add(new int[]{i, j});
                }
            }
        }
        startPoints = new int[k][2];
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            startPoints[i][0] = Integer.parseInt(st.nextToken())-1;
            startPoints[i][1] = Integer.parseInt(st.nextToken())-1;
        }

        maxCount = 0;
        isUsed = new boolean[stones.size()];
        combine(0, 0);

        System.out.println(maxCount);
    }
}