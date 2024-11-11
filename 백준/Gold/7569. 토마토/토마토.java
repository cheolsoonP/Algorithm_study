import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int M; 
	static int H; 
	// 상하좌우앞뒤 
	static int[] dx = {-1,1,0,0,0,0}; 
	static int[] dy = {0,0,-1,1,0,0}; 
	static int[] dz = {0,0,0,0,-1,1}; 
	
	static int[][][]graph; 
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 
		st = new StringTokenizer(in.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		graph = new int[H][N][M];
		boolean hasTomato = false; 
		Deque<int[]> queue = new ArrayDeque<>(); // 
		int[][][] visited = new int[H][N][M];
		for(int i=0;i<H;i++) {
			for(int j=0;j<N;j++) {
				Arrays.fill(visited[i][j], -1);				
			}
		}

		for (int i=0;i<H;i++) {
			for (int j=0;j<N;j++) {
				st = new StringTokenizer(in.readLine());
				for (int k=0;k<M;k++) {
					// 1: 익은 토마토, 0: 익지않은 토마토,  -1: 토마토 X 
					graph[i][j][k] = Integer.parseInt(st.nextToken());
					if (!hasTomato && graph[i][j][k] == 0) {
						hasTomato = true; 
					}
					if (graph[i][j][k] == 1) {
						queue.add(new int[] {i,j,k});
						visited[i][j][k] = 0;
					}
				}
			}
		}
		if (!hasTomato) {
			// 저장될 때부터 모든 토마토가 익어있는 상태이면 0을 출력
			System.out.println(0);
			return;
		}
		
		
		while(!queue.isEmpty()) {
			int[] temp = queue.poll();
			int x = temp[1]; 
			int y = temp[2];
			int z = temp[0];
			for (int dir=0;dir<6;dir++) {
				int nx = x+dx[dir];
				int ny = y+dy[dir];
				int nz = z+dz[dir]; 
				if (nx<0||nx>=N||ny<0||ny>=M||nz<0||nz>=H) continue;
				if (visited[nz][nx][ny] >= 0) continue;
				if (graph[nz][nx][ny] == 0) {
					visited[nz][nx][ny] = visited[z][x][y]+1;
					queue.add(new int[] {nz,nx,ny});
				}
			}
		}
		
		int time = -1; 
		for (int i=0;i<H;i++) {
			for (int j=0;j<N;j++) {
				for (int k=0;k<M;k++) {
					time = Math.max(visited[i][j][k], time);
					if (visited[i][j][k] >= 0) {
						graph[i][j][k] = 1;
					}
				}
			}
		}
		
		for (int i=0;i<H;i++) {
			for (int j=0;j<N;j++) {
				for (int k=0;k<M;k++) {
					if(graph[i][j][k] == 0) {
						time = -1; 
					}
				}
			}
		}
		
		System.out.println(time);	
	}

}
