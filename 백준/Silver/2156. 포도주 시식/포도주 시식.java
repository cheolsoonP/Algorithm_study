import java.io.*;
import java.util.*;

public class Main {
	static int N; 
	static int[][] DP; 
	static int[] arr; 
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		arr = new int[N];
		DP = new int[N][3];
		for (int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(in.readLine());
		}
		
		DP[0][1] = arr[0];
		DP[0][2] = arr[0];
		for (int i=1;i<N;i++) {
			DP[i][0] = Math.max(Math.max(DP[i-1][0], DP[i-1][1]), DP[i-1][2]);//현재 선택 X 
			DP[i][1] = DP[i-1][0]+arr[i];//이전 선택 X, 현재 선택 O
			DP[i][2] = DP[i-1][1]+arr[i];//이전 선택 O, 현재 선택 O 
		}
		
		System.out.println(Math.max(Math.max(DP[N-1][0], DP[N-1][1]), DP[N-1][2]));
	}

}
