import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_7576_토마토 {

	/*
	 * 토마토농장
	 * n m 
	 * 보관후 하루 - 익은 토마토 인접 -> 안익은 토마토 익음 상하좌우
	 * 최소일수
	 * */
	static int N,M, graph[][], maxCnt,cnt, time;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		graph = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < M; j++) {
				// 익은 토마토 1, 익지않은 토마토 0, -1 토마토 없음
				graph[i][j] = Integer.parseInt(st.nextToken());
				if(graph[i][j] == 0) maxCnt++;
			}
		}
		
		while(true) {
			if(cnt==maxCnt) break;
//			if(check()) break;
			// 토마토 확산
			if(!spread()) {// 변한 토마토가 없다면
				time=-1;
				break;
			};
			time++;
		}
		System.out.println(time);
	}

	private static boolean check() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(graph[i][j] == 0) {
					return false;
				}
			}
		}
		return true;
	}

	private static boolean spread() {
		int dx[] = {-1,1,0,0};
		int dy[] = {0,0,-1,1};
		int newGraph[][] = new int[N][M];
		boolean isChange = false;
		for (int i = 0; i < N; i++) {
			newGraph[i] = Arrays.copyOf(graph[i], graph[i].length);
		}
		
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(graph[i][j] == 1) {//익은토마토면 확산
					int nx,ny;
					for (int d = 0; d < 4; d++) {
						nx = i+dx[d];
						ny = j+dy[d];
						if(nx>=0 && nx<N && ny>=0 && ny<M) {
							if(newGraph[nx][ny] == 0) {
								newGraph[nx][ny] = 1;
								isChange=true;
								cnt++;
							}
						}
					}
				}
			}
		}
		
		for (int i = 0; i < N; i++) {
			graph[i] = Arrays.copyOf(newGraph[i], newGraph[i].length);
		}
		return isChange;
	}

}
