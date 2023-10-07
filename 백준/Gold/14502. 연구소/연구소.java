import java.io.*;
import java.util.*;

public class Main {
	
	/*
	 * 바이러스 확산 막으려고 벽 세운다 
	 * NxM 
	 * 바이러스 상하좌우 확산
	 * 새로 세울 수 있는 벽 수 3개 
	 * 0-빈칸 1-벽, 2-바이러스
	 * */
	// 상하좌우
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	static int map[][];
	
	static int N,M;
	static int result;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		result = 0;
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(in.readLine());
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 1. 벽 세우기(모든 경우 벽 세워보기) 
		dfs(3, 0, 0);
		
		System.out.println(result);
	}

	private static void dfs(int blockCnt, int x, int y) {
		if(blockCnt == 0) {
			int tempMap[][] = new int[N][M];
			
			// 2. 확산 시켜보기, 안전 지역 찾기
			Deque<int[]> q = new ArrayDeque<int[]>();
			for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) {
					if(map[i][j] == 1) {
						tempMap[i][j] = 1;
					}
					if(map[i][j] == 2) {
						q.add(new int[] {i,j});
						tempMap[i][j] = 2;
					}
				}
			}
			
			while(!q.isEmpty()) {
				int cx = q.peekFirst()[0];
				int cy = q.pollFirst()[1];
				for(int dir=0;dir<4;dir++) {
					int nx = cx+dx[dir];
					int ny = cy+dy[dir];
					
					if(nx<0||nx>=N||ny<0||ny>=M) continue;
					if(tempMap[nx][ny] == 0) {
						q.offer(new int[] {nx,ny});
						tempMap[nx][ny] = 2;
					}
				}
			}
			
			// 안전 지역 개수 확인
			int cnt = 0;
			for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) {
					if(tempMap[i][j] == 0) {
						cnt++;
					}
				}
			}
			result = Math.max(result, cnt);
			return;
		}
		
		// 벽 세우기
		for(int i=0;i<N;i++) {
			for(int j=y;j<M;j++) {
				if(map[i][j] == 0) {
					map[i][j] = 1;
					dfs(blockCnt-1, i, j);
					map[i][j] = 0;
				}
			}
		}
	}

}
