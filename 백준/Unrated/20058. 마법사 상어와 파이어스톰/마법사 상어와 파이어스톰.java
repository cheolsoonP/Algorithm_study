import java.util.*;
import java.io.*;

public class Main {
	
	static int n, q, graph[][], result, board[][],iceSum;
	static int command[];
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	static boolean visit[][];
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(in.readLine());
		n = Integer.parseInt(st.nextToken());
		q = Integer.parseInt(st.nextToken());
		n = (int)Math.pow(2, n);
		graph = new int[n][n];
		command = new int[q];
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(in.readLine());
			for(int j=0;j<n;j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(in.readLine());
		for(int i=0;i<q;i++) {
			command[i] = Integer.parseInt(st.nextToken());
			// 1. L단계의 격자를 회전 시긴다. 시계방향으로 90도
			rotation(command[i]);
			
			// 2. -ice
			reduceIce();
		}
		
		// 
		// 가장 큰 얼음 덩어리 찾기
		visit = new boolean[n][n];
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				iceSum += graph[i][j];
				if(!visit[i][j] && graph[i][j] > 0) {
					result = Math.max(result, checkLargeIce(i, j));
				}
			}
		}
		System.out.println(iceSum);
		System.out.println(result);
		
	}

	private static int checkLargeIce(int x, int y) {
		ArrayDeque<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {x,y});
		int cnt = 1;
		visit[x][y] = true;
		while(!q.isEmpty()) {
			int temp[] = q.pollFirst();
			x = temp[0];
			y = temp[1];
			for(int i=0;i<4;i++) {
				int nx = x+dx[i];
				int ny = y+dy[i];
				if(nx<0||nx>=n||ny<0||ny>=n) continue;
				if(visit[nx][ny]) continue;
				if(graph[nx][ny] <= 0) continue;
				visit[nx][ny] = true;
				q.offer(new int[] {nx,ny});
				cnt++;
			}
		}
		return cnt;
	}

	private static void reduceIce() {
		int newGraph[][] = new int[n][n];
		for(int i=0;i<n;i++) {
			newGraph[i] = Arrays.copyOf(graph[i], graph[i].length);
		}
		// 각 칸에서 상하좌우 확인
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(graph[i][j] > 0) {					
					int cnt = bfs(i,j);
					if(cnt < 3) {
						newGraph[i][j]--;
					}
				}
			}
		}
		
		for(int i=0;i<n;i++) {
			graph[i] = Arrays.copyOf(newGraph[i], newGraph[i].length);
		}
	}

	private static int bfs(int x, int y) {
		int cnt=0;
		for(int i=0;i<4;i++) {
			int nx = x+dx[i];
			int ny = y+dy[i];
			if(nx<0||nx>=n||ny<0||ny>=n) continue;
			if(graph[nx][ny] > 0) cnt++;
		}
		return cnt;
	}

	private static void rotation(int L) {
		int size = (int)Math.pow(2, L);
		int x=0;
		int y=0;
		int[][]newGraph = new int[n][n];
		for(int i=0;i<n;i++) {
			newGraph[i] = Arrays.copyOf(graph[i], graph[i].length);
		}
		for(int i=0;i<n;i+=size) {
			for(int j=0;j<n;j+=size) {
				//i~(i+size-1) -> 범위
//				//j~(j+size-1)
				rotate_90(i,j,size,newGraph);
			}
		}
		for(int i=0;i<n;i++) {
			graph[i] = Arrays.copyOf(newGraph[i], newGraph[i].length);
		}
	}
	

	private static void printGraph() {
		System.out.println("graph");
		for(int i=0;i<n;i++) {
			System.out.println(Arrays.toString(graph[i]));
		}
	}

	private static void rotate_90(int x, int y, int size, int[][] newGraph) {
		
		for(int i=0;i<size;i++) {
			for(int j=0;j<size;j++) {
				newGraph[x+i][y+j] = graph[x+size-1-j][y+i];
			}
		}
	}
}
