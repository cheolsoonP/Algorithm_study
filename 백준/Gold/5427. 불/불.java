import java.util.*;
import java.io.*;
 
public class Main {
	static int N; 
	static int M; 
	static char[][] graph;
	// 상하좌우 
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static boolean[][] isFire; 
	static int[][] visited; 
	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(in.readLine());
		
		for (int tc=1;tc<=T;tc++) {
			st = new StringTokenizer(in.readLine()); 
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			graph = new char[N][M]; 
			isFire = new boolean[N][M]; 
			visited = new int[N][M]; 
			Deque<int[]> fireQueue = new ArrayDeque<>(); 
			Deque<int[]> moveQueue = new ArrayDeque<>(); 
			
			for (int i=0;i<N;i++) {
				Arrays.fill(visited[i], -1);
			}
			for (int i=0;i<N;i++) {
				String str = in.readLine(); 
				for (int j=0;j<M;j++) {
					graph[i][j] = str.charAt(j);
					if (graph[i][j] == '*') {
						fireQueue.add(new int[] {i,j});
						isFire[i][j] = true;
					}
					if (graph[i][j] == '@') {
						moveQueue.add(new int[] {i,j,0});
						visited[i][j] = 0;
					}
				}
			}

			boolean isPossible = false; 
			while (!isPossible) {
				// 불 이동 
				int fireCnt = fireQueue.size(); 
				for (int i=0;i<fireCnt;i++) {
					int[] firePos = fireQueue.poll();
					for (int dir=0;dir<4;dir++) {
						int nx = firePos[0] + dx[dir];
						int ny = firePos[1] + dy[dir]; 
						if (nx<0 || nx >= N || ny<0 || ny>=M) continue; 
						if (graph[nx][ny] == '#') continue; 
						if (isFire[nx][ny]) continue; 
						isFire[nx][ny] = true; 
						fireQueue.add(new int[] {nx,ny});
					}
				}
				// 사람 이동 
				int moveCnt = moveQueue.size(); 
				for (int i=0;i<moveCnt;i++) {
					if (isPossible) break;
					
					int[] movePos = moveQueue.poll(); 
					for (int dir=0;dir<4;dir++) {
						int nx = movePos[0] + dx[dir];
						int ny = movePos[1] + dy[dir]; 
						if (nx<0 || nx >= N || ny<0 || ny>=M) {
							sb.append(movePos[2]+1+"\n");
							isPossible = true; 
							break;
						}
						if (graph[nx][ny] == '#') continue; 
						if (isFire[nx][ny]) continue; 
						if (visited[nx][ny] >= 0) continue; 
						visited[nx][ny] = movePos[2] + 1;
						moveQueue.add(new int[] {nx,ny, movePos[2]+1});
					}
				}
				if (isPossible) break;
				
				if (fireQueue.size() == 0 && moveQueue.size() == 0) {
					break;
				}
			}
			if (!isPossible) sb.append("IMPOSSIBLE\n");
		}
		System.out.println(sb.toString());
		
	}
}
