import java.io.*; 
import java.util.*; 

public class Main {
	static int N; // 
	static int M; 
	static int[][] graph; 
    // 인접 행렬 
	// 방향 없는 그래프 
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null; 
		
		st = new StringTokenizer(in.readLine()); 
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new int[N][N];
		for (int i=0;i<M;i++) {
			// u, v 
			st = new StringTokenizer(in.readLine()); 
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			graph[u-1][v-1] = 1;
			graph[v-1][u-1] = 1;	
		}
		
		boolean[] visited = new boolean[N];
		int cnt = 0; 
		
		Deque<Integer> queue = new ArrayDeque<>(); 
		for (int i=0;i<N;i++) {
			if (visited[i]) continue;
			queue.add(i);
			visited[i] = true; 
			cnt++;
			while(!queue.isEmpty()) {
				int start = queue.pollFirst();
				for (int next=0;next<N;next++) {
					if (visited[next]) continue;
					if (graph[start][next] == 1) {
						queue.add(next);
						visited[next] = true;
					}
					//System.out.println(next);
				}
			}
		}
		
		System.out.println(cnt);
		
	}

}
