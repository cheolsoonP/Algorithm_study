import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main_1987_알파벳 {

	static HashSet<Character> set;
	static int r, c, res;
	static char graph[][];
	
	// 상 하 좌 우
	static int dx[] = {-1, 1, 0, 0};
	static int dy[] = {0, 0, -1, 1};
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		res = 0;
		graph = new char[r][c];
		set = new HashSet<>();
		for (int i = 0; i < r; i++) {
			graph[i] = in.readLine().toCharArray();
		}
		
		dfs(0, 0, 1);
		System.out.println(res);
	}
	
	private static void dfs(int row, int col, int distance) {
		int prevSize = set.size();
		set.add(graph[row][col]);

		if (prevSize != set.size()) {
			res = Math.max(res, distance);
		}
		if (prevSize == set.size()) {
			return;
		}	
		
		for (int i = 0; i < 4; i++) {
			int nx = row+dx[i];
			int ny = col+dy[i];
			if (nx>=0 && nx<r && ny>=0 && ny<c) {
				dfs(nx, ny, distance+1);				
			}
		}
		
		
	}

}
