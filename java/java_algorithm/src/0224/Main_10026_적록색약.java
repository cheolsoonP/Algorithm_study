import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main_10026_적록색약 {

	/*
	 * 빨간색 초록색
	 * nxn RGB 하나 
	 * 몇개 구역
	 * 구역은 같은 색
	 * 같은 색 - 상하좌우인접 - 같은 구역이다
	 * 색상 차이 못느껴도 같은 색상이라고 한다 / R G 구분 X
	 * RRRBB
	 * GGBBB
	 * BBBRR
	 * BBRRR
	 * RRRRR
	 * 색약 아닌사람이보면 구역은 4개
	 * 색약 인사람 구역 구역 3개
	 * 구역의 수를 구해라
	 * */
	static int N, cnt;
	static char[][] graph;
	static boolean[][] visited;
	// 상하 좌우
	static int dx[] = {-1, 1, 0, 0};
	static int dy[] = {0, 0, -1, 1};
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(in.readLine());
		graph = new char[N][N];
		for (int i = 0; i < N; i++) {
			graph[i] = in.readLine().toCharArray();
		}
		findSection(); // 일반사람이 보는 섹션
		transform(); // 시각 변경
		findSection(); // 색약인 사람이 보는 섹션
		System.out.println(sb);
	}

	private static void transform() {
		cnt = 0; // 섹션 값 초기화
		// 일반 -> 적록색약이 보는 시야로
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (graph[i][j] == 'G') {
					graph[i][j] = 'R';
				}
			}
		}
	}

	private static void findSection() {
		visited = new boolean[N][N]; // 방문 구역 초기화
		// 색약이 없으면 R G B 로 구분
		for (int i = 0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if (!visited[i][j]) { // 방문하지 않은 곳이라면
					bfs(i, j);
					cnt++;					
				}
			}
		}
		sb.append(cnt+" ");
	}
	
	private static void bfs(int x, int y) {
		char ch = graph[x][y];
		Deque<Integer[]> deq = new ArrayDeque<Integer[]>();
		deq.offer(new Integer[] {x,y});
		visited[x][y] = true;
		
		while(!deq.isEmpty()) {
			Integer[] temp = deq.pollFirst();
			x = temp[0];
			y = temp[1];
			for (int i = 0; i < 4; i++) { // 상하좌우 방문
				int nx = x+dx[i];
				int ny = y+dy[i];
				
				if(nx >= 0 && nx < N && ny >= 0 && ny < N) {//범위 안에 있고
					if (graph[nx][ny] == ch && !visited[nx][ny]) {// 같은 색상, 방문하지 않은곳이라면
						visited[nx][ny] = true;
						deq.offer(new Integer[] {nx, ny});
					}
				}
			}
		}
	}
}
