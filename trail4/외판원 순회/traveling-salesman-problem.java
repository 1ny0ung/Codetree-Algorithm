import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int[][] cost;
    static boolean[] isUsed;
    static int[] route;
    static int minCost;
    public static void visit(int idx){
        if(idx == n-1){
            int sum = cost[0][route[0]];

            if (sum == 0) {
                return;
            }
            
            for(int i = 0; i < n-1; i++){
                int c = cost[route[i]][route[i+1]];
                if(c == 0){
                    return;
                }
                sum += c;
            }
            minCost = Math.min(minCost, sum);
            return;
        }
        for(int i = 1; i < n; i++){
            if(isUsed[i] == true){
                continue;
            }
            route[idx] = i;
            isUsed[i] = true;

            visit(idx+1);
            isUsed[i] = false;
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        cost = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // Please write your code here.
        isUsed = new boolean[n];
        route = new int[n];
        route[n-1] = 0;
        minCost = Integer.MAX_VALUE;

        visit(0);
        System.out.println(minCost);
    }
}