import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_BOJ_16236_아기상어 {
	static class Fish implements Comparable<Fish> {
        int row, col;
        int size;    // 크기
        int distance;// 거리

        public Fish(int row, int col, int size, int distance) {
            super();
            this.row = row;
            this.col = col;
            this.size = size;
            this.distance = distance;
        }

        // 1순위 distance, 2순위 세로 위쪽, 3순위 가로 왼쪽
        @Override
        public int compareTo(Fish o) {
            if (distance == o.distance) {//거리가 같다면
                if (row == o.row) {      
                    return Integer.compare(col, o.col); //거리가 같고 같은 행에 위치한다면 가장 좌측의 물고기를 앞으로 (3순위)
                } else {                 
                    return Integer.compare(row, o.row); //가장 위의 물고기를 앞으로(2순위)
                }
            } else {//거리가 다르다면 가까운 것을 앞으로 (1순위)
                return Integer.compare(distance, o.distance);
            }
        }
    }
	private static class Shark{
		int weight;
		int x;
		int y;
		int cnt;
		
		public Shark(int x, int y, int weight, int cnt) {
			this.x = x;
			this.y = y;
			this.weight = weight;
			this.cnt = cnt;
		}
		
		@Override
		public String toString() {
			return "[we]"+weight+" cnt:"+cnt;
		}
	}
	static Shark shark;
	static int N, graph[][], time, nx,ny;
	// 상 좌 우 하
	static int dx[] = {-1,0,0,1};
	static int dy[] = {0,-1,1,0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		N = Integer.parseInt(in.readLine());
		graph = new int[N][N];
		time = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
				if(graph[i][j] == 9) {
					shark = new Shark(i,j,2,0);
					graph[i][j] = 0;
				}
			}
		}
		while(true) {
			nx=-1; ny=-1;
			if(checkFish()) {// 먹을 물고기 있다면
				continue;
			}else {//먹을 물고기 없으면 종료
				System.out.println(time);
				return;
			}
			
		}
	}

	private static boolean checkFish() {
		int newGraph[][] = new int[N][N];

		Deque<Integer[]> q = new ArrayDeque<Integer[]>();
		boolean visited[][] = new boolean[N][N];
		
//		for (int i = 0; i < N; i++) {
//			newGraph[i] = Arrays.copyOf(graph[i], graph[i].length);
//		}
		
		newGraph[shark.x][shark.y] = 0;
		q.offer(new Integer[] {shark.x, shark.y});
		visited[shark.x][shark.y] = true;
		boolean isEat = false;
		PriorityQueue<Fish> fishes = new PriorityQueue<Fish>();
		
		while(!q.isEmpty()) {
			if(isEat) break;
			
			Integer temp[] = q.pollFirst();
			int x=temp[0];
			int y=temp[1];
			for (int i = 0; i < 4; i++) {
				nx = x+dx[i];
				ny = y+dy[i];
				if(nx>=0 && nx<N && ny>=0 & ny<N) {
					if(!visited[nx][ny]) {
						if(graph[nx][ny] > shark.weight) {//상어보다 큰 물고기면 이동불가
							continue; 
						}
						else if(graph[nx][ny] > 0 && graph[nx][ny] < shark.weight) {//먹을 수 있는 물고기
							isEat = true;
							newGraph[nx][ny] = newGraph[x][y]+1;
							fishes.add(new Fish(nx,ny,graph[nx][ny],newGraph[nx][ny]));
							
						}
						else if(graph[nx][ny]==0 || graph[nx][ny]==shark.weight) {//빈칸이거나 상어랑 같은 크기 물고기라면 통과
							newGraph[nx][ny] = newGraph[x][y]+1;
							q.offer(new Integer[] {nx,ny});
						}
						visited[nx][ny] = true;
					}
				}
			}
		}
		if(isEat) {
			//시간 증가
			Fish target = fishes.poll();
			time += target.distance;
			// 상어가 먹을 물고기가 있다면
			shark.x = target.row;
			shark.y = target.col;
			graph[target.row][target.col] = 0;
			shark.cnt++;//상어가 물고기 잡아 먹는다.
			if(shark.cnt == shark.weight) {
				shark.cnt = 0;
				shark.weight++;
			}
			return true;
		}else {
			// 상어가 먹을 물고기가 없다면
			return false;
		}
	}

}
