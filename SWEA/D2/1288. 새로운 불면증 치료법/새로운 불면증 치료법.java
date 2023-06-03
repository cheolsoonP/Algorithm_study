import java.io.*;
import java.util.*;

public class Solution {
	static int N;
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(in.readLine());
		int total = (1 << 10) -1; // 모든 숫자가 등장했을 때 값
		
		for(int tc = 1; tc<= T; tc++) {
			N = Integer.parseInt(in.readLine());

			int visited = 0;
			int count = 0;
			for(count = 1; ;count++) {
				char[] arr = String.valueOf(N * count).toCharArray();
				for(char c: arr) {
					int num = c - '0';
					visited = visited | (1<<num);
				}
				if(visited == total) break;
			}
			System.out.println("#"+ tc + " "+ N*count);
		}
	}
}
