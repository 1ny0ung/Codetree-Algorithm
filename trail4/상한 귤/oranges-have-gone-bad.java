import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static List<int[]> rotten;
    static List<int[]> normal;
    static int[][] grid;
    static int[][] visited;
    static int n;

    static final int[] dirRow = new int[] {-1, 1, 0, 0};
    static final int[] dirCol = new int[] {0, 0, -1, 1};

    static void bfs(){
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        for(int[] rot: rotten){
            queue.offer(new int[]{rot[0], rot[1], 0});
            visited[rot[0]][rot[1]] = 0;
        }

        while(!queue.isEmpty()){
            int[] curr = queue.poll();
            for(int i = 0; i < 4; i++){
                int nextRow = curr[0] + dirRow[i];
                int nextCol = curr[1] + dirCol[i];
                int time = curr[2]+1;

                if(nextRow < 0 || nextCol < 0 || nextRow >= n || nextCol >= n){
                    continue;
                }
                if(visited[nextRow][nextCol] >= 0){
                    continue;
                }
                if(grid[nextRow][nextCol] == 1){
                    visited[nextRow][nextCol] = time;
                    queue.offer(new int[]{nextRow, nextCol, time});
                    grid[nextRow][nextCol] = 2;
                }
            }
        }

        for(int[] norm : normal){
            if(visited[norm[0]][norm[1]] == -1){
                visited[norm[0]][norm[1]] = -2;
            }
        }
        return;
    }

    public static void main(String[] args) throws Exception{
        // Please write your code here.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        grid = new int[n][n];
        rotten = new ArrayList<>();
        normal = new ArrayList<>();
        visited = new int[n][n];
        for(int i = 0; i < n; i++){
            Arrays.fill(visited[i], -1);
        }

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                grid[i][j] = st.nextToken().charAt(0) - '0';
                if(grid[i][j] == 2){
                    rotten.add(new int[]{i, j});
                }
                else if(grid[i][j] == 1){
                    normal.add(new int[]{i, j});
                }
            }
        }

        bfs();

        StringBuffer sb = new StringBuffer();
        for(int i = 0 ; i < n; i++){
            for(int j =0 ; j < n; j++){
                sb.append(visited[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}