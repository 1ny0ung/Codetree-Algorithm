import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.jar.Pack200;

public class Main {
    static int k;
    static int n;
    static int startRow;
    static int startCol;
    static int endRow;
    static int endCol;
    static int[][] grid;
    static List<int[]> walls;
    static boolean[] isUsed;
    static boolean[][] visited;
    static int minDist;

    static final int[] dirRow = {-1, 1, 0, 0};
    static final int[] dirCol = {0, 0, -1, 1};

    static void combine(int start, int idx){
        if(idx == k){
            for(int i = 0; i < walls.size(); i++){
                if(isUsed[i]){
                    int[] wall = walls.get(i);
                    grid[wall[0]][wall[1]] = 0;
                }
            }
            bfs();
            for(int i = 0; i < walls.size(); i++){
                if(isUsed[i]){
                    int[] wall = walls.get(i);
                    grid[wall[0]][wall[1]] = 1;
                }
            }
            return;
        }
        for(int i = start; i < walls.size(); i++){
            if(isUsed[i]){
                continue;
            }
            if(walls.size()-start < k-idx-1){
                return;
            }
            isUsed[i] = true;
            combine(i+1, idx+1);

            isUsed[i] = false;
            combine(i+1, idx);
        }
    }

    static void bfs(){
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        visited = new boolean[n][n];
        queue.offer(new int[]{startRow, startCol, 0});
        visited[startRow][startCol] = true;

        while(!queue.isEmpty()){
            int[] curr = queue.poll();
            for(int i = 0 ; i < 4; i++){
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
                queue.offer(new int[]{nextRow, nextCol, curr[2]+1});
                visited[nextRow][nextCol] = true;

                if(nextRow == endRow && nextCol == endCol){
                    if(minDist > curr[2]+1){
                        minDist = curr[2]+1;
                    }
                }
            }

        }
    }

    public static void main(String[] args) throws Exception {
        // Please write your code here.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = st.nextToken().charAt(0) - '0';

        grid = new int[n][n];
        walls = new ArrayList<>();
        for (int row = 0; row < n; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < n; col++) {
                grid[row][col] = st.nextToken().charAt(0) - '0';
                if(grid[row][col] == 1){
                    walls.add(new int[]{row, col});
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        startRow = Integer.parseInt(st.nextToken())-1;
        startCol = Integer.parseInt(st.nextToken())-1;

        st = new StringTokenizer(br.readLine());
        endRow = Integer.parseInt(st.nextToken())-1;
        endCol = Integer.parseInt(st.nextToken())-1;

        isUsed = new boolean[walls.size()];
        minDist = Integer.MAX_VALUE;
        combine(0, 0);

        if(minDist == Integer.MAX_VALUE){
            minDist = -1;
        }

        System.out.println(minDist);
    }
}