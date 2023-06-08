import java.io.*;
import java.util.*;

public class Main {
	static int N = 12;
	static int M = 6;
	static int graph[][];
	static int score;
	static boolean isPop;
	static boolean visit[][];
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		graph = new int[N][M];
		
		for(int i=0;i<N;i++) {
			char temp[] = in.readLine().toCharArray();
			for(int j=0;j<M;j++) {
				graph[i][j] = temp[j];
			}
		}
		
		while(true) {
			pop();
			if(isPop) {
				score++;
				isPop = false;
				drop();
			}else {
				System.out.println(score);
				return;
			}
		}
	}

	private static void pop() {
		isPop = false;
		visit = new boolean[N][M];
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(graph[i][j] != '.') {
					// 색깔이 있다면 터트릴 수 있는지 확인, 터뜨리기
					popping(i,j);
				}
			}
		}
	}
	
	private static void popping(int x, int y) {
		Deque<int[]> q = new ArrayDeque<>();
		List<int[]> list = new ArrayList<>();
		list.add(new int[] {x,y});
		q.add(new int[] {x,y,graph[x][y]});//x,y,color;
		visit[x][y] = true;
		while(!q.isEmpty()) {
			x = q.peekFirst()[0];
			y = q.peekFirst()[1];
			int color = q.pollFirst()[2];
			
			for(int i=0;i<4;i++) {
				int nx = x+dx[i];
				int ny = y+dy[i];
				if(nx<0 || nx>=N || ny<0 || ny>=M) continue;
				if(graph[nx][ny] != color) continue;
				if(visit[nx][ny]) continue;
				visit[nx][ny] = true;
				q.add(new int[] {nx,ny,color});
				list.add(new int[] {nx,ny});
			}
		}
		
		if(list.size() >= 4) {
			isPop = true;
			for(int temp[]:list) {
				graph[temp[0]][temp[1]] = '.';
			}
		}

	}

	private static void drop() {
		// 각 열마다 확인 
		for(int i=0;i<M;i++) {
			Stack<Integer> q = new Stack<>();
			for(int j=0;j<N;j++) {
				q.add(graph[j][i]);
			}
			// 큐에 넣고, 하나씩 빼면서 .이면 빼기
			int cnt = 11;
			while(!q.isEmpty()) {
				int curr = q.pop();
				if(curr != '.') {
					graph[cnt][i] = curr;
					cnt--;
				}				
			}
			for(int j=cnt;j>=0;j--) {
				graph[j][i] = '.';
			}
		}
	}
}
