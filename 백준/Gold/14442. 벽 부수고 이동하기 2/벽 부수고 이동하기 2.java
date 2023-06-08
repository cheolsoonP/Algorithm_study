import java.util.*;
import java.io.*;

public class Main {

	static int N, M, K, graph[][];
	static boolean visit[][][];
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	static boolean isPossible;
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		graph = new int[N][M];
		visit = new boolean[N][M][K+1];// K개 벽을 부수고 이동
		for(int i=0;i<N;i++) {
			char[] temp = in.readLine().toCharArray();
			for(int j=0;j<M;j++) {
				graph[i][j] = (temp[j] - '0');
			}
		}
		
		Deque<int[]> q = new ArrayDeque<int[]>();
		q.offer(new int[] {0,0,1,0});// x,y,move,count(벽부순개수)
		isPossible = false;
		while(!q.isEmpty()) {
			int x = q.peekFirst()[0];
			int y = q.peekFirst()[1];
			int move = q.peekFirst()[2];
			int count = q.pollFirst()[3];
			if(x == N-1 && y == M-1) {
				System.out.println(move);
				isPossible = true;
				break;
			}
			for(int i=0;i<4;i++) {
				int nx = x+dx[i];
				int ny = y+dy[i];
				if(nx<0 || nx>=N || ny<0 || ny>= M) continue;
				if(visit[nx][ny][count]) continue;
				if(graph[nx][ny] == 1 && count >= K) continue;
				if(graph[nx][ny] == 1 && count < K && !visit[nx][ny][count]) {
					// 벽, 부술 수 있는 횟수가 남아있고, 방문하지 않았다면 
					visit[nx][ny][count] = true;
					q.offer(new int[] {nx,ny,move+1,count+1});
				}
				if(graph[nx][ny] == 0) {
					visit[nx][ny][count] = true;
					q.offer(new int[] {nx,ny,move+1,count});
				}
			}
		}
		
		if(!isPossible) {
			System.out.println(-1);
		}
		
	}
}
