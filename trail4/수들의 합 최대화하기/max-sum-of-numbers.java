import java.util.*;
import java.io.*;
public class Main {
    static int n;
    static int[][] grid;
    static boolean[] colUsed;
    static int[] colPermutation;
    static int maxSum;

    static void fillCol(int idx){
        if(idx == n){
            int sum = 0;
            for(int i = 0 ; i < n; i++){
                sum += grid[i][colPermutation[i]];
            }
            maxSum = Math.max(maxSum, sum);
            return;
        }
        for(int i = 0; i < n; i ++){
            if(colUsed[i]){
                continue;
            }
            colPermutation[idx] = i;
            colUsed[i] = true;

            fillCol(idx+1);
            colUsed[i] = false;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = br.readLine().charAt(0) - '0';
        grid = new int[n][n];
        for (int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++)
                grid[i][j] = Integer.parseInt(st.nextToken());
        }
        colUsed = new boolean[n];
        colPermutation = new int[n];

        maxSum = 0;
        fillCol(0);

        System.out.println(maxSum);
    }
}