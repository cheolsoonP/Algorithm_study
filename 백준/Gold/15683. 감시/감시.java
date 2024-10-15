import java.io.*;
import java.util.*;

public class Main {
	static int N, M; 
	static int[][] originalMap, tempMap;
	static List<int[]> cam;
	
	// 상우하좌
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};
	static int[] dir;
	static int minCnt = Integer.MAX_VALUE;
	
	
	public static void main(String[] args) throws Exception {
		/* N * M 사무실 
		 * K개 CCTV 
		 * CCTV는 5가지 종류
		 * 1방향, 좌우, 상좌, 좌상우, 상하좌우 
		 * 해당 방향 칸 전체 감시 가능 
		 * CCTV는 벽 통과 불가능 
		 * 0은 빈 칸, 6은 벽, 1~5는 CCTV의 번호
		 * 사무실의 크기와 상태, 
		 * 그리고 CCTV의 정보가 주어졌을 때, 
		 * CCTV의 방향을 적절히 정해서, 
		 * 사각 지대의 최소 크기를 구하는 프로그램을 작성하시오.
		 * 
		 * 1번 -> 1방향으로만 봄, 4방향 회전 가능 
		 * 2번 -> 상하 or 좌우 
		 * 3번 -> 좌상, 상우, 우하, 하좌 
		 * 4번 -> 좌상우, 상우하, 우하좌, 하좌상
		 * 5번 -> 상하좌우
		*/
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = null;
		st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		tempMap = new int[N][M];
		originalMap = new int[N][M];
		cam = new ArrayList<>();

		for (int i=0;i<N;i++) {
			st = new StringTokenizer(in.readLine());
			for (int j=0;j<M;j++) {
				originalMap[i][j] = Integer.parseInt(st.nextToken());
				if (originalMap[i][j] >= 1 && originalMap[i][j] <= 5) {
					cam.add(new int[] {i,j,originalMap[i][j]});
				}
			}
		}
		dir = new int[cam.size()];
		
		dfs(0);
		
		System.out.println(minCnt);
	}

	private static void dfs(int idx) {
		if (idx == cam.size()) {
//			System.out.println("cam : "+cam.size());
//			System.out.println("idx : "+idx);
//
//			System.out.println("dir[idx] :"+Arrays.toString(dir));
//			
			simulation();
			count();
			return;
		}
		
		for (int i=0;i<4;i++) {
			dir[idx] = i;
			dfs(idx+1);
		}
	}

	private static void count() {
		int cnt = 0;
		for (int i=0;i<N;i++) {
			for (int j=0;j<M;j++) {
				if(tempMap[i][j] == 0) {
					cnt++;
				}
			}
		}
		if (minCnt > cnt) {
			minCnt = cnt;
		}
	}

	private static void simulation() {
		copyMap();
		for (int i=0;i<cam.size();i++) {
			int[] temp = cam.get(i);
			int x = temp[0];
			int y = temp[1];
			int camNum = temp[2];
			
			if (camNum == 1) {
				spread(x,y,dir[i]);
			} else if (camNum == 2) {
				if (dir[i] <= 1) {
					// 상하
					spread(x,y,0);
					spread(x,y,2);
				} else {
					// 좌우
					spread(x,y,1);
					spread(x,y,3);
				}
			} else if (camNum == 3) {
				spread(x,y,dir[i]);
				spread(x,y,dir[i]+1);
			} else if (camNum == 4) {
				spread(x,y,dir[i]);
				spread(x,y,dir[i]+1);
				spread(x,y,dir[i]+2);
			} else if (camNum == 5) {
				spread(x,y,dir[i]);
				spread(x,y,dir[i]+1);
				spread(x,y,dir[i]+2);
				spread(x,y,dir[i]+3);
			}
		}
	}
	
	private static void spread(int x, int y, int dir) {
		dir = dir%4;
		
		Deque<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] {x,y});
		
		while (!queue.isEmpty()) {
			int[] pos = queue.pollFirst();
			int nx,ny;
			nx = pos[0]+dx[dir];
			ny = pos[1]+dy[dir];
			if (nx<0||nx>=N||ny<0||ny>=M) continue;
			if (tempMap[nx][ny] == 6) continue;
			queue.add(new int[] {nx,ny});
			tempMap[nx][ny] = 7; // 감시하는 곳 			
		}
	}

	private static void copyMap() {
		// TODO Auto-generated method stub
		for (int i=0;i<N;i++) {
			for (int j=0;j<M;j++) {
				tempMap[i][j] = originalMap[i][j];
			}
		}
	}
}
