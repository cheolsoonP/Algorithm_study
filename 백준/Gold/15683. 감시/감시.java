import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int N,M,res, graph[][], newGraph[][];
	// 상 우 하 좌
	static int dx[] = {-1, 0, 1, 0};
	static int dy[] = {0, 1, 0, -1};
	static List<Integer[]> camera;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new  StringBuilder();
		StringTokenizer st = null;
		
		st = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		camera = new ArrayList<>(); //카메라 1번~5번 담는다. 
		graph = new int[N][M];
		res = Integer.MAX_VALUE;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < M; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
				if (graph[i][j] >= 1 && graph[i][j] <= 5) {
					camera.add(new Integer[] {graph[i][j], i, j});
				} 
			}
		}
				
		if (camera.size() > 0) dfs(0, new ArrayList<>());
		else {
			int cnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (graph[i][j] == 0) {
						cnt++;
					}
				}
			}
			res = cnt;
		}
		System.out.println(res);
	}
	
	// look : dir, x,y 을 담음 -> 감시 방향 위치 리스트
	// DFS 각 카메라별 가능한 방향 설정 후 다음 카메라로 넘김 
	private static void dfs(int idx, List<Integer[]> look) { // 카메라 번호, 
		if (idx == camera.size()) {
			newGraph = new int[N][M];
			// 복사			
			for (int i = 0; i < N; i++) {
				newGraph[i] = Arrays.copyOf(graph[i], M);				
			}
						
			// 방향들 감시			
			for (int i = 0; i < look.size(); i++) {
				Integer[] tmp = look.get(i);
				bfs(tmp[0], tmp[1], tmp[2]);
			}
			// 사각지대 계산
			int resTmp = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (newGraph[i][j] == 0) { // 사각지대
						resTmp++;
					}
				}
			}
			
			
			res = Math.min(res, resTmp);
			return;
		}
		

		Integer[] temp = camera.get(idx);
		int camType = temp[0];
		int x = temp[1];
		int y = temp[2];
		
		switch (camType) {
		case 1: // 한방향
			for (int i = 0; i < 4; i++) {
				look.add(new Integer[] {i,x,y});
				dfs(idx+1, look);
				look.remove(look.size()-1);
			}
			break;
		case 2: // 양방향 서로반대
			for (int i = 0; i < 2; i++) {
				look.add(new Integer[] {i,x,y});
				look.add(new Integer[] {i+2,x,y});
				dfs(idx+1, look);
				look.remove(look.size()-1);
				look.remove(look.size()-1);				
			}
			break;
		case 3: // 양방향 서로 직각 - 4방향
			for (int i = 0; i < 4; i++) {
				look.add(new Integer[] {i,x,y});
				look.add(new Integer[] {(i+1)%4,x,y});
				dfs(idx+1, look);
				look.remove(look.size()-1);
				look.remove(look.size()-1);
			}
			break;
		case 4: // 3방향 - > 4방향
			for (int i = 0; i < 4; i++) {
				look.add(new Integer[] {i,x,y});
				look.add(new Integer[] {(i+1)%4,x,y});
				look.add(new Integer[] {(i+2)%4,x,y});
				dfs(idx+1, look);
				look.remove(look.size()-1);
				look.remove(look.size()-1);
				look.remove(look.size()-1);
			}
			break;
		case 5: // 4방향
			look.add(new Integer[] {0,x,y});
			look.add(new Integer[] {1,x,y});
			look.add(new Integer[] {2,x,y});
			look.add(new Integer[] {3,x,y});
			dfs(idx+1, look);
			look.remove(look.size()-1);
			look.remove(look.size()-1);
			look.remove(look.size()-1);
			look.remove(look.size()-1);
			break;
		}
	}
	
	
	private static void bfs(int dir, int x, int y) {// 한방향으로만 탐색
		Deque<Integer[]> q = new ArrayDeque<Integer[]>();
		q.offer(new Integer[] {x, y});
		
		while (!q.isEmpty()) {
			Integer[] temp = q.pollFirst();
			x = temp[0];
			y = temp[1];
			
			int nx = x+dx[dir];
			int ny = y+dy[dir];
			if (nx>=0 && nx<N && ny>=0 && ny<M) {
				if (graph[nx][ny] == 6) {//벽 만나면 멈춤
					break; 
				}
				else if (graph[nx][ny] == 0) {
					newGraph[nx][ny] = -1; // 감시지역 -1
					q.offer(new Integer[] {nx, ny});
				}else if (graph[nx][ny] >= 1 && graph[nx][ny] <= 5) {// 카메라면 통과
					q.offer(new Integer[] {nx, ny});
				}
			}else {
				break;
			}
		}
	}
}
