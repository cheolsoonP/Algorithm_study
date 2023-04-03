import java.util.*;
import java.io.*;

public class Main {
	static int graph[][], time, result, n,m ;
	static boolean isChange, visited[][];
	static int newGraph[][];
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,1,-1};
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(in.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		graph = new int[n][m];
		newGraph = new int[n][m];
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(in.readLine());
			for(int j=0;j<m;j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
				newGraph[i][j] = graph[i][j];
			}
		}
		ArrayDeque<int[]> q = new ArrayDeque<>();
		while(true) {
			isChange = false;
			q.offer(new int[] {0,0});
			visited = new boolean[n][m];
			while(!q.isEmpty()) {
				int temp[] = q.pollFirst();
				int x=temp[0], y=temp[1];
				for(int i=0;i<4;i++) {
					int nx = x+dx[i];
					int ny = y+dy[i];
					if(nx < 0 || nx>=n || ny<0 || ny>=m) continue;
					if(visited[nx][ny]) continue;
					if(graph[nx][ny] == 1) {
						isChange = true;
						newGraph[nx][ny] = 0;
					}else {
						q.offer(new int[] {nx, ny});
					}
					visited[nx][ny] = true;
				}
			}
			
			if(!isChange) {
				System.out.println(time);
				System.out.println(result);
				return;
			}
			copyGraph();
			time++;
		}
	}
	
	private static void copyGraph() {
		result = 0;
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				if(graph[i][j]>0) result++;
				graph[i][j] = newGraph[i][j];
			}
		}
	}
}


