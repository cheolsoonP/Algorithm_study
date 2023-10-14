import java.util.*;
import java.io.*;

public class Main {
	/*
	 * 13시 시작 1420종료(디버깅 시작)
	 * 1548 디버깅 종료
	 * */
	static int N, M, K;
	// 우 하 좌 상 
	static int dx[] = {0,1,0,-1};
	static int dy[] = {1,0,-1,0};
	// 우 우하 하 좌하 좌 좌상 상 우상
	static int ddx[] = {0,1,1,1,0,-1,-1,-1};
	static int ddy[] = {1,1,0,-1,-1,-1,0,1};
	
	static int powerMap[][];//공격력 map 
	static int attackMap[][];// 공격회차 기록 map
	static int damageMap[][];// 공격받은 회차 기록 map
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(in.readLine());q
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		powerMap = new int[N][M];
		attackMap = new int[N][M];
		damageMap = new int[N][M];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(in.readLine());
			for(int j=0;j<M;j++) {
				powerMap[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// Simulation
		for(int time=1;time<=K;time++) {
//			System.out.println("TIME: "+time);
			// 1. 공격자 선정 
			int attackPos[] = pickAttacker();
//			System.out.println("공격자: "+Arrays.toString(attackPos));
			// 2. 공격
			attack(attackPos, time);
			// 3. 포탑 부서짐 (공격력 0 이하인 포탑 부서짐) 
			// 만약 부서지지 않은 포탑이 1개가 된다면 그 즉시 중지됩니다.
			if(checkBrokenTower(time) == (N*M-1)) {
//				System.out.println("포탑 1개남음");
				break;				
			}
			// 4. 포탑 정비 
			repairTower(time);
		}
		
		// 결과 확인
		int result = 0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				result = Math.max(result, powerMap[i][j]);
			}
		}
		System.out.println(result);
	}

	private static int repairTower(int time) {
		int cnt=0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(powerMap[i][j] <= 0) {
					cnt++;
					continue;
				}
				if(attackMap[i][j] != time && damageMap[i][j] != time) {
					powerMap[i][j]++;
				}
			}
		}
		return cnt;
	}

	private static int checkBrokenTower(int time) {
		int cnt=0; 
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				// 이번 공격에서 부서진 포탑 0으로 변환
				if(powerMap[i][j] <= 0 && damageMap[i][j] == time) {
					powerMap[i][j] = 0;
				}
				// 부서진 포탑 개수 세기
				if(powerMap[i][j] <= 0) cnt++;
			}
		}
		// 총 부서진 포탑 수 반환.
		return cnt;
	}

	private static void attack(int[] attackPos, int time) {
		// 공격 받을 포탑 선택
		int damagePos[] = pickDamage(attackPos);
//		System.out.println("공격대상: "+Arrays.toString(damagePos));
//		System.out.println("----공격전 타워 상황----");
//		printTower();
		// 레이저 공격 시도
		if(laserAttack(attackPos, damagePos, time)) {
			// EMPTY
		} else {
			// 레이저 공격 실패 시 포탄 공격
			ballAttack(attackPos, damagePos, time);
		}
		attackMap[attackPos[0]][attackPos[1]] = time;
//		System.out.println("----공격후 타워 상황----");
//		printTower();
	}


