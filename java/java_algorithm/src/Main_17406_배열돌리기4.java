import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main_17406_배열돌리기4 {
	static int n, m, k, minAns;
	static int graph[][], newGraph[][], r, c, s;
	// 우 하 좌 상
	static int dx[] = {0, 1, 0, -1};
	static int dy[] = {1, 0, -1, 0};
	
	
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		graph = new int[n][m];
		newGraph = new int[n][m];
		minAns = Integer.MAX_VALUE;
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < m; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(in.readLine());
			r = Integer.parseInt(st.nextToken())-1;
			c = Integer.parseInt(st.nextToken())-1;
			s = Integer.parseInt(st.nextToken());
			
			rotation(r,c,s);
		}
		for (int i = 0; i < n; i++) {
			int temp = 0;
			for (int j = 0; j < m; j++) {
				temp += graph[i][j];
			}
			minAns = Math.min(minAns, temp);
		}
		System.out.println(minAns);
	}

	private static void rotation(int row, int col, int d) {
		int boxCnt = (int) ((2*d+1)/2);
		Deque<Integer> line;
		
		for (int box = 0; box < boxCnt; box++) {
			line = new ArrayDeque<>();
			int top = row-d+box;
			int bottom = row+d-box;
			int left = col-d+box;
			int right = col+d-box;
			
			int nx = top;
			int ny = left;
			int dir = 0;
			line.offer(graph[nx][ny]);
			while(true) {
				nx+=dx[dir];
				ny+=dy[dir];
				if((nx==top&&ny==right) ||  ((nx==bottom)&&(ny==left||ny==right))) {
					dir = (dir+1)%4;
				}
				if (nx==top && ny==left) {
					dir = (dir+1)%4;
					break;
				}
				line.offer(graph[nx][ny]);
			}
			line.offerFirst(line.pollLast());
			graph[nx][ny] = line.pollFirst();
			while(true) {
				nx+=dx[dir];
				ny+=dy[dir];
				if((nx==top&&ny==right) ||  ((nx==bottom)&&(ny==left||ny==right))) {
					dir = (dir+1)%4;
				}
				if (nx==top && ny==left) {
					dir = (dir+1)%4;
					break;
				}
				graph[nx][ny] = line.pollFirst();
			}
		}
	}	

}
