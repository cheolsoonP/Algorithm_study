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
		
		func(0, 0);
		
		if (S == 0) result--;
		System.out.println(result);
	}
	
	private static void func(int start, int num) {
		if (start == N) {
			if (num == S) {
				result++;
			}			
		}

		// 1. 방문을 이용 
//		for (int i=start;i<N;i++) {
//			if (visited[i]) continue;
//			visited[i] = true;
//			func(i, num+arr[i]);
//			visited[i] = false;
//		}
		
		
		// 2. 선택할 지 안할지로 
		if (start >= N) return;
		func(start+1, num+arr[start]);
		func(start+1, num);
		
	}
	
	
}
