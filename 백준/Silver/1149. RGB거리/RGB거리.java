import java.io.*;
import java.util.*;

public class Main {
	static int DP[][];
	static int N; 
	public static void main(String[] args) throws Exception {
		/*
		 * D[i][R] = min(D[i-1][B], D[i-1][G]) + score[i][R]
		 * D[i][G] = min(D[i-1][R], D[i-1][B]) + score[i][G]
		 * D[i][B] = min(D[i-1][R], D[i-1][G]) + score[i][B] 
		 * */
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(in.readLine());
		
		DP = new int[N+1][3];
		int R,G,B;
		for (int i=1;i<=N;i++) {
			st = new StringTokenizer(in.readLine());
			R = Integer.parseInt(st.nextToken());
			G = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
	
			DP[i][0] = Math.min(DP[i-1][1], DP[i-1][2]) + R;
			DP[i][1] = Math.min(DP[i-1][2], DP[i-1][0]) + G;
			DP[i][2] = Math.min(DP[i-1][0], DP[i-1][1]) + B;
		}
		
		System.out.println(Math.min(Math.min(DP[N][0], DP[N][1]), DP[N][2]));
		
	}
}
