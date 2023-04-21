import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class Main {
	static BigInteger combination[][];
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		combination = new BigInteger[N+1][M+1];
		
		comb(N, M);
		System.out.println(combination[N][M]);
	}
	
	private static void comb(int n, int r) {
		
		for(int i=0;i<=n;i++) {
			for(int j=0;j<=i && j != r+1; j++) {
				if(i==j || j==0) combination[i][j] = BigInteger.ONE;
				else combination[i][j] = combination[i-1][j].add(combination[i-1][j-1]);
			}
		}
	}
}	
