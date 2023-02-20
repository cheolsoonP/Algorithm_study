import java.util.Scanner;

public class Solution_1954_달팽이숫자 {
	static int[][] graph;
	// 오른, 아래, 왼,위
	static int []dx = {0, 1, 0, -1}; 
	static int []dy = {1, 0, -1, 0}; 
	static StringBuffer bf = new StringBuffer();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int testCase = 1; testCase <= T; testCase++) {			
			int n = sc.nextInt();
			graph = new int[n][n];
			
			int x = 0;
			int y = 0;
			int dir = 0;
			int cnt = 1;

			while(true) {
				graph[x][y] = cnt++;
				
				int nx = x+dx[dir];
				int ny = y+dy[dir];
				
				if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
					dir = (dir+1) % 4;
				} else {
					if (graph[nx][ny] > 0) {
						dir = (dir+1) % 4;			
					}
				}
				x += dx[dir];
				y += dy[dir];	

				if (cnt > n*n) break;
			}
			
			bf.append("#").append(testCase).append('\n');
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					bf.append(graph[i][j]).append(" ");
				}
				bf.append('\n');
			}
		}
		System.out.println(bf);
	}
}
