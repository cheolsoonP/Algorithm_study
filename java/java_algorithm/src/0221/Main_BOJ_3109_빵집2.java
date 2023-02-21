import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_3109_빵집2 {
	// 우상 우 우하
	static int dx[] = {-1, 0, 1};
	static int dy[] = {1, 1, 1};
	static int r, c, nx, ny, ans;
	static char graph[][];
	static boolean isFind;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(in.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		graph = new char[r][c];
		for (int i = 0; i < r; i++) {
			graph[i] = in.readLine().toCharArray();
		}
		
		for (int i = 0; i < r; i++) {
			isFind = false;
			findPipe(i, 0);
		}
		
		System.out.println(ans);
	}

	private static void findPipe(int row, int col) {		
		if (isFind) return;

		graph[row][col] = 'P';
		
		if (col >= c-1) {
			isFind = true;
			ans+=1;
			return;
		}
	
		for (int dir = 0; dir < 3; dir++) {
			nx = row+dx[dir];
			ny = col+dy[dir];
			if (nx >= 0 && nx < r && ny >= 0 && ny < c) {
				if (graph[nx][ny] == '.') {
					findPipe(nx, ny);
				}
			}
		}
	}

}
