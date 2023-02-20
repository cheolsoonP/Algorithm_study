import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_1388_바닥장식 {
	
	static int n, m;
	static char[][] graph;
	static boolean[][] visited;
	static int cnt;
	// 하, 우  // 한방향으로만 확인
	static int []dx = {1, 0};
	static int []dy = {0, 1};
	
	
	public static void main(String[] args) throws Exception {
		
		/*
		 * 직사각형 방
		 * 벽과 평행한 모양의 정사각형으로 나누어져있다
		 * - | 바닥 장식 모양
		 * -- 두개 인접, 같은 행 -> 같은 판자 
		 * |
		 * | 인접, 같은 열 -> 같은 판자
		 * 방바닥 장식에 필요한 나무 판자 개수 출력해라
		 * 
		 * */
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		graph = new char[n][m];
		visited = new boolean[n][m];
		
		for (int i = 0; i < n; i++) {
			graph[i] = in.readLine().toCharArray();
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (visited[i][j] == true) continue;
				bfs(i,j);
			}
		}
		System.out.println(cnt);
		
	}//main
	
	private static void bfs(int x, int y) {
		cnt++;
		int nx = x;
		int ny = y;
		
		int dir = -1;
		if (graph[x][y] == '|')
			dir = 0;
		else
			dir = 1;
		
		while(true) {
			visited[nx][ny] = true;
			nx+=dx[dir];
			ny+=dy[dir];
			
			if (nx < 0 || nx >= n || ny < 0 || ny >= m)
				break;
			if (graph[nx][ny] != graph[x][y])
				break;
		}		
	}
}
