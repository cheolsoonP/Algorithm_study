import java.util.*;
import java.io.*;

public class Main {
	static int weight[], value[], N, K, dp[][];
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		weight = new int[N+1];
		value = new int[N+1];
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(in.readLine());
			weight[i] = Integer.parseInt(st.nextToken());
			value[i] = Integer.parseInt(st.nextToken());
		}
		dp = new int[N+1][K+1];
		// i번째 물건까지 고려했을 때 k 무게까지 담을 수 있는 가방의 최대 가치
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=K;j++) {
				// i번째 물건을 넣을 수 없는 경우
				if(j < weight[i]) {
					dp[i][j] = dp[i-1][j];
				}else { // i번째 물건을 넣는 경우
					dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-weight[i]]+value[i]);
				}
			}
		}
		System.out.println(dp[N][K]);
	}

}
