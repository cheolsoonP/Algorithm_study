import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][]W;
	static int N;
	static int minCost;
	static boolean visited[];
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(in.readLine());
		W = new int[N][N];
		visited = new boolean[N];
		minCost = Integer.MAX_VALUE;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < N; j++) {
				W[i][j] = Integer.parseInt(st.nextToken());				
			}
		}
		
		for (int i = 0; i < N; i++) {
			visited[i] = true;
			dfs(i, i, 0, 0);
			visited[i] = false;
		}
		System.out.println(minCost);	
	}
	
	private static void dfs(int start, int from, int cost, int cnt) {
		if(cnt == N-1) {
			if(W[from][start] > 0) {
				minCost = Math.min(minCost, cost+W[from][start]);				
			}
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if(W[from][i] <= 0) continue;
			if(visited[i]) continue;
			visited[i] = true;
			dfs(start, i, cost+W[from][i], cnt+1);
			visited[i] = false;
		}
	}
}
