import java.util.*;
import java.io.*;

public class Main {
	static int R, C; 
	static char[][] graph;
	static int[][] fireScore;
	static int[][] userScore;
	static int[][] fireVisited, userVisited;
	
	// 상하좌우
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	static Deque<int[]> fireQueue, userQueue;
	
	static boolean isPossible;
	static int exitTime;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(in.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		graph = new char[R][C];
		fireScore = new int[R][C];
		userScore = new int[R][C];
		
		fireVisited = new int[R][C];
		userVisited = new int[R][C];
		
		fireQueue = new ArrayDeque<>();
		userQueue = new ArrayDeque<>();
		
		isPossible = false;
		
		for (int i=0; i<R; i++) {
			String str = in.readLine();
			for (int j=0;j<C;j++) {
				char c = str.charAt(j);
				graph[i][j] = c;
				if (c == 'F') {
					fireQueue.add(new int[] {i,j});
					fireVisited[i][j] = 1;
					userScore[i][j] = -1;
				} else if (c == 'J') {
					userQueue.add(new int[] {i,j});
					userVisited[i][j] = 1;
				} else if (c == '#') {
					fireScore[i][j] = -1;
					userScore[i][j] = -1;
				}
			}
		}
		
		// 불은 매분 (상하좌우) 이동 
		// 1. 불을 먼저 이동하여 각 칸에 위치하는 시간 기록 
		moveFire();
//		printFire();
		
		// 2. 지훈이가 그 맵에서 각 시간보다 작으면 못가도록 함. 
		moveUser();
//		printUser();
		if (isPossible) {
			System.out.println(exitTime);
		} else {
			System.out.println("IMPOSSIBLE");
		}
	}


	private static void moveUser() {
		// TODO Auto-generated method stub
		while (!userQueue.isEmpty()) {
			int[] pos = userQueue.pollFirst();
			
			int nx,ny; 
			for (int dir=0;dir<4;dir++) {
				nx = pos[0]+dx[dir];
				ny = pos[1]+dy[dir];
				if (nx<0||nx>=R||ny<0||ny>=C) {
					isPossible = true;
					exitTime = userScore[pos[0]][pos[1]]+1;
					return;
				}
				if (userScore[nx][ny] == -1) continue;
				if (userVisited[nx][ny] == 1) continue;
				if (fireVisited[nx][ny] == 1 && fireScore[nx][ny] <= userScore[pos[0]][pos[1]] +1) continue;
				userVisited[nx][ny] = 1;
				userScore[nx][ny] = userScore[pos[0]][pos[1]] + 1;
				userQueue.add(new int[] {nx,ny});
			}
		}
	}


	private static void moveFire() {
		while (!fireQueue.isEmpty()) {
			int[] pos = fireQueue.pollFirst();
			int nx,ny;
			for (int dir=0;dir<4;dir++) {
				nx = pos[0]+dx[dir];
				ny = pos[1]+dy[dir];
				if (nx<0||nx>=R||ny<0||ny>=C) continue;
				if (fireVisited[nx][ny] == 1) continue;
				if (fireScore[nx][ny] == -1) continue;
				if (fireScore[nx][ny] != 0 
						&& fireScore[nx][ny] < fireScore[pos[0]][pos[1]]+1) continue;
				fireVisited[nx][ny] = 1;
				fireScore[nx][ny] = fireScore[pos[0]][pos[1]]+1;
				fireQueue.add(new int[] {nx,ny});
			}
		}
	}
	
	private static void printFire() {
		System.out.println("FIRE Score!");
		for(int i=0;i<R;i++) {
			System.out.println(Arrays.toString(fireScore[i]));
		}
	}
	
	private static void printUser() {
		System.out.println("user score!");
		for(int i=0;i<R;i++) {
			System.out.println(Arrays.toString(userScore[i]));
		}
	}
}
