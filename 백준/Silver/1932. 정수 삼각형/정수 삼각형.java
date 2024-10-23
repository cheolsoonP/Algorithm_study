import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int DP[][];
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		
		DP = new int[N][N];
		StringTokenizer st = null;
		for (int i=0;i<N;i++) {
			st = new StringTokenizer(in.readLine());
			for (int j=0;j<i+1;j++) {
				int curr = Integer.parseInt(st.nextToken());
				if (i<=0) {// 맨위 
					DP[i][j] = curr;
					continue;
				}
				
				if (j==0) {//왼쪽 끝 
					DP[i][j] = DP[i-1][j] + curr;
				} else {
					DP[i][j] = Math.max(DP[i-1][j], DP[i-1][j-1]) + curr;					
				}
			}
		}
		


		int maxValue = -1;
		for (int i=0;i<N;i++) {
			if (maxValue < DP[N-1][i]) {
				maxValue = DP[N-1][i];
			}
		}
		System.out.print(maxValue);
	}
}
