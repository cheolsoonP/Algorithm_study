import java.io.*;
import java.util.*;

public class Solution {
	static int dp[][];
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(in.readLine());
		for(int tc=1;tc<=T;tc++) {
			st = new StringTokenizer(in.readLine());
			String a = st.nextToken();
			String b = st.nextToken();
			
			int N = a.length();
			int M = b.length();
			dp = new int[N+1][M+1];
			
			for(int i=1;i<=N;i++) {
				char aa = a.charAt(i-1);
				for(int j=1;j<=M;j++) {
					char bb = b.charAt(j-1);
					if(aa == bb) {
						dp[i][j] = Math.max(Math.max(dp[i-1][j-1]+1, dp[i-1][j]), dp[i][j-1]);
					}else {
						dp[i][j] = Math.max(Math.max(dp[i-1][j-1], dp[i-1][j]), dp[i][j-1]);
					}
				}
			}			
			sb.append("#"+tc+" "+dp[N][M]+'\n');
		}
		System.out.println(sb);
	}	
}
