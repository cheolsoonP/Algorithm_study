import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_13023_ABCDE {

	/*
	 * n명 중에 연속된 친구 5명, 4개의 간선이 있는지 확인한다. 
	 * 중복 없이
	 * */
	static int N, M, res;
	static ArrayList<Integer>[] adjList;
	static boolean visited[];
	
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new  StringBuilder();
		StringTokenizer st = new StringTokenizer(in.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		visited = new boolean[N];
		
		// 인접 리스트 - 각각은 integer 리스트, 
		adjList = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			adjList[i] = new ArrayList<Integer>();
		}
		
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			adjList[from].add(to);
			adjList[to].add(from);
		} 
		
		for (int i = 0; i < N; i++) {
			dfs(i, 0);
		}
		System.out.println(res);
	}

	private static void dfs(int idx, int depth) {
		if (depth == 4) {
			res = 1;
			return;
		}
		
		if (res == 0) {
			for (int nx: adjList[idx]) {
				if (!visited[nx]) { // 방문하지 않았다면
					visited[idx] = true;
					dfs(nx, depth+1);
					visited[idx] = false;
				}
			}
		}
	}

}
