import java.io.*;
import java.util.*;
 
public class Main {
	static int N;
	static int M; 
	static int graph[][]; 
	static boolean visited[][][]; 
	
	// 상 하 좌 우 
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	/*
	 * 1 벽 
	 * 벽 최대 한 개 부술 수 있음 
	 * */
	
	static int MIN_CNT = Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 
		st = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new int [N][M];
		
		for (int i=0;i<N;i++) {
			String arr = in.readLine();
			for (int j=0;j<M;j++) {
				graph[i][j] = arr.charAt(j) - '0';
			}
		}
		
		Deque<int[]> deque = new ArrayDeque<>(); 
		deque.add(new int[] {0,0,1,0}); // x,y,count, 부순벽 수 
		visited = new boolean[N][M][2]; // 벽 부수고 이동한 
		visited[0][0][0] = true; 
		while(!deque.isEmpty()) {
			int[] temp = deque.pollFirst();
			int x = temp[0];
			int y = temp[1];
			int count = temp[2];
			int broken = temp[3];

			if (x == N-1 && y == M-1) {
				MIN_CNT = count;
				break;
			}
			
			for (int dir=0;dir<4;dir++) {
				int nx = x+dx[dir];
				int ny = y+dy[dir];
				
				if (nx<0||nx>N-1||ny<0||ny>M-1) continue;
				
				if (graph[nx][ny] == 1) {
					if (broken == 1) continue;
					if (visited[nx][ny][broken+1]) continue;
					
					deque.add(new int[] {nx,ny,count+1,broken+1});
					visited[nx][ny][broken+1] = true;
				} else {
					if (visited[nx][ny][broken]) continue;
					deque.add(new int[] {nx,ny,count+1,broken});
					visited[nx][ny][broken] = true;
				}
			}
		}
		if (MIN_CNT == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(MIN_CNT);			
		}
	}
}
