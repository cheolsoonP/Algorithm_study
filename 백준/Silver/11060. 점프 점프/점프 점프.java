import java.io.*;
import java.util.*;

public class Main {

	static int N, arr[], dp[];
	static int MAX = Integer.MAX_VALUE-10000;
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(in.readLine());
		st = new StringTokenizer(in.readLine());
		arr = new int[N];
		dp = new int[N+N];
		for(int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.fill(dp, MAX);
		dp[0] = 0;
		// dp[i] i번째 칸까지 온 최소 점프 횟수
		for(int i=0;i<N;i++) {
			//해당 칸에서 점프하기
			for(int j=0;j<=arr[i];j++) {
				 dp[i+j] = Math.min(dp[i+j], (dp[i]+1));
			}
		}
		if(dp[N-1] == MAX) {
			System.out.println(-1);
		}else {
			System.out.println(dp[N-1]);	
		}
		
	}
}
