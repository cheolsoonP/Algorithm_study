import java.util.*; 
import java.io.*;

public class Main {
	static int N; 
	static int M; 
	static int V; 
	static StringBuilder sb = new StringBuilder(); 

	static int graph[][]; // 인접행렬 
	static boolean[] visited; 
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 
		
		st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken())-1;
		graph = new int[1001][1001]; 
		visited = new boolean[1001]; 
		for (int i=0;i<M;i++) {
			st = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			graph[a-1][b-1] = 1; 
			graph[b-1][a-1] = 1;
		}
		// DFS 결과 
		Arrays.fill(visited, false);
		dfs(V);
		sb.append("\n");

		// BFS 결과 
		Arrays.fill(visited, false);
		bfs();
		
		System.out.println(sb.toString());
		
	}
	private static void dfs(int curr) {
		if (visited[curr]) return; 
		visited[curr] = true; 
		sb.append(curr+1+" ");
		
		for (int next=0;next<N;next++) {
			if (visited[next]) continue; 
			if (graph[curr][next] == 1) {
				dfs(next);
			}
			
		}
	}
	
	private static void bfs() {
		Deque<Integer> queue = new ArrayDeque<>(); 
		queue.add(V);
		
		while(!queue.isEmpty()) {
			int curr = queue.poll();
			if (visited[curr]) continue;
			sb.append(curr+1+" ");
			visited[curr] = true;
			for (int next=0;next<N;next++) {
				if (visited[next]) continue; 
				if (graph[curr][next] == 1) {
					queue.add(next);
				}
			}
		}
	}
}
