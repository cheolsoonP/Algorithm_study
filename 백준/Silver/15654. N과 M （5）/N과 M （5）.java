import java.io.*;
import java.util.*;

public class Main {
	static int N, M, arr[];
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		st = new StringTokenizer(in.readLine());
		
		for(int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		
		dfs(0, 0, new int[M], new boolean[N]);
		
	}
	private static void dfs(int cnt, int curr, int[] temp, boolean[] used) {
		if(cnt == M) {
			for(int i=0;i<M;i++) {
				System.out.print(temp[i]+" ");
			}
			System.out.println();
			return;
		}
		
		for(int i=0;i<N;i++) {
			if(!used[i]) {
				temp[cnt] = arr[i];
				used[i] = true;
				dfs(cnt+1, i+1, temp, used);
				used[i] = false;				
			}
		}
	}
	

}