//	private static void printTower() {
//		for(int i=0;i<N;i++) {
//			System.out.println(Arrays.toString(powerMap[i]));
//		}
//		System.out.println();
//	}

	private static void ballAttack(int[] attackPos, int[] damagePos, int time) {
//		System.out.println("포탄 공격 성공!");
		int attackDamage = powerMap[attackPos[0]][attackPos[1]];
		powerMap[damagePos[0]][damagePos[1]] -= attackDamage;
		damageMap[damagePos[0]][damagePos[1]] = time;

		for(int dir=0;dir<8;dir++) {
			int nx = damagePos[0] + ddx[dir];
			int ny = damagePos[1] + ddy[dir];
			// 행과 행 연결, 열과 열 연결
			if(nx>0) nx = nx % N;
			if(ny>0) ny = ny % M;
			if(nx<0) nx = N + nx;
			if(ny<0) ny = M + ny;
			
			// 공격자는 제외
			if(nx == attackPos[0] && ny == attackPos[1]) continue;
			if(powerMap[nx][ny] <= 0) continue;
			powerMap[nx][ny] -= attackDamage / 2;
			damageMap[nx][ny] = time;
		}
	}

	private static boolean laserAttack(int[] attackPos, int[] damagePos, int time) {
		// 레이저 공격 시도
		// 상하좌우 이동 가능 BFS 
		// 부서진 포탑 이동 불가능 
		// 열과열은 연결, 행과 행도 연결. 
		// attackPos 에서 damagePos로 최단 거리(BFS) 찾고. 가능하다면 레이저 공격
		// 길이가 똑같다면 (우 하 좌 상 우선순위로 먼저 움직인 경로 선택)
		
		int attackDamage = powerMap[attackPos[0]][attackPos[1]];
		Deque<int[]> q = new ArrayDeque<>();
		
		boolean visit[][] = new boolean[N][M];
		q.add(new int[] {attackPos[0], attackPos[1]});
		visit[attackPos[0]][attackPos[1]] = true;

		// 핵심 : 경로를 저장하는 것. 해당 지점을 호출한 이전 노드를 저장.
		int backX[][] = new int[N][M];
		int backY[][] = new int[N][M];
		for(int i=0;i<N;i++) {
			Arrays.fill(backX[i], -1);
			Arrays.fill(backY[i], -1);
		}
			
		while(!q.isEmpty()) {			
			int x = q.peek()[0];
			int y = q.poll()[1];
			
			if(x == damagePos[0] && y == damagePos[1]) {
				// !발견! 공격 가능
				// 해당 경로로 공격해야 하는데, 어떻게 공격?? 경로를 어떻게 가지고 있지? 
				// 1번째 시도 - ArrayList를 queue에 넣는다. 0번째는 초기 x,y 이후부터 add로 좌표 저장 : 실패 
				// 정답 : 해당 노드를 호출한 좌표를 기억하고 있는다. 
//				System.out.println("레이저 공격 성공!");
				Deque<int[]> bq = new ArrayDeque<>();
				
				powerMap[damagePos[0]][damagePos[1]] -= attackDamage;
				damageMap[damagePos[0]][damagePos[1]] = time;

				// 뒤로 가야할 좌표
				bq.add(new int[] {backX[damagePos[0]][damagePos[1]], backY[damagePos[0]][damagePos[1]]});
				
				while(!bq.isEmpty()) {
					int tx = bq.peek()[0];
					int ty = bq.poll()[1];
					if(tx == attackPos[0] && ty == attackPos[1]) break;
					if(tx < 0 && ty < 0) break;
					powerMap[tx][ty] -= attackDamage / 2;
					damageMap[tx][ty] = time;

					if(backX[tx][ty] >= 0 && backY[tx][ty] >= 0) {
						bq.add(new int[] {backX[tx][ty], backY[tx][ty]});
					}
				}
				
				return true;
			}
			
			for(int dir=0;dir<4;dir++) {
				int nx = x+dx[dir];
				int ny = y+dy[dir];
				// 행과 행 연결, 열과 열 연결 
				if(nx>0) nx = nx % N;
				if(ny>0) ny = ny % M;
				if(nx<0) nx = N + nx;
				if(ny<0) ny = M + ny;
				
				if(visit[nx][ny]) continue;
				if(powerMap[nx][ny] <= 0) continue;//지나갈수없음

				backX[nx][ny] = x;
				backY[nx][ny] = y;
				q.offer(new int[] {nx,ny});
				visit[nx][ny] = true;
			}
		}
		
		return false;
	}

	private static int[] pickDamage(int[] attackPos) {
		// 공격 받을 대상 선택 
		int x = 0, y = 0; 
		int currPower = -1;
		int currTime = Integer.MAX_VALUE;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(i == attackPos[0] && j == attackPos[1]) continue;
				if(powerMap[i][j] <= 0) continue;
				if(powerMap[i][j] < currPower) continue;
				if(powerMap[i][j] > currPower) {
					x = i;
					y = j;
					currPower = powerMap[i][j];
					currTime = attackMap[i][j];
				}
				if(powerMap[i][j] == currPower) {
					// 공격했던 회차 비교
					if(attackMap[i][j] < currTime) {
						x = i;
						y = j;
						currPower = powerMap[i][j];
						currTime = attackMap[i][j];
					}
					if(attackMap[i][j] == currTime) {
						// 행+열 비교 (작은게 공격대상 선정)
						if(i+j < x+y) {
							x = i;
							y = j;
							currPower = powerMap[i][j];
							currTime = attackMap[i][j];
						}
						if(i+j == x+y) {
							// 열 값 작은거
							if(j < y) {
								x = i;
								y = j;
								currPower = powerMap[i][j];
								currTime = attackMap[i][j];
							}
						}
					}
				}
			}
		}
		
		
		return new int[] {x,y};
	}

	private static int[] pickAttacker() {
		// 
		int x=0,y=0;
		int currPower = Integer.MAX_VALUE; 
		int currTime = 0; // 공격한 회차
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(powerMap[i][j] <= 0) continue;
				if(powerMap[i][j] > currPower) continue;
				if(powerMap[i][j] < currPower) {
					x = i;
					y = j;
					currPower = powerMap[i][j];
					currTime = attackMap[i][j];
				}
				if(powerMap[i][j] == currPower) {
					// 가장 최근에 공격한 포탑인지 확인 
					if(attackMap[i][j] > currTime) {
						x = i;
						y = j;
						currPower = powerMap[i][j];
						currTime = attackMap[i][j];
					}
					if(attackMap[i][j] == currTime) {
						// 행과 열 합의 차이 비교
						if(i+j > x+y) {
							x = i;
							y = j;
							currPower = powerMap[i][j];
							currTime = attackMap[i][j];
						}
						if(i+j == x+y) {
							// 열 값 비교
							if(j > y) {
								x = i;
								y = j;
								currPower = powerMap[i][j];
								currTime = attackMap[i][j];
							}
						}
					}
				}
				
			}
		}
		// 공격자는 공격력 증가
		powerMap[x][y] += N+M;
		
		return new int[] {x,y};
	}
}
