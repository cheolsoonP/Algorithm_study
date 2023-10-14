package study2310;

import java.util.*;
import java.io.*;

public class Main_메이즈러너 {
	/*
	 * 시작 시간 - 6시
	 * 디버깅 시작 - 7시
	 * 
	 * N N 
	 * 각 미로는 (빈칸, 벽, 출구) 상태를 갖는다. 
	 * 벽은 내구도 1~9 
	 * 회전할때 내구도 1 감소 , 0되면 빈칸
	 * 출구 -> 참가자가 도착하면 탈출 
	 * 
	 * 1초에 모든 참가자가 한칸씩 움직인다. 
	 * 최단 거리는 x2-x1, y2-y1
	 * 모든 참가자는 동시에 움직인다. -> new map에 그리고 map에 덮어씌워야 안전함
	 * 상하좌우이동, 벽이 없는 빈칸으로 이동 가능 
	 * 움직인 칸은 출구까지 최단거리가 가까워야 한다. 멀리로 이동 불가능
	 * 2개 이상이라면, 상하 우선순위
	 * 움직일 수 없다면 안움직임 
	 * 한칸에 2명이상 있을 수 있다. -> Map으로 관리하기 어려울 듯. 좌표로 관리하는게 나을수도
	 * 
	 * 모든 참가자 이동 끝나면, 미로 회전 
	 * 
	 * 한명 이상의 참가자와 출구를 포함한 가장 작은 정사각형 기준
	 * 
	 * 가장 작은 크기 정사각형이 2개 이상이라면, 왼쪽, 위가 우선순위 
	 * 시계방향으로 90도 회전. (내구도 1 감소) 
	 * 
	 * K초 동안 반복 
	 * K초 전에 모든 참가자 탈출하면 종료
	 * 모든 참가자들의 이동거리 합과 출구 좌표를 출력. 
	 * 
	 * */
	static int N, M, K; 
	static int map[][];
	static int newMap[][];
	
	static int pos[][];
	static int EXIT = -99;
	
	// 상하 좌우 
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	
	static int moveCnt;
	static int exitCnt;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		moveCnt = 0;
		exitCnt = 0;
		
		map = new int[N][N];
		newMap = new int[N][N];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(in.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		pos = new int[M][2]; // 참가자들 x,y 좌표 (0~M번)
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(in.readLine());
			pos[i][0] = Integer.parseInt(st.nextToken())-1;
			pos[i][1] = Integer.parseInt(st.nextToken())-1;
		}
		
		st = new StringTokenizer(in.readLine());
		int exitX = Integer.parseInt(st.nextToken())-1;
		int exitY = Integer.parseInt(st.nextToken())-1;
		map[exitX][exitY] = EXIT;
		
