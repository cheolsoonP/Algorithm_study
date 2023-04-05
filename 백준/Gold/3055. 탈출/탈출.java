import java.util.*;
import java.io.*;

public class Main {
	static int N, M, board[][];
	static char graph[][];
	static boolean visited[][], waterVisited[][];	
	static ArrayDeque<int[]> waterQ, q;
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new char[N][M];
		board = new int[N][M];
		visited = new boolean[N][M];
		waterVisited = new boolean[N][M];
		waterQ = new ArrayDeque<>();
		q = new ArrayDeque<>();
		for(int i=0;i<N;i++) {
			graph[i] = in.readLine().toCharArray();
			for(int j=0;j<M;j++) {
				if(graph[i][j] == 'S') {
					visited[i][j] = true;
					q.offer(new int [] {i,j});
					graph[i][j] = '.';
				}
				if(graph[i][j] == '*') {
					waterVisited[i][j] = true;
					waterQ.offer(new int[] {i,j});
				}
			}
		}
		// 고슴도치 -> 비버집 이동 -> 종료
		int time = -1;
		while(!q.isEmpty()) {
			int temp[] = q.pollFirst();
			int x=temp[0],y=temp[1];
			
			// 고슴도치 -> 비버집 이동완료 (종료)
			if(graph[x][y] == 'D') {
				System.out.println(board[x][y]);
				return;
			}
			
			// 방문 차수가 달라지면 물 이동
			if(time < board[x][y]) {
				time = board[x][y];
				moveWater();
			}			

			// 고슴도치 이동
			int nx,ny;
			for(int i=0;i<4;i++) {
				nx=x+dx[i];ny=y+dy[i];
				if(nx<0||nx>=N||ny<0||ny>=M) continue;
				if(graph[nx][ny] == '*' || graph[nx][ny] == 'X') continue;
				if(visited[nx][ny]) continue;
				visited[nx][ny] = true;
				board[nx][ny] = board[x][y] + 1;
				q.offer(new int[] {nx, ny});					
			}
		}
		System.out.println("KAKTUS");
	}
	
	private static void moveWater() {
		// 물 이동
		int waterSize = waterQ.size();
		for(int w=0;w<waterSize;w++) {
			int waterTemp[] = waterQ.pollFirst();
			int wx=waterTemp[0],wy=waterTemp[1];
			int nx,ny;
			for(int i=0;i<4;i++) {
				nx=wx+dx[i];ny=wy+dy[i];
				if(nx<0||nx>=N||ny<0||ny>=M) continue;
				if(waterVisited[nx][ny]) continue;
				if(graph[nx][ny] == 'D' || graph[nx][ny] == 'X') continue;
				if(graph[nx][ny] == '*') continue;
				if(graph[nx][ny] == '.') {
					graph[nx][ny] = '*';
					waterVisited[nx][ny] = true;
					waterQ.offer(new int[] {nx,ny});
				}
			}
		}
	}

}
