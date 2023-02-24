import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main_17135_캐슬디펜스 {

	/*
	 * n m 
	 * 각 칸 적 최대 1명
	 * n+1 행 - 성있다
	 * 궁수 3명 배치
	 * 성에 배치, 1칸 최대 1명 궁수
	 * 턴마다 적하나 공격 가능
	 * 동시에 공격
	 * 거리가 D이하인 적 중에서 가까운 적 공격, 적이 여럿일경우 가장 왼쪽먼저
	 * 같은 적 - 여러 궁수한테 공격 받을 수 잇다
	 * 공격받은 적 게임에서 제외
	 * 공격 -> 적 이동 적 1칸 아래 이동
	 * 성 -> 게임에서 제외
	 * 모든 적이 격자판에서 제외되면 게임 끝
	 * 격자찬 상태 -> 궁수 공격으로 제거할 수 있는 최대 적 수 
	 * */
	
	static int N, M, D, cnt;
	static int graph[][], newGraph[][];
	static List<Integer[]> position;
	static boolean visited[];
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		graph = new int[N+1][M];
		newGraph = new int[N+1][M];
		position = new ArrayList<Integer[]>();
		visited = new boolean[M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < M; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 궁수 위치 조합
		makePosition();
		for (int i = 0; i < position.size(); i++) {
			// 궁수 위치별로 시뮬레이션 진행
			
			copyGraph();
			attack(position.get(i));
			move();			
		}
	}



	private static void copyGraph() {
		for (int i = 0; i < N+1; i++) {
			newGraph[i] = Arrays.copyOf(graph[i], M);
		}
	}



	private static void makePosition() {
		dfs(0,0);
		System.out.println(position.size());
		for (int i = 0; i < position.size(); i++) {
			System.out.println(Arrays.toString(position.get(i)));			
		}
	}
	private static void dfs(int idx, int cnt) {
		if (cnt == 3) {
			Integer [] temp = new Integer[3];
			int tIdx = 0;
			for (int i = 0; i < M; i++) {
				if (visited[i]) {
					temp[tIdx++] = i;
				}
			}
			position.add(temp);
			return;
		}
		for (int i = idx; i < M; i++) {
			visited[i] = true;
			dfs(i+1, cnt+1);
			visited[i] = false;
		}
	}



	private static void attack(Integer []pos) {
		// 적 죽인다. 
		// 거리가 D 이하인 적 중 가장 가까운, 
		// 거리가 같은 적이 여럿이면 왼쪽부터
		List<Integer[]> enermyList = new ArrayList<>();
		// 궁수별 적 죽일 리스트		
		
		for (Integer arrow : pos) {
			Integer [] enermyPos = getEnermy(arrow);
			newGraph[enermyPos[0]][enermyPos[1]] = 0;//찾은 적 죽이기		
		}
		
		for (int i = 0; i < pos.length; i++) {
			for (int j = 0; j < pos.length; j++) {
				if (graph[i][j] != newGraph[i][j]) {
					cnt++;
				}
			}
		}
		
		for (int i = 0; i < N+1; i++) {
			
		}
	}
	
	
	private static Integer[] getEnermy(Integer arrow) {
		Integer [] enermyPos = new Integer[2];
		
		for (int i = N-1; i <= 0; i--) {
			for (int j = 0; j < M; j++) {
				if (graph[i][j] == 1) { // 적이라면 거리 계산
					enermyPos = new Integer[] {i, j};
					return enermyPos;
				}
			}
		}
		return null;
	}



	private static void move() {
		// 적 이동 아래로 한칸씩
		for (int i = N-1; i >= 0; i--) {
			for (int j = 0; j < N; j++) {
				if(graph[i][j] > 0) { // 적들 한칸씩 이동
					graph[i+1][j] = 1;
					graph[i][j] = 0;
					if (i == N-1) { // 성에 닿으면 사라짐
						graph[i][j] = 0;
					}
				}
			}
		}
	}

}