		for(int time=1;time<=K;time++) {
//			System.out.println();
//			System.out.println();
//			System.out.println("TIME:"+time);
			int exit[] = findExit();
//			System.out.println("탈출구:" +Arrays.toString(exit));

			// 1. 참가자 이동 
			move(exit);
			if(countExit() == M) {
				// 모두 탈출했다면. 
				break;
			}
			
			// 2. 미로 회전
			rotation(exit);
		}
		int exit[] = findExit();
		System.out.println(moveCnt);
		System.out.println((exit[0]+1)+" "+(exit[1]+1));
		
	}

	private static int countExit() {
		int count = 0;
		for(int i=0;i<M;i++) {
			if(pos[i] == null) count++;
		}
//		System.out.println("탈출한 사람: "+count);
		return count;
	}

	private static void rotation(int exit[]) {
		// 1. 한명 이상 참가자와 출구 포함한 가장 작은 정사각형 찾는다. 
//		for(int i=0;i<M;i++) {
//			System.out.println(Arrays.toString(pos[i]));
//		}
//	
//		System.out.println("회전 전 미로");
//		printMap();
		
		int minLength = Integer.MAX_VALUE;
		int minX = Integer.MAX_VALUE;
		int minY = Integer.MAX_VALUE;

		boolean isFind = false;
		
		for(int size=1;size<=N;size++) {
			if(isFind) break;
			for(int i=0;i<N;i++) {
				if(isFind) break;
				for(int j=0;j<N;j++) {
					if(isExit(i, j, size, exit) && isPlayer(i, j, size)) {
						isFind = true;
						minLength = size;
						minX = i;
						minY = j;
						break;
					}					
				}
			}
		}

		// 회전시킬 사각형 따로 뽑기
		int tempSquare[][] = new int[minLength+1][minLength+1];
		int rotateSquare[][] = new int[minLength+1][minLength+1];
		for(int i=0;i<=minLength;i++) {
			for(int j=0;j<=minLength;j++) {
				tempSquare[i][j] = map[minX+i][minY+j];
			}
		}
		
//		System.out.println("회전시킬 사각형");
//		for(int i=0;i<=minLength;i++) {
//			System.out.println(Arrays.toString(tempSquare[i]));
//		}
		for(int i=0;i<=minLength;i++) {
			for(int j=0;j<=minLength;j++) {
				// 내구도 감소
				if(tempSquare[i][j] > 0) tempSquare[i][j]--;
				rotateSquare[j][minLength-i] = tempSquare[i][j];
			}
		}
//		System.out.println("사각형 회전 후");
//		for(int i=0;i<=minLength;i++) {
//			System.out.println(Arrays.toString(rotateSquare[i]));
//		}
		
		for(int i=0;i<=minLength;i++) {
			for(int j=0;j<=minLength;j++) {
				map[minX+i][minY+j] = rotateSquare[i][j];
			}
		}
		
//		System.out.println("minx, miny, length: " +minX+","+minY+","+minLength);
//		System.out.println("회전 후 미로");
//		printMap();
		
		
		// 사람들 회전
//		System.out.println("사람들 회전 전");
//		for(int i=0;i<M;i++) {
//			System.out.println(Arrays.toString(pos[i]));
//		}
		// 해당하는 사람 위치 이동(회전)
		for(int i=0;i<M;i++) { 
			if(pos[i] == null) continue;
			if(pos[i][0] >= minX && pos[i][0] <= minX+minLength
					&& pos[i][1] >= minY && pos[i][1] <= minY+minLength) {
				// 변경 대상 
				pos[i][0] -= minX;
				pos[i][1] -= minY;
				
				int tempX = pos[i][1];
				int tempY = minLength-pos[i][0];
				pos[i][0] = tempX;
				pos[i][1] = tempY;
				
				pos[i][0] += minX;
				pos[i][1] += minY;
			}
		}
//		System.out.println("사람들 회전 후");
//		for(int i=0;i<M;i++) {
//			System.out.println(Arrays.toString(pos[i]));
//		}
	}

	private static boolean isPlayer(int x, int y, int size) {
		for(int i=0;i<M;i++) {
			if(pos[i] == null) continue;
			if(pos[i][0] >= x && pos[i][0] <= x+size && pos[i][1] >= y && pos[i][1] <= y+size) {
				return true;
			}
		}
		return false;
	}

	private static boolean isExit(int x, int y, int size, int exit[]) {
		if(exit[0] >= x && exit[0] <= x+size && exit[1] >= y && exit[1] <= y+size) {
			return true;
		}
		return false;
	}

	private static void printMap() {
		for(int i=0;i<N;i++) {
			System.out.println(Arrays.toString(map[i]));
		}
		System.out.println("");
	}
	
	

	private static void move(int exit[]) {
//		System.out.println("이동 전 좌표");
//		for(int i=0;i<M;i++) {
//			System.out.println(Arrays.toString(pos[i]));
//		}
		
		int cnt = 0;
		int x=-1,y=-1;
		
		for(int i=0;i<M;i++) {
			// 탈출한 사람은 null
			if(pos[i] == null) continue;
			
			x = pos[i][0];
			y = pos[i][1];
			
			int currDist = Math.abs(exit[0] - x) + Math.abs(exit[1] - y);
			
			boolean isMove = false;
			
			for(int dir=0;dir<4;dir++) {
				int nx = x+dx[dir];
				int ny = y+dy[dir];
				int nextDist = Math.abs(exit[0] - nx) + Math.abs(exit[1] - ny);

				if(nx<0||nx>=N||ny<0||ny>=N) continue;
				if(nextDist > currDist) continue;
				if(map[nx][ny] > 0) continue;
				if(isMove) continue;
				
				moveCnt++;
				pos[i][0] = nx;
				pos[i][1] = ny;	
				isMove = true;
			}
		}	
		
		for(int i=0;i<M;i++) {
			if(pos[i] == null) continue;
			if(pos[i][0] == exit[0] && pos[i][1] == exit[1]) {
				pos[i] = null;
			}
		}
		
//		System.out.println("이동한 횟수:"+moveCnt);
	}

	private static int[] findExit() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(map[i][j] == EXIT) {
					return new int[] {i, j};
				}
			}
		}
		
//		System.out.println("출구 사라짐.");
		return null;
	}
}
