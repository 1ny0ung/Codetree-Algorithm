import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static boolean[] isUsed;
    static int[] combination;
    static int maxNum;
    static int[][] grid;

    static void combine(int idx){
        if(idx == n){
            int arrayMin = Integer.MAX_VALUE;
            for(int i = 0; i < n; i++){
                if(isUsed[i]){
                    arrayMin = Math.min(arrayMin, grid[i][combination[i]]);
                }
            }
            maxNum = Math.max(arrayMin, maxNum);
            return;
        }
        for(int i = 0 ; i < n; i++){
            if(isUsed[i]){
                continue;
            }
            isUsed[i] = true;
            combination[idx] = i;
            combine(idx+1);
            isUsed[i] = false;
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        grid = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        isUsed = new boolean[n];
        combination = new int[n];
        maxNum = Integer.MIN_VALUE;
        combine(0);
        System.out.println(maxNum);
    }
}