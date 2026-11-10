import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int[][] grid;
    static int village;
    static int[] dirRow = {-1, 0, 1, 0};
    static int[] dirCol = {0, -1, 0, 1};
    static List<Integer> people;
    static int count;

    static void dfs(int row, int col, int village){
        for(int i = 0 ; i < 4; i++){
            int nextRow = row + dirRow[i];
            int nextCol = col + dirCol[i];

            if(nextRow < 0 || nextCol < 0 || nextRow >= n || nextCol >= n){
                continue;
            }

            if(grid[nextRow][nextCol] <= 0){
                continue;
            }

            grid[nextRow][nextCol] = village;
            count++;
            dfs(nextRow, nextCol, village);
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        grid = new int[n][n];
        for (int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++){
                grid[i][j] = st.nextToken().charAt(0) - '0';
            }
        }

        village = 0;
        people = new ArrayList<>();

        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                if(grid[i][j] == 1){
                    village--;
                    grid[i][j] = village;
                    count = 1;
                    dfs(i, j, village);
                    people.add(count);
                }
            }
        }
        village *= -1;
        System.out.println(village);
        Collections.sort(people);
        for(int i = 0 ; i < village; i++){
            System.out.println(people.get(i));
        }
    }
}