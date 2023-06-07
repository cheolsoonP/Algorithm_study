import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static int cx, cy;
	static int ex, ey;
	static int graph[][];
	static int minDist;
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(in.readLine());
		cx = Integer.parseInt(st.nextToken())-1;
		cy = Integer.parseInt(st.nextToken())-1;
		
		st = new StringTokenizer(in.readLine());
		ex = Integer.parseInt(st.nextToken())-1;
		ey = Integer.parseInt(st.nextToken())-1;
		
		graph = new int[N][M];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(in.readLine());
			for(int j=0;j<M;j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		minDist = -1;

		play();

		System.out.println(minDist);
	}

	private static void play() {
		boolean visit[][][] = new boolean[N][M][2];
		Deque<int[]> q = new ArrayDeque<int[]>();
		q.add(new int[] {cx,cy, 0, 0});//x, y, time, count
		visit[cx][cy][1] = true;
		
		while(!q.isEmpty()) {
			int temp[] = q.pollFirst();
			int x = temp[0];
			int y = temp[1];
			int time = temp[2];
			int count = temp[3];
			
			if(x == ex && y == ey) {
				minDist = time;
				return;
			}
			
			for(int i=0;i<4;i++) {
				int nx = x+dx[i];
				int ny = y+dy[i];
				if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
				if(visit[nx][ny][count]) continue;
				if(count == 1 && graph[nx][ny] == 1) continue;
				if(count == 0 && !visit[nx][ny][1] && graph[nx][ny] == 1) {
					visit[nx][ny][1] = true;
					q.add(new int[] {nx,ny,time+1,count+1});
				}
				if(!visit[nx][ny][count] && graph[nx][ny] == 0){
					visit[nx][ny][count] = true;
					q.add(new int[] {nx,ny,time+1,count});					
				}
			}
		}
		
	}
}
