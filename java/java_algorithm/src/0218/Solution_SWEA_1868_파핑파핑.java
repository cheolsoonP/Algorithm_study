import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Solution_SWEA_1868_파핑파핑 {
	static int N;
	static char[][] graph;
	
	static boolean[][] visited;
	static int[][] ansGraph;
	static int totalCnt;
				// 우, 우하, 하, 좌하, 좌, 좌상, 상, 우상
	static int []dx = {0, 1, 1, 1, 0, -1, -1, -1};
	static int []dy = {1, 1, 0, -1 ,-1, -1, 0, 1};
	
	static Deque<Bomb> q = new ArrayDeque<>();
	static class Bomb {
		int x;
		int y;
		public Bomb(int x, int y) {
			this.x=x;
			this.y=y;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(in.readLine());
		
		for (int testCase = 1; testCase <= T; testCase++) {
			N = Integer.parseInt(in.readLine());
			graph = new char[N][N];
			ansGraph= new int[N][N];
			visited = new boolean[N][N];
			totalCnt = 0;
			for (int i = 0; i < N; i++) {
				graph[i] = in.readLine().toCharArray();
			}
			// ---------input---------------
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (visited[i][j] == true) continue;
					if (graph[i][j] != '.') {
						continue;
					}
					// 지뢰가 아닐 때
					int cnt=0;
					for (int k = 0; k < 8; k++) {
						int nx = i+dx[k];
						int ny = j+dy[k];
						if (isRange(nx, ny) && graph[nx][ny] == '*') {
							cnt+=1;
						}
					}
					if (cnt<=0) {
						q.add(new Bomb(i,j));
						bfs();
						totalCnt+=1;
					}
				}
			}
//			printAnsGraph();
			System.out.println(totalCnt);
		}
	}
	
	static boolean isRange(int x, int y) {
		if(x<0|| x>=N || y<0 || y>=N) 
			return false;
		return true;
	}
	
	private static void bfs() {
		// 주변 탐색
		while(!q.isEmpty()) {
			Bomb b = q.pollFirst();
			int cnt = 0;
			for (int k = 0; k < 8; k++) {
				int nx = b.x+dx[k];
				int ny = b.y+dy[k];
				if (isRange(nx, ny) && graph[nx][ny] == '*') {
					cnt+=1;
				}
			}
			
			if (cnt<=0) { // 주변에 지뢰가 없으면
				for (int k = 0; k < 8; k++) {
					int nx = b.x+dx[k];
					int ny = b.y+dy[k];
					if (!isRange(nx, ny) || graph[nx][ny] != '.'|| visited[nx][ny]) {
						continue;
					}
					visited[nx][ny] = true;
					q.add(new Bomb(nx, ny));
				}
			}else {
				graph[b.x][b.y] = (char)cnt;
			}
			
		}
	}

}
