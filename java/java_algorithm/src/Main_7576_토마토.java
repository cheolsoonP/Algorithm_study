import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_7576_토마토 {

	/*
	 * 토마토농장
	 * n m 
	 * 보관후 하루 - 익은 토마토 인접 -> 안익은 토마토 익음 상하좌우
	 * 최소일수
	 * */
	static int N,M, graph[][], maxCnt,cnt, time;
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		graph = new int[N][M];
		Queue<int[]> queue = new LinkedList<>();//익은 토마토의 정보 저장
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < M; j++) {
				// 익은 토마토 1, 익지않은 토마토 0, -1 토마토 없음
				graph[i][j] = Integer.parseInt(st.nextToken());
				if(graph[i][j] == 0) maxCnt++;
				if(graph[i][j]==1) //익은 토마토
					  queue.add(new int[] {i,j});
			}
		}
		
		while(!queue.isEmpty()) {
			if(cnt==maxCnt) break;

			int[] p = queue.poll();
			int x = p[0];
			int y = p[1];

			for(int i=0; i<4; i++) {
				int nx = x+dx[i];
				int ny = y+dy[i];
				
				if(nx < 0 || nx >= N || ny < 0 || ny >= M)
					continue;
				if( graph[nx][ny] == 0) {// 익지 않은 토마토 
					graph[nx][ny] = graph[x][y] + 1;
					queue.offer(new int[] {nx,ny});
					cnt++;
				}
			}
		}
		if(!check()) {
			System.out.println(-1);
		}else {
			System.out.println(time-1);
		}
	}

	private static boolean check() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(graph[i][j] == 0) {
					return false;
				}
				if(graph[i][j] >= 1) {
					time = Math.max(time, graph[i][j]);
				}
			}
		}
		return true;
	}


}
