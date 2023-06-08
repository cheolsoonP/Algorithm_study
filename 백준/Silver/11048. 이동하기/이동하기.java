import java.io.*;
import java.util.*;

public class Main {

	static int N, M, graph[][], dp[][];
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		graph = new int[N+1][M+1];
		dp = new int[N+1][M+1];
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(in.readLine());
			for(int j=1;j<=M;j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=M;j++) {
				dp[i][j] = Math.max(Math.max(dp[i-1][j-1], dp[i-1][j]), dp[i][j-1])+graph[i][j];
			}
		}
		
		System.out.println(dp[N][M]);
	}
}
