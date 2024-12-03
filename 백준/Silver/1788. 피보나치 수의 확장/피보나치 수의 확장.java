import java.util.*;
import java.io.*;
 
public class Main {

	static int MID = 1000000;
	static int MAX_IDX = 2000002;
	public static void main(String[] args) throws Exception {
	
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 
		StringBuilder sb = new StringBuilder();

		long[] DP = new long[MAX_IDX]; 
		// 중간 
		DP[MID] = 0;	//F(0)=0  
		DP[MID+1] = 1;	//F(1)=1
		DP[MID-1] = 1;
		for (int i=MID+1;i<MAX_IDX;i++) {
			// 양수 : F(N) = F(N-1) + F(N-2)
			DP[i] = (DP[i-1]+DP[i-2])%1000000000;
		}

		for (int i=MID-2;i>=0;i--) {
			 // 음수 : F(N) = F(N+2) - F(N+1)
			DP[i] = (DP[i+2]-DP[i+1])%1000000000;
		}

		int idx = Integer.parseInt(in.readLine())+MID;
		if (DP[idx] == 0) {
			System.out.println(0);
		} else if(DP[idx] < 0) {
			System.out.println(-1);
		} else {
			System.out.println(1);
		}
		System.out.println(Math.abs(DP[idx])%1000000000);
	}

}
