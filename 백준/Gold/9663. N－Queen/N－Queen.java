import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static boolean[] checkCol; // 열 체크 
	static boolean[] checkCross; // 좌하>우상
	static boolean[] checkCross2; // 좌상>우하
	
	static int cnt = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		checkCol = new boolean[N]; // y 
		checkCross = new boolean[N+N-1]; // x+y
		checkCross2 = new boolean[N+N-1]; // x-y+N-1

		nqueen(0);

		System.out.println(cnt);
	}
	
	private static void nqueen(int curr) {
		if (curr==N) {
			cnt++;
			return;
		}
		for (int i=0;i<N;i++) {
			if (checkCol[i] || checkCross[curr+i] || checkCross2[curr-i+N-1]) continue;
			checkCol[i] = true; // y 
			checkCross[curr+i] = true; // x+y
			checkCross2[curr-i+N-1] = true; // x-y+N-1
			nqueen(curr+1);
			checkCol[i] = false; // y 
			checkCross[curr+i] = false; // x+y
			checkCross2[curr-i+N-1] = false; // x-y+N-1			
		}
	}
	
}
