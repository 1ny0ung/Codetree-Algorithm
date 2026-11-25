import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    static int[][] people;
    static int[][] roofs;
    static int[][] grid;
    static int[][] dist;
    static int[][] ans;
    static int n;
    static int m;

    static final int[] dirRow = {-1, 1, 0, 0};
    static final int[] dirCol = {0, 0, -1, 1};
    static boolean[][] visited;

    static void bfs(int startRow, int startCol){
        visited = new boolean[n][n];
        dist = new int[n][n];
        ArrayDeque<int[]> queue = new ArrayDeque<>();

        queue.offer(new int[]{startRow, startCol});
        visited[startRow][startCol] = true;
        dist[startRow][startCol] = 0;
        int roofIdx = 0;

        while(!queue.isEmpty()){
            int[] curr = queue.poll();
            int currDist = dist[curr[0]][curr[1]];
            for(int i = 0; i < 4; i++){
                int nextRow = curr[0] + dirRow[i];
                int nextCol = curr[1] + dirCol[i];

                if(nextRow < 0 || nextCol < 0 || nextRow >= n || nextCol >= n){
                    continue;
                }
                if(grid[nextRow][nextCol] == 1){
                    continue;
                }
                if(visited[nextRow][nextCol]){
                    continue;
                }
                visited[nextRow][nextCol] = true;
                queue.offer(new int[]{nextRow, nextCol});
                dist[nextRow][nextCol] = currDist+1;

                if(grid[nextRow][nextCol] == 3){
                    ans[startRow][startCol] = currDist+1;;
                    return;
                }
            }
        }
        ans[startRow][startCol] = -1;   
        return;
    }

    public static void main(String[] args) throws Exception {
        // Please write your code here.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        grid = new int[n][n];
        people = new int[h][2];
        roofs = new int[m][2];
        ans = new int[n][n];

        int peopleIdx = 0;
        int roofIdx = 0;

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
                if(grid[i][j] == 2){
                    people[peopleIdx][0] = i;
                    people[peopleIdx][1] = j;
                    peopleIdx++;
                }
                else if(grid[i][j] == 3){
                    roofs[roofIdx][0] = i;
                    roofs[roofIdx][1] = j;
                    roofIdx++;
                }
            }
        }
        
        for(int i = 0; i < h; i++){
            bfs(people[i][0] , people[i][1]);
        }

        StringBuffer sb = new StringBuffer();

         for(int i = 0; i < n; i++){
            for (int j = 0; j < n; j++) {
                sb.append(ans[i][j]).append(" ");
            }
            sb.append("\n");
        }
        
        System.out.println(sb.toString());
    }
} 