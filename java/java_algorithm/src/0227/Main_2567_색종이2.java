import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n, graph[][],x,y,res;
	// 상 하 좌 우
	static int dx[] = {-1, 1, 0, 0};
	static int dy[] = {0, 0, -1, 1};
	static boolean visited[][];
	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		n = Integer.parseInt(in.readLine());
		graph = new int[101][101];
		visited = new boolean[101][101];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(in.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			for (int j = 0; j < 10; j++) {
				for (int k = 0; k < 10; k++) {
					graph[x+j][y+k] = 1;
				}
			}
		}

		for (int i = 0; i < 101; i++) {
			for (int j = 0; j < 101; j++) {
				if(graph[i][j] == 1) {
					// 상하좌우 0인지 확인
					for (int k = 0; k < 4; k++) {
						int nx = i+dx[k];
						int ny = j+dy[k];
						if(nx >= 0 && nx < 101 && ny >= 0 && ny < 101) {
							if(graph[nx][ny] == 0) {
								res++;
							}
						}
					}
				}
			}
		}

		System.out.println(res);
	}

}
