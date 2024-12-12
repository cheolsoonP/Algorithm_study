import java.util.*;
import java.io.*;
 
public class Main {
	static int totalFloor; 
	static int curr; 
	static int target; 
	static int[] dx; 
	static int down; 
	static int[] visited;
	public static void main(String[] args) throws Exception {
	
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 
		StringBuilder sb = new StringBuilder();
		
		// F, S, G, U, D
		st = new StringTokenizer(in.readLine());
		totalFloor = Integer.parseInt(st.nextToken()); // 1층 ~ total 
		curr = Integer.parseInt(st.nextToken()); 
		target = Integer.parseInt(st.nextToken());  
		dx = new int[2];
		dx[0] = Integer.parseInt(st.nextToken()); 
		dx[1] = -1 * Integer.parseInt(st.nextToken()); 
		
		visited = new int[totalFloor+1];
		Arrays.fill(visited, -1);

		Deque<int[]> deque = new ArrayDeque<>(); 
		deque.add(new int[] {curr, 0}); 
		visited[curr] = 0; 
		boolean isPass = false; 
		while (!deque.isEmpty()) {
			int[] temp = deque.poll(); 
			int x = temp[0];
			int cnt = temp[1];
			if (x == target) {
				System.out.println(visited[x]);
				isPass = true; 
				break;
			}
			for (int dir=0; dir<2; dir++) {
				int nx = x+dx[dir];
				if (nx < 1) continue; 
				if (nx > totalFloor) continue; 
				if (visited[nx] != -1) continue; 
				visited[nx] = cnt+1; 
				deque.add(new int[] {nx, cnt+1});
			}
		}
		if (!isPass) System.out.println("use the stairs");
		
	}

}
