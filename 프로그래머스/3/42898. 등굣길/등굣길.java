import java.io.*; 
import java.util.*; 

/*
1,1 m,n 
오른쪽과 아래로만 가서, 집에서 학교까지 갈 수 있는 최단 경로의 수 
-> i,j 현재까지 올 수 있는 경우의 수 = i-1,j 의 경우의 수 + i,j-1의 경우의 수 

현재까지의 최단 경로의 수 = 


DP[i][j] = min(DP[i][j-1], DP[i-1][j]) + 1; 
*/
class Solution {
    static int DP[][]; 
    
    public int solution(int M, int N, int[][] puddles) {
        int answer = 0;

        DP = new int[N][M];
        
        for (int i=0;i<puddles.length;i++){
            int y = puddles[i][0]-1;
            int x = puddles[i][1]-1; 
            DP[x][y] = Integer.MAX_VALUE; 
        }
        DP[0][0] = 1;
        
        for (int i=0;i<N;i++){
            for (int j=0;j<M;j++){
                if(DP[i][j] == Integer.MAX_VALUE) {
                    continue;
                }
                if (i-1 >= 0 && DP[i-1][j] != Integer.MAX_VALUE) {
                    DP[i][j] = DP[i-1][j] + DP[i][j];
                }
                if (j-1 >= 0 && DP[i][j-1] != Integer.MAX_VALUE) {
                    DP[i][j] = DP[i][j-1] + DP[i][j];   
                }
                DP[i][j] %= 1000000007;
            }
        }
        
        return DP[N-1][M-1];
        
    }
}