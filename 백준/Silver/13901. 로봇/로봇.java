import java.io.*;
import java.util.*;

public class Main {

	static int N, M, K;
	static int graph[][];
	static int cx, cy;
	static boolean visit[][];
	static int command[];
	static int dx[] = {-1, 1, 0, 0};
	static int dy[] = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader in =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new int[N][M];
		visit = new boolean[N][M];
		
		K = Integer.parseInt(in.readLine());
		for(int i=0;i<K;i++) {
			st = new StringTokenizer(in.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			graph[x][y] = -1; // 장애물 설치
		}
		
		st = new StringTokenizer(in.readLine());
		cx = Integer.parseInt(st.nextToken());
		cy = Integer.parseInt(st.nextToken());
		visit[cx][cy] = true;

		st = new StringTokenizer(in.readLine());
		command = new int[4]; 
		for(int i=0;i<4;i++) {
			command[i] = Integer.parseInt(st.nextToken()) - 1;
		}	
		int count=0;
		int dir = 0;
		while(true) {
			int nx = cx+dx[command[dir]];
			int ny = cy+dy[command[dir]];

			if(count == 4) {
				break;
			}
			
			if(nx<0 || nx>=N || ny<0 || ny>=M 
					|| visit[nx][ny] 
					|| graph[nx][ny] == -1) {
				dir = (dir+1)%4;
				count+=1;
			}else {		
				cx = nx;
				cy = ny;
				visit[nx][ny] = true;
				count = 0;
			}
			
		}
		
		System.out.println(cx+" "+cy);
	}
}
