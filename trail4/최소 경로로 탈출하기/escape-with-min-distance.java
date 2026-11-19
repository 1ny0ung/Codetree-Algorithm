import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    static final int[] dirRow = {-1, 1, 0, 0};
    static final int[] dirCol = {0, 0, -1, 1};
    static int n;
    static int m;
    static int[][] grid;
    static boolean[][] visited;
    static int minCount;

    public static void bfs(){
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        int[] start = {0, 0, 0};
        visited[0][0] = true;
        queue.offer(start);

        while(!queue.isEmpty()){
            int[] curr = queue.poll();
            if(curr[0] == n-1 && curr[1]== m-1){
                minCount = curr[2];
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nextRow = curr[0] + dirRow[i];
                int nextCol = curr[1] + dirCol[i];

                if(nextRow < 0 || nextCol < 0 || nextRow >= n || nextCol >= m){
                    continue;
                }

                if(visited[nextRow][nextCol]){
                    continue;
                }

                if(grid[nextRow][nextCol] == 0){
                    continue;
                }

                queue.offer(new int[]{nextRow, nextCol, curr[2]+1});
                visited[nextRow][nextCol] = true;
            }
        }
        return;
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        grid = new int[n][m];

        for (int row = 0; row < n; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < m; col++) {
                grid[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[n][m];
        minCount = -1;

        bfs();
        
        System.out.println(minCount);
        
    }
}