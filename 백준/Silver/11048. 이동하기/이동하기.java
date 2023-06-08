import java.io.*;
import java.util.*;

public class Main {
	static int N, M, graph[][], dp[][];
	// 우 하 우하
	static int dx[] = {0,1,1};
	static int dy[] = {1,0,1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		graph = new int[N][M];
		dp = new int[N+1][M+1];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(in.readLine());
			for(int j=0;j<M;j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dp[0][0] = graph[0][0];
		Deque<int[]> q = new ArrayDeque<>();
		q.add(new int[] {0,0});
		boolean visit[][];
		visit = new boolean[N][M];
		
		while(!q.isEmpty()) {
			int x = q.peekFirst()[0];
			int y = q.pollFirst()[1];
			for(int i=0;i<2;i++) {
				int nx = x+dx[i];
				int ny = y+dy[i];
				if(nx<0||nx>=N||ny<0||ny>=M) continue;
				if(dp[nx][ny] < dp[x][y] + graph[nx][ny]) {
					dp[nx][ny] = dp[x][y]+graph[nx][ny];
					q.add(new int[] {nx,ny});					
				}else if(dp[nx][ny] == dp[x][y] + graph[nx][ny]) {
					if(!visit[nx][ny]) {
						visit[nx][ny] = true;
						q.add(new int[] {nx,ny});
					}
				}
			}
		}
		
		System.out.println(dp[N-1][M-1]);
	}
}
