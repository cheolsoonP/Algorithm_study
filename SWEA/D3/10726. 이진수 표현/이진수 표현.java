import java.io.*;
import java.util.*;


public class Solution {
	static int N, M;
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(in.readLine());
		for(int tc = 1; tc<=T; tc++) {
			st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			int check = (1 << N) - 1;
			if((check | M) == M) {
				System.out.println("#"+tc + " "+ "ON");
			}else {
				System.out.println("#"+tc + " "+ "OFF");
			}
		}
	}
}
