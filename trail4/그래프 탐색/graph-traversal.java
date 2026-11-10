import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int m;
    static int[] u;
    static int[] v;

    static boolean[] visited;
    static int count;

    static void dfs(int start){
        for(int i = 0 ; i < m; i++){
            int next = 0;
            if(u[i] == start){
                next = v[i];
            }
            else if(v[i] == start){
                next = u[i];
            }
            
            if(next != 0 && !visited[next]){
                visited[next] = true;
                count++;
                dfs(next);
            }
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        u = new int[m];
        v = new int[m];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            u[i] = Integer.parseInt(st.nextToken());
            v[i] = Integer.parseInt(st.nextToken());
        }
        count = 0;
        visited = new boolean[n+1];
        visited[1] = true;
        dfs(1);

        System.out.println(count);
    }
}