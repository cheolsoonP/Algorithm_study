import java.util.*;
import java.io.*;

public class Main {
	static int N,S; 
	static boolean[] visited; 
	static int[] arr;
	static int result;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		visited = new boolean[N];
		arr = new int[N];
		
		st = new StringTokenizer(in.readLine());
		for (int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		func(0, 0, 0);
		
		System.out.println(result);
	}
	
	private static void func(int start, int num, int cnt) {
		if (cnt != 0 && num == S) {
			result++;
		}
		
		for (int i=start;i<N;i++) {
			if (visited[i]) continue;
			visited[i] = true;
			func(i, num+arr[i], cnt+1);
			visited[i] = false;
		}
		
	}
	
	
}
