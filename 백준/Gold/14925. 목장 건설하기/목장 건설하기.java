import java.io.*;
import java.util.*;

public class Main {
	static int N, M, graph[][], maxLength;
	static int dp[][];
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(in.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		graph = new int[M][N];
		dp = new int[M+1][N+1];
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(in.readLine());
			for(int j=0;j<N;j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=1;i<=M;i++) {
			for(int j=1;j<=N;j++) {
				if(graph[i-1][j-1] == 0) {
					dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1])+1;
					maxLength = Math.max(maxLength, dp[i][j]);
				}
			}
		}
		
		System.out.println(maxLength);
	}

}
