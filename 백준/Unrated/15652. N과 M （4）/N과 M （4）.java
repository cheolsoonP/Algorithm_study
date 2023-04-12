import java.io.*;
import java.util.*;


public class Main {
	static int N,M,arr[], temp[];
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N+1];
		temp = new int[M+1];
		for(int i=1;i<=N;i++) {
			arr[i] = i;
		}
		dfs(0, 1);
		System.out.println(sb);
	}

	private static void dfs(int cnt, int start) {
		if(cnt == M) {
			for(int i=0;i<cnt;i++) {
				sb.append(temp[i]+" ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i=start; i<=N;i++) {
			temp[cnt] = i;
			dfs(cnt+1, i);
		}
	}
}
