import java.io.*;
import java.util.*;

class Town implements Comparable<Town> {
	int end;
	int cost;
	
	public Town(int end, int cost) {
		this.end = end;
		this.cost = cost;
	}
	
	@Override
	public int compareTo(Town o) {
		
		return cost - o.cost;
	}
}

public class Main {
	static int N, M, X;
	static ArrayList<Town>[] arr, arrReverse;
    static final int INF = 987654321;

	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		// X가 파티하는 곳. 
		X = Integer.parseInt(st.nextToken());
		
		arr = new ArrayList[N+1];
		arrReverse = new ArrayList[N+1];
		
		for(int i=0;i<=N;i++) {
			arr[i] = new ArrayList<>();
			arrReverse[i] = new ArrayList<>();
		}
		
		
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(in.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			arr[from].add(new Town(to, cost));
			arrReverse[to].add(new Town(from, cost));
		}
		
		// A에서 X로 가는데 걸리는 거리를 구해야 한다.
		// X에서 A로 가는데 걸리는 거리도 구해야 한다. 		
		int dist1[] = dijkstra(arr);
		int dist2[] = dijkstra(arrReverse);
		
		int ans = 0;
		for(int i=1;i<=N;i++) {
			ans = Math.max(ans, dist1[i] + dist2[i]);
		}
		
		System.out.println(ans);
		
	}


	private static int[] dijkstra(ArrayList<Town>[] arr) {
		PriorityQueue<Town> pq = new PriorityQueue<>();
		pq.offer(new Town(X, 0));

		boolean[] check = new boolean[N + 1];
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[X] = 0;
        
        while (!pq.isEmpty()) {
            Town currTown = pq.poll();
            int curr = currTown.end;
 
            if (!check[curr]) {
                check[curr] = true;
 
                for (Town town : arr[curr]) {
                    if (!check[town.end] && dist[town.end] > dist[curr] + town.cost) {
                        dist[town.end] = dist[curr] + town.cost;
                        pq.add(new Town(town.end, dist[town.end]));
                    }
                }
            }
        }
        return dist;
		
	}
}
