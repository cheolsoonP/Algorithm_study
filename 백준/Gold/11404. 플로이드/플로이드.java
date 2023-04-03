import java.util.*;
import java.io.*;


public class Main {

	static int N, M, DP[][]; 
	static int MAX;
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		
		N = Integer.parseInt(in.readLine());
		M = Integer.parseInt(in.readLine());
		MAX = 100000 * 100 * 100;
		DP = new int[N][N];
		for(int i=0;i<N;i++)
			for(int j=0;j<N;j++)
				if(i!=j) DP[i][j] = MAX;
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(in.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			DP[from-1][to-1] = Math.min(cost, DP[from-1][to-1]);
		}
		
		for(int k=0;k<N;k++) {// 경유지
			for(int i=0;i<N;i++) {// 출발지
				if(k==i) continue;
				for(int j=0;j<N;j++) { // 도착지
					if(k==j || i==j) continue;
					DP[i][j] = Math.min(DP[i][j], DP[i][k] + DP[k][j]);					
				}
			}
		}
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(DP[i][j] == MAX) sb.append(0).append(" ");
				else sb.append(DP[i][j]).append(" ");
			}
			sb.append('\n');
		}
		
		System.out.println(sb);
	}

}
