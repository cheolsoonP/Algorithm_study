import java.io.*;
import java.util.*;

public class Main {

	static int MAX_MOVE = 1_000_000;
	static int T;
	static int N, M, graph[][];
	// 상하좌우
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	static int answer, total;
	static boolean visit[][];

	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		String input = null;		
		while((input = in.readLine()) != null) {
			T++;
		
			st = new StringTokenizer(input);
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			graph = new int[N][M];
			visit = new boolean[N][M];
			
			answer = MAX_MOVE;
			total = 0;
			
			for(int i=0;i<N;i++) {
				char[] temp = in.readLine().toCharArray();
				for(int j=0;j<M;j++) {
					graph[i][j] = temp[j];
					if(graph[i][j] == '.') total++;
				}
			}


			for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) {
					if(graph[i][j] == '.') {
						visit[i][j] = true;
						dfs(i, j, 0, 1);
						visit[i][j] = false;
					}
				}
			}
			if(answer != MAX_MOVE) {
				System.out.println("Case "+T+": "+answer);				
			}else {
				System.out.println("Case "+T+": "+"-1");				
			}
		}		
	}

	private static void dfs(int startX, int startY, int depth, int moves) {
		if(moves == total) {
			answer = Math.min(answer, depth);
			return;
		}
		
		for(int dir=0;dir<4;dir++) {
			int cnt = 0;
			int x = startX;
			int y = startY;
			int nx, ny;
			while(true) {
				nx = x+dx[dir];
				ny = y+dy[dir];
				
				if(nx<0 || nx>=N || ny<0 || ny>=M
						|| graph[nx][ny] == '*'
						|| visit[nx][ny]) {
					break;
				}
				
				visit[nx][ny] = true;
				cnt++;
				x = nx;
				y = ny;
			}
			
			if(x == startX && y == startY) continue;
			dfs(x, y, depth+1, moves+cnt);
			
			// 지나온 길 초기화
			for(int i=0;i<cnt; i++) {
				visit[x][y] = false;
				x = x-dx[dir];
				y = y-dy[dir];
			}
		}
		
		
	}

}
