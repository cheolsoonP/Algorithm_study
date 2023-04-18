import java.io.*;
import java.util.*;

public class Main {
	static class Pos implements Comparable<Pos>{
		int x;
		int y;
		int dir;
		int cnt;
		
		public Pos(int x, int y, int dir,int cnt) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Pos o) {
			return this.cnt - o.cnt;
		}
	}
	static int N, start[] = {-1,-1};
	static char graph[][];
	static boolean visit[][][];
	
	// 상 우 하 좌 ,
	static int dx[] = {-1,0,1,0};
	static int dy[] = {0,1,0,-1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(in.readLine());
		visit = new boolean[N][N][4];
		graph = new char[N][N];
		for(int i=0;i<N;i++) {
			graph[i] = in.readLine().toCharArray();
			for(int j=0;j<N;j++) {
				if(graph[i][j] == '#' && start[0] == -1) {
					start[0] = i;
					start[1] = j;
				}
			}
		}
		/*
		 * 문에서 시작 - 직선 방향으로 이동
		 * 거울 만나면 90도 꺽이거나(좌우) 거울을 지나치거나,(꺾였다면 거울 개수 cnt++)
		 * 빛이 통과할 수 없는 곳이면 종료.
		 * 반복
		 * 새로운 문 만나면 종료(그동안 지나온 거울 개수 출력
		 * */
		PriorityQueue<Pos> pq = new PriorityQueue<>();
		graph[start[0]][start[1]] = '.';
		// 처음 거울에서 사방으로 직진탐색
		for(int i=0;i<4;i++) {
			int nx = start[0] + dx[i];
			int ny = start[1] + dy[i];
			if(nx<0||nx>=N||ny<0||ny>=N) continue;
			if(graph[nx][ny] == '*') continue;
			pq.offer(new Pos(start[0],start[1],i,0));
			visit[start[0]][start[1]][i] = true;
		}
		
		
		while(!pq.isEmpty()) {
			Pos temp = pq.poll();
			int x = temp.x;
			int y = temp.y;
			int dir = temp.dir;
			int cnt = temp.cnt;
			
			if(graph[x][y] == '#') {
				System.out.println(cnt);
				return;
			}
			visit[x][y][dir] = true;
			
			int nx = x+dx[dir];
			int ny = y+dy[dir];
			if(nx<0||nx>=N||ny<0||ny>=N) continue;
			if(graph[nx][ny] == '*') continue;
			if(visit[nx][ny][dir]) continue;
			
			if(graph[nx][ny] == '!') {
					pq.offer(new Pos(nx,ny,(dir+1)%4,cnt+1));
					pq.offer(new Pos(nx,ny,(dir+3)%4,cnt+1));
			}
			pq.offer(new Pos(nx,ny,dir,cnt));// 현재방향으로 가고	
		}	
	}
}
