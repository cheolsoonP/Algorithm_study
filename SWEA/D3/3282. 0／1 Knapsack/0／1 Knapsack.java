import java.io.*;
import java.util.*;

public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(in.readLine());
		for(int tc=1;tc<=T;tc++) {
			
			int N, K = 0;
			int item[][];
			st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			item = new int[N+1][2];
			
			for(int i=1;i<=N;i++) {
				st = new StringTokenizer(in.readLine());
				// 부피, 가치
				item[i][0] = Integer.parseInt(st.nextToken());
				item[i][1] = Integer.parseInt(st.nextToken());
			}
			int dp[][] = new int[N+1][K+1];
			
			for(int i=1;i<=N;i++) { // i번째 물건까지 고려했을 때 최대 가치
				int weight = item[i][0];
				int cost = item[i][1];
				for(int j=0;j<=K;j++) {
					dp[i][j] = dp[i-1][j];
					if(j>=weight) {
						dp[i][j] = Math.max(dp[i-1][j-weight]+cost, dp[i][j]);
					}
					// dp[n][k] i번째 물건까지 고려했을 때 K를 넘지 않는 최대 가치					
				}
			}
			sb.append("#"+tc+" "+dp[N][K]+'\n');
		}
		System.out.println(sb);
	}	
}
