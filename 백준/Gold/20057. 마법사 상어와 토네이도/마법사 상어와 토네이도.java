import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static double[][] map;
	static int total = 0;
	static int[] dx = {0,1,0,-1};
	static int[] dy = {-1,0,1,0};
	static double[] percent = {1,1,2,2,7,7,10,10,5,0};
	static int[][] moveX = {
			{-1,1,-2,2,-1,1,-1,1,0,0}, // 좌 
			{-1,-1,0,0,0,0,1,1,2,1}, // 하 
			{-1,1,-2,2,-1,1,-1,1,0,0}, // 우
			{1,1,0,0,0,0,-1,-1,-2,-1}, // 상 
	};
	static int[][] moveY = {
			{1,1,0,0,0,0,-1,-1,-2,-1}, // 좌 
			{-1,1,-2,2,-1,1,-1,1,0,0}, // 하 
			{-1,-1,0,0,0,0,1,1,2,1}, // 우
			{-1,1,-2,2,-1,1,-1,1,0,0}, // 상 
	};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 격자 크기
		map = new double[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Double.parseDouble(st.nextToken());
			}
		}
		
		int[] loc = {N/2, N/2}; // x, y
		int cnt = 0; // kan 만큼 이동한 횟수 
		int kan = 1; // 이동할 칸 수, cnt가 2일때마다 +1  
		int dir = 0; // 방향
		int nx = N/2, ny = N/2;
		double ySand;
		boolean flag = false;
		while(true) 
		{
			if(dir == 4) dir = 0;
			for(int k=0; k<kan; k++) {
				// y로 이동 
				nx += dx[dir];
				ny += dy[dir];
				ySand = map[nx][ny];
				// y칸의 모래 이동
				if(ySand > 0) 
					spread(nx, ny, dir, ySand);
				// 0,0 좌표의 모래까지 모두 이동했으면
				if(nx == 0 && ny == 0) {
					flag = true;
					break;
				}
			}
			if(flag) break;
			
			cnt++;
			if(cnt == 2) {
				kan++;
				cnt = 0;
			}
			dir++;
		}
		
		System.out.println(total);
	}

	private static void spread(int x, int y, int d, double ySand) {
		int nx, ny;
		double spreadSands = 0; // 이동한 모래 양 
		// 남은 모래는 a 위치로 이동하기때문에 0 
		map[x][y] -= ySand;
		for(int i=0; i<10; i++) {
			nx = x + moveX[d][i];
			ny = y + moveY[d][i];
			
			int sand = (int) Math.floor(ySand / 100 * percent[i]);
			if(i == 9) {
				double aSand = ySand - spreadSands; // 이동하고 남은 모래 양
				if(!isRange(nx,ny))
					total += aSand;
				else
					map[nx][ny] += aSand; 
			} else {
				if(!isRange(nx,ny))
					total += sand;
				else
					map[nx][ny] += sand; 
				spreadSands += sand;
			}
			
		}
	}

	private static boolean isRange(int x, int y) {
		return x < 0 || x >= N || y < 0 || y >= N ? false : true;
	}
}