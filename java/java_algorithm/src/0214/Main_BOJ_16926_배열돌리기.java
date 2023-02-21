import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BOJ_16926_배열돌리기 {
	static int n, m, r, squareCnt, graph[][], newGraph[][];
	static Deque<Deque<Integer>> q;
	// 우 하 좌 상
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(in.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		graph = new int[n][m];
		newGraph = new int[n][m];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < m; j++)
				graph[i][j] = Integer.parseInt(st.nextToken());
		}
		
		squareCnt = Math.min(n/2, m/2); // 사각형 개수
		for (int i = 0; i < squareCnt; i++)
			convLine(i);
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				System.out.print(newGraph[i][j]+" ");
			}
			System.out.println();
		}
		
	}

	private static void convLine(int sqCnt) {
		int nx = sqCnt;
		int ny = sqCnt;
		int sx = sqCnt;
		int sy = sqCnt;
		int dir = 0;
		
		// 돌릴 사각형 배열 -> 1차원 변경
		Deque<Integer> arr = new ArrayDeque<>();
		arr.add(graph[nx][ny]);
		while (true) {
			nx += dx[dir];
			ny += dy[dir];
			
			if (nx == sx && ny == sy) break;// 처음 주소로 오면 종료

			arr.offer(graph[nx][ny]);
			if ((nx == n-1-sqCnt || nx == 0+sqCnt) && (ny == m-1-sqCnt || ny == 0+sqCnt)) {
				//모서리에서 방향전환
				dir = (dir+1) % 4;
			}
		}

		// R만큼 회전시키기
//		int currR = r % ((n-1-sqCnt)*2+(m-1-sqCnt)*2);
		for (int i = 0; i < r; i++)
			arr.offerLast(arr.pollFirst());

		// 그래프 다시 그리기
		dir = 0;
		while (!arr.isEmpty()) {
			newGraph[nx][ny] = arr.pollFirst();
			nx += dx[dir];
			ny += dy[dir];
			if ((nx == n-1-sqCnt || nx == 0+sqCnt) && (ny == m-1-sqCnt || ny == 0+sqCnt))
				dir = (dir+1) % 4; //모서리에서 방향전환
		}
	}		
}
