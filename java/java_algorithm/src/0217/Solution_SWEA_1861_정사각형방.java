import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_SWEA_1861_정사각형방 {
	static int n, graph[][], maxCnt, init;
	static int []dx = {-1, 1, 0, 0};// 상 하 좌 우
	static int []dy = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(in.readLine());

		for (int testCase = 1; testCase <= T; testCase++) {
			n = Integer.parseInt(in.readLine());
			graph = new int[n][n];
			maxCnt = 0;
			init = Integer.MAX_VALUE;
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(in.readLine());
				for (int j = 0; j < n; j++) {
					graph[i][j] = Integer.parseInt(st.nextToken());
				}
			}//----------input---------------
			
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					check(graph[i][j], i,j, 1);
				}
			}
			
			sb.append("#").append(testCase).append(" ");
			sb.append(init).append(" ").append(maxCnt).append("\n");
		}
		System.out.println(sb);
		in.close();		
	}//main

	private static void check(int start, int x, int y, int cnt) {
		// start-시작방 번호, (x,y)-현재 방 좌표, cnt-현재까지 지나온 방 개수
		if (cnt > maxCnt) {
			init = start;				
			maxCnt = cnt;//최대 방 이동개수	
		}else if(cnt == maxCnt) {
			init = Math.min(init, start);
		}

		int nx = 0;
		int ny = 0;
		
		for (int dir = 0; dir < 4; dir++) {
			nx = x+dx[dir];
			ny = y+dy[dir];
			if (nx < 0 || nx >= n || ny < 0 || ny >= n) // 범위초과 -> continue
				continue;
			if (graph[nx][ny] == graph[x][y]+1) {// 갈 수 있는 방, 계속 방문
				check(start, nx, ny, cnt+1);
			}
		}	
	}
}
