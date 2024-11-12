import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 
		
		int N = Integer.parseInt(in.readLine());
		int[][]DP = new int [N+1][10];
		long result = 0; 
		for (int i=1;i<=N;i++) {
			for (int j=0;j<=9;j++) {
				for (int k=j;k<=9;k++) {
					if(i==1) {
						DP[i][j] = 1; 
						continue;
					}
					DP[i][j] += DP[i-1][k];
                    DP[i][j] %= 10007;
				}
			}
		}
		for (int i=0;i<=9;i++) {
			result += DP[N][i];
            result %= 10007;
		}
		System.out.println(result);
	}

}
