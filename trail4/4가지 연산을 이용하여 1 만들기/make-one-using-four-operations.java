import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {
    static int n;
    static boolean[] visited;
    static int toTargetNum(){
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{n, 0});
        visited[n] = true;

        while(!queue.isEmpty()){
            int[] curr = queue.poll();
            if(curr[0] == 1){
                return curr[1];
            }

            int[] nextNums = {
                (curr[0] % 3 == 0) ? curr[0]/3 : -1,
                (curr[0] % 2 == 0) ? curr[0]/2 : -1,
                curr[0]+1,
                curr[0]-1
            };

            for(int next: nextNums){
                if(next < 1 || next >= 2*n){
                    continue;
                }
                if(visited[next]){
                    continue;
                }
                queue.offer(new int[]{next, curr[1]+1});
                visited[next] = true;
            }
        }
        return -1;
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        visited = new boolean[2*n];
        int count = toTargetNum();
        System.out.println(count);
    }
}