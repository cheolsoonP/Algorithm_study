

import java.io.*;
import java.util.*;

public class Main {
	/*
	 * N N 
	 * 검은색(-1), 무지개(0), 일반(1~M)
	 * 빈곳(-9)
	 * 상하좌우 인접 
	 * 색이 같으면 같은 그룹, 무지개는 항상 가능
	 **/
	static int N, M;
	static int EMPTY = -9;
	// 상 하 좌 우
	static int dx[] = {-1, 1, 0, 0};
	static int dy[] = {0, 0, -1, 1};
	
	static int map[][];
	static int newMap[][];
	static int score;

	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		score = 0;
		N = 0;
		M = 0;
		
		st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		newMap = new int[N][N];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(in.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 블록 그룹(그룹에 속한 블록의 개수가 2개 이상)이 존재하는 동안 계속 진행
		while(true) {
			// 1. 가장 큰 블록 그룹 찾기. (총 블록 수, 무지개 블록 수, 기준 블록 - 일반 블록의(행, 열))
			// 블록수 같다면 -> 무지개 블록이 많은 것 -> 무지개도 같다면, 기준 블록이 아래에 오른쪽에 있는 것
			int removePos[] = null;
			removePos = findBlockGroup();
			if(removePos == null) {
				// 종료 더이상 삭제할 그룹이 없음.
				System.out.println(score);
				return;
			}
			
			// 2. 가장 큰 블록 그룹 삭제, 점수 획득 
			removeBlockGroup(removePos[0], removePos[1]);
			
			// 3. 중력 (검은 블록 움직x, 나머지만)
			dropBlock();
			
			// 4. 90도 반시계 회전 
			rotateReverse90();
			
			// 5. 중력 
			dropBlock();
		}
		
	}

	private static void rotateReverse90() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				newMap[N-1-j][i] = map[i][j];
			}
		}
		
		// 회전 한 Map으로 다시 복사
		for(int i=0;i<N;i++) {
			map[i] = Arrays.copyOf(newMap[i], newMap[i].length);
		}
	}

	private static void dropBlock() {
		/*
		 * 중요
		 * 열을 기준으로 하나씩 맨 아래부터 확인한다. 
		 * stack 으로 구현
		 * */
		for(int col=0;col<N;col++) {
			Stack<Integer> stack = new Stack<>();
			ArrayList<Integer> list = new ArrayList<>();
			for(int row=N-1;row>=0;row--){	
				if(map[row][col] == -1) { // -1 만나면 지금까지 쌓은 것 다 빼기
					while(!stack.isEmpty()) {
						list.add(stack.pop());
					}
					list.add(-1);
				}
				if(map[row][col] == EMPTY) { // 빈공간일 때 push
					stack.push(map[row][col]);
				}
				if(map[row][col] >= 0) {
					list.add(map[row][col]);
				}
			}
			
			while(!stack.isEmpty()) {
				list.add(stack.pop());
			}
			
			for(int idx=0;idx<N;idx++) {
				map[N-1-idx][col] = list.get(idx);
			}
		}
		// 실수!- i,j로 구분하다보니, 상하를 swap해야하는데, 좌우를 swap함
		// -> 헷갈리니까 row, col로 구분하자.
		// swap 방식은 좋지 않다. -> Stack 방식으로 빈공간을 stack으로 관리하고, 빼야할 조건을 지정해준다. 
	}

	private static void printMap() {
		for(int i=0;i<N;i++) {
			System.out.println(Arrays.toString(map[i]));
		}
		System.out.println("");
	}

	private static void removeBlockGroup(int x, int y) {
		//현재 블록 기준으로 이어지는 것들 방문 및 제거
		boolean visit[][] = new boolean[N][N];
		Deque<int[]> q = new ArrayDeque<>();
		q.add(new int[] {x,y});//x,y
		visit[x][y] = true;
		int cnt = 1;
		int currBlock = map[x][y];
		
		while(!q.isEmpty()) {
			int temp[] = q.poll();
			x = temp[0];
			y = temp[1];
			map[x][y] = EMPTY; // 삭제된 곳은 EMPTY(-99)
			for(int dir=0;dir<4;dir++) {
				int nx = x+dx[dir];
				int ny = y+dy[dir];
				if(nx<0||nx>=N||ny<0||ny>=N) continue;
				if(visit[nx][ny]) continue;
				if(map[nx][ny] == -1 || map[nx][ny] == EMPTY) continue;
 				if(map[nx][ny] == currBlock || map[nx][ny] == 0) {
					q.offer(new int[] {nx,ny});
					visit[nx][ny] = true;
					cnt++;
				}
			}
		}
		
		score += cnt*cnt;
	}

	private static int[] findBlockGroup() {
		int removePos[] = new int[] {-1,-1}; // 최대 블록 그룹의 기준 좌표
		int blockCnt = 2; // 최대 블록 개수(최소 2개 이상 연속이어야 함.)
		int rainbowCnt = 0; // 무지개 블록 개수 
		
		boolean visit[][] = new boolean[N][N];// 지나갔던 일반 블록은 건너뜀.
		int currBlock;
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(visit[i][j]) continue;
				if(map[i][j] == 0 || map[i][j] == -1 || map[i][j] == EMPTY) continue;
				boolean visitTemp[][] = new boolean[N][N];
				currBlock = map[i][j];
				Deque<int[]> q = new ArrayDeque<>();
				q.add(new int[] {i,j});//x,y,rainbow
				visitTemp[i][j] = true; // rainbow의 경우 새로운 그룹에 대해서 지나갈 수 있음.
				visit[i][j] = true;
				// 기준 블록 좌표 = i, j
				int x=-1, y=-1;				
				int cnt=1,rainbow=0;
				while(!q.isEmpty()) {
					int temp[] = q.poll();
					x = temp[0];
					y = temp[1];
					for(int dir=0;dir<4;dir++) {
						int nx = x+dx[dir];
						int ny = y+dy[dir];
						if(nx<0||nx>=N||ny<0||ny>=N) continue;
						if(visitTemp[nx][ny]) continue;
						if(map[nx][ny] == currBlock) {
							q.offer(new int[] {nx,ny});
							visit[nx][ny] = true;
							visitTemp[nx][ny] = true;
							cnt++;
						}
						if(map[nx][ny] == 0) {
							q.offer(new int[] {nx,ny});
							visitTemp[nx][ny] = true;
							cnt++;
							rainbow++;
						}
					}
				}
				
				if(cnt > blockCnt) {
					removePos[0] = i;
					removePos[1] = j;
					blockCnt = cnt;
					rainbowCnt = rainbow;
				} else if(cnt == blockCnt) {
					if(rainbow >= rainbowCnt) {
						removePos[0] = i;
						removePos[1] = j;
						blockCnt = cnt;
						rainbowCnt = rainbow;
					}
				}
			}
		}
		
		// 기준 블록 좌표가 없으면 null 리턴 
		if (removePos[0] == -1 && removePos[1] == -1) {
			return null;
		}
		return removePos;
	}

}
