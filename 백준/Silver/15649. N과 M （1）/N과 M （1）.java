import java.io.*;
import java.util.*;

public class Main {
	static int N,M;
	static StringBuilder sb = null;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		boolean [] visited = new boolean[N+1];
		int[] arr = new int[M];
		
		for (int i=1;i<=N; i++) {
			int cnt = 0;
			visited[i] = true;
			arr[cnt] = i;
			backTracking(visited, cnt+1, arr);
			visited[i] = false;
		}
	}
	
	private static void backTracking(boolean[] visited, int cnt, int[] arr) {
		if (cnt >= M) {
			sb = new StringBuilder();
			for (int i=0;i<cnt;i++) {
				sb.append(arr[i]).append(" ");
			}
			System.out.println(sb.toString());
			return; 
		}
		
		for (int i=1;i<=N;i++) {
			if (visited[i]) continue;
			visited[i] = true;
			arr[cnt] = i;
//			System.out.println("cnt: "+cnt);
//			System.out.println(Arrays.toString(arr));
			backTracking(visited, cnt+1, arr);
			visited[i] = false;	
		}
		
		
	}
	
}
