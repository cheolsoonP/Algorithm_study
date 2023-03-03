import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_17144_미세먼지안녕 {

	/*
	 * 공기청정기 설치
	 * rxc
	 * 미세먼지양 실시간 모니터링
	 * 1번열에 설치
	 * 두행 차지,
	 * 
	 * 1. 미세먼지 확산, 모든 칸 동시에
	 * 		인접 상하좌우
	 * 		인접 공청기, 칸이 없으면 확산 x
	 * 		확산 양 - Arc /5 소수점버린다
	 * 		남은 미세먼지양 A-(a/5)*확산된방향수
	 * 2. 공청기 가동
	 * 		위칸쪽 공청기 - 반시계 방향 순환
	 * 		아래칸쪽 공청기 - 시계방향 순환
	 * 		미세먼지 - 바람 방향대로 한칸씩이동
	 * 		공청기로 들어간 미세먼지는 모두 정화
	 * */
//	public class Aircon {
//		int top, bottom;
//		
//		public Aircon(int top, int bottom) {
//			this.top = top;
//			this.bottom = bottom;
//		}
//	}
	static int[] aircon;
	static int R,C,T, res, graph[][];

	public static void main(String[] args) throws Exception {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		graph = new int [R][C];
		aircon = new int [2];//상 하 위치 
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < C; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
				if (graph[i][j] == -1 && aircon[0] == 0) {
					aircon[0] = i;
					aircon[1] = i+1;
					graph[i][j] = 0;
				}//공기청정기 위치 저장
			}
		}
		
		for (int i = 0; i < T; i++) {
			//1. 미세먼지 확산
			spread();
			//2. 공기 이동
			move();
		}
		//3.남은미세먼지계산
		count();
		System.out.println(res);
	}

	private static void spread() {
		int newGraph[][] = new int[R][C];
		// 상하좌우
		int dx[] = {-1,1,0,0};
		int dy[] = {0,0,-1,1};
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(graph[i][j] > 0) {
					int nx,ny,cnt=0;
					for (int d = 0; d < 4; d++) {
						nx=i+dx[d];
						ny=j+dy[d];
						if(nx>=0 && nx<R && ny>=0 && ny<C) {
							if(ny == 0 && (nx==aircon[0] || nx==aircon[1])) {//공청기있으면 확산X
								continue;
							}else {
								cnt++;
								newGraph[nx][ny] += (int)(graph[i][j] / 5);
							}
						}
					}
					newGraph[i][j] = newGraph[i][j] + graph[i][j] - cnt*((int)graph[i][j]/5);
				}
			}
		}//확산 완료
		for (int i = 0; i < R; i++) {
			graph[i] = Arrays.copyOf(newGraph[i], C);
		}
		
	}

	private static void move() {
		
		// 상 움직임
		for (int i = aircon[0]-1; i >= 0; i--) { // V
			graph[i+1][0] = graph[i][0];
		}
		graph[aircon[0]][0] = 0;
		for(int i=1; i<C; i++) { // <<--
			graph[0][i-1] = graph[0][i];
		}
		for(int i=1; i <= aircon[0]; i++) { // ^
			graph[i-1][C-1] = graph[i][C-1];
		}
		for(int i=C-2;i>=0;i--) {// -->
			graph[aircon[0]][i+1] = graph[aircon[0]][i];
		}		
		
		// 하 움직임
		for(int i=aircon[1]+1; i<R; i++) { // ^
			graph[i-1][0] = graph[i][0];
		}
		graph[aircon[1]][0] = 0;
		for(int i=1; i<C; i++) { // <<--
			graph[R-1][i-1] = graph[R-1][i];
		}
		for (int i = R-2; i >= aircon[1]; i--) { // V
			graph[i+1][C-1] = graph[i][C-1];
		}
		for(int i=C-2;i>=0;i--) {// -->
			graph[aircon[1]][i+1] = graph[aircon[1]][i];
		}
		
	}

	private static void count() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				res += graph[i][j];
			}
		}
	}

}
