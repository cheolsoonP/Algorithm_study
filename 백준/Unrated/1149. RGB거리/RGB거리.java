import java.util.*;
import java.io.*;

public class Main {
	static int arr[][], n, dp[][];
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		n = Integer.parseInt(in.readLine());
		arr = new int[n+1][3];
		dp = new int[n+1][3];
		for(int i=1; i<=n; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j=0;j<3;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 현재집이 0로 칠했을때 이전 집까지 1or2로 칠했을 때 최소값
		// 현재집이 1로 칠했을때 이전 집까지 0or2로 칠했을 때 최소값
		// 현재집이 2로 칠했을때 이전 집까지 0or1로 칠했을 때 최소값		
		for(int i=1;i<=n;i++) {
			dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + arr[i][0];
			dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + arr[i][1];
			dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + arr[i][2];
		}
		
		System.out.println(Math.min(dp[n][0], Math.min(dp[n][1], dp[n][2])));
	}
	
}
