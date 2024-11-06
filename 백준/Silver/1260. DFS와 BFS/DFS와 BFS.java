import java.util.*; 
import java.io.*;

public class Main {
	static int N; 
	static int M; 
	static int V; 
	static StringBuilder sb = new StringBuilder(); 

	static List<Integer>[] graph; // 인접리스트 
	static boolean[] visited; 
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 
		
		st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken())-1;
		graph = new ArrayList[1001];
		for (int i=0;i<=1000;i++) {
			graph[i] = new ArrayList<Integer>();
		}
		visited = new boolean[1001]; 
		for (int i=0;i<M;i++) {
			st = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			graph[a-1].add(b-1);
			graph[b-1].add(a-1);	
		}
		for (int i=0;i<N;i++) {
			Collections.sort(graph[i]);
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
		
		for (int next : graph[curr]) {
			if (visited[next]) continue;
			dfs(next);
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
			for (int next : graph[curr]) {
				if (visited[next]) continue;
				queue.add(next);
			}
		}
	}
}
