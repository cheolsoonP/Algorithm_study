import java.util.*; 
import java.io.*; 
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 
		int N = Integer.parseInt(in.readLine()); 
		
		long[][] DP = new long[101][10];
		DP[1][0] = 0;
		for(int i=1;i<=9;i++) {
			DP[1][i] = 1;
		}
		
		for (int i=2;i<=100;i++) {
			for (int j=0;j<=9;j++) {
				if (j==0) {
					DP[i][j] = DP[i-1][1];
				} else if (j==9) {
					DP[i][j] = DP[i-1][8];
				} else {
					DP[i][j] = (DP[i-1][j-1]+DP[i-1][j+1]) % 1000000000L;
				}
			}
		}
		
		long result = 0L;
		for (int i=0;i<=9;i++) {
			result = (result + DP[N][i]) % 1000000000L;
		}
		
		System.out.println(result);
	}
}
