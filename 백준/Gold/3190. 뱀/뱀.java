import java.util.*;
import java.io.*;
 
public class Main {

	static int N; 
	static int K; 
	static int L; 
	static int[][] apple; 
	static List<int[]> snake;
	static int[][] map;
	// 우 하 좌 상 
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
	static int dir = 0; // 방향 (처음 오른쪽) 
	static int cx; 
	static int cy; 
	public static void main(String[] args) throws Exception {
	
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 
		StringBuilder sb = new StringBuilder();
		snake = new ArrayList<>(); 

		N = Integer.parseInt(in.readLine());
		K = Integer.parseInt(in.readLine());
		apple = new int[N][N];
		snake.add(new int[] {0,0});
		
		for (int i=0;i<K;i++) {
			// 사과 놓기
			st = new StringTokenizer(in.readLine());
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			apple[x][y] = 1; 
		}
		L = Integer.parseInt(in.readLine());
		Map<Integer, Character> dirMap = new HashMap<>();
		for (int i=0;i<L;i++) {
			st = new StringTokenizer(in.readLine());
			int time = Integer.parseInt(st.nextToken());
			char turn = st.nextToken().charAt(0);
			dirMap.put(time, turn);
		}
		
		int time = 0; 
		cx = 0; 
		cy = 0; 
		while(true) {
			time++; 
			int nx = cx+dx[dir];
			int ny = cy+dy[dir];

			// 1. 머리 다음칸 이동 및 충돌 확인 
			if(moveHead(nx, ny)) {
				System.out.println(time);
				return; 
			}
			// 2. 사과 있으면 사과 삭제, 꼬리 가만히 
			checkApple(nx, ny);
			
			if (dirMap.containsKey(time)) {
				rotate(dirMap.get(time));
			}

			cx = nx;
			cy = ny; 
		}
	}
	
	private static void checkApple(int nx, int ny) {
		if (apple[nx][ny] == 1) {
			apple[nx][ny] = 0; 
		} else {
			snake.remove(0);
		}
	}
	
	private static boolean moveHead(int nx, int ny) {
		
		// 벽인지 확인 
		if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
			return true; 
		}
		// 몸 부딯치는지 확인 
		for (int[] temp : snake) {
			if (temp[0] == nx && temp[1] == ny) return true; 
		}
		snake.add(new int[] {nx,ny});
		
		return false; 
	}
	
	private static void rotate(char rot) {
		if (rot == 'D') {
			// 오른쪽 회전 
			dir = (dir+1)%4;
		} else if (rot == 'L') {
			// 왼쪽 회전 
			dir--; 
			if (dir < 0) dir = 3;
		}
	}

}
