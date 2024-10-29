import java.util.*;
import java.io.*; 
/*
현재까지 최대 합 
DP[i][j] = max(DP[i-1][j], DP[i][j-1])
*/
class Solution {
    static int N;
    static int[][] DP; 
    public int solution(int[][] triangle) {
        int answer = 0;
        N = triangle.length; 
        DP = new int[N][N];
        DP[0][0] = triangle[0][0];
        for (int i=1;i<N;i++){
            for (int j=0;j<=i;j++){
                if (j == 0) {
                    DP[i][j] = DP[i-1][j] + triangle[i][j];
                } else {
                    DP[i][j] = Math.max(DP[i-1][j], DP[i-1][j-1]) + triangle[i][j];                    
                }
            }
        }
        
        for (int i=0;i<N;i++){
            answer = Math.max(answer, DP[N-1][i]);
        }
        
        return answer;
    }
}