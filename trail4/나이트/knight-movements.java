import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    static int r1;
    static int c1;
    static int r2;
    static int c2;
    static int n;
    static int minDist;
    static boolean[][] visited;

    static final int[] dirRow = {-1, -1, 1, 1, -2, -2, 2, 2};
    static final int[] dirCol = {-2, 2, -2, 2, -1, 1, -1, 1};

    public static void bfs(){
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        
        int[] start = {r1, c1, 0};
        visited[r1][c1] = true;
        queue.offer(start);

        while(!queue.isEmpty()){
            int[] curr = queue.poll();

            if(curr[0] == r2 && curr[1] == c2){
                minDist = curr[2];
                return;
            }
            for (int i = 0; i < 8; i++) {
                int nextRow = curr[0] + dirRow[i];
                int nextCol = curr[1] + dirCol[i];

                if(nextRow < 0 || nextCol < 0 || nextRow >= n || nextCol >= n){
                    continue;
                }
                if(visited[nextRow][nextCol]){
                    continue;
                }

                visited[nextRow][nextCol] = true;
                queue.offer(new int[]{nextRow, nextCol, curr[2]+1});
            }
        }
        return;
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        r1 = Integer.parseInt(st.nextToken())-1;
        c1 = Integer.parseInt(st.nextToken())-1;
        r2 = Integer.parseInt(st.nextToken())-1;
        c2 = Integer.parseInt(st.nextToken())-1;

        visited = new boolean[n][n];
        minDist = -1;

        bfs();

        System.out.println(minDist);
    }
}