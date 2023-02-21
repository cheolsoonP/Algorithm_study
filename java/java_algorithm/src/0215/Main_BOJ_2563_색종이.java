import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_2563_색종이 {
	
	static int n, x, y, sum;
	static int[][] graph;
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		n = Integer.parseInt(in.readLine());
		graph = new int[101][101];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(in.readLine());
			y = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken());
			
			for (int j = x; j < 10+x; j++) {
				for (int k = y; k < 10+y; k++) {
					graph[j][k] = 1;
				}
			}
		}
		
		for (int i = 0; i < 101; i++) {
			for (int j = 0; j < 101; j++) {
				sum+=graph[i][j]; 
			}
		}
		System.out.println(sum);
		
		
	}

}
