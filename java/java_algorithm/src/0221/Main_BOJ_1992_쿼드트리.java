import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_1992_쿼드트리 {

	static int n, graph[][];
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		/*
		 * 흑백 영상 압축 
		 * 흰점 0 검은 점 1
		 * 한곳에 많이 몰려있으면 간단히 표현
		 * 0과 1 섞이면 (왼위 오른위 왼아래 오른아래) 4개 영상 압축
		 * 4개 영역 압축 순서대로 괄호에 묶여 표현
		 * 4등분 -> 4등분 각 사분면 같은 색 ? 섞인색? 
		 * 
		 * */
		n = Integer.parseInt(in.readLine());
		graph = new int[n][n];
		for (int i = 0; i < n; i++) {
			char[] tmp = in.readLine().toCharArray();
			for (int j = 0; j < n; j++) {
				graph[i][j] = (int)(tmp[j] - '0');
			}
		}
//		for (int i = 0; i < n; i++) {
//			for (int j = 0; j < n; j++) {
//				System.out.print(graph[i][j]+" ");
//			}
//			System.out.println();
//		}
		
		dfs(0, 0, n/2);
		dfs(0, n/2, n/2);
		dfs(n/2, 0, n/2);
		dfs(n/2, n/2, n/2);
	}

	private static void dfs(int x, int y, int size) {
		System.out.println();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.print(graph[i][j]+" ");
			}
			System.out.println();
		}
		
		// 1사분면
		int temp = graph[x][y];
		boolean isDiff = false;
		for (int i = x; i < x+size; i++) {
			for (int j = y; j < y+size; j++) {
				if (graph[i][j] != temp) {
					isDiff = true;
				}
			}
		}
		if (isDiff) {
			dfs(x, y, size/2);
			dfs(x, y+size/2, size/2);
			dfs(x+size/2, y, size/2);
			dfs(x+size/2, y+size/2, size/2);
		}
		else {
			return;
		}
//		System.out.print(temp+" ");
		// 2사분면
		
		// 3사분면
		
		// 4사분면
		
	}

}
