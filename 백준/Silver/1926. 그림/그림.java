import java.io.*;
import java.util.*;

public class Main {
	static int N,M; 
	static int[][] graph;
	static int[][] visited; 
	static Deque<int []> deque;
	// 상하좌우 
	static int[] dx = {0,0,-1,1};
	static int[] dy = {-1,1,0,0};
	
	static int MAX_CNT = 0;
	static int ART_CNT = 0;
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		st = new StringTokenizer(in.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new int[N][M];
		visited = new int[N][M];
		deque = new ArrayDeque<>();
		
		for (int i=0;i<N;i++) {
			st = new StringTokenizer(in.readLine());
			for (int j=0;j<M;j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i=0;i<N;i++) {
			for (int j=0;j<M;j++) {
				if (visited[i][j] == 0 && graph[i][j] == 1) {
					deque.add(new int[] {i,j});
					play();
					ART_CNT++;
				}				
			}
		}
		
		System.out.println(ART_CNT);
		System.out.println(MAX_CNT);
	}
	private static void play() {
		int cnt = 0;
		while(!deque.isEmpty()) {
			int[] pos = deque.poll();
			if (visited[pos[0]][pos[1]] != 0) continue;
			cnt++;
			visited[pos[0]][pos[1]] = 1;
			// 상하좌우 방문
			for (int dir=0;dir<4;dir++) {
				int nx = pos[0]+dx[dir];
				int ny = pos[1]+dy[dir];
				if (nx<0 || nx>=N || ny<0 || ny>=M) continue;
				if (visited[nx][ny] == 0 && graph[nx][ny] == 1) {
					deque.add(new int[] {nx,ny});
				}
			}
		}
		if (cnt > MAX_CNT) {
			MAX_CNT = cnt;
		}
	}
}
